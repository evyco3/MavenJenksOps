package com.evy.framework.data;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public final class RegistrationData {

    private static final Faker faker = new Faker();


    @DataProvider(name = "registrationData")
    public static Object[][]getData(){
        String password=getPassword();
        return new Object[][]{
                {getFirstName(),getLastName(),getEmail(),password,password,"valid registration",""}
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
