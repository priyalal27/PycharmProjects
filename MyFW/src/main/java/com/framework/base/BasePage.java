package com.framework.base;

import com.framework.interfaces.IPageActions;
import com.framework.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage implements IPageActions {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class);


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        PageFactory.initElements(driver, this);
        logger.info("Initialized {} page", this.getClass().getSimpleName());
    }


    @Override
    public void click(WebElement element) {
            try{waitForElementToBeClickable(element);}
            catch (Exception e){
                logger.error("Failed to click on element");
            }
    }

    @Override
    public void type(WebElement element, String text) {
        try{waitForElementToBeClickable(element);
            clear(element);
            element.sendKeys(text);
        }
        catch (Exception e){
            logger.error("Failed to click on element");
        }
    }

    @Override
    public void clear(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            element.clear();
            logger.info("Cleared element: {}", element.toString());
        } catch (Exception e) {
            logger.error("Failed to clear element: {}", element.toString(), e);
            throw new RuntimeException("Failed to clear element", e);
        }
    }

    @Override
    public void isDisplayed(WebElement element) {

    }

    @Override
    public void waitForElementToBeClickable(WebElement element) {

    }

    @Override
    public void waitForElementToBeVisible(WebElement element) {
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Element is visible");

        }
        catch (Exception e){
            logger.info("Element is not visible"+e.getMessage());
        }
    }

    @Override
    public byte[] takeScreenshots() {
        return new byte[0];
    }
}
