package com.Digitalcodes.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.TitleCover_Page;
import com.Digitalcodes.utilities.Sparkreport;

public class TitleCovePage_Test extends Prerequisites_Teardown {

	@BeforeMethod(alwaysRun = true)
	public void beforeMethodsGroupoftitles() {

		//menu.clickOnMenu();
	}

	@Test(priority = 2, description = "Verify user able to navigate to Group of Title")
	public void navigateToGroupTitles() throws Exception {

		TitleCover_Page coverpage = menu.navigateToTitlesCover(jsonArrayValue("I-codes", "section"),
				jsonArrayValue("I-codes", "sub-section"));
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click " + jsonArrayValue("I-codes", "section"));
		Sparkreport.Step("Click " + jsonArrayValue("I-codes", "sub-section"));
		report.create_info("Page Title is " + getTitle());

		assertEquals(coverpage.getHeading(), jsonArrayValue("I-codes", "sub-section"));
		assertEquals(getTitle(), coverpage.getHeading() + " Building Codes - ICC Digital Codes");

	}

}
