Given(/^I am on the build collage page testing loading image$/) do
  visit "http:localhost:8080/Mosaicss/"
end

When(/^I fill in the topic with cat - loading$/) do
  fill_in('search_text', :with => 'cat')
end

And(/^I fill in the shape with meow - loading$/) do
  fill_in('shape_text', :with=> 'meow')
end

And(/^I press Build Collage - loading$/) do
  find("#loading-image", :visible => false).value
  click_button("build-button")
end

Then(/^I should see a loading image$/) do
  
end
