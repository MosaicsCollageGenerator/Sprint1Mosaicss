#2
Feature: Export current collage
Background:
	Given I am on collage export page

#2b
Scenario: Export Collage button type
	Then the button should have label text “Export Collage

Scenario: File download type PNG
	Then the file downloaded should be a PNG when set to PNG

Scenario: File download type PDF
	When I change export as to PDF
	Then the file downloaded should be a PDF when set to PDF
