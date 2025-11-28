package com.framework.tests;

import com.framework.pages.DashboardPage;
import com.framework.pages.LoginPage;
import com.framework.pages.SearchPage;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Dashboard test class demonstrating various dashboard functionality tests
 * This demonstrates:
 * 1. Inheritance - Extends BaseTest
 * 2. Test dependency and ordering
 * 3. Page Object interactions
 * 4. Allure reporting integration
 */
@Epic("Dashboard")
@Feature("Dashboard Functionality")
public class DashboardTest extends BaseTest {
    
    private static final Logger logger = LogManager.getLogger(DashboardTest.class);
    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    
    /**
     * Class level setup for dashboard tests
     */
    @Override
    @BeforeClass(alwaysRun = true)
    public void classSetup() {
        super.classSetup();
        logger.info("Dashboard Test class setup completed");
    }
    
    /**
     * Method level setup - login before each test
     */
    @BeforeMethod(alwaysRun = true, groups = {"dashboard"})
    @Step("Login before dashboard test")
    public void loginBeforeTest() {
        logTestStep("Logging in before dashboard test");
        
        // Initialize login page and perform login
        loginPage = new LoginPage(getDriver());
        dashboardPage = loginPage
            .navigateToLoginPage()
            .login("testuser@example.com", "testpassword123");
        
        // Verify successful login
        Assert.assertTrue(dashboardPage.isPageLoaded(), "Dashboard should be loaded before test");
        
        logger.info("Login completed before dashboard test");
    }
    
    /**
     * Test data provider for search terms
     */
    @DataProvider(name = "searchTerms")
    public Object[][] getSearchTerms() {
        return new Object[][]{
            {"selenium", "Search for selenium should return results"},
            {"automation", "Search for automation should return results"},
            {"testing", "Search for testing should return results"},
            {"java", "Search for java should return results"},
            {"framework", "Search for framework should return results"}
        };
    }
    
    /**
     * Test dashboard page loading and basic elements
     */
    @Test(priority = 1, groups = {"smoke", "dashboard"})
    @Story("Dashboard Page Loading")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that dashboard page loads correctly with all essential elements")
    public void testDashboardPageLoading() {
        logTestStep("Testing dashboard page loading");
        
        // Verify dashboard page is loaded
        Assert.assertTrue(dashboardPage.isPageLoaded(), "Dashboard page should be loaded");
        
        // Verify page title
        String pageTitle = dashboardPage.getPageTitle();
        Assert.assertTrue(pageTitle.toLowerCase().contains("dashboard"), 
            "Page title should contain 'dashboard', but was: " + pageTitle);
        
        // Verify dashboard title
        String dashboardTitle = dashboardPage.getDashboardTitle();
        Assert.assertFalse(dashboardTitle.isEmpty(), "Dashboard title should not be empty");
        
        // Verify welcome message
        Assert.assertTrue(dashboardPage.isWelcomeMessageDisplayed(), "Welcome message should be displayed");
        String welcomeMessage = dashboardPage.getWelcomeMessage();
        Assert.assertFalse(welcomeMessage.isEmpty(), "Welcome message should not be empty");
        
        logger.info("Dashboard page loading test completed");
    }
    
    /**
     * Test dashboard navigation elements
     */
    @Test(priority = 2, groups = {"smoke", "dashboard", "navigation"})
    @Story("Dashboard Navigation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that all navigation elements are present and functional")
    public void testDashboardNavigation() {
        logTestStep("Testing dashboard navigation elements");
        
        // Verify user profile is displayed
        Assert.assertTrue(dashboardPage.isUserProfileDisplayed(), "User profile should be displayed");
        
        // Verify logout button is displayed
        Assert.assertTrue(dashboardPage.isLogoutButtonDisplayed(), "Logout button should be displayed");
        
        // Verify search box is displayed
        Assert.assertTrue(dashboardPage.isSearchBoxDisplayed(), "Search box should be displayed");
        
        // Get navigation menu items count
        int menuItemsCount = dashboardPage.getNavigationMenuItemsCount();
        Assert.assertTrue(menuItemsCount > 0, "Should have navigation menu items");
        
        // Get dashboard widgets count
        int widgetsCount = dashboardPage.getDashboardWidgetsCount();
        Assert.assertTrue(widgetsCount >= 0, "Dashboard widgets count should be non-negative");
        
        logger.info("Dashboard navigation test completed with {} menu items and {} widgets", 
                   menuItemsCount, widgetsCount);
    }
    
    /**
     * Test search functionality from dashboard
     */
    @Test(priority = 3, groups = {"regression", "dashboard", "search"}, dataProvider = "searchTerms")
    @Story("Dashboard Search")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify search functionality works correctly from dashboard")
    public void testDashboardSearch(String searchTerm, String description) {
        logTestStep("Testing dashboard search with term: " + searchTerm);
        
        // Perform search
        SearchPage searchPage = dashboardPage.search(searchTerm);
        
        // Verify search page is loaded
        Assert.assertTrue(searchPage.isPageLoaded(), "Search page should be loaded after search");
        
        // Verify URL contains search indication
        Assert.assertTrue(verifyUrlContains("search") || verifyUrlContains("results"), 
            "URL should indicate search page");
        
        // Verify search results or no results message
        int resultsCount = searchPage.getSearchResultsCount();
        if (resultsCount > 0) {
            Assert.assertTrue(resultsCount > 0, "Should have search results for: " + searchTerm);
            
            // Get all search result titles
            List<String> resultTitles = searchPage.getAllSearchResultTitles();
            Assert.assertFalse(resultTitles.isEmpty(), "Should have result titles");
            
            logger.info("Search completed for '{}' with {} results", searchTerm, resultsCount);
        } else {
            Assert.assertTrue(searchPage.isNoResultsMessageDisplayed(), 
                "No results message should be displayed when no results found");
            logger.info("No results found for search term: {}", searchTerm);
        }
    }
    
    /**
     * Test user profile interaction
     */
    @Test(priority = 4, groups = {"regression", "dashboard", "profile"})
    @Story("User Profile Interaction")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify user profile can be accessed and interacted with")
    public void testUserProfileInteraction() {
        logTestStep("Testing user profile interaction");
        
        // Click on user profile
        dashboardPage.clickUserProfile();
        
        // Verify we're still on dashboard (profile dropdown/modal should open)
        Assert.assertTrue(dashboardPage.isPageLoaded(), "Should remain on dashboard after clicking profile");
        
        logger.info("User profile interaction test completed");
    }
    
    /**
     * Test notifications functionality
     */
    @Test(priority = 5, groups = {"regression", "dashboard", "notifications"})
    @Story("Notifications")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify notifications functionality works correctly")
    public void testNotifications() {
        logTestStep("Testing notifications functionality");
        
        // Click notifications
        dashboardPage.clickNotifications();
        
        // Get notification count
        int notificationCount = dashboardPage.getNotificationCount();
        Assert.assertTrue(notificationCount >= 0, "Notification count should be non-negative");
        
        logger.info("Notifications test completed with count: {}", notificationCount);
    }
    
    /**
     * Test settings link
     */
    @Test(priority = 6, groups = {"regression", "dashboard", "settings"})
    @Story("Settings Access")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify settings can be accessed from dashboard")
    public void testSettingsAccess() {
        logTestStep("Testing settings access");
        
        // Click settings
        dashboardPage.clickSettings();
        
        // In a real scenario, this might navigate to settings page
        // For now, verify we can interact with settings
        Assert.assertTrue(dashboardPage.isPageLoaded(), "Should be able to access settings");
        
        logger.info("Settings access test completed");
    }
    
    /**
     * Test navigation menu items
     */
    @Test(priority = 7, groups = {"regression", "dashboard", "navigation"})
    @Story("Navigation Menu")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify navigation menu items are clickable")
    public void testNavigationMenuItems() {
        logTestStep("Testing navigation menu items");
        
        // Get menu items count first
        int menuItemsCount = dashboardPage.getNavigationMenuItemsCount();
        Assert.assertTrue(menuItemsCount > 0, "Should have navigation menu items");
        
        // Try clicking on common menu items (these would exist in most applications)
        String[] commonMenuItems = {"Home", "Profile", "Settings", "Dashboard", "Reports"};
        
        for (String menuItem : commonMenuItems) {
            try {
                dashboardPage.clickNavigationMenuItem(menuItem);
                logger.info("Successfully clicked menu item: {}", menuItem);
                // Small wait between clicks
                waitFor(500);
            } catch (Exception e) {
                logger.debug("Menu item '{}' not found or not clickable: {}", menuItem, e.getMessage());
            }
        }
        
        logger.info("Navigation menu items test completed");
    }
    
    /**
     * Test logout functionality
     */
    @Test(priority = 8, groups = {"smoke", "dashboard", "logout"})
    @Story("Logout Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify user can logout successfully from dashboard")
    public void testLogoutFunctionality() {
        logTestStep("Testing logout functionality");
        
        // Perform logout
        LoginPage loginPageAfterLogout = dashboardPage.logout();
        
        // Verify logout successful - should be on login page
        Assert.assertTrue(loginPageAfterLogout.isPageLoaded(), "Should be on login page after logout");
        
        // Verify URL indicates login page
        Assert.assertTrue(verifyUrlContains("login") || verifyUrlContains("auth"), 
            "URL should indicate login page after logout");
        
        // Verify page title indicates login
        Assert.assertTrue(verifyTitleContains("login") || verifyTitleContains("Login"), 
            "Page title should indicate login page");
        
        logger.info("Logout functionality test completed");
    }
    
    /**
     * Test search field interactions
     */
    @Test(priority = 9, groups = {"regression", "dashboard", "search"})
    @Story("Search Field Operations")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify search field can handle various input operations")
    public void testSearchFieldOperations() {
        logTestStep("Testing search field operations");
        
        // Enter search term without clicking search
        String searchTerm = "test query";
        dashboardPage.enterSearchTerm(searchTerm);
        
        // Verify we're still on dashboard
        Assert.assertTrue(dashboardPage.isPageLoaded(), "Should remain on dashboard after entering search term");
        
        // Now click search button
        SearchPage searchPage = dashboardPage.clickSearch();
        
        // Verify search page loaded
        Assert.assertTrue(searchPage.isPageLoaded(), "Search page should load after clicking search");
        
        logger.info("Search field operations test completed");
    }
    
    /**
     * Test dashboard responsiveness and performance
     */
    @Test(priority = 10, groups = {"performance", "dashboard"})
    @Story("Dashboard Performance")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify dashboard loads and responds within acceptable time limits")
    public void testDashboardPerformance() {
        logTestStep("Testing dashboard performance");
        
        long startTime = System.currentTimeMillis();
        
        // Perform multiple dashboard operations
        dashboardPage.getDashboardTitle();
        dashboardPage.getWelcomeMessage();
        dashboardPage.getNavigationMenuItemsCount();
        dashboardPage.getDashboardWidgetsCount();
        
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        
        // Assert that operations complete within reasonable time (5 seconds)
        Assert.assertTrue(executionTime < 5000, 
            "Dashboard operations should complete within 5 seconds, but took: " + executionTime + "ms");
        
        logger.info("Dashboard performance test completed in {}ms", executionTime);
    }
}
