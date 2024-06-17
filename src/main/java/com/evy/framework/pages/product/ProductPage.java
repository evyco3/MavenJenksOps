package com.evy.framework.pages.product;

import com.evy.framework.drivers.DriverManager;
import com.evy.framework.pages.BasePage;
import com.evy.framework.pages.checkout.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    @FindBy(css = "#qty")
    private WebElement productQuantity;
    @FindBy(css = "#product-addtocart-button")
    private WebElement addToCartBtn;
    @FindBy(css = "div[data-ui-id='message-success']>div")
    private WebElement successAddToCartMsg;
   @FindBy(xpath = "//a[normalize-space()='shopping cart']")
   private WebElement proceedToCartPage;

    public ProductPage setProductSize(String size){
        String value=String.format("div[id*='option-label-size'][aria-label='%s']",size.toUpperCase());
        click(DriverManager.getInstance().getDriver().findElement(By.cssSelector(value)),size );
        return this;
    }

    public ProductPage setProductColor(String color){
        String value=String.format("div[id*='option-label-color'][aria-label='%s']",color);
        click(DriverManager.getInstance().getDriver().findElement(By.cssSelector(value)),color );
        return this;
    }

    public ProductPage setProductQuantity(String quantity){
        sendKeys(this.productQuantity,quantity,"product Quantity");
        return this;
    }

    public ProductPage clickAddToCartButton(){
        click(this.addToCartBtn,"ad to cart button");
        waitForElementToBeVisible(DriverManager.getInstance().getDriver().findElement(By.cssSelector("div[data-ui-id]>div")));
        return this;
    }

    public boolean getAddToCartSuccessMsg(){
       return isDisplayed(this.successAddToCartMsg,"success add to art message");
    }

    public CartPage clickProceedToCartPage(){
        click(this.proceedToCartPage,"proceed to cartPage Button");
        waitForPageTitleToBeEquals("Shopping Cart");
        return new CartPage();
    }

}
