package com.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ActionsClass {

    WebDriver driver;
    Actions action;

    @BeforeSuite
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterSuite
    public void teardown() {
        driver.close();
    }

    @Test
    public void mouse_hover_movetospecific_element() throws InterruptedException {
        action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//a[text()='Your Account']"))).contextClick().build().perform();
        Thread.sleep(5000);

    }


    @Test
    public void keyword_capital_words() throws InterruptedException {
        action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().build().perform();
        Thread.sleep(5000);
        driver.manage().window().maximize();
        // moveToElement co-ordinates
        action.moveToLocation(84, 250).click().build().perform();
        Thread.sleep(5000);
    }

    @Test
    public void windowHandles() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.className("blinkingText")).click();
        Set<String> windowIds = driver.getWindowHandles();
        Iterator<String> it = windowIds.iterator();
        String ParentID = it.next();
        String ChildID = it.next();
        driver.switchTo().window(ChildID);
        System.out.println(driver.getTitle());
    }

    @Test
    public void Frames() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.switchTo().frame("courses-iframe");
        driver.findElement(By.xpath("//*[text()='All Access plan']")).click();
        Thread.sleep(4000);
    }

    @Test
    public void openFooterLinksInNewTabs() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class,'footer_style')]//a"));
        System.out.println("Total links in footer: " + elements.size());

        String parentWindow = driver.getWindowHandle();

        // Collect all hrefs first
        List<String> links = new ArrayList<>();
        for (WebElement element : elements) {
            links.add(element.getAttribute("href"));
        }

        // Now open each in new tab
        for (String link : links) {
            ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", link);
            Thread.sleep(1000);
        }

        // Switch and print titles
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            driver.switchTo().window(window);
            System.out.println(driver.getTitle());
        }

        driver.switchTo().window(parentWindow);
    }

    @Test
    public void scroll() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(5000);

        //scrolling into table view
        js.executeScript("document.querySelector(\".tableFixHead\").scrollBy(0,200)");
    }

    @Test
    public void get_table_value_do_summation(){
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> elemt=driver.findElements(By.xpath("//div[@class=\"tableFixHead\"]//tr//td[4]"));
        int sum=0;
        for(WebElement a:elemt){
                int val= Integer.parseInt(a.getText());
                sum=sum+val;

        }
        System.out.println("Summation = "+sum);
    }

    @Test
    public void brokenlinks(){

    }

}
