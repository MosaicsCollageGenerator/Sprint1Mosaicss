Given(/^I am on build collage page$/) do
	visit "http:localhost:8080/Mosaicss/login.jsp"
	fill_in('username', :with => 'test')
	fill_in('password', :with=> 'test')
	click_button("Login")
end

Then(/^the requirements should be met/) do
  #expect(find_by_id('collage-div')['innerHTML']=="Collage")
end