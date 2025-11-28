package com.misc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class mis1 {


    WebDriver driver;

    @BeforeSuite
    public void Setup(){
        // check out this website for ChromeOptions --https://developer.chrome.com/docs/chromedriver/capabilities
        ChromeOptions opt=new ChromeOptions();

        //by pass privacy certificate
        opt.setAcceptInsecureCerts(true);
        // set proxy
//        Proxy proxy= new Proxy();
//        proxy.setHttpProxy("ipaddress:4444");
//        opt.setCapability("proxy",proxy);
        opt.setExperimentalOption("excludeSwitches",
                Arrays.asList("disable-popup-blocking"));

        // set download directory
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "/Users/priyalal/IdeaProjects/TestNG/src/test/java/com/misc");
        opt.setExperimentalOption("prefs", prefs);



        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(opt);
    }

    @AfterSuite
    public void teardown(){
        driver.close();
    }

    @Test
    public void InsecureCerts(){
        driver.get("https://expired.badssl.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        System.out.println(driver.getTitle());

    }

    @Test
    public void deletecookies_screenshots() throws InterruptedException, IOException {
        driver.get("https://developer.chrome.com/docs/chromedriver/capabilities");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // delete all cookies
        driver.manage().deleteAllCookies();
        Thread.sleep(5000);

        // take screenshot
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // save screenshot with filename
        File dest = new File("/Users/priyalal/IdeaProjects/TestNG/src/test/java/com/misc/screenshots/screenshot.png");
        FileUtils.copyFile(src, dest);

        System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
    }


    @Test
    public void brokenlinks() throws IOException {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement e : links) {
            String link = e.getAttribute("href");
            if (link == null || link.isEmpty() || link.startsWith("javascript")) {
                System.out.println("Skipping invalid link: " + link);
                continue;
            }
            verifyLinkActive(link);
        }
    }

    public static void verifyLinkActive(String linkUrl) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(linkUrl).openConnection();
        connection.setConnectTimeout(3000);
        connection.connect();

        int statusCode = connection.getResponseCode();
        if (statusCode >= 400) {
            System.out.println(linkUrl + " - BROKEN (Status: " + statusCode + ")");
        } else {
            System.out.println(linkUrl + " - " + connection.getResponseMessage());
        }
    }
}
