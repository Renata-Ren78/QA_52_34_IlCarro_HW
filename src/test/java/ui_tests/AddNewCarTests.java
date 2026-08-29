package ui_tests;

import dto.Car;
import dto.UserLombok;
import manager.AppManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import pages.PopUpPage;

import utils.enums.HeaderMenu;

import static utils.PropertiesReader.getProperty;
import static utils.CarFactory.*;

public class AddNewCarTests extends AppManager {

    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;


    @BeforeMethod
    public void goToLoginPage() {
        //new HomePage(getDriver()).clickBtnLogin();
        //loginPage = new LoginPage(getDriver());
        loginPage = new HomePage(getDriver())
                .clickHeaderButtons(HeaderMenu.LOGIN);

        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties",
                        "email"))
                .password(getProperty("base.properties",
                        "password"))
                .build();
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnYalla();
        new PopUpPage(getDriver()).clickBtnOk();
        letTheCarWorkPage = new HomePage(getDriver())
                .clickHeaderButtons(HeaderMenu.LET_THE_CAR_WORK);

    }

    @Test
    public void addNewCarPositiveTest(){
        Car car = positiveCar();
        System.out.println(car);
        letTheCarWorkPage.typeAddNewCarForm(car);

    }




}
