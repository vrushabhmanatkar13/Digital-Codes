package com.Digitalcodes.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.TableOfContent_Page;
import com.Digitalcodes.pageobject.TitleSection_Page;

import com.Digitalcodes.utilities.Sparkreport;

public class Title_Commentary_Test extends Prerequisites_Teardown {

	TitleSection_Page sectionpage;
	TableOfContent_Page tableOfContent_Page;

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		sectionpage = new TitleSection_Page();
		tableOfContent_Page = new TableOfContent_Page();

	}

	@DataProvider(name = "Premium Complete Commentries")
	public Object[][] getCommentaryData() throws Exception {
		return excel.getDataFromExcle("Titles", "Premium Complete Commentries");
	}

	@Test(priority = 1, description = "Verify User able to Hide commentary section", dataProvider = "Premium Complete Commentries", groups = {
			"Smoke", "Regression" })
	public void TC34_verifyHideCommentary(String Section, String Sub_section, String Title, String Chapter)
			throws Exception {

		commanstep.navigetToTitle(Section, Sub_section, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + Section);
		Sparkreport.Step("Click  " + Sub_section);
		Sparkreport.Step("Click  " + Title);

		String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + actChapter);
		Thread.sleep(2000);

		String showcommentrytext = sectionpage.clickHideButton();
		Sparkreport.Step("Click Hide Commentary");
		boolean subtitle = sectionpage.subtitleIsDisplayed();
		Sparkreport.Step("Text after Click Button :- " + showcommentrytext);
		assertFalse(subtitle);
		assertEquals(showcommentrytext, jsonValue("show-text"));

		String hidecommentrytext = sectionpage.clickShowButton();
		Sparkreport.Step("Text after Click Button :- " + hidecommentrytext);
		assertTrue(sectionpage.subtitleIsDisplayed());
		assertEquals(hidecommentrytext, jsonValue("hide-text"));

	}

}
