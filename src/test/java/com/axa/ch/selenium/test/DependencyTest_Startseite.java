package com.axa.ch.selenium.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.axa.ch.selenium.pageObject.DienstleistungPageObject;
import com.axa.ch.selenium.pageObject.StartseitePageObject;
import com.axa.ch.selenium.pageObject.UeberUnsPageObject;
import com.axa.ch.selenium.pageObject.template.AbstractSeleniumConsultingPageObject;
import io.qameta.allure.Feature;

public class DependencyTest_Startseite {


    @Test(groups = {"allTests", "dependencyTests"}, seleniumTest = true)
    @Feature("DependencyTest")
    public void buttonDienstleistungenFunktioniert(){
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        Assert.assertEquals(startseitePageObject.getTextOfTitle(),"Wir automatisieren Browser mit Liebe und Leidenschaft.");

        startseitePageObject.clickDienstleistungenButton();
        DienstleistungPageObject dienstleistungPageObject = new DienstleistungPageObject();
        Assert.assertEquals(dienstleistungPageObject.getTextOfTitle(),"Mit Unserem Know-How Ans Ziel Kommen");
    }

    @Test(groups = {"allTests", "dependencyTests"}, seleniumTest = true, dependsOnMethods = "buttonDienstleistungenFunktioniert")
    @Feature("DependencyTest")
    public void buttonUeberUnsFunktioniert(){
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        Assert.assertEquals(startseitePageObject.getTextOfTitle(),"Wir automatisieren Browser mit Liebe und Leidenschaft.");

        startseitePageObject.clickUeberUnsButton();
        UeberUnsPageObject ueberUnsPageObject = new UeberUnsPageObject();
        Assert.assertEquals(ueberUnsPageObject.getTextOfTitle(),"Über Uns");
    }

    @Test(groups = {"allTests", "dependencyTests"}, seleniumTest = true)//, dependsOnMethods = "buttonUeberUnsFunktioniert")
    @Feature("DependencyTest")
    public void failed(){
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        Assert.assertEquals(startseitePageObject.getTextOfTitle(),"Selenium-Consulting", "Vergleich der Failed");
    }

    @Test(groups = {"allTests", "dependencyTests"}, seleniumTest = true, dependsOnMethods = "failed")
    @Feature("DependencyTest")
    public void skippedBecauseDepends(){
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        Assert.assertEquals(startseitePageObject.getTextOfTitle(),"Wir automatisieren Browser mit Liebe und Leidenschaft.");
    }

}
