package com.framework.utils;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG listener for Allure reporting and screenshot capture
 * This demonstrates:
 * 1. Observer Pattern - Listening to test events
 * 2. Dependency Injection - Using framework utilities
 */
public class TestListener implements ITestListener {
    
    private static final Logger logger = LogManager.getLogger(TestListener.class);
    
    /**
     * Called when a test starts
     * @param result Test result object
     */
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        logger.info("Starting test: {}.{}", className, testName);
    }
    
    /**
     * Called when a test succeeds
     * @param result Test result object
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        logger.info("Test passed: {}.{}", className, testName);
    }
    
    /**
     * Called when a test fails
     * @param result Test result object
     */
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        Throwable throwable = result.getThrowable();
        
        logger.error("Test failed: {}.{}", className, testName);
        if (throwable != null) {
            logger.error("Failure reason: {}", throwable.getMessage(), throwable);
        }
        
        // Take screenshot on failure if configured
        if (ConfigReader.isScreenshotOnFailure()) {
            try {
                byte[] screenshot = DriverFactory.getDriver() != null ? 
                    ((org.openqa.selenium.TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(org.openqa.selenium.OutputType.BYTES) : null;
                
                if (screenshot != null) {
                    attachScreenshot(screenshot);
                    logger.info("Screenshot attached to Allure report for failed test: {}", testName);
                } else {
                    logger.warn("Could not capture screenshot - WebDriver instance is null");
                }
            } catch (Exception e) {
                logger.error("Failed to capture screenshot for test: {}", testName, e);
            }
        }
    }
    
    /**
     * Called when a test is skipped
     * @param result Test result object
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        Throwable throwable = result.getThrowable();
        
        logger.warn("Test skipped: {}.{}", className, testName);
        if (throwable != null) {
            logger.warn("Skip reason: {}", throwable.getMessage());
        }
    }
    
    /**
     * Called when a test fails but is within success percentage
     * @param result Test result object
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        logger.info("Test failed but within success percentage: {}.{}", className, testName);
    }
    
    /**
     * Attach screenshot to Allure report
     * @param screenshot Screenshot as byte array
     * @return Screenshot attachment
     */
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot(byte[] screenshot) {
        return screenshot;
    }
    
    /**
     * Attach text to Allure report
     * @param text Text to attach
     * @return Text attachment
     */
    @Attachment(value = "Text Log", type = "text/plain")
    public String attachText(String text) {
        return text;
    }
    
    /**
     * Get test execution time in milliseconds
     * @param result Test result object
     * @return Execution time in milliseconds
     */
    private long getExecutionTime(ITestResult result) {
        return result.getEndMillis() - result.getStartMillis();
    }
    
    /**
     * Get formatted test name
     * @param result Test result object
     * @return Formatted test name
     */
    private String getFormattedTestName(ITestResult result) {
        return result.getTestClass().getName() + "." + result.getMethod().getMethodName();
    }
}
