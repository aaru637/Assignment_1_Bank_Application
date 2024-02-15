package com.team1.bank.repository.helpSupport;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.team1.bank.concrete.HelpSupport;

/**
 * HelpSupportRepository
 */
public class HelpSupportRepository {

    private static List<HelpSupport> supports = new LinkedList<>();

    /*
     * to get the supports by the customer username
     */
    public List<HelpSupport> getComplaintsByUsername(String username) {
        return supports.stream().filter(help -> help.getUsername().equals(username)).sorted((a, b) -> a.getStatus().compareTo(b.getStatus())).collect(Collectors.toList());
    }

    /*
     * to get the supports by the admin username
     */
    public List<HelpSupport> getComplaintsByAdminUsername(String username) {
        return supports.stream().filter(help -> help.getAdminUsername().equals(username)).sorted((a, b) -> a.getStatus().compareTo(b.getStatus())).collect(Collectors.toList());
    }

    /*
     * to add the complaints.
     */
    public String makeComplaint(HelpSupport support) {
        if (supports.stream().filter(help -> help.getUsername().equals(support.getUsername()) && help.getStatus().equals("pending")).collect(Collectors.toList()).size() == 1) {
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
        return supports.stream().filter(help -> help.getUsername().equals(username) && help.getStatus().equals("pending")).map(h -> {
            h.setStatus("resolved");
            h.setDateResolved(LocalDateTime.now());
            return h;
        }).collect(Collectors.toList()).size() == 1 ? "updated" : "query-not-found";
    }
}