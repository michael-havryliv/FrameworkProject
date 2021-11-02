package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class SearchTests extends BaseTest{

    Logger logger = Logger.getLogger(SearchLinksTest.class.getName());

    private final int DEFAULT_TIME_TO_WAIT = 30;

    private final String SEARCHED_KEYWORD = "чайник";
    private final String FOXTROT_SITE = "foxtrot9999999999";
    private final String EPICENTER_SITE = "epicentrk";
    private final String HOZSKLAD_SITE = "hozsklad";
    private final String FOXTROT_EXPECTED_PRODUCT = "Електрочайник";
    private final String EPICENTER_EXPECTED_PRODUCT = "Чайник";
    private final String HOZSKLAD_EXPECTED_PRODUCT = "ЧАЙНИК";

    @Test
    public void checkIfFoxtrotSiteContainsExpectedProducts(){
        getGoogleHomePage().inputSearchedKeywordInInputField(SEARCHED_KEYWORD);
        getGoogleResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
        logger.info("Google home page loaded");
        boolean pageIsFound = false;
        int currentPage = 0;
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
            Assert.assertTrue("Site does not have searched product",webElement.getText().contains(FOXTROT_EXPECTED_PRODUCT));
        }
        Assert.assertTrue("Site was not found. End of test.",pageIsFound);
    }

    @Test
    public void checkIfEpicenterSiteContainsExpectedProducts(){
        getGoogleHomePage().inputSearchedKeywordInInputField(SEARCHED_KEYWORD);
        getGoogleResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
        logger.info("Google home page loaded");
        boolean pageIsFound = false;
        int currentPage = 0;
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
            Assert.assertTrue("Site does not have searched product",webElement.getText().contains(EPICENTER_EXPECTED_PRODUCT));
        }
        Assert.assertTrue("Site was not found. End of test.",pageIsFound);
    }

}
