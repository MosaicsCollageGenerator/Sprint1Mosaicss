Given(/^I am on the display page$/) do
  visit "http://localhost:8080/Mosaicss/LoginServlet"
end

Then(/^I should see the text for the label is Build Collage$/) do
	#
	expect(find('input.Build').value).to eq 'Build Collage'
end

Then(/^I should see the text for the label is Save to Gallery$/) do
	#
	expect(find('input.save-button').value).to eq 'Save to Gallery'
end

Then(/^I should see the text for the label is Delete from Gallery$/) do
	#
	expect(find('input.delete-button').value).to eq 'Delete from Gallery'
end
