package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchResultPage extends BasePage {

    public SearchResultPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//h3[contains(text(),'No available cars in')]")
    WebElement messageNoAvailableCarsIn;



    public boolean validateTextInSearchResultPage(String text) {
        return isTextInElementPresent(messageNoAvailableCarsIn, text);

    }

}

// public boolean validateTextInMessageNoContacts(String text){
//        return isTextInElementPresent(messageNoContacts,text);
//    }
