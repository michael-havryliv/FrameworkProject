package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        return driver.findElements(By.xpath(FOXTROT_PRODUCT_TITLES));
    }

    @Step(value = "get epicenter product titles")
    public List<WebElement> getEpicenterProductTitles() {
        return driver.findElements(By.xpath(EPICENTER_PRODUCT_TITLES));
    }

    @Step(value = "get hozsklad product titles")
    public List<WebElement> getHozskladProductTitles() {
        return driver.findElements(By.xpath(HOZSKLAD_PRODUCT_TITLES));
    }

}
