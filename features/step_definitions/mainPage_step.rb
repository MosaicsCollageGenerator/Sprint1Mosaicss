#index.html
Given(/^I am on collage display page testing mainPage$/) do
  visit "http:localhost:8080/Mosaicss/index.html"
  sleep 2
end

When("I am on collage builder page") do
  
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
Then(/^I should see filter radio buttons$/) do
  page.assert_selector('[name=filter]', count: 4)
end
Then(/^I should see a border checkbox$/)do
  page.find('#border')
end
