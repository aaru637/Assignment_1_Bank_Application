package com.team1.bank.custom_exceptions;

/**
 * InvalidAmountException
 */
public class InvalidAmountException extends RuntimeException {
    
    public InvalidAmountException(String message) {
        super(message);
    }
}