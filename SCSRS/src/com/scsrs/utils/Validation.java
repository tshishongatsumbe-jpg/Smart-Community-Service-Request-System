package com.scsrs.utils;

/**
 * Provides validation methods for user input.
 *
 * @author Shonisani
 * @version 2.0
 */
public class Validation {

    /**
     * Validates an email address.
     *
     * @param email The email to validate.
     * @return true if valid, otherwise false.
     */
    public boolean isValidEmail(String email) {

        return email != null
                && email.contains("@")
                && email.contains(".");
    }

    /**
     * Validates a phone number.
     *
     * @param phoneNumber The phone number to validate.
     * @return true if valid, otherwise false.
     */
    public boolean isValidPhoneNumber(String phoneNumber) {

        return phoneNumber != null
                && phoneNumber.matches("\\d{10}");
    }

    /**
     * Validates a password.
     *
     * Password must contain at least 6 characters.
     *
     * @param password The password.
     * @return true if valid, otherwise false.
     */
    public boolean isValidPassword(String password) {

        return password != null
                && password.length() >= 6;
    }

    /**
     * Checks whether a string is empty.
     *
     * @param text The text to check.
     * @return true if empty, otherwise false.
     */
    public boolean isEmpty(String text) {

        return text == null || text.trim().isEmpty();
    }

}