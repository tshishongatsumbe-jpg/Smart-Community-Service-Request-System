package com.scsrs.services;

import com.scsrs.enums.ReportStatus;
import com.scsrs.reports.Report;
import com.scsrs.users.FieldWorker;

import java.util.ArrayList;

/**
 * Provides services for managing community service reports.
 *
 * @author Shonisani
 * @version 2.0
 */
public class ReportService {

    // ==========================
    // Attributes
    // ==========================

    private final ArrayList<Report> reports;

    // ==========================
    // Constructor
    // ==========================

    /**
     * Creates a new ReportService.
     */
    public ReportService() {
        reports = new ArrayList<>();
    }

    // ==========================
    // Getters
    // ==========================

    /**
     * Returns all reports.
     *
     * @return List of reports.
     */
    public ArrayList<Report> getReports() {
        return reports;
    }

    // ==========================
    // Methods
    // ==========================

    /**
     * Adds a report to the system.
     *
     * @param report The report to add.
     * @return true if added successfully,
     *         false if the Report ID already exists.
     */
    public boolean addReport(Report report) {

        if (searchReport(report.getReportId()) != null) {
            return false;
        }

        reports.add(report);
        return true;
    }

    /**
     * Displays all reports.
     */
    public void viewAllReports() {

        if (reports.isEmpty()) {
            System.out.println("No reports available.");
            return;
        }

        for (Report report : reports) {
            System.out.println(report);
            System.out.println("----------------------------");
        }
    }

    /**
     * Searches for a report using its ID.
     *
     * @param reportId Report ID.
     * @return Matching report or null.
     */
    public Report searchReport(int reportId) {

        for (Report report : reports) {

            if (report.getReportId() == reportId) {
                return report;
            }

        }

        return null;
    }

    /**
     * Updates the status of a report.
     *
     * @param reportId Report ID.
     * @param newStatus New report status.
     * @return true if updated successfully,
     *         false if report was not found.
     */
    public boolean updateReportStatus(int reportId,
                                      ReportStatus newStatus) {

        Report report = searchReport(reportId);

        if (report == null) {
            return false;
        }

        report.setStatus(newStatus);
        return true;
    }
    /**
     * Assigns a field worker to a report.
     *
     * @param reportId The report ID.
     * @param worker The field worker to assign.
     * @return true if the assignment was successful;
     *         false if the report was not found.
     */
    public boolean assignReport(int reportId, FieldWorker worker) {

        Report report = searchReport(reportId);

        if (report == null) {
            return false;
        }

        report.setAssignedWorker(worker);
        return true;
    }
    /**
     * Checks whether a report has already been assigned.
     *
     * @param reportId The report ID.
     * @return true if the report has an assigned worker.
     */
    public boolean isAssigned(int reportId) {

        Report report = searchReport(reportId);

        if (report == null) {
            return false;
        }

        return report.getAssignedWorker() != null;
    }

    /**
     * Returns the total number of reports.
     *
     * @return Number of reports.
     */
    public int getTotalReports() {
        return reports.size();
    }

    /**
     * Counts all open reports.
     *
     * @return Number of open reports.
     */
    public int getOpenReports() {

        int count = 0;

        for (Report report : reports) {

            if (report.getStatus() == ReportStatus.OPEN) {
                count++;
            }

        }

        return count;
    }

    /**
     * Counts all reports currently in progress.
     *
     * @return Number of reports in progress.
     */
    public int getInProgressReports() {

        int count = 0;

        for (Report report : reports) {

            if (report.getStatus() == ReportStatus.IN_PROGRESS) {
                count++;
            }

        }

        return count;
    }

    /**
     * Counts all resolved reports.
     *
     * @return Number of resolved reports.
     */
    public int getResolvedReports() {

        int count = 0;

        for (Report report : reports) {

            if (report.getStatus() == ReportStatus.RESOLVED) {
                count++;
            }

        }

        return count;
    }

}