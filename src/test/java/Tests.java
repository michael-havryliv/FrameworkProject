import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tests {

    @Test
    public void simpleTest(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        System.out.println("TITLE: " + driver.getTitle());
        Assertions.assertEquals("Wikipedia, the free encyclopedia",driver.getTitle());
    }

}
