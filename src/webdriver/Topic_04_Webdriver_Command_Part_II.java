package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Webdriver_Command_Part_II {
  WebDriver driver;

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get("http://live.demoguru99.com/index.php/");
  }

  @Test
  public void TC_01_Title() {
    driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
    String loginPageUrl = driver.getCurrentUrl();
    System.out.println(loginPageUrl);
    Assert.assertEquals(
        loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");

    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
    String registerPageUrl = driver.getCurrentUrl();
    System.out.println(registerPageUrl);
    Assert.assertEquals(
        registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
  }

  @Test
  public void TC_02_Url() {
    driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
    String loginPageTitle = driver.getTitle();
    System.out.println(loginPageTitle);
    Assert.assertEquals(loginPageTitle, "Customer Login");
    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
    String registerPageTitle = driver.getTitle();
    System.out.println(registerPageTitle);
    Assert.assertEquals(registerPageTitle, "Create New Customer Account");
  }

  @Test
  public void TC_03_Navigation() {
    driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
    String registerUrl = driver.getCurrentUrl();
    System.out.println(registerUrl);
    Assert.assertEquals(
        registerUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
    driver.navigate().back();
    String loginUrl = driver.getCurrentUrl();
    System.out.println(loginUrl);
    Assert.assertEquals(loginUrl, "http://live.demoguru99.com/index.php/customer/account/login/");
    driver.navigate().forward();
    String registerTitle = driver.getTitle();
    Assert.assertEquals(registerTitle, "Create New Customer Account");
  }

  @Test
  public void TC_04_Page_Source() {
    driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
    String PageSource = driver.getPageSource();
    Assert.assertTrue(PageSource.contains("Login or Create an Account"));
    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
    Assert.assertTrue(PageSource.contains("Create an Account"));
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
