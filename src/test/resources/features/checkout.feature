Feature: Checkout

  @smoke
  Scenario: Place an order as a registered user
    Given a registered user exists
    And I open the home page
    When I open the Signup and Login page
    And I log in with the registered user credentials
    And I open the Products page
    And I add the product "Blue Top" to the cart
    And I open the cart from the confirmation dialog
    And I proceed to checkout
    Then I see my delivery address on the checkout page
    And the order review contains the product "Blue Top"
    When I place the order with the comment "Please deliver in the morning"
    And I pay with a test card
    Then I see the message "Order Placed!"

  Scenario: Guest is asked to register or log in at checkout
    Given I open the home page
    And I open the Products page
    When I add the product "Blue Top" to the cart
    And I open the cart from the confirmation dialog
    And I proceed to checkout as a guest
    Then I am asked to register or log in