package com.team1.bank.custom_exceptions;

/**
 * InvalidCustomerCredentialsException
 */
public class InvalidCustomerCredentialsException extends RuntimeException {
    
    public InvalidCustomerCredentialsException(String message) {
        super(message);
    }
}