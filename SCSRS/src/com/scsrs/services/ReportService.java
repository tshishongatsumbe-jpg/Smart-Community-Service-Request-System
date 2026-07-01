package com.scsrs.services;

import com.scsrs.reports.Report;
import java.util.ArrayList;

/**
 * Provides services for managing community service reports.
 *
 * @author Shonisani
 * @version 1.0
 */
public class ReportService {

    // ==========================
    // Attributes
    // ==========================

    private ArrayList<Report> reports;

    // ==========================
    // Constructor
    // ==========================

    public ReportService() {
        reports = new ArrayList<>();
    }

    // ==========================
    // Methods
    // ==========================

    /**
     * Adds a new report to the system.
     *
     * @param report The report to add.
     */
    public void addReport(Report report) {
        reports.add(report);
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
     * @param reportId The report ID.
     * @return The matching report, or null if not found.
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
     * @param reportId The report ID.
     * @param newStatus The new status.
     */
    public void updateReportStatus(int reportId, String newStatus) {

        Report report = searchReport(reportId);

        if (report != null) {
            report.setStatus(newStatus);
            System.out.println("Report status updated successfully.");
        } else {
            System.out.println("Report not found.");
        }

    }

}
