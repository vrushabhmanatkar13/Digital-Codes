package com.Digitalcodes.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.MenuFavorite_Page;
import com.Digitalcodes.pageobject.TitleCover_Page;
import com.Digitalcodes.pageobject.TitleLanding_Page;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.DataProviders;
import com.Digitalcodes.utilities.Sparkreport;

public class Title_LandingPage_Test extends Prerequisites_Teardown {
	public TitleLanding_Page title;
	TitleCover_Page coverpage;
	String titlename;

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		coverpage = new TitleCover_Page();
	}

	@Test(priority = 1, description = "Verify User able to Navigate to Title Landing page and verify Tag", dataProvider="Premium Complete Titles", dataProviderClass = DataProviders.class, groups = {
			"Smoke", "Regression" })
	public void TC04_verifyTitleLandingPage(String Section, String Sub_section, String Title, String Chapter, String tag)
			throws Exception {

		menu.navigateToTitlesCover(Section, Sub_section);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click " + Section);
		Sparkreport.Step("Click " + Sub_section);
		Sparkreport.Step("Page Title is " + getTitle());
		String heading = coverpage.getHeading().replace(" Building Codes", "");
		assertEquals(heading, Sub_section);
		assertEquals(getTitle(), heading + " Building Codes - ICC Digital Codes");
		
		title = coverpage.clickOnTitlesCover(Title);
		Sparkreport.Step("Click " + Title);
		titlename = title.getTitleHeading();
		Thread.sleep(2000);
		String activepremium=title.getActivepremiumText();
		String tagname=title.getTagName();
		Sparkreport.Step("Subscription Tag is " + tagname);
		Sparkreport.Step("Subscription is " + activepremium);
		
		assertEquals(titlename, Title);
		assertEquals(tagname, tag);
		assertEquals(activepremium, jsonArrayValue("Premium", "Access-title"));
        
	}

	@Test(priority = 2, description = "Verify user able to mark favorite & unfavorite and title should be displayed in favorites", dataProvider = "PC first title", dataProviderClass = DataProviders.class, groups = {
			"Smoke","Regression" })
	public void TC05_verifyMarkFavoriteTitle(String Section, String Sub_section, String Title, String Chapter, String tag)
			throws InterruptedException {

		menu.navigateToTitlesCover(Section, Sub_section);
		title = coverpage.clickOnTitlesCover(Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click " + Section);
		Sparkreport.Step("Click " + Sub_section);
		Sparkreport.Step("Click " + Title);
		Thread.sleep(2000);

		title.clickOnFavorite();
		Sparkreport.Step("Click Blank Heart icon");
		Thread.sleep(4000);
		report.create_info("Text after mark favorite :- " + title.getFavoriteText());

		menu.navigetToStaticFeaturs("Favorites");
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Favorites");
		String favtitlename = new MenuFavorite_Page().getTitleName();
		Thread.sleep(3000);
		menu.clickOnMainMenu();
		menu.closemenu();
		report.create_info("Title Name is :- " + favtitlename);

		assertEquals(title.getFavoriteText(), jsonValue("unfavorite"));
		assertEquals(favtitlename, Title);

		Thread.sleep(3000);

		title.clickOnUnFavorite();
		Thread.sleep(4000);
		Baseclass.refreshBrowser();
		Sparkreport.Step("Click Filled Heart icon");

		report.create_info("Text after mark unfavorite :- " + title.getFavoriteText());

		assertEquals(title.getFavoriteText(), jsonValue("favorite"));

	}

	@Test(priority = 3, description = "Verify User able to Change Version, Releated Titles, Categories", dataProvider = "PC first title", dataProviderClass = DataProviders.class, groups = {
			"Regression" })
	public void TC06_verifyChangeVersion_ReleatedTitles_Categories(String Section, String Sub_section, String Title, String Chapter, String tag) throws Exception {

		menu.navigateToTitlesCover(Section, Sub_section);
		coverpage.clickOnTitlesCover(Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click  " + Section);
		Sparkreport.Step("Click  " + Sub_section);
		Sparkreport.Step("Click  " + Title);

		boolean currentlyviewing = title.changeVersion();
		Sparkreport.Step("Click Current Viewing ListBox");
		Sparkreport.Step("Click 2nd Version");

		int code = title.verifyReleatedTitles();
		Sparkreport.Step("Verifing Releated Titles");

		String category = title.getCategoryText();
		String heading = coverpage.getHeading();
		Sparkreport.Step("Click on category");
		

		assertTrue(currentlyviewing);
		assertEquals(code, 200);
		assertEquals(category, heading);

	}

}
