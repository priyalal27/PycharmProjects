package com.framework.pages;

import com.framework.base.BasePage;
import com.framework.utils.ConfigReader;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Login Page class demonstrating Page Object Model with Page Factory
 * This demonstrates:
 * 1. Inheritance - Extends BasePage
 * 2. Encapsulation - Private WebElements, public methods
 * 3. Page Factory Pattern - Using @FindBy annotations
 */
public class LoginPage extends BasePage {
    
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    
    // Page elements using Page Factory pattern (Encapsulation)
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "login-button")
    private WebElement loginButton;
    
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
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to login page
     * @return LoginPage instance for method chaining
     */
    @Step("Navigate to login page")
    public LoginPage navigateToLoginPage() {
        String loginUrl = ConfigReader.getBaseUrl() + "/login";
        navigateToUrl(loginUrl);
        logger.info("Navigated to login page: {}", loginUrl);
        return this;
    }
    
    /**
     * Enter username
     * @param username Username to enter
     * @return LoginPage instance for method chaining
     */
    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        type(usernameField, username);
        logger.info("Entered username: {}", username);
        return this;
    }
    
    /**
     * Enter password
     * @param password Password to enter
     * @return LoginPage instance for method chaining
     */
    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        logger.info("Entered password");
        return this;
    }
    
    /**
     * Click login button
     * @return DashboardPage instance (assuming successful login)
     */
    @Step("Click login button")
    public DashboardPage clickLoginButton() {
        click(loginButton);
        logger.info("Clicked login button");
        return new DashboardPage(driver);
    }
    
    /**
     * Perform complete login operation
     * @param username Username to login with
     * @param password Password to login with
     * @return DashboardPage instance
     */
    @Step("Login with username: {username}")
    public DashboardPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }
    
    /**
     * Click remember me checkbox
     * @return LoginPage instance for method chaining
     */
    @Step("Click remember me checkbox")
    public LoginPage clickRememberMe() {
        click(rememberMeCheckbox);
        logger.info("Clicked remember me checkbox");
        return this;
    }
    
    /**
     * Click forgot password link
     * @return LoginPage instance for method chaining
     */
    @Step("Click forgot password link")
    public LoginPage clickForgotPassword() {
        click(forgotPasswordLink);
        logger.info("Clicked forgot password link");
        return this;
    }
    
    /**
     * Click sign up link
     * @return LoginPage instance for method chaining
     */
    @Step("Click sign up link")
    public LoginPage clickSignUp() {
        click(signUpLink);
        logger.info("Clicked sign up link");
        return this;
    }
    
    /**
     * Get error message text
     * @return Error message text
     */
    @Step("Get error message")
    public String getErrorMessage() {
        String error = getText(errorMessage);
        logger.info("Error message: {}", error);
        return error;
    }
    
    /**
     * Check if error message is displayed
     * @return true if error message is displayed, false otherwise
     */
    @Step("Check if error message is displayed")
    public boolean isErrorMessageDisplayed() {
        boolean isDisplayed = isDisplayed(errorMessage);
        logger.info("Error message displayed: {}", isDisplayed);
        return isDisplayed;
    }
    
    /**
     * Get login title text
     * @return Login title text
     */
    @Step("Get login title")
    public String getLoginTitle() {
        String title = getText(loginTitle);
        logger.info("Login title: {}", title);
        return title;
    }
    
    /**
     * Check if username field is displayed
     * @return true if username field is displayed, false otherwise
     */
    public boolean isUsernameFieldDisplayed() {
        return isDisplayed(usernameField);
    }
    
    /**
     * Check if password field is displayed
     * @return true if password field is displayed, false otherwise
     */
    public boolean isPasswordFieldDisplayed() {
        return isDisplayed(passwordField);
    }
    
    /**
     * Check if login button is displayed
     * @return true if login button is displayed, false otherwise
     */
    public boolean isLoginButtonDisplayed() {
        return isDisplayed(loginButton);
    }
    
    /**
     * Clear username field
     * @return LoginPage instance for method chaining
     */
    @Step("Clear username field")
    public LoginPage clearUsername() {
        clear(usernameField);
        logger.info("Cleared username field");
        return this;
    }
    
    /**
     * Clear password field
     * @return LoginPage instance for method chaining
     */
    @Step("Clear password field")
    public LoginPage clearPassword() {
        clear(passwordField);
        logger.info("Cleared password field");
        return this;
    }
    
    /**
     * Implementation of abstract method from BasePage
     * Checks if the login page is loaded by verifying key elements
     * @return true if page is loaded, false otherwise
     */
    @Override
    public boolean isPageLoaded() {
        try {
            boolean isLoaded = isDisplayed(usernameField) && 
                             isDisplayed(passwordField) && 
                             isDisplayed(loginButton);
            logger.info("Login page loaded status: {}", isLoaded);
            return isLoaded;
        } catch (Exception e) {
            logger.error("Error checking if login page is loaded", e);
            return false;
        }
    }
}
