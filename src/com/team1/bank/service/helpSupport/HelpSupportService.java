package com.team1.bank.service.helpSupport;

import java.time.LocalDateTime;
import java.util.List;

import com.team1.bank.concrete.HelpSupport;
import com.team1.bank.repository.helpSupport.HelpSupportRepository;

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
            System.out.println("You are already made a complaint. wait for until the query get resolved.");
        }
        else {
            System.out.println("Complaint made successfully.!!!");
        }
    }

    /*
     * to view the complaints according to the customer.
     */
    public void getComplaintsByCustomerUsername(String username) {
        List<HelpSupport> result = supportRepository.getComplaintsByUsername(username);
        if(result.size() == 0) {
            System.out.println("There are no complaints.!!!");
        }
        else {
            result.forEach(System.out::println);
        }
    }

    /*
     * to view the complaints according to the admin
     */
    public void getComplaintsByAdminUsername(String username) {
        List<HelpSupport> result = supportRepository.getComplaintsByAdminUsername(username);
        if(result.size() == 0) {
            System.out.println("There are no complaints.!!!");
        }
        else {
            result.forEach(System.out::println);
        }
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
            System.out.println("Unresolved Query not found to the mentioned customer.");
        }
    }
}