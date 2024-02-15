package com.team1.bank.concrete.interfaces;

import com.team1.bank.concrete.Customer;

/**
 * RBI
 */
public interface RBI {

    public void showBalance();
    public boolean deposit(double amount);
    public boolean withdraw(double amount);
    public boolean login(String username, String password);
    public void remainingAttempts();
    public void refresh();
    public void getCustomerStatements();
    public void makeComplaints(String complaint);
    public void getComplaints();
    public Customer getCustomer();
}