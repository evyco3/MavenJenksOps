package com.evy.framework.data;

import org.testng.annotations.DataProvider;

public class ProductListingData {

    @DataProvider(name = "productListingData")
    public static Object[][] getData() {
        return new Object[][]{
                {"Proteus Fitness Jackshirt", "proteus-fitness-jackshirt.html"},
                {"Montana Wind Jacket", "montana-wind-jacket.html"},
                {"Jupiter All-Weather Trainer", "jupiter-all-weather-trainer.html"},
                {"Typhon Performance Fleece-lined Jacket", "typhon-performance-fleece-lined-jacket.html"}
        };
    }
}
