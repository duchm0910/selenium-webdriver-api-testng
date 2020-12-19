package webdriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Iframe_Frame {
  WebDriver driver;

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver",".\\browserDriver\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void TC_01_Iframe() {
    driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
    //    Switch vao iframe cua Facebook
    driver
        .switchTo()
        .frame(
            driver.findElement(
                By.xpath("//iframe[contains(@title,'fb:page Facebook Social Plugin')]")));

    Assert.assertEquals(
        driver.findElement(By.xpath("//a[@title='Automation FC']")).getText(), "Automation FC");
    Assert.assertEquals(
        driver
            .findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div"))
            .getText(),
        "1,949 likes");
    String likeText =
        driver
            .findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div"))
            .getText();
    int likeNumber = Integer.parseInt(likeText.split(" ")[0].replace(",", ""));
    System.out.println(likeNumber);
    assertThat(likeNumber, greaterThan(1900));

    //    Switch to Top Window
    driver.switchTo().defaultContent();

    Assert.assertEquals(
        driver.findElement(By.className("post-title")).getText(),
        "[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream)");

//    Switch to google doc iframe
    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));
    Assert.assertEquals(driver.findElement(By.cssSelector(".exportFormTitle")).getText(),"KHÓA HỌC SELENIUM AUTOMATION TESTING");


  }
  @Test
  public void TC_02_Iframe(){
    driver.get("https://kyna.vn/");
//    Switch to iframe chat
    driver.switchTo().frame("cs_chat_iframe");

    driver.findElement(By.xpath("//div[@class='meshim_widget_components_chatButton_Button ltr ng-scope']")).click();
    driver.findElement(By.xpath("//textarea[@ng-model='login.content']")).sendKeys("Automation testing");
    driver.findElement(By.xpath("//textarea[@ng-model='login.content']")).sendKeys(Keys.ENTER);
    sleepInSecond(5);

    Assert.assertTrue(driver.findElement(By.xpath("//input[@ng-model='login.username']")).isDisplayed());

//    Switch to home page
    driver.switchTo().defaultContent();

    driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
    driver.findElement(By.cssSelector(".search-button")).click();
//    driver.findElement(By.id("live-search-bar")).sendKeys(Keys.ENTER);

    List<WebElement> courseNameHeader = driver.findElements(By.cssSelector("div.content h4"));
    List<String> courseNameText = new ArrayList<String>();

    for(WebElement courseElement: courseNameHeader){
      System.out.println(courseElement.getText());
      courseNameText.add(courseElement.getText());
    }

    for (String courseName : courseNameText){
      Assert.assertTrue(courseName.contains("Excel"));
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
