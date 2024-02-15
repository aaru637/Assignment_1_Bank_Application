package com.team1.bank.concrete;

/**
 * Admin
 */
public class Admin {
    private String name;
    private String username;
    private String password;
    private String bankName;

    public Admin() {
    }

    public Admin(String name, String username, String password, String bankName) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.bankName = bankName;
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

    @Override
    public String toString() {
        return "{Name : " + name + "\n Username : " + username + "\n Password : " + password + "\n Bank Name : "
                + bankName + "}\n";
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}