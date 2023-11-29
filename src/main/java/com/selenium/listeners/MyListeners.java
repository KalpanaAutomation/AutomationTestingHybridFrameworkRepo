package com.selenium.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.utils.ExtentReporter;
import com.selenium.utils.Utilities;


public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	WebDriver driver;

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Project Tests started executing");
		extentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testCasName = result.getName();
		// System.out.println(testCasName+ " - started executing");
		extentTest = extentReport.createTest(testCasName);
		extentTest.log(Status.INFO, testCasName + " - started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testCaseName = result.getName();
		// System.out.println(testCaseName +" - Successfully executed");
		extentTest.log(Status.PASS, testCaseName + " - Successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testCaseName = result.getName();

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testCaseName + " - got failed");
		
		//we need driver
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destScreenshotPath = Utilities.takeScreenshot(driver,testCaseName);
		
		// And Attach Screenshot to Extent Report
		extentTest.addScreenCaptureFromPath(destScreenshotPath);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testCaseName = result.getName();
		// System.out.println(testCaseName+" - got Skipped");
		extentTest.log(Status.SKIP, testCaseName + " - got Skipped");
		extentTest.log(Status.INFO, result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		// System.out.println("All the test cases executed");
		extentTest.log(Status.INFO, "All the test cases executed");
		extentReport.flush();

	}
}
