Feature: Login and logout

  @smoke
  Scenario: Login with valid credentials and logout
    Given a registered user exists
    And I open the home page
    When I open the Signup and Login page
    And I log in with the registered user credentials
    Then I am logged in with my user name
    When I log out
    Then I am on the login page

  Scenario Outline: Login with a wrong password
    Given a registered user exists
    And I open the home page
    When I open the Signup and Login page
    And I log in with the registered email and password "<password>"
    Then I see the login error "Your email or password is incorrect!"

    Examples:
      | password  |
      | wrong123  |
      | Passw0rd  |
      | PASSW0RD! |

  Scenario: Login with an unregistered email
    Given I open the home page
    When I open the Signup and Login page
    And I log in with email "nobody_registered@example.com" and password "Passw0rd!"
    Then I see the login error "Your email or password is incorrect!"