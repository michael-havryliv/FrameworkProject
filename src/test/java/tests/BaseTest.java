package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;

public class BaseTest {

    private WebDriver driver;
    private static final String URL = "https://en.wikipedia.org/wiki/Main_Page";

    @Before
    public void testsSetUp(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public HomePage getHomePage() {
        return new HomePage(getDriver());
    }

}
