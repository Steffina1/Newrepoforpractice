package utilities;


	import java.io.FileInputStream;
	import java.util.Properties;
		import java.io.File;
		import java.io.FileInputStream;
		import java.util.Properties;

public class ReadConfigFilegaya {

					private Properties properties;
						private final String propertyFilePath= "C:\\Users\\amitf\\eclipse-workspace\\HackathonforAPI\\Backupforapihackathon\\src\\test\\resources\\Configss\\configpropgaya.properties";
						

						public ReadConfigFilegaya() {

							File filePath = new File(propertyFilePath);
							//open the file in read mode 
							FileInputStream fis;
							try {
								fis = new FileInputStream(filePath);
								properties = new Properties();
								properties.load(fis);
							} catch (Exception e)
								{
									System.out.println("Exception is"+e.getMessage());
								}
							}	

							//add method to read variable and the values
							public String getApplicationURL() {

								String baseurl=properties.getProperty("baseurl");
								return baseurl;
							}

							public String getProgramPath(){
								String programendpoint = properties.getProperty("programendpoint");
								return programendpoint;
							}

							public String getBatchesPath() {
								String batchesendpoint = properties.getProperty("batchesendpoint");
								return batchesendpoint;
							}

							public String getprogramExcelPath(){
								String programExcelPath = properties.getProperty("programexcelpath");
								return programExcelPath;
						
							}
							public String getbatchesExcelPath(){
								String batchesexcelpath = properties.getProperty("batchesexcelpath");
								return batchesexcelpath;
						
							}

							public int badrequestStatusCode() {
								int badrequestStatusCode = Integer.parseInt(properties.getProperty("badrequestStatusCode"));
								return badrequestStatusCode;
							}
							
							public int createrequestStatusCode() {
								int createrequestStatusCode = Integer.parseInt(properties.getProperty("createrequestStatusCode"));
								return createrequestStatusCode;
							
							}
							
							public int invalidRequestStatusCode() {
								int invalidRequestStatusCode = Integer.parseInt(properties.getProperty("invalidRequestStatusCode"));
								return invalidRequestStatusCode;
							
							}
							public int invalidEndpointStatusCode() {
								int invalidEndpointStatusCode = Integer.parseInt(properties.getProperty("invalidRequestStatusCode"));
								return invalidEndpointStatusCode;
							
							}
							public String statusLine() {
								String statusLine = properties.getProperty("statusLine");
								return statusLine;
							
							}
							
					public int updaterequestStatusCode() {
						int updaterequestStatusCode = Integer.parseInt(properties.getProperty("updaterequestStatusCode"));
						return updaterequestStatusCode;
					
					}

			public String contentType() {
						String contentType = properties.getProperty("contentType");
						return contentType;
					}
						

					public String getUpdateProgramPathbyId() {
						 
						String updateprogramEndpointbyId = properties.getProperty("updateprogramEndpointbyId");
						return updateprogramEndpointbyId;
					}
					
					public String getUpdateProgramPathbyName() {
						 
						String updateprogramEndpointbyName = properties.getProperty("updateprogramEndpointbyName");
						return updateprogramEndpointbyName;
					}
					
			}

