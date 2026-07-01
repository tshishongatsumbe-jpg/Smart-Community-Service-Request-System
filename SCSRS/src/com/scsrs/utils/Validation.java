package com.scsrs.utils;

/**
 * Provides validation methods for user input.
 *
 * @author Shonisani
 * @version 1.0
 */
public class Validation {

    /**
     * Checks whether a string is empty.
     *
     * @param text The text to check.
     * @return true if the text is empty, otherwise false.
     */
    public boolean isEmpty(String text) {

        return text == null || text.trim().isEmpty();

    }

    /**
     * Checks whether an email address is valid.
     *
     * @param email The email address.
     * @return true if valid, otherwise false.
     */
    public boolean isValidEmail(String email) {

        if (email == null) {
            return false;
        }

        return email.contains("@") && email.contains(".");

    }

    /**
     * Checks whether a phone number is valid.
     *
     * @param phoneNumber The phone number.
     * @return true if valid, otherwise false.
     */
    public boolean isValidPhoneNumber(String phoneNumber) {

        if (phoneNumber == null) {
            return false;
        }

        return phoneNumber.matches("\\d{10}");

    }

}