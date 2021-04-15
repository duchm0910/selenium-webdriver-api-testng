package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_Part_V {
  WebDriver driver;
  WebDriverWait explicitWait;
  @BeforeClass
  public void beforeClass(){
    driver = new FirefoxDriver();
    explicitWait = new WebDriverWait(driver, 15);
//    neu nhu chua thoa man dieu kien thi co co che lap lap de tim/ wait trong vong moi half s
//    neu nhu thoa  man dieu kien roi thi ko can phai cho het timeout nua
  }
  @Test
  public void TC_01_Clickable_Invisible()  {
    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
//    Cho cho Start button duoc click hay chua (Button bi disable va no se duoc trigger 1 su kien nao do)
    explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='start']/button")));

//    Click to START button
    driver.findElement(By.xpath("//div[@id='start']/button")).click();

//Wait for Loading Icon invisible
    explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));

//    Verify text
    Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
  }

  @Test
  public void TC_02_Clickable_Visible()  {
    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
//    Cho cho Start button duoc click hay chua (Button bi disable va no se duoc trigger 1 su kien nao do)
    explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='start']/button")));

//    Click to START button
    driver.findElement(By.xpath("//div[@id='start']/button")).click();

//Wait for HelloWorld Text visible
    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));

//    Verify text
    Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
  }



  @AfterClass
  public void afterClass(){
    driver.quit();
  }

}
