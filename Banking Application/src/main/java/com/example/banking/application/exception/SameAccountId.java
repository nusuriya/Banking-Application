package com.example.banking.application.exception;

public class SameAccountId extends RuntimeException {
    public SameAccountId(String message) {
        super(message);
    }
}
