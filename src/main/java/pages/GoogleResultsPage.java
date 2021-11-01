package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class GoogleResultsPage extends BasePage {

    private String URLS = "//div[@class='TbwUpd NJjxre']/cite";
    private String LINKS_TO_SITES = "//div//a/h3";

    public GoogleResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getResultUrls() {
        return driver.findElements(xpath(URLS));
    }

    public void clickOnLinkOfCurrentSite(int current_site) {
        driver.findElements(xpath(LINKS_TO_SITES)).get(current_site).click();
    }

}
