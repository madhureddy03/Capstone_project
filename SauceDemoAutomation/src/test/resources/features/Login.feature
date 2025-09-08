Feature: Login functionality on SauceDemo

  Scenario: Successful login with valid credentials
    Given user is on the login page
    When user enters valid username "standard_user" and password "secret_sauce"
    And user clicks login
    Then user should see the products page

  Scenario: Login failure with invalid credentials
    Given user is on the login page
    When user enters invalid username "wrong_user" and password "wrong_pass"
    And user clicks login
    Then user should see error message "Epic sadface: Username and password do not match any user in this service"

  Scenario: Login failure with blank input
    Given user is on the login page
    When user enters blank username " " and password " "
    And user clicks login
    Then user should see error message "Epic sadface: Username and password do not match any user in this service"


  Scenario: Login failure with blank username only
    Given user is on the login page
    When user enters blank username "" and password "secret_sauce"
    And user clicks login
    Then user should see error message "Epic sadface: Username is required"

  Scenario: Login failure with blank password only
    Given user is on the login page
    When user enters valid username "standard_user" and blank password ""
    And user clicks login
    Then user should see error message "Epic sadface: Password is required"