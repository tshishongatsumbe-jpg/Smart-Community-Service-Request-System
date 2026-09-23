package com.scsrs.users;

/**
 * Represents a generic user in the Smart Community Service Request System.
 * This is the parent class for Resident, Administrator, and FieldWorker.
 *
 * @author Shonisani
 * @version 2.0
 */
public class User {

    // ==========================
    // Attributes
    // ==========================

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;

    // ==========================
    // Constructor
    // ==========================

    /**
     * Creates a new User.
     *
     * @param userId Unique user ID.
     * @param firstName User's first name.
     * @param lastName User's last name.
     * @param email User's email address.
     * @param password User's password.
     * @param phoneNumber User's phone number.
     */
    public User(int userId,
                String firstName,
                String lastName,
                String email,
                String password,
                String phoneNumber) {

        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // ==========================
    // Getters and Setters
    // ==========================

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // ==========================
    // Methods
    // ==========================

    /**
     * Simulates user login.
     */
    public void login() {
        System.out.println(getFullName() + " has logged in.");
    }

    /**
     * Simulates user logout.
     */
    public void logout() {
        System.out.println(getFullName() + " has logged out.");
    }

    /**
     * Displays the user's role.
     * Child classes override this method.
     */
    public void displayRole() {
        System.out.println("Role: User");
    }

    // ==========================
    // toString()
    // ==========================

    @Override
    public String toString() {

        return "User ID: " + userId +
                "\nName: " + getFullName() +
                "\nEmail: " + email +
                "\nPhone Number: " + phoneNumber;
    }
}