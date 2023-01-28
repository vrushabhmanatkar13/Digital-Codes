package com.Digitalcodes.TestCase;

import java.time.Duration;
import java.util.Properties;

import com.Digitalcodes.Util.Baseclass;
import com.Digitalcodes.Util.LoadData;
import com.Digitalcodes.Util.Load_Excle;
import com.Digitalcodes.Util.Sparkreport;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResult;

import perfecto_cloud.PerfectoLabUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.annotations.IBeforeTest;
import org.testng.internal.annotations.ITest;

//@Listeners(com.Digitalcodes.TestCase.LogResult.class)
public class Prerequisites_Teardown extends Baseclass {

	public static Properties prop;
	public static Sparkreport report;
	public static Load_Excle excel;
	ReportiumClient reportiumClient;

	public String platform;

	@Parameters({"tagName"})
	@BeforeSuite(alwaysRun = true)
	public void setdata(String tag) {
		try {
			LoadData data = new LoadData();
			prop = data.load_properties();

			excel = new Load_Excle(prop.getProperty("Excle"), prop.getProperty("Sheet"));

			report = new Sparkreport(prop.getProperty("Title"), prop.getProperty("Report_Name"));
			System.out.println("Object is initilized");

			String browserName = prop.getProperty("browserName");
			String version = prop.getProperty("version");
			String incognito = prop.getProperty("incognito");
			String headless = prop.getProperty("headless");
			String cloudName = prop.getProperty("cloudName");
			String securityToken = prop.getProperty("securityToken");
			platform = prop.getProperty("platform");
			String wait = prop.getProperty("wait");
			/*
			 * Launch Browser Getting from properties file
			 * 
			 * @Parameters
			 * 
			 * @Properties Config.properties
			 */

			Baseclass.browserlaunch(incognito, headless, version, browserName, platform, securityToken, cloudName, tag);

			/*
			 * NAvigate to Url Getting from properties file
			 * 
			 * @Parameters
			 * 
			 * @Properties Config.properties
			 */
			Baseclass.Navigateurl(prop.getProperty("Url"));

			/*
			 * Add Implicit Wait
			 * 
			 * @Parameters
			 * 
			 * @Properties Config.properties Convert String to Long
			 */
			Implicitwait(Long.parseLong(wait));
			System.out.println(driver.getCurrentUrl());

			System.out.println("Report Generated");

			/*
			 * if (prop.getProperty("browserName").equalsIgnoreCase("Perfecto")) {
			 * reportiumClient = PerfectoLabUtils.setReportiumClient(driver,
			 * reportiumClient); }
			 */

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@BeforeMethod(alwaysRun=false,enabled = false)
	public void read(ITestContext test) throws Exception {
		int i = 1;
		testStart(test.getName(), String.valueOf(i));
		i++;

	}

	@AfterMethod(alwaysRun = true, enabled = true)
	public void log_result(ITestResult result) throws InterruptedException {

		Sparkreport.flush();

		logResult(result);

		System.out.println("<------------Test Result Logged----------------->");
		Thread.sleep(5000);

	}

	@AfterSuite
	public void afterSuite() {
		closebrowser();
        driver.quit();
		getReporturl();

	}

}
