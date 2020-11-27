package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction_Part_II {

  WebDriver driver;
  Actions actions;
  JavascriptExecutor jsExecutor;
  String project_Location = System.getProperty("user.dir");
  String jsHelperPath = project_Location +"\\dragAndDrop\\drag_and_drop_helper.js";

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    actions = new Actions(driver);
    jsExecutor = (JavascriptExecutor)driver;

    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void TC_01_Right_Click() {
    driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

    actions
        .contextClick(driver.findElement(By.xpath("//span[text()='right click me']")))
        .perform();
    sleepInSecond(2);
    //    Verify Quit not contain(visible/ hover status)
    Assert.assertTrue(
        driver
            .findElement(
                By.xpath(
                    "//li[contains(@class,'context-menu-icon-quit')and not(contains(@class,'context-menu-visible'))"
                        + "and not(contains(@class,'context-menu-hover'))]"))
            .isDisplayed());
    //    Hover to Quit
    actions
        .moveToElement(
            driver.findElement(
                By.xpath(
                    "//li[contains(@class,'context-menu-icon-quit')and not(contains(@class,'context-menu-visible'))"
                        + "and not(contains(@class,'context-menu-hover'))]")))
        .perform();
    sleepInSecond(2);

    //    Verify Quit contain(visible/ hover status)
    Assert.assertTrue(
        driver
            .findElement(
                By.xpath(
                    "//li[contains(@class,'context-menu-icon-quit') and (contains(@class,'context-menu-visible')) "
                        + "and (contains(@class,'context-menu-hover'))]"))
            .isDisplayed());

    //    Click to Quit
    driver
        .findElement(
            By.xpath(
                "//li[contains(@class,'context-menu-icon-quit') and (contains(@class,'context-menu-visible')) "
                    + "and (contains(@class,'context-menu-hover'))]"))
        .click();
    sleepInSecond(2);
    //    Verify alert displayed
    Assert.assertEquals(driver.switchTo().alert().getText(),"clicked: quit");
    driver.switchTo().alert().accept();
  }

  @Test
  public void TC_02_Drag_Drop_HTML4(){
    driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");

    driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();
    sleepInSecond(3);
    WebElement sourceCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
    WebElement targetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));

    actions.dragAndDrop(sourceCircle,targetCircle).perform();

    Assert.assertEquals(targetCircle.getText(),"You did great!");

  }
  @Test
  public void TC_03_Drag_Drop_HTML5_Jquery() throws IOException {
    driver.get("https://automationfc.github.io/drag-drop-html5/");

String sourceRetangleCss = "#column-a";
String targetRetangleCss = "#column-b";

    String jsHelperContent = getJSFileContent(jsHelperPath);
    sleepInSecond(2);

//    Drag A to B
    jsHelperContent = jsHelperContent + "$(\"" + sourceRetangleCss + "\").simulateDragDrop({ dropTarget: \"" + targetRetangleCss + "\"});";
    jsExecutor.executeScript(jsHelperContent);

    Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

//    Drag B to A
    jsExecutor.executeScript(jsHelperContent);
    sleepInSecond(2);
    Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());


  }
  @Test
  public void TC_04_Drag_Drop_HTML5_Position() throws AWTException {
    driver.get("https://automationfc.github.io/drag-drop-html5/");
    sleepInSecond(3);

//    A to B
    dragAndDropHtml5ByXpath("//div[@id='column-a']","//div[@id='column-b']");
    sleepInSecond(3);
    Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

//    B to A
    dragAndDropHtml5ByXpath("//div[@id='column-a']","//div[@id='column-b']");
    sleepInSecond(3);
    Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
  }

  @AfterTest
  public void afterTest() {
    driver.quit();
  }

  public void sleepInSecond(long timeInSecond) {
    try {
      Thread.sleep(timeInSecond * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public String getJSFileContent(String file) throws IOException {
    Charset cs = Charset.forName("UTF-8");
    FileInputStream stream = new FileInputStream(file);
    try{
      Reader reader = new BufferedReader(new InputStreamReader(stream,cs));
      StringBuilder builder = new StringBuilder();
      char[] buffer = new char[8192];
      int read;
      while ((read = reader.read(buffer,0,buffer.length))>0){
        builder.append(buffer,0,read);
      }
      return  builder.toString();
    }finally{
      stream.close();
    }
  }

  public void dragAndDropHtml5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

    WebElement source = driver.findElement(By.xpath(sourceLocator));
    WebElement target = driver.findElement(By.xpath(targetLocator));

    // Setup robot
    Robot robot = new Robot();
    robot.setAutoDelay(500);

    // Get size of elements
    Dimension sourceSize = source.getSize();
    Dimension targetSize = target.getSize();

    // Get center distance
    int xCentreSource = sourceSize.width / 2;
    int yCentreSource = sourceSize.height / 2;
    int xCentreTarget = targetSize.width / 2;
    int yCentreTarget = targetSize.height / 2;

    Point sourceLocation = source.getLocation();
    Point targetLocation = target.getLocation();
    System.out.println(sourceLocation.toString());
    System.out.println(targetLocation.toString());

    // Make Mouse coordinate center of element
    sourceLocation.x += 20 + xCentreSource;
    sourceLocation.y += 110 + yCentreSource;
    targetLocation.x += 20 + xCentreTarget;
    targetLocation.y += 110 + yCentreTarget;

    System.out.println(sourceLocation.toString());
    System.out.println(targetLocation.toString());

    // Move mouse to drag from location
    robot.mouseMove(sourceLocation.x, sourceLocation.y);

    // Click and drag
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

    // Move to final position
    robot.mouseMove(targetLocation.x, targetLocation.y);

    // Drop
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
  }

}
