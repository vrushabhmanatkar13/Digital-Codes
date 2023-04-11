package com.Digitalcodes.testcases;

import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.Header;
import com.Digitalcodes.utilities.*;

public class TC_Homepage extends Prerequisites_Teardown  {
	
	private Load_Excle excle=Prerequisites_Teardown.excel;
	@Test(priority = 10,description = "User able to logout")
	private void TC_Logout() {
		
		Header home=new Header(driver);
		report.create_info(this.excle.getCellValue(10, "Steps"));
		//String singin=home.sign_out();
	//	Assert.assertEquals(singin, this.excle.getCellValue(10, "Expecated text"));
		
	}

}
