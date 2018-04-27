Given(/^I am on collage main page$/) do
	visit "http:localhost:8080/Mosaicss/login.jsp"
	fill_in('username', :with => 'test')
	fill_in('password', :with=> 'test')
	click_button("Login")
end

Then(/^the value of the button is Delete from Gallery$/) do
	 expect(find_by_id('delete-button')['innerHTML']=="Delete from Gallery")
end

Then(/^the collage should be deleted from the gallery$/) do
  # Right now I can only think of when I click on delete from 
  # gallery, the size of collages is decremented

  # and then assert that the collage is not in the database
  # by searching using title or one of its elements
  
end