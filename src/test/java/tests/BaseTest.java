package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.GoogleHomePage;
import pages.GoogleResultsPage;
import pages.HomePage;
import pages.SiteResultsPage;

public class BaseTest {

    private WebDriver driver;
    private static final String URL = "https://www.google.com/";

    @BeforeClass
    public static void profileSetUp(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver95.exe");
    }

    @Before
    public void testsSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @After
    public void tearDown(){
        driver.quit();
    }


    public WebDriver getDriver() {
        return driver;
    }

    public HomePage getHomePage() {
        return new HomePage(getDriver());
    }

    public GoogleHomePage getGoogleHomePage(){
        return new GoogleHomePage(getDriver());
    }

    public GoogleResultsPage getGoogleResultsPage(){
        return new GoogleResultsPage(getDriver());
    }

    public SiteResultsPage getSiteResultsPage() {return new SiteResultsPage(getDriver());}

}
