package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class ProductPage {
    public ProductPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    // Dimension Of Choosen product
    @FindBy(xpath = "//div[@class='m-variation']/span")
    public List<WebElement> dimensionsOfProduct;

    // Description of Product
    @FindBy (xpath = "//span[@class='o-productDetail__description']")
    public WebElement descriptionOfProduct;

    // Name of Product
    @FindBy(xpath = "//h1[@class='o-productDetail__title']/a")
    public WebElement nameOfProduct;

    // Price of Product
    @FindBy(id = "priceNew")
    public WebElement priceOfProduct;

    // Add To Cart Button
    @FindBy(id = "addBasket")
    public WebElement addToCartButton;

    // Go To Cart in Notification
    @FindBy(xpath = "//button[@class='m-notification__button btn']")
    public WebElement goToCartButton;
}
