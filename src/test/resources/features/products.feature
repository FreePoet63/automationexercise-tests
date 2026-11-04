Feature: Product search

  @smoke
  Scenario Outline: Search for a product by name
    Given I open the home page
    When I open the Products page
    And I search for "<term>"
    Then I see the search results title
    And the results contain the product "<product>"

    Examples:
      | term       | product    |
      | Blue Top   | Blue Top   |
      | Winter Top | Winter Top |
      | Men Tshirt | Men Tshirt |

  Scenario: Search for a product that does not exist
    Given I open the home page
    When I open the Products page
    And I search for "zzzzzz"
    Then I see the search results title
    And no products are found