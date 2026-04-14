
package com.example.banking.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "idempotency_keys")
public class IdempotencyKey {

    @Id
    @Column(name = "idempotency_key", length = 64)
    private String key;

    @Column(columnDefinition = "TEXT")
    private String response;

    protected IdempotencyKey() {}

    public IdempotencyKey(String key, String response) {
        this.key = key;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
