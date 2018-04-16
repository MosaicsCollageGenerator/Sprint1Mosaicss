Given(/^I am on the login page testing register$/) do
  visit "http:localhost:8080/Mosaicss/login.jsp"
  click_button("Register")
end

When(/^I fill in the username with halfond - register$/) do
  fill_in('username', :with => 'halfond')
end

And(/^I fill in the password with password - register$/) do
  fill_in('password', :with=> 'password')
end

And(/^I press Register$/) do
  click_button("Register")
  sleep 2
end

Then(/^I should see halfond on the page.$/) do
  expect(page).to have_content("halfond")
end

When(/^I fill in the username with halfond - register-twice$/) do
  fill_in('username', :with => 'halfond')
end

And(/^I fill in the password with password - register-twice$/) do
  fill_in('password', :with=> 'password')
end

And(/^I press Register again$/) do
  click_button("Register")
  sleep 2
end

Then(/^I should see Username taken on the page.$/) do
  expect(page).to have_content("Username taken")
end
