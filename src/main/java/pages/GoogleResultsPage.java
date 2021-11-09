package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;

import java.io.ByteArrayInputStream;
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

    @Step(value = "get urls")
    public List<WebElement> getResultUrls() {
        return driver.findElements(xpath(URLS));
    }

    @Step(value = "click on link of current site")
    public void clickOnLinkOfCurrentSite(int current_site) {
        Allure.addAttachment("Link of current site", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        driver.findElements(xpath(LINKS_TO_SITES)).get(current_site).click();
    }

    @Step(value = "go to next page")
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
