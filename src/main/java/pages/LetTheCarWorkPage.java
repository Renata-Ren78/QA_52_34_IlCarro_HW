package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LetTheCarWorkPage extends BasePage{
    public LetTheCarWorkPage (WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;
    @FindBy(xpath = "//a[@href='/let-car-work']")
    WebElement linkLetTheCarWork;
    //@FindBy(xpath = "//h1[text()='Let the car work']")
    @FindBy(xpath = "//h1[contains(text(), 'Let the car work')]")
    WebElement titleLetTheCarWork;

    @FindBy(xpath = "//input[@id='pickUpPlace']")
    WebElement inputPickUpPlace;
    @FindBy(xpath = "//input[@id='make']")
    WebElement inputMake;
    @FindBy(xpath = "//input[@id='model']")
    WebElement inputModel;
    @FindBy(xpath = "//input[@id='year']")
    WebElement inputYear;
    @FindBy(xpath = "//input[@id='fuel']")
    WebElement inputFuel;
    @FindBy(xpath = "//input[@id='fuel']")
    WebElement inputSeats;
    @FindBy(xpath = "//input[@id='class']")
    WebElement inputClass;
    @FindBy(xpath = "//input[@id='serialNumber']")
    WebElement inputserialNumber;
    @FindBy(xpath = "//input[@id='price']")
    WebElement inputPrice;
    @FindBy(xpath = "//textarea[@id='about']")
    WebElement inputTextArea;



    public void clickPickUpPlace(){
        inputPickUpPlace.click();
    }

    public boolean isTitleLetTheCarWorkDisplayed(){
        return titleLetTheCarWork.isDisplayed();
    }

    public void clickLinkLetTheCarWork(){
        linkLetTheCarWork.click();
    }

    public void clickBtnSubmitEithJS(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')");
        btnSubmit.click();
    }

    public void setInputPickUpPlace(String location)
    {
        inputPickUpPlace.clear();
        inputPickUpPlace.sendKeys(location);
    }
}
