package com.Digitalcodes.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.TitleCover_Page;
import com.Digitalcodes.utilities.Load_Excle;
import com.Digitalcodes.utilities.Sparkreport;

public class Title_Collection_Test {
     TitleCover_Page coverpage;
	
	@BeforeClass(alwaysRun = true)
	public void beforeclassCollection() {
		
	}
	
	
	/*
	 * @Test(priority = 1,description =
	 * "Verify user able to navigate to Collection title") public void
	 * TC17_verifyCollectionTitle() { coverpage=menu.navigateToCollections();
	 * Sparkreport.Step("Click menu"); Sparkreport.Step("Click "+
	 * jsonArrayValue("collection", "section")); String
	 * title=coverpage.getHeading(); report.create_info("Page Title is "+
	 * getTitle());
	 * 
	 * coverpage.clickOnTitlesCover(jsonArrayValue("collection",
	 * "collection-title"));
	 * 
	 * assertEquals(title, jsonArrayValue("collection", "section"));
	 * assertEquals(title, jsonArrayValue("collection",
	 * "section")+" Building Codes - ICC Digital Codes"); }
	 */
	
	
	
	
}
