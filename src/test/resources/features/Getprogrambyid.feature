
@getprogbyid
Feature: To test the program by ID using Get Method

  Scenario: User able to Get a Program by ID with valid request
    Given User sends valid base url to get Program by Id
    When User sends request with its endpoint to get Program ID
    Then User should receive response with status code
    And Validate response body  to get Program by ID
    And Verify status line should be success line


 @getprogbyid
  Scenario: User able to Get a Program by ID with invalid request
    Given User sends the url with invalid request
    When User sends the request with its invalid request
    Then Validate response with status code Method not allowed
    And Validate response body with error message not allowed
  
    
@getprogbyid
  Scenario: User able to Get a Program by ID with invalid endpoint
  Given User sends the request to get Program ID
  When User sends a request body with invalid endpoint
  Then Verify response with status code Bad Request
  And Validate the response body with error code message 'ENTITY_DOES_NOT_EXIST'
