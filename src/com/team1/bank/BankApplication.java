package com.team1.bank;

import java.util.Scanner;

import com.team1.bank.controller.admin.AdminController;
import com.team1.bank.controller.customer.CustomerController;
import com.team1.bank.utilities.Utils;

/**
 * BankApplication
 */
public class BankApplication {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        do {
            Utils.singleSpaces();
            Utils.initialMenus();
            System.out.print("Enter your choice : ");
            switch(scan.nextInt()) {
                case 1: 
                new AdminController().start(scan);
                break;

                case 2:
                new CustomerController().start(scan);
                break;
            }
            
            System.out.print("Enter 'Y' or 'y' to continue : ");
        } while (scan.next().toLowerCase().charAt(0) == 'y');
    }
}