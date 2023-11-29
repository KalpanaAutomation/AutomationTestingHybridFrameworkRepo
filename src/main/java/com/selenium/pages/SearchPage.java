package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SearchPage  {
	WebDriver driver;

	public SearchPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void enterProductAndClickOnSearchButton(String productName) {
		driver.findElement(By.name("search")).sendKeys(productName);
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
	}
	
	public void verifyValidProductDetailOnSearchPage() {
		WebElement hpLaptopProd = driver.findElement(By.linkText("HP LP3065"));
		System.out.println(hpLaptopProd.getText());
		Assert.assertTrue(hpLaptopProd.isDisplayed(), "HP Product is not displayed");
	}
	
	public void verifyNoProductDeatilMessage() {
		String noProductMessage = driver.findElement(By.xpath("//div[@id='content']/descendant::p[text()='There is no product that matches the search criteria.']")).getText();
		System.out.println(noProductMessage);
		Assert.assertTrue(noProductMessage.contains("ABCD"), "'No product that matches the search criteria' message is not displayed");		
	}
}