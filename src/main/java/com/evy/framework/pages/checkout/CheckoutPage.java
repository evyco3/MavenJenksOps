package com.evy.framework.pages.checkout;

import com.evy.framework.drivers.DriverManager;
import com.evy.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BasePage {
    @FindBy(css = "input[name='firstname']")
    private WebElement firstName;
    @FindBy(css = "input[name='lastname']")
    private WebElement lastName;
    @FindBy(css = "input[name='street[0]']")
    private WebElement address;
    @FindBy(css = "input[name='city']")
    private WebElement city;
    @FindBy(css = "input[name='postcode']")
    private WebElement zip;
    @FindBy(css = "select[name='region_id']")
    private WebElement region;
    @FindBy(css = "select[name='country_id']")
    private WebElement country;
    @FindBy(css = "input[name='telephone']")
    private WebElement phone;
    @FindBy(css = "input[value='tablerate_bestway']")
    private WebElement shipmentMethod;
    @FindBy(css = "button[data-role='opc-continue']")
    private WebElement proceedToPaymentPageBtn;

    public CheckoutPage setFirstName(String firstName){
        sendKeys(this.firstName,firstName,"firstName");
        return this;
    }
    public CheckoutPage setLastName(String lastName){
        sendKeys(this.lastName,lastName,"lastName");
        return this;
    }
    public CheckoutPage setAddress(String address){
        sendKeys(this.address,address,"address");
        return this;
    }
    public CheckoutPage setCity(String city){
        sendKeys(this.city,city,"city");
        return this;
    }
    public CheckoutPage setZipCode(String zip){
        sendKeys(this.zip,zip,"zip");
        return this;
    }
    public CheckoutPage setCountry(String country){
        selectByVisibleText(this.country,country,"country");
        return this;
    }
    public  CheckoutPage setPhone(String phone){
        sendKeys(this.phone,phone,"phone");
        return this;
    }
    public CheckoutPage setRegion(String region){
        selectByVisibleText(this.region,region,"region");
        return this;
    }
    public CheckoutPage clickShipmentMethod(){
        waitForElementToBeVisible(DriverManager.getInstance().getDriver().findElement(By.id("label_method_bestway_tablerate")));
        click(this.shipmentMethod,"shipment method");
        return this;
    }

    public PaymentPage clickProceedToPaymentPage(){
        click(this.proceedToPaymentPageBtn,"proceed to paymentPage button");
        waitForElementToBeVisible(DriverManager.getInstance().getDriver().findElement(By.xpath("//div[normalize-space()='Payment Method']")));
        return new PaymentPage();
    }




}
