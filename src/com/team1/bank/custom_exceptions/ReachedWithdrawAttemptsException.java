package com.team1.bank.custom_exceptions;

/**
 * ReachedWithdrawAttemptsException
 */
public class ReachedWithdrawAttemptsException extends RuntimeException {

    public ReachedWithdrawAttemptsException(String message) {
        super(message);
    }
}