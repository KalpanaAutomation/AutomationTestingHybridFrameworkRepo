package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.base.Base;
import com.selenium.pages.SearchPage;

public class SearchTest extends Base {

	public WebDriver driver;
	
	SearchTest(){
		super();//we are bringing the code to read data from properties file
	}
	
	@BeforeMethod
	public void startUp(){
		
		driver = initializeBrowserAndLaunchApplicationURL(prop.getProperty("browserName"));
	}
	
	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}	
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() throws Exception {
		
		SearchPage searchPage = new SearchPage(driver);	
		searchPage.enterProductAndClickOnSearchButton(prop.getProperty("productName"));
		searchPage.verifyValidProductDetailOnSearchPage();
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() throws Exception{
		
		SearchPage searchPage = new SearchPage(driver);	
		searchPage.enterProductAndClickOnSearchButton(prop.getProperty("invalidProduct"));
		searchPage.verifyNoProductDeatilMessage();
	}	
	
	@Test(priority=3, dependsOnMethods = {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() throws Exception{
		
		SearchPage searchPage = new SearchPage(driver);	
		searchPage.enterProductAndClickOnSearchButton("");
		searchPage.verifyNoProductDeatilMessage();		
	}
}













