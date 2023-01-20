package commonclass;


import utilities.ConfigReader;

public class Base {
	
	ConfigReader reader=new ConfigReader();
	
	public String baseurl= reader.baseurl();
	public String createbatchendpoint= reader.createbatchendpoint();
	

}
