
package com.example.banking.application.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionResponseDTO {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private String transactionId;
    private String status;

    public TransactionResponseDTO() {
    }

    public TransactionResponseDTO(String transactionId, String status) {
        this.transactionId = transactionId;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getStatus() {
        return status;
    }

    /* -----------------------------
       Idempotency helpers
       ----------------------------- */

    public String toJson() {
        try {
            return OBJECT_MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize TransactionResponseDTO", e);
        }
    }

    public static TransactionResponseDTO fromJson(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, TransactionResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize TransactionResponseDTO", e);
        }
    }
}
