package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Exceltry {
	 DataFormatter fmt;
	 String Rownumber;
	String batchdetail;
	public Exceltry(String rownumber2, String batchdetail) {
		this.Rownumber = rownumber2;
		this.batchdetail=batchdetail;
		
	}
	
	
	public String postmethod(int Rownumber, String batchdetail) {
	
	File file = new File("C:\\Users\\amitf\\eclipse-workspace\\HackathonforAPI\\Backupforapihackathon\\src\\test\\resources\\Testdata\\postapilms.xlsx");
	FileInputStream fs = null;
	try {
		fs = new FileInputStream(file);
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	XSSFWorkbook book = null;
	try {
		book = new XSSFWorkbook(fs);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	XSSFSheet sheet = book.getSheet("postapi");
	int rowcount = sheet.getLastRowNum();

	for (int rownum =0; rownum<rowcount; rownum++) {
		 Row excelrow = sheet.getRow(rownum);
		if(rownum == Rownumber) {
			  fmt = new DataFormatter();
			fmt.formatCellValue(excelrow.getCell(0));     
		} 
		   int cellcount = excelrow.getLastCellNum();
		   for(int cellnum=1; cellnum<cellcount; cellnum++) {
			   if(excelrow.getCell(0).getStringCellValue().equals(batchdetail)) {
				   return fmt.formatCellValue(excelrow.getCell(cellnum));
		   }
		}
			
		}
	return "";
//			
}

}
