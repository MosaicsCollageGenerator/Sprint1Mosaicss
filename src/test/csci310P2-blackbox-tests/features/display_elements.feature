Feature: Display Elements on Display Page

Background:

	Given I am on collage initial page

Scenario: Button is labeled with text "Build Collage"

	Then the value of the button is "Build Collage"

Scenario: Button is labeled with text "Delete from Gallery"
	
	Then the value of the button is "Delete from Gallery"

Scenario: Button is labeled with text "Export Collage"
	
	Then the value of the button is "Export Collage"

Scenario: Topic label
	Then the value of the label is topic

Scenario: Shape label
	Then the value of the label is shape

Scenario: Height label
	Then the value of the label is height

Scenario: Width label
	Then the value of the label is width

Scenario: Filter label
	Then the value of the label is filter

Scenario: Border label
	Then the value of the label is border

Scenario: Rotation label
	Then the value of the label is rotation