package com.axa.ch.selenium.pageObject.template;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumConsulting.ch.selenium.framework.driver.WebDriverManager;

public class TopMenuPageObject extends AbstractPageObject{

    @FindBy(xpath = "//*[@data-seleniumid='Startseite']")
    private WebElement startseite;

    @FindBy(xpath = "//*[@data-seleniumid='Dienstleistung']")
    private WebElement dienstleistung;

    @FindBy(xpath = "//*[@data-seleniumid='Ueber_Selenium']")
    private WebElement ueberSelenium;

    @FindBy(xpath = "//*[@data-seleniumid='Ueber_Uns']")
    private WebElement ueberUns;

    @FindBy(xpath = "//*[@data-seleniumid='Selenium-Java-Toolkit']")
    private WebElement seleniumJavaToolkit;

    @FindBy(xpath = "//*[@data-seleniumid='Kontakt']")
    private WebElement kontakt;

    @FindBy(xpath = "//*[@data-seleniumid='Blog']")
    private WebElement blog;

    public TopMenuPageObject() {
        //Thank the WebDriverManager should the Consttructor always Looks same
        PageFactory.initElements(WebDriverManager.getWebdriver(), this);
    }

    public void clickStartseite(){
        click(startseite);
    }

    public void clickDienstleistung(){
        click(dienstleistung);
    }

    public void clickUeberSelenium(){
        click(ueberSelenium);
    }

    public void clickUeberUns(){
        click(ueberUns);
    }

    public void clickSeleniumJavaToolkit(){
        click(seleniumJavaToolkit);
    }

    public void clickKontakt(){
        click(kontakt);
    }

    public void clickBlog(){
        click(blog);
    }

}
