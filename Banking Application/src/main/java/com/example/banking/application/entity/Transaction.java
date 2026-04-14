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
    private Integer fromAccountId;

    @Column(name = "to_account", nullable = false)
    private Integer toAccountId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "creditdebit", nullable = false, length = 1)
    private String creditDebitFlag;

    @Column(name = "description", length = 255)
    private String description;

    public Transaction() { }

    public Transaction(Integer transactionId,
                       Integer fromAccountId,
                       Integer toAccountId,
                       BigDecimal amount,
                       LocalDateTime transactionDate,
                       String creditDebitFlag,
                       String description) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.creditDebitFlag = creditDebitFlag;
        this.description = description;
    }

    // getters & setters

    public Integer getTransactionId() { return transactionId; }
    public void setTransactionId(Integer transactionId) { this.transactionId = transactionId; }
    public Integer getFromAccountId() { return fromAccountId; }
    public void setFromAccountId(Integer fromAccountId) { this.fromAccountId = fromAccountId; }
    public Integer getToAccountId() { return toAccountId; }
    public void setToAccountId(Integer toAccountId) { this.toAccountId = toAccountId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
    public String getCreditDebitFlag() { return creditDebitFlag; }
    public void setCreditDebitFlag(String creditDebitFlag) { this.creditDebitFlag = creditDebitFlag; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", fromAccountId=" + fromAccountId +
                ", toAccountId=" + toAccountId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                ", creditDebitFlag='" + creditDebitFlag + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}