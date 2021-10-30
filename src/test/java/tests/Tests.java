package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class Tests extends BaseTest{

    @Test
    public void checkTitleTest() {
        assertEquals("Wikipedia, the free encyclopedia", getHomePage().getTitle());
    }

}
