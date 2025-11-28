package com.framework.pages;

import com.framework.base.BasePage;
import com.framework.components.FooterComponent;
import com.framework.components.HeaderComponent;
import com.framework.utils.ConfigReader;
import com.framework.wrappers.Button;
import com.framework.wrappers.Dropdown;
import com.framework.wrappers.TextBox;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Enhanced Login Page demonstrating advanced POM patterns
 * This page showcases:
 * 1. Component-based POM with Header and Footer components
 * 2. Element Wrapper pattern with custom Button, TextBox, and Dropdown wrappers
 * 3. Advanced Page Object Model implementation
 * 4. Comprehensive error handling and logging
 * 5. Method chaining for fluent interface
 */
public class EnhancedLoginPage extends BasePage {
    
    private static final Logger logger = LogManager.getLogger(EnhancedLoginPage.class);
    
    // Page components (Component-based POM pattern)
    private HeaderComponent headerComponent;
    private FooterComponent footerComponent;
    
    // Basic WebElements using Page Factory pattern
    @FindBy(id = "username")
    private WebElement usernameElement;
    
    @FindBy(id = "password")
    private WebElement passwordElement;
    
    @FindBy(id = "login-button")
    private WebElement loginButtonElement;
    
    @FindBy(id = "language-selector")
    private WebElement languageSelectorElement;
    
    @FindBy(className = "error-message")
    private WebElement errorMessage;
    
    @FindBy(xpath = "//a[contains(text(),'Forgot Password')]")
    private WebElement forgotPasswordLink;
    
    @FindBy(xpath = "//a[contains(text(),'Sign Up')]")
    private WebElement signUpLink;
    
    @FindBy(id = "remember-me")
    private WebElement rememberMeCheckbox;
    
    @FindBy(className = "login-title")
    private WebElement loginTitle;
    
    @FindBy(className = "login-form")
    private WebElement loginForm;
    
    @FindBy(className = "social-login-buttons")
    private WebElement socialLoginSection;
    
    @FindBy(id = "google-login")
    private WebElement googleLoginButtonElement;
    
    @FindBy(id = "facebook-login")
    private WebElement facebookLoginButtonElement;
    
    @FindBy(className = "captcha-container")
    private WebElement captchaContainer;
    
    @FindBy(id = "show-password")
    private WebElement showPasswordToggle;
    
    @FindBy(className = "login-help-text")
    private List<WebElement> helpTextElements;
    
    // Element Wrappers (Wrapper pattern)
    private TextBox usernameTextBox;
    private TextBox passwordTextBox;
    private Button loginButton;
    private Button googleLoginButton;
    private Button facebookLoginButton;
    private Dropdown languageDropdown;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public EnhancedLoginPage(WebDriver driver) {
        super(driver);
        initializeComponents();
        initializeWrappers();
    }
    
    /**
     * Initialize page components
     */
    private void initializeComponents() {
        headerComponent = new HeaderComponent(driver);
        footerComponent = new FooterComponent(driver);
        logger.debug("Initialized page components for EnhancedLoginPage");
    }
    
    /**
     * Initialize element wrappers
     */
    private void initializeWrappers() {
        usernameTextBox = new TextBox(usernameElement, driver, "Username Field");
        passwordTextBox = new TextBox(passwordElement, driver, "Password Field");
        loginButton = new Button(loginButtonElement, driver, "Login Button");
        googleLoginButton = new Button(googleLoginButtonElement, driver, "Google Login Button");
        facebookLoginButton = new Button(facebookLoginButtonElement, driver, "Facebook Login Button");
        languageDropdown = new Dropdown(languageSelectorElement, driver, "Language Selector");
        logger.debug("Initialized element wrappers for EnhancedLoginPage");
    }
    
    /**
     * Navigate to login page
     * @return EnhancedLoginPage instance for method chaining
     */
    @Step("Navigate to enhanced login page")
    public EnhancedLoginPage navigateToLoginPage() {
        String loginUrl = ConfigReader.getBaseUrl() + "/login";
        navigateToUrl(loginUrl);
        logger.info("Navigated to enhanced login page: {}", loginUrl);
        return this;
    }
    
    /**
     * Enter username using enhanced TextBox wrapper
     * @param username Username to enter
     * @return EnhancedLoginPage instance for method chaining
     */
    @Step("Enter username: {username}")
    public EnhancedLoginPage enterUsername(String username) {
        usernameTextBox.type(username);
        logger.info("Entered username using enhanced wrapper: {}", username);
        return this;
    }
    
    /**
     * Enter password using enhanced TextBox wrapper
     * @param password Password to enter
     * @return EnhancedLoginPage instance for method chaining
     */
    @Step("Enter password")
    public EnhancedLoginPage enterPassword(String password) {
        passwordTextBox.type(password);
        logger.info("Entered password using enhanced wrapper");
        return this;
    }
    
    /**
     * Click login button using enhanced Button wrapper
     * @return DashboardPage instance (assuming successful login)
     */
    @Step("Click enhanced login button")
    public DashboardPage clickLoginButton() {
        loginButton.click();
        logger.info("Clicked login button using enhanced wrapper");
        return new DashboardPage(driver);
    }
    
    /**
     * Perform complete login operation using enhanced wrappers
     * @param username Username to login with
     * @param password Password to login with
     * @return DashboardPage instance
     */
    @Step("Enhanced login with username: {username}")
    public DashboardPage performEnhancedLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }
    
    /**
     * Login with language selection
     * @param username Username to login with
     * @param password Password to login with
     * @param language Language to select
     * @return DashboardPage instance
     */
    @Step("Login with language selection: {language}")
    public DashboardPage loginWithLanguage(String username, String password, String language) {
        selectLanguage(language);
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }
    
    /**
     * Select language using enhanced Dropdown wrapper
     * @param language Language to select
     * @return EnhancedLoginPage instance for method chaining
     */
    @Step("Select language: {language}")
    public EnhancedLoginPage selectLanguage(String language) {
        languageDropdown.selectByText(language);
        logger.info("Selected language using enhanced dropdown: {}", language);
        return this;
    }
    
    /**
     * Login using Google OAuth
     * @return DashboardPage instance (assuming successful login)
     */
    @Step("Login with Google")
    public DashboardPage loginWithGoogle() {
        googleLoginButton.click();
        logger.info("Clicked Google login button");
        // In real scenario, handle OAuth flow here
        return new DashboardPage(driver);
    }
    
    /**
     * Login using Facebook OAuth
     * @return DashboardPage instance (assuming successful login)
     */
    @Step("Login with Facebook")
    public DashboardPage loginWithFacebook() {
        facebookLoginButton.click();
        logger.info("Clicked Facebook login button");
        // In real scenario, handle OAuth flow here
        return new DashboardPage(driver);
    }
    
    /**
     * Toggle password visibility
     * @return EnhancedLoginPage instance for method chaining
     */
    @Step("Toggle password visibility")
    public EnhancedLoginPage togglePasswordVisibility() {
        click(showPasswordToggle);
        logger.info("Toggled password visibility");
        return this;
    }
    
    /**
     * Click remember me checkbox using enhanced click functionality
     * @return EnhancedLoginPage instance for method chaining
     */
    @Step("Click remember me checkbox")
    public EnhancedLoginPage clickRememberMe() {
        click(rememberMeCheckbox);
        logger.info("Clicked remember me checkbox");
        return this;
    }
    
    /**
     * Clear username field using enhanced TextBox wrapper
     * @return EnhancedLoginPage instance for method chaining
     */
    @Step("Clear username field")
    public EnhancedLoginPage clearUsername() {
        usernameTextBox.clear();
        logger.info("Cleared username field using enhanced wrapper");
        return this;
    }
    
    /**
     * Clear password field using enhanced TextBox wrapper
     * @return EnhancedLoginPage instance for method chaining
     */
    @Step("Clear password field")
    public EnhancedLoginPage clearPassword() {
        passwordTextBox.clear();
        logger.info("Cleared password field using enhanced wrapper");
        return this;
    }
    
    /**
     * Focus on username field
     * @return EnhancedLoginPage instance for method chaining
     */
    @Step("Focus on username field")
    public EnhancedLoginPage focusOnUsername() {
        usernameTextBox.focus();
        logger.info("Focused on username field");
        return this;
    }
    
    /**
     * Press Enter in password field to submit form
     * @return DashboardPage instance
     */
    @Step("Submit form using Enter key")
    public DashboardPage submitFormUsingEnter() {
        passwordTextBox.pressEnter();
        logger.info("Submitted form using Enter key");
        return new DashboardPage(driver);
    }
    
    /**
     * Get available languages from dropdown
     * @return List of available languages
     */
    @Step("Get available languages")
    public List<String> getAvailableLanguages() {
        List<String> languages = languageDropdown.getAllOptionTexts();
        logger.info("Retrieved {} available languages", languages.size());
        return languages;
    }
    
    /**
     * Get currently selected language
     * @return Currently selected language
     */
    @Step("Get selected language")
    public String getSelectedLanguage() {
        String selectedLanguage = languageDropdown.getSelectedText();
        logger.info("Currently selected language: {}", selectedLanguage);
        return selectedLanguage;
    }
    
    /**
     * Get error message text with enhanced error handling
     * @return Error message text
     */
    @Step("Get error message")
    public String getErrorMessage() {
        String error = getText(errorMessage);
        logger.info("Error message: {}", error);
        return error;
    }
    
    /**
     * Check if social login options are available
     * @return true if social login is available, false otherwise
     */
    public boolean isSocialLoginAvailable() {
        boolean available = isDisplayed(socialLoginSection);
        logger.info("Social login available: {}", available);
        return available;
    }
    
    /**
     * Check if captcha is displayed
     * @return true if captcha is displayed, false otherwise
     */
    public boolean isCaptchaDisplayed() {
        boolean displayed = isDisplayed(captchaContainer);
        logger.info("Captcha displayed: {}", displayed);
        return displayed;
    }
    
    /**
     * Get all help text elements
     * @return List of help text strings
     */
    public List<String> getHelpTexts() {
        List<String> helpTexts = helpTextElements.stream()
                .map(this::getText)
                .toList();
        logger.info("Retrieved {} help text elements", helpTexts.size());
        return helpTexts;
    }
    
    /**
     * Verify login form elements using enhanced wrappers
     * @return true if all elements are present and functional, false otherwise
     */
    @Step("Verify login form elements")
    public boolean verifyLoginFormElements() {
        boolean allPresent = usernameTextBox.isDisplayed() &&
                           usernameTextBox.isEnabled() &&
                           passwordTextBox.isDisplayed() &&
                           passwordTextBox.isEnabled() &&
                           loginButton.isDisplayed() &&
                           loginButton.isEnabled();
        
        logger.info("Login form elements verification: {}", allPresent);
        return allPresent;
    }
    
    /**
     * Get header component for header-specific operations
     * @return HeaderComponent instance
     */
    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }
    
    /**
     * Get footer component for footer-specific operations
     * @return FooterComponent instance
     */
    public FooterComponent getFooterComponent() {
        return footerComponent;
    }
    
    /**
     * Access username TextBox wrapper directly
     * @return TextBox wrapper for username field
     */
    public TextBox getUsernameTextBox() {
        return usernameTextBox;
    }
    
    /**
     * Access password TextBox wrapper directly
     * @return TextBox wrapper for password field
     */
    public TextBox getPasswordTextBox() {
        return passwordTextBox;
    }
    
    /**
     * Access login Button wrapper directly
     * @return Button wrapper for login button
     */
    public Button getLoginButtonWrapper() {
        return loginButton;
    }
    
    /**
     * Access language Dropdown wrapper directly
     * @return Dropdown wrapper for language selector
     */
    public Dropdown getLanguageDropdown() {
        return languageDropdown;
    }
    
    /**
     * Implementation of abstract method from BasePage
     * Enhanced with component and wrapper validations
     * @return true if page is loaded, false otherwise
     */
    @Override
    public boolean isPageLoaded() {
        try {
            // Verify basic elements
            boolean basicElementsLoaded = isDisplayed(loginForm) &&
                                        isDisplayed(usernameElement) &&
                                        isDisplayed(passwordElement) &&
                                        isDisplayed(loginButtonElement);
            
            // Verify components (optional, may not be present on all login pages)
            boolean componentsLoaded = true; // headerComponent.isComponentLoaded() && footerComponent.isComponentLoaded();
            
            // Verify wrappers functionality
            boolean wrappersWorking = usernameTextBox.isDisplayed() &&
                                    passwordTextBox.isDisplayed() &&
                                    loginButton.isDisplayed();
            
            boolean isLoaded = basicElementsLoaded && componentsLoaded && wrappersWorking;
            logger.info("Enhanced login page loaded status: {}", isLoaded);
            return isLoaded;
        } catch (Exception e) {
            logger.error("Error checking if enhanced login page is loaded", e);
            return false;
        }
    }
    
    /**
     * Comprehensive page validation using all POM patterns
     * @return true if all validations pass, false otherwise
     */
    @Step("Perform comprehensive page validation")
    public boolean performComprehensiveValidation() {
        try {
            // Validate page loading
            boolean pageLoaded = isPageLoaded();
            
            // Validate form elements
            boolean formValid = verifyLoginFormElements();
            
            // Validate language dropdown
            boolean languageDropdownValid = languageDropdown.isDisplayed() && 
                                          languageDropdown.getOptionsCount() > 0;
            
            // Validate components (if present)
            boolean componentsValid = true; // Would validate header/footer if required
            
            boolean allValid = pageLoaded && formValid && languageDropdownValid && componentsValid;
            logger.info("Comprehensive validation result: {}", allValid);
            
            return allValid;
        } catch (Exception e) {
            logger.error("Error during comprehensive validation", e);
            return false;
        }
    }
}

