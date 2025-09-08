package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;

public class CartPage {
	private WebDriver driver;

	// Locators
	private By cartBadge = By.className("shopping_cart_badge");
	private By cartIcon = By.id("shopping_cart_container");
	private By checkoutIcon = By.id("checkout");

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	// Navigate to cart
	public void openCart() throws InterruptedException {
		WebElement cart = DriverFactory.waitForElementClickable(driver, cartIcon);
		cart.click();
		Thread.sleep(3000);
	}

	// Check if product exists in cart
	public boolean isProductInCart(String productName) {
		String itemXpath = "//div[@class='inventory_item_name' and text()='" + productName + "']";
		try {
			WebElement item = DriverFactory.waitForElementVisible(driver, By.xpath(itemXpath));
			return item.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// Remove product from cart (✅ updated with safe element check)
	public void removeProduct(String productName) throws InterruptedException {
		String productId = "";

		switch (productName) {
		case "Sauce Labs Backpack":
			productId = "remove-sauce-labs-backpack";
			break;
		case "Sauce Labs Bike Light":
			productId = "remove-sauce-labs-bike-light";
			break;
		case "Sauce Labs Bolt T-Shirt":
			productId = "remove-sauce-labs-bolt-t-shirt";
			break;
		case "Sauce Labs Fleece Jacket":
			productId = "remove-sauce-labs-fleece-jacket";
			break;
		case "Sauce Labs Onesie":
			productId = "remove-sauce-labs-onesie";
			break;
		case "Test.allTheThings() T-Shirt (Red)":
			productId = "remove-test.allthethings()-t-shirt-(red)";
			break;
		default:
			throw new IllegalArgumentException("❌ Unknown product: " + productName);
		}

		By removeBtnLocator = By.id(productId);
		if (isElementPresent(removeBtnLocator)) {
			WebElement removeBtn = DriverFactory.waitForElementClickable(driver, removeBtnLocator);
			removeBtn.click();
			Thread.sleep(3000);
		} else {
			System.out.println("⚠ Product not found in cart: " + productName);
		}
	}

	// Get cart badge count (number of items in cart)
	public int getCartBadgeCount() {
		try {
			WebElement badge = DriverFactory.waitForElementVisible(driver, cartBadge);
			return Integer.parseInt(badge.getText());
		} catch (Exception e) {
			return 0; // badge not visible
		}
	}

	public void clickCheckout() {
		WebElement checkoutBtn = DriverFactory.waitForElementClickable(driver, checkoutIcon);
		checkoutBtn.click();
	}

	// ✅ helper method to avoid TimeoutException
	private boolean isElementPresent(By locator) {
		return driver.findElements(locator).size() > 0;
	}
}
