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
        for (Admin admin : admins) {
            if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
                return admin;
            }
        }
        return null;
    }

    /*
     * to get the admin using the bank name
     */
    public Admin getAdminUsername(String bank) {
        for (Admin admin : admins) {
            if(admin.getBankName().equals(bank)) {
                return admin;
            }
        }
        return null;
    }

    /*
     * To get all the admins data.
     */
    public List<Admin> getAllAdmin() {
        return admins;
    }
}