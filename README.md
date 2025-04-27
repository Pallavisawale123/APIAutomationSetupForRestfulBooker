# Restful Booker API Automation

This is a Java-based automation framework for testing the [Restful Booker API](https://restful-booker.herokuapp.com/). 
It uses REST-assured, TestNG, and Jackson for serialization/deserialization, and follows a clean structure with reusable utilities.
## 📌 Features

- 🔐 Auth token generation for secured API access
- 📋 Booking creation and retrieval tests
- 🔁 Retry logic for flaky tests
- ✅ POJO-based validation with Jackson
- 📦 Modular design with config and test data utilities
- 🧪 TestNG framework support with custom assertions

## 🛠️ Tech Stack

- Java
- REST-assured
- TestNG 
- Jackson (for JSON POJOs)
- Spring Boot (for test context loading)

## 📂 Project Structure for restfulbooker

  restfulbooker/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.automation.restfulbooker/
│   │   │       ├── enums/                        // Constants (e.g., Content-Type, Status Codes)
│   │   │       ├── POJO/                         // Plain Java Objects (Request/Response Models)
│   │   │       │    ├── Booking.java
│   │   │       │    ├── BookingDates.java
│   │   │       ├── utils/                        // Utility classes
│   │   │       │    ├── CommonFunctions.java    
│   │   │       │    ├── PropertyReader.java
│   │   │       │    ├── RestAssuredConfig.java
│   │   │       │    ├── TestDataStore.java
│   │   │       └── RestfulbookerApplication.java // (Optional) Main application runner
│   ├── resources/
│   │   └── application.properties                // Configuration file (baseURI, credentials, etc.)

├── src/
│   ├── test/
│   │   ├── java/
│   │   │   └── com.automation.restfulbooker/
│   │   │       ├── API/                          // API Test Classes
│   │   │       │    ├── CreateBookingTests.java
│   │   │       │    ├── UpdateBookingTests.java
│   │   │       │    ├── DeleteBookingTests.java
│   │   │       │    ├── GetBookingTests.java
│   │   │       ├── helper/                       // Helper classes
│   │   │       │    ├── AuthHelper.java
│   │   │       │    ├── PayloadBuilder.java
│   │   │       │    ├── ResponseValidator.java
│   │   │       │    ├── RequestHelper.java
│   │   │       └── RestfulbookerApplicationTests.java // Bootstrap or initial sanity test class
│   ├── resources/
│   │   ├── testdata/
│   │   │    ├── createBookingPayload.json
│   │   │    ├── updateBookingPayload.json
│   │   └── (Optional mock responses / external files)

├── suites/
│   └── testng.xml                               // TestNG Suite to manage test execution

├── pom.xml                                      // Maven dependency management
├── README.md                                    // Project documentation and instructions

├── target/                                      // (Generated after build) Compiled classes and reports

