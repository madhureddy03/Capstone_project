package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.HomePage;
import utils.DriverFactory;

import java.util.List;

public class FooterSteps {

	private HomePage homePage = new HomePage(DriverFactory.getDriver());

	// Footer Logos
	@When("user views all footer logos")
	public void user_views_all_footer_logos() {
		// No extra action required
	}

	@Then("all footer logos should be displayed")
	public void all_footer_logos_should_be_displayed() {
		List<WebElement> logos = homePage.getFooterLogos();
		Assert.assertTrue(logos.size() > 0, "No footer logos found");

		for (WebElement logo : logos) {
			Assert.assertTrue(logo.isDisplayed(), "Footer logo not displayed: " + logo.getAttribute("alt"));
		}
	}

	// Social Media Icons
	@When("user views all social media icons")
	public void user_views_all_social_media_icons() {
		// No extra action required
	}

	@Then("all social media icons should be displayed")
	public void all_social_media_icons_should_be_displayed() {
		List<WebElement> icons = homePage.getSocialIcons();
		Assert.assertTrue(icons.size() > 0, "No social icons found");

		for (WebElement icon : icons) {
			Assert.assertTrue(icon.isDisplayed(), "Social icon not displayed: " + icon.getAttribute("href"));
		}
	}

	// Footer Copyright
	@When("user views footer copyright text")
	public void user_views_footer_copyright_text() {
		// Already accessible via homePage getter
	}

	@Then("footer should display text {string}")
	public void footer_should_display_text(String expectedText) {
		String actualText = homePage.getFooterCopyText().getText();
		Assert.assertEquals(actualText, expectedText, "Footer copyright text mismatch");
	}
}
