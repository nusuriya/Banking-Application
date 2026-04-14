package com.example.banking.application.controller;

import com.example.banking.application.dto.AccountRequestDTO;
import com.example.banking.application.dto.AccountResponseDTO;
import com.example.banking.application.entity.Account;
import com.example.banking.application.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    /**
     * POST /api/accounts
     * Creates a new account.
     * Body: AccountRequestDTO
     * Returns the created resource as AccountResponseDTO.
     */
    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(
            @Valid @RequestBody AccountRequestDTO dto) {

        Account created = service.create(dto);
        return ResponseEntity.ok(toResponse(created));
    }

    /**
     * GET /api/accounts/{id}
     * Fetches a single account by its ID.
     * Path Variable: id
     * Returns AccountResponseDTO or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getById(
            @PathVariable Integer id) {

        Account acc = service.getById(id);
        return ResponseEntity.ok(toResponse(acc));
    }

    /**
     * GET /api/accounts
     * Fetches all accounts.
     * Returns list of AccountResponseDTO.
     */
    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAll() {
        List<AccountResponseDTO> list = service.getAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    /**
     * PUT /api/accounts/{id}
     * Updates name, email or closed‐status of an existing account.
     * Path Variable: id
     * Body: AccountRequestDTO
     * Returns updated AccountResponseDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody AccountRequestDTO dto) {

        Account updated = service.update(id, dto);
        return ResponseEntity.ok(toResponse(updated));
    }

    // ----------------------------------------------------
    // Mapper: convert Account entity to AccountResponseDTO
    // ----------------------------------------------------
    private AccountResponseDTO toResponse(Account acc) {
        AccountResponseDTO res = new AccountResponseDTO();
        res.setAccountId(acc.getAccountId());
        res.setFirstName(acc.getFirstName());
        res.setLastName(acc.getLastName());
        res.setEmail(acc.getEmail());
        res.setBalance(acc.getBalance());
        res.setIsClosed(acc.getIsClosed());
        return res;
    }
}