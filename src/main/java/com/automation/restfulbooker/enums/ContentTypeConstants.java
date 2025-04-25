package com.automation.restfulbooker.enums;

import io.restassured.http.ContentType;

/**
 * @author psawale
 * @project restfulbooker
 * @date 4/20/2025
 */
public enum ContentTypeConstants {
    JSON("application/json"),
    XML("application/xml"),
    TEXT("text/plain"),
    HTML("text/html"),
    URLENC("application/x-www-form-urlencoded"),
    MULTIPART("multipart/form-data");

    private final String value;

    ContentTypeConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
