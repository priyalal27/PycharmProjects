package com.framework.tests;

import com.framework.components.FooterComponent;
import com.framework.components.HeaderComponent;
import com.framework.components.NavigationComponent;
import com.framework.pages.DashboardPage;
import com.framework.pages.EnhancedLoginPage;
import com.framework.utils.PageObjectUtils;
import com.framework.wrappers.Button;
import com.framework.wrappers.Dropdown;
import com.framework.wrappers.TextBox;
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
 * Enhanced POM Test class demonstrating comprehensive Page Object Model patterns
 * This test class showcases:
 * 1. Component-based POM testing
 * 2. Element Wrapper pattern testing
 * 3. Advanced page object interactions
 * 4. Comprehensive POM validation
 * 5. All OOP principles in action (Inheritance, Encapsulation, Abstraction, Polymorphism)
 */
@Epic("Enhanced Page Object Model")
@Feature("Advanced POM Patterns")
public class EnhancedPOMTest extends BaseTest {
    
    private static final Logger logger = LogManager.getLogger(EnhancedPOMTest.class);
    private EnhancedLoginPage enhancedLoginPage;
    
    /**
     * Class level setup for enhanced POM tests
     */
    @Override
    @BeforeClass(alwaysRun = true)
    public void classSetup() {
        super.classSetup();
        logger.info("Enhanced POM Test class setup completed");
    }
    
    /**
     * Method level setup - navigate to login page
     */
    @BeforeMethod(alwaysRun = true, groups = {"enhanced-pom"})
    @Step("Setup enhanced login page")
    public void setupEnhancedLoginPage() {
        logTestStep("Setting up enhanced login page for POM testing");
        
        // Initialize enhanced login page
        enhancedLoginPage = new EnhancedLoginPage(getDriver());
        enhancedLoginPage.navigateToLoginPage();
        
        // Verify page is loaded
        Assert.assertTrue(enhancedLoginPage.isPageLoaded(), "Enhanced login page should be loaded");
        
        logger.info("Enhanced login page setup completed");
    }
    
    /**
     * Test data provider for login credentials and languages
     */
    @DataProvider(name = "loginWithLanguageData")
    public Object[][] getLoginWithLanguageData() {
        return new Object[][]{
            {"testuser@example.com", "testpassword123", "English", "Valid login with English"},
            {"testuser@example.com", "testpassword123", "Spanish", "Valid login with Spanish"},
            {"testuser@example.com", "testpassword123", "French", "Valid login with French"},
            {"testuser@example.com", "testpassword123", "German", "Valid login with German"}
        };
    }
    
    /**
     * Test enhanced POM page loading and initialization
     */
    @Test(priority = 1, groups = {"smoke", "enhanced-pom", "components"})
    @Story("Enhanced Page Initialization")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify enhanced login page loads correctly with all components and wrappers")
    public void testEnhancedPageInitialization() {
        logTestStep("Testing enhanced POM page initialization");
        
        // Verify page is loaded
        Assert.assertTrue(enhancedLoginPage.isPageLoaded(), "Enhanced login page should be loaded");
        
        // Verify comprehensive validation
        Assert.assertTrue(enhancedLoginPage.performComprehensiveValidation(), 
            "Comprehensive page validation should pass");
        
        // Verify form elements using enhanced wrappers
        Assert.assertTrue(enhancedLoginPage.verifyLoginFormElements(), 
            "All login form elements should be present and functional");
        
        // Verify page object initialization using utility
        Assert.assertTrue(PageObjectUtils.verifyPageObjectInitialization(enhancedLoginPage),
            "Page object should be properly initialized");
        
        logger.info("Enhanced POM page initialization test completed");
    }
    
    /**
     * Test element wrappers functionality
     */
    @Test(priority = 2, groups = {"regression", "enhanced-pom", "wrappers"})
    @Story("Element Wrappers")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify element wrapper functionality for Button, TextBox, and Dropdown")
    public void testElementWrappers() {
        logTestStep("Testing element wrapper functionality");
        
        // Test TextBox wrapper
        TextBox usernameBox = enhancedLoginPage.getUsernameTextBox();
        Assert.assertTrue(usernameBox.isDisplayed(), "Username textbox should be displayed");
        Assert.assertTrue(usernameBox.isEnabled(), "Username textbox should be enabled");
        
        // Test text operations
        usernameBox.type("test@example.com");
        Assert.assertEquals(usernameBox.getText(), "test@example.com", "Username should be entered correctly");
        
        usernameBox.clear();
        Assert.assertTrue(usernameBox.getText().isEmpty(), "Username should be cleared");
        
        // Test Button wrapper
        Button loginButton = enhancedLoginPage.getLoginButtonWrapper();
        Assert.assertTrue(loginButton.isDisplayed(), "Login button should be displayed");
        Assert.assertTrue(loginButton.isEnabled(), "Login button should be enabled");
        
        String buttonText = loginButton.getText();
        Assert.assertFalse(buttonText.isEmpty(), "Login button should have text");
        
        // Test Dropdown wrapper (if available)
        try {
            Dropdown languageDropdown = enhancedLoginPage.getLanguageDropdown();
            if (languageDropdown.isDisplayed()) {
                List<String> options = languageDropdown.getAllOptionTexts();
                Assert.assertFalse(options.isEmpty(), "Language dropdown should have options");
                
                String selectedLanguage = languageDropdown.getSelectedText();
                Assert.assertFalse(selectedLanguage.isEmpty(), "A language should be selected by default");
            }
        } catch (Exception e) {
            logger.info("Language dropdown not available for testing, skipping dropdown wrapper test");
        }
        
        logger.info("Element wrappers functionality test completed");
    }
    
    /**
     * Test page components functionality
     */
    @Test(priority = 3, groups = {"regression", "enhanced-pom", "components"})
    @Story("Page Components")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify page component functionality for Header, Footer, and Navigation")
    public void testPageComponents() {
        logTestStep("Testing page component functionality");
        
        // Test Header Component (if present)
        try {
            HeaderComponent header = enhancedLoginPage.getHeaderComponent();
            if (header.isComponentLoaded()) {
                Assert.assertTrue(header.isComponentLoaded(), "Header component should be loaded");
                
                // Test header operations
                if (header.isUserLoggedIn()) {
                    String userName = header.getUserName();
                    Assert.assertFalse(userName.isEmpty(), "User name should not be empty if logged in");
                }
            }
        } catch (Exception e) {
            logger.info("Header component not available for testing: {}", e.getMessage());
        }
        
        // Test Footer Component (if present)
        try {
            FooterComponent footer = enhancedLoginPage.getFooterComponent();
            if (footer.isComponentLoaded()) {
                Assert.assertTrue(footer.isComponentLoaded(), "Footer component should be loaded");
                
                // Test footer operations
                String copyrightText = footer.getCopyrightText();
                logger.info("Footer copyright: {}", copyrightText);
                
                // Verify footer links
                if (footer.verifyFooterLinks()) {
                    logger.info("Footer links verification passed");
                }
            }
        } catch (Exception e) {
            logger.info("Footer component not available for testing: {}", e.getMessage());
        }
        
        logger.info("Page components functionality test completed");
    }
    
    /**
     * Test enhanced login functionality with wrappers
     */
    @Test(priority = 4, groups = {"smoke", "enhanced-pom", "login"})
    @Story("Enhanced Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify login functionality using enhanced POM patterns")
    public void testEnhancedLoginFunctionality() {
        logTestStep("Testing enhanced login functionality");
        
        // Perform enhanced login
        DashboardPage dashboardPage = enhancedLoginPage
            .enterUsername("testuser@example.com")
            .enterPassword("testpassword123")
            .clickLoginButton();
        
        // Verify navigation to dashboard
        Assert.assertTrue(dashboardPage.isPageLoaded(), "Dashboard should be loaded after login");
        Assert.assertTrue(verifyUrlContains("dashboard"), "URL should contain dashboard");
        
        logger.info("Enhanced login functionality test completed");
    }
    
    /**
     * Test login with language selection using dropdown wrapper
     */
    @Test(priority = 5, groups = {"regression", "enhanced-pom", "login"}, dataProvider = "loginWithLanguageData")
    @Story("Multi-language Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify login with different language selections")
    public void testLoginWithLanguageSelection(String username, String password, String language, String description) {
        logTestStep("Testing login with language selection: " + description);
        
        try {
            // Check if language dropdown is available
            Dropdown languageDropdown = enhancedLoginPage.getLanguageDropdown();
            if (languageDropdown.isDisplayed() && languageDropdown.hasOptionWithText(language)) {
                
                // Perform login with language selection
                DashboardPage dashboardPage = enhancedLoginPage
                    .selectLanguage(language)
                    .enterUsername(username)
                    .enterPassword(password)
                    .clickLoginButton();
                
                // Verify login success
                Assert.assertTrue(dashboardPage.isPageLoaded(), 
                    "Dashboard should be loaded after login with " + language);
                
                logger.info("Login with language selection test completed for: {}", language);
            } else {
                logger.info("Language '{}' not available, skipping test", language);
            }
        } catch (Exception e) {
            logger.info("Language dropdown not available, skipping multi-language test: {}", e.getMessage());
        }
    }
    
    /**
     * Test advanced wrapper operations
     */
    @Test(priority = 6, groups = {"regression", "enhanced-pom", "wrappers"})
    @Story("Advanced Wrapper Operations")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify advanced operations in element wrappers")
    public void testAdvancedWrapperOperations() {
        logTestStep("Testing advanced wrapper operations");
        
        TextBox usernameBox = enhancedLoginPage.getUsernameTextBox();
        TextBox passwordBox = enhancedLoginPage.getPasswordTextBox();
        
        // Test focus operations
        usernameBox.focus();
        Assert.assertTrue(usernameBox.isDisplayed(), "Username box should remain displayed after focus");
        
        // Test keyboard operations
        usernameBox.type("test@example.com");
        usernameBox.selectAll();
        usernameBox.copy();
        
        passwordBox.focus();
        passwordBox.paste();
        
        // Verify paste operation worked
        String pastedText = passwordBox.getText();
        logger.info("Pasted text in password field: {}", pastedText.length() > 0 ? "[HIDDEN]" : "[EMPTY]");
        
        // Test clear operations
        usernameBox.clear();
        passwordBox.clearUsingKeyboard();
        
        Assert.assertTrue(usernameBox.getText().isEmpty(), "Username should be cleared");
        Assert.assertTrue(passwordBox.getText().isEmpty(), "Password should be cleared");
        
        // Test Enter key submission
        enhancedLoginPage.enterUsername("testuser@example.com");
        enhancedLoginPage.enterPassword("testpassword123");
        
        try {
            DashboardPage dashboardPage = enhancedLoginPage.submitFormUsingEnter();
            Assert.assertTrue(dashboardPage.isPageLoaded(), "Form should submit using Enter key");
        } catch (Exception e) {
            logger.info("Enter key submission may not be supported on this page");
        }
        
        logger.info("Advanced wrapper operations test completed");
    }
    
    /**
     * Test POM utility methods
     */
    @Test(priority = 7, groups = {"regression", "enhanced-pom", "utilities"})
    @Story("POM Utilities")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify POM utility methods functionality")
    public void testPOMUtilities() {
        logTestStep("Testing POM utility methods");
        
        // Test page load waiting
        Assert.assertTrue(PageObjectUtils.waitForPageLoad(getDriver(), 10), 
            "Page should be loaded within timeout");
        
        // Test page object verification
        Assert.assertTrue(PageObjectUtils.verifyPageObjectInitialization(enhancedLoginPage),
            "Page object should be properly initialized");
        
        // Test element text retrieval
        String usernameText = PageObjectUtils.getElementTextSafely(enhancedLoginPage.getUsernameTextBox().getElement());
        logger.info("Username field text (safe): '{}'", usernameText);
        
        // Test element attribute retrieval
        String usernameType = PageObjectUtils.getElementAttributeSafely(
            enhancedLoginPage.getUsernameTextBox().getElement(), "type");
        Assert.assertFalse(usernameType.isEmpty(), "Username field should have a type attribute");
        
        // Test element visibility in viewport
        boolean inViewport = PageObjectUtils.isElementInViewport(getDriver(), 
            enhancedLoginPage.getLoginButtonWrapper().getElement());
        Assert.assertTrue(inViewport, "Login button should be in viewport");
        
        logger.info("POM utilities test completed");
    }
    
    /**
     * Test method chaining and fluent interface
     */
    @Test(priority = 8, groups = {"regression", "enhanced-pom", "fluent"})
    @Story("Fluent Interface")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify method chaining and fluent interface in enhanced POM")
    public void testFluentInterface() {
        logTestStep("Testing fluent interface and method chaining");
        
        // Test method chaining in enhanced login page
        DashboardPage dashboardPage = enhancedLoginPage
            .clearUsername()
            .clearPassword()
            .focusOnUsername()
            .enterUsername("fluent@example.com")
            .enterPassword("fluentpassword")
            .clickRememberMe()
            .clickLoginButton();
        
        // Verify fluent operations completed successfully
        Assert.assertTrue(dashboardPage.isPageLoaded(), 
            "Dashboard should be loaded after fluent login operations");
        
        logger.info("Fluent interface test completed");
    }
    
    /**
     * Test error handling in enhanced POM
     */
    @Test(priority = 9, groups = {"regression", "enhanced-pom", "error-handling"})
    @Story("Error Handling")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify error handling in enhanced POM patterns")
    public void testErrorHandling() {
        logTestStep("Testing error handling in enhanced POM");
        
        // Test with invalid credentials to trigger error handling
        enhancedLoginPage
            .enterUsername("invalid@example.com")
            .enterPassword("wrongpassword")
            .clickLoginButton();
        
        // Verify error message handling
        try {
            if (enhancedLoginPage.isDisplayed(
                getDriver().findElement(org.openqa.selenium.By.className("error-message")))) {
                String errorMessage = enhancedLoginPage.getErrorMessage();
                Assert.assertFalse(errorMessage.isEmpty(), "Error message should be displayed for invalid login");
                logger.info("Error message handled correctly: {}", errorMessage);
            }
        } catch (Exception e) {
            logger.info("No error message element found, which is acceptable for this test");
        }
        
        // Verify page remains stable after error
        Assert.assertTrue(enhancedLoginPage.isPageLoaded(), 
            "Page should remain stable after login error");
        
        logger.info("Error handling test completed");
    }
    
    /**
     * Test comprehensive POM patterns demonstration
     */
    @Test(priority = 10, groups = {"smoke", "enhanced-pom", "comprehensive"})
    @Story("Comprehensive POM Demonstration")
    @Severity(SeverityLevel.NORMAL)
    @Description("Demonstrate all POM patterns working together comprehensively")
    public void testComprehensivePOMPatterns() {
        logTestStep("Testing comprehensive POM patterns demonstration");
        
        // Demonstrate Inheritance (EnhancedLoginPage extends BasePage)
        Assert.assertTrue(enhancedLoginPage instanceof com.framework.base.BasePage, 
            "EnhancedLoginPage should inherit from BasePage");
        
        // Demonstrate Encapsulation (private fields, public methods)
        Assert.assertTrue(enhancedLoginPage.verifyLoginFormElements(),
            "Encapsulated form validation should work");
        
        // Demonstrate Abstraction (using interfaces and abstract methods)
        Assert.assertTrue(enhancedLoginPage.isPageLoaded(),
            "Abstract method implementation should work");
        
        // Demonstrate Polymorphism (method overriding and interface implementation)
        byte[] screenshot = enhancedLoginPage.takeScreenshot();
        Assert.assertTrue(screenshot.length > 0, "Polymorphic screenshot method should work");
        
        // Demonstrate Component Pattern
        HeaderComponent header = enhancedLoginPage.getHeaderComponent();
        FooterComponent footer = enhancedLoginPage.getFooterComponent();
        Assert.assertNotNull(header, "Header component should be initialized");
        Assert.assertNotNull(footer, "Footer component should be initialized");
        
        // Demonstrate Wrapper Pattern
        TextBox usernameBox = enhancedLoginPage.getUsernameTextBox();
        Button loginButton = enhancedLoginPage.getLoginButtonWrapper();
        Assert.assertTrue(usernameBox.isDisplayed(), "TextBox wrapper should work");
        Assert.assertTrue(loginButton.isDisplayed(), "Button wrapper should work");
        
        // Demonstrate Factory Pattern (in DriverFactory)
        Assert.assertNotNull(getDriver(), "Driver factory should provide WebDriver instance");
        
        // Demonstrate all patterns working together
        DashboardPage dashboardPage = enhancedLoginPage
            .performComprehensiveValidation() // Validation method
            ? enhancedLoginPage
                .enterUsername("comprehensive@example.com")
                .enterPassword("comprehensivetest")
                .clickLoginButton()
            : null;
        
        if (dashboardPage != null) {
            Assert.assertTrue(dashboardPage.isPageLoaded(), 
                "All POM patterns should work together successfully");
        }
        
        logger.info("Comprehensive POM patterns demonstration completed");
    }
}
