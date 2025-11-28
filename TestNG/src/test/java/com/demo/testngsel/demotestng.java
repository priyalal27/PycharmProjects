package com.demo.testngsel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class demotestng {
    
    WebDriver driver;
    
    @BeforeClass
    public void setUp(){
        System.out.println("Setting up WebDriver...");
        // Automatically manage ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println("Chrome browser launched successfully!");
    }
    
    @Test
    public void launchGoogleTest(){
        System.out.println("Starting Google.com test...");
        
        // Navigate to Google
        driver.get("https://www.google.com");
        
        // Get and print the page title
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);
        
        // Verify we're on Google
        if(pageTitle.contains("Google")){
            System.out.println("✅ Successfully launched Google.com!");
        } else {
            System.out.println("❌ Failed to launch Google.com");
        }
        
        // Wait for 3 seconds to see the page
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void test2(){
        System.out.println("Running additional test...");
        
        // Get current URL
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
    }

    @AfterClass
    public void tearDown(){
        System.out.println("Closing the browser...");
        if(driver != null){
            driver.quit();
            System.out.println("Browser closed successfully!");
        }
    }
}