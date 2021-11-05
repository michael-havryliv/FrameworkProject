package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static org.openqa.selenium.By.xpath;

public class GoogleResultsPage extends BasePage {

    Logger logger = Logger.getLogger(GoogleResultsPage.class.getName());

    private static final String URLS = "//div[@class='TbwUpd NJjxre']/cite";
    private static final String LINKS_TO_SITES = "//div//a/h3";
    private static final String LINKS_TO_SEARCH_PAGES = "//*[@id=\"xjs\"]//a[@class='fl']";

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
        boolean goToNextPage = false;
        for (WebElement webElement : driver.findElements(By.xpath(LINKS_TO_SEARCH_PAGES))){
            if(Objects.equals(webElement.getText(), Integer.toString(current_page + 1))){
                webElement.click();
                logger.info("Going to next page");
                goToNextPage = true;
                break;
            }
        }
        if(!goToNextPage) logger.info("Cannot go to next page");
    }
}
