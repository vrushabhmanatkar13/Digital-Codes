package com.Digitalcodes.testcases;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.TableOfContent_Page;
import com.Digitalcodes.pageobject.TitleCover_Page;
import com.Digitalcodes.pageobject.TitleLanding_Page;
import com.Digitalcodes.pageobject.TitleSection_Page;
import com.Digitalcodes.utilities.Sparkreport;

public class Title_ASTMStanderds_Test extends Prerequisites_Teardown{
     TableOfContent_Page tableOfContent_Page;
	 TitleSection_Page sectionpage;
	 TitleLanding_Page landingpage;
	 TitleCover_Page coverpage;
	 
	 private String Chapter;
	
	 
	@BeforeClass(alwaysRun = true)
	public void beforeclass() {
		tableOfContent_Page=new TableOfContent_Page();
		sectionpage=new TitleSection_Page();
		coverpage=new TitleCover_Page();
	}
	
	@DataProvider(name = "ASTM")
	public Object[][] getTitlesByRow() {

		return excel.getDataFromExcle("ASTM", 1);
	}
	
	@Test(priority = 1,description = "Verify user able to navigate to ASTM Standerds thorugh link",dataProvider = "SingleTitle",dataProviderClass = Prerequisites_Teardown.class, groups = {"Smoke","Regression"})
	public void TC34_verifyNavigateToASTM_FromLinks(String Section, String Sub_section, String Title, String Chapter) throws Exception {
		
		landingpage=commanstep.navigetToTitle(Section, Sub_section, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + Section);
		Sparkreport.Step("Click  " + Sub_section);
		Sparkreport.Step("Click  " + Title);

		String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + actChapter);
		
		String text=sectionpage.getLinkText();
		report.create_info("ASTM LINK text :- "+text);
		
		Thread.sleep(2000);
		sectionpage.clickASTMLink();
		Sparkreport.Step("Click "+text);
		
		Thread.sleep(2000);
	    String tag=landingpage.getTagName();
	  
	    report.create_info("Tag :- "+tag);
		assertEquals(tag, jsonArrayValue("Premium", "astm tag"));
		
		String category=landingpage.getCategoryText();
		report.create_info("Categoery :- "+category);
		assertEquals(category, "ASTM Standards");
		
	}
	
	@Test(priority = 2,description = "Verify user able to navigate to ASTM Standerds Landing page",dataProvider = "ASTM", groups = {"Smoke","Regression"})
	public void TC35_verifyNavigateToASTM(String Section, String Sub_section,String category, String Title, String Chapter) throws Exception {
	
		coverpage=menu.navigateToTitlesCover(Section, Sub_section);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + Section);
		Sparkreport.Step("Click  " + Sub_section);
		String title=getTitle();
		String heading = coverpage.getHeading().replace(" Building Codes", "");
		report.create_info("Page Title :- "+title);
		
		coverpage.clickASTMCategories(category);
		Sparkreport.Step("Click "+category);
		
		landingpage=coverpage.clickOnTitlesCover(Title);
		Sparkreport.Step("Click "+Title);
		
		String titlename=landingpage.getTitleHeading();
		report.create_info("Title Name:- "+titlename);
		String tagname=landingpage.getTagName();
		report.create_info("Tag :- "+tagname);
		String activetext= landingpage.getActivepremiumText();
		report.create_info("Title is "+activetext);
		this.Chapter=Chapter;
		assertEquals(heading, Sub_section);
		assertEquals(title, Sub_section + " Building Codes - ICC Digital Codes");
		assertEquals(titlename, Title);
		assertEquals(tagname, jsonArrayValue("Premium", "tag")+" LITE");
		assertEquals(activetext, jsonArrayValue("Premium","Access-title"));
		
	
	}
	
	
	@Test(dependsOnMethods ="TC35_verifyNavigateToASTM",description = "Verify user able to Access ASTM title ", groups = {"Smoke","Regression"})
		public void TC36_verifyASTMSection() throws Exception {
		
		       tableOfContent_Page.clickASTMChapter();
		        Sparkreport.Step("Click "+ Chapter);
		       assertTrue( sectionpage.pdfViewer());
		     
		    
		     
		}
	

	}
		
	
	
	
	

