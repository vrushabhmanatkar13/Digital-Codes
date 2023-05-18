package com.Digitalcodes.testcases;


import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.Codes_Section;
import com.Digitalcodes.pageobject.Favorite;
import com.Digitalcodes.pageobject.Implimation_AbstractClass;
import com.Digitalcodes.pageobject.MyNotesandBookmark_Page;
import com.Digitalcodes.pageobject.TOC;
import com.Digitalcodes.pageobject.TitleCover_Page;
import com.Digitalcodes.pageobject.TitleLanding_Page;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.Sparkreport;

public class TitleLandingPage_Test extends Prerequisites_Teardown {

	TitleCover_Page coverpage;
	public TitleLanding_Page title;
	TOC toc;
	Codes_Section section;
	CommanSteps commanstep;
	String titlename;

	@BeforeClass(alwaysRun = true)
	public void beforeClassTitlepage() {

		toc = new TOC();
		section = new Codes_Section();
		
		commanstep = new Implimation_AbstractClass();
		coverpage = menu.navigateToTitlesCover(jsonArrayValue("I-codes", "section"),jsonArrayValue("I-codes", "sub-section"));
	}

	/*
	 * @BeforeMethod(alwaysRun = true) public void beforeMethodTitlepage() throws
	 * Exception { menu.clickOnMenu();
	 * menu.navigateToTitlesCover(excel.getCellValue(4, "Expected text"),
	 * excel.getCellValue(4, "Expected text _1"));
	 * coverpage.clickOnTitlesCover(excel.getCellValue(5, "Expected text"));
	 * toc.navigateToChapter(excel.getCellValue(9, "Expected text")); }
	 */

	@Test(priority = 3, description = "Verify User able to Navigate to Title Landing page and verify Tag")
	public void verifyTitleLandingPage() throws Exception {

		
		title = coverpage.clickOnTitlesCover(jsonArrayValue("I-codes", "title"));
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Listitem "+jsonArrayValue("I-codes", "section"));
		Sparkreport.Step("Click subList "+jsonArrayValue("I-codes", "sub-section"));
		Sparkreport.Step("Click Title "+jsonArrayValue("I-codes", "title"));
		
		titlename=title.getTitleHeading();
        
		report.create_info("Subscription Tag is " + title.getTagName());
		report.create_info("Subscription is " + title.getActivepremiumText());

		assertEquals(titlename, jsonArrayValue("I-codes", "title"));
		assertEquals(title.getTagName(), jsonArrayValue("Premium", "tag"));
		assertEquals(title.getActivepremiumText(), jsonArrayValue("Premium", "Access-title"));
		
		boolean currentlyviewing=title.changeVersion();
		Sparkreport.Step("Click Current Viewing ListBox");
		Sparkreport.Step("Click 2nd Version");
		
		assertTrue(currentlyviewing);


	}

	@Test(priority = 4, description = "Verify user able to mark favorite and title should be displayed in favorites")
	public void verifyMarkFavoriteTitle() throws InterruptedException {

		
		title.clickOnFavorite();
		Sparkreport.Step("Click Blank Heart icon");
		Thread.sleep(5000);
		report.create_info("Text after mark favorite :- " + title.getFavoriteText());

	
		
		menu.navigetToStaticFeaturs("Favorites");
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Favorites");
		String favtitlename = new Favorite().getTitleName();
		menu.clickOnMainMenu();
		menu.closeMainmenu();
		report.create_info("Title Name is :- " + favtitlename);
		

		assertEquals(title.getFavoriteText(), jsonValue("unfavorite"));
		assertEquals(favtitlename, titlename);

	}

	@Test(priority = 5, description = "Verify user able to remove favorite title")
	public void verifyRemoveFavoriteTitle() throws InterruptedException {


		title.clickOnUnFavorite();
		Sparkreport.Step("Click Filled Heart icon");
		Thread.sleep(4000);
		report.create_info("Text after mark unfavorite :- " + title.getFavoriteText());

		assertEquals(title.getFavoriteText(), jsonValue("favorite"));

	}

	@Test(priority = 6, description = "Verify my notes From TOC when user have no any notes ")
	public void verifyMyNotes_FromTOC() throws Exception {

		toc.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");
		String textInmynotes=toc.getTextInMyNotes();
		report.create_info("Text in My Notes :- " + textInmynotes);
		
		String text=toc.getTagNameInMyNotes();
		Sparkreport.Step("Click Filter by Tags");
		report.create_info("Text in Tag DropDown :-"+text);
		
		boolean moveNoteText=toc.clickMoveNote();
		Sparkreport.Step("Click Move Notes");
		Sparkreport.Step("Click Close");
		
		String pageTitle=toc.clickManageNotes();
		Sparkreport.Step("Click Manage Notes");
		report.create_info("Page Title :-"+pageTitle);
		Baseclass.navigateToBack();
		

		assertEquals(textInmynotes, jsonValue("mynotes-text"));
		assertEquals(text, "No data available");
		assertTrue(moveNoteText);
		assertEquals(pageTitle, jsonArrayValue("Page-titles", "my-notes-bookmark"));
		

	}

	@Test(priority = 7, description = "Verify user able to naviagate Section from TOC")
	public void verifyNavigateToSection_FromTOC() throws Exception {

		
		commanstep.navigetToTitle(jsonArrayValue("I-codes", "section"), jsonArrayValue("I-codes", "sub-section"),
				jsonArrayValue("I-codes", "title"));
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Listitem "+jsonArrayValue("I-codes", "section") );
		Sparkreport.Step("Click subList "+jsonArrayValue("I-codes", "sub-section") );
		Sparkreport.Step("Click Title "+jsonArrayValue("I-codes", "title") );
		
		String chapterName = toc.navigateToChapter(jsonArrayValue("I-codes", "chapter"));
		Sparkreport.Step("Click Chapter "+ chapterName);
		report.create_info("Chapter Name :- " + section.getChapterName());

		assertEquals(section.getChapterName(), chapterName);

	}

	@Test(priority = 8, description = "Verify user able to create notes at section")
	public void verifyCreateNotes_atSection() throws Exception {

		
		
		/*
		 * commanstep.navigetToTitle(jsonArrayValue("I-codes", "section"),
		 * jsonArrayValue("I-codes", "sub-section"), jsonArrayValue("I-codes",
		 * "title"));
		 * 
		 * Sparkreport.Step("Click menu");
		 * Sparkreport.Step("Click Listitem "+jsonArrayValue("I-codes", "section") );
		 * Sparkreport.Step("Click subList "+jsonArrayValue("I-codes", "sub-section") );
		 * Sparkreport.Step("Click Title "+jsonArrayValue("I-codes", "title") );
		 * 
		 * String actChapter = toc.navigateToChapter(jsonArrayValue("I-codes",
		 * "chapter")); Sparkreport.Step("Click Chapter "+ actChapter);
		 * 
		 * section.clickOnApps(section.getSessionID_FromChapter());
		 * Sparkreport.Step("Click Apps");
		 */
        
		section.doubleClickOnTitle_Section();
		Sparkreport.Step("Double Click on text");
		
		section.createNote_FromSection(jsonValue("notes-text"));
        Sparkreport.Step("Click Note icon");
        Sparkreport.Step("Enter Decription in textBox");
        
		section.createNewTag(jsonValue("tag-name"));
		Sparkreport.Step("Click New Tag");
		Sparkreport.Step("Enter TagName");
		Sparkreport.Step("Click Save");
		
		Thread.sleep(2000);
		section.selectTag(jsonValue("tag-name"));
		Sparkreport.Step("Click Tag DropDown");
		Sparkreport.Step("Select Tag Name");

		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");

		
		report.create_info("Tag Name :- " +section.getTagName());
		report.create_info("Note Created at :- " +section.getChapterName());
        report.create_info("Description is :- "+section.getDescription());
        report.create_info("Note Created by :- "+section.getCreatedBy());

		assertEquals(section.getTagName(), jsonValue("tag-name"));
		assertEquals(section.getDescription(), jsonValue("notes-text"));
		assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

		toc.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");
		
		report.create_info("Chapter Name at my notes :- " + toc.getChapterNameInMynotes());

		assertEquals(toc.getChapterNameInMynotes(),jsonArrayValue("I-codes", "chapter"));
		
		menu.navigateToPremiumToolFeaturs("My notes and Bookmarks");
		MyNotesandBookmark_Page mynote_bookmark=new MyNotesandBookmark_Page();
		
		assertEquals(mynote_bookmark.getTitleName(), jsonArrayValue("I-codes", "title"));
		assertTrue(mynote_bookmark.verifyChapterName_Decription(jsonArrayValue("I-codes", "chapter"), jsonValue("notes-text")));
		assertTrue(mynote_bookmark.verifyDetails(Login_Test.NAME, jsonValue("tag-name")));
		
		mynote_bookmark.removeNotes_Bookmark();
		Baseclass.refreshBrowser();
	
      
	}

	@Test(priority = 9, description = "Verify user able to create bookmark at section")
	public void verifyCreateBookmark_atSection() throws Exception {
        
		commanstep.navigetToTitle(jsonArrayValue("I-codes", "section"), jsonArrayValue("I-codes", "sub-section"),
				jsonArrayValue("I-codes", "title"));
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Listitem "+jsonArrayValue("I-codes", "section") );
		Sparkreport.Step("Click subList "+jsonArrayValue("I-codes", "sub-section") );
		Sparkreport.Step("Click Title "+jsonArrayValue("I-codes", "title") );

		String actChapter = toc.navigateToChapter(jsonArrayValue("I-codes", "chapter"));
		Sparkreport.Step("Click Chapter "+ actChapter);
		
		section.clickOnApps(section.getSessionID_FromChapter());
		 Sparkreport.Step("Click Apps");
		 
		section.createBookamrk_FromSection(jsonValue("bookmark-text"));
		 Sparkreport.Step("Click Bookmark");
		 Sparkreport.Step("Enter Decription in textBox");
	
		section.selectTag(jsonValue("tag-name"));
		Sparkreport.Step("Click Tag DropDown");
		Sparkreport.Step("Select Tag Name");
		Thread.sleep(2000);
		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");
		
		Thread.sleep(5000);
		
		
		report.create_info("Tag Name :- " +section.getTagName());
		report.create_info("Note Created at :- " +section.getChapterName());
        report.create_info("Description is :- "+section.getDescription());
        report.create_info("Note Created by :- "+section.getCreatedBy());
		

		assertEquals(section.getTagName(), jsonValue("tag-name"));
		assertEquals(section.getDescription(), jsonValue("bookmark-text"));
		assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

		toc.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");
		report.create_info("Chapter Name at my notes :- " + toc.getChapterNameInMynotes());
		
		assertEquals(toc.getChapterNameInMynotes(), jsonArrayValue("I-codes", "chapter"));
		
		menu.navigateToPremiumToolFeaturs("My notes and Bookmarks");
		MyNotesandBookmark_Page mynote_bookmark=new MyNotesandBookmark_Page();
		
		assertEquals(mynote_bookmark.getTitleName(), jsonArrayValue("I-codes", "title"));
		assertTrue(mynote_bookmark.verifyChapterName_Decription(actChapter, jsonValue("bookmark-text")));
		assertTrue(mynote_bookmark.verifyDetails(Login_Test.NAME, jsonValue("tag-name")));
		
		mynote_bookmark.removeNotes_Bookmark();
		Baseclass.refreshBrowser();
		

	}
	
	
	
	@Test(priority = 10,description = "Verify user able to share the section")
	public void verifyShareSection_FromSection() throws Exception {
		commanstep.navigetToTitle(jsonArrayValue("I-codes", "section"), jsonArrayValue("I-codes", "sub-section"),
				jsonArrayValue("I-codes", "title"));
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Listitem "+jsonArrayValue("I-codes", "section") );
		Sparkreport.Step("Click subList "+jsonArrayValue("I-codes", "sub-section") );
		Sparkreport.Step("Click Title "+jsonArrayValue("I-codes", "title") );

		String actChapter = toc.navigateToChapter(jsonArrayValue("I-codes", "chapter"));
		Sparkreport.Step("Click Chapter "+ actChapter);
		
		section.clickOnApps(section.getSessionID_FromChapter());
		 Sparkreport.Step("Click Apps");
		 
		String successMessage= section.shareSection(jsonValue("username"));
		Sparkreport.Step("Click Share");
		Sparkreport.Step("Enter email");
		Sparkreport.Step("Click AddMore");
		Sparkreport.Step("Click Remove");
		Sparkreport.Step("Click Share Button");
		Sparkreport.Step("Click Close");
		report.create_info("Successful message :- "+successMessage);
		
		assertEquals(successMessage, jsonValue("share-successful"));
		
		
	}
	
	
	
	@Test(priority = 11,description = "Verify user able to Print Section ")
	public void verifyPrintSection_FromSection() throws Exception {
		
		/*
		 * commanstep.navigetToTitle(jsonArrayValue("I-codes", "section"),
		 * jsonArrayValue("I-codes", "sub-section"), jsonArrayValue("I-codes",
		 * "title"));
		 * 
		 * Sparkreport.Step("Click menu");
		 * Sparkreport.Step("Click Listitem "+jsonArrayValue("I-codes", "section") );
		 * Sparkreport.Step("Click subList "+jsonArrayValue("I-codes", "sub-section") );
		 * Sparkreport.Step("Click Title "+jsonArrayValue("I-codes", "title") );
		 * 
		 * String actChapter = toc.navigateToChapter(jsonArrayValue("I-codes",
		 * "chapter")); Sparkreport.Step("Click Chapter "+ actChapter);
		 * 
		 * section.clickOnApps(section.getSessionID_FromChapter());
		 * Sparkreport.Step("Click Apps");
		 */
		 
		 boolean pdfprint=section.printSection();
		 Sparkreport.Step("Click Print");
		
		 assertTrue(pdfprint);
		 
		
	}
	
	
	@Test(priority = 12, description = "Verify user able to share note from section")
	public void verifyShareNote_FromSction() throws Exception {
		/*
		 * commanstep.navigetToTitle(jsonArrayValue("I-codes", "section"),
		 * jsonArrayValue("I-codes", "sub-section"), jsonArrayValue("I-codes",
		 * "title")); Sparkreport.Step("Click menu");
		 * Sparkreport.Step("Click Listitem "+jsonArrayValue("I-codes", "section") );
		 * Sparkreport.Step("Click subList "+jsonArrayValue("I-codes", "sub-section") );
		 * Sparkreport.Step("Click Title "+jsonArrayValue("I-codes", "title") );
		 * 
		 * String actChapter = toc.navigateToChapter(jsonArrayValue("I-codes",
		 * "chapter")); Sparkreport.Step("Click Chapter "+ actChapter);
		 * 
		 * section.clickOnApps(section.getSessionID_FromChapter());
		 * Sparkreport.Step("Click Apps");
		 */
        
        section.doubleClickOnTitle_Section();
		Sparkreport.Step("Double Click on text");
		
		section.createNote_FromSection(jsonValue("notes-text"));
        Sparkreport.Step("Click Note icon");
        Sparkreport.Step("Enter Decription in textBox");
        
        section.selectTag(jsonValue("tag-name"));
		Sparkreport.Step("Click Tag DropDown");
		Sparkreport.Step("Select Tag Name");
		
        section.clickOnSaveButton();
		Sparkreport.Step("Click Save");
		
		Thread.sleep(5000);
		String SuccessfullyMsg=section.shareNotes_Bookmark(jsonValue("username"));
		Sparkreport.Step("Click Share");
		Sparkreport.Step("Enter email");
		Sparkreport.Step("Click Submit");
		report.create_info("Successful message :- "+SuccessfullyMsg);
	
		
		assertEquals(SuccessfullyMsg, jsonValue("share-content-successful"));

	}
	
	@Test(priority = 13,description = "Verify user able to edit notes from Section")
	public void verifyEditNotes_FromSection() throws InterruptedException {
		section.editNotes_Bookmark(jsonValue("notes-edit-text"));
		Sparkreport.Step("Click edit");
		Sparkreport.Step("Enter edited text");
		Sparkreport.Step("Click Save");
		Thread.sleep(5000);
		 report.create_info("Description is :- "+section.getDescription());
		 
		 assertEquals(section.getDescription(), jsonValue("notes-edit-text"));
	}
	
	@Test(priority = 14,description = "Verify user able to delete Notes from Section")
	public void verifyDeleteNotes_FromSection() throws Exception {
		section.deleteNotes__Bookmark();
		Sparkreport.Step("Click delete");
		Sparkreport.Step("Click Remove");
		
		toc.clickOnMyNotes();
		
		report.create_info("Text in My Notes after delete notes :- " + toc.getTextInMyNotes());
		assertEquals(toc.getTextInMyNotes(), jsonValue("mynotes-text"));
		
	}
	@Test(priority = 15,description = "Verify user able to share bookmark from Section")
	public void verifyShareBookmark_FromSection() throws Exception {
		
		  commanstep.navigetToTitle(jsonArrayValue("I-codes", "section"),
		  jsonArrayValue("I-codes", "sub-section"), jsonArrayValue("I-codes",
		  "title"));
		  Sparkreport.Step("Click menu");
		  Sparkreport.Step("Click Listitem "+jsonArrayValue("I-codes", "section") );
		  Sparkreport.Step("Click subList "+jsonArrayValue("I-codes", "sub-section") );
		  Sparkreport.Step("Click Title "+jsonArrayValue("I-codes", "title") );
		  
		  String actChapter = toc.navigateToChapter(jsonArrayValue("I-codes",
		  "chapter")); 
		  Sparkreport.Step("Click Chapter "+ actChapter);
		  
		  section.clickOnApps(section.getSessionID_FromChapter());
		  Sparkreport.Step("Click Apps");
		 
        
        section.createBookamrk_FromSection(jsonValue("bookmark-text"));
		 Sparkreport.Step("Click Bookmark");
		 Sparkreport.Step("Enter Decription in textBox");
	
		section.selectTag(jsonValue("tag-name"));
		Sparkreport.Step("Click Tag DropDown");
		Sparkreport.Step("Select Tag Name");
		
		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");
		
		Thread.sleep(5000);
		String SuccessfullyMsg=section.shareNotes_Bookmark(jsonValue("username"));
		Sparkreport.Step("Click Share");
		Sparkreport.Step("Enter email");
		Sparkreport.Step("Click Submit");
		report.create_info("Successful message :- "+SuccessfullyMsg);
	
		
		assertEquals(SuccessfullyMsg, jsonValue("share-content-successful"));
	}
	
	@Test(priority = 16, description = "Verify user able to edit Bookamrk from Section")
	public void verifyEditBookmark_FromSection() throws Exception {
		section.editNotes_Bookmark(jsonValue("bookmark-edit-text"));
		Sparkreport.Step("Click edit");
		Sparkreport.Step("Enter edited text");
		Sparkreport.Step("Click Save");
		Thread.sleep(5000);
		 report.create_info("Description is :- "+section.getDescription());
		 
		 assertEquals(section.getDescription(), jsonValue("bookmark-edit-text"));
	}
	@Test(priority = 17, description = "Verify user able to Delete Bookmark from Section")
	public void verifyDeleteBookmark_FromSection() throws Exception {
		section.deleteNotes__Bookmark();
		Sparkreport.Step("Click delete");
		Sparkreport.Step("Click Remove");
		
		toc.clickOnMyNotes();
		report.create_info("Text in My Notes after delete bookmark :- " + toc.getTextInMyNotes());

		assertEquals(toc.getTextInMyNotes(), jsonValue("mynotes-text"));
	}
	
	
	
}
