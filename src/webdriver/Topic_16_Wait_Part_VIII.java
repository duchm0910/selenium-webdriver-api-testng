package webdriver;

import com.google.common.base.Function;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
//import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_Part_VIII {
  WebDriver driver;
  FluentWait<WebDriver> fluentDriver;
  FluentWait<WebElement> fluentElement;
  String projectLocation = System.getProperty("user.dir");

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();

    driver.manage().window().maximize();
  }

  @Test
  public void TC_01() {
    driver.get("https://automationfc.github.io/fluent-wait/");
    WebElement countdownTime =
        driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));

    fluentElement = new FluentWait<WebElement>(countdownTime);
    fluentElement.withTimeout(15, TimeUnit.SECONDS)
        .pollingEvery(1,TimeUnit.SECONDS)
        .ignoring(NoSuchElementException.class);

    fluentElement.until(new Function<WebElement, Boolean>() {
          @Override
          public Boolean apply(WebElement countdownTime) {
            String text = countdownTime.getText();
            System.out.println(text);
            return text.endsWith("00");
          }
        });
  }
  @Test
  public void TC_02(){
    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
    waitForElementAndClick(By.xpath("//div[@id='start']/button"));
    Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='finish']/h4")));
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }

  public WebElement getElement(By locator){
    FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(timeoutInSecond, TimeUnit.SECONDS)
        .pollingEvery(intervalInMillisecond,TimeUnit.MILLISECONDS)
        .ignoring(NoSuchElementException.class);
    WebElement element = wait.until(new Function<WebDriver, WebElement>() {
      @Override
      public WebElement apply(WebDriver driver) {
        return driver.findElement(locator);
      }
    });
    return  element;
  }

  public void waitForElementAndClick(By locator){

    WebElement element = getElement(locator);
    element.click();
  }

  public boolean isElementDisplayed(By locator){
    WebElement element = getElement(locator);
    FluentWait<WebElement> wait = new FluentWait<WebElement>(element);
    boolean isDisplayed = wait.until(new Function<WebElement, Boolean>() {
      @Override
      public Boolean apply(WebElement element) {
        boolean flag = element.isDisplayed();
        return flag;
      }
    });
    return isDisplayed;
  }

  long timeoutInSecond = 15;
  long intervalInMillisecond = 300;
}
