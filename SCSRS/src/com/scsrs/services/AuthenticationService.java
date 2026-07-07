package com.scsrs.services;

import com.scsrs.users.User;

import java.util.ArrayList;

/**
 * Handles user authentication.
 *
 * @author Shonisani
 * @version 2.0
 */
public class AuthenticationService {

    // ==========================
    // Attributes
    // ==========================

    private final UserService userService;

    // ==========================
    // Constructor
    // ==========================

    /**
     * Creates a new AuthenticationService.
     *
     * @param userService The user service used to access registered users.
     */
    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    // ==========================
    // Methods
    // ==========================

    /**
     * Authenticates a user.
     *
     * @param email User email.
     * @param password User password.
     * @return Matching User if login succeeds, otherwise null.
     */
    public User login(String email, String password) {

        ArrayList<User> users = userService.getUsers();

        for (User user : users) {

            if (user.getEmail().equalsIgnoreCase(email)
                    && user.getPassword().equals(password)) {

                return user;
            }

        }

        return null;
    }

}