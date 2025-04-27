package com.automation.restfulbooker.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author psawale
 * @project restfulbooker
 * @date 4/26/2025
 */
public class TestDataStore {
    private static final Map<String, Object> testData = new HashMap<>();

    public static void set(String key, Object value) {
        testData.put(key, value);
    }

    public static Object get(String key) {
        return testData.get(key);
    }

    public static String getString(String key) {
        return testData.get(key).toString();
    }

    public static int getInt(String key) {
        return (int) testData.get(key);
    }

    public static boolean getBoolean(String key) {
        return (boolean) testData.get(key);
    }

    public static void clear() {
        testData.clear();
    }
}