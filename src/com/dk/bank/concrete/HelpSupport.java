package com.dk.bank.concrete;

import java.time.LocalDateTime;

/**
 * HelpSupport
 */
public class HelpSupport {
    private String complaint;
    private String username;
    private LocalDateTime date;
    private String status;
    private String adminUsername;
    private LocalDateTime dateResolved;
    
    public HelpSupport() {
    }

    public HelpSupport(String complaint, String username, LocalDateTime date, String status, String adminUsername,
            LocalDateTime dateResolved) {
        this.complaint = complaint;
        this.username = username;
        this.date = date;
        this.status = status;
        this.adminUsername = adminUsername;
        this.dateResolved = dateResolved;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public LocalDateTime getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(LocalDateTime dateResolved) {
        this.dateResolved = dateResolved;
    }

    @Override
    public String toString() {
        return "{ Complaint = " + complaint + ", Username = " + username + ", Date = " + date + ", Status = "
                + status + ", Admin Username = " + adminUsername + ", Date Resolved = " + dateResolved + "}\n";
    }
    
}