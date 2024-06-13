package com.evy.framework.drivers;

import com.evy.framework.constants.BrowserType;
import com.evy.framework.constants.LogType;
import com.evy.framework.utils.LoggerUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.EnumMap;
import java.util.function.Supplier;

/**
 * This class provides a factory for creating WebDriver instances based on the specified browser type.
 * It uses a map of browser types to corresponding WebDriver suppliers for dynamic driver creation.
 */
public final class DriverFactory {

    private static final EnumMap<BrowserType, Supplier<WebDriver>> driverMap = new EnumMap<>(BrowserType.class);

    private DriverFactory() {}

    static {
        driverMap.put(BrowserType.CHROME, DriverFactory::getChromeDriver);
        driverMap.put(BrowserType.FIREFOX, DriverFactory::getFirefoxDriver);
        driverMap.put(BrowserType.EDGE, DriverFactory::getEdgeDriver);
        driverMap.put(BrowserType.SAFARI, DriverFactory::getSafariDriver);
        driverMap.put(BrowserType.EXPLORER, DriverFactory::getExplorerDriver);
        driverMap.put(BrowserType.OPERA, DriverFactory::getOperaDriver);
    }


    static WebDriver getDriver(BrowserType browserType) {
        try {
            Supplier<WebDriver> supplier = driverMap.get(browserType);
            if (supplier == null) {
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
            }
            return supplier.get();
        } catch (Exception e) {
            LoggerUtils.log(DriverManager.class,LogType.ERROR,"Failed to initialize WebDriver for browser type: " + browserType+"\n"+e);
            throw new RuntimeException("Failed to initialize WebDriver for browser type: " + browserType, e);
        }
    }

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static WebDriver getEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    private static WebDriver getExplorerDriver() {
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver();
    }

    private static WebDriver getSafariDriver() {
        WebDriverManager.safaridriver().setup();
        return new SafariDriver();
    }

    private static WebDriver getOperaDriver() {
        WebDriverManager.operadriver().setup();
        return new ChromeDriver();
    }
}
