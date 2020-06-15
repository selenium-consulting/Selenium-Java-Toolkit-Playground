package com.axa.ch.selenium.test;

import com.axa.ch.selenium.pageObject.KontaktPageObject;
import com.axa.ch.selenium.pageObject.StartseitePageObject;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.axa.ch.selenium.pageObject.template.AbstractSeleniumConsultingPageObject;
import io.qameta.allure.Feature;
import seleniumConsulting.ch.selenium.framework.driver.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class InitialTest {

    @Test(groups = {"allTests", "initTests"}, seleniumTest = true, testName = "AntragListeTest", nlUserPath = "asfadf")
    @Feature("InitialTest other depends on this")
    public void webseiteVerfügbar(){
        WebDriverManager.getWebdriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebDriverManager.startNLTransaction("Michel");
        AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        WebDriverManager.stopNLTransaction();
        Assert.assertEquals(WebDriverManager.getWebdriver().getTitle(), "Home - Selenium-Consulting - Gerne für Sie und Ihre Zukunft da");
    }

}
