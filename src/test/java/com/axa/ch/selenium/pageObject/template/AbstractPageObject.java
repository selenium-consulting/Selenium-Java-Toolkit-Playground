package com.axa.ch.selenium.pageObject.template;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class AbstractPageObject {

    protected void click(WebElement element){
        element.click();
    }

    protected void enter(WebElement element, CharSequence... input){
        element.sendKeys(input);
    }

    protected void enterRepalace(WebElement element, CharSequence input){
        enter(element, Keys.chord(Keys.CONTROL, "a") , input);
    }

    protected String getValueOfInput(WebElement element){
        return element.getAttribute("value");
    }

    protected void selectByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    protected void selectByLabel(WebElement element, String label) {
        new Select(element).selectByVisibleText(label);
    }

    protected void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    protected String getText(WebElement element){
        return element.getText();
    }


}
