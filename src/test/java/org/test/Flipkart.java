package org.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {
	static WebDriver driver;
	static long startTime;
	@BeforeClass(groups = "common")
	public static void launch() {
		WebDriverManager.chromedriver().setup();
	      driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	   @BeforeMethod(groups = "common")
	    public void startTime() {
	    	long startTime = System.currentTimeMillis();
	    }
	   @Parameters({"username","password"})
	   @Test(groups = "smoke")
	   public void login(String name,String pass) {
		   driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(name);
		   driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pass);
		   driver.findElement(By.xpath("//button[text()='✕']")).click();
	   }
		  @AfterMethod(groups = "common")
		    public void endTime() {
		    	long endTime = System.currentTimeMillis();
		    	long time = endTime - startTime;
		    	System.out.println("The Time Taken is " +time);
		    	
		    }
		  @AfterClass(groups = "common")
		  public void quit() {
			  driver.quit();
		  }
}
