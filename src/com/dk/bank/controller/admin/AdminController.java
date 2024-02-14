package com.dk.bank.controller.admin;

import java.util.Scanner;

import com.dk.bank.concrete.Admin;
import com.dk.bank.concrete.Customer;
import com.dk.bank.service.admin.AdminService;
import com.dk.bank.utilities.Utils;

/**
 * AdminController
 */
public class AdminController {
    private String username, password, result, cUsername, cPassword, cName;
    private int cBalance;
    private Customer customer = new Customer();

    private AdminService adminService = new AdminService();
    private Admin admin;

    private boolean adminLogin(Scanner scan) {
        System.out.print("Enter the username : ");
        username = scan.next();
        Utils.singleSpaces();
        System.out.print("Enter the password : ");
        password = scan.next();
        Utils.singleSpaces();
        return adminService.login(username, password);
    }

    private void getCustomerFrom(Scanner scan) {
        Utils.singleSpaces();
        System.out.print("Enter the username of the Customer : ");
        cUsername = scan.next();
        Utils.singleSpaces();
        System.out.print("Enter the password of the Customer : ");
        cPassword = scan.next();
        Utils.singleSpaces();
        System.out.print("Enter the balance of the Customer : ");
        cBalance = scan.nextInt();
        Utils.singleSpaces();
        System.out.print("Enter the name of the Customer : ");
        cName = scan.next();
        customer = new Customer(cName, cUsername, cPassword, cBalance, admin.getBankName());
        Utils.singleSpaces();
    }

    public void start(Scanner scan) {
        if (adminLogin(scan)) {
            admin = adminService.getAdmin();
            do {
                Utils.singleSpaces();
                Utils.adminMenus();
                System.out.print("Enter the choice : ");
                switch (scan.nextInt()) {
                    case 1:
                        getCustomerFrom(scan);
                        System.out.println(customer.getBankName());
                        result = adminService.addCustomer(customer);
                        if (result.equals("customer-added")) {
                            System.out.println("Customer added successfully.!!!");
                        } else {
                            System.out.println("Customer-already-enrolled.");
                        }
                        Utils.singleSpaces();
                        break;

                    case 2:
                        System.out.print("Enter the username of the customer : ");
                        result = adminService.deleteCustomer(scan.next());
                        if (result.equals("customer-deleted")) {
                            System.out.println("Customer deleted successfully.!!!");
                        } else {
                            System.out.println("Customer not found.!!!");
                        }
                        Utils.singleSpaces();
                        break;

                    case 3:
                        System.out.println("List of Customers.!");
                        for (Customer customer : adminService.getCustomers()) {
                            System.out.println(customer);
                        }
                        Utils.singleSpaces();
                        break;

                    case 4:
                        System.out.print("Enter the username of the customer to update the status : ");
                        adminService.updateComplaint(scan.next());
                        break;

                    case 5:
                        adminService.getComplaints();
                        break;

                    default:
                        System.out.println("Enter valid Choice.!!!");
                        break;
                }
                Utils.endDecorators();
                Utils.doubleSpaces();
                System.out.println("Enter 'Y' or 'y' to continue in admin panel : ");
            } while (scan.next().toLowerCase().charAt(0) == 'y');
        } else {
            System.out.println("Unauthorized user.!!!");
            start(scan);
        }
    }
}