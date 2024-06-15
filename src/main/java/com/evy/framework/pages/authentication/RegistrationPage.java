package com.evy.framework.pages.authentication;

import com.evy.framework.constants.LogType;
import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
    @FindBy(css = "#firstname")
    private WebElement firstName;
    @FindBy(css = "#lastname")
    private WebElement lastName;
    @FindBy(css = "#email_address")
    private WebElement email;
    @FindBy(css = "#password")
    private WebElement password;
    @FindBy(css = "#password-confirmation")
    private WebElement confirmation;
    @FindBy(css = "button[title='Create an Account']")
    private WebElement registerButton;
    @FindBy(css = "div[data-ui-id='message-success']>div")
    private WebElement validRegistrationMsg;
    @FindBy(css = "#email_address-error")
    private WebElement invalidEmailFormatMsg;
    @FindBy(css = "div[data-ui-id='message-error']>div")
    private WebElement invalidEmailInUsedMessage;
    @FindBy(css = "#password-error")
    private WebElement invalidPasswordFormatMsg;
    @FindBy(css = "#password-confirmation-error")
    private WebElement invalidPasswordMissMatchMsg;

    public <T>T performRegistration(String firstName,String lastName,String email,String password,String confirmation,boolean criteria,Class<T>nextPageClass){
        try{
            sendKeys(this.firstName,firstName,"firstName");
            sendKeys(this.lastName,lastName,"lastName");
            sendKeys(this.email,email,"email");
            sendKeys(this.password,password,"password");
            sendKeys(this.confirmation,confirmation,"confirmation");
            click(this.registerButton,"registrationButton");
            if(criteria){
                LoggerUtils.log(getClass(),LogType.INFO,"Registration operation success navigate to MyAccountPage");
                waitForPageTitleToBeEquals("My Account");
            }
            return nextPageClass.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            LoggerUtils.log(getClass(), LogType.ERROR,"Failed to complete registration operation");
            throw new RuntimeException("Failed to complete registration operation ",e);
        }

    }

    public String getRegistrationResponseMessage(String operation){
        return switch (operation){
            case "valid registration"->getText(validRegistrationMsg,"valid registration message");
            case "invalid email format"->getText(invalidEmailFormatMsg,"invalid email format message");
            case "invalid email in use"->getText(invalidEmailInUsedMessage,"invalid email in used message");
            case "invalid password format"->getText(invalidPasswordFormatMsg,"invalid password format message");
            case "invalid password missMatch"->getText(invalidPasswordMissMatchMsg,"invalid password miss match message");
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }
}
