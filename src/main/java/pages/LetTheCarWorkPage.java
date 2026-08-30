package pages;

import dto.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.enums.Fuel;

import java.io.File;

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
    WebElement inputLocation;
    @FindBy(xpath = "//input[@id='make']")
    WebElement inputMake;
    @FindBy(xpath = "//input[@id='model']")
    WebElement inputModel;
    @FindBy(xpath = "//input[@id='year']")
    WebElement inputYear;
    @FindBy(xpath = "//select[@id='fuel']")
    WebElement inputFuel;
    @FindBy(xpath = "//input[@id='seats']")
    WebElement inputSeats;
    @FindBy(xpath = "//input[@id='class']")
    WebElement inputCarClass;
    @FindBy(xpath = "//input[@id='serialNumber']")
    WebElement inputSerialNumber;
    @FindBy(xpath = "//input[@id='price']")
    WebElement inputPrice;
    @FindBy(xpath = "//textarea[@id='about']")
    WebElement inputAbout;
    @FindBy(id = "photos")
    WebElement inputImage;


    public void typeAddNewCarForm(Car car){
        inputLocation.sendKeys(car.getCity());
        inputMake.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        chooseFuel(car.getFuel());

        inputSeats.sendKeys(car.getSeats().toString());
        //inputSeats.sendKeys(String.valueOf(car.getSeats())); - variant
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        inputPrice.sendKeys(car.getPricePerDay().toString());
        inputAbout.sendKeys(car.getAbout());




    }

    public void downloadImage(String fileName){
        inputImage.sendKeys(new File("src/test/resources/"
                +fileName).getAbsolutePath());
    }

    private void chooseFuel(Fuel fuel){
        inputFuel.click();
        driver.findElement(By.xpath(fuel.getLocator())).click();
    }


    public boolean isBtnSubmitEnabled(){
        return btnSubmit.isEnabled();
    }


    public void clickPickUpPlace(){
        inputLocation.click();
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
        inputLocation.clear();
        inputLocation.sendKeys(location);
    }
}
