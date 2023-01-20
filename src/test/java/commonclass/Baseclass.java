package commonclass;

	import static org.testng.Assert.assertEquals;
	import static io.restassured.RestAssured.*;

	import java.util.HashMap;
	import java.util.Map;

	import org.apache.log4j.LogManager;
	import org.apache.log4j.Logger;
	import org.json.simple.JSONObject;

	import org.hamcrest.Matcher;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import utilities.ExcelUtils;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
	import io.restassured.response.Response;
	import io.restassured.response.ResponseBodyExtractionOptions;
	import io.restassured.response.Validatable;
	import io.restassured.response.ValidatableResponse;
	import io.restassured.specification.RequestSpecification;
import utilities.ReadConfigFilegaya;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
	import static io.restassured.RestAssured.*;

	public class Baseclass {

		static // read the data from the method in ReadConfigFile class

				ReadConfigFilegaya readConfigFile = new ReadConfigFilegaya();
				public static String baseurl = readConfigFile.getApplicationURL();
				public String programpath = readConfigFile.getProgramPath();
				public String batchpath = readConfigFile.getBatchesPath();
				public static RequestSpecification HttpRequest;
				//public String baseprogramExcelPath = readConfigFile.getprogramExcelPath();
				public String basebatchesExcelpath = readConfigFile.getbatchesExcelPath();
				public String basebatchesEndpoint = readConfigFile.getBatchesPath();

				//public String excelFilePath = readConfigFile.excelFilePath();
				public static RequestSpecification httpRequest; 
				public static io.restassured.response.Response response; 
				public static ValidatableResponse json;

			//post program
			public int badrequestStatusCode = readConfigFile.badrequestStatusCode();
			public final int createrequestStatusCode = readConfigFile.createrequestStatusCode();
			public static String baseprogramExcelPath = readConfigFile.getprogramExcelPath();
			
			
			//update program
			public static int updaterequestStatusCode = readConfigFile.updaterequestStatusCode();
			public final static   String updateProgramEndpointbyId = readConfigFile.getUpdateProgramPathbyId();
			public final String updateProgramEndpointbyName = readConfigFile.getUpdateProgramPathbyId();
			
			//common
			public final int invalidRequestStatusCode = readConfigFile.invalidRequestStatusCode();
			public final int invalidEndpointStatusCode = readConfigFile.invalidEndpointStatusCode();
			protected final String statusLine = readConfigFile.statusLine();
			public final String contentType = readConfigFile.contentType();
			public static String baseprogramEndpoint = readConfigFile.getProgramPath();

				public static int rownum;
				public static int colnum;
				public static String programId;
				public static String programName;
				public static String programDescription;
				public static String programStatus;
				public static String creationTime;
				public static String lastModTime;
				protected static Map<String, Response> responseMap = new HashMap<String, Response>(); // create Hashmap object for storing response
			protected static Map<String, String[]> dataMap = new HashMap<String, String[]>();
			
			protected Map<String, Response> batchresponseMap = new HashMap<String, Response>(); // create Hashmap object for storing response
			Map<String, String[]> batchdataMap = new HashMap<String, String[]>();
				public Logger logger=LogManager.getLogger(Baseclass.class);

				

				public void readProgramDataForStatusCode201() throws Exception{

					rownum = utilities.ExcelUtils.getRowCount(baseprogramExcelPath, "programdata");
					colnum = utilities.ExcelUtils.getCellCount(baseprogramExcelPath, "programdata", 1);

					System.out.println("row count:" + rownum);
					System.out.println("col count:" + colnum);
		String[][] progamData = new String[rownum][colnum];

				for (int i = 1; i <= rownum; i++) {
					for (int j = 0; j < colnum; j++) {

						progamData[i - 1][j] = utilities.ExcelUtils.getCellData(baseprogramExcelPath, "programdata", i,j); // i =1, j=0 --> first cell value

					}
				}
			
				JSONObject params = new JSONObject();
				for (String[] row : progamData) {
					
					params.put("programName", row[0]);
					params.put("programDescription", row[1]);
					params.put("programStatus", row[2]);
					params.put("creationTime", row[3]);
					params.put("lastModTime", row[4]);

					httpRequest = given().auth().none().contentType("application/json");
					httpRequest.body(params.toJSONString());
					response = httpRequest.request(Method.POST, baseprogramEndpoint);
					response.then().log().all();

					/* Get the User ID from the response */
					if(!(row[5].equals("401") || row[5].equals("404"))) {
						String programId = response.jsonPath().getString("programId");
						System.out.println("ProgramID: "+programId);
						try {
							responseMap.put(programId, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dataMap.put(programId, row);
					}
					
					String programId = response.jsonPath().getString("programId");
					responseMap.put(programId, response);
					dataMap.put(programId, row);
					/*String errorCode = response.jsonPath().getString("error");
					String errorMessage = response.jsonPath().getString("message");*/
					int statuscode = response.getStatusCode();
					System.out.println("ResponseStatusCode:"+statuscode);
					Assert.assertEquals(statuscode, 201);
				}


		}
				
				public void readProgramDataForStatus400() throws Exception{

					rownum = utilities.ExcelUtils.getRowCount(baseprogramExcelPath, "programerrordata");
					colnum = utilities.ExcelUtils.getCellCount(baseprogramExcelPath, "programerrordata", 1);

					System.out.println("row count:" + rownum);
					System.out.println("col count:" + colnum);

					String[][] progamData = new String[rownum][colnum];

					for (int i = 1; i <= rownum; i++) {
						for (int j = 0; j < colnum; j++) {

							progamData[i - 1][j] = utilities.ExcelUtils.getCellData(baseprogramExcelPath, "programerrordata", i,
									j); // i =1, j=0 --> first cell value

						}
					}
				
					JSONObject params = new JSONObject();
					for (String[] row : progamData) {
						params.put("programName", row[0]);
						params.put("programDescription", row[1]);
						params.put("programStatus", row[2]);
						params.put("creationTime", row[3]);
						params.put("lastModTime", row[4]);

						httpRequest = given().auth().none().contentType("application/json");// request
						httpRequest.body(params.toJSONString());
						response = httpRequest.request(Method.POST, baseprogramEndpoint);// response
						if((row[5].equals("400"))) { 
						String errorCode = response.jsonPath().getString("error");
						String errorMessage = response.jsonPath().getString("message");
						}	

				}
				}
				public void readProgramDataForInvalidCreationTime() throws Exception{

					rownum = utilities.ExcelUtils.getRowCount(baseprogramExcelPath, "programerrordata");
					colnum = utilities.ExcelUtils.getCellCount(baseprogramExcelPath, "programerrordata", 1);

					System.out.println("row count:" + rownum);
					System.out.println("col count:" + colnum);

					String[][] progamData = new String[rownum][colnum];

					for (int i = 1; i <= rownum; i++) {
						for (int j = 0; j < colnum; j++) {

							progamData[i - 1][j] = utilities.ExcelUtils.getCellData(baseprogramExcelPath, "programerrordata", i,
									j); // i =1, j=0 --> first cell value

						}
					}
				
					JSONObject params = new JSONObject();
					for (String[] row : progamData) {
						params.put("programName", row[0]);
						params.put("programDescription", row[1]);
						params.put("programStatus", row[2]);
						params.put("creationTime", row[3]);
						params.put("lastModTime", row[4]);

						httpRequest = given().auth().none().contentType("application/json");// request
						httpRequest.body(params.toJSONString());
						response = httpRequest.request(Method.POST, baseprogramEndpoint);// response
						if((row[6].equals("Invalid Creation Time"))) { 
						String errorCode = response.jsonPath().getString("error");
						String errorMessage = response.jsonPath().getString("message");
						}	

				}
			}
					

				public void readBatchesDataForStatusCode201() throws Exception {
					
					logger.info("Read data from Excel");
					rownum = utilities.ExcelUtils.getRowCount("./src/test/resources/configuration/ProgramTestData.xlsx", "batchdata");
					colnum = utilities.ExcelUtils.getCellCount("./src/test/resources/configuration/ProgramTestData.xlsx", "batchdata", 1);

					System.out.println("row count:" + rownum);
					System.out.println("col count:" + colnum);

					String[][] batchData = new String[rownum][colnum];

					for (int i = 1; i <= rownum; i++) {
						for (int j = 0; j < colnum; j++) {

							batchData[i - 1][j] = utilities.ExcelUtils.getCellData("./src/test/resources/configuration/ProgramTestData.xlsx", "batchdata", i,j); // i =1, j=0 --> first cell value

						}
					}

					JSONObject params = new JSONObject();
					for (String[] row : batchData) {
						
						params.put("batchName", row[0]);
						params.put("batchDescription", row[1]);
						params.put("batchStatus", row[2]);
						params.put("batchNoOfClasses", row[3]);
						params.put("programId", row[4]);
						
						httpRequest = given().auth().none().contentType("application/json");
						httpRequest.body(params.toJSONString());
						
						response = httpRequest.request(Method.POST, "https://lms-backend-service.herokuapp.com/lms/batches");
						response.then().log().all();

						/* Get the User ID from the response */
						if(!(row[5].equals("400") || row[5].equals("404"))) {
							
							System.out.println("responsebody:"+response.asString());
							String batchId = response.jsonPath().getString("batchId");
							System.out.println("batchId: "+batchId);
							batchresponseMap.put(batchId, response);
							batchdataMap.put(batchId, row);
						}else
						{	
							String status = response.jsonPath().getString("status");
							System.out.println("status is: "+status);
							Assert.assertEquals(status, badrequestStatusCode);
							String errorMessage= response.jsonPath().getString("Message");
							Assert.assertEquals(errorMessage, "Bad Request");
						}
						
						String batchId1 = response.jsonPath().getString("batchId");
						batchresponseMap.put(batchId1, response);
						batchdataMap.put(batchId1, row);
						
						int statuscode = response.getStatusCode();
						System.out.println("ResponseStatusCode:"+statuscode);
						Assert.assertEquals(statuscode, badrequestStatusCode);
					}
				}
				
		public void readBatchesDataForStatusCode403() throws Exception {
			
			//invalid existing endpoint
			logger.info("Read data from Excel");
			rownum = utilities.ExcelUtils.getRowCount(basebatchesExcelpath, "batchdata");
			colnum = utilities.ExcelUtils.getCellCount(basebatchesExcelpath, "batchdata", 1);

			System.out.println("row count:" + rownum);
			System.out.println("col count:" + colnum);

			String[][] batchData = new String[rownum][colnum];

			for (int i = 1; i <= rownum; i++) {
				for (int j = 0; j < colnum; j++) {

					batchData[i - 1][j] = utilities.ExcelUtils.getCellData(basebatchesExcelpath, "batchdata", i,j); // i =1, j=0 --> first cell value

				}
			}

			JSONObject params = new JSONObject();
			for (String[] row : batchData) {
				
				params.put("batchName", row[0]);
				params.put("batchDescription", row[1]);
				params.put("batchStatus", row[2]);
				params.put("batchNoOfClasses", row[3]);
				params.put("programId", row[4]);

				httpRequest = given().auth().none().contentType("application/json");
				httpRequest.body(params.toJSONString());
				response = httpRequest.request(Method.POST, baseprogramEndpoint);
				response.then().log().all();

				/* Get the User ID from the response */
				if(!(row[5].equals("401") || row[5].equals("404"))) {
					
					System.out.println("responsebody:"+response.asString());
					String batchId = response.jsonPath().getString("batchId");
					System.out.println("batchId: "+batchId);
					batchresponseMap.put(batchId, response);
					batchdataMap.put(batchId, row);
				}
				
				String batchId = response.jsonPath().getString("batchId");
				batchresponseMap.put(batchId, response);
				batchdataMap.put(batchId, row);
				/*String errorCode = response.jsonPath().getString("error");
				String errorMessage = response.jsonPath().getString("message");*/
				int statuscode = response.getStatusCode();
				System.out.println("ResponseStatusCode:"+statuscode);
				Assert.assertEquals(statuscode, 201);
			}
		}

		public void readBatchesDataForStatusCode400() throws Exception {

			rownum = utilities.ExcelUtils.getRowCount(basebatchesExcelpath, "batcherrordata");
			colnum = utilities.ExcelUtils.getCellCount(basebatchesExcelpath, "batcherrordata", 1);

			System.out.println("row count:" + rownum);
			System.out.println("col count:" + colnum);

			String[][] batchData = new String[rownum][colnum];

			for (int i = 1; i <= rownum; i++) {
				for (int j = 0; j < colnum; j++) {

					batchData[i - 1][j] = utilities.ExcelUtils.getCellData(basebatchesExcelpath, "batcherrordata", i, j); 

				}
			}

			JSONObject params = new JSONObject();
			for (String[] row : batchData) {
				params.put("batchName", row[0]);
				params.put("batchDescription", row[1]);
				params.put("batchStatus", row[2]);
				params.put("batchNoOfClasses", row[3]);
				params.put("programId", row[4]);

				httpRequest = given().auth().none().contentType("application/json");// request
				httpRequest.body(params.toJSONString());
				response = httpRequest.request(Method.POST, basebatchesEndpoint);// response
				String batchId = response.jsonPath().getString("batchId");
				((RestAssured) responseMap.put(batchId, (Response) response)).put(batchId, response);// use hashmap to store response
				dataMap.put(batchId, row);// use another hashmap datamap to read all values by row*/

			}
		}


		public void readUpdateProgramResponseBodyValidation200() throws Exception {


			// response body validation
			System.out.println("=========Responsebody in POST Request=========");
			
			// Response body validation
			System.out.println("==========Validate responsebody is not null==================");
			String responsebody = response.getBody().asString();
			System.out.println("Response body:"+responsebody);
			Assert.assertTrue(response != null);// check response body not equal to null
			
			System.out.println("==========Validate Program Id==================");
			String programId = response.jsonPath().getString("programId");
			Assert.assertEquals(responsebody.contains(programId), true);
			System.out.println("==========Validate Program Name==================");
			String programName = response.jsonPath().getString("programId");
			Assert.assertEquals(responsebody.contains(programName), true);
			System.out.println("==========Validate Program Description==================");
			String programDescription = response.jsonPath().getString("programDescription");
			Assert.assertEquals(responsebody.contains(programDescription), true);
			System.out.println("==========Validate Program Status==================");
			String programStatus = response.jsonPath().getString("programStatus");
			Assert.assertEquals(responsebody.contains(programStatus), true);
			System.out.println("==========Validate Creation Time==================");
			String creationTime = response.jsonPath().getString("creationTime");
			Assert.assertEquals(responsebody.contains(creationTime), true);
			System.out.println("==========Validate last modification Time==================");
			String lastModTime = response.jsonPath().getString("lastModTime");
			Assert.assertEquals(responsebody.contains(lastModTime), true);
			
			/*String errorcode = response.jsonPath().getString("errorCode");
			Assert.assertEquals( errorcode,"DUPLICATE_ENTITY");*/
			
			System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		}

		/*public void readProgramResponseHeaders() throws Exception {
			
			// fetch all headers
					Headers allheaders = response.headers();
					for (Header header : allheaders) {
						System.out.println(header.getName() + " " + header.getValue());
					}

					logger.info("Validate the Content Type");
					for (String programId : responseMap.keySet()) {

						Response response = responseMap.get(programId);
						assertEquals(response.getContentType(), contentType);

					}
	}
}*/
		
		public void readProgramDBValidation()throws Exception{
			
			System.out.println("============DB Validation=============");
			
			for (String programId : dataMap.keySet()) {

				String[] row = dataMap.get(programId);
				
				response = httpRequest.request(Method.GET, baseurl +"programs/" + programId);
				response.then().log().all();
				//String ResponseDBCheck = given().auth().none().when().get().getBody().asString();
				String ResponseDBCheck = response.asString();
				System.out.println("DBCheck::::"+ResponseDBCheck);
				System.out.println("==========Validate Program name==================");
				String programName1 = response.jsonPath().getString("programName");
				Assert.assertEquals(ResponseDBCheck.contains(programName1), true);
				
				System.out.println("==========Validate Program Description==================");
				String programDescription1 = response.jsonPath().getString("programDescription");
				Assert.assertEquals(ResponseDBCheck.contains(programDescription1), true);
				
				System.out.println("==========Validate Program Status==================");
				String programStatus1 = response.jsonPath().getString("programStatus");
				Assert.assertEquals(ResponseDBCheck.contains(programStatus1), true);
				
				System.out.println("==========Validate Creation Time==================");
				String creationTime1 = response.jsonPath().getString("creationTime");
				Assert.assertEquals(ResponseDBCheck.contains(creationTime1), true);
				
				System.out.println("==========Validate last modification Time==================");
				String lastModTime1 = response.jsonPath().getString("lastModTime");
				Assert.assertEquals(ResponseDBCheck.contains(lastModTime1), true);
				
				// assert the value in DB
				
				Assert.assertEquals(programName1, row[0]);
				Assert.assertEquals(programDescription1, row[1]);
				Assert.assertEquals(programStatus1, row[2]);
				Assert.assertEquals(creationTime1, row[3]);
				Assert.assertEquals(lastModTime1, row[4]);
				
			}
			System.out.println("=======DB VALIDATION SUCCESSFULL=========");
			}

		public void readBatchesResponseHeaders() throws Exception {
			
			// fetch all headers
					Headers allheaders = response.headers();
					for (Header header : allheaders) {
						System.out.println(header.getName() + " " + header.getValue());
					}

					logger.info("Validate the Content Type");
					for (String batchId : batchresponseMap.keySet()) {

						Response response = batchresponseMap.get(batchId);
						assertEquals(response.getContentType(), contentType);

					}

		}


		public void readBatchesResponseBodyValidation201() throws Exception {


			// response body validation
			System.out.println("=========Responsebody in POST Request=========");
			
			// Response body validation
			System.out.println("==========Validate responsebody is not null==================");
			String responsebody = response.getBody().asString();
			System.out.println("Response body:"+responsebody);
			Assert.assertTrue(response != null);// check response body not equal to null
			
			System.out.println("==========Validate Batch Id==================");
			String batchId = response.jsonPath().getString("batchId");
			Assert.assertEquals(responsebody.contains(batchId), true);
			
			System.out.println("==========Validate Batch Description==================");
			String batchDescription = response.jsonPath().getString("batchDescription");
			Assert.assertEquals(responsebody.contains(batchDescription), true);
			
			System.out.println("==========Validate Program Status==================");
			String batchStatus = response.jsonPath().getString("batchStatus");
			Assert.assertEquals(responsebody.contains(batchStatus), true);
			
			System.out.println("==========Validate batch No Of Classes==================");
			String batchNoOfClasses = response.jsonPath().getString("batchNoOfClasses");
			Assert.assertEquals(responsebody.contains(batchNoOfClasses), true);
			
			System.out.println("==========Validate program Id==================");
			String programId = response.jsonPath().getString("programId");
			Assert.assertEquals(responsebody.contains(programId), true);
			
			System.out.println("==========Validate program name==================");
			String programName = response.jsonPath().getString("programName");
			Assert.assertEquals(responsebody.contains(programName), true);
			
			/*String errorcode = response.jsonPath().getString("errorCode");
			Assert.assertEquals( errorcode,"DUPLICATE_ENTITY");*/
			
			System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		}

		public void readBatchesDBValidation()throws Exception{

			System.out.println("============DB Validation=============");
			
			for (String batchId : batchdataMap.keySet()) {

				String[] row = batchdataMap.get(batchId);
				
				response = httpRequest.request(Method.GET, baseurl +"batches/batchId" + batchId);
				response.then().log().all();
				//String ResponseDBCheck = given().auth().none().when().get().getBody().asString();
				String ResponseDBCheck = response.asString();
				System.out.println("DBCheck::::"+ResponseDBCheck);
				System.out.println("==========Validate Program name==================");
				String batchName1 = response.jsonPath().getString("batchName");
				Assert.assertEquals(ResponseDBCheck.contains(batchName1), true);
				
				System.out.println("==========Validate Program Description==================");
				String batchDescription1 = response.jsonPath().getString("batchDescription");
				Assert.assertEquals(ResponseDBCheck.contains(batchDescription1), true);
				
				System.out.println("==========Validate Program Status==================");
				String batchStatus1 = response.jsonPath().getString("batchStatus");
				Assert.assertEquals(ResponseDBCheck.contains(batchStatus1), true);
				
				System.out.println("==========Validate Creation Time==================");
				String batchNoOfClasses1 = response.jsonPath().getString("batchNoOfClasses");
				Assert.assertEquals(ResponseDBCheck.contains(batchNoOfClasses1), true);
				
				System.out.println("==========Validate last modification Time==================");
				String programId1 = response.jsonPath().getString("programId");
				Assert.assertEquals(ResponseDBCheck.contains(programId1), true);
				
				// assert the value in DB
				
				Assert.assertEquals(batchName1, row[0]);
				Assert.assertEquals(batchDescription1, row[1]);
				Assert.assertEquals(batchStatus1, row[2]);
				Assert.assertEquals(batchNoOfClasses1, row[3]);
				Assert.assertEquals(programId1, row[4]);
				
			}
			System.out.println("=======DB VALIDATION SUCCESSFULL=========");
			}


		  //=======================update program by id====================
		public  static void readUpdateProgramDatabyIdForStatusCode200() throws Exception{

			rownum = utilities.ExcelUtils.getRowCount(baseprogramExcelPath, "updateprogramdata");
			colnum = utilities.ExcelUtils.getCellCount(baseprogramExcelPath, "updateprogramdata", 1);

			System.out.println("row count:" + rownum);
			System.out.println("col count:" + colnum);

			String[][] progamData = new String[rownum][colnum];

			for (int i = 1; i <= rownum; i++) {
				for (int j = 0; j < colnum; j++) {

					progamData[i - 1][j] = utilities.ExcelUtils.getCellData(baseprogramExcelPath, "updateprogramdata", i,j); // i =1, j=0 --> first cell value

				}
			}

			JSONObject params = new JSONObject();
			for (String[] row : progamData) {
				
				params.put("programName", row[2]);
				params.put("programDescription", row[3]);
				params.put("programStatus", row[4]);
				params.put("creationTime", row[5]);
				params.put("lastModTime", row[6]);

				httpRequest = given().auth().none().contentType("application/json");
				httpRequest.body(params.toJSONString());
				response = httpRequest.request(Method.PUT, baseurl + updateProgramEndpointbyId+row[0]);
				response.then().log().all();

				/* Get the User ID from the response */
				if(!(row[7].equals("401") || row[5].equals("404"))) {
					String programId = response.jsonPath().getString("programId");
					System.out.println("ProgramID: "+programId);
					
						responseMap.put(programId, response);
						dataMap.put(programId, row);
				} 
				
				String programId = response.jsonPath().getString("programId");
				responseMap.put(programId, response);
				dataMap.put(programId, row);
				/*String errorCode = response.jsonPath().getString("error");
				String errorMessage = response.jsonPath().getString("message");*/
				int statuscode = response.getStatusCode();
				System.out.println("ResponseStatusCode:"+statuscode);
				Assert.assertEquals(statuscode, updaterequestStatusCode);
			}
		}


		public void readProgramResponseBodyValidation201() throws Exception {


			// response body validation
			System.out.println("=========Responsebody in POST Request=========");
			
			// Response body validation
			System.out.println("==========Validate responsebody is not null==================");
			String responsebody = response.getBody().asString();
			System.out.println("Response body:"+responsebody);
			Assert.assertTrue(response != null);// check response body not equal to null
			
			System.out.println("==========Validate Program Id==================");
			String programId = response.jsonPath().getString("programId");
			Assert.assertEquals(responsebody.contains(programId), true);
			System.out.println("==========Validate Program Description==================");
			String programDescription = response.jsonPath().getString("programDescription");
			Assert.assertEquals(responsebody.contains(programDescription), true);
			System.out.println("==========Validate Program Status==================");
			String programStatus = response.jsonPath().getString("programStatus");
			Assert.assertEquals(responsebody.contains(programStatus), true);
			System.out.println("==========Validate Creation Time==================");
			String creationTime = response.jsonPath().getString("creationTime");
			Assert.assertEquals(responsebody.contains(creationTime), true);
			System.out.println("==========Validate last modification Time==================");
			String lastModTime = response.jsonPath().getString("lastModTime");
			Assert.assertEquals(responsebody.contains(lastModTime), true);
			
			/*String errorcode = response.jsonPath().getString("errorCode");
			Assert.assertEquals( errorcode,"DUPLICATE_ENTITY");*/
			
			System.out.println("=============RESPONSE BODY VALIDATION SUCCESSFULL==========");
		}

		public void readProgramResponseHeaders() throws Exception {
			
			// fetch all headers
					Headers allheaders = response.headers();
					for (Header header : allheaders) {
						System.out.println(header.getName() + " " + header.getValue());
					}

					logger.info("Validate the Content Type");
					for (String programId : responseMap.keySet()) {

						Response response = responseMap.get(programId);
						assertEquals(response.getContentType(), contentType);

					}

		}

		public void readUpdateProgrambyIdDBValidation()throws Exception{
			
			System.out.println("============DB Validation=============");
			
			for (String programId : dataMap.keySet()) {

				String[] row = dataMap.get(programId);
				
				response = httpRequest.request(Method.GET, baseurl +"programs/" + programId);
				response.then().log().all();
				//String ResponseDBCheck = given().auth().none().when().get().getBody().asString();
				String ResponseDBCheck = response.asString();
				System.out.println("DBCheck::::"+ResponseDBCheck);
				System.out.println("==========Validate Program name==================");
				String programName1 = response.jsonPath().getString("programName");
				Assert.assertEquals(ResponseDBCheck.contains(programName1), true);
				
				System.out.println("==========Validate Program Description==================");
				String programDescription1 = response.jsonPath().getString("programDescription");
				Assert.assertEquals(ResponseDBCheck.contains(programDescription1), true);
				
				System.out.println("==========Validate Program Status==================");
				String programStatus1 = response.jsonPath().getString("programStatus");
				Assert.assertEquals(ResponseDBCheck.contains(programStatus1), true);
				
				System.out.println("==========Validate Creation Time==================");
				String creationTime1 = response.jsonPath().getString("creationTime");
				Assert.assertEquals(ResponseDBCheck.contains(creationTime1), true);
				
				System.out.println("==========Validate last modification Time==================");
				String lastModTime1 = response.jsonPath().getString("lastModTime");
				Assert.assertEquals(ResponseDBCheck.contains(lastModTime1), true);
				
				// assert the value in DB
				
				Assert.assertEquals(programId, row[0]);
				Assert.assertEquals(programName, row[1]);
				Assert.assertEquals(programDescription1, row[2]);
				Assert.assertEquals(programStatus, row[3]);
				Assert.assertEquals(creationTime1, row[4]);
				Assert.assertEquals(lastModTime1, row[5]);
				
			}
			System.out.println("=======DB VALIDATION SUCCESSFULL=========");
			}
		}