
package com.example.banking.application.repository;

import com.example.banking.application.entity.IdempotencyKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdempotencyRepository
        extends JpaRepository<IdempotencyKey, String> {
}
