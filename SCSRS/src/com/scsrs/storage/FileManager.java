package com.scsrs.storage;

import com.scsrs.reports.Report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles saving application data to files.
 *
 * @author Shonisani
 * @version 1.0
 */
public class FileManager {

    /**
     * Saves all reports to a text file.
     *
     * @param reports The list of reports.
     * @param fileName The name of the file.
     */
    public void saveReports(ArrayList<Report> reports, String fileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (Report report : reports) {

                writer.write("Report ID: " + report.getReportId());
                writer.newLine();

                writer.write("Title: " + report.getTitle());
                writer.newLine();

                writer.write("Description: " + report.getDescription());
                writer.newLine();

                writer.write("Category: " + report.getCategory());
                writer.newLine();

                writer.write("Status: " + report.getStatus());
                writer.newLine();

                writer.write("Resident: " + report.getResident().getFullName());
                writer.newLine();

                writer.write("----------------------------------");
                writer.newLine();
            }

            System.out.println("Reports saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving reports: " + e.getMessage());

        }
    }

}