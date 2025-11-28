package com.framework.interfaces;

import org.openqa.selenium.WebElement;

public interface IPageActions {

    void click(WebElement element);

    void type(WebElement element,String text);

    void clear(WebElement element);

    void isDisplayed(WebElement element);

    void waitForElementToBeClickable(WebElement element);

    void waitForElementToBeVisible(WebElement element);

    byte[] takeScreenshots();
}
