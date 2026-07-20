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
    private String loginMessage;

    // ==========================
    // Constructor
    // ==========================

    public AuthenticationService(UserService userService) {
        this.userService = userService;
        this.loginMessage = "";
    }

    // ==========================
    // Login
    // ==========================

    /**
     * Authenticates a user.
     *
     * @param email User email.
     * @param password User password.
     * @return User if login is successful, otherwise null.
     */
    public User login(String email, String password) {

        ArrayList<User> users = userService.getUsers();

        // Check whether the email exists
        for (User user : users) {

            if (user.getEmail().equalsIgnoreCase(email)) {

                // Email exists, now check password
                if (user.getPassword().equals(password)) {

                    loginMessage = "Login successful.";
                    return user;

                } else {

                    loginMessage = "Incorrect password.";
                    return null;

                }
            }
        }

        // Email not found
        loginMessage = "Email address not found. Please check your email or contact the Administrator.";

        return null;
    }

    /**
     * Returns the latest login message.
     *
     * @return Login message.
     */
    public String getLoginMessage() {
        return loginMessage;
    }

}