package ch.seleniumconsulting.selenium.test.screenshot;

import ch.seleniumconsulting.selenium.pageObject.StartseitePageObject;
import ch.seleniumconsulting.selenium.pageObject.template.AbstractSeleniumConsultingPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumConsulting.ch.selenium.framework.dataLoader.TestDataProvider;
import seleniumConsulting.ch.selenium.framework.driver.WebDriverManager;
import seleniumConsulting.ch.selenium.framework.screenshot.core.Capture;
import seleniumConsulting.ch.selenium.framework.screenshot.core.ScreenShootMaker;
import seleniumConsulting.ch.selenium.framework.screenshot.utils.image.UnableToCompareImagesException;

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

        try {
            Assert.assertFalse(ScreenShootMaker.page.shoot(Capture.FULL_SCROLL, 1500).equalsWithDiff("screenshots/screenshotScrollPageDiffFailed1_REV.png", "screenshots/ScreenshotTest_ScreenshotScrollPageDiff1False")
                    , "Screens" +
                            "hot should be same, but is not");
            Assert.assertFalse(true, "Dimension Missmatch Exception was not thrown.");
        } catch (UnableToCompareImagesException unableToCompareImagesException){
            Assert.assertTrue(true, "Dimension Missmatch Exception was thrown.");
        }
    }

    @Test(groups = {"allTests"}, seleniumTest = true)
    public void screenshotScrollPageDiffFailed() {
        WebDriverManager.getWebdriver().get(TestDataProvider.getTestData("startUrl"));
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        startseitePageObject.topMenuPageObject.clickUeberSelenium();

        Assert.assertFalse(ScreenShootMaker.page.shoot(Capture.FULL_SCROLL, 1500).equalsWithDiff("screenshots/screenshotScrollPageDiffFailed2_REV.png", "screenshots/ScreenshotTest_ScreenshotScrollPageDiff2False")
                , "Screenshot should be same, but is not");

        File screenshotFile = new File("screenshots/ScreenshotTest_ScreenshotScrollPageDiff2False.png");
        Assert.assertTrue(screenshotFile.exists(), "File should exist");
    }

    @Test(groups = {"allTests"}, seleniumTest = true)
    public void screenshotElementDiffSuccess() {
        WebDriverManager.getWebdriver().get(TestDataProvider.getTestData("startUrl"));
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        Assert.assertTrue(ScreenShootMaker.element.shoot(startseitePageObject.dienstleistungenButton).equalsWithDiff("screenshots/screenshotElementDiffSuccess_REV.png", "screenshots/ScreenshotTest_ScreenshotElementDiffSuccess")
                , "Screenshot should be same, but is not");

        File screenshotFile = new File("screenshots/ScreenshotTest_ScreenshotElementDiffSuccess.png");
        Assert.assertFalse(screenshotFile.exists(), "File should not exist");
    }
}
