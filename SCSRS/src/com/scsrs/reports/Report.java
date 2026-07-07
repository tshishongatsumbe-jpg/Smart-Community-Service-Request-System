package com.scsrs.reports;

import com.scsrs.enums.ReportCategory;
import com.scsrs.enums.ReportStatus;
import com.scsrs.users.Resident;
import com.scsrs.users.FieldWorker;


/**
 * Represents a community service request submitted by a resident.
 *
 * @author Shonisani
 * @version 2.0
 */
public class Report {

    // ==========================
    // Attributes
    // ==========================

    private int reportId;
    private String title;
    private String description;
    private ReportCategory category;
    private ReportStatus status;
    private Resident resident;
    private FieldWorker assignedWorker;

    // ==========================
    // Constructor
    // ==========================

    /**
     * Creates a new community service report.
     *
     * @param reportId Unique report ID.
     * @param title Report title.
     * @param description Detailed description of the issue.
     * @param category Category of the report.
     * @param status Current report status.
     * @param resident Resident who submitted the report.
     */
    public Report(int reportId,
                  String title,
                  String description,
                  ReportCategory category,
                  ReportStatus status,
                  Resident resident) {

        this.reportId = reportId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = status;
        this.resident = resident;
        this.assignedWorker = null;
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

    public ReportCategory getCategory() {
        return category;
    }

    public void setCategory(ReportCategory category) {
        this.category = category;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }
    public FieldWorker getAssignedWorker() {
        return assignedWorker;
    }
    public void setAssignedWorker(FieldWorker assignedWorker) {
        this.assignedWorker = assignedWorker;
    }

    // ==========================
    // Business Methods
    // ==========================

    /**
     * Checks whether the report has been resolved.
     *
     * @return true if the report is resolved; otherwise false.
     */
    public boolean isResolved() {
        return status == ReportStatus.RESOLVED;
    }

    // ==========================
    // toString()
    // ==========================

    @Override
    public String toString() {

        String workerName = assignedWorker == null
                ? "Not Assigned"
                : assignedWorker.getFullName();

        return "Report ID: " + reportId +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nCategory: " + category +
                "\nStatus: " + status +
                "\nSubmitted By: " + resident.getFullName() +
                "\nAssigned Worker: " + workerName;
    }
}