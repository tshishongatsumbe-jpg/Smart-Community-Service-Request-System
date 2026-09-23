WTC-VASNX8KN
# Smart Community Service Request System (SCSRS)

## 1. Project Overview

The **Smart Community Service Request System (SCSRS)** is a Java-based console application designed to help residents report community service problems and allow administrators and field workers to manage and resolve those reports.

The system provides a structured way for residents to submit service requests such as water problems, electricity issues, road problems, sanitation, waste collection, and street-light problems.

Once a report is submitted, administrators can manage and assign reports to field workers. Field workers can view reports assigned to them and mark completed reports as resolved.

The system demonstrates important **Object-Oriented Programming (OOP)** concepts, including:

* Encapsulation
* Inheritance
* Polymorphism
* Abstraction through system design
* Separation of responsibilities
* Validation
* File persistence

---

## 2. Problem Statement

Community service problems need to be reported and tracked efficiently.

Without a central system, residents may struggle to report problems, administrators may struggle to organize requests, and field workers may not know which problems have been assigned to them.

SCSRS provides a simple workflow:

```text
Resident
   ↓
Submit Service Request
   ↓
Report Created
   ↓
Administrator Assigns Report
   ↓
Field Worker Receives Report
   ↓
Field Worker Resolves Report
   ↓
Report Status = RESOLVED
```

---

## 3. Main Features

### Resident

Residents can:

* Register an account
* Log into the system
* Submit a new service request
* Select a service category
* Provide a description of the problem
* View their submitted reports
* Search for a report using its Report ID
* View the current status of their reports

### Administrator

Administrators can manage community service reports and assign reports to field workers.

The administrator is responsible for managing the reports and ensuring that requests are assigned for processing.

### Field Worker

Field workers can:

* Log into the system
* View reports assigned to them
* View all community reports
* Resolve reports assigned to them
* Update the status of a report to `RESOLVED`

---

## 4. User Roles

The system contains three main user roles.

| Role          | Responsibility                             |
| ------------- | ------------------------------------------ |
| Resident      | Reports community service problems         |
| Administrator | Manages and assigns reports                |
| Field Worker  | Handles assigned reports and resolves them |

The roles are implemented using inheritance.

```text
                 User
                  |
        -----------------------
        |          |          |
    Resident   Administrator  FieldWorker
```

---

## 5. Report Categories

Residents can report problems in the following categories:

* Water
* Electricity
* Roads
* Sanitation
* Waste
* Streetlights
* Other

These categories are represented using the `ReportCategory` enum.

```java
public enum ReportCategory {
    WATER,
    ELECTRICITY,
    ROADS,
    SANITATION,
    WASTE,
    STREET_LIGHTS,
    OTHER
}
```

---

## 6. Report Status

Each report has a status that represents its current stage.

The available statuses are:

```text
OPEN
   ↓
IN_PROGRESS
   ↓
RESOLVED
```

### OPEN

The report has been submitted by a resident but has not yet been assigned to a field worker.

### IN_PROGRESS

The report has been assigned to a field worker and is being handled.

### RESOLVED

The field worker has completed the work associated with the report.

The statuses are represented using the `ReportStatus` enum.

```java
public enum ReportStatus {
    OPEN,
    IN_PROGRESS,
    RESOLVED
}
```

---

# 7. Report Management

Each report contains important information such as:

* Report ID
* Service Number
* Title
* Description
* Category
* Status
* Resident who submitted the report
* Assigned field worker

Example:

```text
Report ID: 3
Service Number: WATER-2
Title: Broken Water Pipe
Description: Water is leaking from a pipe near the community hall.
Category: WATER
Status: IN_PROGRESS
Submitted By: John Smith
Assigned Worker: David Mokoena
```

---

## 8. Report IDs and Service Numbers

### Report ID

Every report receives a unique numerical Report ID.

The `ReportService` determines the next available ID based on the existing reports.

For example:

```text
Report 1
Report 2
Report 3
```

### Service Number

A service number is generated according to the report category.

For example:

```text
WATER-1
WATER-2
ELECTRICITY-1
ROADS-1
```

This makes it easier to identify requests belonging to the same service category.

---

# 9. Resident Registration

Residents can create an account by providing:

* First name
* Last name
* Email
* Password
* Phone number

The system validates the information before creating the account.

### Validation includes:

* First name cannot be empty
* Last name cannot be empty
* Names must contain letters
* Email must be in a valid format
* Email must not already exist
* Password must contain at least 6 characters
* Phone number must contain exactly 10 digits

Names are also formatted so that the first letter is capitalized.

Example:

```text
Input:
john

Stored:
John
```

---

# 10. Authentication

The `AuthenticationService` is responsible for logging users into the system.

The user provides:

```text
Email
Password
```

The system searches through registered users and checks whether the credentials match.

Email comparison is case-insensitive.

For example:

```text
john@example.com
JOHN@EXAMPLE.COM
John@example.com
```

are treated as the same email address for login purposes.

If authentication is successful, the system returns the corresponding `User`.

If authentication fails, the system provides an appropriate login message.

---

# 11. Resident Menu

After successfully logging in, a resident can access:

```text
1. Submit a New Service Request
2. View My Submitted Reports
3. Search for a Report
4. Logout
```

### Submit a New Service Request

The resident selects a category and enters a description of the problem.

The system creates a new report with:

```text
Status = OPEN
```

The resident receives the Report ID and service information.

---

# 12. Administrator Responsibilities

The administrator is responsible for managing reports within the system.

A major responsibility is assigning reports to field workers.

When a report is assigned:

```text
Report Status:
OPEN → IN_PROGRESS
```

The report is then associated with a specific field worker.

The system prevents a report from being assigned again if it already has an assigned worker.

---

# 13. Field Worker Menu

The field worker menu provides the following options:

```text
1. View My Assigned Reports
2. Mark a Report as Resolved
3. View All Community Reports
4. Logout
```

### View Assigned Reports

The system checks the assigned worker's user ID against the currently logged-in field worker.

This ensures that a field worker can see reports assigned to them.

### Mark a Report as Resolved

The field worker enters the Report ID.

The system checks that:

1. The report exists.
2. The report has an assigned worker.
3. The assigned worker is the currently logged-in worker.

If all checks pass:

```text
IN_PROGRESS → RESOLVED
```

---

# 14. Object-Oriented Programming

The project demonstrates several OOP concepts.

## Encapsulation

Encapsulation is used by keeping class fields private and accessing them through methods.

Example:

```java
private String firstName;
private String lastName;
private String email;
```

The values are accessed using getters and modified using setters.

```java
public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}
```

This protects the internal state of the object.

---

## Inheritance

The system has a base `User` class.

Other user types inherit from it:

```java
public class Resident extends User{}
```

```java
public class Administrator extends User{}
```

```java
public class FieldWorker extends User{}
```

This allows common user information and behaviour to be defined once in `User`.

---

## Polymorphism

Polymorphism is demonstrated through overridden methods.

For example, the `User` class has:

```java
public void displayRole();
```

The subclasses provide their own implementations.

```java
@Override
public void displayRole() {
    System.out.println("Role: Resident");
}
```

The same method name can therefore behave differently depending on the type of user object.

---

## Abstraction

The system separates different responsibilities into different classes.

For example:

```text
AuthenticationService
        ↓
Handles login

RegistrationService
        ↓
Handles registration

ReportService
        ↓
Handles reports

UserService
        ↓
Handles users

FileManager
        ↓
Handles file storage
```

This means that each class focuses on a particular responsibility instead of placing all system logic inside one class.

---

# 15. Separation of Responsibilities

The project separates responsibilities across different packages and classes.

For example:

```text
Menu
 ↓
Service
 ↓
Model
```

The menu classes handle interaction with the user.

The service classes handle application logic.

The model classes represent the information used by the system.

This makes the project easier to understand, maintain, and extend.

---

# 16. Project Structure

The project uses the following package structure:

```text
src/
└── main/
    └── java/
        └── com/
            └── scsrs/
                ├── menu/
                │   ├── ResidentMenu.java
                │   └── FieldWorkerMenu.java
                │
                ├── reports/
                │   ├── Report.java
                │   ├── ReportCategory.java
                │   └── ReportStatus.java
                │
                ├── services/
                │   ├── AuthenticationService.java
                │   ├── RegistrationService.java
                │   ├── ReportService.java
                │   └── UserService.java
                │
                ├── storage/
                │   └── FileManager.java
                │
                ├── users/
                │   ├── User.java
                │   ├── Resident.java
                │   ├── Administrator.java
                │   └── FieldWorker.java
                │
                └── utils/
                    └── Validation.java
```

---

# 17. Main Classes and Responsibilities

| Class                   | Responsibility                                 |
| ----------------------- | ---------------------------------------------- |
| `User`                  | Base class for system users                    |
| `Resident`              | Represents a resident                          |
| `Administrator`         | Represents an administrator                    |
| `FieldWorker`           | Represents a field worker                      |
| `Report`                | Represents a community service report          |
| `ReportService`         | Creates, searches, assigns and updates reports |
| `UserService`           | Manages users                                  |
| `AuthenticationService` | Handles user login                             |
| `RegistrationService`   | Handles resident registration                  |
| `Validation`            | Performs input validation                      |
| `FileManager`           | Saves reports to a file                        |
| `ResidentMenu`          | Provides resident functionality                |
| `FieldWorkerMenu`       | Provides field worker functionality            |

---

# 18. Validation

The `Validation` utility class provides reusable validation methods.

### Email

```java
isValidEmail{}
```

Checks that the email contains the required email components.

### Phone Number

```java
isValidPhoneNumber();
```

Checks that the phone number contains exactly 10 digits.

### Password

```java
isValidPassword();
```

Checks that the password contains at least 6 characters.

### Empty Input

```java
isEmpty();
```

Checks whether input is empty or null.

Centralizing validation avoids duplicating the same validation logic throughout the application.

---

# 19. File Persistence

The project includes a `FileManager` class for saving reports.

Reports can be written to a file using:

```java
saveReports(ArrayList<Report> reports, String fileName);
```

The reports are written using `BufferedWriter` and `FileWriter`.

This provides a basic form of persistence/export for report information.

The application's main user and report collections are managed using `ArrayList` objects during runtime.

---

# 20. Example System Workflow

A typical use case looks like this:

### Step 1 — Resident Registration

A resident creates an account.

```text
First Name: John
Last Name: Smith
Email: john@example.com
Password: password123
Phone: 0821234567
```

The system validates the information and creates the resident account.

### Step 2 — Login

The resident logs in using their email and password.

### Step 3 — Submit Report

The resident selects:

```text
Category: WATER
Title: Broken Water Pipe
Description: Water is leaking near the community hall.
```

The system creates the report:

```text
Report ID: 1
Service Number: WATER-1
Status: OPEN
```

### Step 4 — Assignment

An administrator assigns the report to a field worker.

The status becomes:

```text
IN_PROGRESS
```

### Step 5 — Field Worker

The field worker logs in and views assigned reports.

The worker sees the water-related report.

### Step 6 — Resolution

After fixing the problem, the field worker marks the report as resolved.

The status becomes:

```text
RESOLVED
```

---

# 21. Technologies Used

The project uses:

* **Java** — Main programming language
* **Maven** — Project/build management
* **IntelliJ IDEA** — Development environment
* **Git** — Version control
* **GitLab** — Source-code repository
* **Java Collections** — Runtime data management
* **File I/O** — Report persistence/export

---

# 22. How to Run the Project

## Requirements

Before running the project, make sure you have:

* Java JDK installed
* Maven installed
* IntelliJ IDEA or another Java IDE
* Git installed if cloning from GitLab

## Clone the Repository

Clone the project from GitLab and open the project in IntelliJ IDEA.

## Maven

Build the project using:

```bash
mvn clean install
```

To compile the project:

```bash
mvn compile
```

To run the tests:

```bash
mvn test
```

If the project has a configured Maven entry point, the application can then be run through the configured main class.

---

# 23. Example User Flow

```text
              ┌──────────────┐
              │    Start     │
              └──────┬───────┘
                     ↓
              ┌──────────────┐
              │    Login     │
              └──────┬───────┘
                     ↓
          ┌──────────┴──────────┐
          ↓                     ↓
     ┌─────────┐          ┌─────────────┐
     │ Resident│          │ Other Roles │
     └────┬────┘          └──────┬──────┘
          ↓                      ↓
   Submit Report          Manage Reports
          ↓                      ↓
        OPEN              Assign Worker
                                 ↓
                            IN_PROGRESS
                                 ↓
                         Field Worker Resolves
                                 ↓
                              RESOLVED
```

---

# 24. Error Handling

The system performs checks before carrying out important operations.

Examples include:

* Preventing duplicate user IDs
* Preventing duplicate email registration
* Rejecting invalid names
* Rejecting invalid phone numbers
* Rejecting short passwords
* Rejecting empty report descriptions
* Checking whether reports exist
* Checking whether a field worker exists
* Preventing reports from being assigned more than once
* Ensuring field workers can only resolve their assigned reports

These checks help prevent invalid operations and improve the reliability of the system.

---

# 25. Future Improvements

Possible future improvements include:

* Database persistence instead of relying mainly on in-memory `ArrayList` data
* Password hashing instead of storing plain-text passwords
* A graphical user interface or web interface
* Better email validation
* More detailed administrator functionality
* Report priority levels
* Report timestamps
* Notifications to residents
* Location/GPS information for service requests
* Search and filtering by category and status
* Automated tests for all service classes
* Role-based access control
* Report history and audit logs

---

# 26. Project Goals

The main goals of SCSRS are to:

1. Provide residents with a simple way to report community problems.
2. Allow reports to be organized according to service categories.
3. Allow administrators to manage and assign reports.
4. Allow field workers to manage their assigned reports.
5. Track the progress of reports from submission to resolution.
6. Demonstrate practical Java and OOP concepts.
7. Apply separation of responsibilities to a real-world software problem.

---

# 27. Conclusion

The **Smart Community Service Request System** provides a structured approach to managing community service requests.

Residents can submit reports, administrators can manage and assign those reports, and field workers can resolve assigned problems.

The project also demonstrates practical software-development concepts such as:

* Object-Oriented Programming
* Encapsulation
* Inheritance
* Polymorphism
* Separation of responsibilities
* Input validation
* File handling
* Service-layer design
* Version control
* Maven project management

The overall report lifecycle is:

```text
OPEN → IN_PROGRESS → RESOLVED
```

This workflow provides a clear representation of how a community service request moves from being reported to being completed.
