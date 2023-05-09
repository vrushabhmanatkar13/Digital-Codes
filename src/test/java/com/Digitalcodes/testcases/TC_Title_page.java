package com.Digitalcodes.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.Codes_Section;
import com.Digitalcodes.pageobject.Favorite;

import com.Digitalcodes.pageobject.TOC;
import com.Digitalcodes.pageobject.TitleCover_Page;
import com.Digitalcodes.pageobject.TitleLanding_Page;
import com.Digitalcodes.perfectocloud.AssertStatements;



public class TC_Title_page extends Prerequisites_Teardown{
	
	TitleCover_Page coverpage;
	TitleLanding_Page title;
	TOC toc;
	Codes_Section section;
	String titlename;
	
	
	@BeforeClass (alwaysRun = true)
	public void beforeClassTitlepage() {
		coverpage=new TitleCover_Page();
		 title = new TitleLanding_Page();
		 toc=new TOC();
	     section = new Codes_Section();
	     menu.clickOnMenu();
		 menu.navigateToTitlesCover(excel.getCellValue(4, "Expected text"), excel.getCellValue(4, "Expected text _1"));
	}
	
	/*
	 * @BeforeMethod(alwaysRun = true) public void beforeMethodTitlepage() throws
	 * Exception { menu.clickOnMenu();
	 * menu.navigateToTitlesCover(excel.getCellValue(4, "Expected text"),
	 * excel.getCellValue(4, "Expected text _1"));
	 * coverpage.clickOnTitlesCover(excel.getCellValue(5, "Expected text"));
	 * toc.navigateToChapter(excel.getCellValue(9, "Expected text")); }
	 */
	
	
	
	
	@Test(priority = 3,description = "Verify User able to Navigate to Title Landing page and verify Tag")
	public void navigateToTitle() throws Exception {
		
		
		stepStart("========= Navigate to Title landing page ========");
		 report.create_info(excel.getCellValue(5, "Steps"));
	
		//TitleLanding_Page title = new TitleLanding_Page();
		 titlename=coverpage.clickOnTitlesCover(excel.getCellValue(5, "Expected text"));
		 String tagname = title.getTagName();
		 String premiumtext=title.getActivepremiumText();
		 stepEnd();
		
		 report.create_info("Title Name is " + titlename);
		 report.create_info("Tag Name is " + tagname);
		 report.create_info("Subscription is " + premiumtext);
		
		AssertStatements.assertEquals(titlename, excel.getCellValue(5, "Expected text"));
		AssertStatements.assertEquals(tagname, excel.getCellValue(5, "Expected text _1"));
		AssertStatements.assertEquals(premiumtext,  excel.getCellValue(5, "Expected text _2"));
		
		Assert.assertEquals(titlename, excel.getCellValue(5, "Expected text"));
		Assert.assertEquals(tagname, excel.getCellValue(5, "Expected text _1"));
		Assert.assertEquals(premiumtext,  excel.getCellValue(5, "Expected text _2"));
		
	}
	

	@Test(priority = 4, description = "Verify user able to mark favorite and title should be displayed in favorites")
	public void markFavoriteTitle() throws InterruptedException {
		
		 stepStart("==== Click Favorites icon =====");
		 report.create_info(excel.getCellValue(6, "Steps"));
		
		title.clickOnFavorite();
		Thread.sleep(5000);
		String text=title.getFavoriteText();
		
		report.create_info("Text after mark favorite :- "+ text);

		 stepEnd();
		
		
		 stepStart("==== Verify Favorite Title is display in favorites ======"); 
		   menu.clickOnMenu();
		  menu.navigetToStaticFeaturs("Favorites");
		  String favtitlename=new Favorite().getTitleName(); 
		 
		menu.clickOnMainMenu();
		menu.closeMainmenu();
		report.create_info("Title Name is :- "+ favtitlename);
		 stepEnd();
		
	  
		
		AssertStatements.assertEquals(text, excel.getCellValue(6, "Expected text"));
		AssertStatements.assertEquals(favtitlename,titlename);
		
		Assert.assertEquals(text, excel.getCellValue(6, "Expected text"));
		Assert.assertEquals(favtitlename,titlename);
		

	}
	
	
	@Test(priority = 5,description = "Verify user able to remove favorite title")
	public void removeFavoriteTitle() throws InterruptedException {
		 stepStart("===== Remove feavorite of Title ======");
		 
		 report.create_info(excel.getCellValue(7, "Steps"));
		
		
		title.clickOnUnFavorite();
		Thread.sleep(4000);
		String text=title.getFavoriteText();
		 report.create_info("Text after mark unfavorite :- "+ text);
		stepEnd();
		
		
		AssertStatements.assertEquals(text, excel.getCellValue(7, "Expected text"));
		Assert.assertEquals(text,excel.getCellValue(7, "Expected text"));
	
		
		
	}
	
	
	
	@Test(priority = 6, description = "Verify my notes From TOC when user have no any notes ")
	public void navigateToMyNotes_fromTOC() throws Exception {
		 stepStart("========= Click on My Notes present on TOC =========");
		 report.create_info(excel.getCellValue(8, "Steps"));
	
		
		toc.clickOnMyNotes();
		String notestext=toc.getTextInMyNotes();
		 report.create_info("Text in My Notes :- "+ notestext);
		 stepEnd();
		
	
		AssertStatements.assertEquals(notestext, excel.getCellValue(8, "Expected text"));
		Assert.assertEquals(notestext, excel.getCellValue(8, "Expected text"));
		
	}
	
	
	

	@Test(priority = 7, description = "Verify user able to naviagate Section from TOC")
	public void navigateToSection_fromTOC() throws Exception{
		
		//Codes_Section section = new Codes_Section();
		 stepStart("======= Navigate to Section of title =========");
		 report.create_info(excel.getCellValue(9, "Steps"));
		
		toc.clickCodesSection();
		String chapterName=toc.navigateToChapter(excel.getCellValue(9, "Expected text"));
		String chapterHeading=section.getChapterName();
		report.create_info("Section Name :- "+ chapterName);
		 stepEnd();
		 
		
       
       AssertStatements.assertEquals(chapterHeading, chapterName);
       Assert.assertEquals(chapterHeading, chapterName);
       
	}

	@Test(priority = 8, description = "Verify user able to create notes at section")
	public void createNotes_atSection() throws Exception {
	
		 stepStart("========= Click on Apps =========");
		 report.create_info(excel.getCellValue(10, "Steps"));
		
		
		section.clickOnApps(section.getSessionID_FromChapter());
		
		stepStart("========= Double Click on Text and Create Note =========");
		section.doubleClickOnTitle_Section();
		section.createNote_FromSection(excel.getCellValue(10, "Expected text"));
		
		report.create_info("Note Created at :- "+ section.getChapterName());
	     stepEnd();
	     
	     stepStart("========= Create & Select New Tag and Save =========");
		section.createNewTag(excel.getCellValue(10, "Expected text _1"));
		String tagName=section.selectTag(excel.getCellValue(10, "Expected text _1"));
		section.clickOnSaveButton();
		
		report.create_info("Tag Name :- "+ tagName );
		 stepEnd();
		
		System.out.println("Note Create at "+ section.getChapterName());
		
		
		AssertStatements.assertEquals(tagName, section.getTagName());
		AssertStatements.assertEquals(section.getDescription(), excel.getCellValue(10, "Expected text"));
		AssertStatements.assertEquals(section.getCreatedBy(), TC_Loginpage.name+" ("+TC_Loginpage.email+")");
		
		Assert.assertEquals(tagName, section.getTagName());
		Assert.assertEquals(section.getDescription(), excel.getCellValue(10, "Expected text"));
		Assert.assertEquals(section.getCreatedBy(), TC_Loginpage.name+" ("+TC_Loginpage.email+")");
		
	}
	
	
	@Test(dependsOnMethods = {"createNotes_atSection","createBookmark_atSection"}, description = "Verify Chapter Name is displayed in MyNotes and My Notes-Bookmark after note is created")
	public void verifyMyNotes_fromTOC() throws Exception {
		stepStart("========== Click on My Notes & Verify Chapter Name ================");
		report.create_info(excel.getCellValue(8, "Steps"));
		
		toc.clickOnMyNotes();
		String notestext=toc.getChapterNameInMynotes();
		
		report.create_info("Chapter Name at my notes :- "+ notestext);
		 stepEnd();
		System.out.println("Section Name at my notes is "+ notestext);
		
		stepStart("================= click on menu > Premium tools > My notes and Bookmark ==============");
		menu.clickOnMenu();
		menu.clickOnPremiumtools();
		menu.clickOnPremiumToolfeature("My Notes and Bookmarks");
		
		
		Assert.assertTrue(!notestext.isEmpty());
	}
	
	

	
	
	@Test(priority = 9, description = "Verify user able to create bookmark at section")
	public void createBookmark_atSection() throws Exception  {
		
		 menu.clickOnMenu();
		 menu.navigateToTitlesCover(excel.getCellValue(4, "Expected text"), excel.getCellValue(4, "Expected text _1"));
		 coverpage.clickOnTitlesCover(excel.getCellValue(5, "Expected text"));
		 toc.navigateToChapter(excel.getCellValue(9, "Expected text"));
		
		stepStart("==================== click on Apps and Bookmark, Enter decription ================");
		report.create_info(excel.getCellValue(11, "Steps"));
		
		section.clickOnApps(section.getSessionID_FromChapter());
		section.createBookamrk_FromSection(excel.getCellValue(11, "Expected text"));
		report.create_info("Bookmark Created at :- "+ section.getChapterName());
		 stepEnd();
		 
		 stepStart("================== Select Tag and Save =============");
		 String tagName=section.selectTag(excel.getCellValue(11, "Expected text _1"));
		 section.clickOnSaveButton();
		 report.create_info("Tag Name :- "+ tagName );
		 stepEnd();
		 
		 
		    AssertStatements.assertEquals(tagName, section.getTagName());
			AssertStatements.assertEquals(section.getDescription(), excel.getCellValue(11, "Expected text"));
			AssertStatements.assertEquals(section.getCreatedBy(), TC_Loginpage.name+" ("+TC_Loginpage.email+")");
			
			Assert.assertEquals(tagName, section.getTagName());
			Assert.assertEquals(section.getDescription(), excel.getCellValue(11, "Expected text"));
			Assert.assertEquals(section.getCreatedBy(), TC_Loginpage.name+" ("+TC_Loginpage.email+")");
		  
	}
}
