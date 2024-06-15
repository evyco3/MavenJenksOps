package com.evy.test;

import com.evy.framework.constants.LogType;
import com.evy.framework.data.ProductDropdownData;
import com.evy.framework.pages.HomePage;
import com.evy.framework.utils.LoggerUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.evy.framework.utils.AssertionUtils.assertContains;

/**
 * Test class for verifying product selection from dropdown scenarios using TestNG and Allure.
 * This class includes tests related to product navigation functionality,
 * with annotations for Allure reporting and parameterized testing.
 *
 * @version 1.0
 */
@Feature("Product Dropdown Selection Feature")
public class ProductDropdownTest extends BaseTest {

    @Test(dataProvider = "productDropdownData", dataProviderClass = ProductDropdownData.class)
    @Description("Verify product selection scenarios from dropdown")
    @Story("Product Selection from Dropdown")
    @Parameters({"mainCategory", "subCategory", "subSubCategory", "expectedUrl"})
    public void testUserSelectProductScenarios(String mainCategory, String subCategory, String subSubCategory, String expectedUrl) {
        String actualUrl = selectProductFromDropdownAndVerifyCurrentUrl(mainCategory, subCategory, subSubCategory);
        assertContains(actualUrl, expectedUrl, "Verify if (" + actualUrl + ") contains (" + expectedUrl + ")");
    }

    @Description("Perform product selection from dropdown and verify current URL")
    private String selectProductFromDropdownAndVerifyCurrentUrl(String mainCategory, String subCategory, String subSubCategory) {
        try {
            return HomePage.getInstance()
                    .getProductDropdownNavigation()
                    .selectProductFromDropdown(mainCategory, subCategory, subSubCategory)
                    .getCurrentURL();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Error during select product from dropdown operation: " + e.getMessage());
            throw new RuntimeException("Error during select product from dropdown operation: " + e.getMessage(), e);
        }
    }
}
