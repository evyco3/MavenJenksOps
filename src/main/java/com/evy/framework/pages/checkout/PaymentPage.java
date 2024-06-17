package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage {

    @FindBy(css = "button[title='Place Order']")
    private WebElement placeOrderButton;

    public SuccessOrderPage clickPlaceOrderButton(){
        click(this.placeOrderButton,"Place order button");
        waitForPageTitleToBeEquals("Success Page");
        return new SuccessOrderPage();
    }

}
