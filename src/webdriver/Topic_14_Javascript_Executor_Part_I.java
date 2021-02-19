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

public class Topic_14_Javascript_Executor_Part_I {
  WebDriver driver;
  String html5Message;
  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void TC_01_Browser_Element() {
    navigateToUrlByJS("http://live.demoguru99.com/");

    String liveGuruDomain = (String) executeForBrowser("return document.domain;");
    System.out.println("Live Guru Domain: " + liveGuruDomain);
    Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
    // driver.getCurrentUrl();
    String homePageUrl = (String) executeForBrowser("return document.URL");
    System.out.println("Home Page Url: " + homePageUrl);
    Assert.assertEquals(homePageUrl, "http://live.demoguru99.com/");
    // driver.findElement().click();
    highlightElement("//a[text()='Mobile']");
    clickToElementByJS("//a[text()='Mobile']");
    clickToElementByJS(
        "//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
    // driver.findElement().getText
    String innerText = getInnerText();
    Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));
    Assert.assertTrue(
        areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

    highlightElement("//a[text()='Customer Service']");
    clickToElementByJS("//a[text()='Customer Service']");
    String customerServiceTitle = (String) executeForBrowser("return document.title;");
    System.out.println("Customer service titlel = " + customerServiceTitle);
    Assert.assertEquals(customerServiceTitle, "Customer Service");

    highlightElement("//input[@id='newsletter']");
    scrollToElement("//input[@id='newsletter']");
    sleepInSecond(2);

    highlightElement("//input[@id='newsletter']");
    sendkeyToElementByJS("//input[@id='newsletter']", generateEmail());
    sleepInSecond(3);

    highlightElement("//button[@title='Subscribe']");
    clickToElementByJS("//button[@title='Subscribe']");
    Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

    navigateToUrlByJS("https://demo.guru99.com/v4/");

    Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");
  }
  @Test
  public void TC_02_HTML_5_Validation(){
    driver.get("https://automationfc.github.io/html5/index.html");
    driver.findElement(By.name("submit-btn")).click();

    html5Message = getElementValidationMessage("//input[@id='fname']");
    System.out.println(html5Message);
    Assert.assertEquals(html5Message,"Please fill out this field.");

    driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("Duc hoang");
    driver.findElement(By.name("submit-btn")).click();

    html5Message = getElementValidationMessage("//input[@id='pass']");
    System.out.println(html5Message);
    Assert.assertEquals(html5Message,"Please fill out this field.");
    sleepInSecond(2);

    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");

    driver.findElement(By.name("submit-btn")).click();

    html5Message = getElementValidationMessage("//input[@id='em']");
    System.out.println(html5Message);
    Assert.assertEquals(html5Message,"Please fill out this field.");

    driver.findElement(By.xpath("//input[@id='em']")).sendKeys("abc123");
    driver.findElement(By.name("submit-btn")).click();
    sleepInSecond(2);

    driver.findElement(By.xpath("//input[@id='em']")).clear();
    driver.findElement(By.xpath("//input[@id='em']")).sendKeys("duc@gmail.com");
    driver.findElement(By.name("submit-btn")).click();
    sleepInSecond(2);

    html5Message = getElementValidationMessage("//select");
    Assert.assertEquals(html5Message,"Please select an item in the list.");
    System.out.println(html5Message);
  }
  @Test
  public void TC_03_Hidden_Element(){
    driver.get("http://live.demoguru99.com/");
    clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
    sleepInSecond(3);
  }

  public Object executeForBrowser(String javaScript) {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    return jsExecutor.executeScript(javaScript);
  }

  public String getInnerText() {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
  }

  public boolean areExpectedTextInInnerText(String textExpected) {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    String textActual =
        (String)
            jsExecutor.executeScript(
                "return document.documentElement.innerText.match('" + textExpected + "')[0]");
    return textActual.equals(textExpected);
  }

  public void scrollToBottomPage() {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
  }

  public void navigateToUrlByJS(String url) {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    jsExecutor.executeScript("window.location = '" + url + "'");
  }

  public void highlightElement(String locator) {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    WebElement element = getElement(locator);
    String originalStyle = element.getAttribute("style");
    jsExecutor.executeScript(
        "arguments[0].setAttribute(arguments[1], arguments[2])",
        element,
        "style",
        "border: 2px solid red; border-style: dashed;");
    sleepInSecond(1);
    jsExecutor.executeScript(
        "arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
  }

  public void clickToElementByJS(String locator) {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    WebElement element = getElement(locator);
    jsExecutor.executeScript("arguments[0].click();", element);
  }

  public void scrollToElement(String locator) {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    WebElement element = getElement(locator);
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
  }

  public void sendkeyToElementByJS(String locator, String value) {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    WebElement element = getElement(locator);
    jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
  }

  public void removeAttributeInDOM(String locator, String attributeRemove) {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    WebElement element = getElement(locator);
    jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
  }

  public boolean areJQueryAndJSLoadedSuccess() {
    WebDriverWait explicitWait = new WebDriverWait(driver, 30);
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

    ExpectedCondition<Boolean> jQueryLoad =
        new ExpectedCondition<Boolean>() {
          @Override
          public Boolean apply(WebDriver driver) {
            try {
              return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
              return true;
            }
          }
        };

    ExpectedCondition<Boolean> jsLoad =
        new ExpectedCondition<Boolean>() {
          @Override
          public Boolean apply(WebDriver driver) {
            return jsExecutor
                .executeScript("return document.readyState")
                .toString()
                .equals("complete");
          }
        };

    return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
  }

  public String getElementValidationMessage(String locator) {
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    WebElement element = getElement(locator);
    return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
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
