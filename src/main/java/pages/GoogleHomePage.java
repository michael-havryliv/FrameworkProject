package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import io.qameta.allure.Step;

import java.io.ByteArrayInputStream;

public class GoogleHomePage extends BasePage {

    private static final String GOOGLE_INPUT_FIELD = "//input[@title]";

    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    @Step(value = "input searched keyword in input field")
    public void inputSearchedKeywordInInputField(String searchedKeyword) {
        driver.findElement(By.xpath(GOOGLE_INPUT_FIELD)).sendKeys(searchedKeyword);
        Allure.addAttachment("Google input field", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        driver.findElement(By.xpath(GOOGLE_INPUT_FIELD)).sendKeys(Keys.ENTER);
    }
}
