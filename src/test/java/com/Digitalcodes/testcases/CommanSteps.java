package com.Digitalcodes.testcases;


import com.Digitalcodes.pageobject.TitleLanding_Page;

public abstract class CommanSteps {
 
	/** first click on menu, click ListItem in menu then click on title Cover 
	 * @return 
	*
	*
    *
	*/
	public abstract TitleLanding_Page navigetToTitle(String list, String groupofTitle, String Title);
	
	
	public abstract void navigateToCollectionTitle(String section,String subsection, String title) throws Exception;
}




 

	
	
