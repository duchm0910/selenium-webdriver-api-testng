package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Javascript_Executor_Part_II {
  WebDriver driver;
  String email, userID, password, loginPageUrl, customerID;
  String name, address, dobInput, dobOutput, city, pin, phone, state;

  By nameBy = By.name("name");
  By dobBy = By.name("dob");
  By addrBy = By.name("addr");
  By cityBy = By.name("city");
  By stateBy = By.name("state");
  By pinBy = By.name("pinno");
  By telBy = By.name("telephoneno");
  By emailBy = By.name("emailid");
  By passBy = By.name("password");

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get("http://demo.guru99.com/v4/");

    name = "Donald Trump";
    address = "123 HCM";
    dobInput = "04/03/1999";
    dobOutput = "1999-04-03";
    city = "Florida";
    pin = "700000";
    phone = "09377889910";
    state = "NY";
    email = generateEmail();
    password = "123456";

    loginPageUrl = driver.getCurrentUrl();
    driver.findElement(By.xpath("//a[text()='here']")).click();
    driver.findElement(By.name("emailid")).sendKeys(email);
    driver.findElement(By.name("btnLogin")).click();
    // Local varaiable (bien cuc bo)
    userID =
        driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
    password =
        driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();

    driver.get(loginPageUrl);
    driver.findElement(By.name("uid")).sendKeys(userID);
    driver.findElement(By.name("password")).sendKeys(password);

    driver.findElement(By.name("btnLogin")).click();
    String heading3Text = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();

    Assert.assertEquals(heading3Text, "Welcome To Manager's Page of Guru99 Bank");

  }

  @Test
  public void TC_03_New_Customer() {
    driver.findElement(By.xpath("//a[text()='New Customer']")).click();

    driver.findElement(nameBy).sendKeys(name);
    removeAttributeInDOM("//input[@id='dob']", "type");
    sleepInSecond(2);
    driver.findElement(dobBy).sendKeys(dobInput);
    sleepInSecond(2);
    driver.findElement(addrBy).sendKeys(address);
    driver.findElement(cityBy).sendKeys(city);
    driver.findElement(stateBy).sendKeys(state);
    driver.findElement(pinBy).sendKeys(pin);
    driver.findElement(telBy).sendKeys(phone);
    driver.findElement(emailBy).sendKeys(email);
    driver.findElement(passBy).sendKeys(password);
    // p[@class='heading3']
    driver.findElement(By.name("sub")).click();

    Assert.assertEquals(
        driver.findElement(By.xpath("//p[@class='heading3']")).getText(),
        "Customer Registered Successfully!!!");

    //        Verify
    Assert.assertEquals(
        driver
            .findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td"))
            .getText(),
        name);
    Assert.assertEquals(
        driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(),
        dobOutput);
    Assert.assertEquals(
        driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(),
        address);
    Assert.assertEquals(
        driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), city);
    Assert.assertEquals(
        driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(),
        state);
    Assert.assertEquals(
        driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), pin);
    Assert.assertEquals(
        driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(),
        phone);
    Assert.assertEquals(
        driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(),
        email);
  }



  public void removeAttributeInDOM(String locator, String attributeRemove) {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    WebElement element = getElement(locator);
    jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
  }
  public WebElement getElement(String xpathLocator) {
    return driver.findElement(By.xpath(xpathLocator));
  }

  public void sleepInSecond(long time) {
    try {
      Thread.sleep(time * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  public String generateEmail() {
    Random ran = new Random();
    return "test" + ran.nextInt(9999) + "@mailinator.com";
  }

  @AfterTest
  public void afterClass() {
    driver.quit();
  }
}

