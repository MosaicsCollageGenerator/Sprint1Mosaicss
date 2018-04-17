#display.html
Given(/^I am on collage export page$/) do
  visit "http:localhost:8080/Mosaicss/display.jsp"
end

When(/^I am on the collage display page$/) do

end


Then(/^the button should have label text “Export Collage$/) do
  expect(find_by_id('export-button')['innerHTML']=="Export")
end

Then(/^the file downloaded should be a PNG when set to PNG$/) do
  download_extension = File.extname(File.basename("collage.png"))
  download_extension.should == ".png"
end

When(/^I change export as to PDF$/) do
  find('#exportvalue').find(:xpath, 'option[2]').select_option
end

Then(/^the file downloaded should be a PDF when set to PDF$/) do
  download_extension = File.extname(File.basename("collage.pdf"))
  download_extension.should == ".pdf"
end
