package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Xpath_Css_Part_II_FindElement {
    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://live.demoguru99.com/index.php/");
    }
    @Test
    public void TC_01_Login_Empty_Email_Password(){
//        Click vào account link
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
//        Nhap vao email password
        driver.findElement(By.id("email")).sendKeys();
        driver.findElement(By.name("login[password]")).sendKeys();
//        Click vào button login
        driver.findElement(By.name("send")).click();
//        Kiem tra error message xuat hien tai email textbox
        String getErrMessEmail = driver.findElement(By.id("advice-required-entry-email")).getText();
        Assert.assertEquals(getErrMessEmail,"This is a required field.");
        //        Kiem tra error message xuat hien tai email textbox
       String getErrMessPass = driver.findElement(By.id("advice-required-entry-email")).getText();
        Assert.assertEquals(getErrMessPass,"This is a required field.");


    }
    @Test
    public void TC_02_Login_Invalid_Email(){
//        Click vào account link
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

//        Nhap invalid email
        driver.findElement(By.id("email")).sendKeys("123123@123123");
//        Nhap valid password
        driver.findElement(By.name("login[password]")).sendKeys("123456");
//        click button login
        driver.findElement(By.name("send")).click();
//        Kt message invalid email
        String getErrMessEmail = driver.findElement(By.id("advice-validate-email-email")).getText();
        Assert.assertEquals(getErrMessEmail,"Please enter a valid email address. For example johndoe@domain.com.");



    }
    @Test
    public void TC_03_Login_Invalid_Password(){

    }
    @Test
    public void TC_04_(){

    }
@AfterTest
    public void afterTest(){
        driver.quit();
}
}
