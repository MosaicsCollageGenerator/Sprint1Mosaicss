Given(/^I am on collage initial page$/) do
	visit "http:localhost:8080/Mosaicss/login.jsp"
	fill_in('username', :with => 'test')
	fill_in('password', :with=> 'test')
	click_button("Login")
end

Then(/^the value of the button is "Build Collage"$/) do
	expect(find_by_id('build-button')['innerHTML']=="Build Collage")
end

Then(/^the value of the button is "Delete from Gallery"$/) do
	 expect(find_by_id('delete-button')['innerHTML']=="Delete from Gallery")
end

Then(/^the value of the button is "Export Collage"$/) do
	 expect(find_by_id('export-button')['innerHTML']=="Export Collage")
end
