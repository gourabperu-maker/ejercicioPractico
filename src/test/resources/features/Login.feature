Feature: Form Authentication on The Internet
  @login001
  Scenario Outline: Validate login with various credentials
    Given the user is on the login page
    When the user enters username "<username>" and password "<password>"
    And clicks the login button
    Then the user should see the message containing "<expectedMessage>"
    And the login result should be "<success>"

    Examples:
      | username   | password              | expectedMessage                     | success |
      | tomsmith   | SuperSecretPassword!  | You logged into a secure area!      | true    |
      | baduser    | SuperSecretPassword!  | Your username is invalid!           | false   |
      | tomsmith   | wrongpass             | Your password is invalid!           | false   |
      | baduser    | badpass               | Your username is invalid!           | false   |