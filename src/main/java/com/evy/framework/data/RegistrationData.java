package com.evy.framework.data;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public final class RegistrationData {

    private static final Faker faker = new Faker();


    @DataProvider(name = "registrationData")
    public static Object[][]getData(){
        String password=getPassword();
        return new Object[][]{
                {getFirstName(),getLastName(),getEmail(),password,password,"valid registration","Thank you for registering with Main Website Store."},
                {getFirstName(),getLastName(),"@email.com",password,password,"invalid email format","Please enter a valid email address (Ex: johndoe@domain.com)."},
                {getFirstName(),getLastName(),"evy@user.co.il",password,password,"invalid email in use","There is already an account with this email address."},
                {getFirstName(),getLastName(),getEmail(),"password","password","invalid password format","Minimum of different classes of characters in password is 3."},
                {getFirstName(),getLastName(),getEmail(),getPassword(),password,"invalid password missMatch","Please enter the same value again."}
        };
    }



    private static String getFirstName() {
        return faker.name().firstName();
    }

    private static String getLastName() {
        return faker.name().lastName();
    }

    private static String getEmail() {
        return faker.internet().emailAddress();
    }

    private static String getPassword() {
        return faker.internet().password(8, 14, true, true, true);
    }
}
