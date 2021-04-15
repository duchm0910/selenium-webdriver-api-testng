package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_Part_II {
  WebDriver driver;

  @BeforeClass
  public void beforeClass() {
    driver = new FirefoxDriver();
    // Timeout nay duoc apply duy nhat cho viec tim element (findElement/findElements)
    //    Neu nhu ko set thi timeout = 0
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();

    driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
  }
  /*
  * findElement & findElements
  *- Tìm element nhưng ko thấy element nào hết -> 0 matching node
  -> Kết quả trả về là gì?
  *- Tìm element nhưng chỉ có duy nhất 1 cái -> 1 matching node
  -> Kết quả trả về là gì?
  *- Tìm element nhưng thấy nhiều hơn 1 cái -> n matching nodes
  -> Kết quả trả về là gì?

  2 hàm này đều bị ảnh hưởng bởi timeout của implicitWait
  * */

  @Test
  public void TC_01_Find_Element() {
    //    tra ve (return) 1 element(Web Element)

    //    Tìm element nhưng ko thấy element nào hết -> 0 matching node
    //    Moi nua s se tim lai 1 lan cho den khi het timeout thi thoi
    //    neu nhu het timeout ma van ko thay element thi:
    //    + Danh fail testcase
    //    + Throw 1 exception: NoSuchElement - Unable to locate element with...
    //    Trong thoi gian dang tim neu element xuat hien thi pass dc dieu kien(findElement)
    //    Va ko can phai cho cho den het timeout

    /*    if(driver.findElement(By.xpath("//input[@id='FirstName']")).isDisplayed()){
      System.out.println("Go to if");
    }else {
      System.out.println("Go to else");
    }*/
    //    Tìm element nhưng chỉ có duy nhất 1 cái -> 1 matching node
    //    Tim thay element thay ngay -> Retunr lai element do->
    boolean status = driver.findElement(By.xpath("//input[@id='Email']")).isDisplayed();
    if (status) {
      System.out.println("Go to if");
    } else {
      System.out.println("Go to else");
    }
    //    Tìm element nhưng thấy nhiều hơn 1 cái -> n matching nodes
    //    Tim thay element ngay -> Return lai element dau tien
    driver.findElement(By.xpath("//a[@title='My Account']")).sendKeys("testing@gmail.com");
  }

  @Test
  public void TC_02_Find_Elements() {
    //    tra ve (return) >=1 element(List<WebElement>)

    //    Tìm element nhưng ko thấy element nào hết -> 0 matching node
    //    Moi nua s se tim lai 1 lan cho den khi het timeout thi thoi
    //    Trong thoi gian dang tim neu element xuat hien thi pass dc dieu kien(findElement)
    //    Va ko can phai cho cho den het timeout
    //    neu nhu het timeout ma van ko thay element thi:
    //    + Ko Danh fail testcase
    //    + Tra ve 1 list empty (ko chua element nao het)
    List<WebElement> radioButton = driver.findElements(By.xpath("//input[@type='radio']"));
    System.out.println("Size: " + radioButton.size());
    Assert.assertTrue(radioButton.isEmpty());

    //    Tìm element nhưng chỉ có duy nhất 1 cái -> 1 matching node
    //    tim thay element ngay nhung ket qua tra ve la 1 list chua element (1 element)
    List<WebElement> emailTextbox = driver.findElements((By.xpath("//input[@id='Email']")));
    System.out.println("Size: " + emailTextbox.size());
    Assert.assertFalse(emailTextbox.isEmpty());
    emailTextbox.get(0).sendKeys("duc.hm0910@gmail.com");
    //    Tìm element nhưng thấy nhiều hơn 1 cái -> n matching nodes
//can thao tac vs nhieu field/ elements
    List<WebElement> textBoxes = driver.findElements(By.xpath("//input[@type='text]"));
    System.out.println("Size = "+textBoxes.size());
    for(WebElement textBox:textBoxes){
      textBox.sendKeys("List WebElement");
    }
  }

  @AfterClass
  public void afterClass() {
    driver.quit();
  }
}
