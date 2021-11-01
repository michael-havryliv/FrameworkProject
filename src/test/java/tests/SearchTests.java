package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class SearchTests extends BaseTest{

    private int DEFAULT_TIME_TO_WAIT = 30;

    private String SEARCHED_KEYWORD = "чайник";
    private String SEARCHED_SITE = "foxtrot";
    private String EXPECTED_PRODUCT = "Електрочайник";

    @Test
    public void checkIfSiteContainsExpectedProducts(){
        getGoogleHomePage().inputSearchedKeywordInInputField(SEARCHED_KEYWORD);
        getGoogleResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
        int currentSite = 0;
        for(WebElement webElement : getGoogleResultsPage().getResultUrls()){
            if(webElement.getText().contains(SEARCHED_SITE)){
                getGoogleResultsPage().clickOnLinkOfCurrentSite(currentSite);
                getSiteResultsPage().waitForPageLoadComplete(DEFAULT_TIME_TO_WAIT);
                break;
            }else currentSite++;
        }
        for (WebElement webElement : getSiteResultsPage().getProductTitles()){
            Assert.assertTrue(webElement.getText().contains(EXPECTED_PRODUCT));
        }
    }

}
