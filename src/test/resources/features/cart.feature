Feature: Shopping cart

  Background:
    Given I open the home page
    And I open the Products page

  @smoke
  Scenario: Add a product to the cart
    When I add the product "Blue Top" to the cart
    And I open the cart from the confirmation dialog
    Then the cart contains 1 product
    And the cart contains the product "Blue Top"

  Scenario: Add two different products to the cart
    When I add the product "Blue Top" to the cart
    And I continue shopping
    And I add the product "Men Tshirt" to the cart
    And I open the cart from the confirmation dialog
    Then the cart contains 2 products
    And the cart contains the product "Blue Top"
    And the cart contains the product "Men Tshirt"

  Scenario: Cart total is price multiplied by quantity
    When I add the product "Blue Top" to the cart
    And I continue shopping
    And I add the product "Blue Top" to the cart
    And I open the cart from the confirmation dialog
    Then the quantity of "Blue Top" in the cart is 2
    And the total of "Blue Top" equals price multiplied by quantity

  Scenario: Remove a product from the cart
    When I add the product "Blue Top" to the cart
    And I open the cart from the confirmation dialog
    And I remove the product "Blue Top" from the cart
    Then the cart is empty