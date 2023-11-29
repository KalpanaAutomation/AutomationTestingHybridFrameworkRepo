package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.base.Base;
import com.selenium.pages.HomePage;
import com.selenium.pages.LoginPage;
import com.selenium.utils.Utilities;

public class LoginTest extends Base {

public WebDriver driver;
	
	public LoginTest() {
		super();
	}
	
	
	@BeforeMethod
	public void startUp(){
		driver = initializeBrowserAndLaunchApplicationURL(prop.getProperty("browserName"));	
		HomePage homePage = new HomePage(driver);
		homePage.selectMyAccount();
		homePage.selectLoginOption();	
	}

	
	//reading from ExcelFile
	@DataProvider(name = "supplyTestData")
	public Object[][] supplyData() {
		Object[][] data = Utilities.readDataFromExcel("LoginData");
		return data;
	}
	
	@Test(dataProvider  = "supplyTestData")
	public void loginWithValidCredentials(String email, String password) {	
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAndPassword(email,password);
		loginPage.clickOnLoginButton();
		loginPage.verifyLoginSuccessfullMessage();		
	}

	@Test(priority = 2)
	public void loginWithValidUsernameAndWrongPassword() {
		LoginPage loginPage =  new LoginPage(driver);
		loginPage.enterEmailAndPassword(prop.getProperty("validEmail"), "12121");
		loginPage.clickOnLoginButton();
		loginPage.verifyLoginErrorMessage();
	}

	@Test(priority = 3)
	public void loginWithInvalidUsernameAndCorrectPassword() {
		LoginPage loginPage =  new LoginPage(driver);
		loginPage.enterEmailAndPassword("123456789@gmail.com", prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		loginPage.verifyLoginErrorMessage();
	}

	@Test(priority = 4)
	public void loginWithNoCredentials() {
		LoginPage loginPage =  new LoginPage(driver);
		loginPage.clickOnLoginButton();
		loginPage.verifyLoginErrorMessage();
	}
	
	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}
}
