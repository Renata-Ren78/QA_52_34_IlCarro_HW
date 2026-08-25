package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import static utils.PropertiesReader.*;


public class LetTheCarWorkTests extends AppManager {
    LetTheCarWorkPage letTheCarWorkPage;


    @BeforeMethod
    public void precondition(){
        new HomePage(getDriver()).clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties","email"))
                .password(getProperty("base.properties","password"))
                .build();
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnYalla();
        letTheCarWorkPage = new HomePage(getDriver())
                .clickLinkletTheCarWork();
    }

    @Test
    public void verifyRegisteredUserCanOpenLetTheCarWorkPageTest(){
        Assert.assertTrue(letTheCarWorkPage.isTitleLetTheCarWorkDisplayed());
    }

}
