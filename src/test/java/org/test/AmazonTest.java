package org.test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonTest {
	@DataProvider(name = "mobile charge")
	public Object[][] getData(){
	return new Object[][] {{"powerbank"}};
	}
		
	
	
	
	
	static WebDriver driver;
	static long startTime; 
	static String oldPower;
		@BeforeClass(groups = "common")
		public static void launch() {
			WebDriverManager.chromedriver().setup();
		       driver = new ChromeDriver();
			driver.get("https://www.amazon.in/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
		
		
	    @BeforeMethod(groups = "common")
	    public void startTime() {
	    	long startTime = System.currentTimeMillis();
	    }
	    
	  
	    
	    @Test(priority = -1,groups = "smoke",dataProvider = "mobile charge")

	    public void search(String name) throws InterruptedException {
	    	Thread.sleep(3000);
	    	WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
	    	searchBox.click();
	    	searchBox.sendKeys(name,Keys.ENTER);
	    	
	    }
	    
	    @Test(priority = 0,groups = "smoke")
	    public void click() {
	    WebElement powerBank = driver.findElement(By.xpath("(//span[contains(text(),'pTron Dynamo Pro 10000mAh')])[1]"));
	   String oldPower = powerBank.getText();
	   System.out.println(oldPower);
	    powerBank.click();
	    
	    }
	    @Test(priority = 1,groups = "regression")
	    public void window() {
	    String parentWindow = driver.getWindowHandle();
	    Set<String> childWindow = driver.getWindowHandles();
	    for (String newWindow : childWindow) {
			if(!newWindow.equals(parentWindow)) {
				driver.switchTo().window(newWindow);
			}
			}
	    }
	    @Test(priority = 2,groups = "regression")
	    public void validation() {
	    	WebElement newPowerBank = driver.findElement(By.xpath("(//span[contains(text(),'pTron Dynamo Pro 10000mAh')])[2]"));
	    	String newPower = newPowerBank.getText();
	    	System.out.println(newPower);
	  Assert.assertEquals("pTron Dynamo Pro 10000mAh 18W QC3.0 PD Power Bank, Made in India, Fast Charge, Type-C & Micro USB Input Ports, with 18W Type C Mini Cable for Smartphones & Other Smart Device - (Blue)", newPower);
	    }
		@Test(priority = 3,groups = "sanity")
		public void buy() throws InterruptedException {
			Thread.sleep(5000);
			WebElement buyNow = driver.findElement(By.id("buy-now-button"));
			buyNow.click();
		}
		@Test(priority = 4,invocationCount = 3)
		public void screen() throws IOException {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");
			LocalDateTime now = LocalDateTime.now();
			String format = dtf.format(now);
			System.out.println(format);	
			
			TakesScreenshot tk = (TakesScreenshot)driver;
			File source = tk.getScreenshotAs(OutputType.FILE);
			File dest = new File("C:\\Users\\Admin\\eclipse-workspace\\TestNGRun\\target\\amazonss"+format+".png");
			FileUtils.copyFile(source, dest);
		}
	 

		@AfterClass(groups = "common")
		public static void quitScreen() throws IOException {
			
		driver.quit();	
		}
}
