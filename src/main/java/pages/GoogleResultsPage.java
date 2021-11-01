package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class GoogleResultsPage extends BasePage {

    private String URLS = "//div[@class='TbwUpd NJjxre']/cite";
    private String LINKS_TO_SITES = "//div//a/h3";
    private String LINKS_TO_SEARCH_PAGES = "//*[@id=\"xjs\"]//a/span";

    public GoogleResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getResultUrls() {
        return driver.findElements(xpath(URLS));
    }

    public void clickOnLinkOfCurrentSite(int current_site) {
        driver.findElements(xpath(LINKS_TO_SITES)).get(current_site).click();
    }

    public void goToNextPage(int current_page) {
        driver.findElements(By.xpath(LINKS_TO_SEARCH_PAGES)).get(current_page).click();
    }
}
