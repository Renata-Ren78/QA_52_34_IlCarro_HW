package ui_tests;

import dto.UserLombok;
import manager.AppManager;
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

        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnYalla();
    }

    @Test
    public void loginNegativeEmptyEmailFieldTest(){
        loginPage.clickBtnYalla();
    }
}
