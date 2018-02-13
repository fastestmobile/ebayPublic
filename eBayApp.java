package eBayShopping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class eBayApp {
	
	TestData data=null;
	DesiredCapabilities caps = null;
	AppiumDriver<MobileElement> driver = null;
	
	@BeforeSuite
	public void setup() throws IOException {
		
		data = new TestData();
		
		caps = new DesiredCapabilities();
		
		caps.setCapability("deviceName", data.readData("devicename"));
		caps.setCapability("udid", data.readData("udid")); 
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", data.readData("platformversion"));
		caps.setCapability("appPackage", data.readData("apppackage"));
		caps.setCapability("appActivity", data.readData("appactivity"));
		caps.setCapability("noReset", "true");
		
		
		try {
			
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			
		} 
			catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}
}
