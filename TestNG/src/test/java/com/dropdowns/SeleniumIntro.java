package com.dropdowns;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SeleniumIntro {

    static WebDriver driver;
    String baseUrl = "https://rahulshettyacademy.com/dropdownsPractise/";

    @BeforeClass(alwaysRun = true)
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod(alwaysRun = true)
    public void navigateToBaseUrl() {
        driver.get(baseUrl);
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.close();
    }

    @Test(priority = 1, groups = "smoke")
    public void test_alerts() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.findElement(By.name("enter-name")).sendKeys("Priya");
        driver.findElement(By.id("alertbtn")).click();
        String alert_text = driver.switchTo().alert().getText();
        System.out.println(alert_text);
        driver.switchTo().alert().accept();
        // Confirm button
        driver.findElement(By.name("enter-name")).sendKeys("Priya");
        driver.findElement(By.id("confirmbtn")).click();
        driver.switchTo().alert().dismiss();
    }

    @Test(priority = 2, groups = "sanity",dependsOnMethods = "dropdown2")
    public void staticdropdowns() throws InterruptedException {

        WebElement staticdrpdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));

        Select stat_dropdown = new Select(staticdrpdown);
        stat_dropdown.selectByIndex(2);
        Iterator<WebElement> itr = stat_dropdown.getOptions().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next().getText());
        }
        stat_dropdown.selectByValue("INR");

        //Explicit wait
        WebDriverWait exp_wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        exp_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Manage Booking']")));

        boolean flag = stat_dropdown.isMultiple();

        Assert.assertTrue(flag);
    }

    @Test(priority = 1, groups = "smoke")
    public void dropdown2() throws InterruptedException {

        driver.findElement(By.id("divpaxinfo")).click();
        Wait<WebDriver> fl_wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        WebElement passengers = driver.findElement(By.xpath("//div[@id=\"divAdult\"]//span[@id=\"hrefIncAdt\"]"));
        for (int i = 0; i < 3; i++) {
            passengers.click();
        }
    }

    @Test(priority = 2, groups = "sanity")
    public void DynamicDropdown() throws InterruptedException {
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id=\"ctl00_mainContent_ddl_originStation1_CTXT\"]")).sendKeys("BLR");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id=\"ctl00_mainContent_ddl_destinationStation1_CTXT\"]")).sendKeys("MAA");
        Thread.sleep(5000);
    }

    @Test(priority = 4, groups = "smoke")
    public void handling_checkbox() throws InterruptedException {
        String[] ids = {"familyandfriend", "ctl00_mainContent_SeniorCitizenDiv", "ctl00_mainContent_studentAndDefense", "StudentDiv", "UnmrDiv"};
        for (int i = 0; i < ids.length; i++) {
            driver.findElement(By.xpath("//div[@id='" + ids[i] + "']//input")).click();
            Thread.sleep(2000);
        }
        boolean selectedcheckbox = driver.findElement(By.xpath("//div[@id='UnmrDiv']")).isSelected();
        SoftAssert softassertObject = new SoftAssert();
        softassertObject.assertTrue(selectedcheckbox);
        ArrayList<String> actual_value = new ArrayList<String>();
        Thread.sleep(2000);
        //get all checkbox values
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type=\"checkbox\"]//following-sibling::label"));
        for (WebElement i : checkboxes) {
            actual_value.add(i.getText());
        }
        System.out.println(actual_value);

        String[] ExpectedValueList = {"", "Family and Friends", "Senior Citizen", "Indian Armed Forces", "Student", "Unaccompanied Minor"};
        ArrayList<String> ExpectedValue = new ArrayList<>(Arrays.asList(ExpectedValueList));
        softassertObject.assertEquals(ExpectedValue, actual_value);

        softassertObject.assertAll();
    }

    @Test(priority = 9, groups = "smoke")
    public void calendars() throws InterruptedException {
        driver.findElement(By.xpath("//input[@id=\"ctl00_mainContent_rbtnl_Trip_1\"]")).click();

        driver.findElement(By.name("ctl00$mainContent$view_date1")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@class,'ui-datepicker-group-last')]//table//tr//td//a[text()='2']")).click();

        driver.findElement(By.name("ctl00$mainContent$view_date2")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@class,'ui-datepicker-group-last')]//table//tr//td//a[text()='2']")).click();
        driver.findElement(By.xpath("//div[contains(@class,'ui-datepicker-group-last')]//table//tr//td//a[text()='2']")).click();
        Thread.sleep(5000);
    }
}