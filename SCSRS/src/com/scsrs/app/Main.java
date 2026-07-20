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

        boolean running = true;

        while (running) {

            System.out.println("\n==================================================");
            System.out.println("      SMART COMMUNITY SERVICE REQUEST SYSTEM");
            System.out.println("==================================================");
            System.out.println("Welcome!");
            System.out.println();
            System.out.println("Press 1 to Login.");
            System.out.println("Press 2 to Exit the System.");
            System.out.println("==================================================");
            System.out.print("Enter your choice (1 or 2): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    login();

                    if (loggedInUser == null) {
                        break;
                    }

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

                    loggedInUser = null;
                    break;

                case 2:

                    running = false;
                    System.out.println("\nThank you for using SCSRS.");
                    break;

                default:
                    System.out.println("\nInvalid choice.");
                    System.out.println("Please enter 1 to Login or 2 to Exit.");
            }
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

        while (true) {

            System.out.println("\n==================================================");
            System.out.println("                     LOGIN");
            System.out.println("==================================================");
            System.out.println("Who is logging into the system?");
            System.out.println();
            System.out.println("1. Administrator");
            System.out.println("2. Resident");
            System.out.println("3. Field Worker");
            System.out.println("4. Return to Main Menu");
            System.out.println("==================================================");
            System.out.print("Enter your choice (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("\nInvalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int roleChoice = scanner.nextInt();
            scanner.nextLine();

            switch (roleChoice) {

                case 1:

                    System.out.println("\n========== Administrator Login ==========");
                    System.out.println("Administrator accounts manage the system.");
                    break;

                case 2:

                    System.out.println("\n========== Resident Login ==========");
                    System.out.println("Residents can submit and track service requests.");
                    System.out.println("If you do not have an account,");
                    System.out.println("please contact the Administrator.");
                    break;

                case 3:

                    System.out.println("\n========== Field Worker Login ==========");
                    System.out.println("Field Workers manage assigned service requests.");
                    break;

                case 4:

                    return;

                default:

                    System.out.println("\nInvalid choice.");
                    System.out.println("Please enter 1 to Login or 2 to Exit.");
                    continue;
            }

            System.out.print("\nEnter your email address: ");
            String email = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            loggedInUser = authenticationService.login(email, password);

            if (loggedInUser != null) {

                // Ensure the selected role matches the account
                if ((roleChoice == 1 && loggedInUser instanceof Administrator)
                        || (roleChoice == 2 && loggedInUser instanceof Resident)
                        || (roleChoice == 3 && loggedInUser instanceof FieldWorker)) {

                    System.out.println("\n========================================");
                    System.out.println("Login Successful!");
                    System.out.println("Welcome, " + loggedInUser.getFullName());
                    System.out.println("========================================");
                    return;

                } else {

                    System.out.println("\nYou selected the wrong user role.");
                    System.out.println("Please choose the correct role and try again.");
                    loggedInUser = null;
                }

            } else {

                System.out.println();
                System.out.println(authenticationService.getLoginMessage());
                System.out.println();
            }
        }
    }
}