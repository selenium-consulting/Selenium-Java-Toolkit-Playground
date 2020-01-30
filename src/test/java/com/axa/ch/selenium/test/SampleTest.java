package com.axa.ch.selenium.test;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import seleniumConsulting.ch.selenium.framework.driver.WebDriverManager;

public class SampleTest {

    @Test(groups = {"allTests"}, seleniumTest = true)
    @Ignore
    public void sample() {
        WebDriverManager.getWebdriver().get("http://www.google.ch");
    }
}
