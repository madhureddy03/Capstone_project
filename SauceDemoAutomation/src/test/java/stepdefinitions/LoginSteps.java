package stepdefinitions;

import pages.HomePage;
import pages.LoginPage;
import utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;

	@Before
	public void setUp() {
		driver = DriverFactory.getDriver();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@Given("user is on the login page")
	public void user_on_login_page() {
		driver.get("https://www.saucedemo.com/");
	}

	@When("user enters valid username {string} and password {string}")
	public void user_enters_valid_credentials(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@When("user enters invalid username {string} and password {string}")
	public void user_enters_invalid_credentials(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@When("user enters blank username {string} and password {string}")
	public void user_enters_blank_credentials(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@When("user enters valid username {string} and blank password {string}")
	public void user_enters_valid_username_and_blank_password(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@When("user clicks login")
	public void user_clicks_login() {
		loginPage.clickLogin();
	}

	@Then("user should see the products page")
	public void verify_successful_login() {
		Assert.assertEquals(homePage.getTitle(), "Products");
	}

	@Then("user should see error message {string}")
	public void verify_login_error(String expectedError) {
		Assert.assertEquals(loginPage.getErrorMessage(), expectedError);
	}

	@After
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
