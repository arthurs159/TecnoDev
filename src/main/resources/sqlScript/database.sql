CREATE DATABASE TecnoDev;

USE TecnoDev;

CREATE TABLE Category
(id BIGINT PRIMARY KEY AUTO_INCREMENT UNIQUE NOT NULL,
`name` VARCHAR(50) NOT NULL,
`code` VARCHAR(50) NOT NULL,
`description` TEXT,
study_guide VARCHAR(50),
`active` BOOLEAN,
order_in_system SMALLINT,
image_url VARCHAR(150),
color_code VARCHAR(10));

CREATE TABLE Subcategory
(id BIGINT PRIMARY KEY AUTO_INCREMENT UNIQUE NOT NULL,
`name` VARCHAR(50) NOT NULL,
`code` VARCHAR(50) NOT NULL,
`description` TEXT,
study_guide VARCHAR(50),
`active` BOOLEAN,
order_in_system SMALLINT,
category_id BIGINT NOT NULL,
FOREIGN KEY (category_id) REFERENCES Category(id));

CREATE TABLE Course
(id BIGINT PRIMARY KEY AUTO_INCREMENT UNIQUE NOT NULL,
`name` VARCHAR(100) NOT NULL,
`code` VARCHAR(100) NOT NULL,
estimated_time_in_hours SMALLINT,
visibility VARCHAR(10),
target_audience TEXT,
teacher VARCHAR(50),
`description` TEXT,
developed_skills TEXT,
subcategory_id BIGINT NOT NULL,
FOREIGN KEY (subcategory_id) REFERENCES Subcategory (id));

CREATE TABLE Section
(id BIGINT PRIMARY KEY AUTO_INCREMENT UNIQUE NOT NULL,
`name` VARCHAR(50) NOT NULL,
`code` VARCHAR(50) NOT NULL,
order_in_system SMALLINT,
`active` BOOLEAN,
exam BOOLEAN,
course_id BIGINT NOT NULL,
FOREIGN KEY (`course_id`) REFERENCES Course(id));

CREATE TABLE Activity
(id BIGINT PRIMARY KEY AUTO_INCREMENT UNIQUE NOT NULL,
title TEXT,
`code` VARCHAR(50) NOT NULL,
`active` BOOLEAN,
order_in_system SMALLINT,
explication TEXT NOT NULL,
url TEXT NOT NULL,
estimated_time_in_minutes SMALLINT,
transcription TEXT,
statement TEXT NOT NULL,
queston_type ENUM('SINGLE_CHOICE', 'MULTIPLE_CHOICE', 'TRUE_FALSE'),
section_id BIGINT NOT NULL,
FOREIGN KEY (section_id) REFERENCES Section(id));
