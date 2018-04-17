Feature: View Collage History
Background:
	Given I am on collage display page testing history

Scenario: Gallery
  Then I should see a gallery selector
Scenario: Save history
	When I click on Save to Gallery
	Then I should see the id of the image in gallery
Scenario: Display gallery image
	When I click on cat image in Gallery
	Then I should see cat collage be displayed
