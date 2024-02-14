package com.dk.bank.repository.customer;

import java.util.LinkedList;
import java.util.List;

import com.dk.bank.concrete.Customer;

/**
 * CustomerRepository
 */
public class CustomerRepository {
    private static List<Customer> customers = new LinkedList<Customer>(List.of(
        new Customer("Vigneshkumar", "vignesh", "vignesh", 10000, "iob"),
        new Customer("Sathishkumar", "sathish", "sathish", 10000, "iob"),
        new Customer("Ishwarya", "ishu", "ishu", 10000, "ib"),
        new Customer("Sindhu", "sindhu", "sindhu", 10000, "ib")));

    /*
     * To get a particular customer data.
     */
    public Customer getCustomer(String username, String password) {
        for(Customer customer : customers) {
            if(customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }

    /*
     * To get all customers data.
     */
    public List<Customer> getAllCustomers() {
        return customers;
    }
}