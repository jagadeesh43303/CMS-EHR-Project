# Meaningful Use Analysis

## Overview
This Java application fetches data from the CMS EHR Incentive Program Measures API and prints the percentage of eligible and critical access hospitals that demonstrated Meaningful Use of CEHRT in the year 2014, organized by state in descending order.

## Technologies Used
- Java
- Maven
- Jackson Databind (for JSON parsing)
- Apache HttpClient (for making HTTP requests)
- Docker (for containerization)

## Project Structure
meaningful-use-analysis/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── MeaningfulUseFetcher.java
│   │   └── resources/
│   └── test/
├── pom.xml
└── README.md


Setup and Installation
1. download the zip file
2. Navigate to the project directory: Open a terminal and change to the project directory: cd path/to/meaningful-use-analysis
3. Install dependencies: Use Maven to install the project dependencies:mvn install
4. Build the project: To build the project and create a runnable jar file:mvn package


Usage
To run the application, use the following command:java -jar target/meaningful-use-analysis-1.0-SNAPSHOT.jar
This will fetch data from the specified API and display the percentage of eligible and critical access hospitals that demonstrated Meaningful Use in 2014.

Data Source
The data is retrieved from the following API endpoint: https://www.healthit.gov/data/open-api?source=Meaningful-Use-Acceleration-Scorecard.csv


Thank you for reviewing my project!



