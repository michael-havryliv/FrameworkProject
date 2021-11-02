package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class SearchTests extends BaseTest{

    private final int DEFAULT_TIME_TO_WAIT = 30;

    private final String SEARCHED_KEYWORD = "чайник";
    private final String FOXTROT_SITE = "foxtrot";
    private final String EPICENTER_SITE = "epicentrk";
    private final String FOXTROT_EXPECTED_PRODUCT = "Електрочайник";
    private final String EPICENTER_EXPECTED_PRODUCT = "Чайник";

    @Test
    public void checkIfFoxtrotSiteContainsExpectedProducts(){
        getGoogleHomePage().inputSearchedKeywordInInputField(SEARCHED_KEYWORD);
        getGoogleResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
        boolean pageIsFound = false;
        int currentPage = 0;
        while(true){
            int currentSite = 0;
            for (WebElement webElement : getGoogleResultsPage().getResultUrls()) {
                if (webElement.getText().contains(FOXTROT_SITE)) {
                    getGoogleResultsPage().clickOnLinkOfCurrentSite(currentSite);
                    getSiteResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
                    pageIsFound = true;
                    break;
                } else currentSite++;
            }
            if(!pageIsFound){
                getGoogleResultsPage().goToNextPage(currentPage);
                getGoogleResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
                currentPage++;
            }else break;
        }
        for (WebElement webElement : getSiteResultsPage().getFoxtrotProductTitles()){
            Assert.assertTrue(webElement.getText().contains(FOXTROT_EXPECTED_PRODUCT));
        }
    }

    @Test
    public void checkIfEpicenterSiteContainsSearchedKeyWord(){
        getGoogleHomePage().inputSearchedKeywordInInputField(SEARCHED_KEYWORD);
        getGoogleResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
        boolean pageIsFound = false;
        int currentPage = 0;
        while(true){
            int currentSite = 0;
            for (WebElement webElement : getGoogleResultsPage().getResultUrls()) {
                if (webElement.getText().contains(EPICENTER_SITE)) {
                    getGoogleResultsPage().clickOnLinkOfCurrentSite(currentSite);
                    getSiteResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
                    pageIsFound = true;
                    break;
                } else currentSite++;
            }
            if(!pageIsFound){
                getGoogleResultsPage().goToNextPage(currentPage);
                getGoogleResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
                currentPage++;
            }else break;
        }
        for (WebElement webElement : getSiteResultsPage().getEpicenterProductTitles()){
            Assert.assertTrue(webElement.getText().contains(EPICENTER_EXPECTED_PRODUCT));
        }
    }



}
