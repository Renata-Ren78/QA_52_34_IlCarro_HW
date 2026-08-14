package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import static utils.PropertiesReader.*;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();


    @BeforeMethod
    public void goToLoginPage(){
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }




    @Test
    public void loginPositiveTest(){
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties",
                        "email"))
                .password(getProperty("base.properties",
                        "password"))
                .build();
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isPopUpSeccessLoginDisplayed());


//        Assert.assertTrue(new LoginPage(getDriver())
//                .validateTextInMessageEmailIsRequired("Email is required"));
    }

    @Test
    public void loginNegativeEmptyEmailFieldsTest(){
        loginPage.clickBtnYalla();

    }
    @Test
    public void loginNegativeWrongEmailTest(){
        UserLombok user = UserLombok.builder()
                .username("enatae_test_new@gmail.com")
                .password(getProperty("base.properties",
                        "password"))
                .build();

        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isPopUpLoginFailedDisplayed());
    }

    @Test
    public void loginNegativeWrongPasswordTest(){
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties",
                        "email"))
                .password("ren_CER$1233")
                .build();

        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isPopUpLoginFailedDisplayed());
    }

    @Test
    public void loginNegativeEmptyAllFieldsWithoutClickInFieldsTest(){
        loginPage.clickBtnYalla();
        Assert.assertFalse(loginPage.isBtnYallaEnabled());
    }

    @Test
    public void loginNegativeEmptyAllFieldsWithClickInFieldsTest(){
        UserLombok user = UserLombok.builder()
                .username("")
                .password("")
                .build();
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaEnabled(),
                "validate isBtnYallaEnabled");
        System.out.println("test working");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"),
                "validate message: Email is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"),
                "validate message: Password is required");
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeEmptyEmailFieldWithClickOnItTest(){
        UserLombok user = UserLombok.builder()
                .username("")
                .password(getProperty("base.properties",
                        "password"))
                .build();

        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaEnabled(),
                "validate isBtnYallaEnabled");
        System.out.println("test working");
    }

    @Test
    public void loginNegativeEmptyPasswordFieldWithClickOnItTest(){
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties",
                        "email"))
                .password("")
                .build();

        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaEnabled(),
                "validate isBtnYallaEnabled");
        System.out.println("test working");
    }




}
