Given(/^I am on the login page testing register$/) do
  visit "http:localhost:8080/Mosaicss/login.jsp"
end

When(/^I fill in the username with halfond$/) do
  fill_in('username', :with => 'halfond')
end

And(/^I fill in the password with password$/) do
  fill_in('password', :with=> 'password')
end

And(/^I press Register$/) do
  click_button("Register")
  sleep 2
end

Then(/^Then I should see halfond on the page.$/) do
  expect(page).to have_content("halfond")
end
