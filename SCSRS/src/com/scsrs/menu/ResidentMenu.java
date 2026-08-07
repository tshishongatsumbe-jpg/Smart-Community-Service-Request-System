package com.scsrs.menu;

import com.scsrs.enums.ReportCategory;
import com.scsrs.reports.Report;
import com.scsrs.services.ReportService;
import com.scsrs.services.UserService;
import com.scsrs.users.Resident;

import java.util.Scanner;

/**
 * Displays the Resident menu and handles resident actions.
 *
 * @author Shonisani
 * @version 2.0
 */
public class ResidentMenu {

    // ==========================
    // Attributes
    // ==========================

    private final UserService userService;
    private final ReportService reportService;
    private final Scanner scanner;
    private final Resident resident;

    // ==========================
    // Constructor
    // ==========================

    public ResidentMenu(UserService userService,
                        ReportService reportService,
                        Scanner scanner,
                        Resident resident) {

        this.userService = userService;
        this.reportService = reportService;
        this.scanner = scanner;
        this.resident = resident;
    }

    // ==========================
    // Resident Menu
    // ==========================

    public void showMenu() {

        int choice = 0;

        do {

            System.out.println("\n==================================================");
            System.out.println("                 RESIDENT MENU");
            System.out.println("==================================================");
            System.out.println("Welcome, " + resident.getFullName() + "!");
            System.out.println();
            System.out.println("1. Submit a New Service Request");
            System.out.println("2. View My Submitted Reports");
            System.out.println("3. Search for a Report");
            System.out.println("4. Logout");
            System.out.println("==================================================");
            System.out.print("Enter your choice (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input.");
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    submitReport();
                    break;

                case 2:
                    System.out.println("CASE 2 EXECUTED");
                    viewReports();
                    break;

                case 3:
                    searchReport();
                    break;

                case 4:
                    System.out.println("\n==================================================");
                    System.out.println("You have successfully logged out.");
                    System.out.println("Returning to the Main Menu...");
                    System.out.println("==================================================");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);
    }

    // ==========================
    // Submit Report
    // ==========================

    private void submitReport() {

        System.out.println("\n==================================================");
        System.out.println("          SUBMIT A SERVICE REQUEST");
        System.out.println("==================================================");

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.println("\nSelect Category");
        System.out.println("1. Water");
        System.out.println("2. Electricity");
        System.out.println("3. Roads");
        System.out.println("4. Sanitation");
        System.out.println("5. Waste");
        System.out.println("6. Street Lights");
        System.out.println("7. Other");

        System.out.print("Choice: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid category.");
            scanner.nextLine();
            return;
        }

        int option = scanner.nextInt();
        scanner.nextLine();

        ReportCategory category;

        switch (option) {

            case 1:
                category = ReportCategory.WATER;
                break;

            case 2:
                category = ReportCategory.ELECTRICITY;
                break;

            case 3:
                category = ReportCategory.ROADS;
                break;

            case 4:
                category = ReportCategory.SANITATION;
                break;

            case 5:
                category = ReportCategory.WASTE;
                break;

            case 6:
                category = ReportCategory.STREET_LIGHTS;
                break;

            case 7:
                category = ReportCategory.OTHER;
                break;

            default:
                System.out.println("Invalid category.");
                return;
        }

        // Let ReportService generate the Report ID and Service Number
        Report report = reportService.createReport(
                title,
                description,
                category,
                resident
        );

        if (report != null) {

            System.out.println("\n==================================================");
            System.out.println("Service request submitted successfully!");
            System.out.println("Report ID      : " + report.getReportId());
            System.out.println("Category       : " + report.getCategory());
            System.out.println("Complaint No.  : " + report.getServiceNumber());
            System.out.println("Status         : " + report.getStatus());
            System.out.println();
            System.out.println("Please keep your Report ID for tracking.");
            System.out.println("==================================================");

        } else {

            System.out.println("Unable to submit your service request.");
        }
    }

    // ==========================
    // View My Reports
    // ==========================

    private void viewReports() {

        System.out.println("VIEW REPORTS METHOD CALLED");

        reportService.viewResidentReports(resident);
    }

    // ==========================
    // Search Report
    // ==========================

    private void searchReport() {

        System.out.print("Enter the Report ID you wish to search for: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid Report ID.");
            scanner.nextLine();
            return;
        }

        int reportId = scanner.nextInt();
        scanner.nextLine();

        Report report = reportService.searchReport(reportId);

        if (report != null) {

            System.out.println("\n==================================================");
            System.out.println("              REPORT DETAILS");
            System.out.println("==================================================");
            System.out.println(report);

        } else {

            System.out.println("No report exists with Report ID: " + reportId);
        }
    }
}