package com.axa.ch.selenium.pageObject.template;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.axa.ch.selenium.pageObject.StartseitePageObject;
import seleniumConsulting.ch.selenium.framework.driver.WebDriverManager;

public abstract class AbstractSeleniumConsultingPageObject extends AbstractPageObject{

    public TopMenuPageObject topMenuPageObject = new TopMenuPageObject();

    @FindBy(xpath = "//*[@id='title']//*[starts-with(name(), 'h')]")
    private WebElement title;

    public AbstractSeleniumConsultingPageObject() {
        PageFactory.initElements(WebDriverManager.getWebdriver(), this);
    }

    public String getTextOfTitle(){
        return getText(title);
    }

    public static StartseitePageObject loadSeleniumConsultingPage(){
        WebDriverManager.getWebdriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebDriverManager.getWebdriver().get("http://www.selenium-consulting.ch");
        return new StartseitePageObject();
    }
}
