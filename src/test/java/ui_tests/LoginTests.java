package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends AppManager {
    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage(){
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }


    @Test
    public void loginPositiveTest(){
        UserLombok user = UserLombok.builder()
                .username("renatae_test_new@gmail.com")
                .password("ren_CER$123")
                .build();

        LoginPage loginPage= new LoginPage(getDriver());
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnYalla();

//        Assert.assertTrue(new LoginPage(getDriver())
//                .validateTextInMessageEmailIsRequired("Email is required"));
    }

    @Test
    public void loginNegativeEmptyEmailFieldTest(){
        loginPage.clickBtnYalla();

    }
}
