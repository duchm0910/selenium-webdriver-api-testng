package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_Part_IV {
  WebDriver driver;

  @BeforeClass
  public void beforeClass(){
    driver = new FirefoxDriver();
  }
  @Test
  public void TC_01_Equal() throws InterruptedException {
    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

//    Click to START button
    driver.findElement(By.xpath("//div[@id='start']/button")).click();
    Thread.sleep(5500);


//    Verify text
    Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
  }
  @Test
  public void TC_02_Greater() throws InterruptedException {
    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

//    Click to START button
    driver.findElement(By.xpath("//div[@id='start']/button")).click();
    Thread.sleep(15500);


//    Verify text
    Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
  }
  @Test
  public void TC_03_Smaller() throws InterruptedException {
    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

//    Click to START button
    driver.findElement(By.xpath("//div[@id='start']/button")).click();
    Thread.sleep(2500);


//    Verify text
    Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
  }

  @AfterClass
  public void afterClass(){
    driver.quit();
  }

}
