package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Webbrowser_Command_Part_I {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Web_Browser() {
//        Browser
//        Apply cho tab/ window
        driver.close();
//        apply cho browser
        driver.quit();
//        Mo ra 1 web app(url)
        driver.get("https://www.facebook.com");
//        cac ham ma tuong tac  tren trinh duyet /element -> kieu tra ve cua ham va void
//        cac ham ma lay ra du lieu thi se co kieu tra ve chua du lieu do (String/int/boolean
// lay ra url hien tai
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https//:facebook.com");
//        lay HTML page source hientai
        driver.getPageSource();
//        lay title page

//        lay GUID tab hien tai
        driver.getWindowHandle();
//        lay GUID tat ca tab window dang co
        driver.getWindowHandles();
//        cho cho element duoc load thanh cong trong vong ...s
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        cho page load thanh cong
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
//        JS executor
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
//Fullscreen
        driver.manage().window().maximize();
//          back lai page truoc
driver.navigate().back();
//forward toi page truoc
        driver.navigate().forward();
//tai lai trang
driver.navigate().refresh();
// Alert/iframe(frame)/window(tab)
driver.switchTo().alert();
driver.switchTo().frame("");
driver.switchTo().window("");
//
//        Element
//        driver.findElement(By.id(""));
    }
}
