package eBayShopping;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.android.utils.FileUtils;
import io.appium.java_client.android.AndroidDriver;

public class ScreenShot {
	
	public void takeScreenShot(AndroidDriver driver) {
		  
		  //Set destination folder where screenshots are to be saved
		  String destDir = "/Users/fastestuser/eclipse-workspace/eBayShopping/src/screenshots";
		  
		  //Typecasting output file 
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  
		  //declare date format in which the screenshot names are to be stored 
		  DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  
		  //create screenshot folder
		  new File(destDir).mkdirs();
		  
		  //set file name and file format of the captured screenshots
		  String destFile = "Screenshot_"+dateFormat.format(new Date()) + ".png";

		  try {
		   
			//Transferring the captured image data from the original file into a destination file which can be stored
		   FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 }

}
