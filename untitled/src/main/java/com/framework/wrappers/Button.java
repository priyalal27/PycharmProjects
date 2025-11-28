package com.framework.wrappers;

import com.framework.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * Button wrapper class demonstrating Element Wrapper pattern in POM
 * This wrapper provides enhanced functionality for button elements
 * 
 * Demonstrates:
 * 1. Wrapper Pattern - Enhanced functionality around WebElement
 * 2. Encapsulation - Button-specific operations encapsulated
 * 3. Single Responsibility - Only handles button operations
 * 4. Reusability - Can be used across different page objects
 */
public class Button {
    
    private static final Logger logger = LogManager.getLogger(Button.class);
    private final WebElement element;
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String elementName;
    
    /**
     * Constructor
     * @param element WebElement representing the button
     * @param driver WebDriver instance
     * @param elementName Name of the button for logging purposes
     */
    public Button(WebElement element, WebDriver driver, String elementName) {
        this.element = element;
        this.driver = driver;
        this.elementName = elementName != null ? elementName : "Button";
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        logger.debug("Initialized {} wrapper", this.elementName);
    }
    
    /**
     * Constructor with default element name
     * @param element WebElement representing the button
     * @param driver WebDriver instance
     */
    public Button(WebElement element, WebDriver driver) {
        this(element, driver, "Button");
    }
    
    /**
     * Click the button with enhanced error handling and logging
     */
    public void click() {
        try {
            waitForClickable();
            element.click();
            logger.info("{}: Button clicked successfully", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to click button", elementName, e);
            throw new RuntimeException("Failed to click " + elementName, e);
        }
    }
    
    /**
     * Click using JavaScript executor (useful when normal click fails)
     */
    public void clickUsingJS() {
        try {
            waitForVisible();
            ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
            logger.info("{}: Button clicked using JavaScript", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to click button using JavaScript", elementName, e);
            throw new RuntimeException("Failed to click " + elementName + " using JavaScript", e);
        }
    }
    
    /**
     * Double click the button
     */
    public void doubleClick() {
        try {
            waitForClickable();
            org.openqa.selenium.interactions.Actions actions = 
                new org.openqa.selenium.interactions.Actions(driver);
            actions.doubleClick(element).perform();
            logger.info("{}: Button double clicked", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to double click button", elementName, e);
            throw new RuntimeException("Failed to double click " + elementName, e);
        }
    }
    
    /**
     * Right click the button
     */
    public void rightClick() {
        try {
            waitForClickable();
            org.openqa.selenium.interactions.Actions actions = 
                new org.openqa.selenium.interactions.Actions(driver);
            actions.contextClick(element).perform();
            logger.info("{}: Button right clicked", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to right click button", elementName, e);
            throw new RuntimeException("Failed to right click " + elementName, e);
        }
    }
    
    /**
     * Check if button is displayed
     * @return true if button is displayed, false otherwise
     */
    public boolean isDisplayed() {
        try {
            boolean displayed = element.isDisplayed();
            logger.debug("{}: Button displayed status: {}", elementName, displayed);
            return displayed;
        } catch (Exception e) {
            logger.debug("{}: Button not displayed", elementName);
            return false;
        }
    }
    
    /**
     * Check if button is enabled
     * @return true if button is enabled, false otherwise
     */
    public boolean isEnabled() {
        try {
            boolean enabled = element.isEnabled();
            logger.debug("{}: Button enabled status: {}", elementName, enabled);
            return enabled;
        } catch (Exception e) {
            logger.debug("{}: Button not enabled", elementName);
            return false;
        }
    }
    
    /**
     * Check if button is selected (for toggle buttons)
     * @return true if button is selected, false otherwise
     */
    public boolean isSelected() {
        try {
            boolean selected = element.isSelected();
            logger.debug("{}: Button selected status: {}", elementName, selected);
            return selected;
        } catch (Exception e) {
            logger.debug("{}: Button not selected", elementName);
            return false;
        }
    }
    
    /**
     * Get button text
     * @return Button text
     */
    public String getText() {
        try {
            waitForVisible();
            String text = element.getText();
            logger.debug("{}: Button text: {}", elementName, text);
            return text;
        } catch (Exception e) {
            logger.error("{}: Failed to get button text", elementName, e);
            throw new RuntimeException("Failed to get text from " + elementName, e);
        }
    }
    
    /**
     * Get button attribute value
     * @param attributeName Name of the attribute
     * @return Attribute value
     */
    public String getAttribute(String attributeName) {
        try {
            String attributeValue = element.getAttribute(attributeName);
            logger.debug("{}: Button attribute '{}': {}", elementName, attributeName, attributeValue);
            return attributeValue;
        } catch (Exception e) {
            logger.error("{}: Failed to get attribute '{}'", elementName, attributeName, e);
            throw new RuntimeException("Failed to get attribute '" + attributeName + "' from " + elementName, e);
        }
    }
    
    /**
     * Get CSS property value
     * @param propertyName Name of the CSS property
     * @return CSS property value
     */
    public String getCSSValue(String propertyName) {
        try {
            String cssValue = element.getCssValue(propertyName);
            logger.debug("{}: Button CSS property '{}': {}", elementName, propertyName, cssValue);
            return cssValue;
        } catch (Exception e) {
            logger.error("{}: Failed to get CSS property '{}'", elementName, propertyName, e);
            throw new RuntimeException("Failed to get CSS property '" + propertyName + "' from " + elementName, e);
        }
    }
    
    /**
     * Hover over the button
     */
    public void hover() {
        try {
            waitForVisible();
            org.openqa.selenium.interactions.Actions actions = 
                new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(element).perform();
            logger.info("{}: Hovered over button", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to hover over button", elementName, e);
            throw new RuntimeException("Failed to hover over " + elementName, e);
        }
    }
    
    /**
     * Scroll button into view
     */
    public void scrollIntoView() {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("{}: Scrolled button into view", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to scroll button into view", elementName, e);
            throw new RuntimeException("Failed to scroll " + elementName + " into view", e);
        }
    }
    
    /**
     * Wait for button to be clickable
     */
    public void waitForClickable() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.debug("{}: Button is clickable", elementName);
        } catch (Exception e) {
            logger.error("{}: Button is not clickable within timeout", elementName, e);
            throw new RuntimeException(elementName + " is not clickable", e);
        }
    }
    
    /**
     * Wait for button to be visible
     */
    public void waitForVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.debug("{}: Button is visible", elementName);
        } catch (Exception e) {
            logger.error("{}: Button is not visible within timeout", elementName, e);
            throw new RuntimeException(elementName + " is not visible", e);
        }
    }
    
    /**
     * Wait for button to be invisible
     */
    public void waitForInvisible() {
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
            logger.debug("{}: Button is invisible", elementName);
        } catch (Exception e) {
            logger.error("{}: Button is still visible after timeout", elementName, e);
            throw new RuntimeException(elementName + " is still visible", e);
        }
    }
    
    /**
     * Check if button has specific CSS class
     * @param className CSS class name to check
     * @return true if button has the class, false otherwise
     */
    public boolean hasClass(String className) {
        try {
            String classAttribute = getAttribute("class");
            boolean hasClass = classAttribute != null && classAttribute.contains(className);
            logger.debug("{}: Button has class '{}': {}", elementName, className, hasClass);
            return hasClass;
        } catch (Exception e) {
            logger.debug("{}: Could not check for class '{}'", elementName, className);
            return false;
        }
    }
    
    /**
     * Get button location
     * @return Point representing button location
     */
    public org.openqa.selenium.Point getLocation() {
        try {
            org.openqa.selenium.Point location = element.getLocation();
            logger.debug("{}: Button location: {}", elementName, location);
            return location;
        } catch (Exception e) {
            logger.error("{}: Failed to get button location", elementName, e);
            throw new RuntimeException("Failed to get location of " + elementName, e);
        }
    }
    
    /**
     * Get button size
     * @return Dimension representing button size
     */
    public org.openqa.selenium.Dimension getSize() {
        try {
            org.openqa.selenium.Dimension size = element.getSize();
            logger.debug("{}: Button size: {}", elementName, size);
            return size;
        } catch (Exception e) {
            logger.error("{}: Failed to get button size", elementName, e);
            throw new RuntimeException("Failed to get size of " + elementName, e);
        }
    }
    
    /**
     * Get the underlying WebElement (for cases where direct access is needed)
     * @return WebElement
     */
    public WebElement getElement() {
        return element;
    }
    
    /**
     * Get element name for identification
     * @return Element name
     */
    public String getElementName() {
        return elementName;
    }
}

