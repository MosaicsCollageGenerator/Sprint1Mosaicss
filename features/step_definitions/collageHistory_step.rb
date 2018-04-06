Given(/^I am on collage display page testing history$/) do
	visit "http:localhost:8080/Mosaicss/display.jsp"
end

Then(/^I should see a gallery selector$/) do
  page.should have_content('Gallery:')
end
