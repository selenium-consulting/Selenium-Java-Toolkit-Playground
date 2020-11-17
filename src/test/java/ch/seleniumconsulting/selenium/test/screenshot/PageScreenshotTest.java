package ch.seleniumconsulting.selenium.test.screenshot;

import ch.seleniumconsulting.selenium.pageObject.StartseitePageObject;
import ch.seleniumconsulting.selenium.pageObject.template.AbstractSeleniumConsultingPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumConsulting.ch.selenium.framework.dataLoader.TestDataProvider;
import seleniumConsulting.ch.selenium.framework.driver.WebDriverManager;
import seleniumConsulting.ch.selenium.framework.screenshot.core.Capture;
import seleniumConsulting.ch.selenium.framework.screenshot.core.ScreenShootMaker;

public class PageScreenshotTest {

    @Test(groups = {"allTests", "screenshot"}, seleniumTest = true)
    public void screenshotScroll() {
        WebDriverManager.getWebdriver().get(TestDataProvider.getTestData("startUrl"));
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        startseitePageObject.topMenuPageObject.clickUeberUns();

        ScreenShootMaker.page.shoot(Capture.FULL_SCROLL, 1500).withName("ScreenshotTest_ScreenshotScrollPage").save();
    }
}
