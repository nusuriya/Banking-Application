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
            @Valid @RequestBody TransactionRequestDTO dto) {
        TransactionResponseDTO resp = service.transfer(dto);
        return ResponseEntity.ok(resp);
    }
}
