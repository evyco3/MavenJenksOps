package com.evy.framework.utils;

import com.evy.framework.constants.LogType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.EnumMap;
import java.util.Map;

public class LoggerUtils {

    private static final Map<LogType, Logger> loggers = new EnumMap<>(LogType.class);

    static {
        loggers.put(LogType.INFO, LogManager.getLogger(LogType.INFO.toString()));
        loggers.put(LogType.ERROR, LogManager.getLogger(LogType.ERROR.toString()));
        loggers.put(LogType.WARNING, LogManager.getLogger(LogType.WARNING.toString()));
        loggers.put(LogType.DEBUG, LogManager.getLogger(LogType.DEBUG.toString()));
    }

    public static void log(Class<?> pageClass, LogType logType, String message) {
        loggers.get(logType).log(Levels.get(logType), formatLogMessage(pageClass, message));
    }

    private static String formatLogMessage(Class<?> pageClass, String message) {
        return String.format("%s - %s", pageClass.getSimpleName(), message);
    }

    private static class Levels {
        static org.apache.logging.log4j.Level get(LogType logType) {
            return switch (logType) {
                case INFO -> org.apache.logging.log4j.Level.INFO;
                case ERROR -> org.apache.logging.log4j.Level.ERROR;
                case WARNING -> org.apache.logging.log4j.Level.WARN;
                case DEBUG -> org.apache.logging.log4j.Level.DEBUG;
            };
        }
    }
}
