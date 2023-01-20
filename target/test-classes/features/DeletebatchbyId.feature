Feature: To test the batch using DELETE

  Scenario: User able to delete the Batch by ID with valid request.
    Given User sends the base url to delete <3518>.
    When User sends request to delete with existing batch ID.
    Then status code should be '200 ok'.
    And Verify content type header should be application/json.
    And Verify if status line is 'HTTP/1.1 200'.
    
    Scenario: User able to delete a Batch by ID with invalid endpoint.
    Given User sends a invalid request to delete a  <3530>.
    When User sends a request body with invalid endpoint.
    Then Verify status code is ' 404 Not Found'.
    And  Verify content type header 'application/json'.
    
    Scenario: User ableto delete a Batch by ID with invalid batchID.
    Given User send base URL to delete batch with invalid <3530>.
    When User send valid request with invalid Batch ID and its endpoint.
    Then the  status code should be bad request
  
    
    Scenario: User able to delete a Batch by ID with invalid base URL.
    Given User sends invalid base url to delete a Batch ID.
    When User sends invalid base url with valid endpoint.
    Then Verify  status code '503 Service unavailable'.
    
    Scenario: User able to delete a Batch by ID with invalid request.
    Given User sends a base URL with batch ID to delete <3530>.
    When User sends a request with invalid method to delete batch ID.
    Then Verify if the response  status code is method not allowed
    