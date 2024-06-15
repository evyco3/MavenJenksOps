package com.evy.framework.pages;

import com.evy.framework.pages.authentication.AuthenticationNavigation;
import com.evy.framework.pages.product.ProductDropdownNavigation;

public class HomePage extends BasePage{

    public static HomePage getInstance(){
        return new HomePage();
    }

    public AuthenticationNavigation getAuthenticationNavigation(){
        return new AuthenticationNavigation();
    }

    public ProductDropdownNavigation getProductDropdownNavigation(){
        return new ProductDropdownNavigation();
    }
}
