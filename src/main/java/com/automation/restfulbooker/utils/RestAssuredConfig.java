package com.automation.restfulbooker.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;

/**
 * @author psawale
 * @project restfulbooker
 * @date 4/26/2025
 */
public class RestAssuredConfig {
    public static void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        RestAssured.config = RestAssured.config()
                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                        .defaultObjectMapper((io.restassured.mapper.ObjectMapper) objectMapper));
    }
}