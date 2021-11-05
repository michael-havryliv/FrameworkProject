package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;

public class GoogleHomePage extends BasePage {

    private static final String GOOGLE_INPUT_FIELD = "//input[@title]";

    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public void inputSearchedKeywordInInputField(String searchedKeyword) {
        driver.findElement(By.xpath(GOOGLE_INPUT_FIELD)).sendKeys(searchedKeyword, Keys.ENTER);
    }
}
