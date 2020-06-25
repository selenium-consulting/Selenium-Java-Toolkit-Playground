package ch.seleniumconsulting.selenium.pageObject;

import java.util.List;

import ch.seleniumconsulting.selenium.pageObject.template.AbstractSeleniumConsultingPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KontaktPageObject extends AbstractSeleniumConsultingPageObject {

    @FindBy(name = "name")
    private WebElement name;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "phone")
    private WebElement phone;

    @FindBy(xpath = "//textarea[contains(@class, 'SE_nachricht_input')]")
    private WebElement nachricht;

    @FindBy(xpath = "//input[contains(@class, 'SE_absenden_button')]")
    private WebElement absenden;

    @FindBy(xpath = "//*[contains(@class, 'nf-error-msg ')]")
    private List<WebElement> fehlermeldungList;

    public void enterName(CharSequence input) {
        enterRepalace(name, input);
    }

    public String getValueOfName(){
        return getValueOfInput(name);
    }

    public void enterEmail(CharSequence input) {
        enterRepalace(email, input);
    }

    public String getValueOfEmail(){
        return getValueOfInput(email);
    }

    public void enterPhone(CharSequence input) {
        enterRepalace(phone, input);
    }

    public String getValueOfPhone(){
        return getValueOfInput(phone);
    }

    public void enterNachricht(CharSequence input) {
        enterRepalace(nachricht, input);
    }

    public String getValueOfNachricht(){
        return getValueOfInput(nachricht);
    }

    public void clickAbsenden(){
        click(absenden);
    }

    public String getTextOfFehlermeldungList(int index){
        return getText(fehlermeldungList.get(index));
    }

    public int getSizeOfFehlermeldungList(){
        return fehlermeldungList.size();
    }

}
