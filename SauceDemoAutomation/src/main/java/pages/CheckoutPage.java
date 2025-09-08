package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;

public class CheckoutPage {

	private WebDriver driver;

	// Locators
	private By firstNameInput = By.id("first-name");
	private By lastNameInput = By.id("last-name");
	private By postalCodeInput = By.id("postal-code");
	private By continueButton = By.id("continue");
	private By cancelButton = By.id("cancel");
	private By errorMessage = By.cssSelector("h3[data-test='error']");
	private By finishButton = By.id("finish");
	private By confirmationMessage = By.cssSelector(".complete-header");

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterFirstName(String firstName) {
		DriverFactory.waitForElementVisible(driver, firstNameInput).clear();
		DriverFactory.waitForElementVisible(driver, firstNameInput).sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		DriverFactory.waitForElementVisible(driver, lastNameInput).clear();
		DriverFactory.waitForElementVisible(driver, lastNameInput).sendKeys(lastName);
	}

	public void enterPostalCode(String postalCode) {
		DriverFactory.waitForElementVisible(driver, postalCodeInput).clear();
		DriverFactory.waitForElementVisible(driver, postalCodeInput).sendKeys(postalCode);
	}

	public void clickContinue() {
		DriverFactory.waitForElementClickable(driver, continueButton).click();
	}

	public void clickCancel() {
		DriverFactory.waitForElementClickable(driver, cancelButton).click();
	}

	public boolean isCheckoutFormDisplayed() {
		try {
			return DriverFactory.waitForElementVisible(driver, firstNameInput).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getErrorMessage() {
		try {
			WebElement error = DriverFactory.waitForElementVisible(driver, errorMessage);
			return error.getText();
		} catch (Exception e) {
			return "";
		}
	}

	public void clickFinish() {
		DriverFactory.waitForElementClickable(driver, finishButton).click();
	}

	public String getConfirmationMessage() {
		return DriverFactory.waitForElementVisible(driver, confirmationMessage).getText();
	}
}
