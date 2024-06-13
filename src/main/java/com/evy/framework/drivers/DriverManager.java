package com.evy.framework.drivers;

import com.evy.framework.config.ConfigReader;
import com.evy.framework.constants.BrowserType;
import com.evy.framework.constants.LogType;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.time.Duration;

/**
 * This class manages the WebDriver instances using a ThreadLocal to ensure thread safety.
 * It provides methods to initialize, quit, and retrieve the WebDriver.
 */
public final class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreads = new ThreadLocal<>();
    private static DriverManager instance;

    private DriverManager() {
    }

    /**
     * Returns the singleton instance of DriverManager.
     *
     * @return The singleton instance of DriverManager.
     */
    public static synchronized DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    /**
     * Initializes the WebDriver based on the browser type specified in the configuration.
     * Configures the WebDriver with timeouts and navigates to the base URL.
     */
    public void initDriver() {
        try {
            BrowserType browserType = ConfigReader.get().browserType();
            WebDriver driver = DriverFactory.getDriver(browserType);
            driverThreads.set(driver);
            configureDriver(driver);
            LoggerUtils.log(DriverManager.class,LogType.INFO,"WebDriver initialized successfully for browser type: " + browserType);
        } catch (Exception e) {
            LoggerUtils.log(DriverManager.class, LogType.ERROR, "Failed to initialize WebDriver: " + e.getMessage());
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }

    /**
     * Quits the WebDriver and removes it from the ThreadLocal storage.
     */
    public void quitDriver() {
        try {
            WebDriver driver = driverThreads.get();
            if (driver != null) {
                driver.quit();
                driverThreads.remove();
            }
        } catch (WebDriverException e) {
            LoggerUtils.log(DriverFactory.class, LogType.ERROR, "Failed to quit WebDriver: " + e.getMessage());
            throw new RuntimeException("Failed to quit WebDriver: " + e.getMessage(), e);
        }
    }

    /**
     * Returns the WebDriver instance for the current thread.
     *
     * @return The WebDriver instance.
     */
    public WebDriver getDriver() {
        return driverThreads.get();
    }

    /**
     * Configures the WebDriver with window size, timeouts, and base URL.
     *
     * @param driver The WebDriver instance to configure.
     */
    private void configureDriver(WebDriver driver) {
        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.get().pageLoadTime()));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.get().implicitTime()));
            driver.get(ConfigReader.get().baseUrl());
        } catch (WebDriverException e) {
            throw new RuntimeException("Failed to configure WebDriver: " + e.getMessage(), e);
        }
    }
}