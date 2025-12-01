Feature: Dropdown functionality on The Internet

  @dropdown001
  Scenario: User can select and change dropdown options successfully
    Given the user is on the dropdown page
    When the user selects "Option 1" from the dropdown
    Then the selected option should be "Option 1"
    When the user selects "Option 2" from the dropdown
    Then the selected option should be "Option 2"