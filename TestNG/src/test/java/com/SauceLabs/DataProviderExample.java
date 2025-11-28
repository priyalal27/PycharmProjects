package com.SauceLabs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class DataProviderExample {

    WebDriver driver;

    @BeforeSuite
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterSuite
    public void teardown(){
     driver.close();
    }


    @Test(dataProvider = "getdata")
    public void LoginSauceLab(String username,String password) throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Swag Labs']")));
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Products']")));
        Thread.sleep(2000);

    }

    @DataProvider
    public Object[][] getdata(){
        Object[][] data=new Object[3][2];
        data[0][0]="standard_user";
        data[0][1]="secret_sauce";
        data[1][0]="problem_user";
        data[1][1]="secret_sauce";
        data[2][0]="error_user";
        data[2][1]="secret_sauce";

        return data;
    }





}
