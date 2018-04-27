Given(/^I am on the build collage page testing filters$/) do
  visit "http:localhost:8080/Mosaicss/"
end

When(/^I fill in the topic with long string - filters$/) do
  fill_in('search_text', :with => 'pizza')
end

And(/^I fill in the shape with test - filters$/) do
  fill_in('shape_text', :with=> 'pizza')
end

And(/^I click sepia filter - filters$/) do
  find(:css, "#filter_value[value='1']").set(true)
end
And(/^I click black&white filter - filters$/) do
  find(:css, "#filter_value[value='2']").set(true)
end
And(/^I click greyscale filter - filters$/) do
  find(:css, "#filter_value[value='3']").set(true)
end

And(/^I press Build Collage - filters$/) do
  click_button("build-button")
  sleep 2
end

Then(/^I should see a collage with a sepia filter$/) do
	click_button("Save to Gallery")
  	expect(page).to have_content("Collage for topic pizza")
end

Then(/^I should see a collage with a black&white filter$/) do
	click_button("Save to Gallery")
  	expect(page).to have_content("Collage for topic pizza")
end

Then(/^I should see a collage with a greyscale filter$/) do
	click_button("Save to Gallery")
  	expect(page).to have_content("Collage for topic pizza")
end
