Given(/^I am on the login page$/) do
  visit "http:localhost:8080/Mosaicss/login.jsp"
end

When(/^I fill in the username with halfond$/) do
  fill_in('username', :with => 'halfond')
end

And(/^I fill in the password with password$/) do
  fill_in('password', :with=> 'password')
end

And(/^I press Login$/) do
  click_button("Login")
  sleep 2
end

Then(/^I should see the collage options page.$/) do
  expect(page).to have_content("MOSAIC Options Page")
end
