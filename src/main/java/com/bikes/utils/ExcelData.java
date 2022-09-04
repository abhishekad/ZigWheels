package com.bikes.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bikes.baseClass.TestBase;

public class ExcelData extends TestBase {
	public static File file;
	public static FileInputStream work_file;
	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public static XSSFRow row;
	public static FileOutputStream result_file;
     
	/***********************************************************************************
	 * Following method is used to write 'Bike details' in the 'Excel Sheet'
	 ***********************************************************************************/
	
	public static void WriteExcelData(String filePath, String[] bikeNames, String[] bikePrice, String[] bikeLaunch,
			String[] result) throws IOException {

		try {
			file = new File(filePath);
			work_file = new FileInputStream(file);
			workbook = new XSSFWorkbook(work_file);
			worksheet = workbook.getSheet("BikeDetails");
			row = worksheet.getRow(worksheet.getLastRowNum());
			int rowCount = bikeNames.length;

			for (int i = 0; i < rowCount; i++) {

				row = worksheet.createRow(i + 1);

				row.createCell(0).setCellValue(bikeNames[i]);
				row.createCell(1).setCellValue(bikePrice[i]);
				row.createCell(2).setCellValue(bikeLaunch[i]);
				row.createCell(3).setCellValue(result[i]);

			}

			work_file.close();
			result_file = new FileOutputStream(file);
			workbook.write(result_file);
			result_file.close();

		} catch (FileNotFoundException e) {
			System.out.println("The required file is not available");
			e.printStackTrace();
		}
	}

	

	/*******************************************************************************
	 *Following method is used to write ' Popular Car Models Details' in the 'Excel Sheet'
	 *******************************************************************************/
	
	
	public static void WriteExcelData(String filePath, String[] popularModels, String[] result) throws IOException {

		try {
			file = new File(filePath);
			work_file = new FileInputStream(file);
			workbook = new XSSFWorkbook(work_file);
			worksheet = workbook.getSheet("CarDetails");
			row = worksheet.getRow(worksheet.getLastRowNum());
			int rowCount = popularModels.length;

			for (int i = 0; i < rowCount; i++) {

				row = worksheet.createRow(i + 1);
				row.createCell(0).setCellValue(popularModels[i]);
				row.createCell(1).setCellValue(result[i]);

			}
			work_file.close();
			result_file = new FileOutputStream(file);
			workbook.write(result_file);
			result_file.close();

		}

		catch (FileNotFoundException e) {
			System.out.println("The required file is not available in the given location");
			e.printStackTrace();
		}

	}
	
	/****************************************************************************
	Following method is used to read 'Invalid Login Data' from the 'Excel Sheet'
	*****************************************************************************/

	public static String[] readExcel(String filepath) {

		String[] data = new String[3];
		try {
			file = new File(filepath);
			work_file = new FileInputStream(file);
			workbook = new XSSFWorkbook(work_file);
			worksheet = workbook.getSheet("LoginDetails");
			row = worksheet.getRow(worksheet.getLastRowNum());

			for (int i = 0; i < 3; i++) {
				data[i] = String.valueOf(row.getCell(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}
}
