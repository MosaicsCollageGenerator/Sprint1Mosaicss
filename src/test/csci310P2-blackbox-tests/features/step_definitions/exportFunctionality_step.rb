#display.html
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

When(/^I click the export collage button$/) do
	find_by_id("export_button").click
end

Then(/^the file downloaded should be a PNG$/) do
	download_extension = File.extname(File.basename(download))
	download_extension.should == ".png"
end

Then(/^the button should have label text “Export Collage”$/) do
  expect(find_by_id('export_button')['innerHTML']=="Export")
end
