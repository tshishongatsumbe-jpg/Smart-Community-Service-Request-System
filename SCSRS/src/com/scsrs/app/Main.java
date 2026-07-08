package com.scsrs.app;

import com.scsrs.menu.AdministratorMenu;
import com.scsrs.menu.FieldWorkerMenu;
import com.scsrs.menu.ResidentMenu;
import com.scsrs.services.AuthenticationService;
import com.scsrs.services.ReportService;
import com.scsrs.services.UserService;
import com.scsrs.storage.FileManager;
import com.scsrs.users.Administrator;
import com.scsrs.users.FieldWorker;
import com.scsrs.users.Resident;
import com.scsrs.users.User;
import com.scsrs.utils.Validation;

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

    createDefaultUsers();

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
  // Create Default Users
  // ==========================

  /**
   * Creates default users for testing.
   */
  private static void createDefaultUsers() {

    userService.addUser(
            new Administrator(
                    1,
                    "System",
                    "Administrator",
                    "admin@scsrs.com",
                    "admin123",
                    "0123456789"
            )
    );

    userService.addUser(
            new Resident(
                    2,
                    "John",
                    "Smith",
                    "john@scsrs.com",
                    "resident123",
                    "0711111111"
            )
    );

    userService.addUser(
            new FieldWorker(
                    3,
                    "Peter",
                    "Jones",
                    "worker1@scsrs.com",
                    "worker123",
                    "0722222222"
            )
    );

    userService.addUser(
            new FieldWorker(
                    4,
                    "Sarah",
                    "Williams",
                    "worker2@scsrs.com",
                    "worker123",
                    "0733333333"
            )
    );
  }

  // ==========================
  // Login
  // ==========================

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
}