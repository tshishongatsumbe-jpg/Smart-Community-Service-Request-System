package com.scsrs.services;

import com.scsrs.users.User;

import java.util.ArrayList;

/**
 * Provides services for managing users in the
 * Smart Community Service Request System.
 *
 * @author Shonisani
 * @version 2.0
 */
public class UserService {

    // ==========================
    // Attributes
    // ==========================

    private final ArrayList<User> users;

    // ==========================
    // Constructor
    // ==========================

    /**
     * Creates a new UserService.
     */
    public UserService() {
        users = new ArrayList<>();
    }

    // ==========================
    // Getters
    // ==========================

    /**
     * Returns all registered users.
     *
     * @return List of users.
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    // ==========================
    // Methods
    // ==========================

    /**
     * Adds a new user to the system.
     *
     * @param user The user to add.
     * @return true if the user was added successfully,
     *         false if the User ID already exists.
     */
    public boolean addUser(User user) {

        if (searchUser(user.getUserId()) != null) {
            return false;
        }

        users.add(user);
        return true;
    }

    /**
     * Searches for a user using their ID.
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
     * Displays all registered users.
     */
    public void viewAllUsers() {

        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }

        for (User user : users) {
            System.out.println(user);
            System.out.println("----------------------------");
        }
    }

    /**
     * Returns the total number of users.
     *
     * @return Number of registered users.
     */
    public int getTotalUsers() {
        return users.size();
    }

    /**
     * Removes a user from the system.
     *
     * @param userId The ID of the user to remove.
     * @return true if the user was removed successfully,
     *         false if the user was not found.
     */
    public boolean removeUser(int userId) {

        User user = searchUser(userId);

        if (user != null) {
            users.remove(user);
            return true;
        }

        return false;
    }

}