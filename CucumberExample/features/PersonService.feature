Feature: person service

  Scenario: person service returns correct person
    When I call the person service get
    Then the person name is "Susan"
    And the person address is "USA"

  Scenario: person service updates correct person
    Given the person name "John Doe"
    And the person address "Unknown"
    When I call the person service put
    Then the person name is "John Doe"
    And the person address is "Unknown"

  Scenario: person service does not update person without address
    Given the person name "John Doe"
    And the person address "-none-"
    When I call the person service put
    Then an error happends and message contains "Bad request: Address cannot be -none-"

