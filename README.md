# Project JPA JO

## Description

This project is a Java application that uses JPA for data persistence. It provides a menu-driven interface for managing sports events data.

## Features

- List sports, epreuves, events, and organizations
- Import data from CSV files
- Display medals sorted by year, sport, and event

## Technologies Used

- Java
- Maven
- JPA

## Project Structure

- [src/main/java/fr/diginamic/daos](https://github.com/AyoubBenziza/diginamic-jpa-jo/tree/main/src/main/java/fr/diginamic/daos): This directory contains your Data Access Object (DAO) classes, which are responsible for interacting with the database.
- [src/main/java/fr/diginamic/entities](https://github.com/AyoubBenziza/diginamic-jpa-jo/tree/main/src/main/java/fr/diginamic/entities): This directory contains your entity classes, which represent the tables in your database.
- [src/main/java/fr/diginamic/interfaces](https://github.com/AyoubBenziza/diginamic-jpa-jo/tree/main/src/main/java/fr/diginamic/interfaces): This directory contains your interface classes, which define the methods that your DAO classes must implement.
- [src/main/java/fr/diginamic/utils](https://github.com/AyoubBenziza/diginamic-jpa-jo/tree/main/src/main/java/fr/diginamic/utils): This directory contains utility classes like `Menu.java`.
- [src/main/resources](https://github.com/AyoubBenziza/diginamic-jpa-jo/tree/main/src/main/resources): This directory contains resources like configuration files. The `logback.xml` file is a configuration file for Logback, which is a logging framework for Java.
- [src/test/java](https://github.com/AyoubBenziza/diginamic-jpa-jo/tree/main/src/test/java): This directory would contain your Java test files.
- [.gitignore](https://github.com/AyoubBenziza/diginamic-jpa-jo/blob/main/.gitignore): This file tells Git which files or directories to ignore in your project.
- [pom.xml](https://github.com/AyoubBenziza/diginamic-jpa-jo/blob/main/pom.xml): This is your Maven Project Object Model (POM) file. It contains information about the project and configuration details used by Maven to build the project.

## Setup

To run this project, you need to have Java and Maven installed on your machine.

1. Clone the repository: `git clone https://github.com/AyoubBenziza/diginamic-jpa-jo.git`
2. Navigate to the project directory: `cd diginamic-jpa-jo`
3. Build the project: `mvn clean install`
4. Run the application: `java -jar target/diginamic-jpa-jo-1.0-SNAPSHOT.jar`

## Usage

After launching the application, you will be presented with a menu. Enter the number corresponding to the action you want to perform.