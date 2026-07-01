package com.scsrs.reports;

import com.scsrs.users.Resident;

/**
 * Represents a community service request submitted by a resident.
 *
 * @author Shonisani
 * @version 1.0
 */
public class Report {

    // ==========================
    // Attributes
    // ==========================
    private int reportId;
    private String title;
    private String description;
    private String category;
    private String status;
    private Resident resident;

    // ==========================
    // Constructor
    // ==========================
    public Report(int reportId, String title, String description,
                  String category, String status, Resident resident) {

        this.reportId = reportId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = status;
        this.resident = resident;
    }

    // ==========================
    // Getters and Setters
    // ==========================

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    // ==========================
    // toString()
    // ==========================

    @Override
    public String toString() {
        return "Report ID: " + reportId +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nCategory: " + category +
                "\nStatus: " + status +
                "\nSubmitted by : " + resident.getFullName();
    }
}