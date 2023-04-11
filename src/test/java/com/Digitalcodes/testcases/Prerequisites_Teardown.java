package com.Digitalcodes.testcases;

import java.util.Properties;

import com.Digitalcodes.perfectocloud.Perfecto_Capabailites;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.LoadPropertiesfile;
import com.Digitalcodes.utilities.Load_Excle;
import com.Digitalcodes.utilities.Sparkreport;
import com.perfecto.reportium.client.ReportiumClient;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners(com.Digitalcodes.testcases.LogResult.class)
public class Prerequisites_Teardown extends Baseclass {

	public static Properties prop;
	public static Sparkreport report;
	public static Load_Excle excel;
	public Baseclass baseclass;
	ReportiumClient reportiumClient;
   public  String tagname;
  
	
	@BeforeSuite(alwaysRun = true)
	@Parameters({"tagName"})
	public void setdata(String tagname ) {
		try {
			LoadPropertiesfile data = new LoadPropertiesfile();
			prop = data.load_properties();
			excel = new Load_Excle(prop.getProperty("Sheet"));

			report = new Sparkreport(prop.getProperty("Title"), prop.getProperty("Report_Name"), prop.getProperty("platform"));
			baseclass=new Baseclass();
			
			System.out.println("Objects are initilized ====================>>");
			System.out.println("Report Generated ================>>");

			
			String browserName = prop.getProperty("browserName");
			String incognito = prop.getProperty("incognito");
			String headless = prop.getProperty("headless");
			String cloudName = prop.getProperty("cloudName");
			String securityToken = prop.getProperty("securityToken");
			String platform = prop.getProperty("platform");
			String wait = prop.getProperty("wait");
			this.tagname=tagname;
			
			/*
			 * Launch Browser Getting from properties file
			 * 
			 * @Parameters
			 * 
			 * @Properties Config.properties
			 */

			baseclass.browserLaunch(incognito, headless, browserName, platform, securityToken, cloudName, tagname);

			/*
			 * NAvigate to Url Getting from properties file
			 * 
			 * @Parameters
			 * 
			 * @Properties Config.properties
			 */
			baseclass.navigateToUrl(prop.getProperty("Url"));

			/*
			 * Add Implicit Wait
			 * 
			 * @Parameters
			 * 
			 * @Properties Config.properties Convert String to Long
			 */
			baseclass.implicitWait(Long.parseLong(wait));
			
			baseclass.waitForElement(Long.parseLong(wait));
			
			System.out.println(driver.getCurrentUrl());
			
			new Perfecto_Capabailites();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	


	

	@AfterMethod(alwaysRun = true, enabled = true)
	public void log_result(ITestResult result) throws Exception {

		Sparkreport.flush();
		logResult(result);
		
		System.out.println("<------------Test Result Logged----------------->");
		Thread.sleep(2000);
		System.out.println(result.getMethod().getDescription());
		System.out.println("=======================================================");

	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		baseclass.closeBrowser();
        driver.quit();
		getReporturl();

	}

}
