package com.scsrs.services;

import com.scsrs.users.User;
import java.util.ArrayList;

/**
 * Provides services for managing users in the
 * Smart Community Service Request System.
 *
 * @author Shonisani
 * @version 1.0
 */
public class UserService {

    // ==========================
    // Attributes
    // ==========================

    private ArrayList<User> users;

    // ==========================
    // Constructor
    // ==========================

    public UserService() {
        users = new ArrayList<>();
    }

    // ==========================
    // Methods
    // ==========================

    /**
     * Adds a new user.
     *
     * @param user The user to add.
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Displays all users.
     */
    public void viewAllUsers() {

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (User user : users) {
            System.out.println(user);
            System.out.println("----------------------------");
        }

    }

    /**
     * Searches for a user by ID.
     *
     * @param userId The user ID.
     * @return The matching user or null if not found.
     */
    public User searchUser(int userId) {

        for (User user : users) {

            if (user.getUserId() == userId) {
                return user;
            }

        }

        return null;
    }

    /**
     * Removes a user from the system.
     *
     * @param userId The user ID.
     */
    public void removeUser(int userId) {

        User user = searchUser(userId);

        if (user != null) {
            users.remove(user);
            System.out.println("User removed successfully.");
        } else {
            System.out.println("User not found.");
        }

    }

}