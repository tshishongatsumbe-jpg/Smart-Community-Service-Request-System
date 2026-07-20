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
    // Administrator Menu
    // ==========================

    public void showMenu() {

        int choice = 0;

        do {

            System.out.println("\n==================================================");
            System.out.println("              ADMINISTRATOR MENU");
            System.out.println("==================================================");
            System.out.println("Welcome, Administrator!");
            System.out.println();
            System.out.println("Please select one of the following options:");
            System.out.println();
            System.out.println("1. Register a New Resident");
            System.out.println("2. View All Registered Users");
            System.out.println("3. View All Community Reports");
            System.out.println("4. Search for a Report");
            System.out.println("5. Update Report Status");
            System.out.println("6. Assign Report to a Field Worker");
            System.out.println("7. Save Reports");
            System.out.println("8. Logout");
            System.out.println("==================================================");
            System.out.print("Enter your choice (1-8): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

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
                    System.out.println("\n========================================");
                    System.out.println("You have successfully logged out.");
                    System.out.println("Returning to the Main Menu...");
                    System.out.println("========================================");
                    break;

                default:
                    System.out.println("\nInvalid choice.");
                    System.out.println("Please enter a number between 1 and 8.");
            }

        } while (choice != 8);
    }

    // ==========================
    // Register Resident
    // ==========================

    private void registerResident() {

        System.out.println("\n==================================================");
        System.out.println("          REGISTER A NEW RESIDENT");
        System.out.println("==================================================");
        System.out.println("Please enter the resident's details.");
        System.out.println("Fields marked below are required.");
        System.out.println();

        System.out.print("Enter User ID: ");

        if (!scanner.hasNextInt()) {
            System.out.println("User ID must be a number.");
            scanner.nextLine();
            return;
        }

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
            System.out.println("Invalid email.");
            return;
        }

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (!validation.isValidPassword(password)) {
            System.out.println("Password must be at least 6 characters.");
            return;
        }

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        if (!validation.isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phone number.");
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
            System.out.println("\n========================================");
            System.out.println("Resident registered successfully!");
            System.out.println("Resident: " + resident.getFullName());
            System.out.println("Email   : " + resident.getEmail());
            System.out.println("The resident can now log into the system.");
            System.out.println("========================================");
        } else {
            System.out.println("Registration failed.");
        }
    }

    // ==========================
    // View Users
    // ==========================

    private void viewAllUsers() {

        System.out.println("\n==================================================");
        System.out.println("             REGISTERED USERS");
        System.out.println("==================================================");
        userService.viewAllUsers();
    }

    // ==========================
    // View Reports
    // ==========================

    private void viewReports() {

        System.out.println("\n==================================================");
        System.out.println("          COMMUNITY REPORTS");
        System.out.println("==================================================");
        reportService.viewAllReports();
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
            System.out.println(report);
        } else {
            System.out.println("No report exists with the entered Report ID.");
        }
    }

    // ==========================
    // Update Report Status
    // ==========================

    private void updateReportStatus() {

        System.out.print("Enter the Report ID to update: ");

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

        System.out.println("1. OPEN");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. RESOLVED");

        System.out.print("Select the new status (1-3): ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid choice.");
            scanner.nextLine();
            return;
        }
        int choice = scanner.nextInt();
        scanner.nextLine();

        ReportStatus status;

        switch (choice) {

            case 1:
                status = ReportStatus.OPEN;
                break;

            case 2:
                status = ReportStatus.IN_PROGRESS;
                break;

            case 3:
                status = ReportStatus.RESOLVED;
                break;

            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                return;
        }

        if (reportService.updateReportStatus(reportId, status)) {
            System.out.println("\n========================================");
            System.out.println("Report status updated successfully.");
            System.out.println("========================================");
        } else {
            System.out.println("Unable to update the report status.\n" +
                    "Please try again.");
        }
    }

    // ==========================
    // Assign Report
    // ==========================

    private void assignReport() {

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
            System.out.println("No report was found with Report ID: " + reportId);
            return;
        }

        System.out.println("\nAvailable Field Workers");
        System.out.println("----------------------------------");
        System.out.println("Select a field worker from the list below:");

        for (User user : userService.getUsers()) {

            if (user instanceof FieldWorker) {
                System.out.println(user);
                System.out.println("--------------------");
            }
        }

        System.out.print("Enter the Field Worker ID to assign this report: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid Field Worker ID.");
            scanner.nextLine();
            return;
        }
        int workerId = scanner.nextInt();
        scanner.nextLine();

        User user = userService.searchUser(workerId);

        if (!(user instanceof FieldWorker)) {
            System.out.println("Field Worker not found.");
            return;
        }

        FieldWorker worker = (FieldWorker) user;

        if (reportService.assignReport(reportId, worker)) {
            System.out.println("\n========================================");
            System.out.println("Report assigned successfully.");
            System.out.println("Assigned to: " + worker.getFullName());
            System.out.println("========================================");
        } else {
            System.out.println("Unable to assign the report.\n" +
                    "Please verify the report and field worker details.");
        }
    }

    // ==========================
    // Save Reports
    // ==========================

    private void saveReports() {

        fileManager.saveReports(
                reportService.getReports(),
                "reports.txt"
        );

        System.out.println("\n========================================");
        System.out.println("Reports have been saved successfully.");
        System.out.println("========================================");
    }
}