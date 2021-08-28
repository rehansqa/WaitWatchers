package com.weightwatchers.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.weightwatchers.utils.BaseTest;

public class FindWork extends BaseTest {
	
	public static String searchTitile;
	public static String searchDistance;
	
	@Test(priority=1)
	public void verifyCategoryListing(){
		test = extent.createTest("FindYourWork");
		String actualTitle=homePageObj.getTitle();
		Assert.assertEquals(actualTitle, "Find WW�Studios & Meetings Near You | WW USA");
		test.log(Status.PASS, "FindYourWork title Login passed");
	}
	
	@Test(priority=2)
	public void verifySearchResultTitleAndDistance(){
		
		test = extent.createTest("verifySearchResultTitle");
		homePageObj.clickStudio();
		homePageObj.searchLocation("10011");
		homePageObj.waitForSomeTime();
		
		//print the title
		searchTitile=homePageObj.getSearchResultTitle();
		System.out.println("====TITLE===="+searchTitile);
		
		//print the Distance
		searchDistance=homePageObj.getDistance();
		System.out.println("====DISTANCE===="+searchDistance);
		
		test.log(Status.PASS, "FindYourWork Search Result Title And Distance passed");
	}
	
	@Test(priority=3)
	public void verifyStudioDetails(){
		
		test = extent.createTest("verifyStudioDetails");
		homePageObj.clickName();
		
		String expectedTitle=studioDetailsPageObj.getLocationTitle();
		
		//verify location title matched with name of first search result
		Assert.assertEquals(searchTitile, expectedTitle);
		test.log(Status.PASS, "Verify the title matched with name of first search result");
	}
	
	@Test(priority=4)
	public void getBusinessHours(){
		
		test = extent.createTest("getBusinessHours");
		studioDetailsPageObj.clickAndGetBusinessHours();
		test.log(Status.PASS, "All Business hours for studio passed");
	}
}
