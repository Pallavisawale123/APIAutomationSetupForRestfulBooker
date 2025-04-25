package com.automation.restfulbooker.helper;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author psawale
 * @project restfulbooker
 * @date 4/25/2025
 */

public class FailRetry implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test " + result.getName() + " for the " + retryCount + " time.");

            return true;
        }
        return false;
    }
}