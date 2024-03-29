package com.Digitalcodes.testcases;

import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.TableOfContent_Page;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.DataProviders;
import com.Digitalcodes.utilities.Sparkreport;

public class TableOfContent_Test extends Prerequisites_Teardown {

	@Test(priority = 1, description = "Verify my notes From Table Of Content when user have no any notes ", dataProvider = "PC first title", dataProviderClass = DataProviders.class, groups = {
			"Smoke", "Regression" })
	public void TC07_verifyMyNotes_FromTOC(String option_L1, String option_L2, String option_L3, String Title, String Chapter, String tag)
			throws Exception {
		TableOfContent_Page tableOfContent_Page = new TableOfContent_Page();

		commanstep.navigetToTitle(option_L1, option_L2, option_L3, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click option_L1 " + option_L1);
		Sparkreport.Step("Click option_L2 " + option_L2);
		Sparkreport.Step("Click option_L3 " + option_L3);
		Sparkreport.Step("Click Title " + Title);

		tableOfContent_Page.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");

		Thread.sleep(3000);
		String textInmynotes = tableOfContent_Page.getTextInMyNotes();
		report.create_info("Text in My Notes :- " + textInmynotes);

		String text = tableOfContent_Page.getTagNameInMyNotes();
		Sparkreport.Step("Click Filter by Tags");
		report.create_info("Text in Tag DropDown :-" + text);

		boolean moveNoteText = tableOfContent_Page.clickMoveNote();
		Sparkreport.Step("Click Move Notes");
		Sparkreport.Step("Click Close");

		String pageTitle = tableOfContent_Page.clickManageNotes();
		Sparkreport.Step("Click Manage Notes");
		report.create_info("Page Title :-" + pageTitle);
		Baseclass.navigateToBack();

		assertEquals(textInmynotes, jsonValue("mynotes-text"));
		assertEquals(text, "No data available");
		assertTrue(moveNoteText);
		assertEquals(pageTitle, jsonArrayValue("Page-titles", "my-notes-bookmark"));

	}
}
