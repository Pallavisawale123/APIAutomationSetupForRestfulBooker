package com.automation.restfulbooker.helper;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author psawale
 * @project restfulbooker
 * @date 4/25/2025
 */

public class RetryListener implements IAnnotationTransformer {
    @Override
    public void transform(final ITestAnnotation annotation, final Class testClass, final Constructor testConstructor,
                          final Method testMethod) {
        annotation.setRetryAnalyzer(FailRetry.class);
    }
}