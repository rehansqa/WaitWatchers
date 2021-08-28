package com.weightwatchers.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.weightwatchers.pages.HomePage;
import com.weightwatchers.pages.StudioDetailsPage;

public class BaseTest {
	
	public WebDriver driver;
	public HomePage homePageObj;
	public StudioDetailsPage studioDetailsPageObj;
	
	public FileInputStream fis = null;
    public Properties prop = null;
	public String env="staging";
	
	
	//builds a new report using the html template 
    public ExtentHtmlReporter htmlReporter;
    
    public ExtentReports extent;
    
    //helps to generate the logs in test report.
    public ExtentTest test;
	
   
	
	

	@BeforeTest
	public void setup(){
		String brows="chrome";
		String os="windows";
		
		
	

		//======================REPORTS=====================
		
		 //Date object
		 Date date= new Date();
	         //getTime() returns current time in milliseconds
		 long time = date.getTime();
	         //Passed the milliseconds to constructor of Timestamp class 
		 Timestamp ts = new Timestamp(time);
		
		
		// initialize the HtmlReporter
		 System.out.println(System.getProperty("user.dir"));
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/Reports/testReport.html");
        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
      
    	
        //======================BROWSER LAUNCH=====================
		if(os.equals("windows")){
	        if(brows.equals("chrome")){
				System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
				driver=new ChromeDriver();
			}else if(brows.equals("firefox")){
				System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
				driver=new FirefoxDriver();
			}else if(brows.equals("ie")){
				System.setProperty("webdriver.ie.driver", "drivers\\IEDriver.exe");
				driver=new InternetExplorerDriver();
			}
		}else if(os.equals("mac")){
			if(brows.equals("chrome")){
				System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver");
				driver=new ChromeDriver();
			}
		}
		
		
		driver.get("https://www.weightwatchers.com/us/find-a-workshop/");
		
		//======================OBJECT CREATION=====================
		homePageObj=new HomePage(driver);
		studioDetailsPageObj = new StudioDetailsPage(driver);
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
		extent.flush();
	}
	
	@AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }

}
