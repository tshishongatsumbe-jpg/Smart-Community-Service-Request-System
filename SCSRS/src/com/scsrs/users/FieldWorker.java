package com.scsrs.users;

/**
 * Represents a Field Worker in the Smart Community Service Request System.
 * A Field Worker is responsible for resolving community service requests.
 *
 * @author Shonisani
 * @version 2.0
 */
public class FieldWorker extends User {

    /**
     * Constructor for FieldWorker.
     *
     * @param userId The field worker's ID.
     * @param firstName The field worker's first name.
     * @param lastName The field worker's last name.
     * @param email The field worker's email.
     * @param password The field worker's password.
     * @param phoneNumber The field worker's phone number.
     */
    public FieldWorker(int userId,
                       String firstName,
                       String lastName,
                       String email,
                       String password,
                       String phoneNumber) {

        super(userId, firstName, lastName, email, password, phoneNumber);
    }

    /**
     * Displays the field worker's role.
     */
    @Override
    public void displayRole() {
        System.out.println("Role: Field Worker");
    }

    /**
     * Returns the field worker's information.
     *
     * @return Field worker details.
     */
    @Override
    public String toString() {
        return super.toString() + "\nRole: Field Worker";
    }
}