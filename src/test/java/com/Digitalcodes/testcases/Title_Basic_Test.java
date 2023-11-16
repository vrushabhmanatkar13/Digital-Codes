package com.Digitalcodes.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.TableOfContent_Page;
import com.Digitalcodes.pageobject.TitleLanding_Page;
import com.Digitalcodes.utilities.Sparkreport;

public class Title_Basic_Test extends Prerequisites_Teardown {

	TitleLanding_Page landingpage;
	TableOfContent_Page tocpage;

	@BeforeClass(alwaysRun = true)
	public void beforeclass() {
		tocpage = new TableOfContent_Page();

	}

	@DataProvider(name = "Non Accessaible Titles")
	public Object[][] getBasicTitleData() throws Exception {
		return excel.getDataFromExcle("Titles", "Non Accessaible Titles");
	}

	@Test(priority = 1, description = "Verify user able to navigate to Non-accsiable title ", dataProvider = "Non Accessaible Titles", groups = {
			"Smoke", "Regression" })
	public void TC33_verifyNgviateToBasicTitle(String option_L1, String option_L2, String option_L3, String title) {

		landingpage = commanstep.navigetToTitle(option_L1, option_L2,option_L3, title);
		Sparkreport.Step("Click Menu");
		Sparkreport.Step("Click " + option_L1);
		Sparkreport.Step("Click " + option_L2);
		Sparkreport.Step("Click " + option_L3);
		Sparkreport.Step("Click " + title);

		String text2 = landingpage.getNotPremiumText();
		String text1 = tocpage.getNotPremiumText();
		Sparkreport.Step(text1);

		boolean lock = tocpage.locklabelisDisplayed();
		assertTrue(lock);
		assertEquals(text1, text2);

		String titlename = tocpage.clickSubscribedNow();
		Sparkreport.Step(titlename);
		assertFalse(titlename.isBlank());

	}
}