Given(/^I am on the build collage page testing collage generation$/) do
  visit "http:localhost:8080/Mosaicss/"
end

When(/^I fill in the topic with William Halfond USC - generate$/) do
  fill_in('search_text', :with => 'William Halfond USC')
end

And(/^I fill in the shape with goat - generate$/) do
  fill_in('shape_text', :with=> 'GOAT')
end

And(/^I press Build Collage - generate$/) do
  click_button("Build Button")
  sleep 2
end

Then(/^I should see a collage with William Halfond USC$/) do
  expect(page).to have_content("Collage for William Halfond USC")
end
