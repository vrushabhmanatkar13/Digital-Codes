package com.Digitalcodes.TestCase;

import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.Digitalcodes.SetCapbilites;
import com.Digitalcodes.Util.Baseclass;
import com.Digitalcodes.Util.Load_Excle;
import com.Digitalcodes.Util.Sparkreport;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.test.TestContext;

import page_Objects.MyLibrarypage;
import perfecto_cloud.PerfectoLabUtils;

public class TC_MyLibrary extends Prerequisites_Teardown{

	public MyLibrarypage library;
	@Test(priority = 4,description = "validte sync", enabled = false)
	public void TC2() {
     library=new MyLibrarypage();
		try {
			boolean cancel = library.fun_sync();
			 Assert.assertEquals(Load_Excle.Read_String(2, 0), "TC2");
			
			report.create_info(Load_Excle.Read_String(2, 1));
			
			Assert.assertTrue(cancel);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test(priority =5,description = "validte view entire library", enabled = false)
	public void TC3() {

		try {
			String view_entirelibrary=library.fun_view_entirelibrary();
		Assert.assertEquals(Load_Excle.Read_String(3, 0), "TC3");
		report.create_info(Load_Excle.Read_String(3, 1));
			
		Assert.assertEquals(view_entirelibrary, Load_Excle.Read_String(3, 4));
		Assert.assertEquals(library.label_your_entirelibrary.getText(),Load_Excle.Read_String(3, 5));
		Baseclass.scroll(library.label_your_entirelibrary);
		
		Assert.assertTrue(library.label_result.isDisplayed());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
}
