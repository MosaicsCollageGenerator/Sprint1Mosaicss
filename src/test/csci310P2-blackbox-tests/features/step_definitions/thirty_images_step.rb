Given(/^I am on the build collage page testing thirty images$/) do
  visit "http:localhost:8080/Mosaicss/"
end

When(/^I fill in the topic with long string - thirty images$/) do
  fill_in('search_text', :with => 'fdslafdlk;jvlkcjzvljlvjflkjfkl;dasjflkasj')
end

And(/^I fill in the shape with test - thirty images$/) do
  fill_in('shape_text', :with=> 'test')
end

And(/^I press Build Collage - thirty images$/) do
  click_button("build-button")
  sleep 2
end

Then(/^I should see insufficient number of images$/) do
	click_button("Save to Gallery")
  	expect(page).to have_content("Collage for topic fdslafdlk;jvlkcjzvljlvjflkjfkl;dasjflkasj")
end
