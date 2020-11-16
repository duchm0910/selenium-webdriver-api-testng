package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_Custom_Dropdown_Part_I {
    WebDriver driver;
    Select select;
    WebDriverWait expliciWait;
    JavascriptExecutor jsExecutor;
    String[] firstMonths = {"January", "April", "July"};
    String[] secondMonths = {"January", "April", "July", "August", "October"};

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        expliciWait = new WebDriverWait(driver, 30);
        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }

    @Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

//        5
        selectItemCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("ui-selectmenu-text")).getText(), "5");
//        10
        selectItemCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("ui-selectmenu-text")).getText(), "10");
//        3
        selectItemCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "3");
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("ui-selectmenu-text")).getText(), "3");
//        18
        selectItemCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "18");
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("ui-selectmenu-text")).getText(), "18");

    }

    @Test
    public void TC_02_Angular() {
        driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

        selectItemCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//div[@id='games_popup']//li", "Hockey");
        sleepInSecond(3);

//        Get text
//        Assert.assertEquals(driver.findElement(By.xpath("//select[@id='games_hidden']//option")).getText(),"Hockey");
//        Displayed
//        Assert.assertTrue(driver.findElement(By.xpath("//select[@id='games_hidden']//option[text()='Cricket']")).isDisplayed());

//        Select (getFirstSelectedOption().getText())
//        select = new Select(driver.findElement(By.xpath("//select[@id='games_hidden']")));
//        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Hockey");

        Assert.assertEquals(getAngularSelectedValueByJS(), "Hockey");

        selectItemCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//div[@id='games_popup']//li", "Tennis");
        sleepInSecond(3);

        selectItemCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//div[@id='games_popup']//li", "American Football");
        sleepInSecond(3);

    }

    @Test
    public void TC_03_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemCustomDropdown("//div[@role='listbox']", "//div[@role='listbox']//span[@class='text']", "Matt");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Matt']")).isDisplayed());

        selectItemCustomDropdown("//div[@role='listbox']", "//div[@role='listbox']//span[@class='text']", "Jenny Hess");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Jenny Hess']")).isDisplayed());

        selectItemCustomDropdown("//div[@role='listbox']", "//div[@role='listbox']//span[@class='text']", "Justen Kitsune");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Justen Kitsune']")).isDisplayed());


    }

    @Test
    public void TC_04_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");


        selectItemCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");

        selectItemCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");

        selectItemCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");

    }

    @Test
    public void TC_05_Edit() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectItemEditDropdown("//input[@class='search']", "//div[@role='combobox']//span[@class='text']", "Australia");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Australia']")).isDisplayed());

        selectItemEditDropdown("//input[@class='search']", "//div[@role='combobox']//span[@class='text']", "Afghanistan");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Afghanistan']")).isDisplayed());

        selectItemEditDropdown("//input[@class='search']", "//div[@role='combobox']//span[@class='text']", "Belgium");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Belgium']")).isDisplayed());
    }

    @Test
    public void TC_06_Multiple_Custom_Dropdown() {
        driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
        selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "(//button[@class='ms-choice'])[1]/following-sibling::div//span", firstMonths);
        sleepInSecond(2);
        Assert.assertTrue(areItemSelected(firstMonths));

        driver.navigate().refresh();

        selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "(//button[@class='ms-choice'])[1]/following-sibling::div//span", secondMonths);
        sleepInSecond(2);
        Assert.assertTrue(areItemSelected(secondMonths));
    }


    public void selectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {
//        Click dropdown cho xo het gia tri ra
        driver.findElement(By.xpath(parentXpath)).click();
//        Cho cho cac gia tri trong dropdown load thanh cong
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
        List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

//        Duyet cac phan tu cho den khi thoa man dieu kien
        for (WebElement childElement : allItems) {
            for (String item : expectedValueItem) {
                if (childElement.getText().equals(item)) {
//                    Click vao item can chon
                    childElement.click();
                    sleepInSecond(1);

                    List<WebElement> itemselected = driver.findElements(By.xpath("//li[@class='selected']//input"));
                    System.out.println("Item selected: " + itemselected.size());
                    if (expectedValueItem.length == itemselected.size()) {
                        break;
                    }
                }
            }
        }
    }

    public boolean areItemSelected(String[] itemselectedText) {
        List<WebElement> itemSeltected = driver.findElements(By.xpath("//li[@class='selected']//input"));
        int numberItemSelected = itemSeltected.size();

        String allItemSelectedText = driver.findElement(By.xpath("//button[@class='ms-choice']/span[1]")).getText();
        System.out.println("Text da chon= " + allItemSelectedText);

//        January, March, June
        if (numberItemSelected <= 3 && numberItemSelected > 0) {
            for (String item : itemselectedText) {
                if (allItemSelectedText.contains(item)) {
                    break;
                }
            }
            return true;
        } else {
            return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
        }
    }

    public void selectItemCustomDropdown(String parentXpath, String allElementXpath, String expectedValueItem) {
//       1 - Click vao 1 element(parentXpath) de cho no xo het cac item ra
        driver.findElement(By.xpath(parentXpath)).click();

//       2 - Cho cac all element hien ra
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allElementXpath)));

//        3 - luu no lai vao 1 list chua cac element
        List<WebElement> allItems = driver.findElements(By.xpath(allElementXpath));
//        List<WebElement> allItems = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allElementXpath)));

//        19 items
//        4 - Lay ra text cua tung element
//        Tradition for
        for (int i = 0; i < allItems.size(); i++) {
            String actualValueItems = allItems.get(i).getText();
//            5 - Kiem tra co bang voi text can tim hay ko
            if (actualValueItems.equals(expectedValueItem)) {
//           6 - neu nhu co thi click vao va thoat ra vong lap
                String itemSelected = allItems.get(i).getText();
                System.out.println(itemSelected);
                allItems.get(i).click();
                break;
            }
        }
/*//        For-each
        for(WebElement item :allItems){
            if(item.getText().equals(expectedValueItem)){
                item.click();
                break;
            }
        }*/
    }

    public void selectItemEditDropdown(String editableXpath, String allElementXpath, String expectedValueItem) {
//       1 - Click vao 1 element(parentXpath) de cho no xo het cac item ra
        driver.findElement(By.xpath(editableXpath)).sendKeys(expectedValueItem);
        sleepInSecond(1);

//       2 - Cho cac all element hien ra
        expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allElementXpath)));

//        3 - luu no lai vao 1 list chua cac element
        List<WebElement> allItems = driver.findElements(By.xpath(allElementXpath));
//        List<WebElement> allItems = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allElementXpath)));

//        19 items
//        4 - Lay ra text cua tung element
//        Tradition for
        for (int i = 0; i < allItems.size(); i++) {
            String actualValueItems = allItems.get(i).getText();
//            5 - Kiem tra co bang voi text can tim hay ko
            if (actualValueItems.equals(expectedValueItem)) {
//           6 - neu nhu co thi click vao va thoat ra vong lap
                String itemSelected = allItems.get(i).getText().trim();
                System.out.println(itemSelected);
                allItems.get(i).click();
                break;
            }
        }
/*//        For-each
        for(WebElement item :allItems){
            if(item.getText().equals(expectedValueItem)){
                item.click();
                break;
            }
        }*/
    }


    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //    Chi dung cho Angular
    public String getAngularSelectedValueByJS() {
        return (String) jsExecutor.executeScript("return document.querySelector(\"select[name='games'] option\").textContent");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
