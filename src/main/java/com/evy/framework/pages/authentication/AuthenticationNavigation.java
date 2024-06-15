package com.evy.framework.pages.authentication;

import com.evy.framework.constants.LogType;
import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthenticationNavigation extends BasePage {
    @FindBy(xpath = "//header[@class='page-header']//a[normalize-space()='Sign In']")
    private WebElement loginLink;
    @FindBy(xpath = "//header[@class='page-header']//a[normalize-space()='Create an Account']")
    private WebElement registerLink;


    private void navigateAndLog(WebElement element,String elementName,String pageTitle,String pageClassName){
      try{
          click(element,elementName);
          waitForPageTitleToBeEquals(pageTitle);
          LoggerUtils.log(getClass(), LogType.INFO,"Navigate to "+pageClassName);
      }catch (Exception e){
          LoggerUtils.log(getClass(),LogType.ERROR,"Failed to navigate to "+pageClassName+"\n"+e);
          throw new RuntimeException("Navigation to "+pageClassName+" Failed");
      }
    }

    public LoginPage navigateToLoginPage(){
        navigateAndLog(loginLink,"login link","Customer Login","LoginPage");
        return new LoginPage();
    }

    public RegistrationPage navigateToRegisterPage(){
        navigateAndLog(registerLink,"register link","Create New Customer Account","RegisterPage");
        return new RegistrationPage();
    }




}
