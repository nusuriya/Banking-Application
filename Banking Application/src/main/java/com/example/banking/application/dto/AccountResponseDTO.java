
package com.example.banking.application.dto;

import java.math.BigDecimal;

public class AccountResponseDTO {
    private Integer accountId;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal balance;

    /**
     * "Y" = closed, "N" = open
     */
    private String isClosed;

    // getters & setters

    public Integer getAccountId() { return accountId; }
    public void setAccountId(Integer accountId) { this.accountId = accountId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public String getIsClosed() { return isClosed; }
    public void setIsClosed(String isClosed) { this.isClosed = isClosed; }
}