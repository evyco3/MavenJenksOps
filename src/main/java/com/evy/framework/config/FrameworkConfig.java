package com.evy.framework.config;

import com.evy.framework.constants.BrowserType;
import org.aeonbits.owner.Config;

/**
 * This interface defines the configuration settings for the framework.
 * It uses the Owner library to map properties from a config file to Java methods.
 */
@Config.Sources("file:${user.dir}/src/main/resources/config.properties")
public interface FrameworkConfig extends Config {

    @DefaultValue("CHROME")
    @Key("browserType")
    @ConverterClass(BrowserTypeConverter.class)
    BrowserType browserType();

    @Key("implicitTime")
    int implicitTime();

    @Key("pageLoadTime")
    int pageLoadTime();

    @Key("baseUrl")
    String baseUrl();

    @Key("email")
    String email();

    @Key("password")
    String password();
}
