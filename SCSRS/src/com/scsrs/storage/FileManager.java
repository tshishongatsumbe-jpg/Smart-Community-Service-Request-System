package com.scsrs.storage;

import com.scsrs.reports.Report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles file operations for the
 * Smart Community Service Request System.
 *
 * @author Shonisani
 * @version 2.0
 */
public class FileManager {

    /**
     * Saves all reports to a text file.
     *
     * @param reports List of reports.
     * @param fileName Name of the file.
     */
    public void saveReports(ArrayList<Report> reports, String fileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (Report report : reports) {

                writer.write(report.toString());
                writer.newLine();
                writer.write("----------------------------------------");
                writer.newLine();

            }

            System.out.println("\nReports saved successfully to " + fileName);

        } catch (IOException e) {

            System.out.println("Error saving reports: " + e.getMessage());

        }

    }

}