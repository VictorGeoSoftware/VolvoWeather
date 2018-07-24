Feature: Weather forecast
  Perform click on weather forecast button and see the weather forecast for this week

  @forecast-feature
  Scenario: Press weather forecast button and load weather forecast view
    Given I have a MainActivity
    When I click on weather forecast button
    Then I should see weather forecast activity
