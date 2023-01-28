package com.Digitalcodes.TestCase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import com.Digitalcodes.SetCapbilites;
import com.Digitalcodes.Util.LoadData;
import com.Digitalcodes.Util.Sparkreport;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResult;
import com.perfecto.reportium.test.result.TestResultFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import perfecto_cloud.PerfectoLabUtils;
@Listeners
public class LogResult extends Prerequisites_Teardown implements ITestListener {

	  public void onTestStart(ITestResult result) {
	   LoadData data=new LoadData();

		try {
			Properties prop =data.load_properties();
			  
			report.create_test(result.getMethod().getDescription(), prop.getProperty("Auth_Name"), prop.getProperty("Sheet"),prop.getProperty("browserName"));
			testStart(result.getMethod().getDescription(), prop.getProperty("Sheet")); 
			 System.out.println("<------Test Case is =" + result.getMethod().getDescription() + "-------->");
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	  
	  
	  
	  }
	 

	public void onTestSuccess(ITestResult result) {

		try {
			report.test_pass(result.getMethod().getDescription());
			System.out.println(result.getMethod().getDescription() + "Passed -------->");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		try {
			report.test_fail(result.getMethod().getDescription() + "is failed due to " + result.getThrowable());
			System.out.println(result.getMethod().getDescription() + "Failed -------->");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		report.test_skip(result.getTestName());
	}

	/*
	 * public void onStart(ITestContext context) { // not implemented }
	 * 
	 * }
	 */

	/*
	 * public void onFinish(ITestContext context) {
	 * 
	 * Create Url for Perfecto Report
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

}
