package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class SiteResultsPage extends BasePage{

    private static final String FOXTROT_PRODUCT_TITLES = "//div[@class='card__body']/a";
    private static final String EPICENTER_PRODUCT_TITLES = "//em-product-card//b";
    private static final String HOZSKLAD_PRODUCT_TITLES = "//div[contains(@id,'product')]//span[@class='product_card__title']/a";

    public SiteResultsPage(WebDriver driver) {
        super(driver);
    }

    @Step(value = "get foxtrot product titles")
    public List<WebElement> getFoxtrotProductTitles() {
        Allure.addAttachment("Searched site", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return driver.findElements(By.xpath(FOXTROT_PRODUCT_TITLES));
    }

    @Step(value = "get epicenter product titles")
    public List<WebElement> getEpicenterProductTitles() {
        Allure.addAttachment("Searched site", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return driver.findElements(By.xpath(EPICENTER_PRODUCT_TITLES));
    }

    @Step(value = "get hozsklad product titles")
    public List<WebElement> getHozskladProductTitles() {
        Allure.addAttachment("Searched site", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return driver.findElements(By.xpath(HOZSKLAD_PRODUCT_TITLES));
    }

}
