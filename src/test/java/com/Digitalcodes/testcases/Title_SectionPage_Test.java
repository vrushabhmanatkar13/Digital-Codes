package com.Digitalcodes.testcases;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.TitleSection_Page;


import com.Digitalcodes.pageobject.MenuMyNotesandBookmark_Page;
import com.Digitalcodes.pageobject.TableOfContent_Page;

import com.Digitalcodes.pageobject.TitleLanding_Page;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.Sparkreport;

public class Title_SectionPage_Test extends Prerequisites_Teardown {

	TitleLanding_Page landingpage;
	TableOfContent_Page tableOfContent_Page;
	TitleSection_Page section;

	@BeforeClass(alwaysRun = true)
	public void beforeClassTitlepage() {

		tableOfContent_Page = new TableOfContent_Page();
		section = new TitleSection_Page();

	}
	

	@Test(priority = 1, description = "Verify user able to naviagate Section from TableOfContent_Page", dataProvider = "Title", dataProviderClass = Prerequisites_Teardown.class, groups = {
			"Smoke", "Regression" })
	public void TC08_verifyNavigateToSection_FromTOC(String Section, String Sub_section, String Title, String Chapter)
			throws Exception {

		landingpage = commanstep.navigetToTitle(Section, Sub_section, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + Section);
		Sparkreport.Step("Click  " + Sub_section);
		Sparkreport.Step("Click  " + Title);

		String chapterName = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + chapterName);
		report.create_info("Chapter Name :- " + section.getChapterName());

		assertEquals(section.getChapterName(), chapterName);

	}

	@Test(priority = 2, description = "Verify user able to create notes at section", dataProvider = "Title", dataProviderClass = Prerequisites_Teardown.class, groups = {
			"Smoke", "Regression" })
	public void TC09_verifyCreateNotes_atSection(String Section, String Sub_section, String Title, String Chapter)
			throws Exception {

		landingpage = commanstep.navigetToTitle(Section, Sub_section, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + Section);
		Sparkreport.Step("Click  " + Sub_section);
		Sparkreport.Step("Click  " + Title);

		String chapterName = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + chapterName);

		section.doubleClickOnTitle_Section();
		Sparkreport.Step("Double Click on text");
       
		Thread.sleep(2000);
		section.createNote_FromSection(jsonValue("notes-text"));
		Sparkreport.Step("Click Note icon");
		Sparkreport.Step("Enter Decription in textBox");

		section.createNewTag(Section);
		Sparkreport.Step("Click New Tag");
		Sparkreport.Step("Enter TagName");
		Sparkreport.Step("Click Save");

		Thread.sleep(2000);
		section.selectTag(Section);
		Sparkreport.Step("Click Tag DropDown");
		Sparkreport.Step("Select Tag Name");

		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");

		
		report.create_info("Note Created at :- " + section.getChapterName());
		report.create_info("Tag Name :- " + section.getTagName());
		report.create_info("Description is :- " + section.getDescription());
		report.create_info("Note Created by :- " + section.getCreatedBy());

		assertEquals(section.getTagName(), Section);
		assertEquals(section.getDescription(), jsonValue("notes-text"));
		assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

		tableOfContent_Page.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");

		report.create_info("Chapter Name at my notes :- " + tableOfContent_Page.getChapterNameInMynotes());

		assertEquals(tableOfContent_Page.getChapterNameInMynotes(), Chapter);

		menu.navigateToPremiumToolFeaturs("My notes and Bookmarks");
		MenuMyNotesandBookmark_Page mynote_bookmark = new MenuMyNotesandBookmark_Page();

		assertEquals(mynote_bookmark.getTitleName(), Title);
		assertTrue(mynote_bookmark.verifyChapterName_Decription(Chapter, jsonValue("notes-text")));
		assertTrue(mynote_bookmark.verifyDetails(Login_Test.NAME, Section));

		mynote_bookmark.removeNotes_Bookmark();
		
	}

	@Test(priority = 3, description = "Verify user able to create bookmark at section", dataProvider = "Title", dataProviderClass = Prerequisites_Teardown.class, groups = {
			"Smoke", "Regression" })
	public void TC10_verifyCreateBookmark_atSection(String Section, String Sub_section, String Title, String Chapter)
			throws Exception {

		landingpage = commanstep.navigetToTitle(Section, Sub_section, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + Section);
		Sparkreport.Step("Click  " + Sub_section);
		Sparkreport.Step("Click  " + Title);

		String chapterName = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + chapterName);

		section.clickOnApps(section.getSessionID_FromChapter());
		Sparkreport.Step("Click Apps");
        
		Thread.sleep(2000);
		section.createBookamrk_FromSection(jsonValue("bookmark-text"));
		Sparkreport.Step("Click Bookmark");
		Sparkreport.Step("Enter Decription in textBox");

		Thread.sleep(1000);
		section.selectTag(Section);
		Sparkreport.Step("Click Tag DropDown");
		Sparkreport.Step("Select Tag Name");
		Thread.sleep(1000);
		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");

		

		report.create_info("Tag Name :- " + section.getTagName());
		report.create_info("Note Created at :- " + section.getChapterName());
		report.create_info("Description is :- " + section.getDescription());
		report.create_info("Note Created by :- " + section.getCreatedBy());

		assertEquals(section.getTagName(), Section);
		assertEquals(section.getDescription(), jsonValue("bookmark-text"));
		assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

		tableOfContent_Page.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");
		report.create_info("Chapter Name at my notes :- " + tableOfContent_Page.getChapterNameInMynotes());

		assertEquals(tableOfContent_Page.getChapterNameInMynotes(), Chapter);

		menu.navigateToPremiumToolFeaturs("My notes and Bookmarks");
		MenuMyNotesandBookmark_Page mynote_bookmark = new MenuMyNotesandBookmark_Page();

		assertEquals(mynote_bookmark.getTitleName(), Title);
		assertTrue(mynote_bookmark.verifyChapterName_Decription(Chapter, jsonValue("bookmark-text")));
		assertTrue(mynote_bookmark.verifyDetails(Login_Test.NAME, Section));

		mynote_bookmark.removeNotes_Bookmark();
		

	}

	@Test(priority = 4, description = "Verify user able to share the section", dataProvider = "SingleTitle", groups = {
			"Smoke","Regression" })
	public void TC11_verifyShareSection_FromSection(String Section, String Sub_section, String Title, String Chapter)
			throws Exception {

		landingpage = commanstep.navigetToTitle(Section, Sub_section, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + Section);
		Sparkreport.Step("Click  " + Sub_section);
		Sparkreport.Step("Click  " + Title);

		String chapterName = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + chapterName);

		section.clickOnApps(section.getSessionID_FromChapter());
		Sparkreport.Step("Click Apps");

		String successMessage = section.shareSection(Login_Test.EMAIL);
		Sparkreport.Step("Click Share");
		Sparkreport.Step("Enter email");
		Sparkreport.Step("Click AddMore");
		Sparkreport.Step("Click Remove");
		Sparkreport.Step("Click Share Button");
		Sparkreport.Step("Click Close");
		report.create_info("Successful message :- " + successMessage);
		assertEquals(successMessage, jsonValue("share-successful"));

	}

	@Test(dependsOnMethods = "TC11_verifyShareSection_FromSection", description = "Verify user able to Print Section", groups = {
			"Smoke","Regression" })
	public void TC12_verifyPrintSection_FromSection()
			throws Exception {
		/*
		 * menu.navigateToTitlesCover(Section,Sub_section);
		 * coverpage.clickOnTitlesCover(Title); Sparkreport.Step("Click menu");
		 * Sparkreport.Step("Click Listitem "+Section);
		 * Sparkreport.Step("Click subList "+Sub_section);
		 * Sparkreport.Step("Click Title "+Title);
		 * 
		 * String chapterName = tableOfContent_Page.navigateToChapter(Chapter);
		 * Sparkreport.Step("Click Chapter "+ chapterName);
		 * 
		 * section.clickOnApps(section.getSessionID_FromChapter());
		 * Sparkreport.Step("Click Apps");
		 */

		boolean pdfprint = section.printSection();
		
		 Baseclass.retrunToMainWindow();
		Sparkreport.Step("Click Print");

		assertTrue(pdfprint);

	}

	@Test(priority = 5, description = "Verify user able to share note from section", dataProvider = "SingleTitle", dataProviderClass = Prerequisites_Teardown.class, groups = {
			"Smoke","Regression" })
	public void TC13_verifyShareNote_FromSction(String Section, String Sub_section, String Title, String Chapter)
			throws Exception {
		/*
		 * menu.navigateToTitlesCover(Section,Sub_section);
		 * coverpage.clickOnTitlesCover(Title); Sparkreport.Step("Click menu");
		 * Sparkreport.Step("Click Listitem "+Section);
		 * Sparkreport.Step("Click subList "+Sub_section);
		 * Sparkreport.Step("Click Title "+Title);
		 * 
		 * String chapterName = tableOfContent_Page.navigateToChapter(Chapter);
		 * Sparkreport.Step("Click Chapter "+ chapterName);
		 */

		section.doubleClickOnTitle_Section();
		Sparkreport.Step("Double Click on text");

		section.createNote_FromSection(jsonValue("notes-text"));
		Sparkreport.Step("Click Note icon");
		Sparkreport.Step("Enter Decription in textBox");

		Thread.sleep(1000);
		section.selectTag(Section);
		Sparkreport.Step("Click Tag DropDown");
		Sparkreport.Step("Select Tag Name");

		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");

		
		String SuccessfullyMsg = section.shareNotes_Bookmark(Login_Test.EMAIL);
		Sparkreport.Step("Click Share");
		Sparkreport.Step("Enter email");
		Sparkreport.Step("Click Submit");
		report.create_info("Successful message :- " + SuccessfullyMsg);

		assertEquals(SuccessfullyMsg, jsonValue("share-content-successful"));

	}

	@Test(description = "Verify user able to edit notes from Section", dependsOnMethods = "TC13_verifyShareNote_FromSction", groups = {
			"Smoke","Regression" })
	public void TC14_verifyEditNotes_FromSection() throws Exception {

		section.editNotes_Bookmark(jsonValue("notes-edit-text"));
		Sparkreport.Step("Click edit");
		Sparkreport.Step("Enter edited text");
		Sparkreport.Step("Click Save");
		
		Thread.sleep(2000);
		report.create_info("Description is :- " + section.getDescription());
         
		assertEquals(section.getDescription(), jsonValue("notes-edit-text"));
	}

	@Test(description = "Verify user able to delete Notes from Section", dependsOnMethods = "TC14_verifyEditNotes_FromSection", groups = {
			"Smoke","Regression" })
	public void TC15_verifyDeleteNotes_FromSection() throws Exception {

		boolean note_bookmakr=section.Notes_BookmarkisDisplayed();
		section.deleteNotes__Bookmark();
		Sparkreport.Step("Click delete");
		Sparkreport.Step("Click Remove");

		tableOfContent_Page.clickOnMyNotes();
        
		report.create_info("Text in My Notes after delete notes :- " + tableOfContent_Page.getTextInMyNotes());
		assertEquals(tableOfContent_Page.getTextInMyNotes(), jsonValue("mynotes-text"));
		assertFalse(note_bookmakr);

	}

	@Test(priority = 6, description = "Verify user able to share bookmark from Section", dataProvider = "SingleTitle", dataProviderClass = Prerequisites_Teardown.class, groups = {
			"Regression" })
	public void TC16_verifyShareBookmark_FromSection(String Section, String Sub_section, String Title, String Chapter)
			throws Exception {

		landingpage = commanstep.navigetToTitle(Section, Sub_section, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + Section);
		Sparkreport.Step("Click  " + Sub_section);
		Sparkreport.Step("Click  " + Title);

		String chapterName = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + chapterName);

		section.clickOnApps(section.getSessionID_FromChapter());
		Sparkreport.Step("Click Apps");

		section.createBookamrk_FromSection(jsonValue("bookmark-text"));
		Sparkreport.Step("Click Bookmark");
		Sparkreport.Step("Enter Decription in textBox");

		Thread.sleep(1000);
		section.selectTag(Section);
		Sparkreport.Step("Click Tag DropDown");
		Sparkreport.Step("Select Tag Name");

		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");

		
		String SuccessfullyMsg = section.shareNotes_Bookmark(Login_Test.EMAIL);
		Sparkreport.Step("Click Share");
		Sparkreport.Step("Enter email");
		Sparkreport.Step("Click Submit");
		report.create_info("Successful message :- " + SuccessfullyMsg);

		assertEquals(SuccessfullyMsg, jsonValue("share-content-successful"));
	}

	@Test( description = "Verify user able to edit Bookamrk from Section", dependsOnMethods = "TC16_verifyShareBookmark_FromSection", groups = {
			"Regression" })
	public void TC17_verifyEditBookmark_FromSection() throws Exception {

		section.editNotes_Bookmark(jsonValue("bookmark-edit-text"));
		Sparkreport.Step("Click edit");
		Sparkreport.Step("Enter edited text");
		Sparkreport.Step("Click Save");
		
		report.create_info("Description is :- " + section.getDescription());

		assertEquals(section.getDescription(), jsonValue("bookmark-edit-text"));
	}

	@Test(description = "Verify user able to Delete Bookmark from Section", dependsOnMethods = "TC17_verifyEditBookmark_FromSection", groups = {
			"Regression" })
	public void TC18_verifyDeleteBookmark_FromSection() throws Exception {

		boolean note_bookmakr=section.Notes_BookmarkisDisplayed();
		section.deleteNotes__Bookmark();
		Sparkreport.Step("Click delete");
		Sparkreport.Step("Click Remove");

		tableOfContent_Page.clickOnMyNotes();
		report.create_info("Text in My Notes after delete bookmark :- " + tableOfContent_Page.getTextInMyNotes());

		assertEquals(tableOfContent_Page.getTextInMyNotes(), jsonValue("mynotes-text"));
		assertFalse(note_bookmakr);
	}

	@Test(priority = 7, description = "Verify links On Chapter", dataProvider = "SingleTitle", dataProviderClass = Prerequisites_Teardown.class, groups = {
			"Smoke","Regression" })
	public void TC19_verifyLinksOnChapter(String Section, String Sub_section, String Title, String Chapter)
			throws Exception {

		landingpage = commanstep.navigetToTitle(Section, Sub_section, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + Section);
		Sparkreport.Step("Click  " + Sub_section);
		Sparkreport.Step("Click  " + Title);

		String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + actChapter);

		report.create_info("Text of Link is :- " + section.getTitlelinkText());
		
		boolean page = section.clickOnTitleLink();
		Sparkreport.Step("Click on title Link");
		Baseclass.navigateToBack();
		Sparkreport.Step("Navigate to back");

		String subchap1 = tableOfContent_Page.navigateToSubChapter(1);
		Sparkreport.Step("Click Sub Chapter " + subchap1);
        
		report.create_info("Text of Link is :- "+ section.getChapterlinkText());
		
		boolean chapter = section.clickOnChapterLink();
		Sparkreport.Step("Click on chapter Link");
		Baseclass.navigateToBack();
		Sparkreport.Step("Navigate to back");

		assertTrue(page);
		assertTrue(chapter);
	}
	
	@Test(priority = 8,description = "Verify user able to create notes at Sub-Section", dataProvider = "SingleTitle",dataProviderClass =Prerequisites_Teardown.class, groups = {
	"Regression" })
	public void TC20_verifyCreateNotes_atSubsection(String Section, String Sub_section, String Title, String Chapter) throws Exception {
		landingpage = commanstep.navigetToTitle(Section, Sub_section, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + Section);
		Sparkreport.Step("Click  " + Sub_section);
		Sparkreport.Step("Click  " + Title);

		String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + actChapter);
		
		String subsection=tableOfContent_Page.navigateToSubChapter(0);
		Sparkreport.Step("Click Sub Section " + subsection);
		
		section.doubleClickOnTitle_Subsection();
		Sparkreport.Step("Double Click ");
		
		
		section.createNote_FromSubSection(section.getSessionID_FromSubChapter(),jsonValue("notes-text"));
		Sparkreport.Step("Click Note icon");
		Sparkreport.Step("Enter Decription in textBox");
		
		Thread.sleep(1000);
		section.selectTag(Section);
		Sparkreport.Step("Select Tag Name");
		
		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");
		
		
		report.create_info("Note Created at :- " + subsection);
		report.create_info("Tag Name :- " + section.getTagName());
		report.create_info("Description is :- " + section.getDescription());
		report.create_info("Note Created by :- " + section.getCreatedBy());

		assertEquals(section.getTagName(), Section);
		assertEquals(section.getDescription(), jsonValue("notes-text"));
		assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

		tableOfContent_Page.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");

		report.create_info("Chapter Name at my notes :- " + tableOfContent_Page.getChapterNameInMynotes());
        assertEquals(tableOfContent_Page.getChapterNameInMynotes(), subsection);

		menu.navigateToPremiumToolFeaturs("My notes and Bookmarks");
		MenuMyNotesandBookmark_Page mynote_bookmark = new MenuMyNotesandBookmark_Page();
        
		assertEquals(mynote_bookmark.getTitleName(), Title);
		assertTrue(mynote_bookmark.verifyChapterName_Decription(Chapter, jsonValue("notes-text")));
		assertTrue(mynote_bookmark.verifyDetails(Login_Test.NAME, Section));

		mynote_bookmark.removeNotes_Bookmark();
		
		
	}
	
	@Test(priority = 9,description = "Verify user able to create Bookmark at Sub-Section", dataProvider = "SingleTitle",dataProviderClass =Prerequisites_Teardown.class, groups = {
			"Regression" })
			public void TC21_verifyCreateBookmark_atSubsection(String Section, String Sub_section, String Title, String Chapter) throws Exception {
				landingpage = commanstep.navigetToTitle(Section, Sub_section, Title);
				Sparkreport.Step("Click menu");
				Sparkreport.Step("Click  " + Section);
				Sparkreport.Step("Click  " + Sub_section);
				Sparkreport.Step("Click  " + Title);

				String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
				Sparkreport.Step("Click Chapter " + actChapter);
				
				String subsection=tableOfContent_Page.navigateToSubChapter(0);
				Sparkreport.Step("Click Sub Section " + subsection);
				
				Thread.sleep(1000);
				
				section.creteBookmark_FromSubSection(section.getSessionID_FromSubChapter(),jsonValue("bookmark-text"));
				Sparkreport.Step("Click Bookmark icon");
				Sparkreport.Step("Enter Decription in textBox");
				
				Thread.sleep(1000);
				section.selectTag(Section);
				Sparkreport.Step("Select Tag Name");
				
				section.clickOnSaveButton();
				Sparkreport.Step("Click Save");
				
				
				report.create_info("Bookmark Created at :- " + subsection);
				report.create_info("Tag Name :- " + section.getTagName());
				report.create_info("Description is :- " + section.getDescription());
				report.create_info("Note Created by :- " + section.getCreatedBy());

				assertEquals(section.getTagName(), Section);
				assertEquals(section.getDescription(), jsonValue("bookmark-text"));
				assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

				tableOfContent_Page.clickOnMyNotes();
				Sparkreport.Step("Click My Notes");

				report.create_info("Chapter Name at my notes :- " + tableOfContent_Page.getChapterNameInMynotes());
		        assertEquals(tableOfContent_Page.getChapterNameInMynotes(), subsection);

				menu.navigateToPremiumToolFeaturs("My notes and Bookmarks");
				MenuMyNotesandBookmark_Page mynote_bookmark = new MenuMyNotesandBookmark_Page();
		        
				assertEquals(mynote_bookmark.getTitleName(), Title);
				assertTrue(mynote_bookmark.verifyChapterName_Decription(subsection, jsonValue("bookmark-text")));
				assertTrue(mynote_bookmark.verifyDetails(Login_Test.NAME, Section));

				mynote_bookmark.removeNotes_Bookmark();
				
				
			}
	
			@Test(priority = 10, description = "Verify user able to share the Subsection", dataProvider = "SingleTitle", groups = {
					"Regression" })
			public void TC22_verifyShareSection_FromSubSection(String Section, String Sub_section, String Title,String Chapter) throws Exception {

				landingpage = commanstep.navigetToTitle(Section, Sub_section, Title);
				Sparkreport.Step("Click menu");
				Sparkreport.Step("Click  " + Section);
				Sparkreport.Step("Click  " + Sub_section);
				Sparkreport.Step("Click  " + Title);

				String chapterName = tableOfContent_Page.navigateToChapter(Chapter);
				Sparkreport.Step("Click Chapter " + chapterName);
                 
				String subsection=tableOfContent_Page.navigateToSubChapter(0);
				Sparkreport.Step("Click Sub Section " + subsection);
			

				String successMessage = section.shareSubSection(section.getSessionID_FromSubChapter(),Login_Test.EMAIL);
				Sparkreport.Step("Click Share");
				Sparkreport.Step("Enter email");
				Sparkreport.Step("Click AddMore");
				Sparkreport.Step("Click Remove");
				Sparkreport.Step("Click Share Button");
				Sparkreport.Step("Click Close");
				report.create_info("Successful message :- " + successMessage);
				assertEquals(successMessage, jsonValue("share-successful"));

			}

			@Test(priority = 11, description = "Verify user able to Print SubSection", groups = { "Regression" })
			public void TC23_verifyPrintSection_FromSubSection() throws Exception {

				boolean pdfprint = section.printThisSubSection(section.getSessionID_FromSubChapter());
				Sparkreport.Step("Click Print");
				Sparkreport.Step("Click Print This Section");
				assertTrue(pdfprint);
				boolean pdfprint1 = section.printSubSection(section.getSessionID_FromSubChapter());
				Sparkreport.Step("Click Print");
				Sparkreport.Step("Click Print All Section");
				assertTrue(pdfprint1);
			}
			
			
			@Test(priority = 12,description = "Verify user able to create notes at ChildSub-Section", dataProvider = "SingleTitle",dataProviderClass =Prerequisites_Teardown.class, groups = {
					"Regression" })
			 public void TC24_verifyCreateNotes_atChildSubsection(String Section, String Sub_section, String Title, String Chapter) throws Exception {
				landingpage = commanstep.navigetToTitle(Section, Sub_section, Title);
				Sparkreport.Step("Click menu");
				Sparkreport.Step("Click  " + Section);
				Sparkreport.Step("Click  " + Sub_section);
				Sparkreport.Step("Click  " + Title);

				String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
				Sparkreport.Step("Click Chapter " + actChapter);
				
				tableOfContent_Page.navigateToSubChapter(0);
				Sparkreport.Step("Click Sub Section ");
				
				String childsection=section.getChildSubSectionName();
				
				section.doubleClikcOn_ChildSection();
				Sparkreport.Step("Double Click ");
				
				
				section.creatNote_FromChildSubSection(section.getSectionID_FromChildSection(),jsonValue("notes-text"));
				Sparkreport.Step("Click Note icon");
				Sparkreport.Step("Enter Decription in textBox");
				
				Thread.sleep(1000);
				section.selectTag(Section);
				Sparkreport.Step("Select Tag Name");
				Thread.sleep(1000);
				section.clickOnSaveButton();
				Sparkreport.Step("Click Save");
				
				
				report.create_info("Note Created at :- " + childsection);
				report.create_info("Tag Name :- " + section.getTagName());
				report.create_info("Description is :- " + section.getDescription());
				report.create_info("Note Created by :- " + section.getCreatedBy());

				assertEquals(section.getTagName(), Section);
				assertEquals(section.getDescription(), jsonValue("notes-text"));
				assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

				tableOfContent_Page.clickOnMyNotes();
				Sparkreport.Step("Click My Notes");
				report.create_info("Chapter Name at my notes :- " + tableOfContent_Page.getChapterNameInMynotes());
		        assertEquals(tableOfContent_Page.getChapterNameInMynotes().replaceAll(" ", ""), childsection.replaceAll(" ", ""));

				menu.navigateToPremiumToolFeaturs("My notes and Bookmarks");
				MenuMyNotesandBookmark_Page mynote_bookmark = new MenuMyNotesandBookmark_Page();
		        
				assertEquals(mynote_bookmark.getTitleName(), Title);
				assertTrue(mynote_bookmark.verifyChapterName_Decription(Chapter, jsonValue("notes-text")));
				assertTrue(mynote_bookmark.verifyDetails(Login_Test.NAME, Section));

				mynote_bookmark.removeNotes_Bookmark();
				
				
			}
			
			@Test(priority = 13,description = "Verify user able to create Bookmark at ChildSub-Section", dataProvider = "SingleTitle",dataProviderClass =Prerequisites_Teardown.class, groups = {
					"Regression" })
					public void TC25_verifyCreateBookmark_atChildSubsection(String Section, String Sub_section, String Title, String Chapter) throws Exception {
					  commanstep.navigetToTitle(Section, Sub_section, Title);
						Sparkreport.Step("Click menu");
						Sparkreport.Step("Click  " + Section);
						Sparkreport.Step("Click  " + Sub_section);
						Sparkreport.Step("Click  " + Title);

						String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
						Sparkreport.Step("Click Chapter " + actChapter);
						
						tableOfContent_Page.navigateToSubChapter(0);
						Sparkreport.Step("Click Sub Section ");
						
						String childsection=section.getChildSubSectionName();
						
						
						section.clickOnApps(section.getSectionID_FromChildSection());
						Sparkreport.Step("Click Apps");
						
						section.creteBookmark_FromSubSection(section.getSectionID_FromChildSection(),jsonValue("bookmark-text"));
						Sparkreport.Step("Click Bookmark icon");
						Sparkreport.Step("Enter Decription in textBox");
						
						
						section.selectTag(Section);
						Sparkreport.Step("Select Tag Name");
						
						section.clickOnSaveButton();
						Sparkreport.Step("Click Save");
						
						
						report.create_info("Bookmark Created at :- " + childsection);
						report.create_info("Tag Name :- " + section.getTagName());
						report.create_info("Description is :- " + section.getDescription());
						report.create_info("Note Created by :- " + section.getCreatedBy());

						assertEquals(section.getTagName(), Section);
						assertEquals(section.getDescription(), jsonValue("bookmark-text"));
						assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

						tableOfContent_Page.clickOnMyNotes();
						Sparkreport.Step("Click My Notes");
						
                         String Childsection=tableOfContent_Page.getChapterNameInMynotes();
						report.create_info("Chapter Name at my notes :- " + Childsection);
						 assertEquals(Childsection.replaceAll(" ", ""), childsection.replaceAll(" ", ""));

						menu.navigateToPremiumToolFeaturs("My notes and Bookmarks");
						MenuMyNotesandBookmark_Page mynote_bookmark = new MenuMyNotesandBookmark_Page();
				        
						assertEquals(mynote_bookmark.getTitleName(), Title);
						assertTrue(mynote_bookmark.verifyChapterName_Decription(Childsection, jsonValue("bookmark-text")));
						assertTrue(mynote_bookmark.verifyDetails(Login_Test.NAME, Section));

						mynote_bookmark.removeNotes_Bookmark();
						
						
					}
			
			@Test(priority = 14,description = "Verify user able to share section at ChildSub-Section", dataProvider = "SingleTitle",dataProviderClass =Prerequisites_Teardown.class, groups = {
					"Regression" })
			public void TC26_verifyShareSection_atChildSubsection(String Section, String Sub_section, String Title, String Chapter) throws Exception {
			          
				commanstep.navigetToTitle(Section, Sub_section, Title);
				Sparkreport.Step("Click menu");
				Sparkreport.Step("Click  " + Section);
				Sparkreport.Step("Click  " + Sub_section);
				Sparkreport.Step("Click  " + Title);

				String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
				Sparkreport.Step("Click Chapter " + actChapter);
				
				tableOfContent_Page.navigateToSubChapter(0);
				Sparkreport.Step("Click Sub Section ");
				
				section.getChildSubSectionName();
				
				
				section.clickOnApps(section.getSectionID_FromChildSection());
				Sparkreport.Step("Click Apps");
				
				String message =section.shareChildSubSection(section.getSectionID_FromChildSection(), Login_Test.EMAIL);
				Sparkreport.Step("Click Share Icon ");
				Sparkreport.Step("Enter Email Address");
				Sparkreport.Step("Click on Share");
				report.create_info("Message after share Section :- "+message);
				
				assertEquals(message, jsonValue("share-successful"));
				
			
			}
			
			@Test(priority = 15, description = "Verify user able to Print SubSection", groups = { "Regression" })
			public void TC27_verifyPrintSection_FromChildSubSection() throws Exception {

				boolean pdf = section.printChildSubSection(section.getSectionID_FromChildSection());
				Sparkreport.Step("Click Print");
				
				assertTrue(pdf);
			}
	
	

}