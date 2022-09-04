package com.bikes.pages;

import java.io.IOException;
import java.util.Iterator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.bikes.baseClass.TestBase;
import com.bikes.utils.ExcelData;

public class PopularModelsPage extends TestBase {
	
	public String[] chennaiModels= new String[30];
	public String[] result= new String[30];
	public int count=0;

	By popularModels = By.xpath("/html/body/div[11]/div/div[1]/div[1]/div[1]/div[2]/ul/li[2]/div[2]/div[4]/ul/li");

	

	/*******************************************************************************
	 * Following method code is to extract all the 'Popular Car Models' in the Chennai 
	 *******************************************************************************/
	
	    public void getPopularModels() {
		List<WebElement> listOfModels = driver.findElements(popularModels);
		System.out.println("Number of popular models displayed are: " + listOfModels.size());
		Iterator<WebElement> i = listOfModels.iterator();
		while (i.hasNext()) {
			WebElement row = i.next();
			System.out.println(row.getText());
			chennaiModels[count]=row.getText();
			result[count]="PASS";
			count++;
		}
		
		try {
			logger.log(Status.INFO, "Excel reports are going to be generated for Userd Cars in Chennai");
			ExcelData.WriteExcelData(System.getProperty("user.dir")+"\\src\\test\\resources\\ExcelFiles\\Zigwheels.xlsx", chennaiModels, result);
			reportPass("The Excel report is generated for the Used Cars in Chennai");
		} catch (IOException e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}
	    
	    /*****************************************************************************
	     * Following method is used to verify the title of the page
	     *****************************************************************************/
	    
	    public void verifyPlace() {
			String validLoc;
			String validLocResult;
			String UsedCarsPlace = driver.findElement(By.id("usedcarttlID")).getText();
			if (UsedCarsPlace.contains("Chennai")) {
				validLoc = "The used car details are retreived for Chennai";
				validLocResult = "PASS";
				banner("--------------");
				System.out.println(validLoc + "\t" + validLocResult);
				banner("--------------");
			} else {
				validLoc = "The Used cars details are not retreived for Chennai";
				validLocResult = "FAIL";
				banner("--------------");
				System.out.println(validLoc + "\t" + validLocResult);
				banner("--------------");
				assertFalse(true);
			}
		}

}
