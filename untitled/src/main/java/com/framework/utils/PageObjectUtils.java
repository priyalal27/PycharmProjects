package com.framework.utils;

import com.framework.base.BaseComponent;
import com.framework.base.BasePage;
import com.framework.wrappers.Button;
import com.framework.wrappers.Dropdown;
import com.framework.wrappers.TextBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

/**
 * Page Object Model utility class providing helper methods for POM implementation
 * This demonstrates:
 * 1. Utility Pattern - Common POM operations centralized
 * 2. Static Helper Methods - Reusable across all page objects
 * 3. Element Factory Pattern - Dynamic element wrapper creation
 * 4. Page Factory Enhancement - Additional PageFactory functionality
 */
public class PageObjectUtils {
    
    private static final Logger logger = LogManager.getLogger(PageObjectUtils.class);
    
    /**
     * Private constructor to prevent instantiation
     */
    private PageObjectUtils() {
        // Utility class should not be instantiated
    }
    
    /**
     * Initialize PageFactory elements for any object
     * @param driver WebDriver instance
     * @param pageObject Page object to initialize
     */
    public static void initializePageFactory(WebDriver driver, Object pageObject) {
        try {
            PageFactory.initElements(driver, pageObject);
            logger.info("PageFactory initialized for: {}", pageObject.getClass().getSimpleName());
        } catch (Exception e) {
            logger.error("Failed to initialize PageFactory for: {}", pageObject.getClass().getSimpleName(), e);
            throw new RuntimeException("PageFactory initialization failed", e);
        }
    }
    
    /**
     * Create Button wrapper for any WebElement
     * @param element WebElement to wrap
     * @param driver WebDriver instance
     * @param elementName Name for the button
     * @return Button wrapper
     */
    public static Button createButton(WebElement element, WebDriver driver, String elementName) {
        try {
            Button button = new Button(element, driver, elementName);
            logger.debug("Created Button wrapper: {}", elementName);
            return button;
        } catch (Exception e) {
            logger.error("Failed to create Button wrapper: {}", elementName, e);
            throw new RuntimeException("Failed to create Button wrapper", e);
        }
    }
    
    /**
     * Create TextBox wrapper for any WebElement
     * @param element WebElement to wrap
     * @param driver WebDriver instance
     * @param elementName Name for the text box
     * @return TextBox wrapper
     */
    public static TextBox createTextBox(WebElement element, WebDriver driver, String elementName) {
        try {
            TextBox textBox = new TextBox(element, driver, elementName);
            logger.debug("Created TextBox wrapper: {}", elementName);
            return textBox;
        } catch (Exception e) {
            logger.error("Failed to create TextBox wrapper: {}", elementName, e);
            throw new RuntimeException("Failed to create TextBox wrapper", e);
        }
    }
    
    /**
     * Create Dropdown wrapper for any WebElement
     * @param element WebElement to wrap
     * @param driver WebDriver instance
     * @param elementName Name for the dropdown
     * @return Dropdown wrapper
     */
    public static Dropdown createDropdown(WebElement element, WebDriver driver, String elementName) {
        try {
            Dropdown dropdown = new Dropdown(element, driver, elementName);
            logger.debug("Created Dropdown wrapper: {}", elementName);
            return dropdown;
        } catch (Exception e) {
            logger.error("Failed to create Dropdown wrapper: {}", elementName, e);
            throw new RuntimeException("Failed to create Dropdown wrapper", e);
        }
    }
    
    /**
     * Wait for page to be loaded using multiple strategies
     * @param driver WebDriver instance
     * @param timeoutSeconds Timeout in seconds
     * @return true if page is loaded, false otherwise
     */
    public static boolean waitForPageLoad(WebDriver driver, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            
            // Wait for document ready state
            wait.until(webDriver -> 
                ((org.openqa.selenium.JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));
            
            // Wait for jQuery to be loaded (if present)
            try {
                wait.until(webDriver -> 
                    ((org.openqa.selenium.JavascriptExecutor) webDriver)
                        .executeScript("return jQuery.active == 0"));
            } catch (Exception e) {
                logger.debug("jQuery not present on page, skipping jQuery wait");
            }
            
            logger.info("Page loaded successfully within {} seconds", timeoutSeconds);
            return true;
        } catch (Exception e) {
            logger.warn("Page did not load within {} seconds", timeoutSeconds);
            return false;
        }
    }
    
    /**
     * Scroll element into view
     * @param driver WebDriver instance
     * @param element WebElement to scroll to
     */
    public static void scrollIntoView(WebDriver driver, WebElement element) {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            logger.debug("Scrolled element into view");
        } catch (Exception e) {
            logger.warn("Failed to scroll element into view", e);
        }
    }
    
    /**
     * Highlight element for debugging purposes
     * @param driver WebDriver instance
     * @param element WebElement to highlight
     */
    public static void highlightElement(WebDriver driver, WebElement element) {
        try {
            String originalStyle = element.getAttribute("style");
            ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].style.border='3px solid red'", element);
            
            // Wait briefly to show highlight
            Thread.sleep(500);
            
            // Restore original style
            ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].setAttribute('style', '" + originalStyle + "')", element);
            
            logger.debug("Element highlighted for debugging");
        } catch (Exception e) {
            logger.debug("Could not highlight element", e);
        }
    }
    
    /**
     * Take screenshot of specific element
     * @param element WebElement to screenshot
     * @return Screenshot as byte array
     */
    public static byte[] takeElementScreenshot(WebElement element) {
        try {
            return element.getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
        } catch (Exception e) {
            logger.warn("Failed to take element screenshot", e);
            return new byte[0];
        }
    }
    
    /**
     * Check if element is in viewport
     * @param driver WebDriver instance
     * @param element WebElement to check
     * @return true if element is in viewport, false otherwise
     */
    public static boolean isElementInViewport(WebDriver driver, WebElement element) {
        try {
            return (Boolean) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                    "var rect = arguments[0].getBoundingClientRect();" +
                    "return (rect.top >= 0 && rect.left >= 0 && " +
                    "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                    "rect.right <= (window.innerWidth || document.documentElement.clientWidth))",
                    element);
        } catch (Exception e) {
            logger.debug("Could not check if element is in viewport", e);
            return false;
        }
    }
    
    /**
     * Wait for all elements in list to be visible
     * @param driver WebDriver instance
     * @param elements List of WebElements to wait for
     * @param timeoutSeconds Timeout in seconds
     * @return true if all elements are visible, false otherwise
     */
    public static boolean waitForAllElementsVisible(WebDriver driver, List<WebElement> elements, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            logger.debug("All {} elements are visible", elements.size());
            return true;
        } catch (Exception e) {
            logger.warn("Not all elements became visible within {} seconds", timeoutSeconds);
            return false;
        }
    }
    
    /**
     * Find element with retry mechanism
     * @param driver WebDriver instance
     * @param locator By locator
     * @param maxRetries Maximum number of retries
     * @param retryDelayMs Delay between retries in milliseconds
     * @return WebElement if found, null otherwise
     */
    public static WebElement findElementWithRetry(WebDriver driver, By locator, int maxRetries, long retryDelayMs) {
        WebElement element = null;
        for (int i = 0; i <= maxRetries; i++) {
            try {
                element = driver.findElement(locator);
                if (element != null) {
                    logger.debug("Element found on attempt {}", i + 1);
                    break;
                }
            } catch (Exception e) {
                if (i == maxRetries) {
                    logger.warn("Element not found after {} attempts", maxRetries + 1);
                } else {
                    logger.debug("Element not found on attempt {}, retrying...", i + 1);
                    try {
                        Thread.sleep(retryDelayMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
        return element;
    }
    
    /**
     * Get element text with null safety
     * @param element WebElement to get text from
     * @return Element text or empty string if null/error
     */
    public static String getElementTextSafely(WebElement element) {
        try {
            if (element != null) {
                String text = element.getText();
                return text != null ? text.trim() : "";
            }
            return "";
        } catch (Exception e) {
            logger.debug("Could not get element text safely", e);
            return "";
        }
    }
    
    /**
     * Get element attribute with null safety
     * @param element WebElement to get attribute from
     * @param attributeName Name of the attribute
     * @return Attribute value or empty string if null/error
     */
    public static String getElementAttributeSafely(WebElement element, String attributeName) {
        try {
            if (element != null && attributeName != null) {
                String attributeValue = element.getAttribute(attributeName);
                return attributeValue != null ? attributeValue : "";
            }
            return "";
        } catch (Exception e) {
            logger.debug("Could not get element attribute safely: {}", attributeName, e);
            return "";
        }
    }
    
    /**
     * Verify page object is properly initialized
     * @param pageObject Page object to verify
     * @return true if properly initialized, false otherwise
     */
    public static boolean verifyPageObjectInitialization(Object pageObject) {
        try {
            if (pageObject == null) {
                logger.warn("Page object is null");
                return false;
            }
            
            // Check if it's a BasePage or BaseComponent
            if (pageObject instanceof BasePage) {
                return ((BasePage) pageObject).isPageLoaded();
            } else if (pageObject instanceof BaseComponent) {
                return ((BaseComponent) pageObject).isComponentLoaded();
            } else {
                logger.debug("Page object type not recognized: {}", pageObject.getClass().getSimpleName());
                return true; // Assume it's properly initialized if it's not null
            }
        } catch (Exception e) {
            logger.error("Error verifying page object initialization", e);
            return false;
        }
    }
    
    /**
     * Create a dynamic locator using string formatting
     * @param locatorTemplate Locator template with placeholders (e.g., "//div[@id='%s']")
     * @param parameters Parameters to replace in template
     * @return By locator
     */
    public static By createDynamicLocator(String locatorTemplate, Object... parameters) {
        try {
            String formattedLocator = String.format(locatorTemplate, parameters);
            
            // Determine locator type and create appropriate By locator
            if (formattedLocator.startsWith("//") || formattedLocator.startsWith("(")) {
                return By.xpath(formattedLocator);
            } else if (formattedLocator.startsWith("#")) {
                return By.id(formattedLocator.substring(1));
            } else if (formattedLocator.startsWith(".")) {
                return By.className(formattedLocator.substring(1));
            } else {
                return By.cssSelector(formattedLocator);
            }
        } catch (Exception e) {
            logger.error("Failed to create dynamic locator from template: {}", locatorTemplate, e);
            throw new RuntimeException("Failed to create dynamic locator", e);
        }
    }
    
    /**
     * Wait for element to become stable (not changing position/size)
     * @param driver WebDriver instance
     * @param element WebElement to check
     * @param timeoutSeconds Timeout in seconds
     * @param stabilityDelayMs Time to wait for stability in milliseconds
     * @return true if element became stable, false otherwise
     */
    public static boolean waitForElementToBeStable(WebDriver driver, WebElement element, int timeoutSeconds, long stabilityDelayMs) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            
            wait.until(webDriver -> {
                try {
                    org.openqa.selenium.Point location1 = element.getLocation();
                    org.openqa.selenium.Dimension size1 = element.getSize();
                    
                    Thread.sleep(stabilityDelayMs);
                    
                    org.openqa.selenium.Point location2 = element.getLocation();
                    org.openqa.selenium.Dimension size2 = element.getSize();
                    
                    return location1.equals(location2) && size1.equals(size2);
                } catch (Exception e) {
                    return false;
                }
            });
            
            logger.debug("Element became stable");
            return true;
        } catch (Exception e) {
            logger.warn("Element did not become stable within {} seconds", timeoutSeconds);
            return false;
        }
    }
}

