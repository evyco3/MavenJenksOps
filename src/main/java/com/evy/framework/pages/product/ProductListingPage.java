package com.evy.framework.pages.product;

import com.evy.framework.constants.LogType;
import com.evy.framework.drivers.DriverManager;
import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductListingPage extends BasePage {

    public ProductPage selectProductByName(String productName){
        try{
            String productNameValue=String.format("//a[@class='product-item-link'][normalize-space()='%s']",productName);
            WebElement productNameElement= DriverManager.getInstance().getDriver().findElement(By.xpath(productNameValue));
            click(productNameElement,productName);
            waitForElementToBeVisible(DriverManager.getInstance().getDriver().findElement(By.cssSelector("span[itemprop='name']")));
            return new ProductPage();
        }
        catch (Exception e){
            LoggerUtils.log(getClass(), LogType.ERROR,"Error During selecting product from product pool result");
            throw new RuntimeException("Error During selecting product from product pool result",e);
        }
    }
}
