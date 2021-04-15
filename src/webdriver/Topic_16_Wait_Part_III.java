package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_Part_III {
  WebDriver driver;

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    // Timeout nay duoc apply duy nhat cho viec tim element (findElement/findElements)
    //    Neu nhu ko set thi timeout = 0
//    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    //    driver.manage().window().maximize();

    //    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
//    driver.get("http://blueimp.github.io/jQuery-File-Upload/");
  }

  @Test
  public void TC_01() {
    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    //    Click START button
    driver.findElement(By.xpath("//div[@id='start']/button")).click();


    //    Verify Text => Fail
    Assert.assertEquals(
        driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
  }

  @Test
  public void TC_02() {
    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

    //    Click START button
    driver.findElement(By.xpath("//div[@id='start']/button")).click();


    //    Verify Text => Fail
    Assert.assertEquals(
        driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
/*    driver
        .findElement(By.xpath("//input[@type='file']"))
        .sendKeys(
            "E:\\Selenium Online 18\\02 - Selenium API\\selenium-webdriver-api-testing\\uploadFile\\Background.jpg");
    driver.findElement(By.cssSelector("table .start")).click();
//    lam sao de wait cho no upload thanh cong
    Assert.assertTrue(driver.findElement(By.xpath("//p/a[@title='Background.jpg']")).isDisplayed());*/
  }
  @Test
  public void TC_03(){
    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    //    Click START button
    driver.findElement(By.xpath("//div[@id='start']/button")).click();


    //    Verify Text => Fail
    Assert.assertEquals(
        driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
  }

  @AfterClass
  public void afterClass() {

        driver.quit();
  }
}
