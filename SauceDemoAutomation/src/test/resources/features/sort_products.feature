Feature: Product sorting on SauceDemo homepage

  Background:
    Given user is logged in with username "standard_user" and password "secret_sauce"

  Scenario: Verify dropdown options
    When user views the product sort dropdown
    Then dropdown should have options:
      | Name (A to Z)        |
      | Name (Z to A)        |
      | Price (low to high)  |
      | Price (high to low)  |

  Scenario: Verify default dropdown selection
    Then the product sort dropdown should have default option "Name (A to Z)"

  Scenario: Sort products by Name (A to Z)
    When user selects "Name (A to Z)" from sort dropdown
    Then products should be sorted in ascending order by name

  Scenario: Sort products by Name (Z to A)
    When user selects "Name (Z to A)" from sort dropdown
    Then products should be sorted in descending order by name

  Scenario: Sort products by Price (low to high)
    When user selects "Price (low to high)" from sort dropdown
    Then products should be sorted in ascending order by price

  Scenario: Sort products by Price (high to low)
    When user selects "Price (high to low)" from sort dropdown
    Then products should be sorted in descending order by price
