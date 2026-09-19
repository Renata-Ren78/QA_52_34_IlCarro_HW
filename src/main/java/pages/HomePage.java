package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

import static utils.PropertiesReader.*;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        //driver.get("https://ilcarro.web.app/search");
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//a[text()=' Log in ']")
    WebElement btnLogin;
    @FindBy(xpath = "//a[text()=' Sign up ']")
    WebElement btnSignup;
    @FindBy(xpath = "//a[@href='/let-car-work']")
    WebElement linkLetTheCarWork;

    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//div[text()='Dates are required']")
    WebElement requiredMessageDatesAreRequired;
    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnYearOneCalendar;


    public LetTheCarWorkPage clickLinkletTheCarWork() {
        linkLetTheCarWork.click();
        return new LetTheCarWorkPage(driver);
    }

    public void clickBtnLogin() {
        btnLogin.click();
    }

    public void clickBtnSignUp() {
        btnSignup.click();
    }

    public void typeSearchForm(String city, LocalDate startDate,
                               LocalDate endDate) {
        inputCity.sendKeys(city);
        if (startDate != null && endDate != null) {
            System.out.println(startDate);
            System.out.println(endDate);
            // 2026-09-04    9/4/206 - 9/10/2026
            System.out.println(startDate.getMonthValue());
            System.out.println(startDate.getDayOfMonth());
            String dates = startDate.getMonthValue() + "/"
                    + startDate.getDayOfMonth() + "/"
                    + startDate.getYear() + " - "
                    + endDate.getMonthValue() + "/"
                    + endDate.getDayOfMonth() + "/"
                    + endDate.getYear();
            System.out.println(dates);
            inputDates.sendKeys(dates);
        } else {
            inputDates.sendKeys("");
        }
    }


    public void typeSearchFormWithCalendar(String city, LocalDate startDate,
                                           LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.click();
        typeCalendar(startDate);
        typeCalendar(endDate);
    }


    private void typeCalendar(LocalDate date) {
        btnYearOneCalendar.click();
        //td[@aria-label='2026']
        String year = Integer.toString(date.getYear());
        WebElement btnYear = driver.findElement
                (By.xpath("//td[@aria-label='" + year + "']"));
        btnYear.click();
        //td[@aria-label="November 2026"]   //  "//td[@aria-label='"+month+" "+year+"']"
        System.out.println(date.getMonth());
        String month = createMonth(date.getMonth().toString());
        System.out.println(month);
        WebElement btnMonth = driver.findElement
                (By.xpath("//td[@aria-label='" + month + " " + year + "']"));
        btnMonth.click();
        // //td[@aria-label="September 11, 2026"]
        System.out.println(date.getDayOfMonth());
        String day = String.valueOf(date.getDayOfMonth());
        WebElement btnDay = driver.findElement
                (By.xpath("//td[@aria-label='" + month + " " + day + ", " + year + "']"));
        btnDay.click();
    }

    public void pressEscape(){
        inputDates.sendKeys(Keys.ESCAPE);
    }

    // SEPTEMBER --> September
    private String createMonth(String month) {
        return new StringBuilder().append(month.substring(0, 1)
                .toUpperCase()).append(month.substring(1)
                .toLowerCase()).toString();
    }


    public void typeSearchFormNew(String city, String dates) {
        inputCity.sendKeys(city);
        inputDates.sendKeys(dates);
    }


    public void clickBtnYalla() {
        btnYalla.click();
    }

    public void clickBtnSubmitEithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')");
        btnYalla.click();
    }


}
