package utilities;

import org.apache.poi.ss.usermodel.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Cart;
import pages.HomePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class ReusableMethods {

    static String pathExcel=System.getProperty("user.dir")+"\\src\\test\\resources\\beymen.xlsx";
    public static Cell readDataFromExcel(String sheet,int row,int cell) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(pathExcel);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet st = workbook.getSheet(sheet);
        Row rw = st.getRow(row);
        Cell data = rw.getCell(cell);
        return data;
    }
    public static void cookiesAndChooseGender(){
        HomePage homePage = new HomePage();
        homePage.cookiesAccept.click();
        homePage.genderManButton.click();
    }

    public static void wait(int second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void focusToElement(WebDriver driver,WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",element);
    }

    public static int onlyTakeNumber(String number){
        return Integer.parseInt(number.replaceAll("\\D",""));
    }


    // This method will choose given an option from dropDown which is Quantity Product in the Cart
    // and also verify that is working correctly
    public static void chooseAndCheckDropDown(Cart cart,int requestedValue){
        Select select = new Select(cart.quantityOfProductDropDown);
        Log.info("Quantity is being selected from dropBox...");
        if (select.getOptions().size()>=requestedValue)
        {

            int aProductPrice = ReusableMethods.onlyTakeNumber(cart.productPrice.getText());
                select.selectByValue(String.valueOf(requestedValue));
                int expectedPrice = aProductPrice * requestedValue;
                ReusableMethods.wait(2);
                int actualPrice= onlyTakeNumber(cart.productPrice.getText());
                Assert.assertEquals(expectedPrice,actualPrice);
                Log.info("Quantity Change test PASSED");

        }
        else Log.info("There is only one sale...");
    }
    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }
    public static void clearCart(Cart cart){

        while (cart.itemsInCart.size()>0){
            try {
                cart.deleteProduct.click();
                Thread.sleep(500);
            } catch (Exception e) {
                Log.info("Removing product...");
            }
        }

    }


}
