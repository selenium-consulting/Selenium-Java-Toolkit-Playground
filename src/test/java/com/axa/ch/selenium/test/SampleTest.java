package com.axa.ch.selenium.test;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import seleniumConsulting.ch.selenium.framework.dataLoader.TestDataProvider;
import seleniumConsulting.ch.selenium.framework.driver.WebDriverManager;

public class SampleTest {

    @Test(groups = {"allTests"}, seleniumTest = true)
    public void sample() {
        WebDriverManager.getWebdriver().get("http://www.google.ch");
        System.out.println(TestDataProvider.getTestData("startUrl"));
    }
}
