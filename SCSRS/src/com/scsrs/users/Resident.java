package com.scsrs.users;

/**
 * Represents a resident who can submit and track community service requests.
 * A Resident is a type of User.
 *
 * @author Shonisani
 * @version 1.0
 */
public class Resident extends User {

    /**
     * Constructor for Resident.
     *
     * @param userId The unique ID of the resident.
     * @param firstName The resident's first name.
     * @param lastName The resident's last name.
     * @param email The resident's email address.
     * @param password The resident's password.
     * @param phoneNumber The resident's phone number.
     */
    public Resident(int userId, String firstName, String lastName,
                    String email, String password, String phoneNumber) {

        super(userId, firstName, lastName, email, password, phoneNumber);
    }

    /**
     * Displays the role of the user.
     */
    public void displayRole() {
        System.out.println("Role: Resident");
    }

    /**
     * Returns the resident's details.
     */
    @Override
    public String toString() {
        return super.toString() + "\nRole: Resident";
    }
}