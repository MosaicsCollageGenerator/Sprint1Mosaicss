#2
Feature: Export current collage
Background:
	Given I am on collage display page

#2a
Scenario: File type
	When I click the export collage button
	Then the file downloaded should be a PNG

#2b
Scenario: Export Collage button type
	And the button should have label text “Export Collage”
