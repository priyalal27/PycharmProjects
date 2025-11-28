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
 * Base component class for Page Object Model components
 * This demonstrates advanced POM pattern with reusable page components
 * Components are parts of pages that can be shared across multiple pages (Header, Footer, Navigation)
 * 
 * This class demonstrates:
 * 1. Component-based POM architecture
 * 2. Abstraction - Common component functionality
 * 3. Encapsulation - Protected methods for component operations
 * 4. Inheritance - Components extend this base class
 */
public abstract class BaseComponent implements IPageActions {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BaseComponent.class);
    
    /**
     * Constructor initializing driver and PageFactory for component
     * @param driver WebDriver instance
     */
    public BaseComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        PageFactory.initElements(driver, this);
        logger.info("Initialized {} component", this.getClass().getSimpleName());
    }
    
    /**
     * Abstract method to verify component is loaded
     * Each component must implement its own loading verification
     * @return true if component is loaded, false otherwise
     */
    public abstract boolean isComponentLoaded();
    
    /**
     * Get component name for logging and identification
     * @return component name
     */
    protected String getComponentName() {
        return this.getClass().getSimpleName();
    }
    
    @Override
    public void click(WebElement element) {
        try {
            waitForElementToBeClickable(element);
            element.click();
            logger.info("{}: Clicked on element: {}", getComponentName(), element.toString());
        } catch (Exception e) {
            logger.error("{}: Failed to click on element: {}", getComponentName(), element.toString(), e);
            throw new RuntimeException("Failed to click on element in " + getComponentName(), e);
        }
    }
    
    @Override
    public void type(WebElement element, String text) {
        try {
            waitForElementToBeVisible(element);
            clear(element);
            element.sendKeys(text);
            logger.info("{}: Typed '{}' into element: {}", getComponentName(), text, element.toString());
        } catch (Exception e) {
            logger.error("{}: Failed to type '{}' into element: {}", getComponentName(), text, element.toString(), e);
            throw new RuntimeException("Failed to type into element in " + getComponentName(), e);
        }
    }
    
    @Override
    public void clear(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            element.clear();
            logger.info("{}: Cleared element: {}", getComponentName(), element.toString());
        } catch (Exception e) {
            logger.error("{}: Failed to clear element: {}", getComponentName(), element.toString(), e);
            throw new RuntimeException("Failed to clear element in " + getComponentName(), e);
        }
    }
    
    @Override
    public String getText(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            String text = element.getText();
            logger.info("{}: Got text '{}' from element: {}", getComponentName(), text, element.toString());
            return text;
        } catch (Exception e) {
            logger.error("{}: Failed to get text from element: {}", getComponentName(), element.toString(), e);
            throw new RuntimeException("Failed to get text from element in " + getComponentName(), e);
        }
    }
    
    @Override
    public boolean isDisplayed(WebElement element) {
        try {
            boolean isDisplayed = element.isDisplayed();
            logger.info("{}: Element {} is displayed: {}", getComponentName(), element.toString(), isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.debug("{}: Element {} is not displayed", getComponentName(), element.toString());
            return false;
        }
    }
    
    @Override
    public void waitForElementToBeClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.debug("{}: Element is clickable: {}", getComponentName(), element.toString());
        } catch (Exception e) {
            logger.error("{}: Element is not clickable: {}", getComponentName(), element.toString(), e);
            throw new RuntimeException("Element is not clickable in " + getComponentName(), e);
        }
    }
    
    @Override
    public void waitForElementToBeVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.debug("{}: Element is visible: {}", getComponentName(), element.toString());
        } catch (Exception e) {
            logger.error("{}: Element is not visible: {}", getComponentName(), element.toString(), e);
            throw new RuntimeException("Element is not visible in " + getComponentName(), e);
        }
    }
    
    @Override
    public byte[] takeScreenshot() {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            logger.info("{}: Screenshot taken successfully", getComponentName());
            return screenshot;
        } catch (Exception e) {
            logger.error("{}: Failed to take screenshot", getComponentName(), e);
            throw new RuntimeException("Failed to take screenshot from " + getComponentName(), e);
        }
    }
    
    /**
     * Check if element exists (without waiting)
     * @param element WebElement to check
     * @return true if element exists, false otherwise
     */
    protected boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Wait for component to be loaded with custom timeout
     * @param timeoutSeconds Custom timeout in seconds
     * @return true if component is loaded within timeout, false otherwise
     */
    public boolean waitForComponentToLoad(int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            customWait.until(driver -> isComponentLoaded());
            logger.info("{}: Component loaded within {} seconds", getComponentName(), timeoutSeconds);
            return true;
        } catch (Exception e) {
            logger.warn("{}: Component not loaded within {} seconds", getComponentName(), timeoutSeconds);
            return false;
        }
    }
    
    /**
     * Refresh component by re-initializing PageFactory
     * Useful when DOM elements are dynamically updated
     */
    protected void refreshComponent() {
        PageFactory.initElements(driver, this);
        logger.info("{}: Component refreshed", getComponentName());
    }
}
