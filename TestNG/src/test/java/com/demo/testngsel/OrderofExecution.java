package com.demo.testngsel;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrderofExecution {

    WebDriver driver;

    @BeforeSuite
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
    }

    @AfterSuite
    public void teardown(){
        driver.close();
    }



    @Test(priority = -1)
    public void logintohrm() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(5000);
        String title=driver.getTitle();
        System.out.println(title);

        Assert.assertEquals(title, "OrangeHRM");

        WebElement usernameField = driver.findElement(By.xpath("//input[@name='username']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));

        usernameField.click();
        usernameField.sendKeys("Admin");

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("admin123");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(5000);
        WebElement dashboard=driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        wait.until(ExpectedConditions.elementToBeClickable(dashboard));
    }


    @Test(priority = 2)
    public void verifyDashboard() throws InterruptedException {

        WebElement dashboard_leftnav=driver.findElement(By.xpath("//span[text()='Dashboard']"));
        dashboard_leftnav.click();
        Thread.sleep(5000);
        boolean flag=driver.findElement(By.xpath("//p[text()='Quick Launch']")).isDisplayed();

        Assert.assertTrue(flag);
    }

    @Test()
    public void verifyLeaveTracker() throws InterruptedException {
        WebElement leave_leftnav=driver.findElement(By.xpath("//span[text()='Leave']"));
        leave_leftnav.click();
        Thread.sleep(5000);
        WebElement Emp_name=driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
        Emp_name.sendKeys("Priya");
    }

    @Test
    public void verifyRecuirement() throws InterruptedException {

        WebElement Recruitment_leftnav=driver.findElement(By.xpath("//span[text()='Recruitment']"));
        Recruitment_leftnav.click();
        Thread.sleep(5000);
        WebElement Emp_name=driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
        Emp_name.sendKeys("Priya");
    }



}
