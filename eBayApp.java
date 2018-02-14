package eBayShopping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class eBayApp {
	
	// Variables to be used across methods in the code
	TestData data=null;
	DesiredCapabilities caps = null;
	AppiumDriver<MobileElement> driver = null;
	ScreenShot ss=null;
	JavascriptExecutor js = null;
	
	// Primary Setup
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
			
			//Android device is connected to the localhost 
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			
			} 
			catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
		
		// setting implicit wait for 15 seconds
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
	}
	
	// Terminate the instances to free up memory
	@AfterSuite
	public void terminate() {
		
		driver.closeApp();
		driver.close();
	}
	
	//validating the login function via the login option in hamburger menu
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
		ss.takeScreenShot((AndroidDriver) driver);
		
	}
	
	//Validating logout functionality of the app
	@Test (priority=1)
	public void logoutAccount() {
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/home")).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/textview_sign_out_status")).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/button_sign_out")).click();
		ss.takeScreenShot((AndroidDriver) driver);
	}
	
	
	//Validating the login function through the 'Sign In' option readily available in the homepage of the app
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
		ss.takeScreenShot((AndroidDriver) driver);
		
	}
	
	//Method block performs a search in the app with a user defined search text defined in the test data
	@Test(description="perform a search with the keyword")
	public void searchProduct() throws IOException {
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/search_box")).click();
		driver.findElement(By.id("com.ebay.mobile:id/search_box")).sendKeys(data.readData("searchtext"));
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div/header/table/tbody/tr/td[3]/form/table/tbody/tr/td/div[2]/ul/li/a/b[2]")).click();
		ss.takeScreenShot((AndroidDriver) driver);
		
	}
	
	//Scroll downwards in the app screen till object text matches '1701 Nike Air Presto' and selects that product
	@Test(successPercentage=94)
	public void chooseProduct() {
		
		ss.takeScreenShot((AndroidDriver) driver);
		js = (JavascriptExecutor)driver;
		HashMap<String, String> scrollObjects = new HashMap<String, String>();
		scrollObjects.put("direction", "down");
		scrollObjects.put("text", "1701 Nike Air Presto");
		js.executeScript("mobile: swipe", scrollObjects);
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.partialLinkText("1701 Nike Air Presto")).click();
		ss.takeScreenShot((AndroidDriver) driver);
		
	}
	
	//Click the image of the selected product switch to landscape view for 5 seconds and switch back to portrait
	@Test(expectedExceptions = {MalformedURLException.class, InterruptedException.class})
	public void analyzeProduct() {
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/image_view_single_image")).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
		ss.takeScreenShot((AndroidDriver) driver);
		
	}
	
	//Clicks on 'Buy it now' button --> which will navigate to purchase order screen; choose 'Commit Buy'
	@Test
	public void purchaseProduct() {
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/button_bin")).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.id("com.ebay.mobile:id/take_action")).click();
		ss.takeScreenShot((AndroidDriver) driver);
		
	}
	
	//Flow terminated at this point as forthcoming screens involve real time payment mechanisms and logics

}
