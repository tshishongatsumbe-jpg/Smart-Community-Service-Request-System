package coscsrs.users;

/**
 * Represents a generic user in the Smart Community Service Request System.
 * This is the parent class for Resident, Administrator, and FieldWorker.
 *
 * @author Shonisani
 * @version 1.0
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
    public User(int userId, String firstName, String lastName,
                String email, String password, String phoneNumber) {

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

    public void login() {
        System.out.println(firstName + " has logged in.");
    }

    public void logout() {
        System.out.println(firstName + " has logged out.");
    }
    public String getFullName(){
        return firstName + " , " +  lastName;
    }

    @Override
    public String toString() {
        return "User ID: " + userId +
                "\nName: " + firstName + " " + lastName +
                "\nEmail: " + email +
                "\nPhone: " + phoneNumber;
    }
}