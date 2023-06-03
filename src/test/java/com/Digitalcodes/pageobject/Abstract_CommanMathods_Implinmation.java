package com.Digitalcodes.pageobject;

import com.Digitalcodes.testcases.CommanSteps;



public class Abstract_CommanMathods_Implinmation extends CommanSteps{


		
		 @Override
		public TitleLanding_Page navigetToTitle(String list, String groupofTitle, String Title ) {
			 Menu menu=new Menu(); 
			TitleCover_Page coverpage= menu.navigateToTitlesCover(list, groupofTitle);
			 TitleLanding_Page landingpage = coverpage.clickOnTitlesCover(Title);
			 return landingpage;
		}

	
}
