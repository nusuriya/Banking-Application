package com.example.banking.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class AccountRequestDTO {

    @NotBlank(message = "First name is required")
    @Size(max = 45, message = "First name must be less than 45 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 45, message = "Last name must be less than 45 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Must be a valid email")
    private String email;

    // Optional initial balance
    private BigDecimal balance;

    /**
     * Optional status: "Y" = closed, "N" = open
     */
    @Size(min = 1, max = 1, message = "isClosed must be 'Y' or 'N'")
    private String isClosed;

    // getters & setters

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