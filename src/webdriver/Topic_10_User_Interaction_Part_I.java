package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction_Part_I {
  WebDriver driver;
  Actions action;

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    action = new Actions(driver);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    driver.manage().window().maximize();
  }

  @Test
  public void TC_01_Hover_Mouse() {
    driver.get("https://tiki.vn/");
    //    Verify login button is undisplayed
    Assert.assertFalse(driver.findElement(By.xpath("//button[text()='Đăng nhập']")).isDisplayed());

    WebElement shortCutAccount =
        driver.findElement(By.xpath("//div[@data-view-id='header_user_shortcut_account']"));

    action.moveToElement(shortCutAccount).perform();
    sleepInSecond(3);

    //    Verify login button is displayed
    Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Đăng nhập']")).isDisplayed());
  }

  @Test
  public void TC_02_Hover_Mouse_I() {

    driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
    action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
    sleepInSecond(4);

    Assert.assertEquals(
        driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
        "We ask for your age only for statistical purposes.");
  }

  @Test
  public void TC_02_Hover_Mouse_II() {
    driver.get("http://www.myntra.com/");
    WebElement kids =
        driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"));

    action.moveToElement(kids).perform();
    sleepInSecond(3);
    driver.findElement(By.xpath("//a[@href='/kids-home-bath']")).click();

    Assert.assertEquals(
        driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(),
        "Kids Home Bath");
    Assert.assertEquals(
        driver.findElement(By.xpath("//h1[@class='title-title']")).getText(), "Kids Home Bath");
  }

  @Test
  public void TC_03_Hover_Mouse() {
    driver.get("https://hn.telio.vn/");

    action
        .moveToElement(
            driver.findElement(
                By.xpath(
                    "//div[@class='cdz-main-menu left-navigation hidden-xs']//span[text()='Đồ uống']")))
        .perform();
    action
        .click(
            driver.findElement(
                By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='Bia']")))
        .perform();
    //    DOM
    Assert.assertTrue(
        driver
            .findElement(By.xpath("//h1[@id='page-title-heading']//span[text()='Bia']"))
            .isDisplayed());
    //    UI
    Assert.assertEquals(
        driver.findElement(By.xpath("//h1[@id='page-title-heading']//span")).getText(), "BIA");
  }

  @Test
  public void TC_04_Click_And_Hold() {
    driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

    //    Tao ra 1 list chua het tat ca 12 element
    List<WebElement> allNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

    System.out.println("All Number = " + allNumbers.size());
    //    0-11 index
    //    1 - 2 - ...12 element value
    action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(10)).release().perform();
    sleepInSecond(3);

    List<WebElement> allNumberSelected =
        driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
    System.out.println("Selected Number = " + allNumberSelected.size());

    Assert.assertEquals(allNumberSelected.size(), 9);
  }

  @Test
  public void TC_04_Click_And_Hold_Random() {
    driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
    //    Tao ra 1 list chua het tat ca 12 element
    List<WebElement> allNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

    System.out.println("All Number = " + allNumbers.size());

    //    Nhan phim ctrl
    action.keyDown(Keys.CONTROL).perform();
    //    Click vao so: 1,3, 6, 12
    action
        .click(allNumbers.get(0))
        .click(allNumbers.get(2))
        .click(allNumbers.get(5))
        .click(allNumbers.get(11))
        .perform();
    //    Nha phim Ctrl
    action.keyUp(Keys.CONTROL).perform();

    sleepInSecond(3);

    List<WebElement> allNumberSelected =
        driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
    System.out.println("Selected Number = " + allNumberSelected.size());

    Assert.assertEquals(allNumberSelected.size(), 4);
  }

  @Test
  public void TC_05_Double_Click() {
    driver.get("https://automationfc.github.io/basic-form/index.html");
    WebElement doubleClickMe = driver.findElement(By.xpath("//button[text()='Double click me']"));
    action.doubleClick(doubleClickMe).perform();
    sleepInSecond(2);
    Assert.assertTrue(driver.findElement(By.id("demo")).isDisplayed());
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
