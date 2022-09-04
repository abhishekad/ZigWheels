package com.bikes.testCases;

import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.bikes.baseClass.TestBase;
import com.bikes.pages.HondaDetailsPages;
import com.bikes.pages.LaunchPage;
import com.bikes.pages.SelectManufacturerPage;
import com.bikes.utils.ExtentReportManager;

public class BikeDetailsTest extends TestBase {

    LaunchPage launchPage = new LaunchPage();
    SelectManufacturerPage selectManufacturer = new SelectManufacturerPage();
    HondaDetailsPages hondaDetails=new HondaDetailsPages();
    //BikeNames_Xpath=//strong
    		//BikePrices_Xpath=//div[contains(text(),'Lakh') and @class='clr-bl' 'b fnt-15']
    		//BikeLaunch_Xpath=//div[contains(text(),'Exp. Launch ')]

    /***********************************************************************************
     * This method is used for invoking driver
     * @throws MalformedURLException 
     ***********************************************************************************/
    
    @Test(priority=0, groups = "Smoke Test")
    public void invokedriver() throws MalformedURLException {
		logger = report.createTest("ZigWheels Upcoming Bikes in India - Extracting Honda models");
          setupDriver();
          waitFor(3);
    }
    
    /*************************************************************************************
     * This method is used to call the 'clickUpComingBikes' method
     *************************************************************************************/
    @Test(priority=1, groups = "Smoke Test")
    public void clickUpComingBikes() {
    	launchPage.closeSignUpPopup();
    	//logger = report.createTest("ZigWheels Upcoming Bikes in India - Extracting Honda models");
    	//launchPage.closeSignUpPopup();
        launchPage.clickUpcomingBikes();
        waitFor(3);
        
    }

    /*************************************************************************************
     * This method is used to call the 'selectHonda' method
     *************************************************************************************/
    
   // @Test(dependsOnMethods = "clickUpComingBikes", groups = "Smoke Test")
    @Test(priority=2, groups = "Smoke Test")
    public void selectHondaBikes() {
        selectManufacturer.selectHonda();
        
    }
    
    /*************************************************************************************
     * This method is used to call the 'clickViewMore' method
     *************************************************************************************/
    
  //  @Test(dependsOnMethods = "selectHondaBikes", groups = "Smoke Test")
    @Test(priority=3, groups = "Smoke Test")
 public void clickViewMore() {
	
	 hondaDetails.clickViewMore(579,1325);
	   
 }

   /*************************************************************************************
    * This method is used to call the 'displayUpComingBikesInIndia' method
    *************************************************************************************/
    
    @Test(dependsOnMethods = "clickViewMore", groups = "Smoke Test")
    public void displayUpcomingBikes() {
    	   
        hondaDetails.displayUpcomingBikesInIndia();
    }
    
    
    
    @Test(dependsOnMethods = "displayUpcomingBikes", groups = "Regression Test")
    public void verifyHondaPage()
    {
    	hondaDetails.verifyPage();
    	
       }
    
    
    /*************************************************************************************
     * This method is used to close the 'Browser'
     *************************************************************************************/
    
    @AfterClass(groups = "Smoke Test")
    public void quitBrowser() {
    	report.flush();
        driver.quit();
    }
}