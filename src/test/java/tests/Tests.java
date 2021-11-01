package tests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests extends BaseTest{

    private static final String TITLE = "Wikipedia, the free encyclopedia";

    @Test
    public void checkTitleTest() {
        assertEquals(TITLE, getHomePage().getTitle());
    }

}
