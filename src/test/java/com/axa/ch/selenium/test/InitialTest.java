package com.axa.ch.selenium.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.axa.ch.selenium.pageObject.template.AbstractSeleniumConsultingPageObject;
import io.qameta.allure.Feature;
import seleniumConsulting.ch.selenium.framework.driver.WebDriverManager;

public class InitialTest {

    @Test(groups = {"allTests", "initTests"}, seleniumTest = true, testName = "AntragListeTest")
    @Feature("InitialTest other depends on this")
    public void webseiteVerfügbar(){
        AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        Assert.assertEquals(WebDriverManager.getWebdriver().getTitle(), "Home - Selenium-Consulting - Gerne für Sie und Ihre Zukunft da");
    }

}
