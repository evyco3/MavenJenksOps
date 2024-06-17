package com.evy.test;

import com.evy.framework.constants.LogType;
import com.evy.framework.pages.HomePage;
import com.evy.framework.utils.AssertionUtils;
import com.evy.framework.utils.LoggerUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.evy.framework.utils.AssertionUtils.assertCondition;

/**
 * Test class for verifying product addition to cart scenarios using TestNG and Allure.
 * This class includes tests related to adding products to the cart functionality,
 * with annotations for Allure reporting and parameterized testing.
 *
 * @version 1.0
 */
@Feature("Product Cart Feature")
public class ProductPageTest extends BaseTest {


    @Test(dataProvider = "productData")
    @Description("Verify user can add product to cart")
    @Story("Add Product to Cart")
    public void testUserAddProductToCart(String category, String subcategory, String productType,
                                         String productName, String size, String color,
                                         String quantity, boolean expectedResponse) {
        boolean actualResponseMessage = performAddProductToCartAndGetResponseMessage(category, subcategory, productType, productName, size, color, quantity);
        assertCondition(actualResponseMessage == expectedResponse,
                "Verify if success add to cart message is displayed");
    }

    @Description("Perform add product to cart and verify response message")
    private boolean performAddProductToCartAndGetResponseMessage(String category, String subcategory, String productType,
                                                                 String productName, String size, String color,
                                                                 String quantity) {
        try {
            return HomePage.getInstance().getProductDropdownNavigation()
                    .selectProductFromDropdown(category, subcategory, productType)
                    .selectProductByName(productName)
                    .setProductSize(size).setProductColor(color).setProductQuantity(quantity)
                    .clickAddToCartButton().getAddToCartSuccessMsg();
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Error during add product to cart operation: " + e.getMessage());
            throw new RuntimeException("Error during add product to cart operation: " + e.getMessage(), e);
        }
    }
    @DataProvider(name = "productData")
    public Object[][] productData() {
        return new Object[][] {
                {"Men", "Tops", "Jackets", "Proteus Fitness Jackshirt", "M", "Black", "4", true}

        };
    }
}
