package com.evy.test;

import com.evy.framework.constants.LogType;
import com.evy.framework.data.LoginData;
import com.evy.framework.pages.HomePage;
import com.evy.framework.pages.authentication.LoginPage;
import com.evy.framework.utils.AssertionUtils;
import com.evy.framework.utils.LoggerUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.evy.framework.utils.AssertionUtils.assertEquality;
/**
 * Test class for verifying login scenarios using TestNG and Allure.
 * This class includes tests related to user login functionality,
 * with annotations for Allure reporting and parameterized testing.
 *
 * @author evy
 * @version 1.0
 */
@Feature("Login Feature")
public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = LoginData.class)
    @Description("Verify user login scenarios")
    @Story("User Login")
    @Parameters({"email","password","operation","expectedResult"})
    public void testUserLoginScenarios(String email, String password, String operation, String expectedResult) {
        String actualLoginResult = performLoginAndVerifyResponseMessage(email, password, operation);
        assertEquality(actualLoginResult, expectedResult, "Verify if (" + actualLoginResult + ") equals to (" + expectedResult + ")");
    }

    @Description("Perform login and verify response message")
    private String performLoginAndVerifyResponseMessage(String email, String password, String operation) {
        try {
            return HomePage.getInstance().getAuthenticationNavigation()
                    .navigateToLoginPage().performLogin(email, password, false, LoginPage.class)
                    .getLoginResponseMessage(operation);
        } catch (Exception e) {
            LoggerUtils.log(getClass(), LogType.ERROR,"Error during login operation: " + e.getMessage());
            throw new RuntimeException("Error during login operation: " + e.getMessage(), e);
        }
    }
}
