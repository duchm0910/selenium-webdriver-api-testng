package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part_I_Locator {
  WebDriver driver;

  @Test
  public void TC_01() {
    // 1. Mo trinh duyet
    driver = new FirefoxDriver();
    //        2. Nhap http facebook
    driver.get("https://www.facebook.com/");
    //        3. Nhap vao text box
    //          Action: nhap/chon/hover/ drag_drop/ get text/......
    //        tim element: findElement (so it): 1 thang
    //        tim element: findElement (so nhieu): n thang

    //        Id
    driver.findElement(By.id("email")).sendKeys("acb@gmail.com");
    //        Class
    driver.findElement(By.className("_featuredLogin__formContainer")).isDisplayed();
    //        Name
    driver.findElement(By.name("pass")).sendKeys("123456");
    //        Tag name
    int linkNumber = driver.findElements(By.tagName("a")).size();
    System.out.println("link number: " + linkNumber);
    //          Link Text (link -a )
    driver.findElement(By.linkText("English (UK)")).click();
    //        Partial Link text (link -a)
    driver.findElement(By.partialLinkText("Việt")).click();
    //        Css
    driver.findElement(By.cssSelector("#email")).sendKeys("abc@gmail.com");
    driver.findElement(By.cssSelector("input[id='email']")).clear();
    driver.findElement(By.cssSelector("input[name='email']")).sendKeys("ABC@gmail.com");
    //        Xpath
    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys();
    driver.findElement(By.xpath("//input[@name='pass']")).sendKeys();
  }

  public void TC_02() {}
}
