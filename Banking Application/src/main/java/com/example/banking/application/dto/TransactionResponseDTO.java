package com.example.banking.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponseDTO {
    private Integer transactionId;
    private Integer fromAccountId;
    private Integer toAccountId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private String creditDebitFlag;
    private String description;

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
}

