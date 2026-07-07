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

        int choice;

        do {

            System.out.println("\n========== Field Worker Menu ==========");
            System.out.println("1. View All Reports");
            System.out.println("2. Search Report");
            System.out.println("3. Update Report Status");
            System.out.println("4. Logout");
            System.out.println("=======================================");

            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewReports();
                    break;

                case 2:
                    searchReport();
                    break;

                case 3:
                    updateReportStatus();
                    break;

                case 4:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

    }

    // ==========================
    // View Reports
    // ==========================

    private void viewReports() {

        System.out.println("\n===== Community Service Reports =====");

        reportService.viewAllReports();

    }

    // ==========================
    // Search Report
    // ==========================

    private void searchReport() {

        System.out.println("\n===== Search Report =====");

        System.out.print("Enter Report ID: ");
        int reportId = scanner.nextInt();
        scanner.nextLine();

        Report report = reportService.searchReport(reportId);

        if (report != null) {

            System.out.println("\n===== Report Found =====");
            System.out.println(report);

        } else {

            System.out.println("Report not found.");

        }

    }

    // ==========================
    // Update Report Status
    // ==========================

    private void updateReportStatus() {

        System.out.println("\n===== Update Report Status =====");

        System.out.print("Enter Report ID: ");
        int reportId = scanner.nextInt();
        scanner.nextLine();

        Report report = reportService.searchReport(reportId);

        if (report == null) {

            System.out.println("Report not found.");
            return;

        }

        System.out.println("\nCurrent Status: " + report.getStatus());

        System.out.println("\nSelect New Status");
        System.out.println("1. Open");
        System.out.println("2. In Progress");
        System.out.println("3. Resolved");

        System.out.print("Choice: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        ReportStatus newStatus;

        switch (option) {

            case 1:
                newStatus = ReportStatus.OPEN;
                break;

            case 2:
                newStatus = ReportStatus.IN_PROGRESS;
                break;

            case 3:
                newStatus = ReportStatus.RESOLVED;
                break;

            default:
                System.out.println("Invalid status.");
                return;
        }

        if (reportService.updateReportStatus(reportId, newStatus)) {

            System.out.println("\nReport status updated successfully!");

        } else {

            System.out.println("\nFailed to update report.");

        }

    }


}
