package com.bikes.baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.bikes.utils.DateUtils;
import com.bikes.utils.ExtentReportManager;


public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public static ExtentTest logger;

	SoftAssert softAssert = new SoftAssert();

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\ObjectRepository\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}

	public static WebDriver setupDriver() throws MalformedURLException {
//		System.out.println(
//				"\n-------------------------------------------------------------------------------------------------\n");
//		System.out.println("\n1)To choose chrome brower for execution ,type-'Chrome'");
//		System.out.println("\n2)To choose Mozila FireFox brower for execution ,type-'firefox'");
//		System.out.println("\n3)To choose edge brower for execution ,type-'edge'");
//		System.out.println("\n4)To exit the execution ,type-'exit'");
//		System.out.println(
//				"\n-------------------------------------------------------------------------------------------------");
//		@SuppressWarnings("resource")
//		Scanner scan = new Scanner(System.in);
//		String browser = scan.next();
//		System.out.println(
//				"-------------------------------------------------------------------------------------------------\n");
		
		String browser = prop.getProperty("Browser");
		
		if (browser.equalsIgnoreCase("chrome")) {
			// To launch in Chrome browser
			//This is the change I am Making to update chrome driver
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Abhishek Dongare\\Downloads\\chromedriver_win32\\chromedriver.exe");
					//System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);// options
		}
		
		//To launch remote chrome browser "Selenium Grid"
		else if(browser.equalsIgnoreCase("remote chrome")) {
			
			DesiredCapabilities desired = new DesiredCapabilities();
			desired.setBrowserName("chrome");
			desired.setPlatform(Platform.WINDOWS);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.merge(desired);
			//driver = new ChromeDriver(options);// options
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);
		
		}
		
		// To launch in Firefox browser
		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(new FirefoxProfile());
			options.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(options);
			
		}
		
		//To launch remote firefox browser "Selenium grid"
		else if(browser.equalsIgnoreCase("remote firefox")) {
			DesiredCapabilities desired = new DesiredCapabilities();
			desired.setBrowserName("firefox");
			desired.setPlatform(Platform.WINDOWS);
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--disable-notifications");
			options.merge(desired);
			//driver = new ChromeDriver(options);// options
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);
			
		}
		// To launch in Edge browser
		else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\msedgedriver.exe");
			EdgeOptions options = new EdgeOptions();
			// options.addArguments("--disable-notifications");
			driver = new EdgeDriver(options);
		}
		else if(browser.equalsIgnoreCase("exit")) {
			System.out.println("\n-------------------------Execution terminated----------------------\n");
			System.exit(0);
		}
		else {
			System.out.println("\n--------------------------Invalid selection of browser-------------------------\n");
			System.exit(0);
		}
		

		System.out.println(
				"\n-------------------------------------------------------------------------------------------------\n");
		// driver = DriverSetup.getWebDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		return driver;
	}
	
	public void verifyPageTitle(String pageTitle) {
		try {
			String actualTitle = driver.getTitle();
			logger.log(Status.INFO, "Actual Title is : " + actualTitle);
			logger.log(Status.INFO, "Expected tile is : " + prop.getProperty(pageTitle));
			Assert.assertEquals(actualTitle, prop.getProperty(pageTitle));
			banner("---------------------");
			System.out.println("The Title of the page is : "+actualTitle);
			banner("---------------------");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public void banner(String value)
	{
		System.out.println("\n------------------------------------------------------"+value+"--------------------------------------------\n");
	}

	/************************************************
	 * Assertion Functions
	 ****************************************/

	public void assertTrue(boolean flag) {
		softAssert.assertTrue(flag);
	}

	public void assertFalse(boolean flag) {
		softAssert.assertFalse(flag);
	}

	public void assertequals(String actual, String expected) {
		softAssert.assertEquals(actual, expected);
	}

	public void waitFor(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reporting Functions
	 */
	public static void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShots();
		Assert.fail();
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	/**********************************************************************************************
	 * Capturing ScreenShots
	 *********************************************************************************************/

	public static void takeScreenShots() {
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File source = takeScreenshot.getScreenshotAs(OutputType.FILE);

		File dest = new File(
				System.getProperty("user.dir") + "\\ScreenShot\\ScreenShots" + DateUtils.getTimeStamp() + ".png");

		try {
			FileUtils.copyFile(source, dest);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "\\ScreenShot\\ScreenShots" + DateUtils.getTimeStamp() + ".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
