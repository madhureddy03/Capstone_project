Feature: Cart functionality on SauceDemo

  Background:
    Given user is logged in with username "standard_user" and password "secret_sauce"

  Scenario: Add a single item to the cart
    When user adds "Sauce Labs Backpack" to the cart
    Then cart badge should show "1"
    And user should see "Sauce Labs Backpack" in the cart

  Scenario: Add multiple items to the cart
    When user adds "Sauce Labs Backpack" to the cart
    And user adds "Sauce Labs Bike Light" to the cart
    Then cart badge should show "2"
    And user should see "Sauce Labs Backpack" and "Sauce Labs Bike Light" in the cart

  Scenario: Remove an item from the cart
    When user adds "Sauce Labs Backpack" to the cart
    And user removes "Sauce Labs Backpack" from the cart
    Then cart badge should not be visible
    And user should not see "Sauce Labs Backpack" in the cart

  Scenario: View cart from cart icon
    When user adds "Sauce Labs Fleece Jacket" to the cart
    And user clicks on the cart icon
    Then user should be navigated to the cart page
    And user should see "Sauce Labs Fleece Jacket" in the cart

  Scenario: Proceed to checkout with valid information
    When user adds "Sauce Labs Onesie" to the cart
    And user clicks on the cart icon
    And user clicks on Checkout
    Then checkout page should be displayed
    And user should see Checkout info form
    When user enters first name "Madhu"
    And user enters last name "Reddy"
    And user enters postal code "520008"
    And user clicks on Continue
    Then user should be navigated to the Checkout Overview page

  Scenario: Proceed to checkout with missing first name
    When user adds "Sauce Labs Backpack" to the cart
    And user clicks on the cart icon
    And user clicks on Checkout
    Then checkout page should be displayed
    And user should see Checkout info form
    When user enters first name ""
    And user enters last name "reddy"
    And user enters postal code "520008"
    And user clicks on Continue
    Then user should see cart error message "First Name is required"

  Scenario: Proceed to checkout with missing last name
    When user adds "Sauce Labs Bike Light" to the cart
    And user clicks on the cart icon
    And user clicks on Checkout
    Then checkout page should be displayed
    And user should see Checkout info form
    When user enters first name "Madhu"
    And user enters last name ""
    And user enters postal code "520008"
    And user clicks on Continue
    Then user should see cart error message "Last Name is required"

  Scenario: Proceed to checkout with missing postal code
    When user adds "Sauce Labs Fleece Jacket" to the cart
    And user clicks on the cart icon
    And user clicks on Checkout
    Then checkout page should be displayed
    And user should see Checkout info form
    When user enters first name "Madhu"
    And user enters last name "Reddy"
    And user enters postal code ""
    And user clicks on Continue
    Then user should see cart error message "Postal Code is required"

  
  Scenario: Complete checkout and finish order
    When user adds "Sauce Labs Onesie" to the cart
    And user clicks on the cart icon
    And user clicks on Checkout
    And user enters first name "Madhu"
    And user enters last name "Reddy"
    And user enters postal code "520008"
    And user clicks on Continue
    And user clicks on Finish
    Then user should see order confirmation message "Thank you for your order!"
    And cart badge should not be visible

@Ignore
  Scenario: Clear all items from the cart
    When user adds "Sauce Labs Backpack" to the cart
    And user adds "Sauce Labs Bike Light" to the cart
    And user clicks on the cart icon
    And user removes "Sauce Labs Backpack" from the cart
    And user removes "Sauce Labs Bike Light" from the cart
    Then cart badge should not be visible
   
