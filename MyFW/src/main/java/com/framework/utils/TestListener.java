package com.framework.utils;

import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);


    @Override
   public void onTestStart(ITestResult result) {
        String testName=result.getMethod().getMethodName();
        String className=result.getTestClass().getName();
        logger.info("Starting test: {}.{}", className, testName);
    }

    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        logger.info("Test passed: {}.{}", className, testName);
    }

    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        Throwable throwable=result.getThrowable();
        if(throwable!=null){
            logger.info("Test case failed "+throwable.getMessage());
        }


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

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot(byte[] screenshot) {
        return screenshot;
    }


//    default void onTestSkipped(ITestResult result) {
//    }
//
//    default void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//    }
//
//    default void onTestFailedWithTimeout(ITestResult result) {
//        this.onTestFailure(result);
//    }
//
//    default void onStart(ITestContext context) {
//    }
//
//    default void onFinish(ITestContext context) {
//    }
}
