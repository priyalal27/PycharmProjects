package com.framework.utils;

import com.framework.exceptions.FrameworkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration reader utility class
 * This demonstrates Encapsulation - encapsulating configuration reading logic
 * and providing a clean interface to access configuration properties
 */
public class ConfigReader {
    
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
    
    // Static block to load properties once when class is loaded
    static {
        loadProperties();
    }
    
    /**
     * Private constructor to prevent instantiation (Utility class pattern)
     */
    private ConfigReader() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Load properties from config file
     */
    private static void loadProperties() {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();
            logger.info("Configuration properties loaded successfully from: {}", CONFIG_FILE_PATH);
        } catch (IOException e) {
            logger.error("Failed to load configuration properties from: {}", CONFIG_FILE_PATH, e);
            throw new FrameworkException("Failed to load configuration properties", e);
        }
    }
    
    /**
     * Get property value by key
     * @param key Property key
     * @return Property value
     */
    private static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            logger.error("Property '{}' not found or empty in configuration file", key);
            throw new FrameworkException("Property '" + key + "' not found or empty in configuration file");
        }
        return value.trim();
    }
    
    /**
     * Get property value by key with default value
     * @param key Property key
     * @param defaultValue Default value if property not found
     * @return Property value or default value
     */
    private static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            logger.warn("Property '{}' not found, using default value: {}", key, defaultValue);
            return defaultValue;
        }
        return value.trim();
    }
    
    /**
     * Get browser type from configuration
     * @return Browser type
     */
    public static String getBrowser() {
        return getProperty("browser", "chrome").toLowerCase();
    }
    
    /**
     * Get base URL from configuration
     * @return Base URL
     */
    public static String getBaseUrl() {
        return getProperty("base.url");
    }
    
    /**
     * Get implicit wait timeout
     * @return Implicit wait timeout in seconds
     */
    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait", "10"));
    }
    
    /**
     * Get explicit wait timeout
     * @return Explicit wait timeout in seconds
     */
    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait", "20"));
    }
    
    /**
     * Get page load timeout
     * @return Page load timeout in seconds
     */
    public static int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout", "30"));
    }
    
    /**
     * Check if headless mode is enabled
     * @return true if headless mode is enabled, false otherwise
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless", "false"));
    }
    
    /**
     * Check if maximize window is enabled
     * @return true if maximize window is enabled, false otherwise
     */
    public static boolean isMaximizeWindow() {
        return Boolean.parseBoolean(getProperty("maximize.window", "true"));
    }
    
    /**
     * Get screenshot on failure setting
     * @return true if screenshot on failure is enabled, false otherwise
     */
    public static boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(getProperty("screenshot.on.failure", "true"));
    }
    
    /**
     * Get test environment
     * @return Test environment (dev, staging, prod, etc.)
     */
    public static String getEnvironment() {
        return getProperty("environment", "dev").toLowerCase();
    }
    
    /**
     * Get retry count for failed tests
     * @return Retry count
     */
    public static int getRetryCount() {
        return Integer.parseInt(getProperty("retry.count", "1"));
    }
    
    /**
     * Get thread count for parallel execution
     * @return Thread count
     */
    public static int getThreadCount() {
        return Integer.parseInt(getProperty("thread.count", "1"));
    }
}
