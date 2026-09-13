package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;

import java.time.LocalDate;

import static java.lang.Thread.sleep;

public class SearchCarTests  extends AppManager {
    HomePage homePage;

    @BeforeMethod
    public void openHomePage(){
        homePage = new HomePage(getDriver());
    }


    @Test
    public void searchCarPositiveTest(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchForm(city,startDate,endDate);
        homePage.clickBtnSubmitEithJS();
        Assert.assertTrue(homePage.isUrlContainsText("results"));
    }

    @Test
    public void searchCarWithCalendarPositiveTest(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchFormWithCalendar(city,startDate,endDate);
        homePage.clickBtnSubmitEithJS();
        Assert.assertTrue(homePage.isUrlContainsText("results"));
    }

    @Test
    public void searchCarNegativeSameStartDatesTest(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        homePage.typeSearchForm(city,startDate,endDate);
        homePage.clickBtnSubmitEithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent
                ("You can't book car for less than a day"));
    }


    @Test
    public void searchCarNegativeMoreOneYearTest(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now()
                .plusYears(1).plusDays(1);
        homePage.typeSearchForm(city,startDate,endDate);
        homePage.clickBtnSubmitEithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent
                ("You can't pick date after one year"));
    }





    // HW 11.01
    @Test
    public void searchCarPositiveNewTest(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchForm(city,startDate,endDate);
        homePage.clickBtnSubmitEithJS();
        Assert.assertTrue(new SearchResultPage(getDriver())
                .validateTextInSearchResultPage("No available cars in"));
    }

    // HW 11.02
    @Test
    public void searchCarNegativeTest() {
        String city = "Haifa";
        homePage.typeSearchForm(city, null, null);
        homePage.clickBtnSubmitEithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent("Dates are required"));
    }

    // HW 11.03
    @Test
    public void searchCarInvalidDateNegativeTest(){
        String city ="Beersheva";
        String dates = "123";
        homePage.typeSearchFormNew(city,dates);
        homePage.clickBtnSubmitEithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent("Dates are required"));
    }


    // HW 12.01
    @Test
    public void searchNegativeSameDatesWithCalendarTest1(){
        String city = "Haifa";

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        homePage.clickBtnSubmitEithJS();

        Assert.assertTrue(homePage.isTextInErrorPresent
                ("You can't book car for less than a day"));
    }

    // HW 12.02
    @Test
    public void searchNegativeSameDatesWithCalendarTest2(){
        String city = "Haifa";

        LocalDate startDate = LocalDate.now().plusDays(5);
        LocalDate endDate = LocalDate.now().plusDays(2);

        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        homePage.clickBtnSubmitEithJS();



    }


}




// try {
//sleep(1000000);
//        } catch (InterruptedException e) {
//        throw new RuntimeException(e);
//        }


