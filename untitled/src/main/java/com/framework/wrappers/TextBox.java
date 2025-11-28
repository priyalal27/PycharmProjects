package com.framework.wrappers;

import com.framework.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * TextBox wrapper class demonstrating Element Wrapper pattern in POM
 * This wrapper provides enhanced functionality for text input elements
 * 
 * Demonstrates:
 * 1. Wrapper Pattern - Enhanced functionality around WebElement
 * 2. Encapsulation - Text input specific operations encapsulated
 * 3. Single Responsibility - Only handles text input operations
 * 4. Reusability - Can be used across different page objects
 */
public class TextBox {
    
    private static final Logger logger = LogManager.getLogger(TextBox.class);
    private final WebElement element;
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String elementName;
    
    /**
     * Constructor
     * @param element WebElement representing the text box
     * @param driver WebDriver instance
     * @param elementName Name of the text box for logging purposes
     */
    public TextBox(WebElement element, WebDriver driver, String elementName) {
        this.element = element;
        this.driver = driver;
        this.elementName = elementName != null ? elementName : "TextBox";
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        logger.debug("Initialized {} wrapper", this.elementName);
    }
    
    /**
     * Constructor with default element name
     * @param element WebElement representing the text box
     * @param driver WebDriver instance
     */
    public TextBox(WebElement element, WebDriver driver) {
        this(element, driver, "TextBox");
    }
    
    /**
     * Type text into the text box (clears existing text first)
     * @param text Text to type
     */
    public void type(String text) {
        try {
            waitForVisible();
            element.clear();
            element.sendKeys(text);
            logger.info("{}: Typed text: '{}'", elementName, text);
        } catch (Exception e) {
            logger.error("{}: Failed to type text: '{}'", elementName, text, e);
            throw new RuntimeException("Failed to type text into " + elementName, e);
        }
    }
    
    /**
     * Append text to existing text in the text box
     * @param text Text to append
     */
    public void appendText(String text) {
        try {
            waitForVisible();
            element.sendKeys(text);
            logger.info("{}: Appended text: '{}'", elementName, text);
        } catch (Exception e) {
            logger.error("{}: Failed to append text: '{}'", elementName, text, e);
            throw new RuntimeException("Failed to append text to " + elementName, e);
        }
    }
    
    /**
     * Clear the text box
     */
    public void clear() {
        try {
            waitForVisible();
            element.clear();
            logger.info("{}: Cleared text box", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to clear text box", elementName, e);
            throw new RuntimeException("Failed to clear " + elementName, e);
        }
    }
    
    /**
     * Clear using keyboard shortcuts (Ctrl+A, Delete)
     */
    public void clearUsingKeyboard() {
        try {
            waitForVisible();
            element.sendKeys(Keys.CONTROL + "a");
            element.sendKeys(Keys.DELETE);
            logger.info("{}: Cleared text box using keyboard", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to clear text box using keyboard", elementName, e);
            throw new RuntimeException("Failed to clear " + elementName + " using keyboard", e);
        }
    }
    
    /**
     * Get text from the text box
     * @return Text value
     */
    public String getText() {
        try {
            waitForVisible();
            String text = element.getAttribute("value");
            if (text == null) {
                text = element.getText();
            }
            logger.debug("{}: Retrieved text: '{}'", elementName, text);
            return text != null ? text : "";
        } catch (Exception e) {
            logger.error("{}: Failed to get text", elementName, e);
            throw new RuntimeException("Failed to get text from " + elementName, e);
        }
    }
    
    /**
     * Get placeholder text
     * @return Placeholder text
     */
    public String getPlaceholder() {
        try {
            String placeholder = element.getAttribute("placeholder");
            logger.debug("{}: Placeholder text: '{}'", elementName, placeholder);
            return placeholder != null ? placeholder : "";
        } catch (Exception e) {
            logger.error("{}: Failed to get placeholder text", elementName, e);
            throw new RuntimeException("Failed to get placeholder from " + elementName, e);
        }
    }
    
    /**
     * Press Enter key
     */
    public void pressEnter() {
        try {
            waitForVisible();
            element.sendKeys(Keys.ENTER);
            logger.info("{}: Pressed Enter key", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to press Enter key", elementName, e);
            throw new RuntimeException("Failed to press Enter in " + elementName, e);
        }
    }
    
    /**
     * Press Tab key
     */
    public void pressTab() {
        try {
            waitForVisible();
            element.sendKeys(Keys.TAB);
            logger.info("{}: Pressed Tab key", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to press Tab key", elementName, e);
            throw new RuntimeException("Failed to press Tab in " + elementName, e);
        }
    }
    
    /**
     * Press Escape key
     */
    public void pressEscape() {
        try {
            waitForVisible();
            element.sendKeys(Keys.ESCAPE);
            logger.info("{}: Pressed Escape key", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to press Escape key", elementName, e);
            throw new RuntimeException("Failed to press Escape in " + elementName, e);
        }
    }
    
    /**
     * Select all text
     */
    public void selectAll() {
        try {
            waitForVisible();
            element.sendKeys(Keys.CONTROL + "a");
            logger.info("{}: Selected all text", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to select all text", elementName, e);
            throw new RuntimeException("Failed to select all text in " + elementName, e);
        }
    }
    
    /**
     * Copy text to clipboard
     */
    public void copy() {
        try {
            waitForVisible();
            selectAll();
            element.sendKeys(Keys.CONTROL + "c");
            logger.info("{}: Copied text to clipboard", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to copy text", elementName, e);
            throw new RuntimeException("Failed to copy text from " + elementName, e);
        }
    }
    
    /**
     * Paste text from clipboard
     */
    public void paste() {
        try {
            waitForVisible();
            element.sendKeys(Keys.CONTROL + "v");
            logger.info("{}: Pasted text from clipboard", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to paste text", elementName, e);
            throw new RuntimeException("Failed to paste text to " + elementName, e);
        }
    }
    
    /**
     * Check if text box is displayed
     * @return true if displayed, false otherwise
     */
    public boolean isDisplayed() {
        try {
            boolean displayed = element.isDisplayed();
            logger.debug("{}: Text box displayed: {}", elementName, displayed);
            return displayed;
        } catch (Exception e) {
            logger.debug("{}: Text box not displayed", elementName);
            return false;
        }
    }
    
    /**
     * Check if text box is enabled
     * @return true if enabled, false otherwise
     */
    public boolean isEnabled() {
        try {
            boolean enabled = element.isEnabled();
            logger.debug("{}: Text box enabled: {}", elementName, enabled);
            return enabled;
        } catch (Exception e) {
            logger.debug("{}: Text box not enabled", elementName);
            return false;
        }
    }
    
    /**
     * Check if text box is readonly
     * @return true if readonly, false otherwise
     */
    public boolean isReadOnly() {
        try {
            String readonly = element.getAttribute("readonly");
            boolean isReadonly = readonly != null && (readonly.equals("true") || readonly.equals("readonly"));
            logger.debug("{}: Text box readonly: {}", elementName, isReadonly);
            return isReadonly;
        } catch (Exception e) {
            logger.debug("{}: Could not determine readonly status", elementName);
            return false;
        }
    }
    
    /**
     * Check if text box is required
     * @return true if required, false otherwise
     */
    public boolean isRequired() {
        try {
            String required = element.getAttribute("required");
            boolean isRequired = required != null && (required.equals("true") || required.equals("required"));
            logger.debug("{}: Text box required: {}", elementName, isRequired);
            return isRequired;
        } catch (Exception e) {
            logger.debug("{}: Could not determine required status", elementName);
            return false;
        }
    }
    
    /**
     * Get maximum length allowed
     * @return Maximum length or -1 if not specified
     */
    public int getMaxLength() {
        try {
            String maxLength = element.getAttribute("maxlength");
            if (maxLength != null && !maxLength.isEmpty()) {
                int max = Integer.parseInt(maxLength);
                logger.debug("{}: Maximum length: {}", elementName, max);
                return max;
            }
            return -1;
        } catch (Exception e) {
            logger.debug("{}: Could not get maximum length", elementName);
            return -1;
        }
    }
    
    /**
     * Get input type (text, password, email, etc.)
     * @return Input type
     */
    public String getInputType() {
        try {
            String type = element.getAttribute("type");
            logger.debug("{}: Input type: {}", elementName, type);
            return type != null ? type : "text";
        } catch (Exception e) {
            logger.debug("{}: Could not get input type", elementName);
            return "text";
        }
    }
    
    /**
     * Focus on the text box
     */
    public void focus() {
        try {
            waitForVisible();
            element.click();
            logger.info("{}: Focused on text box", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to focus on text box", elementName, e);
            throw new RuntimeException("Failed to focus on " + elementName, e);
        }
    }
    
    /**
     * Scroll text box into view
     */
    public void scrollIntoView() {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("{}: Scrolled text box into view", elementName);
        } catch (Exception e) {
            logger.error("{}: Failed to scroll text box into view", elementName, e);
            throw new RuntimeException("Failed to scroll " + elementName + " into view", e);
        }
    }
    
    /**
     * Wait for text box to be visible
     */
    public void waitForVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.debug("{}: Text box is visible", elementName);
        } catch (Exception e) {
            logger.error("{}: Text box is not visible within timeout", elementName, e);
            throw new RuntimeException(elementName + " is not visible", e);
        }
    }
    
    /**
     * Wait for text box to be clickable
     */
    public void waitForClickable() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.debug("{}: Text box is clickable", elementName);
        } catch (Exception e) {
            logger.error("{}: Text box is not clickable within timeout", elementName, e);
            throw new RuntimeException(elementName + " is not clickable", e);
        }
    }
    
    /**
     * Wait for specific text to appear in the text box
     * @param expectedText Expected text
     */
    public void waitForText(String expectedText) {
        try {
            wait.until(driver -> getText().equals(expectedText));
            logger.debug("{}: Expected text '{}' appeared", elementName, expectedText);
        } catch (Exception e) {
            logger.error("{}: Expected text '{}' did not appear within timeout", elementName, expectedText, e);
            throw new RuntimeException("Expected text '" + expectedText + "' did not appear in " + elementName, e);
        }
    }
    
    /**
     * Get the underlying WebElement
     * @return WebElement
     */
    public WebElement getElement() {
        return element;
    }
    
    /**
     * Get element name
     * @return Element name
     */
    public String getElementName() {
        return elementName;
    }
}

