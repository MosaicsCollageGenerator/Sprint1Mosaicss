Feature: Loading Image
Background:
	Given I am on the build collage page testing loading image
Scenario: Building collage
  When I fill in the topic with cat - loading
  And I fill in the shape with meow - loading
  And I press Build Collage - loading
  Then I should see a loading image
