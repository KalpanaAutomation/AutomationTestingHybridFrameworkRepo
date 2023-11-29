package com.selenium.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReports = new ExtentReports();
		
		File extentReportPath = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportPath);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Selenium Project Report");
		sparkReporter.config().setDocumentTitle("Selenium Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReports.attachReporter(sparkReporter);
		
		extentReports.setSystemInfo("Application URL","https://tutorialsninja.com/demo/");
		extentReports.setSystemInfo("Browser Name", "Chrome");
		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReports;
			
	}
}




