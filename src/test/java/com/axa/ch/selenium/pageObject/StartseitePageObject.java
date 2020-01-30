package com.axa.ch.selenium.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.axa.ch.selenium.pageObject.template.AbstractSeleniumConsultingPageObject;

public class StartseitePageObject extends AbstractSeleniumConsultingPageObject {


    @FindBy(id = "dienstleistungen_button")
    private WebElement dienstleistungenButton;

    @FindBy(id = "ueberUns_button")
    private WebElement ueberUnsButton;

    public void clickDienstleistungenButton(){
        click(dienstleistungenButton);
    }

    public void clickUeberUnsButton(){
        click(ueberUnsButton);
    }

}
