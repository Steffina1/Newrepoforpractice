package stepdefinitions;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import commonclass.CommonMthods;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;
import utilities.Excelreader;

import static org.testng.Assert.assertTrue;

public class GetallbatchesSDbackup {
	ConfigReader reader;
	RequestSpecification request;
	Response response;
	Excelreader excel;
    Logger logs = LogManager.getLogger(GetallbatchesSDbackup.class);
    CommonMthods com;
    public GetallbatchesSDbackup(CommonMthods com) {
    	this.com = com;
    	}
    @Epic("Batches")
	@Description("Response validation for getting all batches")
	@Severity(SeverityLevel.NORMAL)
	@Feature("Test all batches using GET")
	@Given("The user sends base url as a part of request to get all batches")
	public void the_user_sends_base_url_as_a_part_of_request_to_get_all_batches() throws IOException {
		 request =given().baseUri(com.geturl());
		 logs.info("Enter basic url to get all batches");
	}

	@Then("The content type of response header should be application\\/json to get all batches")
	public void the_content_type_of_response_header_should_be_application_json_to_get_all_batches() throws IOException {
		 reader=new ConfigReader();
		 response= request.get(com.endpointforallbatches());
		 response.then().assertThat().header("Content-Type", reader.contenttypejson());
	}

	@When("User sends request with valid endpoint to get all batches")
	public void user_sends_request_with_valid_endpoint_to_get_all_batches() throws IOException {
		 logs.info("User enters valid endpoint for all batches");
		 response= request.get(com.endpointforallbatches());
		
	}

	@Then("User should receive response with success status code for all batches")
	public void user_should_receive_response_with_success_status_code_for_all_batches() throws IOException {
		 reader=new ConfigReader();
		 response= request.get(com.endpointforallbatches());
		response.then().assertThat().statusCode(reader.successcode());
	}

	@Then("The response body should contain details of the batch from {string} {string} for all batches")
	public void the_response_body_should_contain_details_of_the_batch_from_for_all_batches(String testid, String sheetname) {
		excel = new Excelreader(testid, sheetname);
		 String responsebody= response.getBody().asString();
		 if(responsebody.contains(excel.readdatafromexcel())) {
			 assertTrue(true);
			 System.out.println(responsebody);
		 }
		}

	@Then("The status line should be HTTP\\/{double} {int} for all batches")
	public void the_status_line_should_be_http_for_all_batches(Double double1, Integer int1) throws IOException {
		 reader=new ConfigReader();
		response.then().assertThat().statusLine(reader.successsl());
	}

	@When("User sends PUT, PATCH, DELETE requests with valid endpoint for all batches")
	public void user_sends_put_patch_delete_requests_with_valid_endpoint_for_all_batches() throws IOException {
		 logs.info("User sends invalid request for all batches");
		     response = request.put(com.endpointforallbatches());
		     response = request.patch(com.endpointforallbatches());
		     response = request.delete(com.endpointforallbatches());
	}

	@Then("User should receive response with Method Not Allowed status code for all batches")
	public void user_should_receive_response_with_method_not_allowed_status_code_for_all_batches() throws IOException {
		 reader=new ConfigReader();
		response.then().assertThat().statusCode(reader.notallowedsc());
	}

	@Then("The response body should contain error message from {string} {string} for all batches")
	public void the_response_body_should_contain_error_message_from_for_all_batches(String testid, String sheetname) {
		excel=new Excelreader(testid, sheetname);
		 String respbody= response.getBody().asString();
		 if(respbody.contains(excel.readdatafromexcel())) {
			 assertTrue(true);
			 System.out.println(respbody);
		 }
	}

	@Then("The status line should be HTTP\\/{double} {int} for all batches with invalid request")
	public void the_status_line_should_be_http_for_all_batches_with_invalid_request(Double double1, Integer int1) throws IOException {
		 reader=new ConfigReader();
		 response.then().assertThat().statusLine(reader.methodnotallowedsl());
	}

	@When("User sends POST request with valid endpoint for all batches")
	public void user_sends_post_request_with_valid_endpoint_for_all_batches() throws IOException {
		 response = request.post(com.endpointforallbatches());
		
	}

	@Then("User should receive response with Method Unsupported Media Type status code for all the batches")
	public void user_should_receive_response_with_method_unsupported_media_type_status_code_for_all_the_batches() throws IOException {
		 reader=new ConfigReader();
		  response.then().assertThat().statusCode(reader.unsupportedmediasc());
		 
	}
	
	@Then("The response body should contain unsupp error message from {string} {string} for all batches")
	public void the_response_body_should_contain_unsupp_error_message_from_for_all_batches(String testid, String sheetname) {
		excel = new Excelreader(testid, sheetname);
	   String respbody= response.getBody().asString();
		 if(respbody.contains(excel.readdatafromexcel())) {
			 assertTrue(true);
			 System.out.println(respbody);
		 }
	}

	@Then("The status line should be HTTP\\/{double} {int} for all the batches with invalid request")
	public void the_status_line_should_be_http_for_all_the_batches_with_invalid_request(Double double1, Integer int1) throws IOException {
		  reader=new ConfigReader();
		   response.then().assertThat().statusLine(reader.unsupportedmediasl());
	}

	@When("User sends request with invalid endpoint for all batches")
	public void user_sends_request_with_invalid_endpoint_for_all_batches() throws IOException {
		logs.info("The user sends invalid endpoint for all batches");
		  reader=new ConfigReader();
		response = request.get(reader.invalidbatchendpoint());
	}

	@Then("User should receive response with Not found status code for all batches")
	public void user_should_receive_response_with_not_found_status_code_for_all_batches() throws IOException {
		 reader=new ConfigReader();
		 response.then().assertThat().statusCode(reader.notfoundsc());
	}

	@Then("The response body should contain error message from {string} {string} for all batches with invalid endpoint")
	public void the_response_body_should_contain_error_message_from_for_all_batches_with_invalid_endpoint(String testid, String sheetname) {
		excel = new Excelreader(testid, sheetname);
		 String respbody= response.getBody().asString();
		 if(respbody.contains(excel.readdatafromexcel())) {
			 assertTrue(true);
			 System.out.println(respbody);
		 }
	}

	@Then("The status line should be HTTP\\/{double} {int} for all batches with invalid endpoint")
	public void the_status_line_should_be_http_for_all_batches_with_invalid_endpoint(Double double1, Integer int1) throws IOException {
		 reader=new ConfigReader();
		   response.then().assertThat().statusLine(reader.notfoundsl());
	}



}
