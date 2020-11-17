package ch.seleniumconsulting.selenium.test.screenshot;

import ch.seleniumconsulting.selenium.pageObject.StartseitePageObject;
import ch.seleniumconsulting.selenium.pageObject.template.AbstractSeleniumConsultingPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumConsulting.ch.selenium.framework.dataLoader.TestDataProvider;
import seleniumConsulting.ch.selenium.framework.driver.WebDriverManager;
import seleniumConsulting.ch.selenium.framework.screenshot.core.Capture;
import seleniumConsulting.ch.selenium.framework.screenshot.core.ScreenShootMaker;

import java.io.File;

public class DiffScreenshotTest {

    @Test(groups = {"allTests"}, seleniumTest = true)
    public void screenshotScrollDiffSuccess() {
        WebDriverManager.getWebdriver().get(TestDataProvider.getTestData("startUrl"));
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        startseitePageObject.topMenuPageObject.clickKontakt();

        Assert.assertTrue(ScreenShootMaker.page.shoot(Capture.FULL_SCROLL, 1500).equalsWithDiff("screenshots/screenshotScrollDiffSuccess_REV.png", "screenshots/ScreenshotTest_ScreenshotScrollPageDiffSuccess")
                , "Screenshot should be same, but is not");

    }

    @Test(groups = {"allTests"}, seleniumTest = true)
    public void screenshotScrollDiffWithIgnoredElementsSuccess() {
        WebDriverManager.getWebdriver().get(TestDataProvider.getTestData("startUrl"));
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();

        Assert.assertTrue(
                ScreenShootMaker.page.shoot()
                        .cutOut(startseitePageObject.dienstleistungenButton,
                                startseitePageObject.ueberUnsButton)
                        .equalsWithDiff("screenshots/screenshotDiffWithIgnoredElementsSuccess_REV.png", "screenshots/screenshotScrollDiffWithIgnoredElementsSuccess")
                , "Screenshot should be same, but is not");

        File screenshotFile = new File("screenshots/screenshotScrollDiffWithIgnoredElementsSuccess.png");
        Assert.assertFalse(screenshotFile.exists(), "File should not exist");
    }

    @Test(groups = {"allTests"}, seleniumTest = true)
    public void screenshotScrollDiffFailedDimensionMissmatch() {
        WebDriverManager.getWebdriver().get(TestDataProvider.getTestData("startUrl"));
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        startseitePageObject.topMenuPageObject.clickUeberSelenium();

        Assert.assertFalse(ScreenShootMaker.page.shoot(Capture.FULL_SCROLL, 1500).equalsWithDiff("screenshots/screenshotScrollPageDiffFailed_REV.png", "screenshots/ScreenshotTest_ScreenshotScrollPageDiffFalse")
                , "Screens" +
                        "hot should be same, but is not");

        File screenshotFile = new File("screenshots/ScreenshotTest_ScreenshotScrollPageDiffFalse.png");
        Assert.assertFalse(screenshotFile.exists(), "File should not exist, because Dimensiot missmatch");
    }

    @Test(groups = {"allTests"}, seleniumTest = true)
    public void screenshotScrollPageDiffFailed() {
        WebDriverManager.getWebdriver().get(TestDataProvider.getTestData("startUrl"));
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        startseitePageObject.topMenuPageObject.clickUeberSelenium();

        Assert.assertFalse(ScreenShootMaker.page.shoot(Capture.FULL_SCROLL, 1500).equalsWithDiff("screenshots/screenshotScrollPageDiffFailed_REV.png", "screenshots/ScreenshotTest_ScreenshotScrollPageDiffFalse")
                , "Screenshot should be same, but is not");

        File screenshotFile = new File("screenshots/ScreenshotTest_ScreenshotScrollPageDiffFalse.png");
        Assert.assertTrue(screenshotFile.exists(), "File should exist");
    }

    @Test(groups = {"allTests"}, seleniumTest = true)
    public void screenshotElementDiffSuccess() {
        WebDriverManager.getWebdriver().get(TestDataProvider.getTestData("startUrl"));
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        Assert.assertTrue(ScreenShootMaker.element.shoot(startseitePageObject.dienstleistungenButton).equalsWithDiff("screenshots/screenshotElementDiffSuccess_REV.png", "screenshots/ScreenshotTest_ScreenshotElementDiffSuccess")
                , "Screenshot should be same, but is not");

        File screenshotFile = new File("screenshots/ScreenshotTest_ScreenshotScrollPageDiffFalse.png");
        Assert.assertFalse(screenshotFile.exists(), "File should not exist");
    }
}
