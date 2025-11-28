package com.framework.components;

import com.framework.base.BaseComponent;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Navigation Component demonstrating Component-based POM pattern
 * This component handles main navigation functionality across multiple pages
 * 
 * Demonstrates:
 * 1. Component Reusability - Navigation logic separated from page logic
 * 2. Inheritance - Extends BaseComponent
 * 3. Encapsulation - Navigation-specific operations encapsulated
 * 4. Single Responsibility - Only handles navigation functionality
 */
public class NavigationComponent extends BaseComponent {
    
    private static final Logger logger = LogManager.getLogger(NavigationComponent.class);
    
    // Navigation elements using Page Factory pattern
    @FindBy(id = "main-navigation")
    private WebElement navigationContainer;
    
    @FindBy(className = "nav-toggle")
    private WebElement navigationToggle;
    
    @FindBy(xpath = "//nav[@id='main-navigation']//a")
    private List<WebElement> navigationItems;
    
    @FindBy(xpath = "//a[contains(@href, '/dashboard')]")
    private WebElement dashboardLink;
    
    @FindBy(xpath = "//a[contains(@href, '/profile')]")
    private WebElement profileLink;
    
    @FindBy(xpath = "//a[contains(@href, '/reports')]")
    private WebElement reportsLink;
    
    @FindBy(xpath = "//a[contains(@href, '/settings')]")
    private WebElement settingsLink;
    
    @FindBy(xpath = "//a[contains(@href, '/analytics')]")
    private WebElement analyticsLink;
    
    @FindBy(xpath = "//a[contains(@href, '/users')]")
    private WebElement usersLink;
    
    @FindBy(xpath = "//a[contains(@href, '/admin')]")
    private WebElement adminLink;
    
    @FindBy(className = "nav-search")
    private WebElement navigationSearch;
    
    @FindBy(id = "nav-search-input")
    private WebElement navigationSearchInput;
    
    @FindBy(id = "nav-search-button")
    private WebElement navigationSearchButton;
    
    @FindBy(className = "active-nav-item")
    private WebElement activeNavigationItem;
    
    @FindBy(className = "dropdown-menu")
    private List<WebElement> dropdownMenus;
    
    @FindBy(className = "has-dropdown")
    private List<WebElement> dropdownToggleItems;
    
    @FindBy(className = "mobile-nav")
    private WebElement mobileNavigation;
    
    @FindBy(className = "nav-breadcrumb")
    private WebElement navigationBreadcrumb;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public NavigationComponent(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Click on Dashboard navigation link
     * @return NavigationComponent for method chaining
     */
    @Step("Navigate to Dashboard")
    public NavigationComponent navigateToDashboard() {
        click(dashboardLink);
        logger.info("Navigated to Dashboard");
        return this;
    }
    
    /**
     * Click on Profile navigation link
     * @return NavigationComponent for method chaining
     */
    @Step("Navigate to Profile")
    public NavigationComponent navigateToProfile() {
        click(profileLink);
        logger.info("Navigated to Profile");
        return this;
    }
    
    /**
     * Click on Reports navigation link
     * @return NavigationComponent for method chaining
     */
    @Step("Navigate to Reports")
    public NavigationComponent navigateToReports() {
        click(reportsLink);
        logger.info("Navigated to Reports");
        return this;
    }
    
    /**
     * Click on Settings navigation link
     * @return NavigationComponent for method chaining
     */
    @Step("Navigate to Settings")
    public NavigationComponent navigateToSettings() {
        click(settingsLink);
        logger.info("Navigated to Settings");
        return this;
    }
    
    /**
     * Click on Analytics navigation link
     * @return NavigationComponent for method chaining
     */
    @Step("Navigate to Analytics")
    public NavigationComponent navigateToAnalytics() {
        click(analyticsLink);
        logger.info("Navigated to Analytics");
        return this;
    }
    
    /**
     * Click on Users navigation link (admin functionality)
     * @return NavigationComponent for method chaining
     */
    @Step("Navigate to Users")
    public NavigationComponent navigateToUsers() {
        click(usersLink);
        logger.info("Navigated to Users");
        return this;
    }
    
    /**
     * Click on Admin navigation link
     * @return NavigationComponent for method chaining
     */
    @Step("Navigate to Admin")
    public NavigationComponent navigateToAdmin() {
        click(adminLink);
        logger.info("Navigated to Admin");
        return this;
    }
    
    /**
     * Navigate to specific page by link text
     * @param linkText Text of the navigation link
     * @return NavigationComponent for method chaining
     */
    @Step("Navigate to page: {linkText}")
    public NavigationComponent navigateToPage(String linkText) {
        for (WebElement navItem : navigationItems) {
            if (getText(navItem).equalsIgnoreCase(linkText)) {
                click(navItem);
                logger.info("Navigated to page: {}", linkText);
                return this;
            }
        }
        logger.warn("Navigation link '{}' not found", linkText);
        return this;
    }
    
    /**
     * Get all navigation items text
     * @return List of navigation item texts
     */
    @Step("Get all navigation items")
    public List<String> getAllNavigationItems() {
        List<String> navTexts = navigationItems.stream()
                .filter(item -> !getText(item).isEmpty())
                .map(this::getText)
                .toList();
        logger.info("Retrieved {} navigation items", navTexts.size());
        return navTexts;
    }
    
    /**
     * Get currently active navigation item
     * @return Text of active navigation item
     */
    @Step("Get active navigation item")
    public String getActiveNavigationItem() {
        if (isDisplayed(activeNavigationItem)) {
            String activeItem = getText(activeNavigationItem);
            logger.info("Active navigation item: {}", activeItem);
            return activeItem;
        }
        logger.info("No active navigation item found");
        return "";
    }
    
    /**
     * Toggle mobile navigation
     * @return NavigationComponent for method chaining
     */
    @Step("Toggle mobile navigation")
    public NavigationComponent toggleMobileNavigation() {
        click(navigationToggle);
        logger.info("Toggled mobile navigation");
        return this;
    }
    
    /**
     * Check if mobile navigation is displayed
     * @return true if mobile navigation is displayed, false otherwise
     */
    public boolean isMobileNavigationDisplayed() {
        boolean displayed = isDisplayed(mobileNavigation);
        logger.info("Mobile navigation displayed: {}", displayed);
        return displayed;
    }
    
    /**
     * Perform search from navigation
     * @param searchTerm Search term
     * @return NavigationComponent for method chaining
     */
    @Step("Search from navigation: {searchTerm}")
    public NavigationComponent searchFromNavigation(String searchTerm) {
        type(navigationSearchInput, searchTerm);
        click(navigationSearchButton);
        logger.info("Searched from navigation: {}", searchTerm);
        return this;
    }
    
    /**
     * Hover over dropdown navigation item to show dropdown
     * @param itemText Text of dropdown item to hover over
     * @return NavigationComponent for method chaining
     */
    @Step("Hover over dropdown item: {itemText}")
    public NavigationComponent hoverOverDropdown(String itemText) {
        for (WebElement dropdownItem : dropdownToggleItems) {
            if (getText(dropdownItem).equalsIgnoreCase(itemText)) {
                // Use Actions class to hover
                org.openqa.selenium.interactions.Actions actions = 
                    new org.openqa.selenium.interactions.Actions(driver);
                actions.moveToElement(dropdownItem).perform();
                logger.info("Hovered over dropdown item: {}", itemText);
                return this;
            }
        }
        logger.warn("Dropdown item '{}' not found", itemText);
        return this;
    }
    
    /**
     * Click on dropdown menu item
     * @param parentItemText Parent dropdown item text
     * @param childItemText Child dropdown item text
     * @return NavigationComponent for method chaining
     */
    @Step("Click dropdown menu item: {parentItemText} -> {childItemText}")
    public NavigationComponent clickDropdownMenuItem(String parentItemText, String childItemText) {
        // First hover over parent to show dropdown
        hoverOverDropdown(parentItemText);
        
        // Wait a bit for dropdown to appear
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Find and click child item
        for (WebElement dropdown : dropdownMenus) {
            if (isDisplayed(dropdown)) {
                List<WebElement> dropdownItems = dropdown.findElements(
                    org.openqa.selenium.By.xpath(".//a"));
                for (WebElement item : dropdownItems) {
                    if (getText(item).equalsIgnoreCase(childItemText)) {
                        click(item);
                        logger.info("Clicked dropdown menu item: {} -> {}", parentItemText, childItemText);
                        return this;
                    }
                }
            }
        }
        logger.warn("Dropdown menu item '{}' -> '{}' not found", parentItemText, childItemText);
        return this;
    }
    
    /**
     * Get navigation breadcrumb
     * @return Breadcrumb text
     */
    @Step("Get navigation breadcrumb")
    public String getNavigationBreadcrumb() {
        if (isDisplayed(navigationBreadcrumb)) {
            String breadcrumb = getText(navigationBreadcrumb);
            logger.info("Navigation breadcrumb: {}", breadcrumb);
            return breadcrumb;
        }
        return "";
    }
    
    /**
     * Check if specific navigation item is visible
     * @param itemText Text of navigation item to check
     * @return true if item is visible, false otherwise
     */
    public boolean isNavigationItemVisible(String itemText) {
        for (WebElement navItem : navigationItems) {
            if (getText(navItem).equalsIgnoreCase(itemText)) {
                boolean visible = isDisplayed(navItem);
                logger.info("Navigation item '{}' visible: {}", itemText, visible);
                return visible;
            }
        }
        logger.info("Navigation item '{}' not found", itemText);
        return false;
    }
    
    /**
     * Get number of navigation items
     * @return Number of navigation items
     */
    public int getNavigationItemsCount() {
        int count = navigationItems.size();
        logger.info("Navigation items count: {}", count);
        return count;
    }
    
    /**
     * Check if navigation search is available
     * @return true if navigation search is available, false otherwise
     */
    public boolean isNavigationSearchAvailable() {
        boolean available = isDisplayed(navigationSearch);
        logger.info("Navigation search available: {}", available);
        return available;
    }
    
    /**
     * Implementation of abstract method from BaseComponent
     * @return true if navigation component is loaded, false otherwise
     */
    @Override
    public boolean isComponentLoaded() {
        try {
            boolean loaded = isDisplayed(navigationContainer) && !navigationItems.isEmpty();
            logger.info("Navigation component loaded status: {}", loaded);
            return loaded;
        } catch (Exception e) {
            logger.error("Error checking if navigation component is loaded", e);
            return false;
        }
    }
    
    /**
     * Verify all essential navigation elements are present
     * @return true if all essential elements are present, false otherwise
     */
    @Step("Verify navigation elements")
    public boolean verifyNavigationElements() {
        boolean allPresent = isDisplayed(navigationContainer) &&
                           !navigationItems.isEmpty() &&
                           navigationItems.size() > 0;
        
        logger.info("Navigation elements verification: {}", allPresent);
        return allPresent;
    }
}

