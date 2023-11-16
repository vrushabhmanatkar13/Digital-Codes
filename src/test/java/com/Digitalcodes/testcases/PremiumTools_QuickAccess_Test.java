package com.Digitalcodes.testcases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.MenuMyNotesandBookmark_Page;
import com.Digitalcodes.pageobject.PremiumTools_QuickAccess_Page;
import com.Digitalcodes.pageobject.PremiumTools_SharingHistory_Page;
import com.Digitalcodes.pageobject.TableOfContent_Page;
import com.Digitalcodes.pageobject.TitleSection_Page;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.Sparkreport;

public class PremiumTools_QuickAccess_Test extends Prerequisites_Teardown {

	PremiumTools_QuickAccess_Page quickaccess;
	TitleSection_Page sectionpage;
	PremiumTools_SharingHistory_Page sharingHistorypage;
	TableOfContent_Page tocpage;
	MenuMyNotesandBookmark_Page mynote_bookmark;

	private String titlenameInDropDown;
	private String sectionInDropDown;
	private String email;

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		quickaccess = new PremiumTools_QuickAccess_Page();
		sectionpage = new TitleSection_Page();
		sharingHistorypage = new PremiumTools_SharingHistory_Page();
		tocpage = new TableOfContent_Page();
		mynote_bookmark = new MenuMyNotesandBookmark_Page();
		email = RandomStringUtils.randomAlphabetic(8).toLowerCase() + "@iccsafe.info";
	}

	@DataProvider(name = "QuickAccessSection")
	public Object[][] getQuickAccessData() throws Exception {
		return excel.getDataFromExcle("Quick Access", "QuickAccessSection");
	}
	
	  @DataProvider(name = "RecentlyAccessSection") public Object[][]
	  getRecentlyAccessSectionData() throws Exception{ return
	  excel.getDataFromExcle("Quick Access", "RecentlyAccessSection"); }
	 

	@Test(priority = 1, description = "Verify user able to Access section from Quick Access", dataProvider = "QuickAccessSection", groups = {
			"Smoke", "Regression" })
	public void TC37_verifyAccessSection_QuickAccess(String step1, String titlename, String version, Object step2,
			String sectionname) throws Exception {

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Quick Access");
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Premium Tools");
		Sparkreport.Step("Click Quick Access");
		String title = getTitle();
		Sparkreport.Step("Page Title :- " + title);

		titlenameInDropDown = quickaccess.enterTextinStep1_SelectTitle(step1, titlename);
		Sparkreport.Step("Enter " + step1);
		Sparkreport.Step("Click " + titlenameInDropDown);

		String versiontext = quickaccess.selectVersion(version);
		Sparkreport.Step("Version :-" + versiontext);

		sectionInDropDown = quickaccess.enterTextinStep2_SelectSection(step2, sectionname);
		Sparkreport.Step("Enter " + step2);
		Sparkreport.Step("Section :-" + sectionInDropDown);

		assertEquals(quickaccess.getSectionName().replaceAll(" ", ""), sectionInDropDown.replaceAll(" ", ""));

		Baseclass.getParentWindow();
		quickaccess.clickJumpToSection();

		String sectionName = quickaccess.getSectionName();
		Sparkreport.Step("Section Name in TOC :- " + sectionName);
		Thread.sleep(1000);
		assertEquals(sectionName.replaceAll(" ", ""), sectionInDropDown.replaceAll(" ", ""));
		Baseclass.retrunToMainWindow();
		Baseclass.switchToWindow();
		closeWindow();
		retrunToMainWindow();

		assertEquals(title, jsonArrayValue("Page-titles", "quick-access"));
		assertEquals(titlenameInDropDown, titlename);
		assertEquals(sectionInDropDown.replaceAll(" ", ""), sectionname.replaceAll(" ", ""));
		assertEquals(versiontext.replaceAll("[()]", ""), version);

	}

	@Test(priority = 2, description = "Verify user able to Jump, Copy, Print, Share, Bookmark Section from Quick Access", dataProvider = "RecentlyAccessSection", groups = {
			"Smoke", "Regression" })
	public void TC38_verifyActionsOnSection_QuickAccess(String section, String title) throws Exception {

		String sectionOnQuickAccess = quickaccess.getSectionName();

		quickaccess.clickInputIcon();

		Sparkreport.Step("Click input icon");
		String sectionNamefromTOC = quickaccess.getSectionName();
		Sparkreport.Step("Section Name in TOC:- " + sectionNamefromTOC);
		Baseclass.retrunToMainWindow();
		Thread.sleep(2000);
		Baseclass.switchToWindow();
		Baseclass.closeWindow();
		Baseclass.retrunToMainWindow();

		assertEquals(sectionNamefromTOC.replaceAll(" ", ""), sectionOnQuickAccess.replaceAll(" ", ""));

		String copiedText = quickaccess.copyThisSection();
		Sparkreport.Step("Click CopyIcon");
		Sparkreport.Step("Text After copied Section :- " + copiedText);

		assertEquals(copiedText, jsonValue("copied-text"));

		boolean ActPdf = quickaccess.printThisSection();
		Sparkreport.Step("Click PrintIcon");

		assertTrue(ActPdf);

		String sccussfulMsg = quickaccess.shareThisSection(email);
		Sparkreport.Step("Click ShareIcon");
		Sparkreport.Step("Enter " + email);
		Sparkreport.Step("Click Share");
		Sparkreport.Step("Text After share Section :- " + sccussfulMsg);

		assertEquals(sccussfulMsg, jsonValue("share-successful"));

		Thread.sleep(3000);
		quickaccess.bookmarkThisSection(jsonValue("bookmark-text"));
		Sparkreport.Step("Click BookmarkIcon");
		Sparkreport.Step("Enter " + jsonValue("bookmark-text"));
		Sparkreport.Step("Click Save");
		Sparkreport.Step("Description is :- " + sectionpage.getDescription());
		Sparkreport.Step("Note Created by :- " + sectionpage.getCreatedBy());

		assertEquals(sectionpage.getDescription(), jsonValue("bookmark-text"));
		assertEquals(sectionpage.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("My Notes and Bookmark");
		mynote_bookmark.clickTitleName(title);

		assertEquals(mynote_bookmark.getTitleName(title), title);
		assertTrue(mynote_bookmark.verifyDetails(section, Login_Test.NAME, "Default"));

	}

	@Test(priority = 3, description = "Verify user able to Share, Edit, Delete Bookmark from Quick Access", dataProvider = "RecentlyAccessSection", groups = {
			"Smoke", "Regression" })
	public void TC39_verifyShare_Edit_DeleteBookmakr_QuickAccess(String section, String title) throws Exception {

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Quick Access");
		quickaccess.clickRecentlyAccessedSection(section);

		String SuccessfullyMsg = sectionpage.shareNotes_Bookmark(email);
		Sparkreport.Step("Click Share");
		Sparkreport.Step("Enter email");
		Sparkreport.Step("Click Submit");
		Sparkreport.Step("Click Close");
		Sparkreport.Step("Successful message :- " + SuccessfullyMsg);

		assertEquals(SuccessfullyMsg, jsonValue("share-content-successful"));

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Sharing History");
		sharingHistorypage.clickOnvIcon();
		Thread.sleep(2000);
		assertEquals(sharingHistorypage.titleName(), title);
		assertEquals(sharingHistorypage.getSectionName().replaceAll(" ", ""), section.replaceAll(" ", ""));
		assertEquals(sharingHistorypage.getDescription(), jsonValue("bookmark-text"));
		assertEquals(sharingHistorypage.getTagName(), " Default");
		assertEquals(sharingHistorypage.getSharedwith(), email + "(Pending)");

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Quick Access");
		quickaccess.clickRecentlyAccessedSection(section);
		sectionpage.editNotes_Bookmark(jsonValue("bookmark-edit-text"));
		Sparkreport.Step("Click edit");
		Sparkreport.Step("Enter edited text");
		Sparkreport.Step("Click Save");
		Thread.sleep(2000);
		Sparkreport.Step("Description is :- " + sectionpage.getDescription());
		assertEquals(sectionpage.getDescription(), jsonValue("bookmark-edit-text"));
		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Sharing History");
		sharingHistorypage.clickOnvIcon();
		assertEquals(sharingHistorypage.getDescription(), jsonValue("bookmark-edit-text"));

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Quick Access");
		quickaccess.clickRecentlyAccessedSection(section);
		sectionpage.deleteNotes__Bookmark();
		Sparkreport.Step("Click delete");
		Sparkreport.Step("Click Remove");
		Thread.sleep(2000);
		boolean note_bookmakr = sectionpage.Notes_BookmarkisDisplayed();
		assertFalse(note_bookmakr);
		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Sharing History");
		assertEquals(sharingHistorypage.getNoResultText(), "No Results Found");
	}

	@Test(priority = 4, description = "Verify user able to view Recently Accessed Section at Quick Access", dataProvider = "RecentlyAccessSection", groups = {
			"Regression" })
	public void TC40_verifyRecentlyQuickAccessedSections_QuickAccess(String section, String title) throws Exception {

		menu.clickOnMenu();
		menu.click_menu_optionL1("Premium Tools");
		menu.click_menu_optionL2("Quick Access");
		String RecentlyAccessedSection = quickaccess.getRecentlyAccessedSection(section);

		String RecetlyAccessedtitle = quickaccess.getRecentlyAccessedTitleName(title);

		assertEquals(RecentlyAccessedSection, section.replaceAll(" ", ""));
		assertEquals(RecetlyAccessedtitle, title);
	}

}
