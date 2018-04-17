require 'capybara'
require 'capybara/cucumber'
require 'rspec'
# require 'phashion'

Capybara.register_driver :chrome do |app|
  Capybara::Selenium::Driver.new(app, :browser => :chrome, :driver_path=>"/usr/local/Cellar/chromedriver/2.36/bin/chromedriver")
end

Capybara.default_driver = :chrome
