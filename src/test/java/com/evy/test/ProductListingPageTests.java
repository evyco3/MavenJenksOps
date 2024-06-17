package com.evy.test;

import com.evy.framework.data.ProductListingData;
import com.evy.framework.pages.HomePage;
import com.evy.framework.utils.LoggerUtils;
import com.evy.framework.constants.LogType;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.evy.framework.utils.AssertionUtils.assertContains;

/**
 * Test class for verifying product selection from product listing page using TestNG and Allure.
 * This class includes tests related to product selection functionality,
 * with annotations for Allure reporting and parameterized testing.
 */
@Feature("Product Listing Feature")
public class ProductListingPageTests extends BaseTest {

    @Test(dataProvider = "productListingData", dataProviderClass = ProductListingData.class)
    @Description("Verify product selection from listing page")
    @Story("Product Selection")
    @Parameters({"productName", "expectedUrl"})
    public void testUserSelectProductFromListingProducts(String productName, String expectedUrl) {
        String actualUrl = selectProductFromListingProductAndVerifyCurrentUrl(productName);
        assertContains(actualUrl, expectedUrl, "Verify if (" + actualUrl + ") contains (" + expectedUrl + ")");
    }

    @Description("Select product from listing and verify current URL")
    private String selectProductFromListingProductAndVerifyCurrentUrl(String productName) {
        try {
            return HomePage.getInstance().getProductDropdownNavigation()
                    .selectProductFromDropdown("Men", "Tops", "Jackets")
                    .selectProductByName(productName).getCurrentURL();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Error during product selection: " + e.getMessage());
            throw new RuntimeException("Error during product selection: " + e.getMessage(), e);
        }
    }
}
