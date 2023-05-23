package com.Digitalcodes.testcases;

import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.TableOfContent_Page;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.Sparkreport;

public class TableOfContent_Test extends Prerequisites_Teardown {

	@Test(priority = 1, description = "Verify my notes From Table Of Content when user have no any notes ", dataProvider = "SingleTitle", dataProviderClass = Prerequisites_Teardown.class, groups = {
			"Smoke", "Regeration" })
	public void TC07_verifyMyNotes_FromTOC(String Section, String Sub_section, String Title, String Chapter)
			throws Exception {
		TableOfContent_Page tableOfContent_Page = new TableOfContent_Page();

		commanstep.navigetToTitle(Section, Sub_section, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Listitem " + Section);
		Sparkreport.Step("Click subList " + Sub_section);
		Sparkreport.Step("Click Title " + Title);

		tableOfContent_Page.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");
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
		assertEquals(text, "Default");
		assertTrue(moveNoteText);
		assertEquals(pageTitle, jsonArrayValue("Page-titles", "my-notes-bookmark"));

	}
}
