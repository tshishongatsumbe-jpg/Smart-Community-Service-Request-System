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

            System.out.println("\n========== Field Worker Menu ==========");
            System.out.println("1. View My Assigned Reports");
            System.out.println("2. Mark Report as Resolved");
            System.out.println("3. View All Reports");
            System.out.println("4. Logout");
            System.out.println("=======================================");

            System.out.print("Choice: ");

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
                    System.out.println("Logging out...");
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

        System.out.println("\n===== My Assigned Reports =====");

        boolean found = false;

        for (Report report : reportService.getReports()) {

            if (report.getAssignedWorker() != null
                    && report.getAssignedWorker().getUserId()
                    == fieldWorker.getUserId()) {

                System.out.println(report);
                System.out.println("----------------------------");
                found = true;
            }

        }

        if (!found) {
            System.out.println("No reports assigned.");
        }

    }

    // ==========================
    // Resolve Report
    // ==========================

    private void resolveReport() {

        System.out.println("\n===== Resolve Report =====");

        System.out.print("Enter Report ID: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid Report ID.");
            scanner.nextLine();
            return;
        }
        int reportId = scanner.nextInt();
        scanner.nextLine();

        Report report = reportService.searchReport(reportId);

        if (report == null) {
            System.out.println("Report not found.");
            return;
        }

        if (report.getAssignedWorker() == null) {
            System.out.println("This report has not been assigned.");
            return;
        }

        if (report.getAssignedWorker().getUserId() != fieldWorker.getUserId()) {
            System.out.println("This report is assigned to another field worker.");
            return;
        }

        if (reportService.updateReportStatus(reportId, ReportStatus.RESOLVED)) {

            System.out.println("Report marked as resolved.");

        } else {

            System.out.println("Unable to update report.");

        }

    }

    // ==========================
    // View All Reports
    // ==========================

    private void viewAllReports() {

        System.out.println("\n===== All Community Reports =====");

        reportService.viewAllReports();

    }

}