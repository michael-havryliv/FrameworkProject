package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SiteResultsPage extends BasePage{

    private String PRODUCT_TITLES = "//div[@class='card__body']/a";

    public SiteResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getProductTitles() {
        return driver.findElements(By.xpath(PRODUCT_TITLES));
    }
}
