package com.framework.base;

import com.framework.interfaces.IPageActions;
import com.framework.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * Abstract base page class implementing common page actions (Abstraction)
 * This class demonstrates:
 * 1. Abstraction - Abstract class with common functionality
 * 2. Inheritance - Other pages will extend this class
 * 3. Encapsulation - Private fields with protected methods for subclasses
 */
public abstract class BasePage implements IPageActions {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class);
    
    /**
     * Constructor initializing driver and PageFactory
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        PageFactory.initElements(driver, this);
        logger.info("Initialized {} page", this.getClass().getSimpleName());
    }
    
    /**
     * Abstract method to be implemented by child pages
     * Forces each page to define its own page load verification
     * @return true if page is loaded, false otherwise
     */
    public abstract boolean isPageLoaded();
    
    /**
     * Get page title - common functionality for all pages
     * @return current page title
     */
    public String getPageTitle() {
        String title = driver.getTitle();
        logger.info("Current page title: {}", title);
        return title;
    }
    
    /**
     * Get current URL - common functionality for all pages
     * @return current page URL
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.info("Current URL: {}", url);
        return url;
    }
    
    @Override
    public void click(WebElement element) {
        try {
            waitForElementToBeClickable(element);
            element.click();
            logger.info("Clicked on element: {}", element.toString());
        } catch (Exception e) {
            logger.error("Failed to click on element: {}", element.toString(), e);
            throw new RuntimeException("Failed to click on element", e);
        }
    }
    
    @Override
    public void type(WebElement element, String text) {
        try {
            waitForElementToBeVisible(element);
            clear(element);
            element.sendKeys(text);
            logger.info("Typed '{}' into element: {}", text, element.toString());
        } catch (Exception e) {
            logger.error("Failed to type '{}' into element: {}", text, element.toString(), e);
            throw new RuntimeException("Failed to type into element", e);
        }
    }
    
    @Override
    public void clear(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            element.clear();
            logger.info("Cleared element: {}", element.toString());
        } catch (Exception e) {
            logger.error("Failed to clear element: {}", element.toString(), e);
            throw new RuntimeException("Failed to clear element", e);
        }
    }
    
    @Override
    public String getText(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            String text = element.getText();
            logger.info("Got text '{}' from element: {}", text, element.toString());
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: {}", element.toString(), e);
            throw new RuntimeException("Failed to get text from element", e);
        }
    }
    
    @Override
    public boolean isDisplayed(WebElement element) {
        try {
            boolean isDisplayed = element.isDisplayed();
            logger.info("Element {} is displayed: {}", element.toString(), isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.info("Element {} is not displayed", element.toString());
            return false;
        }
    }
    
    @Override
    public void waitForElementToBeClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.debug("Element is clickable: {}", element.toString());
        } catch (Exception e) {
            logger.error("Element is not clickable: {}", element.toString(), e);
            throw new RuntimeException("Element is not clickable", e);
        }
    }
    
    @Override
    public void waitForElementToBeVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.debug("Element is visible: {}", element.toString());
        } catch (Exception e) {
            logger.error("Element is not visible: {}", element.toString(), e);
            throw new RuntimeException("Element is not visible", e);
        }
    }
    
    @Override
    public byte[] takeScreenshot() {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            logger.info("Screenshot taken successfully");
            return screenshot;
        } catch (Exception e) {
            logger.error("Failed to take screenshot", e);
            throw new RuntimeException("Failed to take screenshot", e);
        }
    }
    
    /**
     * Navigate to a specific URL
     * @param url URL to navigate to
     */
    protected void navigateToUrl(String url) {
        try {
            driver.get(url);
            logger.info("Navigated to URL: {}", url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: {}", url, e);
            throw new RuntimeException("Failed to navigate to URL", e);
        }
    }
    
    /**
     * Refresh the current page
     */
    protected void refreshPage() {
        try {
            driver.navigate().refresh();
            logger.info("Page refreshed successfully");
        } catch (Exception e) {
            logger.error("Failed to refresh page", e);
            throw new RuntimeException("Failed to refresh page", e);
        }
    }
}
