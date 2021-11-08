package pages;

import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import io.qameta.allure.Step;

public class GoogleHomePage extends BasePage {

    private static final String GOOGLE_INPUT_FIELD = "//input[@title]";

    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    @Step(value = "input searched keyword in input field")
    @Attachment(value = "Page screenshot", type = "image/png")
    public void inputSearchedKeywordInInputField(String searchedKeyword) {
        driver.findElement(By.xpath(GOOGLE_INPUT_FIELD)).sendKeys(searchedKeyword, Keys.ENTER);
    }
}
