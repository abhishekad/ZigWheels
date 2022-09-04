package com.bikes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.bikes.baseClass.TestBase;
import com.bikes.utils.ExcelData;

public class LoginPage extends TestBase {

	By email = By.xpath("//input[@id='identifierId']");
	By next1 = By.xpath(
			"//*[@id='identifierNext']/div/button/span");
	By pass = By.name("password");
	By showpass = By.xpath("//div[contains(text(),'Show password')]");
	By next2 = By.xpath(
			"//*[@id='passwordNext']/div/button/span");

	By wrnpass = By.xpath(
			"//body/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]");
	By wrnEmail = By.xpath(
			"//body/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/span[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]");

	/**********************************************************************************************
	 * Following method is used to enter invalid login details('Password')
	 **********************************************************************************************/
	
	public void enterLoginDetails1() {
		
		logger.log(Status.INFO, "Invalid Password is entered");
		String[] phraseData = null;

		try {
			phraseData = ExcelData
					.readExcel(System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelFiles\\Zigwheels.xlsx");

		} catch (Exception e) {
			e.getMessage();
		}

		driver.findElement(email).sendKeys(phraseData[0]);
		driver.findElement(next1).click();
		waitFor(3);

		WebElement password = driver.findElement(pass);
		password.sendKeys(phraseData[1]);
		driver.findElement(showpass).click();

		driver.findElement(next2).click();

		String ErrTxt = driver.findElement(wrnpass).getText();
		System.out.println("\n-------------------------------------------------------------------------------------------------------------------------\n");
		System.out.println("\tThe Error Message is : " + ErrTxt);
		System.out.println("\n-------------------------------------------------------------------------------------------------------------------------\n");
		takeScreenShots();
		waitFor(2);
	}
	
	
	
	/**********************************************************************************************
	 * Following method is used to enter invalid login details('Email')
	 **********************************************************************************************/
	

	public void enterLoginDetails2() {
		
		logger.log(Status.INFO, "Invalid email is entered");
		String[] phraseData = null;

		try {
			phraseData = ExcelData
					.readExcel(System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelFiles\\Zigwheels.xlsx");

		} catch (Exception e) {
			e.getMessage();
		}
		driver.findElement(email).sendKeys(phraseData[2]);
		waitFor(3);
		driver.findElement(next1).click();

		String ErrTxt = driver.findElement(wrnEmail).getText();
		System.out.println("\n-------------------------------------------------------------------------------------------------------------------------\n");
		System.out.println("\tThe Error Message is : " + ErrTxt);
		System.out.println("\n-------------------------------------------------------------------------------------------------------------------------\n");
		takeScreenShots();
		waitFor(2);
	}

}
