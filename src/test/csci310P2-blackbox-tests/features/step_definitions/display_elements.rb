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

Then(/^the value of the label is topic$/) do
	topic = find('label', text: 'Topic')
	expect(topic)
end

Then(/^the value of the label is shape$/) do
	shape = find('label', text: 'Shape')
	expect(shape)
end

Then(/^the value of the label is height$/) do
	height = find('label', text: 'Height')
	expect(height)
end

Then(/^the value of the label is width$/) do
	width = find('label', text: 'Width')
	expect(width)
end

Then(/^the value of the label is filter$/) do
	filter = find('label', text: 'Filter')
	expect(filter)
end

Then(/^the value of the label is border$/) do
	border = find('label', text: 'Border')
	expect(border)
end

Then(/^the value of the label is rotation$/) do
	rotation = find('label', text: 'Rotation')
	expect(rotation)
end
