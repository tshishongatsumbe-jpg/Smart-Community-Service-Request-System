package com.scsrs.menu;

import com.scsrs.enums.ReportCategory;
import com.scsrs.enums.ReportStatus;
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

            System.out.println("\n========== Resident Menu ==========");
            System.out.println("1. Submit Report");
            System.out.println("2. View My Reports");
            System.out.println("3. Search Report");
            System.out.println("4. Logout");
            System.out.println("===================================");

            System.out.print("Choice: ");

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
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("\nInvalid choice.");
                    System.out.println("Please enter a number between 1 and 4.");
            }

        } while (choice != 4);
    }

    // ==========================
    // Submit Report
    // ==========================

    private void submitReport() {

        System.out.println("\n===== Submit Report =====");

        System.out.print("Enter Report ID: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid Report ID.");
            scanner.nextLine();
            return;
        }

        int reportId = scanner.nextInt();
        scanner.nextLine();

        if (reportService.searchReport(reportId) != null) {
            System.out.println("Report ID already exists.");
            return;
        }

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

        Report report = new Report(
                reportId,
                title,
                description,
                category,
                ReportStatus.OPEN,
                resident
        );

        if (reportService.addReport(report)) {
            System.out.println("\nReport submitted successfully!");
        } else {
            System.out.println("\nFailed to submit report.");
        }
    }

    // ==========================
    // View My Reports
    // ==========================

    private void viewReports() {

        System.out.println("\n===== My Reports =====");
        reportService.viewResidentReports(resident);

    }

    // ==========================
    // Search Report
    // ==========================

    private void searchReport() {

        System.out.print("Enter Report ID: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid Report ID.");
            scanner.nextLine();
            return;
        }

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

}