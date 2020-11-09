package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_Firefox_Chrome_Edge {
    WebDriver driver;
    String project_location = System.getProperty("user.dir");

    @Test
    public void TC_01_Run_On_Firefox() {
//        Selenium 2.53.1
//        Firefox 47.0.2
//        No need geckodriver
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();
    }

    @Test
    public void TC_02_Run_On_Chrome() {
        //        Selenium 2.53.1
//        Chrome latest ver
//         chromedriver latest ver
//        Windows
//        relative paths
//        1
        System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
//        2
//        System.setProperty("webdriver.chrome.driver", project_location + "\\browserDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();

    }

    @Test
    public void TC_03_Run_On_Edge() {
        System.setProperty("webdriver.edge.driver", ".\\browserDriver\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();

    }
}
