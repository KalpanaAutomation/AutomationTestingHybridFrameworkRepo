package com.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.base.Base;
import com.selenium.pages.HomePage;
import com.selenium.pages.RegisterPage;
import com.selenium.utils.Utilities;

public class RegisterTest extends Base {

	public WebDriver driver;

	public RegisterTest() {
		super();// we are bringing the code to read data from properties file

	}

	
	@BeforeMethod
	public void startUp() throws Exception {

		driver = initializeBrowserAndLaunchApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.selectMyAccount();
		homePage.selectRegisterOption();
	}

	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}

	@Test(priority = 1)
	public void registerAccountWithValidFields() throws Throwable {

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateRandomEmailAddress());
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickOnPrivacyPolicyAgree();
		registerPage.clickOnContinueButton();
		registerPage.verifyAccountHasBeenCreatedSuccessMessage();
		
		Utilities utl = new Utilities(driver);
		utl.takingScreenshot();
	}

	@Test(priority = 2)
	public void registerAccountWithValidFieldsAndSubscribeAsYes() throws Throwable {

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateRandomEmailAddress());
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickOnNewsLetterAsYes();
		registerPage.clickOnPrivacyPolicyAgree();
		registerPage.clickOnContinueButton();
		registerPage.verifyAccountHasBeenCreatedSuccessMessage();	
		
		Utilities utl = new Utilities(driver);
		utl.takingScreenshot();
	}

	@Test(priority = 3)
	public void registerAccountWithoutAgreeingToPrivacyPolicy() throws Throwable {

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateRandomEmailAddress());
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickOnNewsLetterAsYes();
		// Here we are not agreeing the Privacy Policy
		
		registerPage.clickOnContinueButton();
		registerPage.verifyYouMustAgreePolicyWarningMessage();
		
		Utilities utl = new Utilities(driver);
		utl.takingScreenshot();
		
	}

	@Test(priority = 4)
	public void registerAccountWithoutEnteringAnyFields() throws Throwable {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.clickOnContinueButton();
		registerPage.verifyYouMustAgreePolicyWarningMessage();
		registerPage.verifyFirstNameFieldErrorMessage();
		registerPage.verifyLastNameFieldErrorMessage();
		registerPage.verifyEmailFieldErrorMessage();
		registerPage.verifyTelePhoneFieldErrorMessage();
		registerPage.verifyPasswordFieldErrorMessage();
		
		Utilities utl = new Utilities(driver);
		utl.takingScreenshot();
	}
}
