package com.Digitalcodes.TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Digitalcodes.Util.Sparkreport;
import com.Digitalcodes.Util.*;
import page_Objects.Homepage;

public class TC_Homepage extends Prerequisites_Teardown  {
	
	private Load_Excle excle=Prerequisites_Teardown.excel;
	@Test(priority = 10,description = "User able to logout")
	private void TC_Logout() {
		
		Homepage home=new Homepage();
		report.create_info(this.excle.Row(10, "Steps"));
		String singin=home.sign_out();
		Assert.assertEquals(singin, this.excle.Row(10, "Expecated text"));
		
	}

}
