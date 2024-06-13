package com.evy.framework.utils;


import com.evy.framework.constants.LogType;
import io.qameta.allure.Allure;
/**
 * Utility class for performing assertions with logging capabilities.
 * This class provides methods to assert conditions, equality, and string containment,
 * with logging of success or failure messages.
 */

public class AssertionUtils {

    public static void assertCondition(boolean condition, String description) {
        try {
            assert condition;
            logSuccess(description);
        } catch (AssertionError e) {
            logFailure(description);
            throw e;
        }
    }

    public static void assertEquality(Object actual, Object expected, String description) {
        try {
            assert actual.equals(expected);
            logSuccess(description);
        } catch (AssertionError e) {
            logFailure(description);
            throw e;
        }
    }

    public static void assertContains(String actual, String expected, String description) {
        try {
            assert actual.contains(expected);
            logSuccess(description);
        } catch (AssertionError e) {
            logFailure(description);
            throw e;
        }
    }

    private static void logSuccess(String description) {
        Allure.description(description + ": Passed");
        LoggerUtils.log(AssertionUtils.class, LogType.INFO,description+": passed");
    }

    private static void logFailure(String description) {
        Allure.description(description + ": Failed");
        LoggerUtils.log(AssertionUtils.class, LogType.INFO,description+": Failed");
    }
}