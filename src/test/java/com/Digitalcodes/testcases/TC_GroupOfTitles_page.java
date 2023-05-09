package com.Digitalcodes.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Digitalcodes.perfectocloud.AssertStatements;

public class TC_GroupOfTitles_page extends Prerequisites_Teardown {
	
	
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethodsGroupoftitles() {
		
		menu.clickOnMenu();
	}
	
	
	@Test(priority = 2, description = "Verify user able to navigate to Group of Title")
	public void navigateToGroupTitles() throws Exception {
		
		
	    stepStart("==== Click on Menu > CLick on Icodes > Click on 2021 I-codes ========");
		 report.create_info(excel.getCellValue(4, "Steps"));
		
		 
		 menu.navigateToTitlesCover(excel.getCellValue(4, "Expected text"), excel.getCellValue(4, "Expected text _1"));
		 String title= getTitle();
		 String actTitle = menu.getTitleHeading();
		stepEnd();
		
		report.create_info("Group of Title is " + actTitle);
		
		
		AssertStatements.assertEquals(actTitle, excel.getCellValue(4, "Expected text _1"));
		AssertStatements.assertEquals(title, actTitle+" Building Codes - ICC Digital Codes");
		Assert.assertEquals(actTitle, excel.getCellValue(4, "Expected text _1"));
		Assert.assertEquals(title, actTitle+" Building Codes - ICC Digital Codes");
		
		
	
	}

}
