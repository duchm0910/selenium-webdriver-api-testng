package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

public class Topic_17_Download_File {
  WebDriver driver;
  String projectLocation = System.getProperty("user.dir");

  @BeforeClass
  public void beforeClass(){
//    System.setProperty("webdriver.chrome.driver","E:\\Selenium Online 18\\02 - Selenium API\\selenium-webdriver-api-testing\\browserDriver\\chromedriver.exe");
//    driver = new ChromeDriver();
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    driver.manage().window().maximize();

  }


}
