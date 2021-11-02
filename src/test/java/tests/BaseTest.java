package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.GoogleHomePage;
import pages.GoogleResultsPage;
import pages.SiteResultsPage;

public class BaseTest {

    private static WebDriver driver;
    private static final String URL = "https://www.google.com/";

    @BeforeAll
    static void profileSetUp(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver95.exe");
    }

    @BeforeEach
    public void testsSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

    @AfterAll
    static void closeAll(){
        driver.quit();
    }


    public WebDriver getDriver() {
        return driver;
    }

    public GoogleHomePage getGoogleHomePage(){
        return new GoogleHomePage(getDriver());
    }

    public GoogleResultsPage getGoogleResultsPage(){
        return new GoogleResultsPage(getDriver());
    }

    public SiteResultsPage getSiteResultsPage() {return new SiteResultsPage(getDriver());}

}
