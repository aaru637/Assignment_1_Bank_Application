package com.team1.bank.custom_exceptions;

/**
 * AdminNotFoundException
 */
public class AdminNotFoundException extends RuntimeException {

    public AdminNotFoundException(String message) {
        super(message);
    }
}