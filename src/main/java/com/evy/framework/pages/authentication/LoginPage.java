package com.evy.framework.pages.authentication;

import com.evy.framework.constants.LogType;
import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = "#email")
    private WebElement email;
    @FindBy(css = "#pass")
    private WebElement password;
    @FindBy(css = "#send2")
    private WebElement loginButton;
    @FindBy(css = "div[data-ui-id='message-error']>div")
    private WebElement failLoginMessage;
    @FindBy(css = ".page-header .logged-in")
    private WebElement successLoginMessage;




    public <T>T performLogin(String email,String password,boolean criteria,Class<T>nextPageClass){
        try{
            sendKeys(this.email,email,"email");
            sendKeys(this.password,password,"password");
            click(this.loginButton,"loginButton");

            if(criteria){
                waitForPageTitleToBeEquals("Home Page");
                LoggerUtils.log(getClass(),LogType.INFO,"Login operation success navigate to HomePage");
            }
            return nextPageClass.getDeclaredConstructor().newInstance();

        }catch (Exception e){
            LoggerUtils.log(getClass(), LogType.ERROR,"Failed to complete login operation");
            throw new RuntimeException("Failed to complete login operation "+"/n"+e);
        }
    }

    public String getLoginResponseMessage(String operation){
        if(operation.equalsIgnoreCase("valid login")){
            return getText(successLoginMessage,"success login message");
        }
        return getText(failLoginMessage,"fail login message");
    }
}
