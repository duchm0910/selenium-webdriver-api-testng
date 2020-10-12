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
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://live.demoguru99.com/index.php/");
    }

    @Test
    public void TC_01_Login_Empty_Email_Password() {
//        Click vào account link
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
//        Nhap vao email password
        driver.findElement(By.id("email")).sendKeys();
        driver.findElement(By.name("login[password]")).sendKeys();
//        Click vào button login
        driver.findElement(By.name("send")).click();
//        Kiem tra error message xuat hien tai email textbox
        String getErrMessEmail = driver.findElement(By.id("advice-required-entry-email")).getText();
        Assert.assertEquals(getErrMessEmail, "This is a required field.");
        //        Kiem tra error message xuat hien tai email textbox
        String getErrMessPass = driver.findElement(By.id("advice-required-entry-email")).getText();
        Assert.assertEquals(getErrMessPass, "This is a required field.");


    }

    @Test
    public void TC_02_Login_Invalid_Email() {
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
        Assert.assertEquals(getErrMessEmail, "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_Login_Invalid_Password() {
//        Click account link
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
//        nhap valid email
        driver.findElement(By.name("login[username]")).sendKeys("abc@gmail.com");
//        nhap invalid pass
        driver.findElement(By.id("pass")).sendKeys("123");
//        Click Login button
        driver.findElement(By.name("send")).click();
//        Kt message pass invalid
        String passErrMess = driver.findElement(By.id("advice-validate-password-pass")).getText();
        Assert.assertEquals(passErrMess, "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_Invalid_Login_Password() {
//        Click account link
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
//        nhap valid email
        driver.findElement(By.name("login[username]")).sendKeys("autotest435435@gmail.com");
//        nhap invalid pass
        driver.findElement(By.id("pass")).sendKeys("123456");
//        Click Login button
        driver.findElement(By.name("send")).click();
//        Kt message pass invalid
        String invalidLoginPass = driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
        Assert.assertEquals(invalidLoginPass, "Invalid login or password.");
    }

    @Test
    public void TC_05_Login_Success() {
        //        Click account link
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
//        nhap valid email
        driver.findElement(By.name("login[username]")).sendKeys("automation_13@gmail.com");
//        nhap invalid pass
        driver.findElement(By.id("pass")).sendKeys("123123");
//        Click Login button
        driver.findElement(By.name("send")).click();
//        Get text My dashboard, Hello, Automation Testing!,Automation Testing, automation_13@gmail.com
        String myDashboard = driver.findElement(By.className("page-title")).getText();
        String welmsg = driver.findElement(By.xpath("//div[@class='welcome-msg']//p[@class='hello']//strong")).getText();
        Assert.assertEquals(myDashboard,"MY DASHBOARD");
        String contactInfo = driver.findElement(By.xpath("//div[@class='box-account box-info']//div[@class='box-content']")).getText();
        Assert.assertEquals(welmsg,"Hello, Automation Testing!");
//        myDashboard + "\t" + welmsg + "\t" +
                System.out.println(contactInfo);

    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
