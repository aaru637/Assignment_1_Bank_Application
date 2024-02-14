package com.dk.bank.service.helpSupport;

import java.time.LocalDateTime;

import com.dk.bank.concrete.HelpSupport;
import com.dk.bank.repository.helpSupport.HelpSupportRepository;

/**
 * HelpSupportService
 */
public class HelpSupportService {
    private String result ;
    private HelpSupportRepository supportRepository = new HelpSupportRepository();

    /*
     * to place a query.
     */
    public void makeComplaint(String username, String adminUsername, String complaint) {
        HelpSupport support = new HelpSupport(complaint, username, LocalDateTime.now(), "pending", adminUsername, null);
        result = supportRepository.makeComplaint(support);
        if(result.equals("already-queried")) {
            System.out.println("You are already made a complaint.");
        }
        else {
            System.out.println("Complaint made successfully.!!!");
        }
    }

    /*
     * to view the complaints according to the customer.
     */
    public void complaintsByCustomer(String username) {
        supportRepository.getComplaintsByUsername(username);
    }

    /*
     * to view the complaints according to the admin
     */
    public void complaintToAdmin(String username) {
        supportRepository.getComplaintsByAdminUsername(username);
    }

    /*
     * to update the complaint by admin
     */
    public void updateComplaintByAdmin(String username) {
        result = supportRepository.updateComplaint(username);
        if(result.equals("updated")) {
            System.out.println("Query resolved successfully.");
        }
        else {
            System.out.println("Mentioned Query not found.");
        }
    }
}