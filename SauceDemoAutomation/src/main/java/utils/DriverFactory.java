package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {
	private static WebDriver driver;
	private static WebDriverWait wait;

	public static WebDriver getDriver() {
		if (driver == null) {
			// WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--no-sandbox");

			// ✅ Disable Chrome password manager completely
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);

			// ✅ Stronger suppression
			options.addArguments("--disable-save-password-bubble");
			options.addArguments("--disable-password-manager-reauthentication");
			options.addArguments(
					"--disable-features=AutofillEnableAccountWalletStorage,PasswordManagerOnboarding,PasswordManagerUI");

			// ✅ Force Incognito (recommended)
			options.addArguments("--incognito");

			// ✅ Block other popup/notification interruptions
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");

			// Optional: use a fresh Chrome profile every run
			options.addArguments("--user-data-dir=/tmp/chrome-profile");

			driver = new ChromeDriver(options);
			driver.manage().window().maximize();

			// Implicit Wait
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			// Explicit Wait (shared)
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		}
		return driver;
	}

	public static WebDriverWait getWait() {
		if (wait == null && driver != null) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		}
		return wait;
	}

	// Explicit wait helpers (shared wait)
	public static WebElement waitForVisibility(By locator) {
		return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static WebElement waitForClickability(By locator) {
		return getWait().until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void waitForTextToBePresent(By locator, String text) {
		getWait().until(ExpectedConditions.textToBe(locator, text));
	}

	// ===== Overloaded versions that take WebDriver explicitly =====
	public static WebElement waitForElementVisible(WebDriver driver, By locator) {
		WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return localWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static WebElement waitForElementClickable(WebDriver driver, By locator) {
		WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return localWait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
			wait = null;
		}
	}
}
