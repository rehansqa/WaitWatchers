package com.weightwatchers.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='Studio']")
	WebElement studio;

	@FindBy(id="location-search")
	WebElement searchBox;

	@FindBy(id="location-search-cta")
	WebElement searchArrow;
	
	@FindBy(xpath="//div[@id='search-results']/div/a//a")
	WebElement title;
	
	@FindBy(xpath="//div[@id='search-results']/div/a//span")
	WebElement distance;
	
	
	public String getTitle(){
		return driver.getTitle();
	}
	
	public void clickStudio(){
		studio.click();
	}
	
	public void searchLocation(String loc){
		searchBox.clear();
		searchBox.sendKeys(loc);
		searchArrow.click();
	}
	
	public String getSearchResultTitle(){
		return title.getText();
	}
	
	public String getDistance(){
		return distance.getText();
	}
	
	public void clickName(){
		title.click();
	}
	
	public void waitForSomeTime(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
