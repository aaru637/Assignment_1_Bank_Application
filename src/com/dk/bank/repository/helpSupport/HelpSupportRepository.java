package com.dk.bank.repository.helpSupport;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.dk.bank.concrete.HelpSupport;

/**
 * HelpSupportRepository
 */
public class HelpSupportRepository {

    private static List<HelpSupport> supports = new LinkedList<>();

    /*
     * to get the supports by the customer username
     */
    public void getComplaintsByUsername(String username) {
        supports.stream().filter(help -> help.getUsername().equals(username)).forEach(System.out::println);
    }

    /*
     * to get the supports by the admin username
     */
    public void getComplaintsByAdminUsername(String username) {
        supports.stream().filter(help -> help.getAdminUsername().equals(username)).forEach(System.out::println);
    }

    /*
     * to add the complaints.
     */
    public String makeComplaint(HelpSupport support) {
        if (supports.stream().filter(help -> help.getUsername().equals(support.getUsername()))
                .collect(Collectors.toList())
                .size() == 1) {
            return "already-queried";
        } else {
            supports.add(support);
            return "queried";
        }
    }

    /*
     * to update the complaint by admin
     */
    public String updateComplaint(String username) {
        return supports.stream().filter(help -> help.getUsername().equals(username)).map(h -> {
            h.setStatus("resolved");
            h.setDateResolved(LocalDateTime.now());
            return h;
        }).collect(Collectors.toList()).size() == 1 ? "updated" : "query-not-found";
    }
}