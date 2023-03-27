package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class HomePage {
    public HomePage()
    {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    // ------------- Locators which is in Main Page("htpps://www.beymen.com") -----------------

    // After Navigate to Url > Opened "All Accept Button"
    @FindBy (id = "onetrust-accept-btn-handler")
    public WebElement cookiesAccept;

    // Women preference button
    @FindBy (id = "genderWomanButton")
    public WebElement genderWomanButton;

    // Man preference button
    @FindBy (id = "genderManButton")
    public WebElement genderManButton;

    // SearchBox
    @FindBy (xpath = "//div[@role='combobox']/input")
    public WebElement searchBox;

    // After any Search from SearchBox > All Products listed
    @FindBy (xpath = "//div[@class='m-productImageList']")
    public List<WebElement> products;

    //Openable Add To Card Button when hover product
    @FindBy(xpath = "//div[@class='m-productCard__stock']/button")
    public List<WebElement> addToCardButtons;

    //"Beden Seciniz" Texts
    @FindBy(xpath = "x")
    public WebElement chooseSizeText;

}
