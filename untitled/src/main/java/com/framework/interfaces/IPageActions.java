package com.framework.interfaces;

import org.openqa.selenium.WebElement;

/**
 * Interface defining common page actions (Abstraction contract)
 * This demonstrates Abstraction - defining what actions are available without implementation
 */
public interface IPageActions {
    
    /**
     * Click on a web element
     * @param element WebElement to click
     */
    void click(WebElement element);
    
    /**
     * Type text into a web element
     * @param element WebElement to type into
     * @param text Text to type
     */
    void type(WebElement element, String text);
    
    /**
     * Clear text from a web element
     * @param element WebElement to clear
     */
    void clear(WebElement element);
    
    /**
     * Get text from a web element
     * @param element WebElement to get text from
     * @return Text from the element
     */
    String getText(WebElement element);
    
    /**
     * Check if element is displayed
     * @param element WebElement to check
     * @return true if displayed, false otherwise
     */
    boolean isDisplayed(WebElement element);
    
    /**
     * Wait for element to be clickable
     * @param element WebElement to wait for
     */
    void waitForElementToBeClickable(WebElement element);
    
    /**
     * Wait for element to be visible
     * @param element WebElement to wait for
     */
    void waitForElementToBeVisible(WebElement element);
    
    /**
     * Take screenshot
     * @return screenshot as byte array
     */
    byte[] takeScreenshot();
}
