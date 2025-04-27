package com.automation.restfulbooker.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author psawale
 * @project restfulbooker
 * @date 4/26/2025
 */
public class CommonFunctions {

    static String formattedDate;

    public static String getCurrentDate() {
        formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return formattedDate;
    }

    public static String getTwoDaysAfterCurrentDate() {
        LocalDate futureDate = LocalDate.now().plusDays(2);
        formattedDate = futureDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return formattedDate;
    }
}
