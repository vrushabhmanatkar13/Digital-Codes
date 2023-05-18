package com.Digitalcodes.perfectocloud;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import com.Digitalcodes.capabilities.SetCapbilites;
import com.Digitalcodes.testcases.Prerequisites_Teardown;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResult;
import com.perfecto.reportium.test.result.TestResultFactory;
import com.perfecto.reportium.test.result.TestResultFailure;
import com.perfecto.reportium.test.result.TestResultSuccess;
import com.perfecto.reportium.test.result.TestResultVisitor;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Perfecto_Capabailites extends SetCapbilites {

	static ReportiumClient reportiumClient;
	static String PLATFORM;
	ChromeOptions chrome;
	FirefoxOptions firefox;
	RemoteWebDriver Rdriver;

	
	
	
	
	public static Map<String, Object> perfectoOptions(String securityToken) {
		Map<String, Object> perfectoOptions = new HashMap<>();
		perfectoOptions.put("resolution", "1920x1080");
		perfectoOptions.put("securityToken", securityToken);
		perfectoOptions.put("platformVersion", "10");
	
		
		return perfectoOptions;
		
	}

	public WebDriver Perfecto(String browserName, String securityToken, String cloudName, String tag, String incognito, String headless) throws Exception {

		if (browserName.equalsIgnoreCase("Chrome")) {
			chrome=getChromecapabalites(incognito, headless);
			chrome.setPlatformName("Windows");
			chrome.setBrowserVersion("latest");
			chrome.setCapability("perfecto:options", perfectoOptions(securityToken));
		    
			 Rdriver = new RemoteWebDriver(
					new URL("https://"+ cloudName +".perfectomobile.com/nexperience/perfectomobile/wd/hub"),chrome);
		}
		if (browserName.equalsIgnoreCase("firefox")) {
			firefox=getFirefoxcapabalites(incognito, headless);
			firefox.setPlatformName("Windows");
			firefox.setBrowserVersion("latest");
			firefox.setCapability("perfecto:options", perfectoOptions(securityToken));
			
			 Rdriver = new RemoteWebDriver(
					new URL("https://"+ cloudName +".perfectomobile.com/nexperience/perfectomobile/wd/hub"),firefox);
		}
		
		/*
		 * DesiredCapabilities capabilities = setCapabilities(browserName);
		 * capabilities.setCapability("resolution", "1024x768");
		 */
		// The below capability is mandatory. Please do not replace it.
	//	capabilities.setCapability("securityToken", PerfectoLabUtils.fetchSecurityToken(securityToken));

		

		reportiumClient = PerfectoLabUtils.setReportiumClient(Rdriver, reportiumClient, tag);
		WebDriver driver = (WebDriver) Rdriver;
		
		return driver;
	}

	public static void stepStart(String step) {

		if (PLATFORM.equalsIgnoreCase("Perfecto")) {
			reportiumClient.stepStart(step); // Starts a reportium step

		}
	}

	public static void stepEnd() {
		if (PLATFORM.equalsIgnoreCase("Perfecto")) {
			reportiumClient.stepEnd();
		}
	}

	public static void testStart(String testName, String testtag) throws Exception {
		
		PLATFORM=Prerequisites_Teardown.PLATFORM;

		if (PLATFORM.equalsIgnoreCase("Perfecto")) {
			reportiumClient.testStart(testName, new TestContext(testtag)); // Starts a reportium step

		}
	}
	
	

	public static void Assert(String massege, boolean expected) {

		if (PLATFORM.equalsIgnoreCase("Perfecto")) {
			reportiumClient.reportiumAssert(massege, expected);

		}
	}

	public static void AssertEquels(String actual, String expected) {
		if (PLATFORM.equalsIgnoreCase("Perfecto")) {

			if (actual.equals(expected)) {
				reportiumClient.reportiumAssert("Actual and Expecated text is matched", true);
			} else {
				reportiumClient.reportiumAssert("Actual and Expecated text is not matched", false);

			}

		}

	}

	public static void logResult(ITestResult result) throws Exception {

		if (PLATFORM.equalsIgnoreCase("Perfecto")) {
			
			
			TestResult testResult = null;
			if (result.getStatus() == ITestResult.SUCCESS) {
				testResult = TestResultFactory.createSuccess();
			
			} else if (result.getStatus() == ITestResult.FAILURE) {
				testResult = TestResultFactory.createFailure(result.getThrowable());
			         	
			}

			reportiumClient.testStop(testResult);
		}

	}

	public void getReporturl() {
		if (PLATFORM.equalsIgnoreCase("Perfecto")) {
			String reportURL = reportiumClient.getReportUrl();
			System.out.println(reportURL);
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.get(reportURL);
			driver.manage().window().maximize();
		}
	}

}
