package com.Digitalcodes.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.MenuFavorite_Page;
import com.Digitalcodes.pageobject.TitleCover_Page;
import com.Digitalcodes.pageobject.TitleLanding_Page;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.Sparkreport;

public class Title_LandingPage_Test extends Prerequisites_Teardown {
	public TitleLanding_Page title;
	TitleCover_Page coverpage;
	String titlename;

	@BeforeClass
	public void beforeClass() {
		coverpage = new TitleCover_Page();
	}

	@Test(priority = 1, description = "Verify User able to Navigate to Title Landing page and verify Tag", dataProvider = "Title", dataProviderClass = Prerequisites_Teardown.class, groups = {
			"Smoke", "Regeration" })
	public void TC04_verifyTitleLandingPage(String Section, String Sub_section, String Title, String Chapter)
			throws Exception {

		menu.navigateToTitlesCover(Section, Sub_section);
	
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Listitem " + Section);
		Sparkreport.Step("Click subList " + Sub_section);
		
		report.create_info("Page Title is " + getTitle());
		String heading = coverpage.getHeading().replace(" Building Codes", "");
		assertEquals(heading, Sub_section);
		assertEquals(getTitle(), heading + " Building Codes - ICC Digital Codes");
		
		title = coverpage.clickOnTitlesCover(Title);
		Sparkreport.Step("Click Title " + Title);
		titlename = title.getTitleHeading();
		
		report.create_info("Subscription Tag is " + title.getTagName());
		report.create_info("Subscription is " + title.getActivepremiumText());
		
		
		assertEquals(titlename, Title);
		assertEquals(title.getTagName(), jsonArrayValue("Premium", "tag"));
		assertEquals(title.getActivepremiumText(), jsonArrayValue("Premium", "Access-title"));

	}

	@Test(priority = 2, description = "Verify user able to mark favorite & unfavorite and title should be displayed in favorites", dataProvider = "SingleTitle", dataProviderClass = Prerequisites_Teardown.class, groups = {
			"Smoke" })
	public void TC05_verifyMarkFavoriteTitle(String Section, String Sub_section, String Title, String Chapter)
			throws InterruptedException {

		menu.navigateToTitlesCover(Section, Sub_section);
		coverpage.clickOnTitlesCover(Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Listitem " + Section);
		Sparkreport.Step("Click subList " + Sub_section);
		Sparkreport.Step("Click Title " + Title);
		Thread.sleep(2000);

		title.clickOnFavorite();
		Sparkreport.Step("Click Blank Heart icon");
		Thread.sleep(5000);
		report.create_info("Text after mark favorite :- " + title.getFavoriteText());

		menu.navigetToStaticFeaturs("Favorites");
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Favorites");
		String favtitlename = new MenuFavorite_Page().getTitleName();
		Thread.sleep(3000);
		menu.clickOnMainMenu();
		menu.closeMainmenu();
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

	@Test(priority = 3, description = "Verify User able to Change Version, Releated Titles, Categories", dataProvider = "SingleTitle", dataProviderClass = Prerequisites_Teardown.class, groups = {
			"Regeration" })
	public void TC06_verifyChangeVersion_ReleatedTitles_Categories(String Section, String Sub_section, String Title,
			String Chapter) throws Exception {

		menu.navigateToTitlesCover(Section, Sub_section);
		coverpage.clickOnTitlesCover(Title);
		Sparkreport.Step("Click menu");
		Sparkreport.Step("Click Listitem " + Section);
		Sparkreport.Step("Click subList " + Sub_section);
		Sparkreport.Step("Click Title " + Title);

		boolean currentlyviewing = title.changeVersion();
		Sparkreport.Step("Click Current Viewing ListBox");
		Sparkreport.Step("Click 2nd Version");

		int code = title.verifyReleatedTitles();
		Sparkreport.Step("Verifing Releated Titles");

		String category = title.getCategoryText();
		String heading = coverpage.getHeading();
		Sparkreport.Step("Click on category");
		Thread.sleep(2000);

		assertTrue(currentlyviewing);
		assertEquals(code, 200);
		assertEquals(category, heading);

	}

}