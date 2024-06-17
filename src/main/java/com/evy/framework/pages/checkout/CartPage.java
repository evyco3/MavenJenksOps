package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    @FindBy(css = "button[data-role='proceed-to-checkout']")
    private WebElement proceedToCheckoutPage;


    public CheckoutPage clickProceedToCheckoutPage(){
        click(this.proceedToCheckoutPage,"proceed to checkoutPage");
        waitForPageTitleToBeEquals("Checkout");
        return new CheckoutPage();
    }
}
