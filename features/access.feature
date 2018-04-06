Feature: Availability
Background:
	Given I am on the login page
Scenario: Accessing the system
  When I fill in the username with halfond
  And I fill in the password with password
  And I press Login
  Then I should see the collage options page.
