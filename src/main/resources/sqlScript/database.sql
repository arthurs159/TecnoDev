CREATE DATABASE TecnoDev;

USE TecnoDev;

CREATE TABLE Category
(id BIGINT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL,
`code` VARCHAR(100) UNIQUE NOT NULL,
`description` TEXT,
study_guide TEXT,
`active` BOOLEAN,
order_in_system SMALLINT,
image_url VARCHAR(200),
color_code VARCHAR(10));

CREATE TABLE Subcategory
(id BIGINT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL,
`code` VARCHAR(100) UNIQUE NOT NULL,
`description` TEXT,
study_guide TEXT,
`active` BOOLEAN,
order_in_system SMALLINT,
category_id BIGINT NOT NULL,
FOREIGN KEY (category_id) REFERENCES Category(id));

CREATE TABLE Course
(id BIGINT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL,
`code` VARCHAR(100) UNIQUE NOT NULL,
estimated_time_in_hours SMALLINT,
visibility ENUM('PUBLIC', 'PRIVATE') DEFAULT 'PRIVATE',
target_audience TEXT,
teacher VARCHAR(50),
`description` TEXT,
developed_skills TEXT,
subcategory_id BIGINT NOT NULL,
FOREIGN KEY (subcategory_id) REFERENCES Subcategory (id));

CREATE TABLE Section
(id BIGINT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) NOT NULL,
`code` VARCHAR(100) UNIQUE NOT NULL,
order_in_system SMALLINT,
`active` BOOLEAN,
exam BOOLEAN,
course_id BIGINT NOT NULL,
FOREIGN KEY (`course_id`) REFERENCES Course(id));

CREATE TABLE Activity
(id BIGINT PRIMARY KEY AUTO_INCREMENT,
activity_type VARCHAR(50),
title TEXT,
`code` VARCHAR(100) UNIQUE NOT NULL,
`active` BOOLEAN,
order_in_system SMALLINT,
explication TEXT NOT NULL,
url TEXT NOT NULL,
estimated_time_in_minutes SMALLINT,
transcription TEXT,
statement TEXT NOT NULL,
question_type ENUM('SINGLE_CHOICE', 'MULTIPLE_CHOICE', 'TRUE_FALSE'),
section_id BIGINT NOT NULL,
FOREIGN KEY (section_id) REFERENCES Section(id));

CREATE TABLE Alternative
(id BIGINT PRIMARY KEY AUTO_INCREMENT,
alternative_text TEXT,
order_in_system SMALLINT,
correct BOOLEAN,
justification TEXT,
activity_id BIGINT NOT NULL,
FOREIGN KEY (activity_id) REFERENCES Activity(id));

CREATE TABLE User
(id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50),
email VARCHAR(100),
password VARCHAR (50));

CREATE TABLE Role
(id BIGINT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(50));

CREATE TABLE User_role
(user_id bigint,
 role_id bigint)

INSERT INTO User (name, email, password)
VALUES ('Arthur', 'arthur@email.com', '$2a$10$vyVwTTcztUl8b1ty62LUce.e0YsyPufAwa0neAodqf5hmF5QfwMpe')