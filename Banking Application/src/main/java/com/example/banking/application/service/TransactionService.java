package com.example.banking.application.service;

import com.example.banking.application.dto.TransactionRequestDTO;
import com.example.banking.application.dto.TransactionResponseDTO;
import com.example.banking.application.entity.Account;
import com.example.banking.application.entity.Transaction;
import com.example.banking.application.exception.*;
import com.example.banking.application.repository.AccountRepository;
import com.example.banking.application.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final AccountRepository accountRepo;
    private final TransactionRepository txRepo;

    public TransactionService(AccountRepository accountRepo,
                              TransactionRepository txRepo) {
        this.accountRepo = accountRepo;
        this.txRepo = txRepo;
    }

    @Transactional
    public TransactionResponseDTO transfer(TransactionRequestDTO dto) {
        Account from = accountRepo.findById(dto.getFromAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Source account not found"));
        Account to = accountRepo.findById(dto.getToAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Destination account not found"));

        // Closed‐account checks now look at the string flag
        if ("Y".equalsIgnoreCase(from.getIsClosed())) {
            throw new AccountClosedException("Source account is closed");
        }
        if ("Y".equalsIgnoreCase(to.getIsClosed())) {
            throw new AccountClosedException("Destination account is closed");
        }

        if (from.getBalance().compareTo(dto.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        // Perform funds movement
        from.setBalance(from.getBalance().subtract(dto.getAmount()));
        to.setBalance(to.getBalance().add(dto.getAmount()));
        accountRepo.save(from);
        accountRepo.save(to);

        // Record the transaction
        Transaction tx = new Transaction();
        tx.setFromAccountId(dto.getFromAccountId());
        tx.setToAccountId(dto.getToAccountId());
        tx.setAmount(dto.getAmount());
        tx.setTransactionDate(LocalDateTime.now());
        tx.setCreditDebitFlag("D");
        tx.setDescription(dto.getDescription());
        Transaction saved = txRepo.save(tx);

        // Map to response DTO
        TransactionResponseDTO resp = new TransactionResponseDTO();
        resp.setTransactionId(saved.getTransactionId());
        resp.setFromAccountId(saved.getFromAccountId());
        resp.setToAccountId(saved.getToAccountId());
        resp.setAmount(saved.getAmount());
        resp.setTransactionDate(saved.getTransactionDate());
        resp.setCreditDebitFlag(saved.getCreditDebitFlag());
        resp.setDescription(saved.getDescription());
        return resp;
    }
}
