Given(/^I am on collage display page$/) do
  visit "http:localhost:8080/Mosaicss/login.jsp"
  fill_in('username', :with => 'halfond')
  fill_in('user_password', :with=> 'password')
  click_button("login")
  sleep 2
  fill_in('search_text', :with => 'dogs')
  fill_in('shape_text', :with => 'cat')
  choose('none')
  click_button("build_collage")
end

Then(/^Then I should see a gallery selector$/) do
  page.should have_selector('gallery')
end
