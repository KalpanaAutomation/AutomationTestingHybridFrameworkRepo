package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {
WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterEmailAndPassword(String email, String password) {
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
	}
	
	public void clickOnLoginButton() {
		driver.findElement(By.xpath("//input[@value='Login']")).click();
	}
	
	public void verifyLoginSuccessfullMessage() {
		String expectedText = "Edit your account information";
		String actualText = driver.findElement(By.linkText("Edit your account information")).getText();
		System.out.println("Login SUccessful message is : "+actualText);
		Assert.assertEquals(expectedText, actualText, "Edit account link is not prosent on thr page");
	}
	
	public void verifyLoginErrorMessage() {
		String loginErrorText = driver.findElement(By.xpath("//div[text()='Warning: No match for E-Mail Address and/or Password.']")).getText();
		System.out.println(loginErrorText);
		Assert.assertTrue(loginErrorText.contains("Warning"), "Login Error message is not getting displayed");
	}
}






