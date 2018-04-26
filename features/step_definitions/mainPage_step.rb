#index.html
Given(/^I am on collage display page testing mainPage$/) do
  visit "http:localhost:8080/Mosaicss/display.jsp"
  sleep 2
end

When("I am on collage display page") do
  
end

Then(/^I should see a topic input box$/) do
  page.find('#search_text', visible: :all)
end
Then(/^I should see a collage shape input box$/) do
  page.find('#shape_text')
end
Then(/^I should see a height dropdown box$/) do
  page.find('#height_value')
end
Then(/^I should see a width dropdown box$/) do
  page.find('#width_value')
end
Then(/^I should see a filter dropdown box$/) do
  page.find('#filter_value')
end
Then(/^I should see a border checkbox$/)do
  page.find('#border')
end
Then(/^I should see a rotation checkbox$/)do
  page.find('#rotation')
end
