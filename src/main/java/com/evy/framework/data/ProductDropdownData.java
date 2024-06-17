package com.evy.framework.data;

import org.testng.annotations.DataProvider;

public class ProductDropdownData {


    @DataProvider(name = "productDropdownData")
    public Object[][]getData(){
        return new Object[][]{
                {"Men","Tops","Jackets","jackets-men.html"},
                {"Men","Tops","Hoodies & Sweatshirts","hoodies-and-sweatshirts-men.html"},
                {"Men","Tops","Tees","tees-men.html"},
                {"Men","Tops","Tanks","tanks-men.html"},
                {"Men","Bottoms","Pants","pants-men.html"},
                {"Men","Bottoms","Shorts","shorts-men.html"},



        };
    }
}
