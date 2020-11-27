package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Webdriver_Element_Part_I {
  WebDriver driver;
  WebElement element;
  List<WebElement> elements;
  List<String> address;
  List<Integer> price;

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void TC_01_Web_Element_Command() {
    /*//        Email textbox
            driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abc@gmail.com");
    //        Password textbox
            driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");

            driver.findElement(By.xpath("//input[@id='pass']"));

    //Kiểu dữ liệu: byte, short, int, long, float, double, boolean, char => kiểu dữ liệu nguyên thủy
    //Kiểu dữ liệu: String, Class, Object, Interface, Array, Collection (List/Set), Map => kiểu dữ liệu tham chi
            */
    //        Textbox/ TextArea/ Dropdown (Editable)
    //        Xoa du lieu trong field
    element.clear();
    //        Nhap du lieu
    element.sendKeys("");
    //        Cho phep tim element (1)
    element = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
    //        Cho phep tim nhieu elements
    elements = driver.findElements(By.xpath(""));

    WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='search'"));
    searchTextBox.getAttribute("placeholder");
    //        Search entire store here....
    //        GUI: font/ size/ color/ location/ position/...
    WebElement subcribeButton = driver.findElement(By.xpath("//button[@title='Subscibe]"));
    subcribeButton.getCssValue("background");
    subcribeButton.getCssValue("font-family");
    subcribeButton.getCssValue("font-size");
    subcribeButton.getCssValue("text-align");
    //       Report HTML: ReportNG/ ExtentReport/ AllureReport
    //        element.getScreenshotAs(target);

    //        A
    WebElement subcribeTextbox = driver.findElement(By.cssSelector("#newsletter"));
    String subcribeTagname = subcribeTextbox.getTagName();
    searchTextBox.getTagName();
    //      B
    driver.findElement(By.xpath("//" + subcribeTagname + "[@id='search']"));
    element.getText();

    //        Kiem tra element co hien thi hay ko
    //        hien thi = true
    //        ko = false
    element.isDisplayed();
    Assert.assertTrue(element.isDisplayed());

    //        ko bi disbale = enable
    Assert.assertTrue(element.isEnabled());
    //        dg bi disable
    Assert.assertFalse(element.isEnabled());
    //        duoc chon hay chua: checkbox/ radio
    //        dropdown (item) -> thu vien rieng  = class rieng = select (selenium)
    Assert.assertTrue(element.isSelected());

    //        Checkbox/ radio/ button/ image/ link....
    element.click();
    //        from
    element.submit();
  }
}
