package com.Digitalcodes.pageobject;

import com.Digitalcodes.testcases.CommanSteps;
import com.Digitalcodes.testcases.TitleLandingPage_Test;

public class Implimation_AbstractClass extends CommanSteps{


		
		 @Override
		public void navigetToTitle(String list, String groupofTitle, String Title ) {
			 Menu menu=new Menu(); 
			TitleCover_Page coverpage= menu.navigateToTitlesCover(list, groupofTitle);
			 new TitleLandingPage_Test().title=coverpage.clickOnTitlesCover(Title);
		}
}
