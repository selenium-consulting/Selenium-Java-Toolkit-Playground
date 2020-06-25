package ch.seleniumconsulting.selenium.test;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import seleniumConsulting.ch.selenium.framework.dataLoader.TestDataProvider;
import seleniumConsulting.ch.selenium.framework.driver.WebDriverManager;

public class SampleTest {

    @Test(groups = {"allTests"}, seleniumTest = true)
    public void sample() {
        WebDriverManager.getWebdriver().get(TestDataProvider.getTestData("startUrl"));
        System.out.println(TestDataProvider.getTestData("startUrl"));
    }
}
