package com.evy.test;

import com.evy.framework.constants.LogType;
import com.evy.framework.pages.HomePage;
import com.evy.framework.utils.AssertionUtils;
import com.evy.framework.utils.LoggerUtils;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

/**
 * This class contains end-to-end test scenarios for user actions on the e-commerce website.
 * It includes steps from user registration to product purchase and order confirmation.
 */
@Epic("E-Commerce Platform")
@Feature("User Journey")
public class EndToEndTest extends BaseTest {

    private final Faker faker = new Faker();

    /**
     * Test case to verify the end-to-end process of user registration, product selection,
     * and order placement on the e-commerce platform.
     */
    @Test
    @Description("Verifies the end-to-end process of user registration, product selection, and order placement on the e-commerce platform.")
    @Story("Demonstrate EndToEnd Scenario")
    public void testUserPerformEnd2End() {
        LoggerUtils.log(getClass(), LogType.INFO, "Starting end-to-end test scenario.");
        String actualResponseMessage = performEndToEnd();
        AssertionUtils.assertEquality(actualResponseMessage, "Thank you for your purchase!",
                "Verify that (" + actualResponseMessage + ") equals to (" + "Thank you for your purchase!)");
    }

    /**
     * Executes the end-to-end user flow including registration, product selection, and order placement.
     *
     * @return the success message after placing the order
     */
    @Description("Executes the steps from user registration to product purchase and order confirmation.")
    private String performEndToEnd() {
        try {
            // Generate random user details using Faker
            String password = faker.internet().password(8, 14, true, true, true);

            return HomePage.getInstance()
                    // Step 1: Navigate to the registration page
                    .getAuthenticationNavigation()
                    .navigateToRegisterPage()

                    // Step 2: Perform user registration
                    .performRegistration(
                            faker.name().firstName(),
                            faker.name().lastName(),
                            faker.internet().emailAddress(),
                            password,
                            password,
                            true,
                            HomePage.class
                    )

                    // Step 3: Navigate to the product category using the dropdown
                    .getProductDropdownNavigation()
                    .selectProductFromDropdown("Men", "Tops", "Jackets")

                    // Step 4: Select a specific product by name
                    .selectProductByName("Proteus Fitness Jackshirt")

                    // Step 5: Set product specifications and add to cart
                    .setProductSize("M")
                    .setProductColor("Black")
                    .setProductQuantity("1")
                    .clickAddToCartButton()

                    // Step 6: Proceed to the cart page
                    .clickProceedToCartPage()

                    // Step 7: Proceed to the checkout page
                    .clickProceedToCheckoutPage()

                    // Step 8: Fill in the shipping details
                    .setFirstName(faker.name().firstName())
                    .setLastName(faker.name().lastName())
                    .setAddress(faker.address().fullAddress())
                    .setCity(faker.address().city())
                    .setZipCode("12345-6789")
                    .setRegion("Maryland")
                    .setPhone(faker.phoneNumber().phoneNumber())

                    // Step 9: Select the shipment method and proceed to payment
                    .clickShipmentMethod()
                    .clickProceedToPaymentPage()

                    // Step 10: Place the order
                    .clickPlaceOrderButton()
                    .getSuccessOrderMessage();

        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "End-to-end process failed due to an unexpected exception: " + e.getMessage());
            throw new RuntimeException("Failure During EndToEnd Test " + e.getMessage());
        }
    }
}
