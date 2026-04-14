package com.example.banking.application.exception;

public class AccountClosedException extends RuntimeException {
    public AccountClosedException(String msg) {
        super(msg);
    }
}