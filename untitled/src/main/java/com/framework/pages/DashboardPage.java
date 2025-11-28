package com.framework.pages;

import com.framework.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Dashboard Page class demonstrating Page Object Model with Page Factory
 * This demonstrates:
 * 1. Inheritance - Extends BasePage
 * 2. Encapsulation - Private WebElements, public methods
 * 3. Page Factory Pattern - Using @FindBy annotations
 */
public class DashboardPage extends BasePage {
    
    private static final Logger logger = LogManager.getLogger(DashboardPage.class);
    
    // Page elements using Page Factory pattern (Encapsulation)
    @FindBy(className = "welcome-message")
    private WebElement welcomeMessage;
    
    @FindBy(id = "user-profile")
    private WebElement userProfile;
    
    @FindBy(id = "logout-button")
    private WebElement logoutButton;
    
    @FindBy(className = "search-box")
    private WebElement searchBox;
    
    @FindBy(id = "search-button")
    private WebElement searchButton;
    
    @FindBy(xpath = "//nav//a")
    private List<WebElement> navigationMenuItems;
    
    @FindBy(className = "dashboard-title")
    private WebElement dashboardTitle;
    
    @FindBy(id = "notifications")
    private WebElement notificationsIcon;
    
    @FindBy(className = "notification-count")
    private WebElement notificationCount;
    
    @FindBy(xpath = "//div[@class='widget']")
    private List<WebElement> dashboardWidgets;
    
    @FindBy(id = "settings-link")
    private WebElement settingsLink;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Get welcome message text
     * @return Welcome message text
     */
    @Step("Get welcome message")
    public String getWelcomeMessage() {
        String message = getText(welcomeMessage);
        logger.info("Welcome message: {}", message);
        return message;
    }
    
    /**
     * Get dashboard title
     * @return Dashboard title text
     */
    @Step("Get dashboard title")
    public String getDashboardTitle() {
        String title = getText(dashboardTitle);
        logger.info("Dashboard title: {}", title);
        return title;
    }
    
    /**
     * Click logout button
     * @return LoginPage instance
     */
    @Step("Click logout button")
    public LoginPage logout() {
        click(logoutButton);
        logger.info("Clicked logout button");
        return new LoginPage(driver);
    }
    
    /**
     * Click user profile
     * @return DashboardPage instance for method chaining
     */
    @Step("Click user profile")
    public DashboardPage clickUserProfile() {
        click(userProfile);
        logger.info("Clicked user profile");
        return this;
    }
    
    /**
     * Perform search operation
     * @param searchTerm Search term to search for
     * @return SearchPage instance
     */
    @Step("Search for: {searchTerm}")
    public SearchPage search(String searchTerm) {
        type(searchBox, searchTerm);
        click(searchButton);
        logger.info("Searched for: {}", searchTerm);
        return new SearchPage(driver);
    }
    
    /**
     * Enter search term without clicking search button
     * @param searchTerm Search term to enter
     * @return DashboardPage instance for method chaining
     */
    @Step("Enter search term: {searchTerm}")
    public DashboardPage enterSearchTerm(String searchTerm) {
        type(searchBox, searchTerm);
        logger.info("Entered search term: {}", searchTerm);
        return this;
    }
    
    /**
     * Click search button
     * @return SearchPage instance
     */
    @Step("Click search button")
    public SearchPage clickSearch() {
        click(searchButton);
        logger.info("Clicked search button");
        return new SearchPage(driver);
    }
    
    /**
     * Click notifications icon
     * @return DashboardPage instance for method chaining
     */
    @Step("Click notifications icon")
    public DashboardPage clickNotifications() {
        click(notificationsIcon);
        logger.info("Clicked notifications icon");
        return this;
    }
    
    /**
     * Get notification count
     * @return Notification count as integer
     */
    @Step("Get notification count")
    public int getNotificationCount() {
        try {
            String countText = getText(notificationCount);
            int count = Integer.parseInt(countText);
            logger.info("Notification count: {}", count);
            return count;
        } catch (Exception e) {
            logger.warn("Could not parse notification count, returning 0", e);
            return 0;
        }
    }
    
    /**
     * Click settings link
     * @return DashboardPage instance for method chaining
     */
    @Step("Click settings link")
    public DashboardPage clickSettings() {
        click(settingsLink);
        logger.info("Clicked settings link");
        return this;
    }
    
    /**
     * Get navigation menu items count
     * @return Number of navigation menu items
     */
    @Step("Get navigation menu items count")
    public int getNavigationMenuItemsCount() {
        int count = navigationMenuItems.size();
        logger.info("Navigation menu items count: {}", count);
        return count;
    }
    
    /**
     * Click navigation menu item by text
     * @param menuItemText Text of the menu item to click
     * @return DashboardPage instance for method chaining
     */
    @Step("Click navigation menu item: {menuItemText}")
    public DashboardPage clickNavigationMenuItem(String menuItemText) {
        for (WebElement menuItem : navigationMenuItems) {
            if (getText(menuItem).equalsIgnoreCase(menuItemText)) {
                click(menuItem);
                logger.info("Clicked navigation menu item: {}", menuItemText);
                return this;
            }
        }
        logger.warn("Navigation menu item not found: {}", menuItemText);
        return this;
    }
    
    /**
     * Get dashboard widgets count
     * @return Number of dashboard widgets
     */
    @Step("Get dashboard widgets count")
    public int getDashboardWidgetsCount() {
        int count = dashboardWidgets.size();
        logger.info("Dashboard widgets count: {}", count);
        return count;
    }
    
    /**
     * Check if search box is displayed
     * @return true if search box is displayed, false otherwise
     */
    public boolean isSearchBoxDisplayed() {
        return isDisplayed(searchBox);
    }
    
    /**
     * Check if logout button is displayed
     * @return true if logout button is displayed, false otherwise
     */
    public boolean isLogoutButtonDisplayed() {
        return isDisplayed(logoutButton);
    }
    
    /**
     * Check if user profile is displayed
     * @return true if user profile is displayed, false otherwise
     */
    public boolean isUserProfileDisplayed() {
        return isDisplayed(userProfile);
    }
    
    /**
     * Check if welcome message is displayed
     * @return true if welcome message is displayed, false otherwise
     */
    public boolean isWelcomeMessageDisplayed() {
        return isDisplayed(welcomeMessage);
    }
    
    /**
     * Implementation of abstract method from BasePage
     * Checks if the dashboard page is loaded by verifying key elements
     * @return true if page is loaded, false otherwise
     */
    @Override
    public boolean isPageLoaded() {
        try {
            boolean isLoaded = isDisplayed(welcomeMessage) && 
                             isDisplayed(userProfile) && 
                             isDisplayed(logoutButton);
            logger.info("Dashboard page loaded status: {}", isLoaded);
            return isLoaded;
        } catch (Exception e) {
            logger.error("Error checking if dashboard page is loaded", e);
            return false;
        }
    }
}
