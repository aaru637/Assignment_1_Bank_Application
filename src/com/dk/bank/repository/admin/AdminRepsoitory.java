package com.dk.bank.repository.admin;

import java.util.LinkedList;
import java.util.List;
import com.dk.bank.concrete.Admin;

/**
 * AdminRepsoitory
 */
public class AdminRepsoitory {
    private static List<Admin> admins = new LinkedList<Admin>(
            List.of(new Admin("Dhineshkumar", "dhinesh", "dhinesh", "iob"),
                    new Admin("Ajith", "ajith", "ajith", "sbi"),
                    new Admin("Arun", "arun", "arun", "ib")));

    /*
     * To get the particular admin data.
     */
    public Admin getAdmin(String username, String password) {
        return admins.stream().filter(admin -> admin.getUsername().equals(username) && admin.getPassword().equals(password)).findFirst().orElse(null);
    }

    /*
     * to get the admin using the bank name
     */
    public Admin getAdminUsername(String bank) {
        return admins.stream().filter(admin -> admin.getBankName().equals(bank)).findFirst().orElse(null);
    }

    /*
     * To get all the admins data.
     */
    public List<Admin> getAllAdmin() {
        return admins;
    }
}