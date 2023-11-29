package com.selenium.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {
	WebDriver driver;

	public Utilities(WebDriver driver) {
		this.driver = driver;
	}

	public static void timestamp() {

	}

	// code to take screenshot
	public void takingScreenshot() throws Throwable {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dst = new File(System.getProperty("user.dir") + "\\screenshots\\RegisterPageScreenshot.png");
		FileUtils.copyFile(src, dst);
	}

	// Take Screenshot for attaching it in extent report
	public static String takeScreenshot(WebDriver driver, String testCaseName) {
		// Add Screenshot when test case failed
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcScreenshotPath = screenshot.getScreenshotAs(OutputType.FILE);
		String destScreenshotPath = System.getProperty("user.dir") + "\\screenshots\\" + testCaseName + ".png";
		try {
			FileUtils.copyFile(srcScreenshotPath, new File(destScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destScreenshotPath;
	}


	// to generate Random email address
	public static String generateRandomEmailAddress() {
		Random rnd = new Random();
		int number = rnd.nextInt(99999);
		String emailId = "Subhash" + number + "@gamil.com";
		System.out.println(emailId);
		return emailId;
	}

	// Read data from excel
	public static Object[][] readDataFromExcel(String sheetName) {
		XSSFWorkbook wb = null;
		String path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\selenium\\testData\\ExcelTestData.xlsx";
		try {
			FileInputStream fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = wb.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rowCount][colCount];

		for (int i = 0; i < rowCount; i++) {
			XSSFRow row = sheet.getRow(i + 1);
			for (int j = 0; j < colCount; j++) {
				XSSFCell cell = row.getCell(j);

				switch (cell.getCellType()) {
				case STRING:
					System.out.println(cell.getStringCellValue());
					data[i][j] = cell.getStringCellValue();
					break;

				case NUMERIC:
					System.out.println(cell.getNumericCellValue());
					data[i][j] = cell.getNumericCellValue();
				}

			}
		}
		return data;
	}

}
