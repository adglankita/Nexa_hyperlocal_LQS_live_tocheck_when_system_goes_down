package com.test.NexaLQS;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import junit.framework.Assert;

public class Testing_NEXA_LQS_Performance {
	WebDriver driver;
	
	//long timestamp = System.currentTimeMillis();
	String name = "TestingPerformanceNexaUrl"; 
	String dealerID = "manish.saini@Pasco.in";
	String browserURL = "http://www.nexaofmathuraroad.com/";
	String dealerURL = "http://lqs.co.in";
	String email = "automationtesting@adglobal360.com";
	boolean flag = true;
	
	@Test (priority=1)
	public void Insurance_openBrowser() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\AGL\\Desktop\\Setup\\Important_downloads\\chromedriver_win32\\chromedriver.exe");
		//logger.info("Opening browser");
		driver = new ChromeDriver();
		//logger.debug("Maximizing the window");
		driver.manage().window().maximize();
		//logger.info("Opening website");
		driver.navigate().to(browserURL);
		//logger.info("Verifying title of the page");
		String Home_Title = driver.getTitle();
		Assert.assertEquals("NEXA Car Dealers in Mathura Road, Faridabad - Pasco Automobiles Showroom in Mathura Road, Faridabad", Home_Title);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Insurance')]")).click();
		String Insurance_Title = driver.getTitle();
		Assert.assertEquals("Insurance Offers at Pasco Automobiles Mathura Road, Faridabad | NEXA", Insurance_Title);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		Select model = new Select
				(driver.findElement(By.xpath("//select[@id='ddlCarModel']")));
		model.selectByVisibleText("Ciaz");
		
		driver.findElement(By.xpath("//input[@id='inpregno']")).sendKeys("99999999999");
		
		Select regisMonth = new Select (driver.findElement(By.xpath("//select[@id='ddlregmonth']")));
		regisMonth.selectByVisibleText("January");
		
		Select regisYear = new Select (driver.findElement(By.xpath("//select[@id='ddlregyear']")));
		regisYear.selectByVisibleText("2017");
		
		driver.findElement(By.xpath("//input[@id='inpploicyno']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='inpinscompany']")).sendKeys("NIC");
		driver.findElement(By.xpath("//input[@id='inpdateexp']")).sendKeys("01-09-2018");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0,200)");
		
		
		
		driver.findElement(By.xpath("//input[@id='inpname']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@id='inpemail']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='inpphone']")).sendKeys("9910333654");
		driver.findElement(By.xpath("//input[@id='disclaimer']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='btninsurance']")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		
		driver.close();
		
		}
	
	
	@Test (priority=2)
	
	public void Insurance_openDealerBrowser() throws Exception {
		
		
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\AGL\\Desktop\\Setup\\Important_downloads\\chromedriver_win32\\chromedriver.exe");
	//	logger.info("Opening browser");
		driver = new ChromeDriver();
	//	logger.debug("Maximizing the window");
		driver.manage().window().maximize();
		//logger.info("Opening website");
		driver.navigate().to(dealerURL);
		//logger.info("Verifying title of the page");
		String Login_Title = driver.getTitle();
		Assert.assertEquals("Login - LQS", Login_Title);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(dealerID);
		driver.findElement(By.xpath("//form[@id='form_login']//button[@type='submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='otp']")).sendKeys("0687");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Thread.sleep(2000);
		String HomePage_title = driver.getTitle();
		Assert.assertEquals("Maruti Suzuki LQS", HomePage_title);
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[@href='/lms/list']")).click();
		
		
		driver.findElement(By.xpath("//input[@id='all_src']")).sendKeys(name);
		driver.findElement(By.xpath("//div[@class='customSearch']//button[@type='submit'][contains(text(),'Go')]")).click();
		
		Thread.sleep(1000);
		
		Testing_NEXA_LQS_Performance.takeSnapShot(driver, "C:\\Users\\AGL\\eclipse-workspace\\Nexa_LQS_Performance\\src\\test\\Screenshots\\Image.png") ;
		
		 Thread.sleep(1500);
		 
		 String value = driver.findElement(By.xpath("//span[@class='label label-info btn']")).getText();
		 System.out.println(value);
		 if(value.equals("0 results found")){
			 System.out.println("Could not find the details");
			 flag = false;
			 
		 }else {
			 System.out.println("Found the details");
			 
		 }
		 Assert.assertTrue(flag);
		 
		 Thread.sleep(1500);
		 
		 driver.close();
		
		 
	}
	

	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File(fileWithPath);

                //Copy file at destination

                FileUtils.copyFile(SrcFile, DestFile);

    }

}
