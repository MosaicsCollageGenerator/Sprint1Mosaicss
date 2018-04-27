Given(/^I am on collage main page$/) do
	visit "http:localhost:8080/Mosaicss/login.jsp"
	fill_in('username', :with => 'test')
	fill_in('password', :with=> 'test')
	click_button("Login")
end


Then(/^the page should have a div "collage" section/) do
  expect(find_by_id('collage-div')['innerHTML']=="Collage")
end

Then(/^the main collage should be swapped/) do
 # click(find_by_id('galleryTable'))
 #find('h3', text: 'Name2').find('+table')
  # node = find_by_id('galleryTable').find('+img')
  node = find_by_id('galleryTable')['innerHTML']
  className = node.class
  expect(find_by_id('collage-div')['innerHTML'] == className)
end


