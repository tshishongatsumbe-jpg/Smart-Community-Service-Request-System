package com.scsrs.menu;

import com.scsrs.enums.ReportStatus;
import com.scsrs.reports.Report;
import com.scsrs.services.ReportService;
import com.scsrs.users.FieldWorker;

import java.util.Scanner;

/**
 * Displays the Field Worker menu and handles field worker actions.
 *
 * @author Shonisani
 * @version 2.0
 */
public class FieldWorkerMenu {

    // ==========================
    // Attributes
    // ==========================

    private final ReportService reportService;
    private final Scanner scanner;
    private final FieldWorker fieldWorker;

    // ==========================
    // Constructor
    // ==========================

    public FieldWorkerMenu(ReportService reportService,
                           Scanner scanner,
                           FieldWorker fieldWorker) {

        this.reportService = reportService;
        this.scanner = scanner;
        this.fieldWorker = fieldWorker;
    }

    // ==========================
    // Menu
    // ==========================

    public void showMenu() {

        int choice = 0;

        do {

            System.out.println("\n==================================================");
            System.out.println("               FIELD WORKER MENU");
            System.out.println("==================================================");
            System.out.println("Welcome, " + fieldWorker.getFullName() + "!");
            System.out.println();
            System.out.println("Please choose one of the following options:");
            System.out.println();
            System.out.println("1. View My Assigned Reports");
            System.out.println("2. Mark a Report as Resolved");
            System.out.println("3. View All Community Reports");
            System.out.println("4. Logout");
            System.out.println("==================================================");
            System.out.print("Enter your choice (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewAssignedReports();
                    break;

                case 2:
                    resolveReport();
                    break;

                case 3:
                    viewAllReports();
                    break;

                case 4:
                    System.out.println("\n==================================================");
                    System.out.println("You have successfully logged out.");
                    System.out.println("Returning to the Main Menu...");
                    System.out.println("==================================================");
                    break;

                default:
                    System.out.println("\nInvalid choice.");
                    System.out.println("Please enter a number between 1 and 4.");
            }

        } while (choice != 4);

    }

    // ==========================
    // View Assigned Reports
    // ==========================

    private void viewAssignedReports() {

        System.out.println("\n==================================================");
        System.out.println("           MY ASSIGNED REPORTS");
        System.out.println("==================================================");

        boolean found = false;

        for (Report report : reportService.getReports()) {

            if (report.getAssignedWorker() != null
                    && report.getAssignedWorker().getUserId()
                    == fieldWorker.getUserId()) {

                System.out.println(report);
                System.out.println("==================================================");
                found = true;
            }

        }

        if (!found) {
            System.out.println("You currently have no assigned reports.");
        }

    }

    // ==========================
    // Resolve Report
    // ==========================

    private void resolveReport() {

        System.out.println("\n==================================================");
        System.out.println("           RESOLVE A REPORT");
        System.out.println("==================================================");

        System.out.print("Enter the Report ID you want to mark as resolved: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid Report ID. Please enter a numeric value.");
            scanner.nextLine();
            return;
        }
        int reportId = scanner.nextInt();
        scanner.nextLine();

        Report report = reportService.searchReport(reportId);

        if (report == null) {
            System.out.println("No report was found with the entered Report ID.");
            return;
        }

        if (report.getAssignedWorker() == null) {
            System.out.println("This report has not yet been assigned to a field worker.");
            return;
        }

        if (report.getAssignedWorker().getUserId() != fieldWorker.getUserId()) {
          System.out.println("This report is assigned to another Field Worker.");
            return;
        }

        if (reportService.updateReportStatus(reportId, ReportStatus.RESOLVED)) {

            System.out.println("\n==================================================");
            System.out.println("Report successfully resolved.");
            System.out.println("Report ID       : " + report.getReportId());
            System.out.println("Complaint Number: " + report.getServiceNumber());
            System.out.println("Category        : " + report.getCategory());
            System.out.println("Status          : RESOLVED");
            System.out.println("==================================================");

        } else {

            System.out.println("Unable to update the report status.");
            System.out.println("Please try again.");
        }

    }

    // ==========================
    // View All Reports
    // ==========================

    private void viewAllReports() {

        System.out.println("\n==================================================");
        System.out.println("         ALL COMMUNITY REPORTS");
        System.out.println("==================================================");

        reportService.viewAllReports();
    }
}