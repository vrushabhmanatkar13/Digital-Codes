package com.Digitalcodes.testcases;

import java.util.Properties;

import com.Digitalcodes.pageobject.Abstract_CommanMathods_Implinmation;
import com.Digitalcodes.pageobject.Header;
import com.Digitalcodes.pageobject.Menu;

import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.LoadPropertiesfile;
import com.Digitalcodes.utilities.Load_Excle;
import com.Digitalcodes.utilities.Sparkreport;
import com.fasterxml.jackson.databind.JsonNode;
import com.perfecto.reportium.client.ReportiumClient;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

//@Listeners(com.Digitalcodes.testcases.LogResult.class)
public class Prerequisites_Teardown extends Baseclass {

	public static Properties prop;
	public static JsonNode json;
	public static Sparkreport report;
	public static Load_Excle excel;
	public Baseclass baseclass;
	ReportiumClient reportiumClient;

	// wait time
	static final long IMPLICIT_WAIT = 20;
	static final long PAGE_LOAD_WAIT = 5;
	static final long WEBDRIVER_WAIT = 90;

	// Objects
	public static Header header;
	public static Menu menu;
	public static CommanSteps commanstep;
	// Capabilites
	// String browserName;
	static String INCOGNITO;
	static String HEADLESS;
	static final String CLOUD_NAME = "icc";
	static String SECURITY_TOCKEN;
	public static String PLATFORM;
	static String TAGNAME;
	static String BEOWSER_NAME;
	static final String ENVIROMENT = "Stage";
	static String RESOLUTION;

	// Users
	public static String USER;

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "tagname" })
	public void beforeSuite(String tagname) {
		try {

			LoadPropertiesfile data = new LoadPropertiesfile();
			prop = data.load_properties();
			json = data.readJson(System.getProperty("user.dir") + "\\TestData\\DC.json");

			excel = new Load_Excle();

			report = new Sparkreport(prop.getProperty("Title"), prop.getProperty("Report_Name"),ENVIROMENT + " / " + PLATFORM, tagname);
			baseclass = new Baseclass();

			// System.out.println("Properties file are loaded ====================>>");
			// System.out.println("Report Generated ================>>");
			/*
			 * Pass Platform name in command 
			 * -DPlatformName="local"       OR       -DPlatformName="Perfecto"
			 * If not then By default if Take value is local 
			*/
			  PLATFORM = Baseclass.fatechPlatformName(prop);
			
			
			INCOGNITO = prop.getProperty("incognito");
			HEADLESS = prop.getProperty("headless");
			SECURITY_TOCKEN = prop.getProperty("securityToken");
			RESOLUTION=prop.getProperty("Resolution");
			TAGNAME = tagname;

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@BeforeTest(alwaysRun = true)
	@Parameters({ "Browser", "User" })
	public void beforeTest(String BROWSER_NAME, String USER) {

		try {
			// Baseclass.fatechBrowserName(prop, System.getProperty("browser"));

			baseclass.browserLaunch(INCOGNITO, HEADLESS, BROWSER_NAME, PLATFORM, SECURITY_TOCKEN, CLOUD_NAME, TAGNAME,RESOLUTION,"1920","1080");
			baseclass.navigateToUrl(prop.getProperty("Url"));
			baseclass.implicitWait(IMPLICIT_WAIT);
			baseclass.pageLoadTimeout(PAGE_LOAD_WAIT);
			baseclass.waitForElement(WEBDRIVER_WAIT);
			driver.manage().deleteAllCookies();
			Baseclass.refreshBrowser();

			BEOWSER_NAME = BROWSER_NAME;
			Prerequisites_Teardown.USER = USER;

			System.out.println("Platform Name :- " + PLATFORM);
			System.out.println("Browser Name :- " + BROWSER_NAME);
			System.out.println(" ");

			System.out.println(driver.getCurrentUrl());
			System.out.println(" ");

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		header = new Header();
		menu = new Menu();
		commanstep = new Abstract_CommanMathods_Implinmation();

	}

	@AfterMethod(alwaysRun = true, enabled = true)
	public void log_result(ITestResult result) throws Exception {
		stepEnd();
		Sparkreport.flush();
		logResult(result);
		System.out.println("=======================================================");
		System.out.println("  ");

	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		closeWindow();
		driver.quit();
		getReporturl();

	}

	@DataProvider(name = "SingleTitle")
	public Object[][] getTitlesByRow() {

		return excel.getDataFromExcle("Titles", 1);
	}

	@DataProvider(name = "Title")
	public Object[][] getAllTitles() {
		return excel.getDataFromExcle("Titles");
	}

}
