package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "stepdefinitions" }, plugin = { "pretty",
		"html:target/cucumber-reports/cucumber.html", "json:target/cucumber-reports/cucumber.json",
		"junit:target/cucumber-reports/cucumber.xml",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, monochrome = true, tags = "not @Ignore")
public class TestRunner extends AbstractTestNGCucumberTests {
}
