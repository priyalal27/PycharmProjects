package com.framework.pages;

import com.framework.base.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Search Page class demonstrating Polymorphism and advanced page interactions
 * This demonstrates:
 * 1. Inheritance - Extends BasePage
 * 2. Polymorphism - Overriding methods from parent class with different behavior
 * 3. Encapsulation - Private WebElements, public methods
 * 4. Page Factory Pattern - Using @FindBy annotations
 */
public class SearchPage extends BasePage {
    
    private static final Logger logger = LogManager.getLogger(SearchPage.class);
    
    // Page elements using Page Factory pattern (Encapsulation)
    @FindBy(id = "search-query")
    private WebElement searchQueryField;
    
    @FindBy(id = "search-submit")
    private WebElement searchSubmitButton;
    
    @FindBy(className = "search-results")
    private WebElement searchResultsContainer;
    
    @FindBy(xpath = "//div[@class='search-result-item']")
    private List<WebElement> searchResultItems;
    
    @FindBy(className = "results-count")
    private WebElement resultsCount;
    
    @FindBy(id = "no-results-message")
    private WebElement noResultsMessage;
    
    @FindBy(xpath = "//button[@class='filter-button']")
    private List<WebElement> filterButtons;
    
    @FindBy(id = "sort-dropdown")
    private WebElement sortDropdown;
    
    @FindBy(className = "pagination")
    private WebElement paginationContainer;
    
    @FindBy(xpath = "//a[@class='page-link']")
    private List<WebElement> pageLinks;
    
    @FindBy(id = "clear-search")
    private WebElement clearSearchButton;
    
    @FindBy(xpath = "//div[@class='search-suggestions']//li")
    private List<WebElement> searchSuggestions;
    
    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public SearchPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Perform search with new query
     * @param query Search query
     * @return SearchPage instance for method chaining
     */
    @Step("Search for: {query}")
    public SearchPage searchFor(String query) {
        type(searchQueryField, query);
        click(searchSubmitButton);
        logger.info("Performed search for: {}", query);
        return this;
    }
    
    /**
     * Get search results count
     * @return Number of search results
     */
    @Step("Get search results count")
    public int getSearchResultsCount() {
        int count = searchResultItems.size();
        logger.info("Search results count: {}", count);
        return count;
    }
    
    /**
     * Get results count text (e.g., "Showing 1-10 of 50 results")
     * @return Results count text
     */
    @Step("Get results count text")
    public String getResultsCountText() {
        String countText = getText(resultsCount);
        logger.info("Results count text: {}", countText);
        return countText;
    }
    
    /**
     * Click on search result by index
     * @param index Index of search result to click (0-based)
     * @return SearchPage instance for method chaining
     */
    @Step("Click on search result at index: {index}")
    public SearchPage clickSearchResult(int index) {
        if (index >= 0 && index < searchResultItems.size()) {
            click(searchResultItems.get(index));
            logger.info("Clicked on search result at index: {}", index);
        } else {
            logger.warn("Search result index {} is out of bounds. Total results: {}", index, searchResultItems.size());
        }
        return this;
    }
    
    /**
     * Click on search result by title text
     * @param title Title text of search result to click
     * @return SearchPage instance for method chaining
     */
    @Step("Click on search result with title: {title}")
    public SearchPage clickSearchResultByTitle(String title) {
        for (WebElement result : searchResultItems) {
            if (getText(result).contains(title)) {
                click(result);
                logger.info("Clicked on search result with title: {}", title);
                return this;
            }
        }
        logger.warn("Search result with title '{}' not found", title);
        return this;
    }
    
    /**
     * Get all search result titles
     * @return List of search result titles
     */
    @Step("Get all search result titles")
    public List<String> getAllSearchResultTitles() {
        List<String> titles = searchResultItems.stream()
            .map(this::getText)
            .collect(Collectors.toList());
        logger.info("Retrieved {} search result titles", titles.size());
        return titles;
    }
    
    /**
     * Check if no results message is displayed
     * @return true if no results message is displayed, false otherwise
     */
    @Step("Check if no results message is displayed")
    public boolean isNoResultsMessageDisplayed() {
        boolean isDisplayed = isDisplayed(noResultsMessage);
        logger.info("No results message displayed: {}", isDisplayed);
        return isDisplayed;
    }
    
    /**
     * Get no results message text
     * @return No results message text
     */
    @Step("Get no results message")
    public String getNoResultsMessage() {
        String message = getText(noResultsMessage);
        logger.info("No results message: {}", message);
        return message;
    }
    
    /**
     * Apply filter by filter name
     * @param filterName Name of the filter to apply
     * @return SearchPage instance for method chaining
     */
    @Step("Apply filter: {filterName}")
    public SearchPage applyFilter(String filterName) {
        for (WebElement filter : filterButtons) {
            if (getText(filter).equalsIgnoreCase(filterName)) {
                click(filter);
                logger.info("Applied filter: {}", filterName);
                return this;
            }
        }
        logger.warn("Filter '{}' not found", filterName);
        return this;
    }
    
    /**
     * Clear all filters
     * @return SearchPage instance for method chaining
     */
    @Step("Clear all filters")
    public SearchPage clearFilters() {
        // Assuming there's a way to clear all filters
        for (WebElement filter : filterButtons) {
            if (filter.getAttribute("class").contains("active")) {
                click(filter);
                logger.info("Cleared filter: {}", getText(filter));
            }
        }
        return this;
    }
    
    /**
     * Clear search query
     * @return SearchPage instance for method chaining
     */
    @Step("Clear search")
    public SearchPage clearSearch() {
        click(clearSearchButton);
        logger.info("Cleared search");
        return this;
    }
    
    /**
     * Go to specific page in pagination
     * @param pageNumber Page number to go to
     * @return SearchPage instance for method chaining
     */
    @Step("Go to page: {pageNumber}")
    public SearchPage goToPage(int pageNumber) {
        String pageText = String.valueOf(pageNumber);
        for (WebElement pageLink : pageLinks) {
            if (getText(pageLink).equals(pageText)) {
                click(pageLink);
                logger.info("Navigated to page: {}", pageNumber);
                return this;
            }
        }
        logger.warn("Page {} not found in pagination", pageNumber);
        return this;
    }
    
    /**
     * Check if pagination is displayed
     * @return true if pagination is displayed, false otherwise
     */
    public boolean isPaginationDisplayed() {
        return isDisplayed(paginationContainer);
    }
    
    /**
     * Get search suggestions
     * @return List of search suggestion texts
     */
    @Step("Get search suggestions")
    public List<String> getSearchSuggestions() {
        List<String> suggestions = searchSuggestions.stream()
            .map(this::getText)
            .collect(Collectors.toList());
        logger.info("Retrieved {} search suggestions", suggestions.size());
        return suggestions;
    }
    
    /**
     * Click on search suggestion
     * @param suggestion Suggestion text to click
     * @return SearchPage instance for method chaining
     */
    @Step("Click on search suggestion: {suggestion}")
    public SearchPage clickSearchSuggestion(String suggestion) {
        for (WebElement suggestionElement : searchSuggestions) {
            if (getText(suggestionElement).equalsIgnoreCase(suggestion)) {
                click(suggestionElement);
                logger.info("Clicked on search suggestion: {}", suggestion);
                return this;
            }
        }
        logger.warn("Search suggestion '{}' not found", suggestion);
        return this;
    }
    
    /**
     * Polymorphism demonstration - Override takeScreenshot with custom behavior for search page
     * This method adds search context to the screenshot
     * @return screenshot as byte array
     */
    @Override
    public byte[] takeScreenshot() {
        logger.info("Taking screenshot from Search page with current query context");
        // Could add search-specific screenshot logic here
        return super.takeScreenshot(); // Call parent implementation
    }
    
    /**
     * Polymorphism demonstration - Override getText with search-specific behavior
     * This method adds search context logging
     * @param element WebElement to get text from
     * @return Text from the element
     */
    @Override
    public String getText(WebElement element) {
        String text = super.getText(element); // Call parent implementation
        logger.debug("Retrieved text from search page element: {}", text);
        return text;
    }
    
    /**
     * Implementation of abstract method from BasePage
     * Checks if the search page is loaded by verifying key elements
     * @return true if page is loaded, false otherwise
     */
    @Override
    public boolean isPageLoaded() {
        try {
            boolean isLoaded = isDisplayed(searchQueryField) && 
                             isDisplayed(searchSubmitButton) && 
                             (isDisplayed(searchResultsContainer) || isDisplayed(noResultsMessage));
            logger.info("Search page loaded status: {}", isLoaded);
            return isLoaded;
        } catch (Exception e) {
            logger.error("Error checking if search page is loaded", e);
            return false;
        }
    }
    
    /**
     * Navigate back to dashboard
     * @return DashboardPage instance
     */
    @Step("Navigate back to dashboard")
    public DashboardPage navigateBackToDashboard() {
        // Assuming there's a back button or home button
        driver.navigate().back();
        logger.info("Navigated back to dashboard");
        return new DashboardPage(driver);
    }
}
