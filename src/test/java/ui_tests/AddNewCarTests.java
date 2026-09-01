package ui_tests;

import dto.Car;
import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import pages.PopUpPage;

import utils.enums.Fuel;
import utils.enums.HeaderMenu;

import javax.swing.*;

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
    public void addNewCarPositiveTest() {
        Car car = positiveCar();
        System.out.println(car);
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.downloadImage("cat.jpg");
        letTheCarWorkPage.clickBtnSubmitEithJS();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("{\"city\":\"must not be blank\"}"));
        //PopUpPage popup = new PopUpPage(getDriver());
        //Assert.assertTrue(popup.isTextInPopUpMessagePresent("{\"city\":\"must not be blank\"}"));

        //popup.isTextInPopUpMessagePresent("{\"city\":\"must not be blank\"}");
        // System.out.println("Button is " + letTheCarWorkPage.isBtnSubmitEnabled());
        //Assert.assertFalse(letTheCarWorkPage.isBtnSubmitEnabled());
    }

    // HW 09.01
    @Test
    public void addNewCarNegativeOnlyClickBtnSubmitTest() {
        letTheCarWorkPage.clickBtnSubmitEithJS();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextCarAddingFailedPresents("Car adding failed"));
    }

    // HW 09.02
    @Test
    public void addNewCarNegativeClickAllFieldsAndBtnSubmitTest() {
        letTheCarWorkPage.clickAllFields();
        letTheCarWorkPage.clickBtnSubmitEithJS();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextCarAddingFailedPresents("Car adding failed"));
    }

    // HW 09.03
    @Test
    public void addNewCarNegativeLeaveOneFieldBlancAndOtherFieldsTypeWithValidDataTest() {
        Car car = Car.builder()
                .city("Tel Aviv")
                .manufacture("Toyota")
                .model("")
                .year("2020")
                .fuel(Fuel.PETROL)
                .seats(4)
                .carClass("C")
                .serialNumber("123456789")
                .pricePerDay(100.00)
                .about("Family car")
                .build();
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.clickBtnSubmitEithJS();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent
                        ("city\":\"must not be blank\",\"model\":\"must not be blank"));
    }

    // HW 09.04
    @Test
    public void addNewCarNegativeTypeWrongYearAndOtherFieldsTypeWithValidDataTest() {
        Car car = Car.builder()
                .city("Tel Aviv")
                .manufacture("Toyota")
                .model("Corolla")
                .year("abcd")
                .fuel(Fuel.PETROL)
                .seats(4)
                .carClass("C")
                .serialNumber("123456789")
                .pricePerDay(100.00)
                .about("Family car")
                .build();
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.clickBtnSubmitEithJS();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent
                        ("{\"year\":\"must not be blank\",\"city\":\"must not be blank\"}"));
    }


}
