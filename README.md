# Restful Booker API Automation

This is a Java-based automation framework for testing the [Restful Booker API](https://restful-booker.herokuapp.com/). 
It uses REST-assured, TestNG, and Jackson for serialization/deserialization, and follows a clean structure with reusable utilities.
##  Features

-  Auth token generation for secured API access
-  Booking creation and retrieval tests
-  Retry logic for flaky tests
-  POJO-based validation with Jackson
-  Modular design with config and test data utilities
-  TestNG framework support with custom assertions

##  Tech Stack

- Java
- REST-assured
- TestNG 
- Jackson (for JSON POJOs)
- Spring Boot (for test context loading)

## Project Structure for restfulbooker
```
restfulbooker/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/automation/restfulbooker/
│   │   │       ├── enums/                  // Constants (e.g., Content-Type, Status Codes)
│   │   │       ├── POJO/                    // Plain Java Objects (Request/Response Body Models)
│   │   │       ├── utils/                   // Utility classes (Common functions, Config readers, etc.)
│   │   │       └── RestfulbookerApplication.java  // Main application runner (optional)
│   │   └── resources/
│   │       └── application.properties       // Configuration (Base URL, Credentials, etc.)
│
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   └── com/automation/restfulbooker/
│   │   │       ├── API/                     // Actual API Test Cases
│   │   │       ├── helper/                  // Helper methods for API calls, payload builders, etc.
│   │   │       └── RestfulbookerApplicationTests.java  // (Optional) Test Bootstrap or initial sanity tests
│   ├── resources/
│   │   └── application.properties       // Configuration (Base URL, Credentials, etc.)
│
├── suites/
│   └── testng.xml                           // TestNG Suite file to organize and run test cases
│
├── pom.xml                                   // Maven dependencies and plugins
├── README.md                                 // Project documentation (optional)
└── target/                                   // Build output (generated automatically)
```


## Run Project 
mvn clean test


