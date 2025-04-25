package com.automation.restfulbooker.API;

import com.automation.restfulbooker.enums.ContentTypeConstants;
import com.automation.restfulbooker.enums.StatusCode;
import com.automation.restfulbooker.helper.AssertHelper;
import com.automation.restfulbooker.utils.PropertyReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

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

    String serverAddress = PropertyReader.propertyReader("src//test//resources//application.properties",
            "serverAddress");
    String username = PropertyReader.propertyReader("src//test//resources//application.properties",
            "username");
    String password = PropertyReader.propertyReader("src//test//resources//application.properties",
            "password");

    private static String authToken;

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


}