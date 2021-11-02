package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SiteResultsPage extends BasePage{

    private String FOXTROT_PRODUCT_TITLES = "//div[@class='card__body']/a";
    private String EPICENTER_PRODUCT_TITLES = "//em-product-card//b";

    public SiteResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getFoxtrotProductTitles() {
        return driver.findElements(By.xpath(FOXTROT_PRODUCT_TITLES));
    }

    public List<WebElement> getEpicenterProductTitles() {
        return driver.findElements(By.xpath(EPICENTER_PRODUCT_TITLES));
    }
}
