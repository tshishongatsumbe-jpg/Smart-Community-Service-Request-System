package com.scsrs.app;

import com.scsrs.reports.Report;
import com.scsrs.services.ReportService;
import com.scsrs.services.UserService;
import com.scsrs.users.Resident;

/**
 * Entry point for the Smart Community Service Request System.
 *
 * @author Shonisani
 * @version 1.0
 */
public class Main {

  public static void main(String[] args) {

    // Create services
    UserService userService = new UserService();
    ReportService reportService = new ReportService();

    // Create a sample resident
    Resident resident = new Resident(
            1,
            "John",
            "Smith",
            "john@gmail.com",
            "12345",
            "0821234567"
    );

    // Add resident to the system
    userService.addUser(resident);

    // Create a sample report
    Report report = new Report(
            1001,
            "Water Leak",
            "Water leaking near the community hall.",
            "Water",
            "Open",
            resident
    );

    // Add report
    reportService.addReport(report);

    // Display data
    System.out.println("===== USERS =====");
    userService.viewAllUsers();

    System.out.println("\n===== REPORTS =====");
    reportService.viewAllReports();

  }
}