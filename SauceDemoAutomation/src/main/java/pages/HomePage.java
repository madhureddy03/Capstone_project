package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.DriverFactory;

import java.util.List;

public class HomePage {
	private WebDriver driver;

	private By productsTitle = By.cssSelector(".title");
	private By productNames = By.cssSelector(".inventory_item_name");
	private By cartBadge = By.cssSelector(".shopping_cart_badge");
	private By cartIcon = By.cssSelector(".shopping_cart_link");

	// Footer elements
	private By footerLogos = By.cssSelector("div.footer a img"); // all logos in footer
	private By footerCopy = By.className("footer_copy"); // copyright text
	private By socialIcons = By.cssSelector("div.social a"); // social media icons

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public String getTitle() {
		return driver.findElement(productsTitle).getText();
	}

	public List<String> getProductNames() {
		List<WebElement> items = driver.findElements(productNames);
		return items.stream().map(WebElement::getText).toList();
	}

	// ✅ Add product to cart by product name
	// Add product to cart by name
	// Add product to cart by product name
	public void addProductToCart(String productName) throws InterruptedException {
		String productId = "";

		switch (productName) {
		case "Sauce Labs Backpack":
			productId = "add-to-cart-sauce-labs-backpack";
			break;
		case "Sauce Labs Bike Light":
			productId = "add-to-cart-sauce-labs-bike-light";
			break;
		case "Sauce Labs Bolt T-Shirt":
			productId = "add-to-cart-sauce-labs-bolt-t-shirt";
			break;
		case "Sauce Labs Fleece Jacket":
			productId = "add-to-cart-sauce-labs-fleece-jacket";
			break;
		case "Sauce Labs Onesie":
			productId = "add-to-cart-sauce-labs-onesie";
			break;
		case "Test.allTheThings() T-Shirt (Red)":
			productId = "add-to-cart-test.allthethings()-t-shirt-(red)";
			break;
		default:
			throw new IllegalArgumentException("❌ Unknown product: " + productName);
		}

		WebElement addToCartButton = DriverFactory.waitForElementClickable(driver, By.id(productId));
		addToCartButton.click();
		Thread.sleep(3000);
	}

	// ✅ Get cart badge count text
	public String getCartBadge() {
		return driver.findElement(cartBadge).getText();
	}

	// ✅ Check if cart badge is visible
	public boolean isCartBadgeVisible() {
		return driver.findElements(cartBadge).size() > 0;
	}

	// ✅ Click cart and go to CartPage
	public CartPage clickCart() {
		driver.findElement(cartIcon).click();
		return new CartPage(driver);
	}

	// ================== Footer Methods ==================
	public List<WebElement> getFooterLogos() {
		return driver.findElements(footerLogos);
	}

	public WebElement getFooterCopyText() {
		return driver.findElement(footerCopy);
	}

	public List<WebElement> getSocialIcons() {
		return driver.findElements(socialIcons);
	}

}

//package pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//import utils.DriverFactory;
//
//import java.util.List;
//
//public class HomePage {
//    private WebDriver driver;
//
//    private By productsTitle = By.cssSelector(".title");
//    private By productNames = By.cssSelector(".inventory_item_name");
//    private By cartBadge = By.cssSelector(".shopping_cart_badge");
//    private By cartIcon = By.cssSelector(".shopping_cart_link");
//    
//    // ===== Corrected Footer elements =====
//    private By footerLogos = By.cssSelector(".footer_robot");   // robot logo in footer
//    private By footerCopy = By.cssSelector(".footer_copy");     // copyright text
//    private By socialIcons = By.cssSelector(".social a");       // social media icons
//
//    public HomePage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    public String getTitle() {
//        return driver.findElement(productsTitle).getText();
//    }
//
//    public List<String> getProductNames() {
//        List<WebElement> items = driver.findElements(productNames);
//        return items.stream().map(WebElement::getText).toList();
//    }
//
//    // ✅ Add product to cart by product name
//    public void addProductToCart(String productName) throws InterruptedException {
//        String productId = "";
//
//        switch (productName) {
//            case "Sauce Labs Backpack":
//                productId = "add-to-cart-sauce-labs-backpack";
//                break;
//            case "Sauce Labs Bike Light":
//                productId = "add-to-cart-sauce-labs-bike-light";
//                break;
//            case "Sauce Labs Bolt T-Shirt":
//                productId = "add-to-cart-sauce-labs-bolt-t-shirt";
//                break;
//            case "Sauce Labs Fleece Jacket":
//                productId = "add-to-cart-sauce-labs-fleece-jacket";
//                break;
//            case "Sauce Labs Onesie":
//                productId = "add-to-cart-sauce-labs-onesie";
//                break;
//            case "Test.allTheThings() T-Shirt (Red)":
//                productId = "add-to-cart-test.allthethings()-t-shirt-(red)";
//                break;
//            default:
//                throw new IllegalArgumentException("❌ Unknown product: " + productName);
//        }
//
//        WebElement addToCartButton = DriverFactory.waitForElementClickable(driver, By.id(productId));
//        addToCartButton.click();
//        Thread.sleep(3000);
//    }
//
//    // ✅ Get cart badge count text
//    public String getCartBadge() {
//        return driver.findElement(cartBadge).getText();
//    }
//
//    // ✅ Check if cart badge is visible
//    public boolean isCartBadgeVisible() {
//        return driver.findElements(cartBadge).size() > 0;
//    }
//
//    // ✅ Click cart and go to CartPage
//    public CartPage clickCart() {
//        driver.findElement(cartIcon).click();
//        return new CartPage(driver);
//    }
//    
//    // ================== Footer Methods ==================
//    public List<WebElement> getFooterLogos() {
//        return driver.findElements(footerLogos);
//    }
//
//    public WebElement getFooterCopyText() {
//        return driver.findElement(footerCopy);
//    }
//
//    public List<WebElement> getSocialIcons() {
//        return driver.findElements(socialIcons);
//    }
//}
