package eBayShopping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import codeData.ID;
import eBayShopping.ScreenShot;
import eBayShopping.TestData;

public class eBayApp {
	
	
	// Variables to be used across methods in the code
	TestData data=null;
	DesiredCapabilities caps = null;
	AndroidDriver<MobileElement> driver = null;
	ScreenShot ss=null;
	JavascriptExecutor js = null;
	
	
	// Primary Setup
	@Test(priority=0)
	public void setup() throws IOException {
		
		ss = new ScreenShot();
		data = new TestData();
		caps = new DesiredCapabilities();
		
		
		//Device details on which the application is installed is fetched from testdata sheet
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
	@Test(dependsOnMethods={"purchaseProduct"})
	public void terminate() {
		
		driver.closeApp();
		driver.close();
	}
	
	
	
	//validating the login function via the login option in hamburger menu
	@Test (priority=99)
	public void loginviaMenu() throws IOException {
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.menu).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.signoption).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.username).click();
		driver.findElement(ID.username).sendKeys(data.readData("username"));
		driver.findElement(ID.password).click();
		driver.findElement(ID.password).sendKeys(data.readData("password"));
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.signinbutton).click();
		ss.takeScreenShot((AndroidDriver) driver);
		
	}
	
	
	
	//Validating logout functionality of the app
	@Test (priority=98)
	public void logoutAccount() {
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.menu).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.signoption).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.signoutbutton).click();
		ss.takeScreenShot((AndroidDriver) driver);
	}
	
	
	
	//Validating the login function through the 'Sign In' option readily available in the homepage of the app
	@Test(priority=1)
	public void loginviaHome() throws IOException {
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.signinbutton).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.username).click();
		driver.findElement(ID.username).sendKeys(data.readData("username"));
		driver.findElement(ID.password).click();
		driver.findElement(ID.password).sendKeys(data.readData("password"));
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.signinbutton).click();
		ss.takeScreenShot((AndroidDriver) driver);
		
	}
	
	
	
	//Method block performs a search in the app with a user defined search text defined in the test data
	@Test(dependsOnMethods={"loginviaHome"})
	public void searchProduct() throws IOException {
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.searchicon).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.searchbox).click();
		driver.findElement(ID.searchbox).sendKeys(data.readData("searchtext"));
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.searchproduct).click();
		ss.takeScreenShot((AndroidDriver) driver);
		
	}
	
	
	
	//Scroll downwards in the app screen till object text matches '1701 Nike Air Presto' and selects that product
	@Test(dependsOnMethods={"searchProduct"})
	public void chooseProduct() {
		
		ss.takeScreenShot((AndroidDriver) driver);
		js = (JavascriptExecutor)driver;
		HashMap<String, String> scrollObjects = new HashMap<String, String>();
		scrollObjects.put("direction", "down");
		scrollObjects.put("text", "1701 Nike Air Presto");
		js.executeScript("mobile: scrollTo", scrollObjects);
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(By.partialLinkText("1701 Nike Air Presto")).click();
		ss.takeScreenShot((AndroidDriver) driver);
		
	}
	
	
	
	//Click the image of the selected product switch to landscape view for 5 seconds and switch back to portrait
	@Test(dependsOnMethods={"chooseProduct"})
	public void analyzeProduct() {
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.productimage).click();
		
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
	
	
	
	//method to perform scroll on app screen
	public void swipeDown() {
		
		HashMap<String, Double> scrollDown = new HashMap<String, Double>();
        scrollDown.put("startX", 0.50);
        scrollDown.put("startY", 0.95);
        scrollDown.put("endX", 0.50);
        scrollDown.put("endY", 0.01);
        scrollDown.put("duration", 3.0);
        js.executeScript("mobile: swipe", scrollDown);
		
	}
	
	
	
	//Clicks on 'Buy it now' button --> which will navigate to purchase order screen; choose 'Commit Buy'
	@Test(dependsOnMethods={"analyzeProduct"})
	public void purchaseProduct() {
		
		
		//Screen is scrolled down as 'Buy it now' button is not visible in Samsung Galaxy S4 and other small screens
		swipeDown();	
	        
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.buy).click();
		
		ss.takeScreenShot((AndroidDriver) driver);
		driver.findElement(ID.commit).click();
		ss.takeScreenShot((AndroidDriver) driver);
		
	}
	
	
	//Flow terminated at this point as the forthcoming screens involve real time payment mechanisms and logics

}
