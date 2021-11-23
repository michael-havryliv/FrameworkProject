package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.By.xpath;

public class GoogleResultsPage extends BasePage {

    Logger logger = Logger.getLogger(GoogleResultsPage.class.getName());

    private static final String URLS = "//div[@class='TbwUpd NJjxre']/cite";
    private static final String LINKS_TO_SITES = "//div//a/h3";
    private static final String LINKS_TO_SEARCH_PAGES = "//*[@id=\"xjs\"]//a[@class='fl']";

    public GoogleResultsPage(WebDriver driver) {
        super(driver);
    }

    @Step(value = "get urls as web elements")
    public List<WebElement> getSiteWebElementUrls() {
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

    @Step(value = "get site urls as text")
    public List<String> getSiteUrls(){
        List<String> result = new ArrayList<>();
        for(WebElement url : driver.findElements(xpath(URLS))){
            result.add(url.getText());
        }
        return result;
    }


    public List<String> getSiteDomainNames(List<String> siteUrls) {
        String regex = "://(?:\\w{2,3}\\W)?(\\w+)";
        List<String> result = new ArrayList<>();
        for (String url : siteUrls){
            Pattern pt = Pattern.compile(regex);
            Matcher mt = pt.matcher(url);
            if(mt.find()){
                result.add(mt.group(1));
            }
        }
        return result;
    }

}
