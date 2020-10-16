package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_02_Xpath_Css_Part_II_FindElement {
    WebDriver driver;
    Random rand;
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        rand = new Random();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://live.demoguru99.com/index.php/");

        emailAddress = "autotest" + rand.nextInt(999) + "@automation.vn";
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
        Assert.assertEquals(myDashboard, "MY DASHBOARD");
        String contactInfo = driver.findElement(By.xpath("//div[@class='box-account box-info']//div[@class='box-content']")).getText();
        Assert.assertEquals(welmsg, "Hello, Automation Testing!");
//        myDashboard + "\t" + welmsg + "\t" +
        System.out.println(contactInfo);

    }

    @Test
    public void TC_06_Register_To_System() {
        //Click link my account
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
//        click vao create an account button
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
//        Nhap
        driver.findElement(By.id("firstname")).sendKeys("Anthony");
        driver.findElement(By.id("middlename")).sendKeys("Hoang");
        driver.findElement(By.id("lastname")).sendKeys("Duc");
        driver.findElement(By.id("email_address")).sendKeys(emailAddress);
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("confirmation")).sendKeys("123456");
        driver.findElement(By.id("confirmation")).sendKeys("123456");

//        Click Register button
        driver.findElement(By.cssSelector("button[title='Register']")).click();
//        so sanh
        String msgSuccess = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
        System.out.println(msgSuccess);

        Assert.assertEquals(msgSuccess,"Thank you for registering with Main Website Store.");

        String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
        Assert.assertTrue(contactInformation.contains("Anthony"));
        Assert.assertTrue(contactInformation.contains("Duc"));
        Assert.assertTrue(contactInformation.contains(emailAddress));
//       logout
        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
//kt logo hien thi
        Assert.assertTrue(driver.findElement(By.cssSelector("img[src$='logo.png']")).isDisplayed());
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
