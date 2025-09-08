package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverFactory;

public class CartSteps {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private CartPage cartPage;
	private CheckoutPage checkoutPage;

	@Given("user is logged in with username {string} and password {string}")
	public void user_is_logged_in_with_username_and_password(String username, String password)
			throws InterruptedException {
		driver = DriverFactory.getDriver();
		driver.get("https://www.saucedemo.com/");
		loginPage = new LoginPage(driver);
		homePage = loginPage.login(username, password);
		Thread.sleep(3000);
		Assert.assertEquals(homePage.getTitle(), "Products", "Login failed or not on Products page");
	}

	@When("user adds {string} to the cart")
	public void user_adds_to_the_cart(String productName) throws InterruptedException {
		homePage.addProductToCart(productName);
	}

	@Then("cart badge should show {string}")
	public void cart_badge_should_show(String count) {
		String actualBadge = homePage.getCartBadge();
		Assert.assertEquals(actualBadge, count, "Cart badge count mismatch");
	}

	@Then("cart badge should not be visible")
	public void cart_badge_should_not_be_visible() {
		Assert.assertFalse(homePage.isCartBadgeVisible(), "Cart badge is still visible when it should not be");
	}

	@Then("user should see {string} in the cart")
	public void user_should_see_in_the_cart(String productName) {
		cartPage = homePage.clickCart();
		Assert.assertTrue(cartPage.isProductInCart(productName), "Product not found in cart: " + productName);
	}

	@Then("user should see {string} and {string} in the cart")
	public void user_should_see_and_in_the_cart(String product1, String product2) {
		cartPage = homePage.clickCart();
		Assert.assertTrue(cartPage.isProductInCart(product1), "Product not found in cart: " + product1);
		Assert.assertTrue(cartPage.isProductInCart(product2), "Product not found in cart: " + product2);
	}

	@When("user removes {string} from the cart")
	public void user_removes_from_the_cart(String productName) throws InterruptedException {
		cartPage = homePage.clickCart();
		cartPage.removeProduct(productName);
	}

	@When("user clicks on the cart icon")
	public void click_cart_icon() {
		cartPage = homePage.clickCart();
	}

	@Then("user should be navigated to the cart page")
	public void user_should_be_navigated_to_the_cart_page() {
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("cart"), "User is not on the cart page!");
	}

	@When("user clicks on Checkout")
	public void user_clicks_on_checkout() {
		cartPage.clickCheckout();
		// Initialize checkoutPage immediately
		checkoutPage = new CheckoutPage(driver);
	}

	@Then("checkout page should be displayed")
	public void checkout_page_should_be_displayed() {
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("checkout-step-one"), "Checkout page not displayed!");
	}

	@When("user should see Checkout info form")
	public void checkout_form_be_displayed() {
		checkoutPage = new CheckoutPage(driver);
		Assert.assertTrue(checkoutPage.isCheckoutFormDisplayed(), "Checkout info form is not visible!");
	}

	@When("user enters first name {string}")
	public void user_enters_first_name(String firstName) {
		checkoutPage.enterFirstName(firstName);
	}

	@When("user enters last name {string}")
	public void user_enters_last_name(String lastName) {
		checkoutPage.enterLastName(lastName);
	}

	@When("user enters postal code {string}")
	public void user_enters_postal_code(String postalCode) {
		checkoutPage.enterPostalCode(postalCode);
	}

	@When("user clicks on Continue")
	public void user_clicks_on_continue() {
		checkoutPage.clickContinue();
	}

	@Then("user should be navigated to the Checkout Overview page")
	public void user_should_be_navigated_to_checkout_overview_page() {
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("checkout-step-two"), "User is not on Checkout Overview page!");
	}

	@Then("user should see cart error message {string}")
	public void user_should_see_error_message(String expectedError) {
		String actualError = checkoutPage.getErrorMessage();
		Assert.assertTrue(actualError.contains(expectedError),
				"Expected message to contain: [" + expectedError + "] but found: [" + actualError + "]");
	}

	@When("user clicks on Cancel")
	public void user_clicks_on_cancel() {
		if (checkoutPage == null) {
			checkoutPage = new CheckoutPage(driver); // initialize if not already
		}
		checkoutPage.clickCancel();
	}

	@Then("user should be navigated back to the Products page")
	public void user_should_be_navigated_back_to_products_page() {
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("inventory"), "User is not on Products page after cancel");
	}

	@Then("user should not see {string} in the cart")
	public void user_should_not_see_in_the_cart(String productName) {
		Assert.assertFalse(cartPage.isProductInCart(productName), "Product is still in the cart: " + productName);
	}

	@When("user clicks on Finish")
	public void user_clicks_on_finish() {
		if (checkoutPage == null) {
			checkoutPage = new CheckoutPage(driver);
		}
		checkoutPage.clickFinish();
	}

	@Then("user should see order confirmation message {string}")
	public void user_should_see_order_confirmation_message(String expectedMessage) {
		if (checkoutPage == null) {
			checkoutPage = new CheckoutPage(driver);
		}
		String actualMessage = checkoutPage.getConfirmationMessage();
		Assert.assertEquals(actualMessage, expectedMessage, "Order confirmation message mismatch");
	}

}
