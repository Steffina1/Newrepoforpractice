package stepdefinitions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Getprogrambyid {
	RequestSpecification request;
    Response response;
    String ProgramID;
    int StatusCode;
    String  statusline;
	@Given("User sends valid base url to get Program by Id")
	public void user_sends_valid_base_url_to_get_program_by_id() {
		RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms/";
		request=RestAssured.given(); 
	}

	@When("User sends request with its endpoint to get Program ID")
	public void user_sends_request_with_its_endpoint_to_get_program_id() {
		  response=request.get("/programs/1699");
		    ProgramID=  response.then().contentType(ContentType.JSON).extract().path("programId").toString();
		    String jsonString = response.asPrettyString();
		    System.out.println(jsonString);
	}

	@Then("User should receive response with status code")
	public void user_should_receive_response_with_status_code() {
		int StatusCode = response.getStatusCode();
		Assert.assertEquals(StatusCode,200);
	}

	@Then("Validate response body  to get Program by ID")
	public void validate_response_body_to_get_program_by_id() {
		String responsebody=response.getBody().asString();
		if(responsebody.contains("programId")){
		assertTrue(true);
		System.out.println("responsebody");
		}

	}

	@Then("Validate Get Program by ID Schema in json format")
	public void validate_get_program_by_id_schema_in_json_format() {
		response.then().assertThat().body(matchesJsonSchemaInClasspath("jsonschemalms.json")).statusCode(200);
	}

	@Then("Verify status line should be success line")
	public void verify_status_line_should_be_success_line() {
		String statusline = response.getStatusLine();
		Assert.assertEquals("HTTP/1.1 200 ", statusline);
	}

	@Given("User sends the url with invalid request")
	public void user_sends_the_url_with_invalid_request() {
		RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms/";
		request=RestAssured.given(); 
	}

	@When("User sends the request with its invalid request")
	public void user_sends_the_request_with_its_invalid_request() {
		response=request.post("/programs/1699");
	}

	@Then("Validate response with status code Method not allowed")
	public void validate_response_with_status_code_method_not_allowed() {
		int StatusCode = response.getStatusCode();
		Assert.assertEquals(StatusCode,405);
	}

	@Then("Validate response body with error message not allowed")
	public void validate_response_body_with_error_message_not_allowed() {
		String responsebody=response.getBody().asString();
		if(responsebody.contains("Method Not Allowed")){
		assertTrue(true);
		System.out.println("responsebody");
		}

	}

	@Given("User sends the request to get Program ID")
	public void user_sends_the_request_to_get_program_id() {
		RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms/";
		request=RestAssured.given(); 
	}

	@When("User sends a request body with invalid endpoint")
	public void user_sends_a_request_body_with_invalid_endpoint() {
		response=request.get("/programs/160");
	}

	@Then("Validate the response body with error code message {string}")
	public void validate_the_response_body_with_error_code_message(String string) {
		String responsebody=response.getBody().asString();
		if(responsebody.contains("ENTITY_DOES_NOT_EXIST")){
		assertTrue(true);
		System.out.println("responsebody");
		}
	}
	@Then("Verify response with status code Bad Request")
	public void verify_response_with_status_code_bad_request() {
		int StatusCode = response.getStatusCode();
		Assert.assertEquals(StatusCode,400);
	}



}
