package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Topic_05_Webdriver_Element_Part_II {
    WebDriver driver;
    By emailTextboxBy = By.name("user_email");
    By educationTextAreaBy = By.id("edu");
    By ageUnder18Radio = By.id("under_18");
    By jobRole01Dropdown = By.name("user_job1");
    By interestsCheckbox = By.name ("user_interest");
    By slider01 = By.id("slider-1");

    By passwordTextboxBy = By.name("user_pass");
    By disableRadioBy = By.id("radio-disabled");
    By jobRole03Dropdown = By.id("job3");
    By javaLanguagecheckbox = By.name("java");


    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://automationfc.github.io/basic-form/index.html");
    }
    @Test
    public void TC_01_Element_Displayed() throws InterruptedException {
        //Email textbox
        if(driver.findElement(emailTextboxBy).isDisplayed()){
            System.out.println("Element is displayed");
            driver.findElement(emailTextboxBy).sendKeys("Automation Testing");
        }else {
            System.out.println("Element is not displayed");
        }

        Thread.sleep(1000);
        //Education textbox
        if(driver.findElement(educationTextAreaBy).isDisplayed()){
            System.out.println("Element is displayed");
            driver.findElement(educationTextAreaBy).sendKeys("Automation Testing");
        }else {
            System.out.println("Element is not displayed");
        }
        Thread.sleep(3000);
//        Age (under 18) radio button
        if(driver.findElement(ageUnder18Radio).isDisplayed()){
            System.out.println("Element is displayed");
            driver.findElement(ageUnder18Radio).click();
        }else {
            System.out.println("Element is not displayed");
        }
        Thread.sleep(3000);
    }

    @Test
//    All element: check box/ radio/ textbox/ link/ text...
    public void TC_02_Element_Displayed(){
        driver.navigate().refresh();


        if(isElementDisplayed(emailTextboxBy)){
            driver.findElement(emailTextboxBy).sendKeys("Automation Testing");
        }
        if(isElementDisplayed(educationTextAreaBy)){
            driver.findElement(educationTextAreaBy).sendKeys("Automation Testing");
        }
        if((isElementDisplayed(ageUnder18Radio))){

            driver.findElement(ageUnder18Radio).click();
        }
    }


    @Test
//    Checkbox/ textbox/ radio/ button/ image/...
    public void TC_03_Element_Enabled(){
        driver.navigate().refresh();
        Assert.assertTrue(isElementEnabled(emailTextboxBy));
        Assert.assertTrue(isElementEnabled(educationTextAreaBy));
        Assert.assertTrue(isElementEnabled(ageUnder18Radio));
        Assert.assertTrue(isElementEnabled(jobRole01Dropdown));
        Assert.assertTrue(isElementEnabled(interestsCheckbox));
        Assert.assertTrue(isElementEnabled(slider01));

        Assert.assertFalse(isElementEnabled(passwordTextboxBy));
        Assert.assertFalse(isElementEnabled(disableRadioBy));
        Assert.assertFalse(isElementEnabled(jobRole03Dropdown));


    }
    @Test
//    Checkbox/ radio
    public void TC_04_Element_Selected() throws InterruptedException {
        driver.navigate().refresh();
//        Verify age under 18/ java checkbox deselected
        Assert.assertFalse(isElementSelected(ageUnder18Radio));
        Assert.assertFalse(isElementSelected(javaLanguagecheckbox));

        driver.findElement(ageUnder18Radio).click();
        driver.findElement(javaLanguagecheckbox).click();
        Thread.sleep(3000);

        Assert.assertTrue(isElementSelected(ageUnder18Radio));
        Assert.assertTrue(isElementSelected(javaLanguagecheckbox));

        driver.findElement(ageUnder18Radio).click();
        driver.findElement(javaLanguagecheckbox).click();
        Thread.sleep(3000);

        Assert.assertTrue(isElementSelected(ageUnder18Radio));
        Assert.assertFalse(isElementSelected(javaLanguagecheckbox));


    }

    @Test
    public void TC_05_Validate_Register_Form() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("new_username")).sendKeys("HOang Minh Duc");

//        Verify sign up button disabled
        Assert.assertFalse(isElementEnabled(By.id("create-account")));

//      Lower Case
        driver.findElement(By.name("password")).sendKeys("auto");
        Thread.sleep(3000);
        Assert.assertFalse(isElementEnabled(By.id("create-account")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));

//        Special Char
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("auto!@#");
        Thread.sleep(3000);
        Assert.assertFalse(isElementEnabled(By.id("create-account")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));

//        Upper Case
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("Autoooo!");
        Thread.sleep(3000);
        Assert.assertFalse(isElementEnabled(By.id("create-account")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));

//        8 Char
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("Auto!@#as");
        Thread.sleep(3000);
        Assert.assertFalse(isElementEnabled(By.id("create-account")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));

        //     Number
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("Autoo!1");
        Thread.sleep(3000);
        Assert.assertFalse(isElementEnabled(By.id("create-account")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));

//        Full
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("Autooooooo!1");
        Thread.sleep(3000);
        Assert.assertTrue(isElementEnabled(By.id("create-account")));
        Assert.assertTrue(isElementDisplayed(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")));

        Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")));
        Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='special-char completed' and text()='One special character']")));
        Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")));
        Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")));
        Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
    }



//    Common function(Call function)
    private boolean isElementDisplayed(By by){
        if(driver.findElement(by).isDisplayed()){
            System.out.println("Element is Displayed");
            return true;
        }else {
            System.out.println("Element is NOT Displayed");
            return false;
        }
    }
    private boolean isElementEnabled(By by){
        if(driver.findElement(by).isEnabled()){
            System.out.println("Element is Enabled");
            return true;
        }else {
            System.out.println("Element is Disabled");
            return false;
        }
    }
    private boolean isElementSelected(By by){
        if(driver.findElement(by).isSelected()){
            System.out.println("Element is selected");

            return true;
        }else {
            System.out.println("Element is Deselected");
            return false;
        }
    }
    @AfterClass
    public void afterClass(){
        driver.close();
    }

}
