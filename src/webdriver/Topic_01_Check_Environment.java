package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Check_Environment {
  WebDriver driver;

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get("http://demo.guru99.com/v4/");
  }

  @Test
  public void TC_01_ValidateCurrentUrl() {
    // Login Page Url matching
    String loginpageUrl = driver.getCurrentUrl();
    Assert.assertEquals(loginpageUrl, "http://demo.guru99.com/v4/");
  }

  @Test
  public void TC_02_ValidatePageTitle() {
    // Login Page title
    String loginpageTitle = driver.getTitle();
    Assert.assertEquals(loginpageTitle, "Guru99 Bank Home Page");
  }

  @Test
  public void TC_03_LoginFormDisplayed() {
    // Login form displayed
    Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
  }

  @AfterTest
  public void afterClass() {
    driver.quit();
  }
}
