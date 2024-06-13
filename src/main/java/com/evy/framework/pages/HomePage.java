package com.evy.framework.pages;

import com.evy.framework.pages.authentication.AuthenticationNavigation;

public class HomePage extends BasePage{

    public static HomePage getInstance(){
        return new HomePage();
    }

    public AuthenticationNavigation getAuthenticationNavigation(){
        return new AuthenticationNavigation();
    }
}
