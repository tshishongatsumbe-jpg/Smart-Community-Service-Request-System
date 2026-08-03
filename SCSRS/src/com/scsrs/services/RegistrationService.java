package com.scsrs.services;

import com.scsrs.users.Resident;
import com.scsrs.users.User;
import com.scsrs.utils.Validation;

import java.util.Scanner;

/**
 * Handles registration of new residents.
 *
 * @author Shonisani
 * @version 2.0
 */
public class RegistrationService {

    // ==========================
    // Attributes
    // ==========================

    private final UserService userService;
    private final Validation validation;
    private final Scanner scanner;

    // ==========================
    // Constructor
    // ==========================

    public RegistrationService(UserService userService,
                               Validation validation,
                               Scanner scanner) {

        this.userService = userService;
        this.validation = validation;
        this.scanner = scanner;
    }

    // ==========================
    // Register Resident
    // ==========================

    /**
     * Registers a new resident.
     *
     * @return Newly created Resident, or null if registration failed.
     */
    public Resident createResidentAccount() {

        System.out.println("\n==================================================");
        System.out.println("          CREATE RESIDENT ACCOUNT");
        System.out.println("==================================================");

        // Generate the next available User ID
        int userId = 1;

        for (User user : userService.getUsers()) {

            if (user.getUserId() >= userId) {
                userId = user.getUserId() + 1;
            }
        }

        System.out.println("Your User ID has been generated: " + userId);
        System.out.println();

        // First Name
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        if (validation.isEmpty(firstName)) {
            System.out.println("First name cannot be empty.");
            return null;
        }

        // Last Name
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        if (validation.isEmpty(lastName)) {
            System.out.println("Last name cannot be empty.");
            return null;
        }

        // Email
        System.out.print("Enter Email Address: ");
        String email = scanner.nextLine();

        if (!validation.isValidEmail(email)) {
            System.out.println("Invalid email address.");
            return null;
        }

        // Check for duplicate email
        for (User user : userService.getUsers()) {

            if (user.getEmail().equalsIgnoreCase(email)) {
                System.out.println("An account with this email already exists.");
                return null;
            }
        }

        // Password
        System.out.print("Create Password: ");
        String password = scanner.nextLine();

        if (!validation.isValidPassword(password)) {
            System.out.println("Password must be at least 6 characters.");
            return null;
        }

        // Phone Number
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        if (!validation.isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phone number.");
            return null;
        }

        // Create Resident
        Resident resident = new Resident(
                userId,
                firstName,
                lastName,
                email,
                password,
                phoneNumber
        );

        // Save Resident
        if (userService.addUser(resident)) {

            System.out.println("\n========================================");
            System.out.println("Resident account created successfully!");
            System.out.println("Welcome, " + resident.getFullName() + "!");
            System.out.println("Your User ID is: " + resident.getUserId());
            System.out.println("You can now sign in using your");
            System.out.println("email address and password.");
            System.out.println("========================================");

            return resident;
        }

        System.out.println("\nUnable to create your account.");
        return null;
    }

}