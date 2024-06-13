package com.evy.test;


import com.evy.framework.drivers.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    @BeforeMethod
    public void setup(){
        DriverManager.getInstance().initDriver();
    }

    @AfterMethod
    public void tearDown(){
        DriverManager.getInstance().quitDriver();
    }
}
