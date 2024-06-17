package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SuccessOrderPage extends BasePage {
    @FindBy(css = "span[data-ui-id='page-title-wrapper']")
    private WebElement successOrderMessage;


    public String getSuccessOrderMessage(){
        return getText(this.successOrderMessage,"success order message");
    }
}
