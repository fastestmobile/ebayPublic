package eBayShopping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class eBayApp {
	
	TestData data=null;
	DesiredCapabilities caps = null;
	AppiumDriver<MobileElement> driver = null;
	ScreenShot ss=null;
	
	@BeforeSuite
	public void setup() throws IOException {
		
		ss = new ScreenShot();
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
	
	@AfterSuite
	public void terminate() {
		
		driver.closeApp();
		driver.close();
	}
	
	@Test (priority=0)
	public void loginviaMenu() throws IOException {
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/home")).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/textview_sign_out_status")).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/edit_text_username")).click();
		driver.findElement(By.id("com.ebay.mobile:id/edit_text_username")).sendKeys(data.readData("username"));
		driver.findElement(By.id("com.ebay.mobile:id/edit_text_password")).click();
		driver.findElement(By.id("com.ebay.mobile:id/edit_text_password")).sendKeys(data.readData("password"));
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/button_sign_in")).click();
		
	}
	
	@Test (priority=1)
	public void logoutAccount() {
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/home")).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/textview_sign_in_status")).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/button_sign_out")).click();
	}
	
	@Test(dependsOnMethods={"loginviaMenu","logoutAccount"})
	public void loginviaHome() throws IOException {
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/button_sign_in")).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/edit_text_username")).click();
		driver.findElement(By.id("com.ebay.mobile:id/edit_text_username")).sendKeys(data.readData("username"));
		driver.findElement(By.id("com.ebay.mobile:id/edit_text_password")).click();
		driver.findElement(By.id("com.ebay.mobile:id/edit_text_password")).sendKeys(data.readData("password"));
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/button_sign_in")).click();
		
	}
}
