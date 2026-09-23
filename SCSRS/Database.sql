-- ============================================
-- Smart Community Service Request System
-- Database Schema
-- ============================================

CREATE DATABASE scsrs;

USE scsrs;


-- ============================================
-- USERS
-- ============================================

CREATE TABLE Users (
                       user_id INT PRIMARY KEY AUTO_INCREMENT,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       phone_number VARCHAR(20) NOT NULL
);


-- ============================================
-- RESIDENTS
-- ============================================

CREATE TABLE Residents (
                           resident_id INT PRIMARY KEY,
                           FOREIGN KEY (resident_id)
                               REFERENCES Users(user_id)
                               ON DELETE CASCADE
);


-- ============================================
-- ADMINISTRATORS
-- ============================================

CREATE TABLE Administrators (
                                administrator_id INT PRIMARY KEY,
                                FOREIGN KEY (administrator_id)
                                    REFERENCES Users(user_id)
                                    ON DELETE CASCADE
);


-- ============================================
-- FIELD WORKERS
-- ============================================

CREATE TABLE FieldWorkers (
                              field_worker_id INT PRIMARY KEY,
                              FOREIGN KEY (field_worker_id)
                                  REFERENCES Users(user_id)
                                  ON DELETE CASCADE
);


-- ============================================
-- REPORT CATEGORIES
-- ============================================

CREATE TABLE ReportCategories (
                                  category_id INT PRIMARY KEY AUTO_INCREMENT,
                                  category_name VARCHAR(50) NOT NULL UNIQUE
);


-- ============================================
-- REPORT STATUSES
-- ============================================

CREATE TABLE ReportStatuses (
                                status_id INT PRIMARY KEY AUTO_INCREMENT,
                                status_name VARCHAR(30) NOT NULL UNIQUE
);


-- ============================================
-- REPORTS
-- ============================================

CREATE TABLE Reports (
                         report_id INT PRIMARY KEY AUTO_INCREMENT,

                         service_number VARCHAR(50) NOT NULL,

                         title VARCHAR(150) NOT NULL,

                         description TEXT NOT NULL,

                         category_id INT NOT NULL,

                         status_id INT NOT NULL,

                         resident_id INT NOT NULL,

                         assigned_worker_id INT NULL,

                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                             ON UPDATE CURRENT_TIMESTAMP,

                         FOREIGN KEY (category_id)
                             REFERENCES ReportCategories(category_id),

                         FOREIGN KEY (status_id)
                             REFERENCES ReportStatuses(status_id),

                         FOREIGN KEY (resident_id)
                             REFERENCES Residents(resident_id),

                         FOREIGN KEY (assigned_worker_id)
                             REFERENCES FieldWorkers(field_worker_id)
                             ON DELETE SET NULL
);