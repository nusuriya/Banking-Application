
package com.example.banking.application.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionid")
    private Integer transactionId;

    @Column(name = "from_account", nullable = false)
    private Integer fromAccount;

    @Column(name = "to_account")
    private Integer toAccount;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "creditdebit", nullable = false, length = 1)
    private String creditDebit; // C or D

    @Column(name = "description", length = 255)
    private String description;

    // ✅ Required by JPA
    protected Transaction() {}

    // ✅ Constructor used by service
    public Transaction(Integer fromAccount,
                       Integer toAccount,
                       BigDecimal amount,
                       String creditDebit,
                       String description) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.creditDebit = creditDebit;
        this.description = description;
        this.transactionDate = LocalDateTime.now();
    }

    // Getters only (immutability preferred)

    public Integer getTransactionId() {
        return transactionId;
    }

    public Integer getFromAccount() {
        return fromAccount;
    }

    public Integer getToAccount() {
        return toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getCreditDebit() {
        return creditDebit;
    }

    public String getDescription() {
        return description;
    }
}

