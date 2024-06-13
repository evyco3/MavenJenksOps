package com.evy.framework.data;

import org.testng.annotations.DataProvider;

public final class LoginData {

    private LoginData(){}

    @DataProvider(name = "loginData")
    public static Object[][]getData(){
        return new Object[][]{
                {"evy@user.co.il","Password123","valid login","Welcome, evy user!"},
                {"evy@wrongEmail.co.il","Password123","invalid login","The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."},
                {"evy@user.co.il","wrongPassword","invalid login","The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."}
        };
    }
}
