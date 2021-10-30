package tests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests extends BaseTest{

    @Test
    public void checkTitleTest() {
        assertEquals("Wikipedia, the free encyclopedia", getHomePage().getTitle());
    }

}
