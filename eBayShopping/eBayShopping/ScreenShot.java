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
		  
		   String destDir = "/Users/fastestuser/eclipse-workspace/eBayShopping/src/screenshots";
		  
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  
		  DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  
		  new File(destDir).mkdirs();
		  
		  String destFile = "Screenshot_"+dateFormat.format(new Date()) + ".png";

		  try {
		   
		   FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 }

}
