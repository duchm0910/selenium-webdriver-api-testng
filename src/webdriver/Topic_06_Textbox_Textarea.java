package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_06_Textbox_Textarea {
    //    Global variable ( bien toan cuc)
    WebDriver driver;
    String email, userID, password, loginPageUrl;
    String name, address, dobInput, dobOutput, city, pin, phone, state;

    By nameBy = By.name("name");
    By dob By = By.name("dob");
    By addrBy = By.name("addr");
    By cityBy = By.name("city");
    By stateBy = By.name("state");
    By pinBy = By.name("pinno");
    By telBy = By.name("telephoneno");
    By emailBy = By.name("emailid");
    By passBy = By.name("password");
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://demo.guru99.com/v4/");

        name = "Donald Trump";
        address = "123 HCM";
        dobInput = "04/03/1999";
        dobOutput = "1999-04-03";
        city = "Florida";
        pin = "700000";
        phone = "09377889910";
        state = "NY";
        email =generateEmail();
        password="123456";

        email = "automation@mailinator.com";
        loginPageUrl = driver.getCurrentUrl();
    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.xpath("//a[text()='here']")).click();
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.name("btnLogin")).click();
// Local varaiable (bien cuc bo)
        userID = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();

    }

    @Test
    public void TC_02_Login() {
        driver.get(loginPageUrl);
        driver.findElement(By.name("uid")).sendKeys(userID);
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("btnLogin")).click();
        String heading3Text = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();

        Assert.assertEquals(heading3Text, "Welcome To Manager's Page of Guru99 Bank");
    }

    @Test
    public void TC_03_New_Customer() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();

        driver.findElement(nameBy).sendKeys(name);
        driver.findElement(dobBy).sendKeys(dobInput);
        driver.findElement(addrBy).sendKeys(address);
        driver.findElement(cityBy).sendKeys(city);
        driver.findElement(stateBy).sendKeys(state);
        driver.findElement(pinBy).sendKeys(pin);
        driver.findElement(telBy).sendKeys(phone);
        driver.findElement(emailBy).sendKeys(email);
        driver.findElement(passBy).sendKeys(password);
        //p[@class='heading3']
        driver.findElement(By.name("sub")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")), "Customer Registered Successfully!!!");

        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")), name);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")), dobOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")), address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")), city);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")), state);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")), pin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")), phone);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")), email);
    }
    @Test
    public void TC_04_Edit_Customer(){
        driver.findElement(By.xpath("//a[text()='Edit Account']")).click();

    }
    public String generateEmail(){
        Random rand = new Random();
        return "duc.hoang"+rand.nextInt(99999)+"@github.io";
    }
}
