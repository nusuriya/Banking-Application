package com.example.banking.application.service;

import com.example.banking.application.dto.AccountRequestDTO;
import com.example.banking.application.entity.Account;
import com.example.banking.application.exception.ResourceNotFoundException;
import com.example.banking.application.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public Account create(AccountRequestDTO dto) {
        Account acc = new Account();
        acc.setFirstName(dto.getFirstName());
        acc.setLastName(dto.getLastName());
        acc.setEmail(dto.getEmail());
        if (dto.getBalance() != null) {
            acc.setBalance(dto.getBalance());
        }
        // default is "N" in entity
        if (dto.getIsClosed() != null) {
            acc.setIsClosed(dto.getIsClosed());
        }
        return repo.save(acc);
    }

    public Account getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found: " + id));
    }

    public List<Account> getAll() {
        return repo.findAll();
    }

    public Account update(Integer id, AccountRequestDTO dto) {
        Account acc = getById(id);
        if (dto.getFirstName() != null) acc.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null)  acc.setLastName(dto.getLastName());
        if (dto.getEmail() != null)     acc.setEmail(dto.getEmail());
        if (dto.getIsClosed() != null)  acc.setIsClosed(dto.getIsClosed());
        // balance changes happen via transactions only
        return repo.save(acc);
    }
}