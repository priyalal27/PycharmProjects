package com.framework.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();


    private DriverFactory() {
        // Private constructor to prevent instantiation
    }

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
            }

        }
        catch (Exception e){
            logger.info("Message "+e.getMessage());
        }

        configureDriver(driver);

        driverThreadLocal.set(driver);

        return driver;
    }

    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            logger.error("WebDriver instance is null for current thread");
        }
        return driver;
    }

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

    public static WebDriver createChromeDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();

        if(ConfigReader.isHeadless()){
            options.addArguments("--headless");
            logger.info("Test cases running in headless mode");
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

    public static WebDriver createFirefoxDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();

        if(ConfigReader.isHeadless()){
            options.addArguments("--headless");
            logger.info("Test cases running in headless mode");
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

    public static WebDriver createEdgeDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();

        if(ConfigReader.isHeadless()){
            options.addArguments("--headless");
            logger.info("Test cases running in headless mode");
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

    public static WebDriver createSafariDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();

        if(ConfigReader.isHeadless()){
            options.addArguments("--headless");
            logger.info("Test cases running in headless mode");
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


}


