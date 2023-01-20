@tag
Feature: Validate Post Request to Create Program

  @tag1
  Scenario: User able to create Program
    Given User sends request with the endpoint to create program
    When User sends a request body with valid endpoint to create Program.
    Then Validate response status <201>.
    And Validate response body for programName,programDescription,programStatus
    And Validate response headers should be application json format
    And Verify response body schema './src/test/resources/configuration/JsonSchema_Program/postRequest/Post_ValidRequest.json' in json format.
    And Verify the values are present in DB 
    