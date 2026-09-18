# language: en
Feature: ReqRes API validation

  Scenario: TC4 - List users returns correct response and content
    When the user requests the list of users on page 2
    Then the API response status should be 200
    And the response should contain a list of users
    And each user should have id, email, first_name and last_name fields

  Scenario: TC5 - Create a new user returns correct response and content
    When the user creates a new user with name "Ricardo Parra" and job "QA Engineer"
    Then the API response status should be 201
    And the response should contain the created user name "Ricardo Parra"
    And the response should contain the created user job "QA Engineer"
    And the response should contain an id and a createdAt timestamp

  Scenario: TC6 - Update a user returns correct response and content
    When the user updates user 2 with name "Ricardo Updated" and job "Senior QA"
    Then the API response status should be 200
    And the response should contain the updated name "Ricardo Updated"
    And the response should contain the updated job "Senior QA"
    And the response should contain an updatedAt timestamp
