Feature: Book a room

Background: open the application
  Given I am on the home page
@book
Scenario: Option to book a room is available on the page
Given I am on the home page
When I browse through the page
Then I have the option to book a room
  @book
Scenario: booking form is properly displayed
  Given button to open booking form is clickable
  When I open booking form
  Then booking form appear with required fields
  And book button is clickable
  @book
Scenario Outline: book the room for 2 nights with invalid details
  Given I open booking form
  When I fill in form with details "<firstName>", "<lastName>", "<email>", "<phone>"
  And select 2 days in future
  And book the room
  Then error message "<error>" appears
  Examples:
    |firstName|lastName|email|phone|error|
    #invalid first name
    |web123|potnur|sravs@gmail.com|32523654547|firstNameError|
    |we|potnur|sravs@gmail.com|32523654547|firstNameLength|
    ||potnur|sravs@gmail.com|32523654547|emptyFirstName|
    #invalid last name
    |sravs|web123|sravs@gmail.com|32523654547|lastNameError  |
    |sravs|we|sravs@gmail.com|32523654547|lastNameLength  |
    |sravs||sravs@gmail.com|32523654547|lastNameEmpty  |
    #invalid phone
    |sravs|potnur|sravs@gmail.com|3252365457|phoneError      |
    |sravs|potnur|sravs@gmail.com||phoneEmpty      |
    #invalid email
    |sravs|potnu|sravs@mail|32523654547|emailError          |
    |sravs|potnu||32523654547|emailEmpty         |

  @book
Scenario:book the room for past date
  Given I open booking form
  When I fill in form with details "sravs123", "potnur", "sravs.example@gmail.com", "32574185241"
  And select 2 days in past
  And book the room
  Then error message "invalid" appears
  @book
Scenario: book the room for 2 nights with valid information
  Given I open booking form
  When I fill in form with details "sravs123", "potnur", "sravs.example@gmail.com", "32574185241"
  And select 2 days in future
  And book the room
  Then booking is successful with success message
  And booking dates for 2 days appear in the success message