Feature: Collage Generation
Background:
	Given I am on the build collage page testing collage generation
Scenario: Build normal collage
  When I fill in the topic with cat - generate
  And I fill in the shape with meow - generate
  And I press Build Collage - generate
  Then I should see a collage with cat
