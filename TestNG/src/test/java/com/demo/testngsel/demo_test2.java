package com.demo.testngsel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class demo_test2 {

    WebDriver driver;

    @BeforeClass
    public void launchbrowser(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
    }

    @AfterClass
    public void closebrowser(){
        driver.quit();
    }

        @Parameters({"usernameValue","password"})
        @Test
        public void test_title(@Optional("SuperAdmin") String username, String password) throws InterruptedException {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            Thread.sleep(5000);
            String title=driver.getTitle();
            System.out.println(title);

            Assert.assertEquals(title, "OrangeHRM");

            WebElement usernameField = driver.findElement(By.xpath("//input[@name='username']"));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(usernameField));

            usernameField.click();
            usernameField.sendKeys(username);

            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys(password);

            driver.findElement(By.xpath("//button[@type='submit']")).click();

            Thread.sleep(5000);
            WebElement dashboard=driver.findElement(By.xpath("//h6[text()='Dashboard']"));
            wait.until(ExpectedConditions.elementToBeClickable(dashboard));

            SoftAssert softAssertObject=new SoftAssert();



            String current_url=driver.getCurrentUrl();
            softAssertObject.assertEquals(current_url, "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");

            boolean flag=driver.findElement(By.xpath("//p[text()='Time at Work']")).isDisplayed();
            softAssertObject.assertTrue(flag);


            boolean falseflag=driver.findElement(By.xpath("//p[text()='Time at Work']")).isSelected();
            softAssertObject.assertFalse(falseflag);

            softAssertObject.assertAll();
        }


}
