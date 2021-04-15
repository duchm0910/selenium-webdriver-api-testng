package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_Part_VI {
  WebDriver driver;
  WebDriverWait explicitWait;
String projectLocation = System.getProperty("user.dir");
  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, 15);
driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void TC_01_Only_Implicit() {
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    driver.get(
        "https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
    driver.findElement(By.xpath("//a[text()='15']/parent::td")).click();
    Assert.assertEquals(
        driver
            .findElement(
                By.xpath(
                    "//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='Monday, March 15, 2021']"))
            .getText(),
        "Monday, March 15, 2021");
  }

  @Test
  public void TC_02_Only_Explicit() {
    driver.get(
        "https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
    WebElement todayText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
    Assert.assertEquals(todayText.getText(), "No Selected Dates to display.");
    // Cho cho ngay 15 co the duoc click
    explicitWait.until(
        ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='15']/parent::td")));

    driver.findElement(By.xpath("//a[text()='15']/parent::td")).click();
//    todayText bi thay doi trang thai khi duoc click
    // CHo cho ngay chonj thanh cong
    explicitWait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//a[text()='15']/parent::td[@class='rcSelected']")));
    //    Cho cho icon loading bien mat
    explicitWait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@class='raDiv']/parent::div[not(@style='display:none;')]")));
    // Wait cho ngay duoc chon thanh cong
    todayText = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
    Assert.assertEquals(
        todayText.getText(),
        "Monday, March 15, 2021");
  }

  @Test
  public void TC_03_Only_Explicit(){
    driver.get("https://www.file.io");
    System.out.println(projectLocation+"\\uploadFile\\Background.jpg");
    driver.findElement(By.xpath("//input[@type='file']")).sendKeys(projectLocation+"\\uploadFile\\Background.jpg");
    explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("progress-button-total")));
    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("download-url")));
    driver.findElement(By.id("download-url")).click();
  }
  @Test
  public void TC_04_Only_Explicit(){
    driver.get("http://demo.guru99.com/v4/");
    driver.findElement(By.name("btnLogin")).click();

    Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
    Assert.assertEquals(alert.getText(),"User or Password is not valid");
    alert.accept();
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
