Feature: Register
Background:
	Given I am on the login page testing register
Scenario: Register
  When I fill in the username with halfond - register
  And I fill in the password with password - register
  And I press Register
  Then I should see halfond on the page.
