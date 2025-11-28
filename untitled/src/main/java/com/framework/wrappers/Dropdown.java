package com.framework.wrappers;

import com.framework.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

/**
 * Dropdown wrapper class demonstrating Element Wrapper pattern in POM
 * This wrapper provides enhanced functionality for dropdown/select elements
 * 
 * Demonstrates:
 * 1. Wrapper Pattern - Enhanced functionality around Select WebElement
 * 2. Encapsulation - Dropdown specific operations encapsulated
 * 3. Single Responsibility - Only handles dropdown operations
 * 4. Reusability - Can be used across different page objects
 */
public class Dropdown {
    
    private static final Logger logger = LogManager.getLogger(Dropdown.class);
    private final WebElement element;
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String elementName;
    private final Select select;
    
    /**
     * Constructor
     * @param element WebElement representing the dropdown
     * @param driver WebDriver instance
     * @param elementName Name of the dropdown for logging purposes
     */
    public Dropdown(WebElement element, WebDriver driver, String elementName) {
        this.element = element;
        this.driver = driver;
        this.elementName = elementName != null ? elementName : "Dropdown";
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        this.select = new Select(element);
        logger.debug("Initialized {} wrapper", this.elementName);
    }
    
    /**
     * Constructor with default element name
     * @param element WebElement representing the dropdown
     * @param driver WebDriver instance
     */
    public Dropdown(WebElement element, WebDriver driver) {
        this(element, driver, "Dropdown");
    }
    
    /**
     * Select option by visible text
     * @param text Visible text of the option to select
     */
    public void selectByText(String text) {
        try {
            waitForClickable();
            select.selectByVisibleText(text);
            logger.info("{}: Selected option by text: '{}'", elementName, text);
        } catch (Exception e) {
            logger.error("{}: Failed to select option by text: '{}'", elementName, text, e);
            throw new RuntimeException("Failed to select option '" + text + "' in " + elementName, e);
        }
    }
    
    /**
     * Select option by value attribute
     * @param value Value attribute of the option to select
     */
    public void selectByValue(String value) {
        try {
            waitForClickable();
            select.selectByValue(value);
            logger.info("{}: Selected option by value: '{}'", elementName, value);
        } catch (Exception e) {
            logger.error("{}: Failed to select option by value: '{}'", elementName, value, e);
            throw new RuntimeException("Failed to select option with value '" + value + "' in " + elementName, e);
        }
    }
    
    /**
     * Select option by index
     * @param index Index of the option to select (0-based)
     */
    public void selectByIndex(int index) {
        try {
            waitForClickable();
            select.selectByIndex(index);
            logger.info("{}: Selected option by index: {}", elementName, index);
        } catch (Exception e) {
            logger.error("{}: Failed to select option by index: {}", elementName, index, e);
            throw new RuntimeException("Failed to select option at index " + index + " in " + elementName, e);
        }
    }
    
    /**
     * Deselect option by visible text (for multi-select dropdowns)
     * @param text Visible text of the option to deselect
     */
    public void deselectByText(String text) {
        try {
            waitForClickable();
            if (isMultiSelect()) {
                select.deselectByVisibleText(text);
                logger.info("{}: Deselected option by text: '{}'", elementName, text);
            } else {
                logger.warn("{}: Cannot deselect from single-select dropdown", elementName);
            }
        } catch (Exception e) {
            logger.error("{}: Failed to deselect option by text: '{}'", elementName, text, e);
            throw new RuntimeException("Failed to deselect option '" + text + "' in " + elementName, e);
        }
    }
    
    /**
     * Deselect option by value (for multi-select dropdowns)
     * @param value Value attribute of the option to deselect
     */
    public void deselectByValue(String value) {
        try {
            waitForClickable();
            if (isMultiSelect()) {
                select.deselectByValue(value);
                logger.info("{}: Deselected option by value: '{}'", elementName, value);
            } else {
                logger.warn("{}: Cannot deselect from single-select dropdown", elementName);
            }
        } catch (Exception e) {
            logger.error("{}: Failed to deselect option by value: '{}'", elementName, value, e);
            throw new RuntimeException("Failed to deselect option with value '" + value + "' in " + elementName, e);
        }
    }
    
    /**
     * Deselect option by index (for multi-select dropdowns)
     * @param index Index of the option to deselect (0-based)
     */
    public void deselectByIndex(int index) {
        try {
            waitForClickable();
            if (isMultiSelect()) {
                select.deselectByIndex(index);
                logger.info("{}: Deselected option by index: {}", elementName, index);
            } else {
                logger.warn("{}: Cannot deselect from single-select dropdown", elementName);
            }
        } catch (Exception e) {
            logger.error("{}: Failed to deselect option by index: {}", elementName, index, e);
            throw new RuntimeException("Failed to deselect option at index " + index + " in " + elementName, e);
        }
    }
    
    /**
     * Deselect all options (for multi-select dropdowns)
     */
    public void deselectAll() {
        try {
            waitForClickable();
            if (isMultiSelect()) {
                select.deselectAll();
                logger.info("{}: Deselected all options", elementName);
            } else {
                logger.warn("{}: Cannot deselect all from single-select dropdown", elementName);
            }
        } catch (Exception e) {
            logger.error("{}: Failed to deselect all options", elementName, e);
            throw new RuntimeException("Failed to deselect all options in " + elementName, e);
        }
    }
    
    /**
     * Get the first selected option
     * @return First selected option WebElement
     */
    public WebElement getFirstSelectedOption() {
        try {
            waitForVisible();
            WebElement selectedOption = select.getFirstSelectedOption();
            String selectedText = selectedOption.getText();
            logger.debug("{}: First selected option: '{}'", elementName, selectedText);
            return selectedOption;
        } catch (Exception e) {
            logger.error("{}: Failed to get first selected option", elementName, e);
            throw new RuntimeException("Failed to get first selected option from " + elementName, e);
        }
    }
    
    /**
     * Get all selected options (for multi-select dropdowns)
     * @return List of selected option WebElements
     */
    public List<WebElement> getAllSelectedOptions() {
        try {
            waitForVisible();
            List<WebElement> selectedOptions = select.getAllSelectedOptions();
            logger.debug("{}: Number of selected options: {}", elementName, selectedOptions.size());
            return selectedOptions;
        } catch (Exception e) {
            logger.error("{}: Failed to get all selected options", elementName, e);
            throw new RuntimeException("Failed to get all selected options from " + elementName, e);
        }
    }
    
    /**
     * Get all available options
     * @return List of all option WebElements
     */
    public List<WebElement> getAllOptions() {
        try {
            waitForVisible();
            List<WebElement> allOptions = select.getOptions();
            logger.debug("{}: Total number of options: {}", elementName, allOptions.size());
            return allOptions;
        } catch (Exception e) {
            logger.error("{}: Failed to get all options", elementName, e);
            throw new RuntimeException("Failed to get all options from " + elementName, e);
        }
    }
    
    /**
     * Get text of the first selected option
     * @return Text of selected option
     */
    public String getSelectedText() {
        try {
            String selectedText = getFirstSelectedOption().getText();
            logger.debug("{}: Selected option text: '{}'", elementName, selectedText);
            return selectedText;
        } catch (Exception e) {
            logger.error("{}: Failed to get selected option text", elementName, e);
            throw new RuntimeException("Failed to get selected text from " + elementName, e);
        }
    }
    
    /**
     * Get value of the first selected option
     * @return Value attribute of selected option
     */
    public String getSelectedValue() {
        try {
            String selectedValue = getFirstSelectedOption().getAttribute("value");
            logger.debug("{}: Selected option value: '{}'", elementName, selectedValue);
            return selectedValue;
        } catch (Exception e) {
            logger.error("{}: Failed to get selected option value", elementName, e);
            throw new RuntimeException("Failed to get selected value from " + elementName, e);
        }
    }
    
    /**
     * Get all option texts
     * @return List of all option texts
     */
    public List<String> getAllOptionTexts() {
        try {
            List<String> optionTexts = getAllOptions().stream()
                .map(WebElement::getText)
                .toList();
            logger.debug("{}: Retrieved {} option texts", elementName, optionTexts.size());
            return optionTexts;
        } catch (Exception e) {
            logger.error("{}: Failed to get all option texts", elementName, e);
            throw new RuntimeException("Failed to get all option texts from " + elementName, e);
        }
    }
    
    /**
     * Get all option values
     * @return List of all option values
     */
    public List<String> getAllOptionValues() {
        try {
            List<String> optionValues = getAllOptions().stream()
                .map(option -> option.getAttribute("value"))
                .toList();
            logger.debug("{}: Retrieved {} option values", elementName, optionValues.size());
            return optionValues;
        } catch (Exception e) {
            logger.error("{}: Failed to get all option values", elementName, e);
            throw new RuntimeException("Failed to get all option values from " + elementName, e);
        }
    }
    
    /**
     * Check if dropdown supports multiple selections
     * @return true if multi-select, false otherwise
     */
    public boolean isMultiSelect() {
        try {
            boolean multiSelect = select.isMultiple();
            logger.debug("{}: Is multi-select: {}", elementName, multiSelect);
            return multiSelect;
        } catch (Exception e) {
            logger.debug("{}: Could not determine multi-select status", elementName);
            return false;
        }
    }
    
    /**
     * Check if dropdown is displayed
     * @return true if displayed, false otherwise
     */
    public boolean isDisplayed() {
        try {
            boolean displayed = element.isDisplayed();
            logger.debug("{}: Dropdown displayed: {}", elementName, displayed);
            return displayed;
        } catch (Exception e) {
            logger.debug("{}: Dropdown not displayed", elementName);
            return false;
        }
    }
    
    /**
     * Check if dropdown is enabled
     * @return true if enabled, false otherwise
     */
    public boolean isEnabled() {
        try {
            boolean enabled = element.isEnabled();
            logger.debug("{}: Dropdown enabled: {}", elementName, enabled);
            return enabled;
        } catch (Exception e) {
            logger.debug("{}: Dropdown not enabled", elementName);
            return false;
        }
    }
    
    /**
     * Check if specific option exists by text
     * @param optionText Text of the option to check
     * @return true if option exists, false otherwise
     */
    public boolean hasOptionWithText(String optionText) {
        try {
            return getAllOptionTexts().contains(optionText);
        } catch (Exception e) {
            logger.debug("{}: Could not check for option with text '{}'", elementName, optionText);
            return false;
        }
    }
    
    /**
     * Check if specific option exists by value
     * @param optionValue Value of the option to check
     * @return true if option exists, false otherwise
     */
    public boolean hasOptionWithValue(String optionValue) {
        try {
            return getAllOptionValues().contains(optionValue);
        } catch (Exception e) {
            logger.debug("{}: Could not check for option with value '{}'", elementName, optionValue);
            return false;
        }
    }
    
    /**
     * Get number of available options
     * @return Number of options
     */
    public int getOptionsCount() {
        try {
            int count = getAllOptions().size();
            logger.debug("{}: Options count: {}", elementName, count);
            return count;
        } catch (Exception e) {
            logger.debug("{}: Could not get options count", elementName);
            return 0;
        }
    }
    
    /**
     * Wait for dropdown to be visible
     */
    public void waitForVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.debug("{}: Dropdown is visible", elementName);
        } catch (Exception e) {
            logger.error("{}: Dropdown is not visible within timeout", elementName, e);
            throw new RuntimeException(elementName + " is not visible", e);
        }
    }
    
    /**
     * Wait for dropdown to be clickable
     */
    public void waitForClickable() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.debug("{}: Dropdown is clickable", elementName);
        } catch (Exception e) {
            logger.error("{}: Dropdown is not clickable within timeout", elementName, e);
            throw new RuntimeException(elementName + " is not clickable", e);
        }
    }
    
    /**
     * Wait for specific option to be available
     * @param optionText Text of the option to wait for
     */
    public void waitForOptionToBeAvailable(String optionText) {
        try {
            wait.until(driver -> hasOptionWithText(optionText));
            logger.debug("{}: Option '{}' is available", elementName, optionText);
        } catch (Exception e) {
            logger.error("{}: Option '{}' is not available within timeout", elementName, optionText, e);
            throw new RuntimeException("Option '" + optionText + "' is not available in " + elementName, e);
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
     * Get the underlying Select object
     * @return Select object
     */
    public Select getSelect() {
        return select;
    }
    
    /**
     * Get element name
     * @return Element name
     */
    public String getElementName() {
        return elementName;
    }
}

