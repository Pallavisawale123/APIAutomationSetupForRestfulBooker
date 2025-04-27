package com.automation.restfulbooker.API;

import com.automation.restfulbooker.POJO.Booking;
import com.automation.restfulbooker.POJO.BookingDates;
import com.automation.restfulbooker.enums.ContentTypeConstants;
import com.automation.restfulbooker.enums.StatusCode;
import com.automation.restfulbooker.helper.AssertHelper;
import com.automation.restfulbooker.utils.CommonFunctions;
import com.automation.restfulbooker.utils.PropertyReader;
import com.automation.restfulbooker.utils.RestAssuredConfig;
import com.automation.restfulbooker.utils.TestDataStore;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertNotNull;

/**
 * @author psawale
 * @project restfulbooker
 * @date 4/25/2025
 */

@SpringBootTest
//@Listeners({RetryListener.class,TestListeners.class})
public class RestfulBooker {
    private static final Logger log = LoggerFactory.getLogger(RestfulBooker.class);

    private static int attempt = 1;
    private static String authToken;
    String serverAddress = PropertyReader.propertyReader("src//test//resources//application.properties",
            "serverAddress");
    String username = PropertyReader.propertyReader("src//test//resources//application.properties",
            "username");
    String password = PropertyReader.propertyReader("src//test//resources//application.properties",
            "password");
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void setup() {
        RestAssuredConfig.setup();
    }

    public RequestSpecification requestSpec() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(serverAddress);
        requestSpecBuilder.setContentType(ContentTypeConstants.JSON.getValue());
        return requestSpecBuilder.build();
    }

    public ResponseSpecification responseSpec() {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectContentType(ContentTypeConstants.JSON.getValue());
        return responseSpecBuilder.build();
    }

    @BeforeClass
    public void generateAuthToken() {
        Map<String, String> authPayload = new HashMap<>();
        authPayload.put("username", username);
        authPayload.put("password", password);

        Response response = (Response) given().spec(requestSpec())
                .body(authPayload)
                .post("/auth").then().spec(responseSpec()).extract();

        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        authToken = jsonPath.getString("token");

        AssertHelper.assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code, "Verified status code for Authentication API");
    }

    @Test
    public void validateTokenExists() {
        assertNotNull(authToken, "Auth token should not be null");
        log.info("Auth Token generated successfully" + authToken);
    }


    @Test
    public void retryTest() {
        System.out.println("Attempt: " + attempt);
        if (attempt <= 1) {
            attempt++;
            Assert.fail("Failing on first attempt");
        }
        System.out.println("Test passed on attempt " + attempt);
    }


    @Test
    public void createBooking() {

        // Create BookingDates object
        BookingDates dates = new BookingDates(CommonFunctions.getCurrentDate(), CommonFunctions.getTwoDaysAfterCurrentDate());

        String firstname = "John";
        String lastname = "Doe" + System.currentTimeMillis();  // Unique lastname
        int totalprice = new Random().nextInt(500) + 100;
        boolean depositpaid = true;
        String additionalneeds = "Lunch";

        // Create Booking object
        Booking booking = new Booking(firstname, lastname, totalprice, depositpaid, dates, additionalneeds);

        Response response = (Response) given().spec(requestSpec())
                .body(booking)
                .when()
                .post("/booking").then().spec(responseSpec()).extract();

        AssertHelper.assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code, "Verified status code for craeted bokking");
        AssertHelper.assertNotNull(response.jsonPath().getInt("bookingid"), "Verifying booking is not null");
        TestDataStore.set("bookingId", response.jsonPath().getInt("bookingid"));

    }

    @Test
    public void getBookingDetails() {
        Response response = (Response) given().spec(requestSpec())
                .pathParam("id", TestDataStore.getString("bookingId"))
                .when()
                .get("/booking/{id}")
                .then().spec(responseSpec()).extract();
        String jsonResponse = response.asString();

        objectMapper.findAndRegisterModules(); // Registers JavaTimeModule automatically
        try {
            Booking booking = objectMapper.readValue(jsonResponse, Booking.class);
            AssertHelper.assertEquals("John", booking.getFirstname(), "Able to access the values using POJO");
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize JSON response", e);
        }

    }
}