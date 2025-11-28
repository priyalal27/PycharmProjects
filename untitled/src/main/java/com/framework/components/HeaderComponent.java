package com.framework.components;

import com.framework.base.BaseComponent;
import com.framework.pages.DashboardPage;
import com.framework.pages.LoginPage;
import com.framework.pages.SearchPage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Header Component demonstrating Component-based POM pattern
 * This component can be reused across multiple pages that have the same header
 * 
 * Demonstrates:
 * 1. Component Reusability - Header logic separated from page logic
 * 2. Inheritance - Extends BaseComponent
 * 3. Encapsulation - Header-specific operations encapsulated
 * 4. Single Responsibility - Only handles header functionality
 */
public class HeaderComponent extends BaseComponent {
    
    private static final Logger logger = LogManager.getLogger(HeaderComponent.class);
    
    // Header elements using Page Factory pattern
    @FindBy(className = "header-container")
    private WebElement headerContainer;
    
    @FindBy(id = "logo")
    private WebElement logo;
    
    @FindBy(id = "user-menu")
    private WebElement userMenu;
    
    @FindBy(id = "user-avatar")
    private WebElement userAvatar;
    
    @FindBy(className = "user-name")
    private WebElement userName;
    
    @FindBy(id = "logout-link")
    private WebElement logoutLink;
    
    @FindBy(id = "profile-link")
    private WebElement profileLink;
    
    @FindBy(id = "settings-link")
    private WebElement settingsLink;
    
    @FindBy(className = "search-header")
    private WebElement headerSearchBox;
    
    @FindBy(id = "search-header-button")
    private WebElement headerSearchButton;
    
    @FindBy(id = "notifications-icon")
    private WebElement notificationsIcon;
    
    @FindBy(className = "notification-badge")
    private WebElement notificationBadge;
    
    @FindBy(id = "help-icon")
    private WebElement helpIcon;
    
    @FindBy(xpath = "//nav[@class='main-navigation']//a")
    private List<WebElement> navigationLinks;
    
    @FindBy(id = "breadcrumb")
    private WebElement breadcrumb;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public HeaderComponent(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Click on company logo
     * @return DashboardPage (assuming logo navigates to dashboard)
     */
    @Step("Click on company logo")
    public DashboardPage clickLogo() {
        click(logo);
        logger.info("Clicked on company logo");
        return new DashboardPage(driver);
    }
    
    /**
     * Click on user avatar to open user menu
     * @return HeaderComponent for method chaining
     */
    @Step("Click on user avatar")
    public HeaderComponent clickUserAvatar() {
        click(userAvatar);
        logger.info("Clicked on user avatar");
        return this;
    }
    
    /**
     * Get logged-in user name
     * @return User name text
     */
    @Step("Get user name from header")
    public String getUserName() {
        String name = getText(userName);
        logger.info("Retrieved user name from header: {}", name);
        return name;
    }
    
    /**
     * Logout from header
     * @return LoginPage
     */
    @Step("Logout from header")
    public LoginPage logout() {
        clickUserAvatar(); // Open user menu first
        click(logoutLink);
        logger.info("Logged out from header");
        return new LoginPage(driver);
    }
    
    /**
     * Click on profile link in user menu
     * @return HeaderComponent for method chaining
     */
    @Step("Click on profile link in header")
    public HeaderComponent clickProfile() {
        clickUserAvatar(); // Open user menu first
        click(profileLink);
        logger.info("Clicked on profile link");
        return this;
    }
    
    /**
     * Click on settings link in user menu
     * @return HeaderComponent for method chaining
     */
    @Step("Click on settings link in header")
    public HeaderComponent clickSettings() {
        clickUserAvatar(); // Open user menu first
        click(settingsLink);
        logger.info("Clicked on settings link");
        return this;
    }
    
    /**
     * Perform search from header
     * @param searchTerm Search term to search for
     * @return SearchPage
     */
    @Step("Search from header: {searchTerm}")
    public SearchPage searchFromHeader(String searchTerm) {
        type(headerSearchBox, searchTerm);
        click(headerSearchButton);
        logger.info("Performed search from header: {}", searchTerm);
        return new SearchPage(driver);
    }
    
    /**
     * Click on notifications icon
     * @return HeaderComponent for method chaining
     */
    @Step("Click on notifications icon")
    public HeaderComponent clickNotifications() {
        click(notificationsIcon);
        logger.info("Clicked on notifications icon");
        return this;
    }
    
    /**
     * Get notification count from badge
     * @return Notification count
     */
    @Step("Get notification count from header")
    public int getNotificationCount() {
        try {
            String countText = getText(notificationBadge);
            int count = Integer.parseInt(countText);
            logger.info("Notification count from header: {}", count);
            return count;
        } catch (Exception e) {
            logger.info("No notification count available or count is 0");
            return 0;
        }
    }
    
    /**
     * Click on help icon
     * @return HeaderComponent for method chaining
     */
    @Step("Click on help icon")
    public HeaderComponent clickHelp() {
        click(helpIcon);
        logger.info("Clicked on help icon");
        return this;
    }
    
    /**
     * Click on navigation link by text
     * @param linkText Text of the navigation link
     * @return HeaderComponent for method chaining
     */
    @Step("Click on navigation link: {linkText}")
    public HeaderComponent clickNavigationLink(String linkText) {
        for (WebElement link : navigationLinks) {
            if (getText(link).equalsIgnoreCase(linkText)) {
                click(link);
                logger.info("Clicked on navigation link: {}", linkText);
                return this;
            }
        }
        logger.warn("Navigation link '{}' not found", linkText);
        return this;
    }
    
    /**
     * Get all navigation link texts
     * @return List of navigation link texts
     */
    @Step("Get all navigation links")
    public List<String> getAllNavigationLinks() {
        return navigationLinks.stream()
                .map(this::getText)
                .toList();
    }
    
    /**
     * Get breadcrumb text
     * @return Breadcrumb navigation text
     */
    @Step("Get breadcrumb navigation")
    public String getBreadcrumb() {
        String breadcrumbText = getText(breadcrumb);
        logger.info("Breadcrumb navigation: {}", breadcrumbText);
        return breadcrumbText;
    }
    
    /**
     * Check if user is logged in (by checking if user menu is present)
     * @return true if user is logged in, false otherwise
     */
    public boolean isUserLoggedIn() {
        boolean loggedIn = isDisplayed(userMenu);
        logger.info("User logged in status: {}", loggedIn);
        return loggedIn;
    }
    
    /**
     * Check if notifications badge is visible
     * @return true if notification badge is visible, false otherwise
     */
    public boolean isNotificationBadgeVisible() {
        return isDisplayed(notificationBadge);
    }
    
    /**
     * Check if search box is available in header
     * @return true if header search is available, false otherwise
     */
    public boolean isHeaderSearchAvailable() {
        return isDisplayed(headerSearchBox);
    }
    
    /**
     * Implementation of abstract method from BaseComponent
     * @return true if header component is loaded, false otherwise
     */
    @Override
    public boolean isComponentLoaded() {
        try {
            boolean loaded = isDisplayed(headerContainer) && isDisplayed(logo);
            logger.info("Header component loaded status: {}", loaded);
            return loaded;
        } catch (Exception e) {
            logger.error("Error checking if header component is loaded", e);
            return false;
        }
    }
    
    /**
     * Verify all essential header elements are present
     * @return true if all essential elements are present, false otherwise
     */
    @Step("Verify header elements")
    public boolean verifyHeaderElements() {
        boolean allPresent = isDisplayed(headerContainer) &&
                           isDisplayed(logo) &&
                           isDisplayed(userMenu);
        
        logger.info("Header elements verification: {}", allPresent);
        return allPresent;
    }
}
