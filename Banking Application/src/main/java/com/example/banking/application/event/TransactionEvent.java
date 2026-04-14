
package com.example.banking.application.event;

import java.math.BigDecimal;
import java.time.Instant;

public class TransactionEvent {
    private String transactionId;
    private Integer fromAccountId;
    private Integer toAccountId;
    private BigDecimal amount;
    private String status;
    private Instant timestamp;

    public TransactionEvent(String transactionId, Integer fromAccountId,
                            Integer toAccountId, BigDecimal amount,
                            String status, Instant timestamp) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Integer getFromAccountId() {
        return fromAccountId;
    }

    public Integer getToAccountId() {
        return toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    // getters
}
