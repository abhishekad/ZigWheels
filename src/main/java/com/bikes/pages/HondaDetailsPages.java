package com.bikes.pages;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.aventstack.extentreports.Status;
import com.bikes.baseClass.TestBase;
import com.bikes.utils.ExcelData;
public class HondaDetailsPages extends TestBase {
	//*[@id="headerNewNavWrap"]/div[2]/ul/li[5]/ul/li/div[2]/ul/li[5]/a
	By usedCar = By.xpath("//*[@id=\"headerNewNavWrap\"]/div[2]/ul/li[5]/a");
	By chennai = By.xpath("//*[@id=\"headerNewNavWrap\"]/div[2]/ul/li[5]/ul/li/div[2]/ul/li[5]/a");
	By ViewMore = By.xpath("//span[contains(text(),'View More Bikes')]");

	By BikeNames = By.xpath(prop.getProperty("BikeNames_Xpath"));
	By BikePrices = By.xpath(prop.getProperty("BikePrices_Xpath"));
	By BikeLaunch = By.xpath(prop.getProperty("BikeLaunch_Xpath"));
	int count = 0;

	public String[] bNames = new String[30];
	public String[] bPrice = new String[30];
	public String[] bLaunch = new String[30];
	public String[] result = new String[30];
	int count1 = 0;
	LaunchPage launchPage = new LaunchPage();

	/*************************************************************************
	 * Here we are clicking on viewmore button
	 *************************************************************************/
	public void clickViewMore(int x, int y) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(" + x + "," + y + ");");

		} catch (Exception e) {
			e.printStackTrace();
		}
		waitFor(3);
		driver.findElement(ViewMore).click();
	}

	/*************************************************************************************
	 * This method is used to click on the 'Upcoming Bikes'
	 *************************************************************************************/

	public void displayUpcomingBikesInIndia() {
		List<WebElement> bikeNames = driver.findElements(BikeNames);
		List<WebElement> bikePrices = driver.findElements(BikePrices);
		List<WebElement> bikeLaunch = driver.findElements(BikeLaunch);

		System.out.println("\n********************************************************************\n");
		System.out.println("\n The Upcoming Honda bikes details are as follows : \n");
		count = bikeNames.size();
		String priceTxt;
		
		try {
			for (int i = 0; i < count; i++) {
				priceTxt = bikePrices.get(i).getText();
				float price = Float.parseFloat(priceTxt.replaceAll("Rs. ", "").replaceAll(" Lakh", ""));
				if (price < 4) {
					System.out.println(bikeNames.get(i).getText() + "\t" + bikePrices.get(i).getText() + "\t"
							+ bikeLaunch.get(i).getText());

					/*********************************************************************************
					 * Below is code is for write bikes details in excel sheet
					 **********************************************************************************/

					bNames[count1] = bikeNames.get(i).getText();
					bPrice[count1] = bikePrices.get(i).getText();
					bLaunch[count1] = bikeLaunch.get(i).getText();

					result[count1] = "PASS";
					count1++;

				}

			}

			logger.log(Status.INFO, "Excel reports are going to be generated for Userd Cars in Chennai");
			ExcelData.WriteExcelData(
					System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelFiles\\Zigwheels.xlsx", bNames,
					bPrice, bLaunch, result);
			reportPass("The Excel report is generated for the Upcoming bikes in India");

		} catch (Exception e) {
			e.getMessage();
			reportFail(e.getMessage());
		}

		System.out.println("\n********************************************************************\n");
		System.out.println("Above bike prices are less than 4 lakhs");
		System.out.println("\n********************************************************************\n");
	}

	/*************************************************************************************
	 * This method is used to click on the option - 'Chennai'
	 *************************************************************************************/

	public void selectChennai() {
		try {
			launchPage.closeSignUpPopup();
			logger.log(Status.INFO, "The Curser is going to hover over 'Used Cars' option");
			Actions actions = new Actions(driver);
			WebElement uCars = driver.findElement(usedCar);
			actions.moveToElement(uCars).build().perform();
			reportPass("Curser is moved to 'User Cars' top menu");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebElement ch = driver.findElement(chennai);
			logger.log(Status.INFO, "'Chennai' option is going to be clicked");
			ch.click();
			reportPass("'Chennai' option is clicked");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}
	

    /*****************************************************************************
     * Following method is used to verify the title of the page
     *****************************************************************************/
	
	public void verifyPage()
	{
		verifyPageTitle("title");
	}

	
	
	
}