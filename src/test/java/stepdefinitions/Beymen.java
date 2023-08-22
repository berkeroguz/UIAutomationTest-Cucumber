package stepdefinitions;

import io.cucumber.java.en.Given;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.Cart;
import pages.HomePage;
import pages.ProductPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Log;
import utilities.ReusableMethods;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Beymen {
    Cart cart;
    HomePage homePage;
    ProductPage productPage;
    int randomProduct;
    String data;
    String priceOfProduct;
    String priceOfProductinTheCart;
    @Given("beymen sitesi acilir")
    public void beymen_sitesi_acilir() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
    }
    @Given("anasayfanin acildigi kontrol edilir")
    public void anasayfanin_acildigi_kontrol_edilir() {
        Assert.assertTrue(Driver.getDriver().getTitle().contains("Beymen.com"));
        Log.info("Successfully navigated to 'www.beymen.com'");

        ReusableMethods.cookiesAndChooseGender();
        Log.info("Cookie turned off and gender selected");
    }
    @Given("arama kutucuguna sort kelimesi girilir")
    public void arama_kutucuguna_sort_kelimesi_girilir() throws IOException {
        homePage= new HomePage();
        data = ReusableMethods.readDataFromExcel("Sheet1",0,0).toString();
        homePage.searchBox.sendKeys(data);
        Log.info("Read '"+data+"' from excel");
        ReusableMethods.wait(1);
        Log.info("Waited for loading...");
    }
    @Given("arama kutucuguna girilen sort kelimesi silinir")
    public void arama_kutucuguna_girilen_sort_kelimesi_silinir() {
        //homePage.searchBox.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
        homePage.searchBox.clear();
        Log.info("Deleted first word");
        ReusableMethods.wait(1);
        Log.info("Waited for loading...");
    }
    @Given("arama kutucuguna gomlek kelimesi girilir ve entere basilir")
    public void arama_kutucuguna_gomlek_kelimesi_girilir_ve_entere_basilir() throws IOException {
        homePage=new HomePage();
        data = ReusableMethods.readDataFromExcel("Sheet1",0,1).toString();
        Log.info("Read '"+data+"' from excel");
        homePage.searchBox.sendKeys(data+ Keys.ENTER);
        Log.info("Searching relevant word...");
    }
    @Given("sonuca gore sergilenen urunlerden rastgele bir urun secilir")
    public void sonuca_gore_sergilenen_urunlerden_rastgele_bir_urun_secilir() {
        homePage=new HomePage();
        productPage=new ProductPage();
        Random rndm = new Random();
        int sizeProducts=homePage.products.size();
        int randomProduct = rndm.nextInt(sizeProducts);
        ReusableMethods.focusToElement(Driver.getDriver(),homePage.products.get(randomProduct));
        homePage.products.get(randomProduct).click();
        Log.info("Clicked on randomly selected product");

        //Choose Size of Product
        for (WebElement each : productPage.dimensionsOfProduct){
            if(!each.getAttribute("class").contains("-disabled")){
                each.click();
                Log.info("Choosen active,clickable product");
                break;
            }
        }
    }
    @Given("secilen urunun urun bilgisi ve tutar bilgisi txt dosyasine yazilir")
    public void secilen_urunun_urun_bilgisi_ve_tutar_bilgisi_txt_dosyasine_yazilir() throws IOException {
        productPage=new ProductPage();
        priceOfProduct = productPage.priceOfProduct.getText().trim().replaceAll("[^0-9]","");
        Log.info("Saved first Price of Product in the product page");
        FileWriter file = new FileWriter(System.getProperty("user.dir")+"\\textFiles"+"\\"+productPage.nameOfProduct.getText());
        Log.info("Created Text File");
        file.write(productPage.nameOfProduct.getText() +
                " ( "+productPage.descriptionOfProduct.getText() + " ) "+
                "Fiyatı = " + productPage.priceOfProduct.getText());
        file.close();
        Log.info("Written information of product");
    }
    @Given("secilen urun sepete eklenir")
    public void secilen_urun_sepete_eklenir() {
        productPage=new ProductPage();
        cart=new Cart();
        productPage.addToCartButton.click();
        Log.info("Clicked Add to Cart Button");
        productPage.goToCartButton.click();
        Log.info("Clicked Go to Cart Button");
        ReusableMethods.waitForVisibility(cart.productPrice,3);
        priceOfProductinTheCart = cart.productPrice.getText().trim().replaceAll("[^0-9]","");
    }
    @Given("urun sayfasindaki fiyat ile sepette yer alan urun fiyatinin dogrulugu karsilastirilir")
    public void urun_sayfasindaki_fiyat_ile_sepette_yer_alan_urun_fiyatinin_dogrulugu_karsilastirilir() {
        Assert.assertTrue(priceOfProductinTheCart.contains(priceOfProduct));
        Log.info("Price comparison test PASSED");

    }
    @Given("adet arttirilarak urun adedinin {int} oldugu dogrulanir")
    public void adet_arttirilarak_urun_adedinin_oldugu_dogrulanir(Integer int1) {
        cart=new Cart();
        ReusableMethods.chooseAndCheckDropDown(cart,2);
    }
    @Given("urun sepetten silinerek sepetin bos oldugu kontrol edilir")
    public void urun_sepetten_silinerek_sepetin_bos_oldugu_kontrol_edilir() {
        cart=new Cart();
        ReusableMethods.clearCart(cart);
        Log.info("The Cart was emptied !");
        Assert.assertTrue(cart.emptyCartMessage.isDisplayed());
        Log.info("Clear Cart Test PASSED");
    }
    @Given("rastgele bir urun uzerinde beklenir")
    public void rastgele_bir_urun_uzerinde_beklenir() {
        homePage=new HomePage();
        productPage=new ProductPage();
        Random rndm = new Random();
        int sizeProducts=homePage.products.size();
        randomProduct = rndm.nextInt(sizeProducts);
        ReusableMethods.focusToElement(Driver.getDriver(),homePage.products.get(randomProduct));
        ReusableMethods.hover(homePage.products.get(randomProduct));
    }
    @Given("sepete ekle butonuna tiklanir")
    public void sepete_ekle_butonuna_tiklanir() {
        homePage=new HomePage();
        homePage.addToCardButtons.get(randomProduct).click();
    }
    @Given("beden seciniz uyarisinin gorundugu dogrulanir")
    public void beden_seciniz_uyarisinin_gorundugu_dogrulanir() {
        homePage=new HomePage();
        Assert.assertTrue(homePage.chooseSizeText.isDisplayed());
    }
}

