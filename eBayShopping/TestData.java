package eBayShopping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {
	
	public String readData (String field) throws IOException {
		
		//Declare file location and file name
		File file =    new File("/Users/fastestuser/Desktop/Appium Automation/ebay/testdata.xlsx");
		String fileName = "testdata.xlsx";

	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook book = null;

	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
	    
	    //Object created for appropriate class based on the file type
	    if(fileExtensionName.equals(".xlsx")){

	    		book = new XSSFWorkbook(inputStream);
	    }

	    else if(fileExtensionName.equals(".xls")){

	        book = new HSSFWorkbook(inputStream);
	    }
	    
	    Sheet sheet = book.getSheet("Sheet1");
	    int i=0;
	    Row r=sheet.getRow(i);
	   
	    //perform linear search in test data sheet to fetch the corresponding value
	    while(!r.getCell(0).getStringCellValue().equals(field)){
	    	  	  
	    	  	r = sheet.getRow(++i);
	      }
	    
	   //loop breaks as search text matches cell value and the corresponding value is returned
	   return r.getCell(1).getStringCellValue();

	}
}
	