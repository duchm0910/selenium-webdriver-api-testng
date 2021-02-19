package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Window_Tab {
  WebDriver driver;

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    //    driver.manage().window().maximize();
  }

  @Test
  public void TC_01_Only_2_Window_Tab() {
    driver.get("https://automationfc.github.io/basic-form/index.html");
    //    Mỗi Tab/Window sẽ có 1 ID đại diện gọi là GUID (Global Unique Indentifier)
    //    Lấy ra cái ID của tab/window đang actve
    //    Lấy ra ID của tab trước khi thực hiện
    String parentWindowID = driver.getWindowHandle();
    System.out.println("ID của parent tab là: ");
    System.out.println(parentWindowID);

    driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
    sleepInSecond(3);
    swicthToWindowID(parentWindowID);
    Assert.assertTrue(driver.findElement(By.xpath("//div[@id='hplogo']")).isDisplayed());
    String googleWindowID = driver.getWindowHandle();
    swicthToWindowID(googleWindowID);
    driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
    sleepInSecond(2);
  }

  @Test
  public void TC_02_Greater_Than_2_Windows_Tab() {
    driver.get("https://automationfc.github.io/basic-form/index.html");
    String parentWindowID = driver.getWindowHandle();
    System.out.println("ID của parent tab là: ");
    System.out.println(parentWindowID);

    driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
    sleepInSecond(3);

    switchToWindowByTitle("Google");
    sleepInSecond(2);
    Assert.assertTrue(driver.findElement(By.xpath("//div[@id='hplogo']")).isDisplayed());

    switchToWindowByTitle("SELENIUM FORM DEMO");
    sleepInSecond(2);

    driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
    sleepInSecond(2);

    switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
    sleepInSecond(1);
    Assert.assertTrue(driver.findElement(By.xpath("//button[@name='login']")).isDisplayed());

    switchToWindowByTitle("SELENIUM FORM DEMO");
    sleepInSecond(2);
    driver.findElement(By.xpath("//a[text()='LAZADA']")).click();

    switchToWindowByTitle("Shopping online - Buy online on Lazada.vn");
    Assert.assertTrue(driver.findElement(By.xpath("//input[@id='q']")).isDisplayed());

    clossAllWindowExceptParent(parentWindowID);
  }

  @Test
  public void TC_03_Compare_Produc() {
    driver.get("http://live.demoguru99.com/index.php/");

    driver.findElement(By.xpath("//a[text()='Mobile']")).click();
    String parentID = driver.getWindowHandle();
    driver
        .findElement(
            By.xpath(
                "//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
        .click();
    Assert.assertEquals(
        driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
        "The product IPhone has been added to comparison list.");
    driver
        .findElement(
            By.xpath(
                "//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
        .click();
    Assert.assertEquals(
        driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
        "The product Samsung Galaxy has been added to comparison list.");
    driver.findElement(By.xpath("//button[@title='Compare']")).click();

    switchToWindowByTitle("Products Comparison List - Magento Commerce");
    sleepInSecond(2);

    Assert.assertEquals(driver.findElements(By.xpath("//h2[@class='product-name']/a")).size(),2);
    clossAllWindowExceptParent(parentID);
    sleepInSecond(2);

    driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Sony Xperia");
    driver.findElement(By.xpath("//button[@title='Search']")).click();
  }

  // Dung cho 2 window/tab
  public void swicthToWindowID(String parentID) {
    //    Lấy ra tất cả ID của window/tab đang có
    Set<String> allWindowID = driver.getWindowHandles();
    //    Nếu window ID này khác với parent ID thì nhảy vào If
    for (String windowId : allWindowID) {
      if (!windowId.equals(parentID)) {
        // Switch vao window/tab theo ID
        driver.switchTo().window(windowId);
        break;
      }
    }
  }

  // >= 2 window tab deu dung duoc
  public void switchToWindowByTitle(String expectedWindowTitle) {
    //    Lay ra tat ca cac window/tab dg co
    Set<String> allWindowID = driver.getWindowHandles();
    System.out.println("Số lượng window/tab đang có: " + allWindowID.size());
    //    Duyet qua cac gia tri trong Set
    for (String windowID : allWindowID) {
      //      Switch vao tung window/tab truoc
      driver.switchTo().window(windowID);
      String actualWindowTitle = driver.getTitle();
      //       Kiem tra neu nhu cai nao bang voi title mong muon
      if (actualWindowTitle.equals(expectedWindowTitle)) {
        break;
      }
    }
  }
  //  Close all tab/window except Parent tab
  public void clossAllWindowExceptParent(String parentWindowID) {
    Set<String> allWindowID = driver.getWindowHandles();
    for (String windowID : allWindowID) {
      if (!windowID.equals(parentWindowID)) {
        driver.switchTo().window(windowID);
        driver.close();
        sleepInSecond(1);
      }

      if (driver.getWindowHandles().size() == 1) {
        driver.switchTo().window(parentWindowID);
        break;
      }
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
