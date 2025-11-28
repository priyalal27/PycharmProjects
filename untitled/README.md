# Selenium TestNG Allure Framework

A comprehensive test automation framework built with Selenium WebDriver, TestNG, and Allure reporting, demonstrating Object-Oriented Programming principles and best practices.

## 🏗️ Framework Architecture

```
selenium-testng-allure-framework/
│
├── pom.xml                          # Maven dependencies and plugins
├── README.md                        # Framework documentation
├── logs/                           # Log files directory
├── allure-results/                 # Allure test results
│
├── src/
│   ├── main/
│   │   ├── java/com/framework/
│   │   │   ├── base/
│   │   │   │   ├── BasePage.java          # Abstract base page class
│   │   │   │   └── BaseComponent.java     # Abstract base component class
│   │   │   ├── interfaces/
│   │   │   │   └── IPageActions.java      # Page actions interface
│   │   │   ├── pages/
│   │   │   │   ├── LoginPage.java         # Basic login page object
│   │   │   │   ├── EnhancedLoginPage.java # Advanced POM login page
│   │   │   │   ├── DashboardPage.java     # Dashboard page object
│   │   │   │   └── SearchPage.java        # Search page object (Polymorphism)
│   │   │   ├── components/
│   │   │   │   ├── HeaderComponent.java   # Reusable header component
│   │   │   │   ├── FooterComponent.java   # Reusable footer component
│   │   │   │   └── NavigationComponent.java # Navigation component
│   │   │   ├── wrappers/
│   │   │   │   ├── Button.java            # Enhanced button wrapper
│   │   │   │   ├── TextBox.java           # Enhanced textbox wrapper
│   │   │   │   └── Dropdown.java          # Enhanced dropdown wrapper
│   │   │   ├── utils/
│   │   │   │   ├── DriverFactory.java     # WebDriver factory
│   │   │   │   ├── ConfigReader.java      # Configuration reader
│   │   │   │   ├── TestListener.java      # TestNG listener
│   │   │   │   └── PageObjectUtils.java   # POM utility methods
│   │   │   └── exceptions/
│   │   │       └── FrameworkException.java # Custom exception class
│   │   └── resources/
│   │       ├── config.properties          # Framework configuration
│   │       └── log4j2.xml                 # Logging configuration
│   │
│   └── test/
│       ├── java/com/framework/tests/
│       │   ├── BaseTest.java              # Base test class
│       │   ├── LoginTest.java             # Basic login test cases
│       │   ├── DashboardTest.java         # Dashboard test cases
│       │   └── EnhancedPOMTest.java       # Advanced POM pattern tests
│       └── resources/
│           └── testng.xml                 # TestNG suite configuration
```

## 🎯 Key Features

### Object-Oriented Programming Principles
- **Abstraction**: `IPageActions` interface, `BasePage` and `BaseComponent` abstract classes
- **Inheritance**: All pages extend `BasePage`, components extend `BaseComponent`, tests extend `BaseTest`
- **Encapsulation**: Private WebElements with public methods, wrapper classes, configuration encapsulation
- **Polymorphism**: Method overriding in `SearchPage` and `EnhancedLoginPage` classes

### Advanced Page Object Model (POM) Implementation
- **Basic POM**: Traditional page objects with Page Factory pattern (`LoginPage`, `DashboardPage`)
- **Enhanced POM**: Advanced patterns with components and wrappers (`EnhancedLoginPage`)
- **Component-Based POM**: Reusable components (`HeaderComponent`, `FooterComponent`, `NavigationComponent`)
- **Element Wrapper Pattern**: Enhanced element functionality (`Button`, `TextBox`, `Dropdown` wrappers)
- **Page Factory Pattern**: Automatic element initialization with `@FindBy` annotations
- **Fluent Interface**: Method chaining for readable test code
- **Page Component Pattern**: Modular, reusable UI components across pages

### Framework Capabilities
- **Multi-Level POM Architecture**: Basic POM, Enhanced POM, Component-based POM
- **Cross-browser Support**: Chrome, Firefox, Edge, Safari
- **Parallel Execution**: TestNG parallel execution support
- **Comprehensive Logging**: Log4j2 with multiple appenders and component-specific logging
- **Allure Reporting**: Detailed test reports with screenshots and step annotations
- **Configuration Management**: Externalized configuration via properties file
- **Exception Handling**: Custom framework exceptions with context
- **Data-Driven Testing**: TestNG DataProvider with multiple data sets
- **Element Wrappers**: Enhanced WebElement functionality with error handling
- **POM Utilities**: Helper methods for common POM operations
- **Component Reusability**: Shared components across multiple pages

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser installed

### Installation
1. Clone the repository
2. Navigate to the project directory
3. Install dependencies:
   ```bash
   mvn clean install
   ```

### Configuration
Update `src/main/resources/config.properties` with your test environment details:

```properties
# Browser configuration
browser=chrome
headless=false
base.url=https://your-app-url.com

# Timeout settings
implicit.wait=10
explicit.wait=20
page.load.timeout=30

# Test execution
thread.count=3
screenshot.on.failure=true
```

## 🧪 Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Suite
```bash
# Smoke tests
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml -Dgroups=smoke

# Regression tests  
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml -Dgroups=regression

# Login tests only
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml -Dtest=LoginTest

# Enhanced POM tests
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml -Dtest=EnhancedPOMTest

# Component tests
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml -Dgroups=components

# Element wrapper tests
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml -Dgroups=wrappers
```

### Run with Different Browser
```bash
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

### Run in Headless Mode
```bash
mvn clean test -Dheadless=true
```

### Parallel Execution
```bash
mvn clean test -Dparallel=methods -DthreadCount=3
```

## 📊 Generating Reports

### Allure Reports
1. Run tests to generate results:
   ```bash
   mvn clean test
   ```

2. Generate and serve Allure report:
   ```bash
   mvn allure:serve
   ```

3. Generate static report:
   ```bash
   mvn allure:report
   ```

### View Reports
- Allure report will open automatically in your browser
- Reports include test results, screenshots, logs, and execution details

## 🏛️ Framework Components

### Base Classes
- **`BasePage`**: Abstract class with common page operations
- **`BaseTest`**: Base test class with setup/teardown methods

### Page Objects
- **`LoginPage`**: Login functionality with various test scenarios
- **`DashboardPage`**: Dashboard operations and navigation
- **`SearchPage`**: Search functionality demonstrating polymorphism

### Utilities
- **`DriverFactory`**: WebDriver instance management with factory pattern
- **`ConfigReader`**: Configuration properties reader
- **`TestListener`**: TestNG listener for screenshots and reporting

### Test Classes
- **`LoginTest`**: Comprehensive login test scenarios
- **`DashboardTest`**: Dashboard functionality tests with data-driven approach

## 🔧 Customization

### Adding New Page Objects

#### Basic Page Object
1. Create new page class extending `BasePage`
2. Implement `isPageLoaded()` method
3. Add page-specific methods with `@FindBy` annotations

```java
public class NewPage extends BasePage {
    @FindBy(id = "element-id")
    private WebElement element;
    
    public NewPage(WebDriver driver) {
        super(driver);
    }
    
    @Override
    public boolean isPageLoaded() {
        return isDisplayed(element);
    }
}
```

#### Enhanced Page Object with Components and Wrappers
```java
public class EnhancedNewPage extends BasePage {
    // Components
    private HeaderComponent headerComponent;
    private FooterComponent footerComponent;
    
    // WebElements
    @FindBy(id = "username")
    private WebElement usernameElement;
    
    @FindBy(id = "submit-btn")
    private WebElement submitButtonElement;
    
    // Element Wrappers
    private TextBox usernameTextBox;
    private Button submitButton;
    
    public EnhancedNewPage(WebDriver driver) {
        super(driver);
        initializeComponents();
        initializeWrappers();
    }
    
    private void initializeComponents() {
        headerComponent = new HeaderComponent(driver);
        footerComponent = new FooterComponent(driver);
    }
    
    private void initializeWrappers() {
        usernameTextBox = new TextBox(usernameElement, driver, "Username");
        submitButton = new Button(submitButtonElement, driver, "Submit");
    }
    
    public EnhancedNewPage enterUsername(String username) {
        usernameTextBox.type(username);
        return this;
    }
    
    public void clickSubmit() {
        submitButton.click();
    }
    
    @Override
    public boolean isPageLoaded() {
        return usernameTextBox.isDisplayed() && submitButton.isDisplayed();
    }
}
```

#### Creating Page Components
```java
public class CustomComponent extends BaseComponent {
    @FindBy(className = "custom-element")
    private WebElement customElement;
    
    public CustomComponent(WebDriver driver) {
        super(driver);
    }
    
    public void performAction() {
        click(customElement);
    }
    
    @Override
    public boolean isComponentLoaded() {
        return isDisplayed(customElement);
    }
}
```

### Adding New Test Classes
1. Create test class extending `BaseTest`
2. Add TestNG annotations and Allure annotations
3. Use appropriate test groups

```java
@Epic("Feature Name")
@Feature("Sub Feature")
public class NewTest extends BaseTest {
    
    @Test(groups = {"smoke", "regression"})
    @Story("Test Story")
    @Severity(SeverityLevel.HIGH)
    public void testMethod() {
        // Test implementation
    }
}
```

### Configuration Options
All framework configurations are externalized in `config.properties`:
- Browser settings
- Environment URLs  
- Timeouts
- Execution parameters
- Logging levels

## 📝 Best Practices Implemented

### Design Patterns
1. **Page Object Model**: Clean separation of page logic and tests with multiple POM levels
2. **Factory Pattern**: WebDriver creation and element wrapper factories
3. **Observer Pattern**: TestNG listeners for test events and reporting
4. **Template Method**: BaseTest and BasePage provide common structure
5. **Wrapper Pattern**: Enhanced element functionality with custom wrappers
6. **Component Pattern**: Reusable UI components across pages
7. **Builder Pattern**: Fluent interface for method chaining

### POM Architecture Levels
1. **Level 1 - Basic POM**: Traditional page objects (`LoginPage`, `DashboardPage`)
2. **Level 2 - Enhanced POM**: Advanced patterns with wrappers (`EnhancedLoginPage`)
3. **Level 3 - Component-based POM**: Modular components (`HeaderComponent`, `FooterComponent`)
4. **Level 4 - Utility-enhanced POM**: Helper methods and utilities (`PageObjectUtils`)

### Code Quality Practices
1. **Configuration Management**: External property files with environment support
2. **Comprehensive Logging**: Structured logging with component-specific contexts
3. **Exception Handling**: Custom exceptions with meaningful messages and context
4. **Test Data Management**: DataProvider with multiple data sets
5. **Reporting Integration**: Allure annotations throughout framework
6. **Code Reusability**: Common utilities, base classes, and shared components
7. **Error Recovery**: Retry mechanisms and graceful error handling
8. **Performance Optimization**: Lazy loading and efficient element location

## 🔍 Troubleshooting

### Common Issues
1. **WebDriver not found**: Ensure WebDriverManager is properly configured
2. **Element not found**: Check wait conditions and locators
3. **Test failures**: Review logs in `logs/` directory
4. **Configuration issues**: Verify `config.properties` settings

### Debug Mode
Enable debug logging by updating `config.properties`:
```properties
log.level=DEBUG
```

## 🤝 Contributing

1. Follow OOP principles demonstrated in the framework
2. Add appropriate logging and exception handling
3. Include Allure annotations for reporting
4. Write comprehensive test documentation
5. Maintain consistent code style

## 📄 License

This framework is provided as a template for test automation projects.

## 📞 Support

For questions or issues:
1. Check the logs in `logs/` directory
2. Review Allure reports for test details
3. Verify configuration in `config.properties`
4. Ensure all dependencies are properly installed

---

**Built with ❤️ using Selenium, TestNG, and Allure**
