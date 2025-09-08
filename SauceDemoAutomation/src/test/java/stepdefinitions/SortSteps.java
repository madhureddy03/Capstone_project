package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortSteps {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;

	public SortSteps() {
		driver = DriverFactory.getDriver();
	}

	@When("user views the product sort dropdown")
	public void user_views_the_product_sort_dropdown() {
		WebElement dropdown = driver.findElement(By.className("product_sort_container"));
		Assert.assertTrue(dropdown.isDisplayed(), "Sort dropdown is not visible");
	}

	@Then("dropdown should have options:")
	public void dropdown_should_have_options(io.cucumber.datatable.DataTable dataTable) {
		List<String> expectedOptions = dataTable.asList();
		Select select = new Select(driver.findElement(By.className("product_sort_container")));
		List<String> actualOptions = new ArrayList<>();
		for (WebElement option : select.getOptions()) {
			actualOptions.add(option.getText().trim());
		}
		Assert.assertEquals(actualOptions, expectedOptions, "Dropdown options mismatch");
	}

	@When("user selects {string} from sort dropdown")
	public void user_selects_from_sort_dropdown(String option) {
		Select select = new Select(driver.findElement(By.className("product_sort_container")));
		select.selectByVisibleText(option);
	}

	@Then("products should be sorted in ascending order by name")
	public void products_should_be_sorted_in_ascending_order_by_name() {
		List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
		List<String> actualNames = new ArrayList<>();
		for (WebElement p : products)
			actualNames.add(p.getText().trim());

		List<String> sortedNames = new ArrayList<>(actualNames);
		Collections.sort(sortedNames);

		Assert.assertEquals(actualNames, sortedNames, "Products are not sorted alphabetically A to Z");
	}

	@Then("products should be sorted in ascending order by price")
	public void products_should_be_sorted_in_ascending_order_by_price() {
		List<WebElement> products = driver.findElements(By.className("inventory_item_price"));
		List<Double> actualPrices = new ArrayList<>();
		for (WebElement p : products) {
			String text = p.getText().replace("$", "").trim();
			actualPrices.add(Double.parseDouble(text));
		}

		List<Double> sortedPrices = new ArrayList<>(actualPrices);
		Collections.sort(sortedPrices);

		Assert.assertEquals(actualPrices, sortedPrices, "Products are not sorted by price low to high");
	}

	@Then("products should be sorted in descending order by name")
	public void products_should_be_sorted_by_name_descending() {
		List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));
		List<String> names = productNames.stream().map(WebElement::getText).toList();
		List<String> sortedNames = new ArrayList<>(names);
		sortedNames.sort(Collections.reverseOrder());
		Assert.assertEquals(names, sortedNames, "Products are not sorted by Name (Z to A)");
	}

	@Then("products should be sorted in descending order by price")
	public void products_should_be_sorted_by_price_descending() {
		List<WebElement> productPrices = driver.findElements(By.className("inventory_item_price"));
		List<Double> prices = productPrices.stream().map(p -> Double.parseDouble(p.getText().replace("$", "")))
				.toList();
		List<Double> sortedPrices = new ArrayList<>(prices);
		sortedPrices.sort(Collections.reverseOrder());
		Assert.assertEquals(prices, sortedPrices, "Products are not sorted by Price (high to low)");
	}

	@Then("the product sort dropdown should have default option {string}")
	public void verify_default_dropdown_option(String defaultOption) {
		Select dropdown = new Select(driver.findElement(By.className("product_sort_container")));
		Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), defaultOption,
				"Default dropdown option mismatch");
	}

}
