package com.team1.bank.custom_exceptions;

/**
 * CustomerNotFoundException
 */
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}