//package stepdefinitions;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import utilities.Exceltry;
//
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.equalTo;
//
//import java.util.Map;
//
//import org.apache.commons.collections4.map.HashedMap;
//import org.json.simple.JSONObject;
//
////import org.json.simple.JSONObject;
////
//import commonclass.Base;
////
//public class Createbatchstep extends Base {
//	 RequestSpecification request;
//	 Response response;
//	 Exceltry exce;
////	@Given("User sends the baseurl")
////	public void user_sends_the_baseurl() {
////	    baseURI = baseurl;
////	    response = request.get(createbatchendpoint);
////	}
////
////	@When("User sends the request  body to create Batch.")
////	public void user_sends_the_request_body_to_create_batch() {
////		
////		Map<String, Object> map = new HashedMap<String, Object>();
////		map.put("batchName", );
////		map.put("batchDescription", );
////		map.put("batchStatus", );
////		map.put("batchNoOfClasses", );
////		map.put("programId", );
////		JSONObject json = new JSONObject();
////		json.put("name", "morpheus");
////		json.put("job", "leader");
////		given().
////		header("Content-Type", "application/json").
////		contentType(ContentType.JSON).
////		accept(ContentType.JSON).
////		body(json.toJSONString()).
////		when().post("/users").
////		then().statusCode(201).
////		body("name", equalTo("morpheus"));
////	}
////
//
//@Given("User sends the baseurls")
//public void user_sends_the_baseurls() {
//	  baseURI = baseurl;
//	  request =given().baseUri(baseurl);
//	    response = request.post(createbatchendpoint);
//}
//
//@When("User sends the request  body to create Batch from {string} {string}")
//public void user_sends_the_request_body_to_create_batch_from(String Rownumber, String batchdetail) {
//	exce = new Exceltry(Rownumber, batchdetail);
//	JSONObject json = new JSONObject();
//	json.put("batchName", exce.postmethod(0, "batchname"));
//	System.out.println(exce.postmethod(0, "batchname"));
//	json.put("batchDescription", exce.postmethod(1, "batchDescription"));
//	System.out.println(exce.postmethod(1, "batchDescription"));
//	json.put("batchStatus", exce.postmethod(2, "batchStatus"));
//	System.out.println(exce.postmethod(2, "batchStatus"));
//	json.put("batchNoOfClasses", exce.postmethod(3, "batchNoOfClasses"));
//	System.out.println(exce.postmethod(3, "batchNoOfClasses"));
//	json.put("programId", exce.postmethod(4, "programId"));
//	given().
//	header("Content-Type", "application/json").
//	contentType(ContentType.JSON).
//	accept(ContentType.JSON).
//	body(json.toJSONString()).
//	when().post(createbatchendpoint);
//	 String responsebody= response.getBody().asPrettyString();
//		System.out.println(responsebody);
//}
//
//
//@Then("Validate the batch response status as <{int}>.")
//public void validate_the_batch_response_status_as(Integer int1) {
//	response.then().statusCode(201);
//	
//}
//
//@Then("Validate the response body for the batchName,batchDescription,batchStatus")
//public void validate_the_response_body_for_the_batch_name_batch_description_batch_status() {
//  System.out.println("passed");
//}
//
//@Then("Validate the batch response headers should be the application json format")
//public void validate_the_batch_response_headers_should_be_the_application_json_format() {
//    System.out.println("passed");
//}
//}
