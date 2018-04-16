Feature: Collage Generation
Background:
	Given I am on the build collage page testing collage generation
Scenario: Build normal collage
  When I fill in the topic with William Halfond USC - generate
  And I fill in the shape with goat - generate
  And I press Build Collage - generate
  Then I should see a collage with William Halfond USC
