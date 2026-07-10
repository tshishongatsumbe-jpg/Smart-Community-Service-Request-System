# Smart Community Service Request System (SCSRS)

## Overview

The Smart Community Service Request System (SCSRS) is a Java console-based application developed using Object-Oriented Programming (OOP) principles. The system enables residents to report community service issues such as water leaks, electricity faults, road damage, sanitation problems, waste collection, and street light failures.

The application allows administrators to manage residents, monitor reports, assign field workers, update report statuses, and save reports to a text file. Field workers can view assigned reports and update their progress.

---

# Features

## Administrator

* Login to the system
* Register new residents
* View all registered users
* View all reports
* Search reports by Report ID
* Update report status
* Assign reports to field workers
* Save reports to a text file

## Resident

* Login to the system
* Submit community service reports
* View personal reports
* Search reports by Report ID

## Field Worker

* Login to the system
* View assigned reports
* Update report status
* Logout

---

# Technologies Used

* Java
* Object-Oriented Programming (OOP)
* IntelliJ IDEA
* Java Collections (ArrayList)
* File Handling (BufferedWriter, FileWriter)
* UML Class Diagram

---

# Project Structure

```
src
└── com
    └── scsrs
        ├── app
        │     Main.java
        │
        ├── users
        │     User.java
        │     Administrator.java
        │     Resident.java
        │     FieldWorker.java
        │
        ├── reports
        │     Report.java
        │
        ├── enums
        │     ReportCategory.java
        │     ReportStatus.java
        │
        ├── services
        │     UserService.java
        │     ReportService.java
        │     AuthenticationService.java
        │
        ├── menu
        │     AdministratorMenu.java
        │     ResidentMenu.java
        │     FieldWorkerMenu.java
        │
        ├── storage
        │     FileManager.java
        │
        └── utils
              Validation.java
```

---

# Default Login Credentials

## Administrator

| Email                                     | Password |
| ----------------------------------------- | -------- |
| [admin@scsrs.com](mailto:admin@scsrs.com) | admin123 |

## Resident

| Email                                   | Password    |
| --------------------------------------- | ----------- |
| [john@scsrs.com](mailto:john@scsrs.com) | resident123 |

## Field Worker 1

| Email                                         | Password  |
| --------------------------------------------- | --------- |
| [worker1@scsrs.com](mailto:worker1@scsrs.com) | worker123 |

## Field Worker 2

| Email                                         | Password  |
| --------------------------------------------- | --------- |
| [worker2@scsrs.com](mailto:worker2@scsrs.com) | worker123 |

---

# How to Run the Project

1. Open the project in IntelliJ IDEA.
2. Open `Main.java`.
3. Run the `Main` class.
4. Select **Login** from the main menu.
5. Log in using one of the default user accounts.
6. Test the available features according to the selected user role.

---

# Report Categories

* Water
* Electricity
* Roads
* Sanitation
* Waste
* Street Lights
* Other

---

# Report Status

* Open
* In Progress
* Resolved

---

# Object-Oriented Programming Concepts Used

This project demonstrates the following OOP concepts:

* Encapsulation
* Inheritance
* Polymorphism
* Abstraction

---

# Data Storage

Reports are saved locally to a text file named:

```
reports.txt
```

The file is generated when the administrator selects **Save Reports** from the Administrator Menu.

---

# Validation

The system validates:

* Email addresses
* Password length
* Phone numbers
* Empty input fields
* Duplicate User IDs
* Duplicate Report IDs

---

# Future Improvements

Future versions of the project could include:

* Database integration (MySQL)
* Graphical User Interface (JavaFX)
* Password encryption
* Search by report category
* Report filtering and sorting
* Email notifications
* User self-registration
* Persistent storage for users and reports
* Dashboard and statistics

---

# Author

**Shonisani Tshishonga**

Smart Community Service Request System (SCSRS)

Version 2.0

---

# License

This project was developed for educational purposes.
