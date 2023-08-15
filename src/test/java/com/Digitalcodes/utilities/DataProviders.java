package com.Digitalcodes.utilities;

import org.testng.annotations.DataProvider;

import com.Digitalcodes.testcases.Prerequisites_Teardown;

public class DataProviders extends Prerequisites_Teardown{
	
	
	@DataProvider(name = "PC first title")
	public Object[][] getTitlesByRow() throws Exception {

		return excel.getDataFromExcle("Titles", "Premium Complete Titles", 1);
	}

	@DataProvider(name = "Premium Complete Titles")
	public Object[][] getAllTitles() throws Exception {
			
		if (TAGNAME.equalsIgnoreCase("Smoke") && USER.equalsIgnoreCase("PREMIUM")) {
			return excel.getDataFromExcle("Titles", "Premium Complete Titles", 1);
			
		}
		if (TAGNAME.equalsIgnoreCase("Regression") && USER.equalsIgnoreCase("PREMIUM")) {
			return excel.getDataFromExcle("Titles", "Premium Complete Titles");
		}
		if (TAGNAME.equalsIgnoreCase("Smoke") && USER.equalsIgnoreCase("SINGLE")) {
			return excel.getDataFromExcle("Titles", "Single Subscription Titles",1);
		}
		if (TAGNAME.equalsIgnoreCase("Regression") && USER.equalsIgnoreCase("SINGLE")) {
			return excel.getDataFromExcle("Titles", "Single Subscription Titles");
		}
		else {
			throw new Exception("Please set Tag Name and User Type in testng.xml");
		}
		
	
	}
}
