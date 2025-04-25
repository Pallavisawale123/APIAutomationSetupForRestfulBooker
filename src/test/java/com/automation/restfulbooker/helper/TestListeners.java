package com.automation.restfulbooker.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

/**
 * @author psawale
 * @project restfulbooker
 * @date 4/25/2025
 */
public class TestListeners implements ITestListener, ISuiteListener, IClassListener {
    private static final Logger log = LoggerFactory.getLogger(TestListeners.class);

    @Override
    public void onStart(ISuite suite) {
        // Code to execute before the suite starts, e.g., creating a CSV file.
        log.info("Suite execution started: {}", suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        // Code to execute after the suite finishes, e.g., generating reports.
        log.info("Suite execution finished: {}", suite.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Started execution of test: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed successfully: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failed: {}", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test skipped: {}", result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.warn("Test failed but within success percentage: {}", result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        log.error("Test failed due to timeout: {}", result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Test context started: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test context finished: {}", context.getName());
    }


}
