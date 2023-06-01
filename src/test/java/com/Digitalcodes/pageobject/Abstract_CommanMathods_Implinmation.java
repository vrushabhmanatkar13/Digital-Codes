package com.Digitalcodes.pageobject;

import com.Digitalcodes.testcases.CommanSteps;
import com.Digitalcodes.testcases.Title_SectionPage_Test;
import com.Digitalcodes.utilities.Baseclass;

public class Abstract_CommanMathods_Implinmation extends CommanSteps{


		
		 @Override
		public TitleLanding_Page navigetToTitle(String list, String groupofTitle, String Title ) {
			 Menu menu=new Menu(); 
			TitleCover_Page coverpage= menu.navigateToTitlesCover(list, groupofTitle);
			 TitleLanding_Page landingpage = coverpage.clickOnTitlesCover(Title);
			 return landingpage;
		}

		@Override
		public void navigateToCollectionTitle(String section,String subsection, String title) throws Exception {
			Menu menu=new Menu(); 
			TitleCover_Page coverpage=menu.navigateToCollections(section);
			 coverpage.clickOnTitlesCover(subsection);
			 Thread.sleep(8000);
			new Collections_Page().clickIncludeTitles(title);
			  Baseclass.switchToWindow();
		
			
		}
}
