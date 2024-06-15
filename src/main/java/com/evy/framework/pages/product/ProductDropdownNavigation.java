package com.evy.framework.pages.product;

import com.evy.framework.drivers.DriverManager;
import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import com.evy.framework.constants.LogType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDropdownNavigation extends BasePage {

    public ProductListingPage selectProductFromDropdown(String mainCategory, String subCategory, String subSubCategory) {
        try {
            String mainCategoryValue = String.format("//ul[@id='ui-id-2']/li/a[normalize-space()='%s']", mainCategory);
            WebElement mainCategoryElement = DriverManager.getInstance().getDriver().findElement(By.xpath(mainCategoryValue));

            if (subCategory.isEmpty() && subSubCategory.isEmpty()) {
                click(mainCategoryElement, mainCategory);
            } else if (subSubCategory.isEmpty()) {
                String subCategoryValue = String.format("//ul[@id='ui-id-2']/li/a[normalize-space()='%s']/parent::li//ul//span[normalize-space()='%s']", mainCategory, subCategory);
                WebElement subCategoryElement = DriverManager.getInstance().getDriver().findElement(By.xpath(subCategoryValue));
                moveTo(mainCategoryElement, mainCategory);
                click(subCategoryElement, subCategory);
            } else {
                String subCategoryValue = String.format("//ul[@id='ui-id-2']/li/a[normalize-space()='%s']/parent::li//ul//span[normalize-space()='%s']", mainCategory, subCategory);
                WebElement subCategoryElement = DriverManager.getInstance().getDriver().findElement(By.xpath(subCategoryValue));
                moveTo(mainCategoryElement, mainCategory);
                moveTo(subCategoryElement, subCategory);

                String subSubCategoryValue = String.format("//ul[@id='ui-id-2']/li/a[normalize-space()='%s']/parent::li//ul//span[normalize-space()='%s']/parent::a/parent::li//ul//span[normalize-space()='%s']", mainCategory, subCategory, subSubCategory);
                WebElement subSubCategoryElement = DriverManager.getInstance().getDriver().findElement(By.xpath(subSubCategoryValue));
                click(subSubCategoryElement, subSubCategory);
            }

            waitForElementToBeVisible(DriverManager.getInstance().getDriver().findElement(By.cssSelector("div.breadcrumbs")));
            return new ProductListingPage();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Error during select product from dropdown operation: " + e.getMessage());
            throw new RuntimeException("Error during select product from dropdown operation: " + e.getMessage(), e);
        }
    }
}
