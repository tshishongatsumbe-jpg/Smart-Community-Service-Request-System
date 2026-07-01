package com.scsrs.app;

import com.scsrs.reports.Report;
import com.scsrs.services.ReportService;
import com.scsrs.services.UserService;
import com.scsrs.storage.FileManager;
import com.scsrs.users.Resident;
import com.scsrs.users.User;

import java.util.Scanner;

/**
 * Entry point for the Smart Community Service Request System.
 *
 * @author Shonisani
 * @version 1.0
 */
public class Main {

  // Services
  private static final UserService userService = new UserService();
  private static final ReportService reportService = new ReportService();
  private static final FileManager fileManager = new FileManager();

  // Scanner
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    int choice;

    do {

      displayMenu();

      System.out.print("Enter your choice: ");

      choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {

        case 1:
          registerResident();
          break;

        case 2:
          viewAllUsers();
          break;

        case 3:
          submitReport();
          break;

        case 4:
          viewReports();
          break;

        case 5:
          searchReport();
          break;

        case 6:
          updateReportStatus();
          break;

        case 7:
          saveReports();
          break;

        case 8:
          System.out.println("\nThank you for using the Smart Community Service Request System.");
          break;

        default:
          System.out.println("\nInvalid choice. Please try again.");
      }

    } while (choice != 8);

    scanner.close();
  }

  /**
   * Displays the main menu.
   */
  private static void displayMenu() {

    System.out.println("\n==========================================");
    System.out.println(" Smart Community Service Request System");
    System.out.println("==========================================");
    System.out.println("1. Register Resident");
    System.out.println("2. View All Users");
    System.out.println("3. Submit Report");
    System.out.println("4. View All Reports");
    System.out.println("5. Search Report");
    System.out.println("6. Update Report Status");
    System.out.println("7. Save Reports");
    System.out.println("8. Exit");
    System.out.println("==========================================");
  }

  /**
   * Registers a new resident.
   */
  private static void registerResident() {

    System.out.println("\n===== Register Resident =====");

    System.out.print("Enter User ID: ");
    int userId = scanner.nextInt();
    scanner.nextLine();

    // Check for duplicate User ID
    if (userService.searchUser(userId) != null) {
      System.out.println("User ID already exists.");
      return;
    }

    System.out.print("Enter First Name: ");
    String firstName = scanner.nextLine();

    System.out.print("Enter Last Name: ");
    String lastName = scanner.nextLine();

    System.out.print("Enter Email: ");
    String email = scanner.nextLine();

    System.out.print("Enter Password: ");
    String password = scanner.nextLine();

    System.out.print("Enter Phone Number: ");
    String phoneNumber = scanner.nextLine();

    Resident resident = new Resident(
            userId,
            firstName,
            lastName,
            email,
            password,
            phoneNumber
    );

    userService.addUser(resident);

    System.out.println("\nResident registered successfully!");
  }

  /**
   * Displays all registered users.
   */
  private static void viewAllUsers() {

    System.out.println("\n===== Registered Users =====");

    userService.viewAllUsers();
  }

  /**
   * Allows a resident to submit a report.
   */
  private static void submitReport() {

    System.out.println("\n===== Submit Report =====");

    System.out.print("Enter Resident ID: ");
    int residentId = scanner.nextInt();
    scanner.nextLine();

    User user = userService.searchUser(residentId);

    if (user == null) {
      System.out.println("Resident not found.");
      return;
    }

    if (!(user instanceof Resident)) {
      System.out.println("The selected user is not a resident.");
      return;
    }

    Resident resident = (Resident) user;

    System.out.print("Enter Report ID: ");
    int reportId = scanner.nextInt();
    scanner.nextLine();

    // Check for duplicate Report ID
    if (reportService.searchReport(reportId) != null) {
      System.out.println("Report ID already exists.");
      return;
    }

    System.out.print("Enter Title: ");
    String title = scanner.nextLine();

    System.out.print("Enter Description: ");
    String description = scanner.nextLine();

    System.out.print("Enter Category: ");
    String category = scanner.nextLine();

    Report report = new Report(
            reportId,
            title,
            description,
            category,
            "Open",
            resident
    );

    reportService.addReport(report);

    System.out.println("\nReport submitted successfully!");
  }

  /**
   * Displays all reports.
   */
  private static void viewReports() {

    System.out.println("\n===== All Reports =====");

    reportService.viewAllReports();
  }

  /**
   * Searches for a report by its ID.
   */
  private static void searchReport() {

    System.out.println("\n===== Search Report =====");

    System.out.print("Enter Report ID: ");
    int reportId = scanner.nextInt();
    scanner.nextLine();

    Report report = reportService.searchReport(reportId);

    if (report != null) {
      System.out.println("\nReport Found!");
      System.out.println(report);
    } else {
      System.out.println("\nReport not found.");
    }
  }

  /**
   * Updates the status of a report.
   */
  private static void updateReportStatus() {

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

    String newStatus;

    switch (option) {

      case 1:
        newStatus = "Open";
        break;

      case 2:
        newStatus = "In Progress";
        break;

      case 3:
        newStatus = "Resolved";
        break;

      default:
        System.out.println("Invalid status option.");
        return;
    }

    reportService.updateReportStatus(reportId, newStatus);

    System.out.println("Report status updated successfully!");
  }

  /**
   * Saves all reports to a text file.
   */
  private static void saveReports() {

    System.out.println("\n===== Save Reports =====");

    fileManager.saveReports(
            reportService.getReports(),
            "reports.txt"
    );
  }
}