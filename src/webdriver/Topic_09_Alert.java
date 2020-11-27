package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {
  WebDriver driver;
  Alert alert;
  WebDriverWait expliciWait;
  By resultText = By.xpath("//p[@id='result']");
  String userName = "admin";
  String password = "admin";
  String project_location = System.getProperty("user.dir");
  String firefoxAuthenFile = project_location + "\\autoIT\\authen_firefox.exe";

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    expliciWait = new WebDriverWait(driver, 30);

    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void TC_01_Accept_Alert() {
    driver.get("https://automationfc.github.io/basic-form/index.html");

    driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

    //        Cho cho alert xuat hien
    expliciWait.until(ExpectedConditions.alertIsPresent());

    sleepInSecond(2);

    alert = driver.switchTo().alert();

    Assert.assertEquals(alert.getText(), "I am a JS Alert");

    alert.accept();

    Assert.assertEquals(
        driver.findElement(resultText).getText(), "You clicked an alert successfully");

    /*
    //        Switch vao alert
            alert = driver.switchTo().alert();
    //        Accept
            alert.accept();
    //        Cancel
            alert.dismiss();
    //        senkey
            alert.sendKeys("");
    //        gettext
            alert.getText();
            */
  }

  @Test
  public void TC_02_Confirm_Alert() {
    driver.get("https://automationfc.github.io/basic-form/index.html");

    driver.findElement(By.xpath(" //button[text()='Click for JS Confirm']")).click();

    //        Cho alert xuat hien
    expliciWait.until(ExpectedConditions.alertIsPresent());

    //        Switch qua Alert
    alert = driver.switchTo().alert();

    sleepInSecond(3);
    Assert.assertEquals(alert.getText(), "I am a JS Confirm");

    alert.dismiss();
    sleepInSecond(2);

    Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");
  }

  @Test
  public void TC_03_Promt_Alert() {
    String loginValue = "Automation Testing";

    driver.get("https://automationfc.github.io/basic-form/index.html");

    driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

    //        Cho alert xuat hien
    expliciWait.until(ExpectedConditions.alertIsPresent());

    //        Switch to alert
    alert = driver.switchTo().alert();

    alert.sendKeys(loginValue);
    sleepInSecond(3);

    Assert.assertEquals(alert.getText(), "I am a JS prompt");
    alert.accept();
    sleepInSecond(3);

    Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + loginValue);
  }

  @Test
  public void TC_04_Authentication_Alert() {

    driver.get("https://" + userName + ":" + password + "@the-internet.herokuapp.com/basic_auth");

    Assert.assertTrue(
        driver
            .findElement(
                By.xpath(
                    "//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
            .isDisplayed());
  }

  @Test
  public void TC_05_Authentication_Alert() {
    driver.get("http://the-internet.herokuapp.com/");

    String basicAuthenLink =
        driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
    System.out.println(basicAuthenLink);

    //        Input: http://the-internet.herokuapp.com/basic_auth
    //        Ouput: http://admin:admin@the-internet.herokuapp.com/basic_auth
    driver.get(getAuthenticationUrl(basicAuthenLink, userName, password));

    Assert.assertTrue(
        driver
            .findElement(
                By.xpath(
                    "//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
            .isDisplayed());
  }

  @Test
  public void TC_06_Authentication_Alert_Auto_IT() throws IOException {

    //        expliciWait.until(ExpectedConditions.alertIsPresent());

    Runtime.getRuntime().exec(new String[] {firefoxAuthenFile, userName, password});
    driver.get("http://the-internet.herokuapp.com/basic_auth");

    Assert.assertTrue(
        driver
            .findElement(
                By.xpath(
                    "//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
            .isDisplayed());
  }

  public String getAuthenticationUrl(String url, String username, String password) {
    //        split (tach chuoi)
    String urlValue[] = url.split("//");

    url = urlValue[0] + "//" + username + ":" + password + "@" + urlValue[1];

    return url;
  }

  public void sleepInSecond(long timeInSecond) {
    try {
      Thread.sleep(timeInSecond * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @AfterTest
  public void afterClass() {
    driver.quit();
  }
}
