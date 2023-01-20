package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Deleteprogbyid {
	RequestSpecification httprequest;
	Response response;
	@Given("User sends base url to delete a <{int}>.")
	public void user_sends_base_url_to_delete_a(Integer Pgmid) {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms/";
		RestAssured.basePath="/deletebyprogid/{programId}";
		httprequest=RestAssured.given().pathParam("programId", Pgmid);
	}

	@When("User sends request to delete with existing Program_ID")
	public void user_sends_request_to_delete_with_existing_program_id() {
		response=httprequest.delete();
		System.out.println(response.asPrettyString());
	}

	@Then("status code is the success")
	public void status_code_is_the_success() {
		int StatusCode = response.getStatusCode();
	    Assert.assertEquals(StatusCode, 200);
	}

	@Then("status line should be HTTP\\/{double} {int}")
	public void status_line_should_be_http(Double double1, Integer int1) {
		String statusline = response.getStatusLine();
	    Assert.assertEquals("HTTP/1.1 200 ", statusline);
	}

	@Given("User sends a base URL to delete <{int}>.")
	public void user_sends_a_base_url_to_delete(Integer pgmid) {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms/";
		RestAssured.basePath="/deletebyprogid/{programId}";
		httprequest=RestAssured.given().pathParam("programId", pgmid);
	}

	@When("User sends a request with invalid method.")
	public void user_sends_a_request_with_invalid_method() {
		response=httprequest.get();
		System.out.println(response.asPrettyString());
	}

	@Then("Verify response with status code ' {int} Method Not Allowed")
	public void verify_response_with_status_code_method_not_allowed(Integer int1) {
		int StatusCode = response.getStatusCode();
	    Assert.assertEquals(StatusCode, 405);
	}

	@Given("User sends base URL to delete invalid endpoint with valid <{int}>.")
	public void user_sends_base_url_to_delete_invalid_endpoint_with_valid(Integer pgmid) {
		RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms/";
		RestAssured.basePath="/deletebyprog/{programId}";
		httprequest=RestAssured.given().pathParam("programId", pgmid);
	}

	@When("User sends the request with invalid end point to to delete invalid end point.")
	public void user_sends_the_request_with_invalid_end_point_to_to_delete_invalid_end_point() {
		response=httprequest.delete();
		System.out.println(response.asPrettyString());
	}

	@Then("Verify response with status code Not found.")
	public void verify_response_with_status_code_not_found() {
		int StatusCode = response.getStatusCode();
	    Assert.assertEquals(StatusCode, 404);
	}

	@Given("User sends invalid base url")
	public void user_sends_invalid_base_url() {
		RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms/";
		httprequest=RestAssured.given();
	}

	@When("User sends invalid base url along with valid endpoint")
	public void user_sends_invalid_base_url_along_with_valid_endpoint() {
		response=httprequest.get("https://lms-backend.herokuapp.com/lms/");
		System.out.println("Invalid url");
	}

	@Then("Verify response with status code Service unavailable.")
	public void verify_response_with_status_code_service_unavailable() {
		int StatusCode = response.getStatusCode();
	    Assert.assertEquals(StatusCode,503);
	}

	@Given("User sends the request with invalid <{int}>")
	public void user_sends_the_request_with_invalid(Integer pgmid) {
		RestAssured.baseURI="https://lms-backend-service.herokuapp.com/lms/";
		RestAssured.basePath="/deletebyprogid/{programId}";
		httprequest=RestAssured.given().pathParam("programId", pgmid);;
	}

	@When("User sends valid request with invalid Program ID and its endpoint")
	public void user_sends_valid_request_with_invalid_program_id_and_its_endpoint() {
		response=httprequest.delete();
		System.out.println(response.asPrettyString());
	}

	@Then("Verify response should be Bad Request")
	public void verify_response_should_be_bad_request() {
		int StatusCode = response.getStatusCode();
	    Assert.assertEquals(StatusCode, 400);
	}

}
