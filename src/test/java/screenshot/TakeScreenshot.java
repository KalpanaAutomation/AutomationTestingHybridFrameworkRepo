package screenshot;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TakeScreenshot {

	WebDriver driver;
	public void takingScreenshot() throws IOException {
		
		driver = new ChromeDriver();
		driver.get("https://www.google.com");		
		//Convert web driver object to TakesScreenshot
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		//Call getScreenshotAs method to create image file
		File source = screenShot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File dest = new File("D:\\Java-Selenium Course\\Automation\\SeleniumClassAutomationProject\\screenshots\\SeleniumTesting.png");
		//Copy file at destination
		FileUtils.copyFile(source, dest);		
		driver.quit();
	}
	
	@Test
	public void takingScreenshotUsingAshot() throws IOException {
		
		driver = new ChromeDriver();
		driver.get("https://www.ebay.com/");
		driver.manage().window().maximize();
		
		File dest = new File("D:\\Java-Selenium Course\\Automation\\SeleniumClassAutomationProject\\screenshots\\SeleniumTesting2.png");
		
		//Screenshot screenshot = new AShot().takeScreenshot(driver);
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		//Here 1000 is scrolled out time in milliseconds, so for taking a screenshot, the program will scroll for each 1000 msec.
		
		ImageIO.write(screenshot.getImage(), "png", dest);
		driver.quit();
	}
}














