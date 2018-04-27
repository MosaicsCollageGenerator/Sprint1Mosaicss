Feature: Delete Collage

Background: 

	Given I am on collage display page

Scenario: Button is labeled with text "Delete from Gallery"
	
	Then the value of the button is Delete from Gallery	

Scenario: Delete a collage from Gallery
	Then the collage should be deleted from the gallery