 Feature: Tables sorting and validations
    @tables001
  Scenario: Order table2 descending by Due and validate Due of row 2
    Given the user is on the tables page for table "table2"
    When the user sorts the column "Due" in descending order for table "table2"
    Then the Due amount in row 2 of table "table2" should be "$51.00"

