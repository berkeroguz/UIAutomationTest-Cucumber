package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class Cart {
    public Cart(){
            PageFactory.initElements(Driver.getDriver(),this);
    }

    // Price of Product in the cart.
    @FindBy(xpath = "//span[@class='m-productPrice__salePrice']")
    public WebElement productPrice;

    // Quantity of Product DropDown
    @FindBy(id = "quantitySelect0-key-0")
    public WebElement quantityOfProductDropDown;

    // Delete Product Button
    @FindBy(id = "removeCartItemBtn0-key-0")
    public WebElement deleteProduct;

    // Message of Empty Cart
    @FindBy(xpath = "(//strong[@class='m-empty__messageTitle'])[1]")
    public WebElement emptyCartMessage;

    // Items in Cart
    @FindBy(className = "m-basket__item")
    public List<WebElement> itemsInCart;
}
