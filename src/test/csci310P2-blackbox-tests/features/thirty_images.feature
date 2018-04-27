Feature: Thirty Images
Background:
	Given I am on the build collage page testing thirty images
Scenario: Build long string
  When I fill in the topic with long string - thirty images
  And I fill in the shape with test - thirty images
  And I press Build Collage - thirty images
  Then I should see insufficient number of images
