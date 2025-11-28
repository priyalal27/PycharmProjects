package com.framework.utils;

import com.framework.exceptions.FrameworkException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

/**
 * Driver factory utility class using Factory Design Pattern
 * This demonstrates:
 * 1. Factory Pattern - Creating driver instances based on browser type
 * 2. Encapsulation - Encapsulating driver creation logic
 * 3. Inheritance - Different driver classes inherit from WebDriver interface
 */
public class DriverFactory {
    
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    
    /**
     * Private constructor to prevent instantiation (Utility class pattern)
     */
    private DriverFactory() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Create and initialize WebDriver instance based on browser type
     * @param browserType Type of browser (chrome, firefox, edge, safari)
     * @return WebDriver instance
     */
    public static WebDriver createDriver(String browserType) {
        WebDriver driver = null;
        
        try {
            switch (browserType.toLowerCase()) {
                case "chrome":
                    driver = createChromeDriver();
                    break;
                case "firefox":
                    driver = createFirefoxDriver();
                    break;
                case "edge":
                    driver = createEdgeDriver();
                    break;
                case "safari":
                    driver = createSafariDriver();
                    break;
                default:
                    logger.error("Unsupported browser type: {}", browserType);
                    throw new FrameworkException("Unsupported browser type: " + browserType);
            }
            
            // Configure driver settings
            configureDriver(driver);
            
            // Set driver to ThreadLocal for parallel execution
            driverThreadLocal.set(driver);
            
            logger.info("WebDriver instance created successfully for browser: {}", browserType);
            return driver;
            
        } catch (Exception e) {
            logger.error("Failed to create WebDriver instance for browser: {}", browserType, e);
            throw new FrameworkException("Failed to create WebDriver instance", e);
        }
    }
    
    /**
     * Create Chrome driver with options
     * @return ChromeDriver instance
     */
    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        // Add Chrome options based on configuration
        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless");
            logger.info("Chrome browser will run in headless mode");
        }
        
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-features=VizDisplayCompositor");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins");
        options.addArguments("--disable-images");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-translate");
        options.addArguments("--disable-default-apps");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        return new ChromeDriver(options);
    }
    
    /**
     * Create Firefox driver with options
     * @return FirefoxDriver instance
     */
    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        
        // Add Firefox options based on configuration
        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless");
            logger.info("Firefox browser will run in headless mode");
        }
        
        return new FirefoxDriver(options);
    }
    
    /**
     * Create Edge driver with options
     * @return EdgeDriver instance
     */
    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        
        // Add Edge options based on configuration
        if (ConfigReader.isHeadless()) {
            options.addArguments("--headless");
            logger.info("Edge browser will run in headless mode");
        }
        
        return new EdgeDriver(options);
    }
    
    /**
     * Create Safari driver
     * @return SafariDriver instance
     */
    private static WebDriver createSafariDriver() {
        // Safari doesn't require WebDriverManager setup
        logger.info("Creating Safari driver (headless mode not supported)");
        return new SafariDriver();
    }
    
    /**
     * Configure common driver settings
     * @param driver WebDriver instance to configure
     */
    private static void configureDriver(WebDriver driver) {
        // Set timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
        
        // Maximize window if configured
        if (ConfigReader.isMaximizeWindow()) {
            driver.manage().window().maximize();
            logger.info("Browser window maximized");
        }
    }
    
    /**
     * Get current thread's WebDriver instance
     * @return WebDriver instance for current thread
     */
    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            logger.error("WebDriver instance is null for current thread");
            throw new FrameworkException("WebDriver instance is null. Please initialize driver first.");
        }
        return driver;
    }
    
    /**
     * Quit driver and remove from ThreadLocal
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
                logger.info("WebDriver instance quit successfully");
            } catch (Exception e) {
                logger.error("Error occurred while quitting WebDriver", e);
            } finally {
                driverThreadLocal.remove();
                logger.info("WebDriver instance removed from ThreadLocal");
            }
        }
    }
    
    /**
     * Close current browser window
     */
    public static void closeDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.close();
                logger.info("Current browser window closed successfully");
            } catch (Exception e) {
                logger.error("Error occurred while closing browser window", e);
            }
        }
    }
}
