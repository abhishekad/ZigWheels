package com.bikes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;
import com.bikes.baseClass.TestBase;

public class SelectManufacturerPage extends TestBase {

	By manufacturer = By.id("makeId");
	
	/************************************************************************
	 * Following method is used to select  Manufacturer - 'Honda'
	 ************************************************************************/
	
	public void selectHonda() {
		
		logger.log(Status.INFO, "This method is used to select Manufacturer 'Honda'");
		Select hBikes = new Select(driver.findElement(manufacturer));
		hBikes.selectByVisibleText("Honda");
		waitFor(3);
	}
   
	

}
