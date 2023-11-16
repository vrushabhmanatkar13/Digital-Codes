package com.Digitalcodes.testcases;

import java.util.HashMap;
import java.util.Map;
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

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Parameters;

//@Listeners(com.Digitalcodes.testcases.LogResult_ITestListner.class)
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
	static final long WEBDRIVER_WAIT = 60;

	// Objects
	public static Header header;;
	public static Menu menu;
	public static CommanSteps commanstep;
	// Capabilites
	// String browserName;
	static String INCOGNITO;
	static String HEADLESS;
	static final String CLOUD_NAME = "icc";
	static String SECURITY_TOCKEN;
	public static String PLATFORM;
	public static String TAGNAME;
	public static String BEOWSER_NAME;
	static String ENVIROMENT;
	static String WIDTH;
	static String HIGHT;
	static String URL;
	// Users
	public static String USER;
	public static String USERTYPE;
	public static Map<String, String> userdata;

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "tagname", "Enviroment" })
	public void beforeSuite(String tagname, String Enviroment) {
		try {

			LoadPropertiesfile data = new LoadPropertiesfile();
			prop = data.load_properties(System.getProperty("user.dir") + "\\TestData\\Config.properties");
			json = data.readJson(System.getProperty("user.dir") + "\\TestData\\DC.json");
			excel = new Load_Excle();

			userdata = new HashMap<String, String>();

			/*
			 * Pass Platform name in command -DPlatformName="local" OR
			 * -DPlatformName="Perfecto" If not then By default value is local
			 * 
			 * 
			 */
			PLATFORM = Baseclass.fatechPlatformName(prop);
			INCOGNITO = prop.getProperty("incognito");
			/*
			 * Pass headless value in command -Dheadless="true" OR "false" If not then by
			 * default value is false
			 */
			HEADLESS = Baseclass.selectHeadless(prop);

			SECURITY_TOCKEN = prop.getProperty("securityToken");
			// Screen width and height for perfecto execution
			WIDTH = prop.getProperty("width");
			HIGHT = prop.getProperty("hight");
			/*
			 * Pass environment value in command -DEnviroment="Stage" OR "Dev" If not then
			 * by default value is Stage url
			 * 
			 */
			// URL=Baseclass.selectEnviroment(prop);

			ENVIROMENT = Enviroment;
			TAGNAME = tagname;

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@BeforeTest(alwaysRun = true)
	@Parameters({ "Browser", "User", "UserType" })
	public void beforeTest(String BROWSER_NAME, String USER, String USERTYPE) {

		try {
			// Baseclass.fatechBrowserName(prop, System.getProperty("browser"));
			baseclass = new Baseclass();
			URL = Baseclass.selectEnviroment(prop);
			report = new Sparkreport(prop.getProperty("Title"), prop.getProperty("Report_Name"),
					ENVIROMENT + "/" + PLATFORM, USER, TAGNAME);

			baseclass.browserLaunch(INCOGNITO, HEADLESS, BROWSER_NAME, PLATFORM, SECURITY_TOCKEN, CLOUD_NAME, TAGNAME,
					WIDTH, HIGHT);
			baseclass.navigateToUrl(URL);
			baseclass.implicitWait(IMPLICIT_WAIT);
			baseclass.pageLoadTimeout(PAGE_LOAD_WAIT);
			baseclass.waitForElement(WEBDRIVER_WAIT);
			driver.manage().deleteAllCookies();

			BEOWSER_NAME = BROWSER_NAME;
			Prerequisites_Teardown.USER = USER;
			Prerequisites_Teardown.USERTYPE = USERTYPE;

			System.out.println("Platform Name :- " + PLATFORM);
			System.out.println("Browser Name :- " + BROWSER_NAME);
			System.out.println("Headless Mode :- " + HEADLESS);
			System.out.println(driver.getCurrentUrl());
			System.out.println(" ");

			header = new Header();
			menu = new Menu();
			commanstep = new Abstract_CommanMathods_Implinmation();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@BeforeClass(alwaysRun = true, enabled = true)
	public void beforeClass() {

		System.out.println("Header ????????????????????????????????????");

	}

	@AfterMethod(alwaysRun = true)
	public void log_result(ITestResult result) throws Exception {
		stepEnd();
		Sparkreport.flush();
		logResult(result);
		System.out.println("=======================================================");
		System.out.println("  ");

	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {

		 driver.quit();
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {

		getReporturl();

	}

}
