package com.team1.bank.concrete;

import java.util.LinkedList;
import java.util.List;

/**
 * Customer
 */
public class Customer {
    private String name;
    private String username;
    private String password;
    private double balance;
    private String bankName;
    private List<BankStatement> statements;
    private int withdrawAttempts;
    private int depositAttempts;
    
    public Customer() {
    }

    public Customer(String name, String username, String password, double balance, String bankName) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.bankName = bankName;
        withdrawAttempts = 3;
        depositAttempts = 3;
        this.statements = new LinkedList<BankStatement>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{ Name : " + name + ", Username : " + username + ", Password : " + password + ", Balance : " + balance
                + "}\n";
    }

    public int getWithdrawAttempts() {
        return withdrawAttempts;
    }

    public void setWithdrawAttempts(int withdrawAttempts) {
        this.withdrawAttempts = withdrawAttempts;
    }

    public int getDepositAttempts() {
        return depositAttempts;
    }

    public void setDepositAttempts(int depositAttempts) {
        this.depositAttempts = depositAttempts;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<BankStatement> getStatements() {
        return statements;
    }

    public void setStatements(BankStatement statements) {
        this.statements.add(statements);
    }
    
}