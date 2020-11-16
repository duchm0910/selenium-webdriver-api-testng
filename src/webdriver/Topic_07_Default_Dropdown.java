package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_07_Default_Dropdown {
    WebDriver driver;
    Select select;
    String firstName, lastName, email, password, companyName, date, month, year;

    By genderMaleBy = By.id("gender-male");
    By firstNameBy = By.name("FirstName");
    By lastNameBy = By.name("LastName");
    By emailBy = By.id("Email");
    By companyBy = By.id("Company");
    By passBy = By.id("Password");
    By confirmPassBy = By.id("ConfirmPassword");
    By dateBy = By.name("DateOfBirthDay");
    By monthBy = By.name("DateOfBirthMonth");
    By yearBy = By.name("DateOfBirthYear");
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();



        firstName = "Hoang";
        lastName= "Minh Duc";
        email= generateEmail();
        password= "123456";
        companyName= "InApps";
        date= "3";
        month= "March";
        year= "1933";


    }

    @Test
    public void TC_01_Register() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(genderMaleBy).click();
        driver.findElement(firstNameBy).sendKeys(firstName);
        driver.findElement(lastNameBy).sendKeys(lastName);

        select = new Select(driver.findElement(dateBy));

//        Verify date of birth is single dropdown
        Assert.assertFalse(select.isMultiple());
//        Chon ngay 10
        select.selectByIndex(10);

//        Chon ngay 15
        select.selectByValue("15");

//        Chon ngay 3
        select.selectByVisibleText("3");
//        Bo chon(dung cho multiple)

//        Verify item selected
        Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
//        Verify all item in dropdown
        Assert.assertEquals(select.getOptions().size(), 32);
//        Multiple
//        select.getAllSelectedOptions();

        select = new Select(driver.findElement(monthBy));
        select.selectByVisibleText("March");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
        Assert.assertEquals(select.getOptions().size(), 13);

        select = new Select(driver.findElement(yearBy));
        select.selectByVisibleText("1933");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
        Assert.assertEquals(select.getOptions().size(), 112);

        driver.findElement(emailBy).sendKeys(email);
        driver.findElement(companyBy).sendKeys(companyName);
        driver.findElement(passBy).sendKeys(password);
        driver.findElement(confirmPassBy).sendKeys(password);

        driver.findElement(By.name("register-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");

//        Navigate to My Account link
        driver.findElement(By.className("ico-account")).click();

        Assert.assertTrue(driver.findElement(genderMaleBy).isSelected());

        Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"),email);
        Assert.assertEquals(driver.findElement(companyBy).getAttribute("value"),companyName);

        select = new Select(driver.findElement(dateBy));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),date);
        select = new Select(driver.findElement(monthBy));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
        select = new Select(driver.findElement(yearBy));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),year);

    }
    @Test
    public void TC_02_Single_Dropdown(){
        driver.get("https://automationfc.github.io/basic-form/index.html");


        select = new Select(driver.findElement(By.name("user_job1")));
        Assert.assertFalse(select.isMultiple());
        select.selectByVisibleText("Mobile Testing");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Mobile Testing");

        select.selectByValue("manual");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Manual Testing");

        select.selectByIndex(9);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Functional UI Testing");

        Assert.assertEquals(select.getOptions().size(),10);
    }
    @Test
    public void TC_03_Multiple(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        List<String> itemText = new ArrayList<String>();
        itemText.add("Manual");
        itemText.add("Mobile");
        itemText.add("Security");
        itemText.add("Functional UI");

        select = new Select(driver.findElement(By.name("user_job2")));

        select.selectByVisibleText("Manual");
        select.selectByVisibleText("Mobile");
        select.selectByVisibleText("Sercurity");
        select.selectByVisibleText("Functional UI");

        List<WebElement> itemSelected = select.getAllSelectedOptions();
        List<String> itemSelectedText = new ArrayList<String>();
//        Verify 4 item selected
        Assert.assertEquals(itemSelected.size(),4);

        for(WebElement item:itemSelected){
            itemSelectedText.add(item.getText());
            System.out.println(item.getText());
        }

        Assert.assertTrue(itemSelectedText.contains("Manual"));
        Assert.assertTrue(itemSelectedText.contains("Mobile"));
        Assert.assertTrue(itemSelectedText.contains("Security"));
        Assert.assertTrue(itemSelectedText.contains("Functional UI"));

        Assert.assertEquals(itemText,itemSelectedText);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String generateEmail() {
        Random rand = new Random();
        return "hoang" + rand.nextInt(9999) + "@mailinator.com";
    }
}
