package com.team1.bank.controller.customer;

import java.util.Scanner;
import com.team1.bank.concrete.interfaces.RBI;
import com.team1.bank.custom_exceptions.CustomerNotFoundException;
import com.team1.bank.custom_exceptions.InvalidAmountException;
import com.team1.bank.custom_exceptions.InvalidCustomerCredentialsException;
import com.team1.bank.custom_exceptions.ReachedDepositAttemptsException;
import com.team1.bank.custom_exceptions.ReachedWithdrawAttemptsException;
import com.team1.bank.service.customer.IBCustomerService;
import com.team1.bank.service.customer.IOBCustomerService;
import com.team1.bank.service.onlineTransaction.OnlineTransactionService;
import com.team1.bank.utilities.Utils;

/**
 * CustomerController
 */
public class CustomerController {
    private RBI bankService;
    private String username, password, bankName, complaint;
    private int amount;
    private OnlineTransactionService oService = new OnlineTransactionService();

    public void start(Scanner scan) {
        customerLogin(scan);
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
                    try {
                        bankService.deposit(scan.nextInt());
                        System.out.println("Amount Deposited Successfully.!!!");
                    } catch (ReachedDepositAttemptsException e) {
                        System.out.println(e);
                    }
                    Utils.singleSpaces();
                    bankService.showBalance();
                    break;

                case 3:
                    try {
                        amount = checkAmount(scan);
                    } catch (InvalidAmountException e) {
                        System.out.println(e);
                        amount = checkAmount(scan);
                    }
                    try {
                        bankService.withdraw(amount);
                        System.out.println("Withdrawn Successfully.!!!");
                    } catch (InvalidAmountException e) {
                        System.out.println(e);
                    } catch (ReachedWithdrawAttemptsException e) {
                        System.out.println(e);
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

                case 7:
                    System.out.print("Enter the username to send the amount : ");
                    String username = scan.next();
                    Utils.singleSpaces();
                    System.out.print("Enter the amount to be send to the " + username + " : ");
                    double amount = scan.nextDouble();
                    try {
                        String result = oService.onlineTransaction(bankService.getCustomer(), username, amount);
                        System.out.println(result);
                    } catch (CustomerNotFoundException e) {
                        System.out.println(e);
                    } catch (InvalidAmountException e) {
                        System.out.println(e);
                    } catch (ReachedWithdrawAttemptsException e) {
                        System.out.println(e);
                    }
                    System.out.println();
                    break;

                default:
                    System.out.println("Enter valid choice.!!!");
                    break;
            }
            bankService.remainingAttempts();
            System.out.println("Enter 'Y' or 'y' to continue in the customer panel : ");
        } while (scan.next().toLowerCase().charAt(0) == 'y');
    }

    /*
     * below methods are used inside this particular class only to simplify the
     * logic.
     */

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
                try {
                    bankService = new IOBCustomerService();
                    return bankService.login(username, password);
                } catch (InvalidCustomerCredentialsException e) {
                    System.out.println(e);
                    return customerLogin(scan);
                }

            case "ib":
                try {
                    bankService = new IBCustomerService();
                    return bankService.login(username, password);
                } catch (InvalidCustomerCredentialsException e) {
                    System.out.println(e);
                    bankService.refresh();
                    return customerLogin(scan);
                }
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
            return amount;
        } else {
            throw new InvalidAmountException("Please Enter the amount in 100's.");
        }
    }
}