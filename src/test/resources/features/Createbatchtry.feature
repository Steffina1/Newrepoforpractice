#@tag
#Feature: Validate POST request to Create the Batch.
#Background: Valid the the endpoint
#Given User sends the baseurls
  #@tag1
  #Scenario Outline: User able to create the the batch with the existing program
    #When User sends the request  body to create Batch from "<Rownumber>" "<batchdetail>"
    #Then Validate the batch response status as <201>.
    #And Validate the response body for the batchName,batchDescription,batchStatus
    #And Validate the batch response headers should be the application json format
 #
 #Examples: 
     #|Rownumber | batchdetail | 
     #|0      |batchname        |
     #|1      |batchDescription|
     #|2      |batchStatus |
     #|3      |batchNoOfClasses |
     #|4      | programId       |
     