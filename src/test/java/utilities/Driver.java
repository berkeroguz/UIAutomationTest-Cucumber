package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class Driver {
    private Driver(){

    }
    public static WebDriver driver;
    public static WebDriver getDriver(){
        String browser = ConfigReader.getProperty("browser");
        if(driver==null){
            switch(browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOps = new ChromeOptions();
                    chromeOps.addArguments("--remote-allow-origins=*");
                    chromeOps.addArguments("--disable-notifications");
                    driver=new ChromeDriver(chromeOps);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOps = new EdgeOptions();
                    edgeOps.addArguments("--disable-notifications");
                    driver=new EdgeDriver(edgeOps);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOps = new FirefoxOptions();
                    firefoxOps.addArguments("--disable-notifications");
                    driver=new FirefoxDriver(firefoxOps);
                    break;

                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
    public static void quitDriver(){
        if(driver!=null)
        {
            driver.quit();
            driver=null;
        }
    }
}
