package com.Digitalcodes.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.Digitalcodes.pageobject.TableOfContent_Page;
import com.Digitalcodes.pageobject.TitleSection_Page;

import com.Digitalcodes.utilities.Sparkreport;

public class Title_Commentary_Test extends Prerequisites_Teardown{

	TitleSection_Page sectionpage;
	TableOfContent_Page tableOfContent_Page;
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		sectionpage=new TitleSection_Page();
		tableOfContent_Page=new TableOfContent_Page();
		
	}
	
	@DataProvider(name = "Commentary")
	public Object[][] getCommentaryData(){
		return excel.getDataFromExcle("Titles", 2);
	}
	
	
	@Test(priority = 1,description = "Verify User able to Hide commentary section",dataProvider = "Commentary",groups = {"Smoke","Regression"})
	public void TC34_verifyHideCommentary(String Section, String Sub_section, String Title, String Chapter) throws Exception {
		commanstep.navigetToTitle(Section, Sub_section, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Listitem " + Section);
		Sparkreport.Step("Click subList " + Sub_section);
		Sparkreport.Step("Click Title " + Title);

		String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + actChapter);
		
		Thread.sleep(3000);
		String hidetext=sectionpage.clickHideButton();
		Sparkreport.Step("Click Hide Commentary");
		report.create_info("Text after Click on Hide Button :- "+hidetext);
		
		assertEquals(hidetext, jsonValue("show-text"));
		
		sectionpage.clickHideButton();
		Sparkreport.Step("Click Show Commentary");
		
	}
	
}
