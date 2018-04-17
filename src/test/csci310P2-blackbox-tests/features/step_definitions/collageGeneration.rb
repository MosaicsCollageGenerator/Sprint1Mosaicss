Given(/^I am on the build collage page testing collage generation$/) do
  visit "http:localhost:8080/Mosaicss/"
end

When(/^I fill in the topic with cat - generate$/) do
  fill_in('search_text', :with => 'cat')
end

And(/^I fill in the shape with meow - generate$/) do
  fill_in('shape_text', :with=> 'meow')
end

And(/^I press Build Collage - generate$/) do
  click_button("build-button")
  sleep 2
end

Then(/^I should see a collage with cat$/) do
  expect(page).to have_content("Collage for topic cat")
end
