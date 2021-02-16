package ch.seleniumconsulting.selenium.test;

import ch.seleniumconsulting.selenium.helper.ExcelAdapter;
import ch.seleniumconsulting.selenium.pageObject.KontaktPageObject;
import ch.seleniumconsulting.selenium.pageObject.StartseitePageObject;
import ch.seleniumconsulting.selenium.pageObject.template.AbstractSeleniumConsultingPageObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import io.qameta.allure.Feature;

public class DataProviderTest_Kontakt {

    @Test(groups = {"allTests", "dependencyTests", "dataProvider"}, seleniumTest = true, dataProvider = "kontaktProvider")
    @Feature("DataProvider")
    public void kontaktWithStandartDataproviderParallel(String name
                                               ,String email
                                               ,String phone
                                               ,String nachricht
                                               ,String ersteFehlermessage
                                               ,int anzFehler){
        testeKontakt(name, email, phone, nachricht, ersteFehlermessage, anzFehler);
    }

    @Test(groups = {"allTests", "dependencyTests", "dataProvider"}, seleniumTest = true, dataProvider = "kontaktProviderFromExcel")
    @Feature("DataProvider")
    public void kontaktWithExcelDataproviderNotParallel(String name
                                               ,String email
                                               ,String phone
                                               ,String nachricht
                                               ,String ersteFehlermessage
                                               ,int anzFehler){
        testeKontakt(name, email, phone, nachricht, ersteFehlermessage, anzFehler);
    }

    private void testeKontakt(String name
            ,String email
            ,String phone
            ,String nachricht
            ,String ersteFehlermessage
            ,int anzFehler){
        StartseitePageObject startseitePageObject = AbstractSeleniumConsultingPageObject.loadSeleniumConsultingPage();
        Assert.assertEquals(startseitePageObject.getTextOfTitle(),"Wir automatisieren Browser mit Liebe und Leidenschaft.");

        startseitePageObject.topMenuPageObject.clickKontakt();
        KontaktPageObject kontaktPageObject = new KontaktPageObject();
        Assert.assertEquals(kontaktPageObject.getTextOfTitle(),"In Kontakt kommen");

        if(Strings.isNotNullAndNotEmpty(name)){
            kontaktPageObject.enterName(name);
        }
        if(Strings.isNotNullAndNotEmpty(email)){
            kontaktPageObject.enterEmail(email);
        }
        if(Strings.isNotNullAndNotEmpty(phone)){
            kontaktPageObject.enterPhone(phone);
        }
        if(Strings.isNotNullAndNotEmpty(nachricht)){
            kontaktPageObject.enterNachricht(nachricht);
        }
        kontaktPageObject.clickAbsenden();
        Assert.assertEquals(kontaktPageObject.getTextOfFehlermeldungList(0), ersteFehlermessage, "Erste Fehlermeldung");
        Assert.assertEquals(kontaktPageObject.getSizeOfFehlermeldungList(), anzFehler, "Erwartete Fehlermeldungen");
    }

    @DataProvider(parallel = false)
    public Object[][] kontaktProvider() {
        return new Object[][]{
                {"Michel", "no Vailid", "052 000 00 00", "Message", "Bitte geben Sie eine gültige E-Mail-Adresse ein.", 2},
                {"", "info@selenium-consulting.ch", "", "Message", "Dies ist ein Pflichtfeld.", 2},
                {"", "", "", "", "Dies ist ein Pflichtfeld.", 4},
        };
    }

    @DataProvider(parallel = false)
    public Object[][] kontaktProviderFromExcel() throws Exception {
        Object[][] testObjArray = ExcelAdapter.getTableArray("testdata/kontaktanfragen.xlsx","Daten");
        return (testObjArray);
    }

}
