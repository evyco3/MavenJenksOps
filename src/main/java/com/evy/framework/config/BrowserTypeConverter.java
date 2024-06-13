package com.evy.framework.config;

import com.evy.framework.constants.BrowserType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

/**
 * This class is a custom converter that transforms a string representation
 * of a browser type into a BrowserType enum.
 */
public final class BrowserTypeConverter implements Converter<BrowserType> {
    @Override
    public BrowserType convert(Method method, String browserType) {
        return BrowserType.valueOf(browserType.toUpperCase());
    }
}
