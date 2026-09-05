package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import utils.TestNGListener;
import static utils.PropertiesReader.*;

@Listeners(TestNGListener.class)

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

        System.out.println("Url is : ->>> "+ getDriver().getCurrentUrl());
        //System.out.println("Title is : ->>> " + letTheCarWorkPage);
        //System.out.println("Title is : ->>> " + letTheCarWorkPage.isTitleLetTheCarWorkDisplayed());
        //Assert.assertTrue(letTheCarWorkPage.isTitleLetTheCarWorkDisplayed());
    }
    @Test
    public void inputPickUpPlace(){
        letTheCarWorkPage.clickPickUpPlace();
        letTheCarWorkPage.setInputPickUpPlace("Riga");
    }

}
