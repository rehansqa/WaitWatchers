package com.weightwatchers.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StudioDetailsPage {

	WebDriver driver;
	
	public StudioDetailsPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1")
	WebElement title;

	
	@FindBy(xpath="//div[text()='Business hours']")
	WebElement businessHrs;
	
	@FindBy(xpath="//div[contains(@class,'hoursWrapper')]/div/div")
	List<WebElement> getBusinessHrCount;
	
	
	
	public String getLocationTitle(){
		return title.getText();
	}
	
	public void clickAndGetBusinessHours(){
		businessHrs.click();
		
		int count=getBusinessHrCount.size();
		
		for(int i=1;i<=count;i++){
			String value=driver.findElement(By.xpath("//div[contains(@class,'hoursWrapper')]/div/div["+i+"]")).getText();
			System.out.println(value);
			System.out.println("=================");
		}
		
		
		
	}
}
