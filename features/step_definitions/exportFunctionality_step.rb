#display.html
Given(/^I am on collage export page$/) do
  visit "http:localhost:8080/Mosaicss/display.jsp"
end

When(/^I am on the collage display page$/) do
	
end


Then(/^the button should have label text “Export Collage$/) do
  expect(find_by_id('export_button')['innerHTML']=="Export")
end
