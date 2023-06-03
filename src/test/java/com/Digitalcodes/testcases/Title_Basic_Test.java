package com.Digitalcodes.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.TableOfContent_Page;
import com.Digitalcodes.pageobject.TitleLanding_Page;
import com.Digitalcodes.utilities.Sparkreport;

public class Title_Basic_Test extends Prerequisites_Teardown{

	TitleLanding_Page landingpage;
	TableOfContent_Page tocpage;
	@BeforeClass(alwaysRun = true)
	public void beforeclass() {
		tocpage=new TableOfContent_Page();
		
	}
	
	
	@DataProvider(name = "Basic")
	public Object[][] getBasicTitleData(){
		return excel.getDataFromExcle("Basic", 1);
	}
	
	
	@Test(priority = 1,description = "Verify user able to navigate to Non-accsiable title ",dataProvider = "Basic",groups = {"Smoke","Regression"})
	public void TC33_verifyNgviateToBasicTitle(String Section, String Sub_section,String title) {
		landingpage=commanstep.navigetToTitle(Section, Sub_section, title);
		Sparkreport.Step("Click Menu");
		Sparkreport.Step("Click "+ Section);
		Sparkreport.Step("Click "+ Sub_section);
		Sparkreport.Step("Click "+ title);
		 
		String text2=landingpage.getNotPremiumText();
		String text1=tocpage.getNotPremiumText();
		report.create_info(text1);
		
		boolean lock=tocpage.locklabelisDisplayed();
		assertTrue(lock);
		assertEquals(text1, text2);
		
		String titlename=tocpage.clickSubscribedNow();
		report.create_info(titlename);
		
		
	}
	}