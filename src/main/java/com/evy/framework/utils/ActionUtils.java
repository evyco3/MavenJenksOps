package com.evy.framework.utils;

import com.evy.framework.constants.LogType;
import io.qameta.allure.Allure;

import java.util.function.Supplier;

/**
 * Utility class for executing actions and functions with logging capabilities.
 * This class provides methods to perform actions and functions while handling exceptions and logging the results.
 */

public class ActionUtils {

    public static void execAction(Class<?> Tclass, Runnable execution, String successMessage, String errorMessage) {
        try {
            execution.run();
            logAction(Tclass, successMessage);
        } catch (Exception e) {
            logActionError(Tclass, errorMessage, e);
            throw new ElementNotFoundException(errorMessage, e);
        }
    }

    public static String execFunction(Class<?> Tclass, Supplier<String> function, String successMessage, String errorMessage) {
        try {
            String result = function.get();
            logAction(Tclass, successMessage);
            return result;
        } catch (Exception e) {
            logActionError(Tclass, errorMessage, e);
            throw new ElementNotFoundException(errorMessage, e);
        }
    }

    public static boolean execBooleanFunction(Class<?> Tclass, Supplier<Boolean> function, String successMessage, String errorMessage) {
        try {
            boolean result = function.get();
            logAction(Tclass, successMessage);
            return result;
        } catch (Exception e) {
            logActionError(Tclass, errorMessage, e);
            throw new ElementNotFoundException(errorMessage, e);
        }
    }

    private static void logAction(Class<?> Tclass, String message) {
        LoggerUtils.log(Tclass, LogType.INFO, message);
        Allure.step(message);
    }

    private static void logActionError(Class<?> Tclass, String errorMessage, Exception e) {
        LoggerUtils.log(Tclass, LogType.ERROR, errorMessage + ": " + e.getMessage());
        Allure.step(errorMessage);
    }

    public static class ElementNotFoundException extends RuntimeException {
        public ElementNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
