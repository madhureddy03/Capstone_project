Feature: Homepage Footer Verification on SauceDemo

  Background:
    Given user is logged in with username "standard_user" and password "secret_sauce"

@Ignore
  Scenario: Verify all footer logos are visible
    When user views all footer logos
    Then all footer logos should be displayed

@Ignore
  Scenario: Verify social media icons are visible
    When user views all social media icons
    Then all social media icons should be displayed

  Scenario: Verify footer copyright text
    When user views footer copyright text
    Then footer should display text "© 2025 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy"
