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
 * @version 3.0
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
                System.out.println("Invalid input. Please enter a number.");
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
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
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

        System.out.println("\nSelect Service Type");
        System.out.println("1. Water");
        System.out.println("2. Electricity");
        System.out.println("3. Roads");
        System.out.println("4. Sanitation");
        System.out.println("5. Waste");
        System.out.println("6. Street Lights");
        System.out.println("7. Other");

        System.out.print("Choice: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid service type.");
            scanner.nextLine();
            return;
        }

        int option = scanner.nextInt();
        scanner.nextLine();

        ReportCategory category;
        String title;

        switch (option) {

            case 1:
                category = ReportCategory.WATER;
                title = "Water";
                break;

            case 2:
                category = ReportCategory.ELECTRICITY;
                title = "Electricity";
                break;

            case 3:
                category = ReportCategory.ROADS;
                title = "Roads";
                break;

            case 4:
                category = ReportCategory.SANITATION;
                title = "Sanitation";
                break;

            case 5:
                category = ReportCategory.WASTE;
                title = "Waste";
                break;

            case 6:
                category = ReportCategory.STREET_LIGHTS;
                title = "Street Lights";
                break;

            case 7:
                category = ReportCategory.OTHER;
                title = "Other";
                break;

            default:
                System.out.println("Invalid service type.");
                return;
        }

        System.out.print("Enter Description of the service type you just choose: ");
        String description = scanner.nextLine().trim();

        if (description.isEmpty()) {
            System.out.println("Description cannot be empty.");
            return;
        }

        if (!description.matches(".*[a-zA-Z].*")) {
            System.out.println("Invalid description. Please enter a description containing letters.");
            return;
        }

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
            System.out.println("Service Type   : " + report.getCategory());
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

        System.out.println("\n==================================================");
        System.out.println("            MY SERVICE REQUESTS");
        System.out.println("==================================================");

        boolean found = false;

        for (Report report : reportService.getReports()) {

            if (report.getResident().getUserId() == resident.getUserId()) {

                System.out.println(report);
                System.out.println("--------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("You have not submitted any service requests yet.");
        }
    }
    // ==========================
    // Search Report
    // ==========================

    private void searchReport() {

        System.out.println("\n==================================================");
        System.out.println("              SEARCH FOR A REPORT");
        System.out.println("==================================================");

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

            System.out.println("\nNo report exists with Report ID: " + reportId);
        }
    }
}