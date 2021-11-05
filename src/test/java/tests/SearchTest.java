package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class SearchTest extends BaseTest{

    Logger logger = Logger.getLogger(SearchLinksTest.class.getName());

    private final int DEFAULT_TIME_TO_WAIT = 30;

    private static final String SEARCHED_KEYWORD = "чайник";
    private static final String FOXTROT_SITE = "foxtrot";
    private static final String EPICENTER_SITE = "epicentrk";
    private static final String FOXTROT_EXPECTED_PRODUCT = "Електрочайник";
    private static final String EPICENTER_EXPECTED_PRODUCT = "Чайник";


    @Test
    @DisplayName("Search Foxtrot site, and check if it contains expected products")
    public void checkIfFoxtrotSiteContainsExpectedProducts(){
        getGoogleHomePage().inputSearchedKeywordInInputField(SEARCHED_KEYWORD);
        getGoogleResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
        logger.info("Google home page loaded");
        boolean pageIsFound = false;
        int currentPage = 1;
        while(currentPage < 6){
            logger.info("Current page: " + currentPage);
            int currentSite = 0;
            for (WebElement webElement : getGoogleResultsPage().getResultUrls()) {
                if (webElement.getText().contains(FOXTROT_SITE)) {
                    getGoogleResultsPage().clickOnLinkOfCurrentSite(currentSite);
                    getSiteResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
                    pageIsFound = true;
                    logger.info("Site is found");
                    break;
                } else currentSite++;
            }
            if(!pageIsFound){
                getGoogleResultsPage().goToNextPage(currentPage);
                getGoogleResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
                logger.info("Site is not found");
                currentPage++;
            }else break;
        }
        for (WebElement webElement : getSiteResultsPage().getFoxtrotProductTitles()){
            Assertions.assertTrue(webElement.getText().contains(FOXTROT_EXPECTED_PRODUCT),"Site does not have searched product");
        }
        Assertions.assertTrue(pageIsFound,"Site was not found. End of test.");
    }

    @Test
    @DisplayName("Search Epicenter site, and check if it contains expected products")
    public void checkIfEpicenterSiteContainsExpectedProducts(){
        getGoogleHomePage().inputSearchedKeywordInInputField(SEARCHED_KEYWORD);
        getGoogleResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
        logger.info("Google home page loaded");
        boolean pageIsFound = false;
        int currentPage = 1;
        while(currentPage < 6){
            logger.info("Current page: " + currentPage);
            int currentSite = 0;
            for (WebElement webElement : getGoogleResultsPage().getResultUrls()) {
                if (webElement.getText().contains(EPICENTER_SITE)) {
                    getGoogleResultsPage().clickOnLinkOfCurrentSite(currentSite);
                    getSiteResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
                    logger.info("Site is found");
                    pageIsFound = true;
                    break;
                } else currentSite++;
            }
            if(!pageIsFound){
                getGoogleResultsPage().goToNextPage(currentPage);
                getGoogleResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
                logger.info("Site is not found");
                currentPage++;
            }else break;
        }
        for (WebElement webElement : getSiteResultsPage().getEpicenterProductTitles()){
            Assertions.assertTrue(webElement.getText().contains(EPICENTER_EXPECTED_PRODUCT),"Site does not have searched product");
        }
        Assertions.assertTrue(pageIsFound,"Site was not found. End of test.");
    }

}
