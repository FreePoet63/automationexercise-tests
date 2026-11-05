Feature: Contact Us

  @smoke
  Scenario: Send a message with an attachment
    Given I open the home page
    When I open the Contact Us page
    And I fill in the contact form with the subject "Order question" and the message "Hello, I have a question about my order."
    And I attach the file "files/attachment.txt"
    And I submit the contact form
    Then I see the contact success message "Success! Your details have been submitted successfully."

  Scenario: Return to the home page after sending a message
    Given I open the home page
    When I open the Contact Us page
    And I fill in the contact form with the subject "Feedback" and the message "Great demo site."
    And I submit the contact form
    Then I see the contact success message "Success! Your details have been submitted successfully."
    When I return to the home page from the contact page
    Then I am on the home page