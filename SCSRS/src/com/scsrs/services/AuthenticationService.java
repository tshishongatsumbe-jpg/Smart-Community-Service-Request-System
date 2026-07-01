package com.scsrs.services;

import com.scsrs.users.User;

/**
 * Provides authentication services for users.
 *
 * @author Shonisani
 * @version 1.0
 */
public class AuthenticationService {

    /**
     * Authenticates a user using their email and password.
     *
     * @param user The user attempting to log in.
     * @param email The entered email.
     * @param password The entered password.
     * @return true if the credentials are correct, otherwise false.
     */
    public boolean login(User user, String email, String password) {

        if (user.getEmail().equals(email)
                && user.getPassword().equals(password)) {

            System.out.println("Login successful.");
            return true;
        }

        System.out.println("Invalid email or password.");
        return false;
    }

    /**
     * Logs the user out.
     *
     * @param user The user logging out.
     */
    public void logout(User user) {

        System.out.println(user.getFullName() + " has logged out.");

    }

}