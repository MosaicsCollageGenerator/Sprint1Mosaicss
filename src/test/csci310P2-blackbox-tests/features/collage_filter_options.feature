Feature: Collage Filter Options
Background:
	Given I am on the build collage page testing filters
Scenario: Build Collage with Sepia Filter
  When I fill in the topic with long string - filters
  And I fill in the shape with test - filters
  And I click sepia filter - filters
  And I press Build Collage - filters
  Then I should see a collage with a sepia filter

Scenario: Build Collage with black&white Filter
  When I fill in the topic with long string - filters
  And I fill in the shape with test - filters
  And I click black&white filter - filters
  And I press Build Collage - filters
  Then I should see a collage with a black&white filter

Scenario: Build Collage with greyscale Filter
  When I fill in the topic with long string - filters
  And I fill in the shape with test - filters
  And I click greyscale filter - filters
  And I press Build Collage - filters
  Then I should see a collage with a greyscale filter
