package com.dk.bank.controller.customer;

import java.util.Scanner;

import com.dk.bank.concrete.interfaces.RBI;
import com.dk.bank.service.customer.IBCustomerService;
import com.dk.bank.service.customer.IOBCustomerService;
import com.dk.bank.utilities.Utils;

/**
 * CustomerController
 */
public class CustomerController {
    private RBI bankService;
    private String username, password, bankName, result, complaint;
    private int amount;

    private boolean customerLogin(Scanner scan) {
        System.out.print("Enter the username : ");
        username = scan.next();
        Utils.singleSpaces();
        System.out.print("Enter the password : ");
        password = scan.next();
        Utils.singleSpaces();
        System.out.print("Enter the bank name (iob or ib) : ");
        bankName = scan.next().toLowerCase();
        switch (bankName) {
            case "iob":
                bankService = new IOBCustomerService();
                return bankService.login(username, password);

            case "ib":
                bankService = new IBCustomerService();
                return bankService.login(username, password);
        }
        return false;
    }

    /*
     * getting the complaint from the customer
     */
    private String getQuery(Scanner scan) {
        Utils.singleSpaces();
        System.out.print("Enter the complaint : ");
        scan.nextLine();
        complaint = scan.nextLine();
        if (complaint == "") {
            System.out.println("Please mention any complaint. Don't be empty.");
            return getQuery(scan);
        } else {
            return complaint;
        }
    }

    /*
     * check the amount is matching the system format or not.
     */
    private int checkAmount(Scanner scan) {
        System.out.print("Enter the amount to be withdraw : ");
        amount = scan.nextInt();
        if (amount % 100 != 0) {
            System.out.println("Enter the amount in 100's.!!!");
            return checkAmount(scan);
        } else {
            return amount;
        }
    }

    public void start(Scanner scan) {
        if (customerLogin(scan)) {
            System.out.println();
            do {
                Utils.customerMenus();
                System.out.print("Enter your Choice : ");
                switch (scan.nextInt()) {
                    case 1:
                        bankService.showBalance();
                        break;

                    case 2:
                        System.out.print("Enter the amount to be deposit : ");
                        result = bankService.deposit(scan.nextInt());
                        if (result.equals("deposited-success")) {
                            System.out.println("Amount Deposited Successfully.!!!");
                        } else {
                            System.out.println("You have reached your daiy attempts.!!!");
                        }
                        Utils.singleSpaces();
                        bankService.showBalance();
                        break;

                    case 3:
                        amount = checkAmount(scan);
                        result = bankService.withdraw(amount);
                        if (result.equals("withdrawn-success")) {
                            System.out.println("Withdrawn Successfully.!!!");
                        } else if (result.equals("low-min-balance")) {
                            System.out.println(
                                    "You have low minimum balance in your account. so you can't withdraw . please mention low amount.");
                        } else {
                            System.out.println("You have reached your daily attempts.!!!");
                        }
                        Utils.singleSpaces();
                        bankService.showBalance();
                        break;

                    case 4:
                        bankService.getCustomerStatements();
                        Utils.singleSpaces();
                        break;

                    case 5:
                        bankService.makeComplaints(getQuery(scan));
                        break;

                    case 6:
                        Utils.singleSpaces();
                        bankService.getComplaints();
                        Utils.singleSpaces();
                        break;

                    default:
                        System.out.println("Enter valid choice.!!!");
                        break;
                }
                bankService.remainingAttempts();
                System.out.println("Enter 'Y' or 'y' to continue in the customer panel : ");
            } while (scan.next().toLowerCase().charAt(0) == 'y');
        } else {
            System.out.println("Unauthorized customer.!!!");
            bankService.refresh();
            start(scan);
        }

    }
}