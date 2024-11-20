package org.pages;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordGeneratorPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for various elements on the page
    private By passwordLengthSlider = By.id("password-length-slider");
    private By uppercaseCheckbox = By.id("include-uppercase");
    private By lowercaseCheckbox = By.id("include-lowercase");
    private By digitsCheckbox = By.id("include-digits");
    private By symbolsCheckbox = By.id("include-symbols");
    private By generateButton = By.id("generate-password");
    private By passwordField = By.id("generated-password");
    private By copyButton = By.id("copy-password");

    // Constructor
    public PasswordGeneratorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    // Set password length by adjusting slider
    public void setPasswordLength(int length) {
        WebElement slider = wait.until(ExpectedConditions.elementToBeClickable(passwordLengthSlider));
        slider.sendKeys(String.valueOf(length));  // Adjust slider based on input
    }

    // Select or deselect character options
    public void selectCharacterOptions(boolean uppercase, boolean lowercase, boolean digits, boolean symbols) {
        setCheckbox(uppercaseCheckbox, uppercase);
        setCheckbox(lowercaseCheckbox, lowercase);
        setCheckbox(digitsCheckbox, digits);
        setCheckbox(symbolsCheckbox, symbols);
    }

    // Helper method to set checkboxes
    private void setCheckbox(By locator, boolean shouldSelect) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (checkbox.isSelected() != shouldSelect) {
            checkbox.click();
        }
    }

    // Generate the password
    public void generatePassword() {
        WebElement generateBtn = wait.until(ExpectedConditions.elementToBeClickable(generateButton));
        generateBtn.click();
    }

    // Retrieve the generated password
    public String getPassword() {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        return passwordElement.getText();
    }

    // Copy the generated password
    public void copyPassword() {
        WebElement copyBtn = wait.until(ExpectedConditions.elementToBeClickable(copyButton));
        copyBtn.click();
    }

    // Validate password against specified criteria
    public boolean validatePasswordCriteria(String password, boolean uppercase, boolean lowercase, boolean digits, boolean symbols) {
        boolean containsUppercase = uppercase && password.matches(".*[A-Z].*");
        boolean containsLowercase = lowercase && password.matches(".*[a-z].*");
        boolean containsDigits = digits && password.matches(".*\\d.*");
        boolean containsSymbols = symbols && password.matches(".*[^a-zA-Z0-9].*");

        return (!uppercase || containsUppercase) &&
               (!lowercase || containsLowercase) &&
               (!digits || containsDigits) &&
               (!symbols || containsSymbols);
    }
}