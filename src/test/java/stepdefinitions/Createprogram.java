package stepdefinitions;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.testng.Assert;

import commonclass.Baseclass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class Createprogram extends Baseclass{
	String[][] progamData;
	protected Map<String, Response> responseMap = new HashMap<String, Response>();
	Map<String, String[]> dataMap = new HashMap<String, String[]>();
	
	@Given("User sends request with the endpoint to create program")
	public void user_sends_request_with_the_endpoint_to_create_program() {
		RestAssured.baseURI = baseurl;
	}

	@When("User sends a request body with valid endpoint to create Program.")
	public void user_sends_a_request_body_with_valid_endpoint_to_create_program() throws Exception {
		readProgramDataForStatusCode201();
	}
	@Then("Validate response status <{int}>.")
	public void validate_response_status(Integer statuscode) {
System.out.println("=================Status Code Validation=============");
		
		int statusCode = response.getStatusCode();
		System.out.println("ResponseStatusCode:"+statusCode);
		//Assert.assertEquals(statusCode, statuscode);
		
		for (String programId : responseMap.keySet()) {// key set returns set of values
			
			Response response = responseMap.get(programId);
			int responsebody = response.getStatusCode();
			Assert.assertEquals(responsebody, statuscode);
		}
	}

	@Then("Validate response body for programName,programDescription,programStatus")
	public void validate_response_body_for_program_name_program_description_program_status() throws Exception {
		readProgramResponseBodyValidation201();
	}

	@Then("Validate response headers should be application json format")
	public void validate_response_headers_should_be_application_json_format() throws Exception {
		readProgramResponseHeaders();
	}

	@Then("Verify response body schema {string} in json format.")
	public void verify_response_body_schema_in_json_format(String Schema)  {
		System.out.println("=========SCHEMA VALIDATION ========");
		 logger.info("Validate Json Schema");
		for (String programId : responseMap.keySet()) {
			Response response = responseMap.get(programId);
			response.then().assertThat().body(matchesJsonSchemaInClasspath(Schema));
		}
		System.out.println("=======SCHEMA VALIDATION SUCCESSFULL=========");
	}
	

	@Then("Verify the values are present in DB")
	public void verify_the_values_are_present_in_db() throws Exception {
		readProgramDBValidation();
	}


}
