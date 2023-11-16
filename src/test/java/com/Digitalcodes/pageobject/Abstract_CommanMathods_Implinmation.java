package com.Digitalcodes.pageobject;

import com.Digitalcodes.testcases.CommanSteps;

public class Abstract_CommanMathods_Implinmation extends CommanSteps {

	@Override
	public TitleLanding_Page navigetToTitle(String option_L1, String option_L2, String option_L3, String title_name) {
		Menu menu = new Menu();
		TitleCover_Page coverpage = menu.navigateToTitlesCover(option_L1, option_L2,option_L3);
		TitleLanding_Page landingpage = coverpage.clickOnTitlesCover(title_name);
		return landingpage;
	}

}
