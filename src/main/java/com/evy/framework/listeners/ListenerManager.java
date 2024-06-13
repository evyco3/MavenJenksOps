package com.evy.framework.listeners;



import com.evy.framework.constants.LogType;
import com.evy.framework.drivers.DriverManager;
import com.evy.framework.utils.LoggerUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


import java.io.ByteArrayInputStream;

public class ListenerManager extends TestListenerAdapter {

    @Override
    public void onTestStart(ITestResult result) {
        Allure.description(result.getMethod().getDescription());
       LoggerUtils.log(getClass(), LogType.INFO, "Test started: " + result.getMethod().getMethodName()+ "Test Start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.step("Test passed: " + result.getMethod().getMethodName());
        LoggerUtils.log(getClass(), LogType.INFO, "Test passed: " + result.getMethod().getMethodName()+ "Test Success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.step("Test failed: " + result.getMethod().getMethodName());
        LoggerUtils.log(getClass(), LogType.ERROR, "Test failed: " + result.getMethod().getMethodName()+ "Test Failure");
        attachScreenshotToAllure();
    }

    private void attachScreenshotToAllure() {
        try {
            WebDriver driver = DriverManager.getInstance().getDriver();
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
            } else {
               LoggerUtils.log(getClass(), LogType.ERROR, "Driver does not support taking screenshots"+ "Screenshot Error");
            }
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Failed to capture screenshot: " + e.getMessage()+ "Screenshot Error");
        }
    }
}