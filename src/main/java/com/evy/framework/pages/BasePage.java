package com.evy.framework.pages;


import com.evy.framework.constants.LogType;
import com.evy.framework.drivers.DriverManager;
import com.evy.framework.utils.ActionUtils;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BasePage {

    private final WebDriver driver;

    public BasePage(){
        driver= DriverManager.getInstance().getDriver();
        PageFactory.initElements(driver,this);
    }

    protected void sendKeys(WebElement element, String value, String elementName) {
        ActionUtils.execAction(getClass(), () -> {
                    waitForElementToBeVisible(element).clear();
                    waitForElementToBeVisible(element).sendKeys(value);
                },
                "Send keys to " + elementName + ": " + value,
                "Failed to send keys to " + elementName
        );
    }

    protected void click(WebElement element, String elementName) {
        ActionUtils.execAction(getClass(), () -> {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }, "Click on " + elementName, "Failed to click on " + elementName);
    }

    protected String getText(WebElement element,String elementName){
        return ActionUtils.execFunction(getClass(),()->
           waitForElementToBeVisible(element).getText(),
           "Retrieve text from "+elementName+":"+element.getText(),
            "Failed to trouvere text from "+elementName
        );
    }

    protected boolean isDisplayed(WebElement element,String elementName){
        return ActionUtils.execBooleanFunction(getClass(), element::isDisplayed,
                elementName+ " Displayed status "+true,
                elementName+" Displayed status is "+false
                );
    }

    protected void waitForPageTitleToBeEquals(String title){
        ActionUtils.execAction(getClass(),()->{
            new WebDriverWait(DriverManager.getInstance().getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.titleIs(title));
        },"title is "+true,title+" is not matched to the current title"+DriverManager.getInstance().getDriver().getTitle());
    }

    protected void moveTo(WebElement element, String elementName) {
        ActionUtils.execAction(getClass(), () -> {
            new Actions(driver).moveToElement(element).perform();
        }, "Move to " + elementName, "Failed to move to " + elementName);
    }

    public String getCurrentURL(){
        return ActionUtils.execFunction(getClass(), driver::getCurrentUrl,
                    "Success Retrieve Url",
                    "Failed to retrieve url"
        );
    }

    protected String getAttribute(WebElement element, String attributeName, String elementName) {
        return ActionUtils.execFunction(getClass(), () ->
                        waitForElementToBeVisible(element).getAttribute(attributeName),
                "Retrieve attribute '" + attributeName + "' from " + elementName,
                "Failed to retrieve attribute '" + attributeName + "' from " + elementName
        );
    }

    protected void selectByVisibleText(WebElement element, String value, String elementName) {
        ActionUtils.execAction(getClass(), () -> {
            Select select = new Select(element);
            select.selectByVisibleText(value);
        }, "Select '" + value + "' in " + elementName, "Failed to select '" + value + "' in " + elementName);
    }


    protected WebElement waitForElementToBeVisible(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(DriverManager.getInstance().getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class);
        return wait.until(driver -> {
            return ExpectedConditions.visibilityOf(element).apply(driver);
        });
    }




}
