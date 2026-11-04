Feature: User registration

  @smoke
  Scenario: Register a new user and delete the account
    Given I open the home page
    When I open the Signup and Login page
    And I sign up with a new unique user
    And I fill in the account information and create the account
    Then I see the message "Account Created!"
    When I continue to the home page
    Then I am logged in with my user name
    When I delete my account
    Then I see the message "Account Deleted!"

  Scenario: Sign up with an already registered email
    Given a registered user exists
    And I open the home page
    When I open the Signup and Login page
    And I try to sign up with the registered user email
    Then I see the signup error "Email Address already exist!"