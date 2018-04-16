Feature: Register
Background:
	Given I am on the login page testing register
Scenario: Accessing the system
  When I fill in the username with halfond
  And I fill in the password with password
  And I press Register
  Then I should see halfond on the page.
