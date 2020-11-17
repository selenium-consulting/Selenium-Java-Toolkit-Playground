package ch.seleniumconsulting.selenium.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import ch.seleniumconsulting.selenium.pageObject.template.AbstractSeleniumConsultingPageObject;
import io.qameta.allure.Feature;
import seleniumConsulting.ch.selenium.framework.driver.WebDriverManager;
import seleniumConsulting.ch.selenium.framework.screenshot.core.Capture;
import seleniumConsulting.ch.selenium.framework.screenshot.core.ShootPage;

import java.util.concurrent.TimeUnit;

public class InitialTest {

    @Test(groups = {"allTests", "initTests"}, seleniumTest = true, testName = "AntragListeTest")
    @Feature("InitialTest other depends on this")
    public void webseiteVerfügbar(){
        WebDriverManager.getWebdriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        Assert.assertEquals(WebDriverManager.getWebdriver().getTitle(), "Home - Selenium-Consulting - Gerne für Sie und Ihre Zukunft da");
    }

}
