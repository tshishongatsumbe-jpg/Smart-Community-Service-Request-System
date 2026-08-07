package com.scsrs.services;

import com.scsrs.enums.ReportCategory;
import com.scsrs.enums.ReportStatus;
import com.scsrs.reports.Report;
import com.scsrs.users.FieldWorker;
import com.scsrs.users.Resident;

import java.util.ArrayList;

/**
 * Provides services for managing community service reports.
 *
 * @author Shonisani
 * @version 3.0
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
    // Report Creation
    // ==========================

    /**
     * Generates the next available Report ID.
     *
     * @return Next Report ID.
     */
    private int generateReportId() {

        int reportId = 1;

        for (Report report : reports) {

            if (report.getReportId() >= reportId) {
                reportId = report.getReportId() + 1;
            }

        }

        return reportId;
    }

    /**
     * Generates the next Service Number within a category.
     *
     * @param category Report category.
     * @return Next Service Number.
     */
    private int generateServiceNumber(ReportCategory category) {

        int serviceNumber = 1;

        for (Report report : reports) {

            if (report.getCategory() == category) {
                serviceNumber++;
            }

        }

        return serviceNumber;
    }

    /**
     * Creates a new report with an automatically generated
     * Report ID and Service Number.
     *
     * @param title Report title.
     * @param description Report description.
     * @param category Report category.
     * @param resident Resident submitting the report.
     * @return Newly created Report or null if creation fails.
     */
    public Report createReport(String title,
                               String description,
                               ReportCategory category,
                               Resident resident) {

        int reportId = generateReportId();

        int serviceNumber = generateServiceNumber(category);

        Report report = new Report(
                reportId,
                title,
                description,
                category,
                ReportStatus.OPEN,
                resident,
                serviceNumber
        );

        if (addReport(report)) {
            return report;
        }

        return null;
    }

    // ==========================
    // Methods
    // ==========================

    /**
     * Adds a report to the system.
     *
     * @param report The report to add.
     * @return true if added successfully,
     * false if the Report ID already exists.
     */
    public boolean addReport(Report report) {

        if (report == null) {
            return false;
        }

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
            System.out.println("There are currently no reports in the system.");
            return;
        }

        for (Report report : reports) {
            System.out.println(report);
            System.out.println("--------------------------------");
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
     * @return true if successful.
     */
    public boolean updateReportStatus(int reportId,
                                      ReportStatus newStatus) {

        Report report = searchReport(reportId);

        if (report == null) {
            return false;
        }

        if (newStatus == null) {
            return false;
        }

        report.setStatus(newStatus);
        return true;
    }

    /**
     * Assigns a Field Worker to a report.
     *
     * @param reportId Report ID.
     * @param worker Field Worker.
     * @return true if successful.
     */
    public boolean assignReport(int reportId,
                                FieldWorker worker) {

        Report report = searchReport(reportId);

        if (report == null) {
            return false;
        }

        if (worker == null) {
            return false;
        }

        if (report.getAssignedWorker() != null) {
            return false;
        }

        report.setAssignedWorker(worker);
        report.setStatus(ReportStatus.IN_PROGRESS);

        return true;
    }

    /**
     * Checks whether a report has already been assigned.
     *
     * @param reportId Report ID.
     * @return true if assigned.
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
     * @return Total reports.
     */
    public int getTotalReports() {
        return reports.size();
    }

    /**
     * Counts all OPEN reports.
     *
     * @return Number of OPEN reports.
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
     * Counts all IN_PROGRESS reports.
     *
     * @return Number of IN_PROGRESS reports.
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
     * Counts all RESOLVED reports.
     *
     * @return Number of RESOLVED reports.
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

    /**
     * Displays all reports submitted by a resident.
     *
     * @param resident Resident.
     */
    public void viewResidentReports(Resident resident) {

        System.out.println("========== DEBUG ==========");
        System.out.println("Logged in Resident ID: " + resident.getUserId());
        System.out.println("Total Reports: " + reports.size());

        boolean found = false;

        for (Report report : reports) {

            System.out.println("------------------------");
            System.out.println("Report ID: " + report.getReportId());
            System.out.println("Stored Resident ID: "
                    + report.getResident().getUserId());

            if (report.getResident().getUserId() == resident.getUserId()) {

                System.out.println("MATCH FOUND");
                System.out.println(report);
                found = true;
            }
        }

        if (!found) {
            System.out.println("NO MATCH FOUND");
        }

        System.out.println("===========================");
    }
}