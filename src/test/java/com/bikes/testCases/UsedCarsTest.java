package com.bikes.testCases;

import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.bikes.baseClass.TestBase;
import com.bikes.pages.HondaDetailsPages;
import com.bikes.pages.LaunchPage;
import com.bikes.pages.PopularModelsPage;
//import com.bikes.utils.ExtentReportManager;

public class UsedCarsTest extends TestBase {
	
	HondaDetailsPages hondaDetails = new HondaDetailsPages();
	PopularModelsPage carModels = new PopularModelsPage();
	
	 /*************************************************************************************
     * This method is used to call the 'selectChennai' method
	 * @throws MalformedURLException 
     *************************************************************************************/

	@Test(priority=6,groups = "Smoke Test")
	public void selectChennai() throws MalformedURLException {
	logger = report.createTest("ZigWheels Used Cars in Chennai - Extracting popular models");
		setupDriver();
		waitFor(2);
	hondaDetails.selectChennai();
		
	}
	
	/*************************************************************************************
     * This method is used to call the 'getPopularModels' method
     *************************************************************************************/

	@Test(dependsOnMethods = "selectChennai", groups = "Smoke Test")
	public void getPopularModels() {
		
		
		carModels.getPopularModels();
	}
	
	/***************************************************************************************
	 * This method is used to verify the UsedCars page
	 ***************************************************************************************/
	
	@Test(dependsOnMethods = "getPopularModels", groups = "Regression Test")
	public void verifyChennai() {
		
		carModels.verifyPlace();
	}
	
	 /*************************************************************************************
     * This method is used to close the 'Browser'
     *************************************************************************************/

	 @AfterClass(groups = "Smoke Test")
	public void browserQuit() {
		report.flush();
		driver.quit();
	}
}
