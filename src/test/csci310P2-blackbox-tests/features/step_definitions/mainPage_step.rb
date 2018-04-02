#index.html
Given(/^I am on collage display page$/) do
  visit "http:localhost:8080/Mosaicss/login.jsp"
  fill_in('username', :with => 'halfond')
  fill_in('user_password', :with=> 'password')
  click_button("login")
  sleep 2
end

Then(/^I should see a topic see a topic input box$/) do
  page.should have_content('search_text')
And(/^I should see a collage shape input box$/)
  page.should have_content('shape_text')
And(/^I should see a height dropdown box$/)
  page.should have_content('height_value')
And(/^I should see a width dropdown box$/)
  page.should have_content('width_value')
And(/^I should see filter radio buttons$/)
  page.should have_content('filter')
And(/^I should see a border checkbox$/)
  page.should have_content('border')
end
