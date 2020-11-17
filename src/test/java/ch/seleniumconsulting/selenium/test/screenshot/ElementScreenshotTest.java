package ch.seleniumconsulting.selenium.test.screenshot;

import ch.seleniumconsulting.selenium.pageObject.StartseitePageObject;
import ch.seleniumconsulting.selenium.pageObject.template.AbstractSeleniumConsultingPageObject;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumConsulting.ch.selenium.framework.dataLoader.TestDataProvider;
import seleniumConsulting.ch.selenium.framework.driver.WebDriverManager;
import seleniumConsulting.ch.selenium.framework.screenshot.core.Capture;
import seleniumConsulting.ch.selenium.framework.screenshot.core.ScreenShootMaker;

import java.io.File;

public class ElementScreenshotTest {

    @Test(groups = {"allTests", "screenshot"}, seleniumTest = true)
    public void screenshot() {
        WebDriverManager.getWebdriver().get(TestDataProvider.getTestData("startUrl"));
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();

        ScreenShootMaker.element.shoot(startseitePageObject.dienstleistungenButton).withName("ScreenshotTest_ScreenshotElement").save();

        File screenshotFile = new File("screenshots/ScreenshotTest_ScreenshotElement.png");
        Assert.assertTrue(screenshotFile.exists(), "File should exist");
    }
}
