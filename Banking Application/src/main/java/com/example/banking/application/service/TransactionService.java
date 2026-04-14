
package com.example.banking.application.service;

import com.example.banking.application.dto.TransactionRequestDTO;
import com.example.banking.application.dto.TransactionResponseDTO;
import com.example.banking.application.entity.Account;
import com.example.banking.application.entity.IdempotencyKey;
import com.example.banking.application.entity.Transaction;
import com.example.banking.application.event.TransactionEvent;
import com.example.banking.application.kafka.TransactionEventProducer;
import com.example.banking.application.repository.AccountRepository;
import com.example.banking.application.repository.IdempotencyRepository;
import com.example.banking.application.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.banking.application.exception.AccountClosedException;
import com.example.banking.application.exception.InsufficientBalanceException;
import com.example.banking.application.exception.ResourceNotFoundException;
import com.example.banking.application.exception.SameAccountId;



import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final IdempotencyRepository idempotencyRepository;
    private final TransactionEventProducer producer;

    public TransactionService(AccountRepository accountRepository,
                              TransactionRepository transactionRepository,
                              IdempotencyRepository idempotencyRepository,
                              TransactionEventProducer producer) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.idempotencyRepository = idempotencyRepository;
        this.producer = producer;
    }

    @Transactional
    public TransactionResponseDTO transfer(String idempotencyKey,
                                           TransactionRequestDTO dto) {

        /* ---------------------------
           Idempotency check
           --------------------------- */
        Optional<IdempotencyKey> existing =
                idempotencyRepository.findById(idempotencyKey);

        if (existing.isPresent()) {
            return TransactionResponseDTO.fromJson(existing.get().getResponse());
        }

        /* ---------------------------
           Load accounts
           --------------------------- */
        Account fromAccount = accountRepository.findById(dto.getFromAccountId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Source account not found"));

        Account toAccount = accountRepository.findById(dto.getToAccountId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Destination account not found"));

        if(dto.getFromAccountId().equals(dto.getToAccountId())) {
            throw new SameAccountId("Source and Destination account can't be the same");
        }

        /* ---------------------------
           Account closed validation
           --------------------------- */
        if ("Y".equalsIgnoreCase(fromAccount.getIsClosed())) {
            throw new AccountClosedException("Source account is closed");
        }

        if ("Y".equalsIgnoreCase(toAccount.getIsClosed())) {
            throw new AccountClosedException("Destination account is closed");
        }

        /* ---------------------------
           Balance validation
           --------------------------- */
        BigDecimal transferAmount = dto.getAmount();

        if (fromAccount.getBalance().compareTo(transferAmount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        /* ---------------------------
           Update balances
           --------------------------- */
        fromAccount.setBalance(fromAccount.getBalance().subtract(transferAmount));
        toAccount.setBalance(toAccount.getBalance().add(transferAmount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        /* ---------------------------
           Persist transactions
           --------------------------- */
        Transaction debitTxn = new Transaction(
                fromAccount.getAccountId(),
                toAccount.getAccountId(),
                transferAmount,
                "D",
                "Amount debited"
        );

        Transaction creditTxn = new Transaction(
                fromAccount.getAccountId(),
                toAccount.getAccountId(),
                transferAmount,
                "C",
                "Amount credited"
        );

        transactionRepository.save(debitTxn);
        transactionRepository.save(creditTxn);

        /* ---------------------------
           Build response
           --------------------------- */
        String referenceId = UUID.randomUUID().toString();
        TransactionResponseDTO response =
                new TransactionResponseDTO(referenceId, "SUCCESS");

        /* ---------------------------
           Save idempotency key
           --------------------------- */
        idempotencyRepository.save(
                new IdempotencyKey(idempotencyKey, response.toJson())
        );

        /* ---------------------------
           Publish Kafka event
           --------------------------- */
        TransactionEvent event = new TransactionEvent(
                referenceId,
                fromAccount.getAccountId(),
                toAccount.getAccountId(),
                transferAmount,
                "COMPLETED",
                Instant.now()
        );

        producer.publish(event);

        return response;
    }
}
