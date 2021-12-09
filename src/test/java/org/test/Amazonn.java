package org.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazonn {
	static WebDriver driver;
	static long startTime; 
	static String oldPower;
		@BeforeClass()
		public static void launch() {
			WebDriverManager.chromedriver().setup();
		       driver = new ChromeDriver();
			driver.get("https://www.amazon.in/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
		
		
	    @BeforeMethod()
	    public void startTime() {
	    	long startTime = System.currentTimeMillis();
	    }
	    
	  
	    
	    @Test(priority = -1)

	    public void search() throws InterruptedException {
	    	Thread.sleep(3000);
	    	WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
	    	searchBox.click();
	    	searchBox.sendKeys("powerbank",Keys.ENTER);
	    	
	    }
	    @Test(priority = 0)
	    public void click() {
	    WebElement powerBank = driver.findElement(By.xpath("(//span[contains(text(),'pTron Dynamo Pro 10000mAh')])[1]"));
	   String oldPower = powerBank.getText();
	   System.out.println(oldPower);
	    powerBank.click();
	    
	    }
}
