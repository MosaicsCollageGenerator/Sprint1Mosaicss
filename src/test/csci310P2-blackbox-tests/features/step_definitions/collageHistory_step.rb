Given(/^I am on collage display page testing history$/) do
	visit "http:localhost:8080/Mosaicss/login.jsp"
	fill_in('username', :with => 'halfond')
	fill_in('password', :with=> 'password')
	click_button("Login")
	click_button("See Collage History")
end

Then(/^I should see a gallery selector$/) do
  page.should have_content('Gallery:')
end

When(/^I click on Save to Gallery$/) do
  click_button("Save to Gallery")
end

Then(/^I should see the id of the image in gallery$/) do
  page.should have_css('#cat')
end

When(/^I click on cat image in Gallery$/) do
  click_button("Save to Gallery")
end

Then(/^I should see cat collage be displayed$/) do
  page.should have_css('#cat')
end
