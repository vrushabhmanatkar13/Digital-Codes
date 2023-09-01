package com.Digitalcodes.testcases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.ContentSearch_Page;
import com.Digitalcodes.pageobject.MenuFavorite_Page;
import com.Digitalcodes.pageobject.MenuMyNotesandBookmark_Page;
import com.Digitalcodes.pageobject.PremiumTools_SharingHistory_Page;
import com.Digitalcodes.pageobject.TableOfContent_Page;
import com.Digitalcodes.pageobject.TitleSection_Page;
import com.Digitalcodes.utilities.Baseclass;

import com.Digitalcodes.utilities.Sparkreport;

public class ContentSearch_Test extends Prerequisites_Teardown {

	ContentSearch_Page contentsearch;
	TableOfContent_Page tocpage;
	TitleSection_Page sectionpage;
	MenuMyNotesandBookmark_Page mynotesbookmarkpage;
	PremiumTools_SharingHistory_Page sharinghistory;
	MenuFavorite_Page favoritespage;

	private String Titlename;
	private String SearchData;

	@BeforeClass(alwaysRun = true)
	public void beforeclass() {
		contentsearch = new ContentSearch_Page();
		tocpage = new TableOfContent_Page();
		sectionpage = new TitleSection_Page();
		mynotesbookmarkpage = new MenuMyNotesandBookmark_Page();
		sharinghistory = new PremiumTools_SharingHistory_Page();
		favoritespage = new MenuFavorite_Page();
	}

	@DataProvider(name = "VerifyContentSearch")
	public Object[][] getContentSearchData() throws Exception {
		return excel.getDataFromExcle("Content Search", "VerifyContentSearch");
	}

	@DataProvider(name = "Search by Text")
	public Object[][] getContentSearchData_firstRow() throws Exception {
		return excel.getDataFromExcle("Content Search", "VerifyContentSearch", 1);
	}

	@DataProvider(name = "VerifySerachWithin Title")
	public Object[][] getSearchWithinTitleData() throws Exception {
		return excel.getDataFromExcle("Content Search", "VerifySerachWithin Title");
	}

	@DataProvider(name = "Advance Term Serach")
	public Object[][] getAdvanceTermSerachData() throws Exception {
		return excel.getDataFromExcle("Content Search", "Advance Term Serach");
	}

	@DataProvider(name = "Near Search In Advance Term Search")
	public Object[][] getNearSerachInAdvanceTermSearchData() throws Exception {
		return excel.getDataFromExcle("Content Search", "Near Search In Advance Term Search");
	}

	@Test(priority = 1, description = "Verify user able to search within Title and navigate to content search Page", dataProvider = "VerifySerachWithin Title", groups = {
			"Smoke", "Regression" })
	public void TC42_verifySearchWithinTitle(String Section, String Sub_section, String Title, String Chapter,
			String data) throws Exception {
		Titlename = Title;
		SearchData = data;

		commanstep.navigetToTitle(Section, Sub_section, Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click " + Section);
		Sparkreport.Step("Click " + Sub_section);
		Sparkreport.Step("Click " + Title);
		Thread.sleep(2000);
		contentsearch.search(data);
		Sparkreport.Step("Enter :- " + data);
		contentsearch.pressEnter();
		Sparkreport.Step("Press ENTER");

		report.create_info("Page Title :- " + getTitle());
		report.create_info("Titel Name on Content search :- " + contentsearch.getTitleName());
		assertTrue(contentsearch.getSearchResultText(data));
		assertEquals(getTitle(), jsonArrayValue("Page-titles", "content-search"));
		assertTrue(contentsearch.titleNameDisplyed(Title));

	}

	@Test(dependsOnMethods = "TC42_verifySearchWithinTitle", description = "verify Search text and filters are alredy applyed", groups = {
			"Smoke", "Regression" })
	public void TC43_verifyText_FiltersApplyed() {

		String searchtext = contentsearch.getSearchText();
		report.create_info("Search text :- " + searchtext);

		String filters = null;
		try {
			filters = contentsearch.getFilters(Titlename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Sparkreport.Step("Filters :- " + filters);
		assertEquals(searchtext, SearchData);
		assertTrue(filters != null);

	}

	@Test(priority = 2, description = "verify user able to navigate Content search page and search any content", dataProvider = "VerifyContentSearch", groups = {
			"Smoke", "Regression" })
	public void TC44_verifyContentSearch(String searchby, String data) throws Exception {

		menu.navigateToPremiumToolFeaturs(jsonArrayValue("Premium tools", "CS"));
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Premium Tools");
		Sparkreport.Step("Click " + jsonArrayValue("Premium tools", "CS"));
		Thread.sleep(1000);
		contentsearch.search(data);
		Sparkreport.Step("Search by :- " + searchby);
		Thread.sleep(1000);
		String button = contentsearch.clickAdvanceSearch();
		Sparkreport.Step("Click " + button);

		boolean text = contentsearch.getSearchResultText(data);
		String extractphasetext = contentsearch.getSearchText();
		Sparkreport.Step("Search Text in Extratc Phase :- " + extractphasetext);
		assertEquals(extractphasetext, data);
		assertTrue(text);

	}

	@Test(dependsOnMethods = "TC44_verifyContentSearch", description = "verify user able to apply filters on content search", groups = {
			"Smoke", "Regression" }, enabled = false)
	public void TC45_verifyApplyFiltersOncontentSearch() throws Exception {

		contentsearch.clickPlusButton("Categories");
		Sparkreport.Step("Click Categories");
		contentsearch.applyFilterOnCategories("Alabama");
		Sparkreport.Step("Clck Alabama");
		contentsearch.clickMinusButtton("Categories");
		String Categories = contentsearch.checkbox("Alabama");
		report.create_info("Filter Text :- " + Categories);

		contentsearch.clickCloseFilter();
		Sparkreport.Step("Close filter");
		Thread.sleep(2000);

	}

	@Test(priority = 3, description = "verify user able to jump to the section from Content search", dataProvider = "Search by Text", groups = {
			"Smoke", "Regression" })
	public void TC46_verifyJumpToSection(String searchby, String data) throws InterruptedException {

		menu.navigateToPremiumToolFeaturs(jsonArrayValue("Premium tools", "CS"));
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Premium Tools");
		Sparkreport.Step("Click " + jsonArrayValue("Premium tools", "CS"));
		Thread.sleep(1000);
		contentsearch.search(data);
		Sparkreport.Step("Enter :-" + data);
		Thread.sleep(1000);
		String button = contentsearch.clickAdvanceSearch();
		Sparkreport.Step("Click " + button);

		String subtitle = contentsearch.getSubtitleText().replaceAll(" ", "").trim();
		String titlename = contentsearch.getTitleName();
		Baseclass.getParentWindow();
		contentsearch.clickJumptoSection();
		String sectionname = null;
		String actultitlename = null;
		try {
			Baseclass.switchToWindow();
			sectionname = tocpage.getOpenSubSection().replaceAll(" ", "").trim();
			;
			actultitlename = sectionpage.getTitleName();

		} catch (Exception e) {

			e.printStackTrace();

		}

		Baseclass.closeWindow();
		Baseclass.retrunToMainWindow();
		report.create_info("Section Name :- " + subtitle);
		report.create_info("Title Name :- " + titlename);
		assertEquals(sectionname, subtitle);
		assertEquals(actultitlename, titlename);
	}

	@Test(priority = 4, description = "verify user able to Copy and Print the section", groups = { "Smoke",
			"Regression" })
	public void TC47_verifyCopyAndPrintThisSection() throws InterruptedException {

		String text = contentsearch.copyThisSection();
		Sparkreport.Step("Click Copy icon ");
		report.create_info("Text after click copy icon :- " + text);
		Thread.sleep(3000);
		contentsearch.clickPrint();
		Sparkreport.Step("Click Print icon");
		try {
			Baseclass.switchToWindow();
		} catch (Exception e) {

			e.printStackTrace();
		}

		boolean pdf = sectionpage.printSection_NewWindow();
		Baseclass.closeWindow();
		Baseclass.retrunToMainWindow();
		assertTrue(pdf);

	}

	@Test(priority = 4, description = "verify user able to Copy and Print Content search", groups = { "Smoke",
			"Regression" })
	public void TC48_verifyShareThisSection() {
		contentsearch.clickShareSection();
		Sparkreport.Step("Click Share link");
		String email = RandomStringUtils.randomAlphabetic(6).toLowerCase() + "@iccsafe.info";
		String msg = sectionpage.shareSection(email);
		Sparkreport.Step("Enter email :- " + email);
		Sparkreport.Step("Click Share");
		report.create_info("Message after share :- " + msg);
		assertEquals(msg, jsonValue("share-successful"));
	}

	@Test(priority = 5, description = "verify user able to Create Bookmark from Content search", groups = { "Smoke",
			"Regression" })
	public void TC49_verifyCreateBookmark() throws Exception {
		contentsearch.clickBookmark();
		Sparkreport.Step("Click Bookmark");
		sectionpage.createBookamrk_FromSection(jsonValue("bookmark-text"));
		Sparkreport.Step("Enter Description");
		String tagname = sectionpage.selectTag("Default");
		Sparkreport.Step("Select tag");
		Thread.sleep(1000);
		sectionpage.clickOnSaveButton();
		Sparkreport.Step("Click save");
		Thread.sleep(2000);
		String subtitle = contentsearch.getSubtitleText();
		String titlename = contentsearch.getTitleName();
		report.create_info("Bookmark created at :- " + subtitle);
		report.create_info("Title Name :- " + titlename);
		report.create_info("Description of bookmark :- " + sectionpage.getDescription());
		report.create_info("Tag Name :- " + sectionpage.getTagName());
		report.create_info("Created By :- " + sectionpage.getCreatedBy());
		Thread.sleep(3000);
		assertEquals(sectionpage.getDescription(), jsonValue("bookmark-text"));
		assertEquals(sectionpage.getTagName(), tagname);
		assertEquals(sectionpage.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

		Baseclass.getParentWindow();
		contentsearch.clickJumptoSection();
		Sparkreport.Step("Click jump to section");
		try {
			Baseclass.switchToWindow();

		} catch (Exception e) {

			e.printStackTrace();

		}

		assertEquals(sectionpage.getDescription(), jsonValue("bookmark-text"));
		assertEquals(sectionpage.getTagName(), tagname);
		assertEquals(sectionpage.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");
		Baseclass.closeWindow();
		Baseclass.retrunToMainWindow();

		menu.navigateToPremiumToolFeaturs(jsonArrayValue("Premium tools", "M-NB"));
		assertEquals(mynotesbookmarkpage.getTitleName(titlename), titlename);
		assertTrue(mynotesbookmarkpage.verifyChapterName_Decription(titlename, subtitle, jsonValue("bookmark-text")));
		assertTrue(mynotesbookmarkpage.verifyDetails(subtitle, Login_Test.NAME, tagname));

	}

	@Test(priority = 6, description = "verify user able to Share, Edit, Delete Bookmark from Content search", dataProvider = "Search by Text", groups = {
			"Smoke", "Regression" })
	public void TC50_verifyShareEditDeleteBookmark(String searchby, String data) throws Exception {
		contentsearch.search(data);
		Sparkreport.Step("Enter :- " + data);
		Thread.sleep(1000);
		String button = contentsearch.clickAdvanceSearch();
		Sparkreport.Step("Click " + button);
		String titlename = contentsearch.getTitleName();
		String subtitle = contentsearch.getSubtitleText().replaceAll(" ", "").trim();
		String email = RandomStringUtils.randomAlphabetic(5).toLowerCase() + "@iccsafe.info";
		String msg = sectionpage.shareNotes_Bookmark(email);
		Sparkreport.Step("Click share icon");
		Sparkreport.Step("Enter email");
		Sparkreport.Step("Click share button");
		Sparkreport.Step("Click close");
		report.create_info("Text after share bookmark :- " + msg);
		assertEquals(msg, jsonValue("share-content-successful"));

		menu.navigateToPremiumToolFeaturs(jsonArrayValue("Premium tools", "SH"));
		sharinghistory.clickOnvIcon();
		Thread.sleep(3000);
		assertEquals(sharinghistory.titleName(), titlename);
		assertEquals(sharinghistory.getSectionName().replaceAll(" ", "").trim(), subtitle);
		assertEquals(sharinghistory.getDescription(), jsonValue("bookmark-text"));
		assertEquals(sharinghistory.getTagName(), " Default");
		assertEquals(sharinghistory.getSharedwith(), email + "(Pending)");

		contentsearch.search(data);
		Thread.sleep(1000);
		contentsearch.clickAdvanceSearch();
		Sparkreport.Step("Click Advance Search");
		sectionpage.editNotes_Bookmark(jsonValue("bookmark-edit-text"));
		Sparkreport.Step("Click edit icon");
		Sparkreport.Step("Enter Description");
		Sparkreport.Step("Click Save");
		Thread.sleep(2000);
		report.create_info("Edit Description :- " + sectionpage.getDescription());
		assertEquals(sectionpage.getDescription(), jsonValue("bookmark-edit-text"));
		menu.navigateToPremiumToolFeaturs(jsonArrayValue("Premium tools", "SH"));
		sharinghistory.clickOnvIcon();
		Thread.sleep(3000);
		assertEquals(sharinghistory.getDescription(), jsonValue("bookmark-edit-text"));

		contentsearch.search(data);
		Thread.sleep(1000);
		contentsearch.clickAdvanceSearch();
		sectionpage.deleteNotes__Bookmark();
		Sparkreport.Step("Click Delete icon");
		Sparkreport.Step("Click Remove");
		assertTrue(sectionpage.Notes_BookmarkisDisplayed());
		menu.navigateToPremiumToolFeaturs(jsonArrayValue("Premium tools", "SH"));
		assertEquals(sharinghistory.getNoResultText(), "No Results Found");
	}

	@Test(priority = 7, description = "verify Advance Term Search from Content search", dataProvider = "Advance Term Serach", groups = {
			"Smoke", "Regression" })
	public void TC51_verifyAdvanceTermSearch(String data, String allofwords, String anyofwords, String noneofwords)
			throws InterruptedException {

		contentsearch.search(data);
		Thread.sleep(1000);
		contentsearch.pressEnter();
		contentsearch.clickAdvanceTermSearch();
		Thread.sleep(2000);
		contentsearch.AdvanceTermSearch(allofwords, anyofwords, noneofwords);
		Sparkreport.Step("Click Advance Term Search");
		Sparkreport.Step("Enter values ");
		Sparkreport.Step("Click Advance search button");

		boolean result = contentsearch.getSubtitleText().isEmpty();
		Thread.sleep(2000);
		report.create_info("All of Words :- " + contentsearch.getAllofWords());
		report.create_info("Any of Words :- " + contentsearch.getAnyofWords());
		report.create_info("None of Words :- " + contentsearch.getNoneofWords());
		assertFalse(result);
		assertEquals(contentsearch.getAllofWords(), allofwords);
		assertEquals(contentsearch.getAnyofWords(), anyofwords);
		assertEquals(contentsearch.getNoneofWords(), noneofwords);
	}

	@Test(priority = 8, description = "verify near search in Advance Term Search from Content search", dataProvider = "Near Search In Advance Term Search", groups = {
			"Smoke", "Regression" })
	public void TC52_verifyNearSearch(String nearsearch, String near) throws InterruptedException {
		contentsearch.clickClearSearch();
		Thread.sleep(2000);
		contentsearch.clickAdvanceTermSearch();
		Sparkreport.Step("Click Advance Term Search");

		contentsearch.nearSearch(nearsearch, near);
		Sparkreport.Step("Enter values ");
		Sparkreport.Step("Click Advance search button");
		report.create_info("Near search :- " + contentsearch.getSearchText());

		assertEquals(contentsearch.getSearchText(), nearsearch + " near " + near);
		assertFalse(contentsearch.getSubtitleText().isEmpty());

	}

	@Test(priority = 9, description = "verify Relevant Titles from Content search", dataProvider = "Search by Text", groups = {
			"Smoke", "Regression" })
	public void TC53_verifyShowRelevantTitles(String searchby, String data) throws InterruptedException {
		contentsearch.clickClearSearch();
		contentsearch.search(data);
		Sparkreport.Step("Enter :- " + data);
		Thread.sleep(1000);
		contentsearch.pressEnter();
		Thread.sleep(1000);
		contentsearch.clickShowRelevantTitles();
		Sparkreport.Step("Click Show Relevant Titles");
		report.create_info("Relevant Title :- " + contentsearch.getRelevantTitle());
		assertFalse(contentsearch.getRelevantTitle().isEmpty());
		contentsearch.closeRelevantTitles();
	}

	@Test(priority = 10, description = "verify save and delete serach Content search", dataProvider = "Search by Text", groups = {
			"Smoke", "Regression" })
	public void TC54_verifySaveDeleteContentSerach(String searchby, String data) throws InterruptedException {
		menu.navigateToPremiumToolFeaturs(jsonArrayValue("Premium tools", "CS"));
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Premium Tools");
		Sparkreport.Step("Click " + jsonArrayValue("Premium tools", "CS"));
		contentsearch.search(data);
		Sparkreport.Step("Enter :- " + data);
		Thread.sleep(1000);
		contentsearch.pressEnter();
		Thread.sleep(1000);
		String totalresults = contentsearch.getofResults();
		String text = contentsearch.getSubtitleText();
		report.create_info("Total Search Results :- " + totalresults);
		contentsearch.clickSaveSearch();
		String savetext = contentsearch.saveSearch("Test");
		contentsearch.clickClearSearch();
		assertEquals(savetext, "Test");
		contentsearch.clickSaveSearchText();
		assertEquals(contentsearch.getofResults(), totalresults);
		assertEquals(contentsearch.getSubtitleText(), text);

		contentsearch.deleteSaveSearch();
		Sparkreport.Step("Click Delete");
		Sparkreport.Step("Click Confom");
		report.create_info(contentsearch.getNoSaveSearchText());

	}

	@Test(priority = 11, description = "verify mark favorite and un favorite Title from Content search", dataProvider = "Search by Text", groups = {
			"Smoke", "Regression" })
	public void TC55_verifymarkFavorite_unFavoriteTitleformConetntSearch(String searchby, String data)
			throws Exception {
		contentsearch.clickClearSearch();
		contentsearch.search(data);
		Sparkreport.Step("Enter :- " + data);
		Thread.sleep(1000);
		contentsearch.pressEnter();
		Thread.sleep(2000);
		contentsearch.clickShowRelevantTitles();
		Sparkreport.Step("Click Show Relevant Titles");
		String title1 = contentsearch.getRelevantTitle();
		Sparkreport.Step("Title Name " + title1);
		contentsearch.clickFavorite();
		Sparkreport.Step("Click favorite icon");
		menu.navigetToStaticFeaturs("Favorites");
		String title2 = favoritespage.getTitleName();
		menu.closemenu();
		contentsearch.clickUnfavorite();
		contentsearch.closeRelevantTitles();
		assertEquals(title2, title1);

	}

	@Test(priority = 12, description = "verify paginations in Content search", dataProvider = "Search by Text", groups = {
			"Smoke", "Regression" })
	public void TC56_verifyPaginationinContentSearch(String searchby, String data) throws InterruptedException {
		menu.navigateToPremiumToolFeaturs(jsonArrayValue("Premium tools", "CS"));
		contentsearch.search(data);
		Sparkreport.Step("Enter :- " + data);
		contentsearch.pressEnter();
		int currentcount = contentsearch.getCount();
		int currentresult = contentsearch.getCountofResultOnpage();
		report.create_info("Current count :- " + currentcount);
		assertEquals(currentcount, currentresult);
		contentsearch.selectCount(50);
		Sparkreport.Step("Click Showing");
		report.create_info("Change count :- " + contentsearch.getCount());
		int newcount = contentsearch.getCountofResultOnpage();
		assertEquals(newcount, 50);
	}

	@Test(priority = 13, description = "verify search suggestions", dataProvider = "Search by Text", groups = {
			"Smoke" })
	public void TC57_verifySearchSuggestions(String searchby, String data) {
		contentsearch.clickClearSearch();
		contentsearch.search(data);
		boolean suggetiontext = contentsearch.getSearchSuggetions(data);
		String suggetiontitle = null;
		try {
			suggetiontitle = contentsearch.getSearchSuggetions();
			Sparkreport.Step("Title Name :- " + suggetiontitle);
		} catch (Exception e) {

			e.printStackTrace();
		}
		assertTrue(suggetiontitle != null);
		assertTrue(suggetiontext);

	}

	@Test(priority = 14, description = "verify see all form search suggestion", dataProvider = "Search by Text", groups = {
			"Smoke" })
	public void TC57_verifySeeAll(String searchby, String data) throws Exception {
		contentsearch.clickClearSearch();
		contentsearch.search(data);
		String text = contentsearch.getSeeAlltext();
		Sparkreport.Step("Text showing in See All :- " + text);
		assertEquals(text, data);

		contentsearch.clickSeeAll();
		boolean resulttext = contentsearch.getSearchResultText(data);
		assertTrue(resulttext);

	}

}
