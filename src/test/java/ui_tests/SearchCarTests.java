package ui_tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;

import java.time.LocalDate;

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


}



