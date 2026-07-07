package com.scsrs.app;

import com.scsrs.enums.ReportCategory;
import com.scsrs.enums.ReportStatus;
import com.scsrs.menu.FieldWorkerMenu;
import com.scsrs.menu.ResidentMenu;
import com.scsrs.reports.Report;
import com.scsrs.services.ReportService;
import com.scsrs.services.UserService;
import com.scsrs.storage.FileManager;
import com.scsrs.users.Administrator;
import com.scsrs.users.FieldWorker;
import com.scsrs.users.Resident;
import com.scsrs.users.User;
import com.scsrs.utils.Validation;
import com.scsrs.services.AuthenticationService;
import com.scsrs.menu.AdministratorMenu;


import java.util.Scanner;

/**
 * Entry point for the Smart Community Service Request System.
 *
 * @author Shonisani
 * @version 2.0
 */
public class Main {

  // ==========================
  // Services
  // ==========================

  private static final Scanner scanner = new Scanner(System.in);

  private static final UserService userService = new UserService();
  private static final ReportService reportService = new ReportService();
  private static final FileManager fileManager = new FileManager();
  private static final Validation validation = new Validation();

  private static final AuthenticationService authenticationService =
          new AuthenticationService(userService);

  private static User loggedInUser;

  private static final AdministratorMenu administratorMenu =
          new AdministratorMenu(
                  userService,
                  reportService,
                  fileManager,
                  validation,
                  scanner
          );
  // ==========================
  // Main Method
  // ==========================


  public static void main(String[] args) {

    Administrator admin = new Administrator(
            1,
            "System",
            "Administrator",
            "admin@scsrs.com",
            "admin123",
            "0123456789"
    );

    userService.addUser(admin);

    Resident resident = new Resident(
            2,
            "John",
            "Smith",
            "john@scsrs.com",
            "resident123",
            "0711111111"
    );

    userService.addUser(resident);

    FieldWorker worker1 = new FieldWorker(
            3,
            "Peter",
            "Jones",
            "worker1@scsrs.com",
            "worker123",
            "0722222222"
    );

    userService.addUser(worker1);

    FieldWorker worker2 = new FieldWorker(
            4,
            "Sarah",
            "Williams",
            "worker2@scsrs.com",
            "worker123",
            "0733333333"
    );

    userService.addUser(worker2);

    login();

    if (loggedInUser instanceof Administrator) {

      administratorMenu.showMenu();

    } else if (loggedInUser instanceof Resident) {

      ResidentMenu residentMenu =
              new ResidentMenu(
                      userService,
                      reportService,
                      scanner,
                      (Resident) loggedInUser
              );

      residentMenu.showMenu();

    } else if (loggedInUser instanceof FieldWorker) {

      FieldWorkerMenu fieldWorkerMenu =
              new FieldWorkerMenu(
                      reportService,
                      scanner,
                      (FieldWorker) loggedInUser
              );

      fieldWorkerMenu.showMenu();

    }
    scanner.close();
  }

  // ==========================
  // Menu
  // ==========================

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
   * Allows a user to log into the system.
   */
  private static void login() {

    System.out.println("====================================");
    System.out.println(" Welcome to SCSRS");
    System.out.println("====================================");

    while (true) {

      System.out.print("Email: ");
      String email = scanner.nextLine();

      System.out.print("Password: ");
      String password = scanner.nextLine();

      loggedInUser = authenticationService.login(email, password);

      if (loggedInUser != null) {

        System.out.println("\nLogin successful!");
        System.out.println("Welcome " + loggedInUser.getFullName());

        break;

      }

      System.out.println("\nInvalid email or password.\n");
    }

  }

  // ==========================
  // Register Resident
  // ==========================

  private static void registerResident() {

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

  private static void viewAllUsers() {

    System.out.println("\n===== Registered Users =====");

    userService.viewAllUsers();
  }

  // ==========================
  // Submit Report
  // ==========================

  private static void submitReport() {

    System.out.println("\n===== Submit Report =====");

    System.out.print("Enter Resident ID: ");
    int residentId = scanner.nextInt();
    scanner.nextLine();

    User user = userService.searchUser(residentId);

    if (user == null || !(user instanceof Resident)) {
      System.out.println("Resident not found.");
      return;
    }

    Resident resident = (Resident) user;

    System.out.print("Enter Report ID: ");
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

    System.out.println("\nSelect Report Category");
    System.out.println("1. Water");
    System.out.println("2. Electricity");
    System.out.println("3. Roads");
    System.out.println("4. Sanitation");
    System.out.println("5. Waste");
    System.out.println("6. Street Lights");
    System.out.println("7. Other");

    System.out.print("Choice: ");
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
    }}
  // ==========================
  // View Reports
  // ==========================

  /**
   * Displays all reports.
   */
  private static void viewReports () {

    System.out.println("\n===== Community Service Reports =====");

    reportService.viewAllReports();
  }

  // ==========================
  // Search Report
  // ==========================

  /**
   * Searches for a report by its ID.
   */
  private static void searchReport () {

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

  /**
   * Updates the status of an existing report.
   */
  private static void updateReportStatus () {

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

  // ==========================
  // Save Reports
  // ==========================

  /**
   * Saves all reports to a text file.
   */
  private static void saveReports () {

    System.out.println("\n===== Save Reports =====");

    fileManager.saveReports(
            reportService.getReports(),
            "reports.txt"
    );

  }

}