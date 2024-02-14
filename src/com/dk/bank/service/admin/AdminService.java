package com.dk.bank.service.admin;

import java.util.LinkedList;
import java.util.List;

import com.dk.bank.concrete.Admin;
import com.dk.bank.concrete.Customer;
import com.dk.bank.repository.admin.AdminRepsoitory;
import com.dk.bank.repository.customer.CustomerRepository;
import com.dk.bank.service.helpSupport.HelpSupportService;

/**
 * AdminService
 */
public class AdminService {
    AdminRepsoitory adminRepsoitory = new AdminRepsoitory();
    CustomerRepository customerRepository = new CustomerRepository();
    List<Customer> customers = customerRepository.getAllCustomers();
    HelpSupportService hService = new HelpSupportService();
    Admin admin = new Admin();

    /*
     * to validate the admin details
     */
    public boolean login(String username, String password) {
        admin = adminRepsoitory.getAdmin(username, password);
        if(admin != null) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * to get the admin details
     */
    public Admin getAdmin() {
        return admin;
    }

    /*
     * to get all the customers under the admin bank
     */
    public List<Customer> getCustomers() {
        List<Customer> temp = new LinkedList<Customer>();
        for(Customer customer : customers) {
            if(customer.getBankName().equals(admin.getBankName())) {
                temp.add(customer);
            }
        }
        return temp;
    }

    /*
     * to add new customer
     */
    public String addCustomer(Customer customer) {
        for(Customer c : customers) {
            if(c.getUsername().equals(customer.getUsername())) {
                return "customer-already-enrolled";
            }
        }
        customers.add(customer);
        return "customer-added";
    }

    /*
     * to delete customer
     */
    public String deleteCustomer(String username) {
        for(Customer customer : customers) {
            if(customer.getUsername().equals(username)) {
                customers.remove(customer);
                return "customer-deleted";
            }
        }
        return "customer-not-found";
    }

    /*
     * to get the admin username through the bank
     */
    public Admin getAdmin(String bank) {
        return adminRepsoitory.getAdminUsername(bank);
    }

    /*
     * to view the complaints associated to the admin
     */
    public void getComplaints() {
        hService.complaintToAdmin(admin.getUsername());
    }

    /*
     * to update the compaints by the admin
     */
    public void updateComplaint(String username) {
        hService.updateComplaintByAdmin(username);
    }
}