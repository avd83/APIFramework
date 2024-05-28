Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being addedsuccessfuly by AddPlace API 
	Given Add Place payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then The API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "GetPlaceAPI"
	
Examples:
 |name      | language         |   address                   |
 |Matoshree |Marathi           |lower parel,mumbai           |
 #|servive   |Hindi             |tahen,mumbai                |
	 
@DeletePlace @Regression
Scenario Outline: Verify if delete place functionality is working
	Given DeletePlace payload 
	When user calls "DeletePlaceAPI" with "POST" http request
	Then The API call got success with status code 200
	And "status" in response body is "OK"
	 
	
	