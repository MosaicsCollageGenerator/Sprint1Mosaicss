Given(/^I am on collage display page testing history$/) do
	visit "http:localhost:8080/Mosaicss/"
	fill_in('search_text', :with => 'cat')
	fill_in('shape_text', :with=> 'meow')
	click_button("build-button")
end

Then(/^I should see a gallery selector$/) do
  page.should have_content('Gallery:')
end

When(/^I click on Save to Gallery$/) do
  click_button("Save to Gallery")
end

Then(/^I should see the id of the image in gallery$/) do
  page.should have_content('#cat')
end

When(/^I click on cat image in Gallery$/) do
  click_button("Save to Gallery")
end

Then(/^I should see cat collage be displayed$/) do
  page.should have_content('#cat')
end
