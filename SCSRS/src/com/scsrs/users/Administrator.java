package com.scsrs.users;

/**
     * Represents an Administrator in the Smart Community Service Request System.
     * An Administrator manages reports and assigns work to field workers.
     *
     * @author Shonisani
     * @version 1.0
     */
    public class Administrator extends User {

        /**
         * Constructor for Administrator.
         *
         * @param userId The administrator's ID.
         * @param firstName The administrator's first name.
         * @param lastName The administrator's last name.
         * @param email The administrator's email.
         * @param password The administrator's password.
         * @param phoneNumber The administrator's phone number.
         */
        public Administrator(int userId, String firstName, String lastName,
                             String email, String password, String phoneNumber) {

            super(userId, firstName, lastName, email, password, phoneNumber);
        }

        /**
         * Displays the user's role.
         */
        public void displayRole() {
            System.out.println("Role: Administrator");
        }

        /**
         * Displays administrator information.
         */
        @Override
        public String toString() {
            return super.toString() + "\nRole: Administrator";
        }
    }

