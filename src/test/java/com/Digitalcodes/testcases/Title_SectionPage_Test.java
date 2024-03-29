package com.Digitalcodes.testcases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.TitleSection_Page;

import com.Digitalcodes.pageobject.MenuMyNotesandBookmark_Page;
import com.Digitalcodes.pageobject.PremiumTools_SharingHistory_Page;
import com.Digitalcodes.pageobject.TableOfContent_Page;

import com.Digitalcodes.pageobject.TitleLanding_Page;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.DataProviders;
import com.Digitalcodes.utilities.Sparkreport;

public class Title_SectionPage_Test extends Prerequisites_Teardown {

	TitleLanding_Page landingpage;
	TableOfContent_Page tableOfContent_Page;
	TitleSection_Page section;
	MenuMyNotesandBookmark_Page mynote_bookmark;
	PremiumTools_SharingHistory_Page sharinghistory;

	private String email;

	@BeforeClass(alwaysRun = true)
	public void beforeClassTitlepage() {

		tableOfContent_Page = new TableOfContent_Page();
		section = new TitleSection_Page();
		mynote_bookmark = new MenuMyNotesandBookmark_Page();
		sharinghistory = new PremiumTools_SharingHistory_Page();
		email = RandomStringUtils.randomAlphabetic(8).toLowerCase() + "@iccsafe.info";
	}

	@Test(priority = 1, description = "Verify user able to create notes at section", dataProvider = "Premium Complete Titles", dataProviderClass = DataProviders.class, groups = {
			"Smoke", "Regression", "Section" })
	public void TC09_verifyCreateNotes_atSection(String option_L1, String option_L2, String option_L3, String Title, String Chapter,
			String tag)

			throws Exception {

		landingpage = commanstep.navigetToTitle(option_L1, option_L2,option_L3, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + option_L1);
		Sparkreport.Step("Click  " + option_L2);
		Sparkreport.Step("Click  " + option_L3);
		Sparkreport.Step("Click  " + Title);

		String chapterName = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + chapterName);

		report.create_info("Chapter Name :- " + section.getChapterName());

		assertEquals(section.getChapterName(), chapterName);

		section.doubleClickOnTitle_Section();
		Sparkreport.Step("Double Click on text");

		section.clickNotesIcon();
		Thread.sleep(1000);
		section.createNote_FromSection(jsonValue("notes-text"));
		Sparkreport.Step("Click Note icon");
		Sparkreport.Step("Enter Decription in textBox");

		section.createNewTag(option_L2);
		Sparkreport.Step("Click New Tag");
		Sparkreport.Step("Enter TagName");
		Sparkreport.Step("Click Save");

		Thread.sleep(3000);
		section.selectTag(option_L2);
		Sparkreport.Step("Click Tag DropDown");
		Sparkreport.Step("Select Tag Name");

		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");
		Thread.sleep(1000);
		// Baseclass.refreshBrowser();
		Sparkreport.Step("Note Created at :- " + section.getChapterName());
		Sparkreport.Step("Tag Name :- " + section.getTagName());
		Sparkreport.Step("Description is :- " + section.getDescription());
		Sparkreport.Step("Note Created by :- " + section.getCreatedBy());

		assertEquals(section.getTagName(), option_L2);
		assertEquals(section.getDescription(), jsonValue("notes-text"));
		assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

		tableOfContent_Page.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");
		Sparkreport.Step("Chapter Name in my notes :- " + tableOfContent_Page.getChapterNameInMynotes());

		assertEquals(tableOfContent_Page.getChapterNameInMynotes(), Chapter);

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("My notes and Bookmarks");

		assertEquals(mynote_bookmark.getTitleName(Title), Title);
		assertTrue(mynote_bookmark.verifyChapterName_Decription(Title, Chapter, jsonValue("notes-text")));
		assertTrue(mynote_bookmark.verifyDetails(Chapter, Login_Test.NAME, option_L2));

		mynote_bookmark.removeNotes_Bookmark(jsonValue("notes-text"));

	}

	@Test(priority = 2, description = "Verify user able to create bookmark at section", dataProvider = "Premium Complete Titles", dataProviderClass = DataProviders.class, groups = {
			"Smoke", "Regression" })

	public void TC10_verifyCreateBookmark_atSection(String option_L1, String option_L2, String option_L3, String Title, String Chapter,
			String tag)

			throws Exception {

		landingpage = commanstep.navigetToTitle(option_L1, option_L2,option_L3, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + option_L1);
		Sparkreport.Step("Click  " + option_L2);
		Sparkreport.Step("Click  " + Title);

		String chapterName = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + chapterName);

		section.clickOnApps(section.getSessionID_FromChapter());
		Sparkreport.Step("Click Apps");

		section.clickBookmarkIcon();
		Thread.sleep(1000);
		section.createBookamrk_FromSection(jsonValue("bookmark-text"));
		Sparkreport.Step("Click Bookmark");
		Sparkreport.Step("Enter Decription in textBox");

		section.selectTag(option_L2);
		Sparkreport.Step("Click Tag DropDown");
		Sparkreport.Step("Select Tag Name");

		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");
		Thread.sleep(2000);
		report.create_info("Tag Name :- " + section.getTagName());
		report.create_info("Note Created at :- " + section.getChapterName());
		report.create_info("Description is :- " + section.getDescription());
		report.create_info("Note Created by :- " + section.getCreatedBy());

		Baseclass.refreshBrowser();
		assertEquals(section.getTagName(), option_L2);
		assertEquals(section.getDescription(), jsonValue("bookmark-text"));
		assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

		tableOfContent_Page.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");
		report.create_info("Chapter Name at my notes :- " + tableOfContent_Page.getChapterNameInMynotes());

		assertEquals(tableOfContent_Page.getChapterNameInMynotes(), Chapter);

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("My notes and Bookmarks");

		assertEquals(mynote_bookmark.getTitleName(Title), Title);
		assertTrue(mynote_bookmark.verifyChapterName_Decription(Title, Chapter, jsonValue("bookmark-text")));
		assertTrue(mynote_bookmark.verifyDetails(Chapter, Login_Test.NAME, option_L2));

		mynote_bookmark.removeNotes_Bookmark(jsonValue("bookmark-text"));

	}

	@Test(priority = 3, description = "Verify user able to share the section", dataProvider = "PC first title", dataProviderClass = DataProviders.class, groups = {
			"Smoke", "Regression" })

	public void TC11_verifyShareSection_FromSection(String option_L1, String option_L2, String option_L3, String Title, String Chapter,
			String tag)

			throws Exception {
		Baseclass.refreshBrowser();
		landingpage = commanstep.navigetToTitle(option_L1, option_L2,option_L3, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + option_L1);
		Sparkreport.Step("Click  " + option_L2);
		Sparkreport.Step("Click  " + Title);

		String chapterName = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + chapterName);

		section.clickOnApps(section.getSessionID_FromChapter());
		Sparkreport.Step("Click Apps");

		section.clickShareIcon();
		String successMessage = section.shareSection(email);
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
			"Smoke", "Regression" })
	public void TC11_verifyPrintSection_FromSection() throws Exception {
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

	@Test(priority = 4, description = "Verify user able to share note from section", dataProvider = "PC first title", dataProviderClass = DataProviders.class, groups = {
			"Smoke", "Regression" })

	public void TC13_verifyShareNote_FromSction(String option_L1, String option_L2, String option_L3, String Title, String Chapter,
			String tag)

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
		section.clickNotesIcon();
		section.createNote_FromSection(jsonValue("notes-text"));
		Sparkreport.Step("Click Note icon");
		Sparkreport.Step("Enter Decription in textBox");

		Thread.sleep(1000);
		// section.selectTag(Section);
		Sparkreport.Step("Click Tag DropDown");
		Sparkreport.Step("Select Tag Name");

		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");

		String SuccessfullyMsg = section.shareNotes_Bookmark(email);
		Sparkreport.Step("Click Share");
		Sparkreport.Step("Enter email");
		Sparkreport.Step("Click Submit");
		Sparkreport.Step("Click Close");
		report.create_info("Successful message :- " + SuccessfullyMsg);

		assertEquals(SuccessfullyMsg, jsonValue("share-content-successful"));

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Sharing History");
		
		sharinghistory.clickOnvIcon();
		Thread.sleep(2000);
		assertEquals(sharinghistory.titleName(), Title);
		assertEquals(sharinghistory.getSectionName(), Chapter);
		assertEquals(sharinghistory.getDescription(), jsonValue("notes-text"));
		assertEquals(sharinghistory.getTagName(), " Default");
		assertEquals(sharinghistory.getSharedwith(), email + "(Pending)");

	}

	@Test(description = "Verify user able to edit notes from Section", dependsOnMethods = "TC13_verifyShareNote_FromSction", groups = {
			"Smoke", "Regression" })

	public void TC14_verifyEditNotes_FromSection() throws Exception {
		Baseclass.navigateToBack();
		menu.clickOnMainMenu();
		menu.closemenu();

		section.editNotes_Bookmark(jsonValue("notes-edit-text"));
		Sparkreport.Step("Click edit");
		Sparkreport.Step("Enter edited text");
		Sparkreport.Step("Click Save");
		Thread.sleep(2000);
		report.create_info("Description is :- " + section.getDescription());

		assertEquals(section.getDescription(), jsonValue("notes-edit-text"));

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Sharing History");
		
		sharinghistory.clickOnvIcon();
		assertEquals(sharinghistory.getDescription(), jsonValue("notes-edit-text"));
	}

	@Test(description = "Verify user able to delete Notes from Section", dependsOnMethods = "TC14_verifyEditNotes_FromSection", groups = {
			"Smoke", "Regression" })

	public void TC15_verifyDeleteNotes_FromSection() throws Exception {
		Baseclass.navigateToBack();
		menu.clickOnMainMenu();
		menu.closemenu();

		section.deleteNotes__Bookmark();
		refreshBrowser();
		boolean note_bookmakr = section.Notes_BookmarkisDisplayed();
		Sparkreport.Step("Click delete");
		Sparkreport.Step("Click Remove");
		
		tableOfContent_Page.clickOnMyNotes();
		report.create_info("Text in My Notes after delete notes :- " + tableOfContent_Page.getTextInMyNotes());

		assertEquals(tableOfContent_Page.getTextInMyNotes(), jsonValue("mynotes-text"));

		assertEquals(tableOfContent_Page.getTextInMyNotes(), jsonValue("mynotes-text"));

		assertFalse(note_bookmakr);
		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Sharing History");
		assertEquals(sharinghistory.getNoResultText(), "No Results Found");
	}

	@Test(priority = 5, description = "Verify user able to share bookmark from Section", dataProvider = "PC first title", dataProviderClass = DataProviders.class, groups = {
			"Smoke", "Regression" })
	public void TC16_verifyShareBookmark_FromSection(String option_L1, String option_L2, String option_L3, String Title, String Chapter,
			String tag)

			throws Exception {

		landingpage = commanstep.navigetToTitle(option_L1, option_L2,option_L3, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + option_L1);
		Sparkreport.Step("Click  " + option_L2);
		Sparkreport.Step("Click  " + Title);

		String chapterName = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + chapterName);

		section.clickOnApps(section.getSessionID_FromChapter());
		Sparkreport.Step("Click Apps");

		section.clickBookmarkIcon();
		section.createBookamrk_FromSection(jsonValue("bookmark-text"));
		Sparkreport.Step("Click Bookmark");
		Sparkreport.Step("Enter Decription in textBox");

		Thread.sleep(1000);
		// section.selectTag(Section);
		Sparkreport.Step("Click Tag DropDown");
		Sparkreport.Step("Select Tag Name");

		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");

		String SuccessfullyMsg = section.shareNotes_Bookmark(email);
		Sparkreport.Step("Click Share");
		Sparkreport.Step("Enter email");
		Sparkreport.Step("Click Submit");
		report.create_info("Successful message :- " + SuccessfullyMsg);
		assertEquals(SuccessfullyMsg, jsonValue("share-content-successful"));

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Sharing History");
		sharinghistory.clickOnvIcon();
		Thread.sleep(2000);
		assertEquals(sharinghistory.titleName(), Title);
		assertEquals(sharinghistory.getSectionName(), Chapter);
		assertEquals(sharinghistory.getDescription(), jsonValue("bookmark-text"));
		assertEquals(sharinghistory.getTagName(), " Default");
		assertEquals(sharinghistory.getSharedwith(), email + "(Pending)");
	}

	@Test(description = "Verify user able to edit Bookamrk from Section", dependsOnMethods = "TC16_verifyShareBookmark_FromSection", groups = {
			"Smoke", "Regression" })
	public void TC17_verifyEditBookmark_FromSection() throws Exception {
		Baseclass.navigateToBack();
		menu.clickOnMainMenu();
		menu.closemenu();

		section.editNotes_Bookmark(jsonValue("bookmark-edit-text"));
		Sparkreport.Step("Click edit");
		Sparkreport.Step("Enter edited text");
		Sparkreport.Step("Click Save");

		Thread.sleep(2000);
		report.create_info("Description is :- " + section.getDescription());

		assertEquals(section.getDescription(), jsonValue("bookmark-edit-text"));
		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Sharing History");
		sharinghistory.clickOnvIcon();
		assertEquals(sharinghistory.getDescription(), jsonValue("bookmark-edit-text"));
	}

	@Test(description = "Verify user able to Delete Bookmark from Section", dependsOnMethods = "TC17_verifyEditBookmark_FromSection", groups = {
			"Smoke", "Regression" })
	public void TC18_verifyDeleteBookmark_FromSection() throws Exception {
		Baseclass.navigateToBack();
		menu.clickOnMainMenu();
		menu.closemenu();

		section.deleteNotes__Bookmark();
		refreshBrowser();
		boolean note_bookmakr = section.Notes_BookmarkisDisplayed();
		Sparkreport.Step("Click delete");
		Sparkreport.Step("Click Remove");
		
		tableOfContent_Page.clickOnMyNotes();
		report.create_info("Text in My Notes after delete bookmark :- " + tableOfContent_Page.getTextInMyNotes());

		assertEquals(tableOfContent_Page.getTextInMyNotes(), jsonValue("mynotes-text"));
		assertFalse(note_bookmakr);
		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Sharing History");
		assertEquals(sharinghistory.getNoResultText(), "No Results Found");
	}

	@Test(priority = 6, description = "Verify links On Chapter", dataProvider = "PC first title", dataProviderClass = DataProviders.class, groups = {
			"Smoke", "Regression" })

	public void TC19_verifyLinksOnChapter(String option_L1, String option_L2, String option_L3, String Title, String Chapter, String tag)

			throws Exception {

		commanstep.navigetToTitle(option_L1, option_L2,option_L3, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + option_L1);
		Sparkreport.Step("Click  " + option_L2);
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

		report.create_info("Text of Link is :- " + section.getChapterlinkText());

		boolean chapter = section.clickOnChapterLink();
		Sparkreport.Step("Click on chapter Link");
		Baseclass.navigateToBack();
		Sparkreport.Step("Navigate to back");

		assertTrue(page);
		assertTrue(chapter);
	}

	@Test(priority = 7, description = "Verify user able to create notes at Sub-Section", dataProvider = "PC first title", dataProviderClass = DataProviders.class, groups = {
			"Regression" })

	public void TC20_verifyCreateNotes_atSubsection(String option_L1, String option_L2, String option_L3, String Title, String Chapter,
			String tag)

			throws Exception {
		commanstep.navigetToTitle(option_L1, option_L2,option_L3, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + option_L1);
		Sparkreport.Step("Click  " + option_L2);
		Sparkreport.Step("Click  " + Title);

		String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + actChapter);

		String subsection = tableOfContent_Page.navigateToSubChapter(0);
		Sparkreport.Step("Click Sub Section " + subsection);

		section.doubleClickOnTitle_Subsection();
		Sparkreport.Step("Double Click ");

		section.createNote_FromSubSection(section.getSessionID_FromSubChapter(), jsonValue("notes-text"));
		Sparkreport.Step("Click Note icon");
		Sparkreport.Step("Enter Decription in textBox");

		Thread.sleep(1000);
		section.selectTag(option_L2);
		Sparkreport.Step("Select Tag Name");

		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");
		Thread.sleep(1000);
		report.create_info("Note Created at :- " + subsection);
		report.create_info("Tag Name :- " + section.getTagName());
		report.create_info("Description is :- " + section.getDescription());
		report.create_info("Note Created by :- " + section.getCreatedBy());
		Baseclass.refreshBrowser();
		assertEquals(section.getTagName(), option_L2);
		assertEquals(section.getDescription(), jsonValue("notes-text"));
		assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

		tableOfContent_Page.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");

		report.create_info("Chapter Name at my notes :- " + tableOfContent_Page.getChapterNameInMynotes());
		assertEquals(tableOfContent_Page.getChapterNameInMynotes(), subsection);

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("My notes and Bookmarks");

		assertEquals(mynote_bookmark.getTitleName(Title), Title);
		assertTrue(mynote_bookmark.verifyChapterName_Decription(Title, Chapter, jsonValue("notes-text")));
		assertTrue(mynote_bookmark.verifyDetails(Chapter, Login_Test.NAME, option_L2));

		mynote_bookmark.removeNotes_Bookmark(jsonValue("notes-text"));

	}

	@Test(priority = 8, description = "Verify user able to create Bookmark at Sub-Section", dataProvider = "PC first title", dataProviderClass = DataProviders.class, groups = {
			"Regression" })

	public void TC21_verifyCreateBookmark_atSubsection(String option_L1, String option_L2, String option_L3, String Title, String Chapter,
			String tag)

			throws Exception {
		commanstep.navigetToTitle(option_L1, option_L2,option_L3, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + option_L1);
		Sparkreport.Step("Click  " + option_L2);
		Sparkreport.Step("Click  " + Title);

		String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + actChapter);

		String subsection = tableOfContent_Page.navigateToSubChapter(0);
		Sparkreport.Step("Click Sub Section " + subsection);
		Thread.sleep(1000);
		section.creteBookmark_FromSubSection(section.getSessionID_FromSubChapter(), jsonValue("bookmark-text"));
		Sparkreport.Step("Click Bookmark icon");
		Sparkreport.Step("Enter Decription in textBox");

		section.selectTag(option_L2);
		Sparkreport.Step("Select Tag Name");

		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");
		Thread.sleep(2000);
		report.create_info("Bookmark Created at :- " + subsection);
		report.create_info("Tag Name :- " + section.getTagName());
		report.create_info("Description is :- " + section.getDescription());
		report.create_info("Note Created by :- " + section.getCreatedBy());
		Baseclass.refreshBrowser();
		assertEquals(section.getTagName(), option_L2);
		assertEquals(section.getDescription(), jsonValue("bookmark-text"));
		assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

		tableOfContent_Page.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");

		report.create_info("Chapter Name at my notes :- " + tableOfContent_Page.getChapterNameInMynotes());
		assertEquals(tableOfContent_Page.getChapterNameInMynotes(), subsection);

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("My notes and Bookmarks");

		assertEquals(mynote_bookmark.getTitleName(Title), Title);
		assertTrue(mynote_bookmark.verifyChapterName_Decription(Title, subsection, jsonValue("bookmark-text")));
		assertTrue(mynote_bookmark.verifyDetails(subsection, Login_Test.NAME, option_L2));

		mynote_bookmark.removeNotes_Bookmark(jsonValue("bookmark-text"));

	}

	@Test(priority = 9, description = "Verify user able to share the Subsection", dataProvider = "PC first title", dataProviderClass = DataProviders.class, groups = {
			"Regression" })

	public void TC22_verifyShareSection_FromSubSection(String option_L1, String option_L2, String option_L3, String Title, String Chapter,
			String tag)

			throws Exception {

		commanstep.navigetToTitle(option_L1, option_L2,option_L3, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + option_L1);
		Sparkreport.Step("Click  " + option_L2);
		Sparkreport.Step("Click  " + Title);

		String chapterName = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + chapterName);

		String subsection = tableOfContent_Page.navigateToSubChapter(0);
		Sparkreport.Step("Click Sub Section " + subsection);

		String successMessage = section.shareSubSection(section.getSessionID_FromSubChapter(), email);
		Sparkreport.Step("Click Share");
		Sparkreport.Step("Enter email");
		Sparkreport.Step("Click AddMore");
		Sparkreport.Step("Click Remove");
		Sparkreport.Step("Click Share Button");
		Sparkreport.Step("Click Close");
		report.create_info("Successful message :- " + successMessage);
		assertEquals(successMessage, jsonValue("share-successful"));

	}

	@Test(priority = 10, description = "Verify user able to Print SubSection", groups = { "Regression" })

	public void TC22_verifyPrintSection_FromSubSection() throws Exception {

		boolean pdfprint = section.printThisSubSection(section.getSessionID_FromSubChapter());
		Sparkreport.Step("Click Print");
		Sparkreport.Step("Click Print This Section");
		assertTrue(pdfprint);
		boolean pdfprint1 = section.printSubSection(section.getSessionID_FromSubChapter());
		Sparkreport.Step("Click Print");
		Sparkreport.Step("Click Print All Section");
		assertTrue(pdfprint1);
	}

	@Test(priority = 11, description = "Verify user able to create notes at ChildSub-Section", dataProvider = "PC first title", dataProviderClass = DataProviders.class, groups = {
			"Regression" })

	public void TC24_verifyCreateNotes_atChildSubsection(String option_L1, String option_L2, String option_L3, String Title,
			String Chapter, String tag) throws Exception {
		commanstep.navigetToTitle(option_L1, option_L2,option_L3, Title);


		String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + actChapter);

		tableOfContent_Page.navigateToSubSection(0);
		Sparkreport.Step("Click Sub Section ");

		String childsection = section.getChildSubSectionName();

		section.doubleClikcOn_ChildSection();
		Sparkreport.Step("Double Click ");

		section.creatNote_FromChildSubSection(section.getSectionID_FromChildSection(), jsonValue("notes-text"));
		Sparkreport.Step("Click Note icon");
		Sparkreport.Step("Enter Decription in textBox");

		section.selectTag(option_L2);
		Sparkreport.Step("Select Tag Name");

		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");
		Thread.sleep(2000);
		report.create_info("Note Created at :- " + childsection);
		report.create_info("Tag Name :- " + section.getTagName());
		report.create_info("Description is :- " + section.getDescription());
		report.create_info("Note Created by :- " + section.getCreatedBy());
		Baseclass.refreshBrowser();
		assertEquals(section.getTagName(), option_L2);
		assertEquals(section.getDescription(), jsonValue("notes-text"));
		assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

		tableOfContent_Page.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");
		report.create_info("Chapter Name at my notes :- " + tableOfContent_Page.getChapterNameInMynotes());
		assertEquals(tableOfContent_Page.getChapterNameInMynotes().replaceAll(" ", ""),
				childsection.replaceAll(" ", ""));

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("My notes and Bookmarks");

		assertEquals(mynote_bookmark.getTitleName(Title), Title);
		assertTrue(mynote_bookmark.verifyChapterName_Decription(Title, Chapter, jsonValue("notes-text")));
		assertTrue(mynote_bookmark.verifyDetails(Chapter, Login_Test.NAME, option_L2));

		mynote_bookmark.removeNotes_Bookmark(jsonValue("notes-text"));

	}

	@Test(priority = 12, description = "Verify user able to create Bookmark at ChildSub-Section", dataProvider = "PC first title", dataProviderClass = DataProviders.class, groups = {
			"Regression" })

	public void TC25_verifyCreateBookmark_atChildSubsection(String option_L1, String option_L2, String option_L3, String Title,
			String Chapter, String tag) throws Exception {

		commanstep.navigetToTitle(option_L1, option_L2,option_L3, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + option_L1);
		Sparkreport.Step("Click  " + option_L2);
		Sparkreport.Step("Click  " + Title);

		String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + actChapter);

		tableOfContent_Page.navigateToSubSection(0);
		Sparkreport.Step("Click Sub Section ");

		String childsection = section.getChildSubSectionName();

		// section.clickOnApps(section.getSectionID_FromChildSection());
		Sparkreport.Step("Click Apps");

		section.creteBookmark_FromSubSection(section.getSectionID_FromChildSection(), jsonValue("bookmark-text"));
		Sparkreport.Step("Click Bookmark icon");
		Sparkreport.Step("Enter Decription in textBox");

		section.selectTag(option_L2);
		Sparkreport.Step("Select Tag Name");

		section.clickOnSaveButton();
		Sparkreport.Step("Click Save");
		Thread.sleep(2000);
		report.create_info("Bookmark Created at :- " + childsection);
		report.create_info("Tag Name :- " + section.getTagName());
		report.create_info("Description is :- " + section.getDescription());
		report.create_info("Note Created by :- " + section.getCreatedBy());
		Baseclass.refreshBrowser();
		assertEquals(section.getTagName(), option_L2);
		assertEquals(section.getDescription(), jsonValue("bookmark-text"));
		assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

		tableOfContent_Page.clickOnMyNotes();
		Sparkreport.Step("Click My Notes");

		String Childsection = tableOfContent_Page.getChapterNameInMynotes();
		report.create_info("Chapter Name at my notes :- " + Childsection);
		assertEquals(Childsection.replaceAll(" ", ""), childsection.replaceAll(" ", ""));

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("My notes and Bookmarks");

		assertEquals(mynote_bookmark.getTitleName(Title), Title);
		assertTrue(mynote_bookmark.verifyChapterName_Decription(Title, Childsection, jsonValue("bookmark-text")));
		assertTrue(mynote_bookmark.verifyDetails(Childsection, Login_Test.NAME, option_L2));

		mynote_bookmark.removeNotes_Bookmark(jsonValue("bookmark-text"));

	}

	@Test(priority = 13, description = "Verify user able to share section at ChildSub-Section", dataProvider = "PC first title", dataProviderClass = DataProviders.class, groups = {
			"Regression" })

	public void TC26_verifyShareSection_atChildSubsection(String option_L1, String option_L2, String option_L3, String Title,
			String Chapter, String tag) throws Exception {

		commanstep.navigetToTitle(option_L1, option_L2,option_L3, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + option_L1);
		Sparkreport.Step("Click  " + option_L2);
		Sparkreport.Step("Click  " + Title);

		String actChapter = tableOfContent_Page.navigateToChapter(Chapter);
		Sparkreport.Step("Click Chapter " + actChapter);

		tableOfContent_Page.navigateToSubSection(0);
		Sparkreport.Step("Click Sub Section ");

		section.getChildSubSectionName();
		// section.clickOnApps(section.getSectionID_FromChildSection());
		// Sparkreport.Step("Click Apps");
		Thread.sleep(1000);
		String message = section.shareChildSubSection(section.getSectionID_FromChildSection(), email);
		Sparkreport.Step("Click Share Icon ");
		Sparkreport.Step("Enter Email Address");
		Sparkreport.Step("Click on Share");
		report.create_info("Message after share Section :- " + message);

		assertEquals(message, jsonValue("share-successful"));

	}

	@Test(priority = 14, description = "Verify user able to Print SubSection", groups = { "Regression" })

	public void TC27_verifyPrintSection_FromChildSubSection() throws Exception {
		Thread.sleep(1000);

		boolean pdf = section.printChildSubSection(section.getSectionID_FromChildSection());
		Sparkreport.Step("Click Print");

		assertTrue(pdf);
	}

}