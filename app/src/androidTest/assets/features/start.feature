#Feature: Login
#  Perform login on email and password are inputted
#
#  @login-feature
#  Scenario Outline: Input email and password in wrong format
#    Given I have a LoginActivity
#    When I input email <email>
#    And I input password "<password>"
#    And I press submit button
#    Then I should see error on the <view>
#
#    Examples: (linked to Outline!)
#      | email | password  | view  |
#      | test  | lemoncake | email |
#      | test@test.com || password |
#
#  @login-feature
#  Scenario Outline: Input email and password in correct format
#    Given I have a LoginActivity
#    When I input email <email>
#    And I input password "<password>"
#    And I press submit button
#    Then I should <see> auth error
#
#    Examples:
#      | email              | password   | see   |
#      | espresso@spoon.com | bananacake | true  |
#      | espresso@spoon.com | lemoncake  | false |
#      | latte@spoon.com    | lemoncake  | true  |

  Feature: app start
    Load activity from desktop and wait for information

    @start-feature
    Scenario Outline: Start app and wait for requested weather forecast
    Given I have my MainActivity
    When I see my MainActivity
    Then I should <see> current weather forecast

    Examples:
      | see |
      | true |