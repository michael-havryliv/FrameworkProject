package tests;

import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import java.io.FileWriter;
import java.io.IOException;

public class SearchLinksTest extends BaseTest{

    Logger logger = Logger.getLogger(SearchLinksTest.class.getName());

    private final int DEFAULT_TIME_TO_WAIT = 30;

    private final String SEARCHED_KEYWORD = "чайник";
    private final String HOZSKLAD_SITE = "hozsklad";
    private final String HOZSKLAD_EXPECTED_PRODUCT = "ЧАЙНИК";

    @Test
    public void checkIfHozskladSiteContainsExpectedProducts() throws IOException {
        getGoogleHomePage().inputSearchedKeywordInInputField(SEARCHED_KEYWORD);
        getGoogleResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
        logger.info("Google home page loaded");
        boolean pageIsFound = false;
        int currentPage = 0;
        while(currentPage < 6){
            logger.info("Current page: " + currentPage);
            int currentSite = 0;
            for (WebElement webElement : getGoogleResultsPage().getResultUrls()) {
                if (webElement.getText().contains(HOZSKLAD_SITE)) {
                    getGoogleResultsPage().clickOnLinkOfCurrentSite(currentSite);
                    getSiteResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
                    pageIsFound = true;
                    logger.info("Site is found.");
                    break;
                } else currentSite++;
            }
            if(!pageIsFound){
                logger.info("Site is not found.");
                getGoogleResultsPage().goToNextPage(currentPage);
                getGoogleResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
                currentPage++;
            }else{
                FileWriter nFile = new FileWriter("src\\test\\resources\\file1.txt");
                for (WebElement webElement : getSiteResultsPage().getHozskladProductTitles()){
                    Assertions.assertTrue(webElement.getText().contains(HOZSKLAD_EXPECTED_PRODUCT),"Site does not have searched product.");
                    nFile.write(webElement.getAttribute("href")+"\n");
                }
                logger.info("Links saved to file. End of test.");
                nFile.close();
                break;
            }
        }
        Assertions.assertTrue(pageIsFound,"Page was not found. End of test.");
    }
}
