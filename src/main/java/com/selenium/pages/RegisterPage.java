package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.selenium.utils.Utilities;

public class RegisterPage {

	WebDriver driver;
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterFirstName(String firstName) {
		driver.findElement(By.id("input-firstname")).sendKeys(firstName);
	}
	
	
	public void enterLastName(String lastName) {
		driver.findElement(By.id("input-lastname")).sendKeys(lastName);
	}

	public void enterEmail(String email) {
		driver.findElement(By.id("input-email")).sendKeys(email);
		
	}

	public void enterTelephone(String telePhone) {
		driver.findElement(By.name("telephone")).sendKeys(telePhone);
	}

	public void enterPassword(String password) {
		driver.findElement(By.id("input-password")).sendKeys(password);
	}

	public void enterConfirmPassword(String confirmPassword) {
		driver.findElement(By.id("input-confirm")).sendKeys(confirmPassword);
	}

	public void clickOnPrivacyPolicyAgree() {
		driver.findElement(By.xpath("//input[@name='agree']")).click();
	}

	public void clickOnContinueButton() {
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
	}

	public void verifyAccountHasBeenCreatedSuccessMessage() {
		String accountSuccessMessage = driver.findElement(By.xpath("//h1[contains(text(),'Account Has Been Created')]"))
				.getText();
		Assert.assertTrue(accountSuccessMessage.contains("Account Has Been Created"),
				"Account has not been created!!!");
	}

	public void clickOnNewsLetterAsYes() {
		driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
	}

	public void verifyYouMustAgreePolicyWarningMessage() {
		String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarning = "Warning: You must agree to the Privacy Policy!";
		Assert.assertEquals(actualWarning, expectedWarning, "Privacy Policy error message did not get displayed");
	}

	public void verifyFirstNameFieldErrorMessage() {
		String actualFirstNameWarning = driver
				.findElement(By.xpath("//input[@id='input-firstname']//following-sibling::div")).getText();
		System.out.println(actualFirstNameWarning);
		Assert.assertTrue(actualFirstNameWarning.contains("First Name must be between 1 and 32 characters!"),
				"First name error message is not displayed");
	}

	public void verifyLastNameFieldErrorMessage() {
		String actualLastNameWarning = driver
				.findElement(By.xpath("//input[@id='input-lastname']//following-sibling::div")).getText();
		System.out.println(actualLastNameWarning);
		Assert.assertTrue(actualLastNameWarning.contains("Last Name must be between 1 and 32 characters!"),
				"Last name error message is not displayed");
	}

	public void verifyEmailFieldErrorMessage() {
		String actualEmailAddressWarning = driver
				.findElement(By.xpath("//input[@id='input-email']//following-sibling::div")).getText();
		System.out.println(actualEmailAddressWarning);
		Assert.assertTrue(actualEmailAddressWarning.contains("E-Mail Address does not appear to be valid!"),
				"Email address error message is not displayed");
	}

	public void verifyTelePhoneFieldErrorMessage() {
		String actualtelephoneWarning = driver
				.findElement(By.xpath("//input[@id='input-telephone']//following-sibling::div")).getText();
		System.out.println(actualtelephoneWarning);
		Assert.assertTrue(actualtelephoneWarning.contains("Telephone must be between 3 and 32 characters!"),
				"Telephone address error message is not displayed");
	}

	public void verifyPasswordFieldErrorMessage() {
		String actualPasswordWarning = driver
				.findElement(By.xpath("//input[@id='input-password']//following-sibling::div")).getText();
		System.out.println(actualPasswordWarning);
		Assert.assertTrue(actualPasswordWarning.contains("Password must be between 4 and 20 characters!"),
				"Password error message is not displayed");
	}
}
