package com.dk.bank.concrete.interfaces;

/**
 * RBI
 */
public interface RBI {

    public void showBalance();
    public String deposit(double amount);
    public String withdraw(double amount);
    public boolean login(String username, String password);
    public void remainingAttempts();
    public void refresh();
    public void getCustomerStatements();
    public void makeComplaints(String complaint);
    public void getComplaints();
}