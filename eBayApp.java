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
	
	TestData data=null;
	DesiredCapabilities caps = null;
	AppiumDriver<MobileElement> driver = null;
	ScreenShot ss=null;
	JavascriptExecutor js = null;
	
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
	
	@Test(description="perform a search with the keyword")
	public void searchProduct() throws IOException {
		
		driver.findElement(By.id("com.ebay.mobile:id/search_box")).click();
		driver.findElement(By.id("com.ebay.mobile:id/search_box")).sendKeys(data.readData("searchtext"));
		driver.findElement(By.xpath("/html/body/div[5]/div/div/div/header/table/tbody/tr/td[3]/form/table/tbody/tr/td/div[2]/ul/li/a/b[2]")).click();
		
	}
	
	@Test(successPercentage=94)
	public void chooseProduct() {
		
		js = (JavascriptExecutor)driver;
		HashMap<String, String> scrollObjects = new HashMap<String, String>();
		scrollObjects.put("direction", "down");
		scrollObjects.put("text", "1701 Nike Air Presto");
		js.executeScript("mobile: swipe", scrollObjects);
		driver.findElement(By.partialLinkText("1701 Nike Air Presto")).click();
		
	}
	
	@Test(expectedExceptions = {MalformedURLException.class, InterruptedException.class})
	public void analyzeProduct() {
		
		driver.findElement(By.id("com.ebay.mobile:id/image_view_single_image")).click();
		driver.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
		
	}
	
	@Test
	public void purchaseProduct() {
		
		driver.findElement(By.id("com.ebay.mobile:id/button_bin")).click();
		driver.findElement(By.id("com.ebay.mobile:id/take_action")).click();
		
	}

}
