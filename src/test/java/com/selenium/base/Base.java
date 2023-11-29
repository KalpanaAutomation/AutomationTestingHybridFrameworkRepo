package com.selenium.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class Base {

	WebDriver driver;
	public Properties prop;
	

	
	public Base(){
		String path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\selenium\\config\\config.properties";
		prop = new Properties();
		try {
			
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
			
		}catch(Throwable e) {
			e.printStackTrace();
		}
		System.out.println("url is : "+prop.getProperty("url"));
	}

	
	void addition(int a) {
		System.out.println(a);
	}
	
	
	public WebDriver initializeBrowserAndLaunchApplicationURL(String browserName){		
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("No Browser Found!!");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
		return driver;
	}
}
