package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_Part_I {
  WebDriver driver;
  WebDriverWait explicitWait;

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    explicitWait = new WebDriverWait(driver, 10);

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }
//Trang thai cua element
  @Test
  public void TC_01_DisplayedVisible() {
    driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    //    Wait cho element hien thi/ Visible
    //    Co hien thi tren UI (User Interface)
    //    Co trong DOM (HTML)
    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
//Failed: trong vong 10s
    explicitWait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@id='create_account_error']")));
  }

  @Test
  public void TC_02_Undisplayed_Invisible_In_DOM() {
    //    Wait cho element khong hien thi (undisplayed/ invisible)
    //    Khong hien thi tren UI - nguoi dung ko nhin thay va thao tac duoc
    //    TH1: Element van co trong DOM
    //    Tim element dau tien: Tim thay ngay va apply dieu kien (invisibility) luon
    //    Pass dieu kien luon ko can cho het timeout
    explicitWait.until(
        ExpectedConditions.invisibilityOfElementLocated(
            By.xpath("//div[@id='create_account_error']")));
//    Failed
    explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("email")));
  }

  @Test
  public void TC_03_Undisplayed_Invisible_Out_DOM() {
    //    Wait cho element khong hien thi (undisplayed/ invisible)
    //    Khong hien thi tren UI - nguoi dung ko nhin thay va thao tac duoc
    //    TH2: Element ko co trong DOM
    //    Tim element dau tien: ko tim thay element -> tim nhieu lan cho den khi het timeout cua
    // implicit -> 10s
    //    Sau khi het implicit moi apply dieu kien vao (invisibility)-> Pass
    explicitWait.until(
        ExpectedConditions.invisibilityOfElementLocated(
            By.xpath("//div[@id='edit_account_error']")));
  }

  @Test
  public void TC_04_Presence() {
    //    Wait cho element co trong DOM
    //    TH1: Element co trong DOM + hien thi tren UI
    explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
    //    TH2: Element co tromg DOM + KO hien thi tren UI

    explicitWait.until(
        ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[@id='create_account_error']")));
//Failed
    explicitWait.until(
        ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[@id='edit_account_error']")));
  }
  @Test
  public void TC_05_Clickable(){
    driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
//BUtton/Radio/Checkbox/Link/Dropdown/ -> Stable truoc khi thao tac
    explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitLogin")));

    driver.get("https://login.mailchimp.com/signup/");

    driver.findElement(By.id("email")).sendKeys("automationfc1.vn@gmail.com");
    driver.findElement(By.id("new_username")).sendKeys("automationfc");
    driver.findElement(By.id("new_password")).sendKeys("Passwo!@d1234");
//    failed in 10s
    explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("create-account")));
    driver.findElement(By.id("create-account")).click();
  }
  @Test
  public void TC_06_Staleness(){
    driver.get("https://shopee.vn/");
//    Wait cho 1 element Staleness:
//    Khong co/ con o trong DOM
//    Step 1 - Thao tac vs 1 element -> Error message xuat hien (*) - hien thi

    explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//img[@alt='home_popup_banner']")));
    WebElement popup =driver.findElement(By.xpath("//img[@alt='home_popup_banner']"));
//    Step 2 - Thao tac vs ... -> (*) ko con xuat hien
    driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
//    Step 3 - Can cho cho (*) ko con trong DOM nua
    explicitWait.until(ExpectedConditions.stalenessOf(popup));
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
