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
 * Footer Component demonstrating Component-based POM pattern
 * This component can be reused across multiple pages that have the same footer
 * 
 * Demonstrates:
 * 1. Component Reusability - Footer logic separated from page logic
 * 2. Inheritance - Extends BaseComponent
 * 3. Encapsulation - Footer-specific operations encapsulated
 * 4. Single Responsibility - Only handles footer functionality
 */
public class FooterComponent extends BaseComponent {
    
    private static final Logger logger = LogManager.getLogger(FooterComponent.class);
    
    // Footer elements using Page Factory pattern
    @FindBy(id = "footer")
    private WebElement footerContainer;
    
    @FindBy(className = "footer-logo")
    private WebElement footerLogo;
    
    @FindBy(className = "copyright-text")
    private WebElement copyrightText;
    
    @FindBy(xpath = "//footer//a[contains(@href, 'privacy')]")
    private WebElement privacyPolicyLink;
    
    @FindBy(xpath = "//footer//a[contains(@href, 'terms')]")
    private WebElement termsOfServiceLink;
    
    @FindBy(xpath = "//footer//a[contains(@href, 'contact')]")
    private WebElement contactUsLink;
    
    @FindBy(xpath = "//footer//a[contains(@href, 'support')]")
    private WebElement supportLink;
    
    @FindBy(xpath = "//footer//a[contains(@href, 'about')]")
    private WebElement aboutUsLink;
    
    @FindBy(className = "social-media-links")
    private WebElement socialMediaContainer;
    
    @FindBy(xpath = "//footer//a[contains(@href, 'facebook')]")
    private WebElement facebookLink;
    
    @FindBy(xpath = "//footer//a[contains(@href, 'twitter')]")
    private WebElement twitterLink;
    
    @FindBy(xpath = "//footer//a[contains(@href, 'linkedin')]")
    private WebElement linkedinLink;
    
    @FindBy(xpath = "//footer//a[contains(@href, 'instagram')]")
    private WebElement instagramLink;
    
    @FindBy(className = "newsletter-signup")
    private WebElement newsletterSignup;
    
    @FindBy(id = "newsletter-email")
    private WebElement newsletterEmailField;
    
    @FindBy(id = "newsletter-subscribe")
    private WebElement newsletterSubscribeButton;
    
    @FindBy(xpath = "//footer//a")
    private List<WebElement> allFooterLinks;
    
    @FindBy(className = "footer-section-links")
    private List<WebElement> footerSections;
    
    @FindBy(className = "company-address")
    private WebElement companyAddress;
    
    @FindBy(className = "company-phone")
    private WebElement companyPhone;
    
    @FindBy(className = "company-email")
    private WebElement companyEmail;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public FooterComponent(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Click on privacy policy link
     * @return FooterComponent for method chaining
     */
    @Step("Click on Privacy Policy link")
    public FooterComponent clickPrivacyPolicy() {
        click(privacyPolicyLink);
        logger.info("Clicked on Privacy Policy link");
        return this;
    }
    
    /**
     * Click on terms of service link
     * @return FooterComponent for method chaining
     */
    @Step("Click on Terms of Service link")
    public FooterComponent clickTermsOfService() {
        click(termsOfServiceLink);
        logger.info("Clicked on Terms of Service link");
        return this;
    }
    
    /**
     * Click on contact us link
     * @return FooterComponent for method chaining
     */
    @Step("Click on Contact Us link")
    public FooterComponent clickContactUs() {
        click(contactUsLink);
        logger.info("Clicked on Contact Us link");
        return this;
    }
    
    /**
     * Click on support link
     * @return FooterComponent for method chaining
     */
    @Step("Click on Support link")
    public FooterComponent clickSupport() {
        click(supportLink);
        logger.info("Clicked on Support link");
        return this;
    }
    
    /**
     * Click on about us link
     * @return FooterComponent for method chaining
     */
    @Step("Click on About Us link")
    public FooterComponent clickAboutUs() {
        click(aboutUsLink);
        logger.info("Clicked on About Us link");
        return this;
    }
    
    /**
     * Click on Facebook social media link
     * @return FooterComponent for method chaining
     */
    @Step("Click on Facebook link")
    public FooterComponent clickFacebook() {
        click(facebookLink);
        logger.info("Clicked on Facebook social media link");
        return this;
    }
    
    /**
     * Click on Twitter social media link
     * @return FooterComponent for method chaining
     */
    @Step("Click on Twitter link")
    public FooterComponent clickTwitter() {
        click(twitterLink);
        logger.info("Clicked on Twitter social media link");
        return this;
    }
    
    /**
     * Click on LinkedIn social media link
     * @return FooterComponent for method chaining
     */
    @Step("Click on LinkedIn link")
    public FooterComponent clickLinkedIn() {
        click(linkedinLink);
        logger.info("Clicked on LinkedIn social media link");
        return this;
    }
    
    /**
     * Click on Instagram social media link
     * @return FooterComponent for method chaining
     */
    @Step("Click on Instagram link")
    public FooterComponent clickInstagram() {
        click(instagramLink);
        logger.info("Clicked on Instagram social media link");
        return this;
    }
    
    /**
     * Subscribe to newsletter
     * @param email Email address to subscribe
     * @return FooterComponent for method chaining
     */
    @Step("Subscribe to newsletter with email: {email}")
    public FooterComponent subscribeToNewsletter(String email) {
        type(newsletterEmailField, email);
        click(newsletterSubscribeButton);
        logger.info("Subscribed to newsletter with email: {}", email);
        return this;
    }
    
    /**
     * Get copyright text
     * @return Copyright text from footer
     */
    @Step("Get copyright text")
    public String getCopyrightText() {
        String copyright = getText(copyrightText);
        logger.info("Copyright text: {}", copyright);
        return copyright;
    }
    
    /**
     * Get company address
     * @return Company address from footer
     */
    @Step("Get company address")
    public String getCompanyAddress() {
        String address = getText(companyAddress);
        logger.info("Company address: {}", address);
        return address;
    }
    
    /**
     * Get company phone number
     * @return Company phone number from footer
     */
    @Step("Get company phone")
    public String getCompanyPhone() {
        String phone = getText(companyPhone);
        logger.info("Company phone: {}", phone);
        return phone;
    }
    
    /**
     * Get company email
     * @return Company email from footer
     */
    @Step("Get company email")
    public String getCompanyEmail() {
        String email = getText(companyEmail);
        logger.info("Company email: {}", email);
        return email;
    }
    
    /**
     * Get all footer links
     * @return List of all footer link texts
     */
    @Step("Get all footer links")
    public List<String> getAllFooterLinks() {
        List<String> linkTexts = allFooterLinks.stream()
                .filter(link -> !getText(link).isEmpty())
                .map(this::getText)
                .toList();
        logger.info("Retrieved {} footer links", linkTexts.size());
        return linkTexts;
    }
    
    /**
     * Verify all essential footer links are present
     * @return true if all essential links are present, false otherwise
     */
    @Step("Verify footer links")
    public boolean verifyFooterLinks() {
        boolean allPresent = isDisplayed(privacyPolicyLink) &&
                           isDisplayed(termsOfServiceLink) &&
                           isDisplayed(contactUsLink);
        
        logger.info("Footer links verification: {}", allPresent);
        return allPresent;
    }
    
    /**
     * Verify social media links are present
     * @return true if social media links are present, false otherwise
     */
    @Step("Verify social media links")
    public boolean verifySocialMediaLinks() {
        boolean socialMediaPresent = isDisplayed(socialMediaContainer);
        logger.info("Social media links present: {}", socialMediaPresent);
        return socialMediaPresent;
    }
    
    /**
     * Check if newsletter signup is available
     * @return true if newsletter signup is available, false otherwise
     */
    public boolean isNewsletterSignupAvailable() {
        boolean available = isDisplayed(newsletterSignup);
        logger.info("Newsletter signup available: {}", available);
        return available;
    }
    
    /**
     * Click on any footer link by its text
     * @param linkText Text of the link to click
     * @return FooterComponent for method chaining
     */
    @Step("Click on footer link: {linkText}")
    public FooterComponent clickFooterLink(String linkText) {
        for (WebElement link : allFooterLinks) {
            if (getText(link).equalsIgnoreCase(linkText)) {
                click(link);
                logger.info("Clicked on footer link: {}", linkText);
                return this;
            }
        }
        logger.warn("Footer link '{}' not found", linkText);
        return this;
    }
    
    /**
     * Get number of footer sections
     * @return Number of footer sections
     */
    public int getFooterSectionsCount() {
        int count = footerSections.size();
        logger.info("Footer sections count: {}", count);
        return count;
    }
    
    /**
     * Scroll to footer (useful for pages with long content)
     * @return FooterComponent for method chaining
     */
    @Step("Scroll to footer")
    public FooterComponent scrollToFooter() {
        try {
            // Scroll footer into view
            ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", footerContainer);
            logger.info("Scrolled to footer");
        } catch (Exception e) {
            logger.warn("Could not scroll to footer", e);
        }
        return this;
    }
    
    /**
     * Implementation of abstract method from BaseComponent
     * @return true if footer component is loaded, false otherwise
     */
    @Override
    public boolean isComponentLoaded() {
        try {
            boolean loaded = isDisplayed(footerContainer);
            logger.info("Footer component loaded status: {}", loaded);
            return loaded;
        } catch (Exception e) {
            logger.error("Error checking if footer component is loaded", e);
            return false;
        }
    }
    
    /**
     * Verify all essential footer elements are present
     * @return true if all essential elements are present, false otherwise
     */
    @Step("Verify footer elements")
    public boolean verifyFooterElements() {
        boolean allPresent = isDisplayed(footerContainer) &&
                           isDisplayed(copyrightText);
        
        logger.info("Footer elements verification: {}", allPresent);
        return allPresent;
    }
}
