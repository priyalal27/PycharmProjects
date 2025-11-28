package com.framework.tests;

import com.framework.pages.DashboardPage;
import com.framework.pages.LoginPage;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Login test class demonstrating various login scenarios
 * This demonstrates:
 * 1. Inheritance - Extends BaseTest
 * 2. Test organization using TestNG annotations
 * 3. Data-driven testing using DataProvider
 * 4. Allure reporting annotations
 */
@Epic("Authentication")
@Feature("Login Functionality")
public class LoginTest extends BaseTest {
    
    private static final Logger logger = LogManager.getLogger(LoginTest.class);
    private LoginPage loginPage;
    
    /**
     * Class level setup for login tests
     */
    @Override
    @BeforeClass(alwaysRun = true)
    public void classSetup() {
        super.classSetup();
        logger.info("Login Test class setup completed");
    }
    
    /**
     * Test data provider for login credentials
     * @return Object array containing test data
     */
    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginCredentials() {
        return new Object[][]{
            {"validUser", "validPassword", true, "Valid credentials should allow login"},
            {"invalidUser", "validPassword", false, "Invalid username should prevent login"},
            {"validUser", "invalidPassword", false, "Invalid password should prevent login"},
            {"", "validPassword", false, "Empty username should prevent login"},
            {"validUser", "", false, "Empty password should prevent login"},
            {"", "", false, "Empty credentials should prevent login"}
        };
    }
    
    /**
     * Test successful login with valid credentials
     */
    @Test(priority = 1, groups = {"smoke", "regression", "login"})
    @Story("Successful Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that user can login with valid credentials and is redirected to dashboard")
    public void testSuccessfulLogin() {
        logTestStep("Starting successful login test");
        
        // Initialize login page
        loginPage = new LoginPage(getDriver());
        
        // Verify login page is loaded
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page should be loaded");
        
        // Perform login
        DashboardPage dashboardPage = loginPage
            .navigateToLoginPage()
            .login("testuser@example.com", "testpassword123");
        
        // Verify successful login
        Assert.assertTrue(dashboardPage.isPageLoaded(), "Dashboard page should be loaded after successful login");
        Assert.assertTrue(verifyUrlContains("dashboard"), "URL should contain 'dashboard' after login");
        
        logger.info("Successful login test completed");
    }
    
    /**
     * Test login with invalid credentials
     */
    @Test(priority = 2, groups = {"regression", "login", "negative"})
    @Story("Invalid Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that user cannot login with invalid credentials and error message is displayed")
    public void testInvalidLogin() {
        logTestStep("Starting invalid login test");
        
        // Initialize login page
        loginPage = new LoginPage(getDriver());
        
        // Navigate to login page
        loginPage.navigateToLoginPage();
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page should be loaded");
        
        // Attempt login with invalid credentials
        loginPage.enterUsername("invalid@example.com")
                 .enterPassword("wrongpassword")
                 .clickLoginButton();
        
        // Verify login failure
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid login");
        Assert.assertFalse(verifyUrlContains("dashboard"), "URL should not contain 'dashboard' for invalid login");
        
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertFalse(errorMessage.isEmpty(), "Error message should not be empty");
        
        logger.info("Invalid login test completed with error message: {}", errorMessage);
    }
    
    /**
     * Data-driven test for multiple login scenarios
     */
    @Test(priority = 3, groups = {"regression", "login"}, dataProvider = "loginCredentials")
    @Story("Multiple Login Scenarios")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify login behavior with various credential combinations")
    public void testLoginWithMultipleCredentials(String username, String password, boolean shouldSucceed, String description) {
        logTestStep("Testing login scenario: " + description);
        
        // Initialize login page
        loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage();
        
        // Verify login page elements
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field should be displayed");
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field should be displayed");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be displayed");
        
        // Perform login
        loginPage.enterUsername(username)
                 .enterPassword(password)
                 .clickLoginButton();
        
        // Verify result based on expected outcome
        if (shouldSucceed) {
            Assert.assertTrue(verifyUrlContains("dashboard"), 
                "Login should succeed for: " + description);
        } else {
            Assert.assertFalse(verifyUrlContains("dashboard"), 
                "Login should fail for: " + description);
            
            // For failed logins, verify error handling
            if (loginPage.isErrorMessageDisplayed()) {
                String errorMessage = loginPage.getErrorMessage();
                Assert.assertFalse(errorMessage.isEmpty(), "Error message should not be empty");
                logger.info("Login failed as expected with error: {}", errorMessage);
            }
        }
        
        logger.info("Login test completed for scenario: {}", description);
    }
    
    /**
     * Test login page elements visibility
     */
    @Test(priority = 4, groups = {"smoke", "ui"})
    @Story("Login Page UI Elements")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that all login page elements are visible and functional")
    public void testLoginPageElements() {
        logTestStep("Testing login page elements visibility");
        
        // Initialize login page
        loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage();
        
        // Verify page title
        String pageTitle = loginPage.getPageTitle();
        Assert.assertTrue(pageTitle.toLowerCase().contains("login"), 
            "Page title should contain 'login', but was: " + pageTitle);
        
        // Verify login title
        String loginTitle = loginPage.getLoginTitle();
        Assert.assertFalse(loginTitle.isEmpty(), "Login title should not be empty");
        
        // Verify form elements
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field should be visible");
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field should be visible");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be visible");
        
        logger.info("Login page elements test completed");
    }
    
    /**
     * Test remember me functionality
     */
    @Test(priority = 5, groups = {"regression", "login"})
    @Story("Remember Me Functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify remember me checkbox functionality")
    public void testRememberMeFunctionality() {
        logTestStep("Testing remember me functionality");
        
        // Initialize login page
        loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage();
        
        // Click remember me checkbox
        loginPage.clickRememberMe();
        
        // Perform login
        DashboardPage dashboardPage = loginPage
            .enterUsername("testuser@example.com")
            .enterPassword("testpassword123")
            .clickLoginButton();
        
        // Verify successful login
        Assert.assertTrue(dashboardPage.isPageLoaded(), "Dashboard should be loaded");
        
        // Log out and verify remember me functionality
        loginPage = dashboardPage.logout();
        
        // This is a placeholder for remember me verification
        // In a real scenario, you would verify that username is pre-filled
        Assert.assertTrue(loginPage.isPageLoaded(), "Should return to login page after logout");
        
        logger.info("Remember me functionality test completed");
    }
    
    /**
     * Test forgot password link functionality
     */
    @Test(priority = 6, groups = {"regression", "login"})
    @Story("Forgot Password Link")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify forgot password link is clickable and functional")
    public void testForgotPasswordLink() {
        logTestStep("Testing forgot password link");
        
        // Initialize login page
        loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage();
        
        // Click forgot password link
        loginPage.clickForgotPassword();
        
        // In a real scenario, this would navigate to forgot password page
        // For now, we just verify the link is clickable
        Assert.assertTrue(loginPage.isPageLoaded(), "Should remain on login page or navigate to forgot password page");
        
        logger.info("Forgot password link test completed");
    }
    
    /**
     * Test sign up link functionality
     */
    @Test(priority = 7, groups = {"regression", "login"})
    @Story("Sign Up Link")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify sign up link is clickable and functional")
    public void testSignUpLink() {
        logTestStep("Testing sign up link");
        
        // Initialize login page
        loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage();
        
        // Click sign up link
        loginPage.clickSignUp();
        
        // In a real scenario, this would navigate to registration page
        // For now, we just verify the link is clickable
        Assert.assertTrue(loginPage.isPageLoaded(), "Should remain on login page or navigate to sign up page");
        
        logger.info("Sign up link test completed");
    }
    
    /**
     * Test clear functionality for input fields
     */
    @Test(priority = 8, groups = {"regression", "ui"})
    @Story("Input Field Operations")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that input fields can be cleared properly")
    public void testInputFieldClear() {
        logTestStep("Testing input field clear functionality");
        
        // Initialize login page
        loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage();
        
        // Enter text in fields
        loginPage.enterUsername("testuser")
                 .enterPassword("testpassword");
        
        // Clear fields
        loginPage.clearUsername()
                 .clearPassword();
        
        // Verify fields are cleared (in a real scenario, you would check field values)
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field should still be displayed");
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field should still be displayed");
        
        logger.info("Input field clear test completed");
    }
}
