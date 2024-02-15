package com.team1.bank.custom_exceptions;

/**
 * ReachedDepositAttemptsException
 */
public class ReachedDepositAttemptsException extends RuntimeException {

    public ReachedDepositAttemptsException(String message) {
        super(message);
    }
}