package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Upload_File_I {
  WebDriver driver;
  String projectLocation = System.getProperty("user.dir");
  String firstFileName = "Despicable-Me-2-Minions.jpg";
  String secondFileName = "Background.jpg";
  String thirdFileName = "Sunflower_from_Silesia2.jpg";
  String firstFileNamePath = projectLocation+"\\uploadfile\\"+ firstFileName;
  String secondFileNamePath = projectLocation + "\\uploadfile\\" + secondFileName;
  String thirdFileNamePath = projectLocation + "\\uploadfile\\" + thirdFileName;
  String documentFilePath = projectLocation + "\\uploadfile\\Travel Expenses.pdf";

  @BeforeClass
  public void beforeClass() {
//    System.setProperty("webdriver.chrome.driver","E:\\Selenium Online 18\\02 - Selenium API\\selenium-webdriver-api-testing\\browserDriver\\chromedriver.exe");
//    driver = new ChromeDriver();
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void TC_01_Sendkey_One_File_Per_Time() {
    driver.get("http://blueimp.github.com/jQuery-File-Upload/");

    //    Work with element(//input[@type='file']
    driver.findElement(By.xpath("//input[@type='file']")).sendKeys(firstFileNamePath);
    driver.findElement(By.xpath("//input[@type='file']")).sendKeys(secondFileNamePath);
    driver.findElement(By.xpath("//input[@type='file']")).sendKeys(thirdFileNamePath);
    //    Verify file loaded success
    Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ firstFileName +"']")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+secondFileName+"']")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+thirdFileName+"']")).isDisplayed());

//    Click Start upload each file
    List<WebElement> startUpload = driver.findElements(By.cssSelector("table .start"));
    for(WebElement startButton:startUpload){
      startButton.click();
      sleepInSecond(1);
    }
//    Verify file uploaded success
    Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ firstFileName +"']")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+secondFileName+"']")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+thirdFileName+"']")).isDisplayed());
  }

  @Test
  public void TC_02_Sendkey_Multi_File_Per_Time() {
    driver.get("http://blueimp.github.com/jQuery-File-Upload/");

    //    Work with element(//input[@type='file']
    driver.findElement(By.xpath("//input[@type='file']")).sendKeys(firstFileNamePath+"\n"+secondFileNamePath+"\n"+thirdFileNamePath);

    //    Verify file loaded success
    Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+ firstFileName +"']")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+secondFileName+"']")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+thirdFileName+"']")).isDisplayed());

//    Click Start upload each file
    List<WebElement> startUpload = driver.findElements(By.cssSelector("table .start"));
    for(WebElement startButton:startUpload){
      startButton.click();
      sleepInSecond(1);
    }
//    Verify file uploaded success
    Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ firstFileName +"']/img")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+secondFileName+"']/img")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+thirdFileName+"']/img")).isDisplayed());
//    Verify image not broken
    Assert.assertTrue(isImageLoadedSuccess("//a[@title='"+firstFileName +"']"));
    Assert.assertTrue(isImageLoadedSuccess("//a[@title='"+secondFileName +"']"));
    Assert.assertTrue(isImageLoadedSuccess("//a[@title='"+thirdFileName +"']"));
  }

  @Test
  public void TC_03_Sendkey_Google_Translate(){
    driver.get("https://translate.google.com/?hl=en&tab=TT&sl=en&tl=vi&op=docs");

    driver.findElement(By.xpath("//input[@name='file']")).sendKeys(documentFilePath);
  }

  @Test
  public void TC_05_Java_Robot() throws AWTException {
    driver.get("http://blueimp.github.com/jQuery-File-Upload/");
    driver.findElement(By.cssSelector(".fileinput-button")).click();

//    Specify the file location with extension
    StringSelection select = new StringSelection(firstFileNamePath);

//    Copy to clipboard
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

    Robot robot = new Robot();
    sleepInSecond(1);

//    Nhan phim ENTER
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);

//    Nhan xuong Ctrl-V
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);

//    Nha Ctrl-V
    robot.keyRelease(KeyEvent.VK_CONTROL);
    robot.keyRelease(KeyEvent.VK_V);

    //    Nhan phim ENTER
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    sleepInSecond(3);
  }
  @AfterClass
  public void afterClass(){
    driver.quit();
  }

  public boolean isImageLoadedSuccess(String locator){
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    WebElement element = driver.findElement(By.xpath(locator));
  return(boolean) jsExecutor.executeScript("return arguments[0].complete && typeof"+"arguments[0].naturalWidth != 'undefined && arguments[0].naturalWidth > 0", element);
  }

  public void sleepInSecond(long timeInSecond){
    try{
      Thread.sleep(timeInSecond*1000);
    }catch (InterruptedException e){
      e.printStackTrace();
    }
  }
}
