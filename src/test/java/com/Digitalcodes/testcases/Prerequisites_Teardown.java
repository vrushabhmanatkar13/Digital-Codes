package com.Digitalcodes.testcases;

import java.util.Properties;

import com.Digitalcodes.pageobject.Header;
import com.Digitalcodes.pageobject.Menu;

import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.LoadPropertiesfile;
import com.Digitalcodes.utilities.Load_Excle;
import com.Digitalcodes.utilities.Sparkreport;
import com.perfecto.reportium.client.ReportiumClient;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

//@Listeners(com.Digitalcodes.testcases.LogResult.class)
public class Prerequisites_Teardown extends Baseclass {

	public static Properties prop;
	public static Sparkreport report;
	public static Load_Excle excel;
	public Baseclass baseclass;
	ReportiumClient reportiumClient;
   public  String tagname;
   
   //Objects
   public static Header header;
   public static Menu menu;
    
   //Capabilites 
  // String browserName; 
   String incognito;
   String headless;
   String cloudName;
   String securityToken;
   String platform;
   String wait;
	@BeforeSuite(alwaysRun = true)
	@Parameters({"tagname","platform"})
	public void setdata(String tagname, String platform) {
		try {
			
			LoadPropertiesfile data = new LoadPropertiesfile();
			prop = data.load_properties();
			
			excel = new Load_Excle(prop.getProperty("Sheet"));

			report = new Sparkreport(prop.getProperty("Title"), prop.getProperty("Report_Name"), prop.getProperty("platform"),tagname);
			baseclass=new Baseclass();
			
			System.out.println("Properties file are loaded ====================>>");
			System.out.println("Report Generated ================>>");

			
			// browserName = prop.getProperty("browserName");
			 incognito = prop.getProperty("incognito");
			 headless = prop.getProperty("headless");
			 cloudName = prop.getProperty("cloudName");
			 securityToken = prop.getProperty("securityToken");
		    // this.platform = prop.getProperty("platform");
			 this.platform=platform;
			 
			 
			 System.out.println(platform);
			 wait = prop.getProperty("wait");
			this.tagname=tagname;
			
			
			
			
			
			
			
			      

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
  @BeforeTest(alwaysRun = true)
  @Parameters({"Browser"})
  public void beforeTest(String browsername) {
	  

		try {
			baseclass.browserLaunch(incognito, headless,browsername, platform, securityToken, cloudName, tagname);
			baseclass.navigateToUrl(prop.getProperty("Url"));
            baseclass.implicitWait(Long.parseLong(wait));
			baseclass.waitForElement(Long.parseLong(wait));
			
			System.out.println(browsername);
			
			
			System.out.println(driver.getCurrentUrl()+"  ====================  Url loaded=============>>");
			
			
			 header=new Header();
			  menu=new Menu();
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	  
	 
  }
  
  
  

	

	@AfterMethod(alwaysRun = true, enabled = true)
	public void log_result(ITestResult result) throws Exception {

		Sparkreport.flush();
		logResult(result);
		
		System.out.println("<------------Test Result Logged----------------->");
		Thread.sleep(2000);
		
		System.out.println("=======================================================");

	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		baseclass.closeBrowser();
        driver.quit();
        
		getReporturl();

	}

}
