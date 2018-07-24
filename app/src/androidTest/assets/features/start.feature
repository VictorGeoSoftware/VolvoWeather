Feature: app start
  Load activity from desktop and wait for information

  @start-feature
  Scenario Outline: Start app and wait for requested weather forecast
    Given I have my MainActivity
    When I see my MainActivity
    And I press weather forecast button
    And I see the DetailActivity
    Then I should <see> current weather forecast

    Examples:
      | see |
      | true |
      | false |