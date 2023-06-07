package com.Digitalcodes.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.MenuMyNotesandBookmark_Page;
import com.Digitalcodes.pageobject.PremiumTools_QuickAccess_Page;
import com.Digitalcodes.pageobject.PremiumTools_SharingHistory_Page;
import com.Digitalcodes.pageobject.TitleSection_Page;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.Sparkreport;

public class PremiumTools_QuickAccess_Test extends Prerequisites_Teardown {

	PremiumTools_QuickAccess_Page quickaccess;
	TitleSection_Page sectionpage;
     PremiumTools_SharingHistory_Page sharingHistorypage;
     String titleName;
     String section;
     
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		quickaccess = new PremiumTools_QuickAccess_Page();
		sectionpage=new TitleSection_Page();
		sharingHistorypage=new PremiumTools_SharingHistory_Page();
	}

	
	
	@Test(priority = 1, description = "Verify user able to Access section from Quick Access", groups = { "Smoke",
			"Regression" })
	public void TC34_verifyAccessSection_QuickAccess() throws Exception {

		menu.navigateToPremiumToolFeaturs("Quick Access");
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Premium Tools");
		Sparkreport.Step("Click Quick Access");

		assertEquals(getTitle(), jsonArrayValue("Page-titles", "quick-access"));

		 titleName = quickaccess.inputTitleName("2021 IBC");
		Sparkreport.Step("Enter IBC");
		report.create_info("Title :-" + titleName);

		String version = quickaccess.selectVersion();
		report.create_info("Version :-" + version);

		section = quickaccess.inputSectionName("301.1");
		Sparkreport.Step("Enter 301");
		report.create_info("Section :-" + section);

		assertEquals(quickaccess.getSectionName().replaceAll(" ", ""), section.replaceAll(" ", ""));

		Baseclass.getParentWindow();
		quickaccess.clickJumpToSection();
		String sectionName = quickaccess.getSectionName();
		report.create_info("Section Name :- " + sectionName);
		Thread.sleep(3000);
		assertEquals(sectionName.replaceAll(" ", ""), section.replaceAll(" ", ""));
		Baseclass.retrunToMainWindow();
		Baseclass.switchToWindow();
		Baseclass.closeWindow();
		Baseclass.retrunToMainWindow();

		

	}
	
	
	
	@Test(priority = 2,description = "Verify user able to Jump, Copy, Print, Share, Bookmark Section from Quick Access",groups = { "Smoke",
	"Regression" })
	public void TC35_verifyActionsOnSection_QuickAccess() throws Exception {
		
		String section= quickaccess.getSectionName();
		quickaccess.clickInputIcon();
		Sparkreport.Step("Click JumptoSection icon");
		String sectionName = quickaccess.getSectionNameOnChapterPage();
		report.create_info("Section Name :- " + sectionName);
		Baseclass.retrunToMainWindow();
		Thread.sleep(3000);
		Baseclass.switchToWindow();
		Baseclass.closeWindow();
		Baseclass.retrunToMainWindow();
		assertEquals(sectionName.replaceAll(" ", ""), section.replaceAll(" ", ""));
		
		
		String copiedText=quickaccess.copyThisSection();
		Sparkreport.Step("Click CopyIcon");
		report.create_info("Text After copied Section :- "+ copiedText);
		
		assertEquals(copiedText, jsonValue("copied-text"));
		
		
		boolean ActPdf=quickaccess.printThisSection();
		Sparkreport.Step("Click PrintIcon");
		
		assertTrue(ActPdf);
		
		String sccussfulMsg=quickaccess.shareThisSection(Login_Test.EMAIL);
		Sparkreport.Step("Click ShareIcon");
		Sparkreport.Step("Enter "+ Login_Test.EMAIL);
		Sparkreport.Step("Click Share");
		
		report.create_info("Text After share Section :- "+ sccussfulMsg);
		
		assertEquals(sccussfulMsg, jsonValue("share-successful"));
		
		Thread.sleep(3000);
		
		quickaccess.bookmarkThisSection(jsonValue("bookmark-text"));
		Sparkreport.Step("Click BookmarkIcon");
		Sparkreport.Step("Enter "+jsonValue("bookmark-text"));
		Sparkreport.Step("Click Save");
		report.create_info("Description is :- " + sectionpage.getDescription());
		report.create_info("Note Created by :- " + sectionpage.getCreatedBy());
		
		assertEquals(sectionpage.getDescription(), jsonValue("bookmark-text"));
		assertEquals(sectionpage.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");
		
	}

	@Test(priority = 3,description = "Verify user able to Share, Edit, Delete Bookmark from Quick Access",groups = { 
	"Smoke","Regression" })
	public void TC36_verifyShare_Edit_DeleteBookmakr_QuickAccess() throws Exception {
		
		String SuccessfullyMsg = sectionpage.shareNotes_Bookmark(Login_Test.EMAIL);
		Sparkreport.Step("Click Share");
		Sparkreport.Step("Enter email");
		Sparkreport.Step("Click Submit");
		Sparkreport.Step("Click Close");
		report.create_info("Successful message :- " + SuccessfullyMsg);

		assertEquals(SuccessfullyMsg, jsonValue("share-content-successful"));
		
	   	menu.navigateToPremiumToolFeaturs("Sharing History");
		sharingHistorypage.clickOnvIcon();
		 assertEquals(sharingHistorypage.titleName(), titleName);
		 assertEquals(sharingHistorypage.getSectionName(), section);
		 assertEquals(sharingHistorypage.getDescription(),jsonValue("bookmark-text"));
		 assertEquals(sharingHistorypage.getTagName(), " Default");
		 assertEquals(sharingHistorypage.getSharedwith(), Login_Test.NAME+"(Pending)");
		 
		
		menu.navigateToPremiumToolFeaturs("Quick Access");
		quickaccess.clickRecentlyAccessedSection(section);
		Thread.sleep(2000);
		sectionpage.editNotes_Bookmark(jsonValue("bookmark-edit-text"));
		Sparkreport.Step("Click edit");
		Sparkreport.Step("Enter edited text");
		Sparkreport.Step("Click Save");

		report.create_info("Description is :- " + sectionpage.getDescription());
          Thread.sleep(3000);
		assertEquals(sectionpage.getDescription(), jsonValue("bookmark-edit-text"));
		
		sectionpage.deleteNotes__Bookmark();
		//Thread.sleep(3000);
		//boolean note_bookmakr = sectionpage.Notes_BookmarkisDisplayed();
		Sparkreport.Step("Click delete");
		Sparkreport.Step("Click Remove");
		
		//assertFalse(note_bookmakr);
	}
	
	@Test(priority = 4,description = "Verify user able to view Recently Accessed Section at Quick Access",groups = { "Smoke",
	"Regression" })
	public void TC37_verifyRecentlyQuickAccessedSections_QuickAccess() throws Exception {
		menu.navigateToPremiumToolFeaturs("Quick Access");
		String RecentlyAccessedSection=quickaccess.getRecentlyAccessedSection(section);
		 report.create_info("Section Name :- "+ RecentlyAccessedSection);
		String RecetlyAccessedtitle=quickaccess.getRecentlyAccessedTitleName(titleName);
		 report.create_info("Title Name :- "+ RecetlyAccessedtitle);
		 
		assertEquals(RecentlyAccessedSection, section.replaceAll(" ", ""));
		assertEquals(RecetlyAccessedtitle, titleName);
	}
	
	
	@Test(priority = 5,description = "Verify User able to View already created Bookmark at Quick Access",groups = {
	"Regression" })
	public void TC38_verifyAlreadyCreatedBookmark_QuickAccess() throws Exception {
		Baseclass.refreshBrowser();
		
		String titleName = quickaccess.inputTitleName("2021 IBC");
		Sparkreport.Step("Enter IBC");
		report.create_info("Title :- " + titleName);

		String section = quickaccess.inputSectionName("301.1");
		Sparkreport.Step("Enter 301");
		report.create_info("Section :- " + section);
		
		Thread.sleep(1000);
		quickaccess.bookmarkThisSection(jsonValue("bookmark-text"));
		Sparkreport.Step("Click BookmarkIcon");
		Sparkreport.Step("Enter "+jsonValue("bookmark-text"));
		Sparkreport.Step("Click Save");
		
		menu.navigateToPremiumToolFeaturs("My notes and Bookmarks");
		Sparkreport.Step("Click Menu");
		Sparkreport.Step("Click Premium Tools");
		Sparkreport.Step("Click My notes and Bookmarks");
		MenuMyNotesandBookmark_Page mynote_bookmark = new MenuMyNotesandBookmark_Page();
        mynote_bookmark.clickTitleName();
        Sparkreport.Step("Click Title");
        report.create_info("Title :- " + mynote_bookmark.getTitleName());
        
		assertEquals(mynote_bookmark.getTitleName(), titleName);
		assertTrue(mynote_bookmark.verifyDetails(Login_Test.NAME, "Default"));
		
		menu.navigateToPremiumToolFeaturs("Quick Access");
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Premium Tools");
		Sparkreport.Step("Click Quick Access");
		
		quickaccess.clickRecentlyAccessedSection(section);
		Sparkreport.Step("Click "+ section);
		 report.create_info("Description :- " + sectionpage.getDescription());
		 report.create_info("Created By :- " +sectionpage.getCreatedBy());
		 
		assertEquals(sectionpage.getDescription(), jsonValue("bookmark-text"));
		assertEquals(sectionpage.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");
		sectionpage.deleteNotes__Bookmark();
	}
	
	
	
	

}
