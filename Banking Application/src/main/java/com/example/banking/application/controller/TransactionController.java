package com.example.banking.application.controller;

import com.example.banking.application.dto.TransactionRequestDTO;
import com.example.banking.application.dto.TransactionResponseDTO;
import com.example.banking.application.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDTO> transfer(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @Valid @RequestBody TransactionRequestDTO dto) {

        return ResponseEntity.ok(service.transfer(idempotencyKey, dto));
    }
}

