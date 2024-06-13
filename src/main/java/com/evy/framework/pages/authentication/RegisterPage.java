package com.evy.framework.pages.authentication;

import com.evy.framework.constants.LogType;
import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {
    @FindBy(css = "")
    private WebElement firstName;
    @FindBy(css = "")
    private WebElement lastName;
    @FindBy(css = "")
    private WebElement email;
    @FindBy(css = "")
    private WebElement password;
    @FindBy(css = "")
    private WebElement confirmation;
    @FindBy(css = "")
    private WebElement registerButton;

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
        return null;
    }
}
