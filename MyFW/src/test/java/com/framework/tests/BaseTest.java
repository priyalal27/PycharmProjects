package com.framework.tests;

import com.framework.utils.ConfigReader;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {


    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;

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


}
