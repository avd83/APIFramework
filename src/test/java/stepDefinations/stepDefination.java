package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import io.cucumber.cienvironment.internal.com.eclipsesource.json.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.SetLocation;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utility;
import pojo.Location;

public class stepDefination extends Utility {
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response1;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("Add Place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {	    					
	//Spec builder for response and request as well.	
	res = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		
		APIResources resourceAPI= 	APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resSpec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))	
		response1 = res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
		response1 = res.when().get(resourceAPI.getResource());
	}

	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response1.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(getJsonPath(response1,keyValue),expectedValue);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    
		place_id = getJsonPath(response1,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_http_request(resource,"GET");
		String actualName = getJsonPath(response1,"name");
		assertEquals(actualName,expectedName);
	}
	
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	    
	}

}
