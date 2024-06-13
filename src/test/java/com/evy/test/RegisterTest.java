package com.evy.test;

import com.evy.framework.data.RegistrationData;
import com.evy.framework.pages.HomePage;
import com.evy.framework.pages.authentication.RegisterPage;
import com.evy.framework.utils.AssertionUtils;
import com.evy.framework.utils.LoggerUtils;
import com.evy.framework.constants.LogType;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.evy.framework.utils.AssertionUtils.assertEquality;

/**
 * Test class for verifying user registration scenarios using TestNG and Allure.
 * This class includes tests related to user registration functionality,
 * with annotations for Allure reporting and parameterized testing.
 *
 * @author evy
 * @version 1.0
 */
@Feature("Registration Feature")
public class RegisterTest extends BaseTest {


    @Test(dataProvider = "registrationData", dataProviderClass = RegistrationData.class)
    @Description("Verify user registration scenarios")
    @Story("User Registration")
    @Parameters({"firstName", "lastName", "email", "password", "confirmation", "operation", "expectedResult"})
    public void testUserRegistrationScenarios(String firstName, String lastName, String email, String password, String confirmation, String operation, String expectedResult) {
        String actualResult = performRegistrationAndGetResponseMessage(firstName, lastName, email, password, confirmation, operation);
        assertEquality(actualResult, expectedResult, "Verify if (" + actualResult + ") is equal to (" + expectedResult + ")");

    }


    @Description("Perform registration and verify response message")
    private String performRegistrationAndGetResponseMessage(String firstName, String lastName, String email, String password, String confirmation, String operation) {
        try {
            return HomePage.getInstance()
                    .getAuthenticationNavigation()
                    .navigateToRegisterPage()
                    .performRegistration(firstName, lastName, email, password, confirmation, false, RegisterPage.class)
                    .getRegistrationResponseMessage(operation);
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR, "Error during registration operation: " + e.getMessage());
            throw new RuntimeException("Error during registration operation: " + e.getMessage(), e);
        }
    }
}
