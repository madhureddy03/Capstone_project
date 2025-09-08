package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private WebDriver driver;

	private By usernameInput = By.id("user-name");
	private By passwordInput = By.id("password");
	private By loginButton = By.id("login-button");
	private By errorMessage = By.cssSelector("h3[data-test='error']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String username) {
		driver.findElement(usernameInput).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordInput).sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(loginButton).click();
	}

	public String getErrorMessage() {
		return driver.findElement(errorMessage).getText();
	}

	public HomePage login(String username, String password) {
		driver.findElement(usernameInput).clear();
		driver.findElement(usernameInput).sendKeys(username);

		driver.findElement(passwordInput).clear();
		driver.findElement(passwordInput).sendKeys(password);

		driver.findElement(loginButton).click();

		// After successful login, return HomePage object
		return new HomePage(driver);

	}
}
