package ch.seleniumconsulting.selenium.pageObject;

import ch.seleniumconsulting.selenium.pageObject.template.AbstractSeleniumConsultingPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
