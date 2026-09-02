package ui_tests;

import data_provider.UserDataProvider;
import dto.UserLombok;
import manager.AppManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;
import utils.TestNGListener;
import utils.UserFactory;


import static utils.UserFactory.*;
@Listeners(TestNGListener.class)

public final class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;


    @BeforeMethod
    public void goToRegistrationPage() {
        logger.info("Start registration test");
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        UserLombok user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxIAgree();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in"));
    }

    @Test
    public void registrationPositiveWithJSTest() {
        UserLombok user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckboxTermsOfUse();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in"));
    }

    @Test
    public void registrationPositiveWithActionsTest() {
        UserLombok user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in"));
    }

    @Test(dataProvider = "dataProviderForRegistrationWrongPasswordOrEmail",
            dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongPasswordTest(UserLombok user) {
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain 1 uppercase letter, " +
                "1 lowercase letter, 1 number and one special symbol of [@$#^&*!"));

    }


}
