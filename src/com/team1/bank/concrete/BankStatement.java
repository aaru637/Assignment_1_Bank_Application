package com.team1.bank.concrete;

import java.time.LocalDateTime;

/**
 * BankStatement
 */
public class BankStatement {

    private String username;
    private String type;
    private double amount;
    private double userBalance;
    private LocalDateTime transactionDate;
    private String bankName;
    
    public BankStatement() {
    }

    public BankStatement(String username, String type, double amount, double userBalance, LocalDateTime transactionDate,
            String bankName) {
        this.username = username;
        this.type = type;
        this.amount = amount;
        this.userBalance = userBalance;
        this.transactionDate = transactionDate;
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "{ Username : " + username + ", Type : " + type + ", Amount : " + amount + ", User Balance : "
                + userBalance + ", Transaction Date : " + transactionDate + ", Bank Name : " + bankName + "}\n";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}