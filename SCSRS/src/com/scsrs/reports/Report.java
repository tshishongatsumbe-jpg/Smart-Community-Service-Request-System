package com.scsrs.reports;

import com.scsrs.enums.ReportCategory;
import com.scsrs.enums.ReportStatus;
import com.scsrs.users.FieldWorker;
import com.scsrs.users.Resident;

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
    private int serviceNumber;

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
     * @param reportId Unique system report ID.
     * @param title Report title.
     * @param description Report description.
     * @param category Report category.
     * @param status Report status.
     * @param resident Resident who submitted the report.
     * @param serviceNumber Service number within the selected category.
     */
    public Report(int reportId,
                  String title,
                  String description,
                  ReportCategory category,
                  ReportStatus status,
                  Resident resident,
                  int serviceNumber) {

        this.reportId = reportId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = status;
        this.resident = resident;
        this.assignedWorker = null;
        this.serviceNumber = serviceNumber;
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

    public int getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(int serviceNumber) {
        this.serviceNumber = serviceNumber;
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
     * @return true if resolved; otherwise false.
     */
    public boolean isResolved() {
        return status == ReportStatus.RESOLVED;
    }

    // ==========================
    // toString()
    // ==========================

    @Override
    public String toString() {

        String workerName = (assignedWorker == null)
                ? "Not Assigned"
                : assignedWorker.getFullName();

        return "Report ID: " + reportId +
                "\nService Number: " + serviceNumber +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nCategory: " + category +
                "\nStatus: " + status +
                "\nSubmitted By: " + resident.getFullName() +
                "\nAssigned Worker: " + workerName;
    }
}