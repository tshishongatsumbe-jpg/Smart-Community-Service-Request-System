package com.scsrs.menu;

import com.scsrs.enums.ReportStatus;
import com.scsrs.reports.Report;
import com.scsrs.services.ReportService;
import com.scsrs.services.UserService;
import com.scsrs.storage.FileManager;
import com.scsrs.users.FieldWorker;
import com.scsrs.users.Resident;
import com.scsrs.users.User;
import com.scsrs.utils.Validation;

import java.util.Scanner;

/**
 * Displays the Administrator menu and handles administrator actions.
 *
 * @author Shonisani
 * @version 2.0
 */
public class AdministratorMenu {

    // ==========================
    // Attributes
    // ==========================

    private final UserService userService;
    private final ReportService reportService;
    private final FileManager fileManager;
    private final Validation validation;
    private final Scanner scanner;

    // ==========================
    // Constructor
    // ==========================

    public AdministratorMenu(UserService userService,
                             ReportService reportService,
                             FileManager fileManager,
                             Validation validation,
                             Scanner scanner) {

        this.userService = userService;
        this.reportService = reportService;
        this.fileManager = fileManager;
        this.validation = validation;
        this.scanner = scanner;
    }

    // ==========================
    // Menu
    // ==========================

    public void showMenu() {

        int choice;

        do {

            System.out.println("\n========== Administrator Menu ==========");
            System.out.println("1. Register Resident");
            System.out.println("2. View All Users");
            System.out.println("3. View All Reports");
            System.out.println("4. Search Report");
            System.out.println("5. Update Report Status");
            System.out.println("6. Assign Report");
            System.out.println("7. Save Reports");
            System.out.println("8. Logout");
            System.out.println("========================================");

            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    registerResident();
                    break;

                case 2:
                    viewAllUsers();
                    break;

                case 3:
                    viewReports();
                    break;

                case 4:
                    searchReport();
                    break;

                case 5:
                    updateReportStatus();
                    break;

                case 6:
                    assignReport();
                    break;

                case 7:
                    saveReports();
                    break;

                case 8:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);
    }

    // ==========================
    // Register Resident
    // ==========================

    private void registerResident() {

        System.out.println("\n===== Register Resident =====");

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        if (userService.searchUser(userId) != null) {
            System.out.println("User ID already exists.");
            return;
        }

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        if (validation.isEmpty(firstName)) {
            System.out.println("First name cannot be empty.");
            return;
        }

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        if (validation.isEmpty(lastName)) {
            System.out.println("Last name cannot be empty.");
            return;
        }

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        if (!validation.isValidEmail(email)) {
            System.out.println("Invalid email address.");
            return;
        }

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (!validation.isValidPassword(password)) {
            System.out.println("Password must contain at least 6 characters.");
            return;
        }

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        if (!validation.isValidPhoneNumber(phoneNumber)) {
            System.out.println("Phone number must contain exactly 10 digits.");
            return;
        }

        Resident resident = new Resident(
                userId,
                firstName,
                lastName,
                email,
                password,
                phoneNumber
        );

        if (userService.addUser(resident)) {
            System.out.println("\nResident registered successfully!");
        } else {
            System.out.println("\nRegistration failed.");
        }
    }

    // ==========================
    // View Users
    // ==========================

    private void viewAllUsers() {

        System.out.println("\n===== Registered Users =====");

        userService.viewAllUsers();
    }

    // ==========================
    // View Reports
    // ==========================

    private void viewReports() {

        System.out.println("\n===== Community Reports =====");

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
            System.out.println("Report status updated successfully!");
        } else {
            System.out.println("Failed to update report.");
        }
    }

    // ==========================
    // Assign Report
    // ==========================

    private void assignReport() {

        System.out.println("\n===== Assign Report =====");

        System.out.print("Enter Report ID: ");
        int reportId = scanner.nextInt();
        scanner.nextLine();

        Report report = reportService.searchReport(reportId);

        if (report == null) {
            System.out.println("Report not found.");
            return;
        }

        System.out.print("Enter Field Worker ID: ");
        int workerId = scanner.nextInt();
        scanner.nextLine();

        User user = userService.searchUser(workerId);

        if (!(user instanceof FieldWorker)) {
            System.out.println("Field Worker not found.");
            return;
        }

        FieldWorker worker = (FieldWorker) user;

        if (reportService.assignReport(reportId, worker)) {
            System.out.println("Report assigned successfully to " +
                    worker.getFullName());
        } else {
            System.out.println("Failed to assign report.");
        }
    }

    // ==========================
    // Save Reports
    // ==========================

    private void saveReports() {

        System.out.println("\n===== Save Reports =====");

        fileManager.saveReports(
                reportService.getReports(),
                "reports.txt"
        );

        System.out.println("Reports saved successfully!");
    }

}