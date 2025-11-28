package com.framework.tests;

import com.framework.utils.ConfigReader;
import com.framework.utils.DriverFactory;
import com.framework.utils.TestListener;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

/**
 * Base test class providing common setup and teardown functionality
 * This demonstrates:
 * 1. Inheritance - Other test classes will extend this class
 * 2. Template Method Pattern - Defining common test structure
 * 3. Encapsulation - Protected access to common test utilities
 */
@Listeners(TestListener.class)
public abstract class BaseTest {
    
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;
    
    /**
     * Suite level setup - runs once before all tests in the suite
     */
    @BeforeSuite(alwaysRun = true)
    @Step("Suite Setup")
    public void suiteSetup() {
        logger.info("=== Starting Test Suite ===");
        logger.info("Test Environment: {}", ConfigReader.getEnvironment());
        logger.info("Base URL: {}", ConfigReader.getBaseUrl());
        logger.info("Browser: {}", ConfigReader.getBrowser());
        logger.info("Headless Mode: {}", ConfigReader.isHeadless());
        logger.info("Thread Count: {}", ConfigReader.getThreadCount());
        logger.info("=== Suite Setup Completed ===");
    }
    
    /**
     * Test level setup - runs before each test method
     */
    @BeforeMethod(alwaysRun = true)
    @Step("Test Setup")
    public void setUp() {
        logger.info("Setting up test: {}", getTestMethodName());
        
        try {
            // Create driver instance
            driver = DriverFactory.createDriver(ConfigReader.getBrowser());
            logger.info("WebDriver initialized successfully for test: {}", getTestMethodName());
            
            // Navigate to base URL
            driver.get(ConfigReader.getBaseUrl());
            logger.info("Navigated to base URL: {}", ConfigReader.getBaseUrl());
            
        } catch (Exception e) {
            logger.error("Failed to setup test: {}", getTestMethodName(), e);
            throw new RuntimeException("Test setup failed", e);
        }
    }
    
    /**
     * Test level teardown - runs after each test method
     */
    @AfterMethod(alwaysRun = true)
    @Step("Test Teardown")
    public void tearDown() {
        logger.info("Tearing down test: {}", getTestMethodName());
        
        try {
            // Quit driver if it exists
            if (driver != null) {
                DriverFactory.quitDriver();
                logger.info("WebDriver quit successfully for test: {}", getTestMethodName());
            }
        } catch (Exception e) {
            logger.error("Error during test teardown for test: {}", getTestMethodName(), e);
        }
        
        logger.info("Test teardown completed: {}", getTestMethodName());
    }
    
    /**
     * Suite level teardown - runs once after all tests in the suite
     */
    @AfterSuite(alwaysRun = true)
    @Step("Suite Teardown")
    public void suiteTeardown() {
        logger.info("=== Test Suite Completed ===");
    }
    
    /**
     * Class level setup - runs once before all test methods in the test class
     * Can be overridden by child test classes for specific setup requirements
     */
    @BeforeClass(alwaysRun = true)
    @Step("Class Setup")
    public void classSetup() {
        logger.info("Setting up test class: {}", this.getClass().getSimpleName());
    }
    
    /**
     * Class level teardown - runs once after all test methods in the test class
     * Can be overridden by child test classes for specific cleanup requirements
     */
    @AfterClass(alwaysRun = true)
    @Step("Class Teardown")
    public void classTeardown() {
        logger.info("Tearing down test class: {}", this.getClass().getSimpleName());
    }
    
    /**
     * Get the current test method name
     * This is a utility method that can be used by child classes
     * @return Current test method name
     */
    protected String getTestMethodName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }
    
    /**
     * Get the WebDriver instance
     * This provides controlled access to the driver instance
     * @return WebDriver instance
     */
    protected WebDriver getDriver() {
        if (driver == null) {
            logger.error("WebDriver instance is null");
            throw new RuntimeException("WebDriver instance is null. Test setup may have failed.");
        }
        return driver;
    }
    
    /**
     * Wait for a specified amount of time
     * This is a utility method for explicit waits in tests
     * @param milliseconds Time to wait in milliseconds
     */
    protected void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
            logger.debug("Waited for {} milliseconds", milliseconds);
        } catch (InterruptedException e) {
            logger.warn("Wait interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Log test step information
     * This is a utility method for logging test steps
     * @param stepDescription Description of the test step
     */
    @Step("{stepDescription}")
    protected void logTestStep(String stepDescription) {
        logger.info("Test Step: {}", stepDescription);
    }
    
    /**
     * Abstract method that child classes can implement for test-specific data setup
     * This demonstrates the Template Method pattern
     */
    protected void setupTestData() {
        // Default implementation - can be overridden by child classes
        logger.debug("Setting up test data for: {}", this.getClass().getSimpleName());
    }
    
    /**
     * Abstract method that child classes can implement for test-specific data cleanup
     * This demonstrates the Template Method pattern
     */
    protected void cleanupTestData() {
        // Default implementation - can be overridden by child classes
        logger.debug("Cleaning up test data for: {}", this.getClass().getSimpleName());
    }
    
    /**
     * Verify if the current URL contains expected text
     * @param expectedUrlPart Expected part of URL
     * @return true if URL contains expected text, false otherwise
     */
    @Step("Verify URL contains: {expectedUrlPart}")
    protected boolean verifyUrlContains(String expectedUrlPart) {
        String currentUrl = getDriver().getCurrentUrl();
        boolean contains = currentUrl.contains(expectedUrlPart);
        logger.info("URL verification - Current URL: {}, Expected part: {}, Contains: {}", 
                   currentUrl, expectedUrlPart, contains);
        return contains;
    }
    
    /**
     * Verify if the current page title contains expected text
     * @param expectedTitlePart Expected part of title
     * @return true if title contains expected text, false otherwise
     */
    @Step("Verify page title contains: {expectedTitlePart}")
    protected boolean verifyTitleContains(String expectedTitlePart) {
        String currentTitle = getDriver().getTitle();
        boolean contains = currentTitle.contains(expectedTitlePart);
        logger.info("Title verification - Current title: {}, Expected part: {}, Contains: {}", 
                   currentTitle, expectedTitlePart, contains);
        return contains;
    }
}
