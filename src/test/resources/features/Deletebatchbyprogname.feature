
Feature: To test the program using DELETE


   Scenario: To test the program using DELETE
   Given User sends base URL to delete "LMS practic test".
   When User sends request with valid program name.
   Then User status code success.
   And content type header should be application/json
   
   Scenario: User able to delete a Program by name with invalid Method.
   Given User able to delete a Program Name with invalid method with valid "Jan23-APIAspirants-SDET-12".
   When User sends a invalid request to delete a Program Name.
   Then Verify response with status code '405 Method Not Allowed'
    
    Scenario: User ableto delete a Program Name with invalid endpoint. 
   Given User sends the request to to delete a Program Name with invalid end point and with valid "Jan23-APIAspirants-SDET-12"..
   When User sends a request with invalid end point.
   Then User checks response if status code is bad request
  
   Scenario: User able to delete a Program Namewith invalid base URL.
   Given User sends invalid base url to delete a Program Name.
   When User sends invalid base url with valid end point
   Then status code should be Service error
    
    Scenario: Validate if user is able to delete with invalid Program Name
   Given User sends the request with invalid "PostgreSQL991".
   When User sends valid request with invalid Program Name and its endpoint
   Then Verify response with bad request
  
   
