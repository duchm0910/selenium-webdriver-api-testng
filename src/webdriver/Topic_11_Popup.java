package webdriver;

import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup {
  WebDriver driver;
  WebDriverWait explicitWait;

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    explicitWait = new WebDriverWait(driver, 5);

    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void Test_01_Fixed_Popup() {
    driver.get("https://zingpoll.com/");

    driver.findElement(By.id("Loginform")).click();
    //    Wait until element duoc hien thi
    explicitWait.until(
        ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));

    Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());

    driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
    //    Wait until element ko duoc hien thi
    explicitWait.until(
        ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));

    Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
  }

  @Test
  public void Test_02_Fixed_Popup() {
    driver.get("https://bni.vn/");
    //    Wait until element appear
    explicitWait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
    Assert.assertTrue(
        driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());

    //    driver.findElement(By.xpath("//input[@value='JOIN WITH US']")).click();

    //    Wait until element has click
    explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Close']")));

    driver.findElement(By.xpath("//img[@alt='Close']")).click();

    //    Wait until element disapear
    explicitWait.until(
        ExpectedConditions.invisibilityOfElementLocated(
            By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
    Assert.assertFalse(
        driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
  }

  @Test
  public void Test_03_Random_Popup_In_DOM() {
    driver.get("https://blog.testproject.io/");
    //   Co xuat hien >> dong pop up >> qua step tiep theo
    //     KHong xuat hien >> qua step tiep theo'
    sleepInSecond(7);
    if (driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
      //      Cho cho element co click duoc hay khong
      explicitWait.until(
          ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='close-mailch']")));
      driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
      //
      // explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")));
      sleepInSecond(2);
    }

    explicitWait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//section//input[@class='search-field']")));
    driver.findElement(By.xpath("//section//input[@class='search-field']")).sendKeys("selenium");

    explicitWait.until(
        ExpectedConditions.elementToBeClickable(By.xpath("//section//span[@class='glass']")));
    driver.findElement(By.xpath("//section//span[@class='glass']")).click();
    sleepInSecond(3);
  }

  @Test
  public void Test_04_Random_Popup_Not_In_DOM() {
    driver.get("https://shopee.vn/");
    sleepInSecond(5);

    List<WebElement> popup = driver.findElements(By.xpath("//img[@alt='home_popup_banner']"));
    if (popup.size() > 0 && popup.get(0).isDisplayed()) {
      System.out.println("Close Popup");
      explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".shopee-popup__close-btn")));
      driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
    }else{
      System.out.println("Popup khong xuat hien");
    }
  }

  public void sleepInSecond(long timeInSecond) {
    try {
      Thread.sleep(timeInSecond * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @AfterTest
  public void afterTest() {
    driver.quit();
  }
}
