Feature: To test the program using DELETE

  Scenario: User able to delete a Program by ID1 with valid request.
    Given User sends base url to delete a <13843>.
    When User sends request to delete with existing Program_ID
    Then  status code is the success
    And status line should be HTTP/1.1 200
   
  Scenario: User able to delete a Program by ID with invalid request.
    Given User sends a base URL to delete <8113>.
    When User sends a request with invalid method.
    Then Verify response with status code ' 405 Method Not Allowed
      
   Scenario: User ableto delete a Program by ID with invalid endpoint.
   Given User sends base URL to delete invalid endpoint with valid <8113>.
   When User sends the request with invalid end point to to delete invalid end point.
   Then Verify response with status code Not found.
 
   Scenario: User able to delete a Program by ID with invalid base URL.
   Given User sends invalid base url 
   When User sends invalid base url along with valid endpoint
   Then Verify response with status code Service unavailable.

   Scenario: Validate if user is able to delete with invalid Program ID
   Given User sends the request with invalid <8113>
   When User sends valid request with invalid Program ID and its endpoint
   Then Verify response should be Bad Request