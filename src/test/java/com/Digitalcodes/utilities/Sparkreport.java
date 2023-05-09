package com.Digitalcodes.utilities;


import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Sparkreport {
	
	public ExtentSparkReporter spark;
	private static ExtentReports extent;
	private static  ExtentTest test;
	private String tagname; 
	
	public Sparkreport(String title,String report_name,String hostname, String tagname) {
		String Dateformat=new SimpleDateFormat("YYYY-MM-DD").format(new Date());
		
		

		spark=new ExtentSparkReporter(System.getProperty("user.dir")+"\\Report\\"+Dateformat+"\\Reportsparkreport.html");
		extent=new ExtentReports();
		extent.attachReporter(spark);
	    spark.config().setDocumentTitle(title);
	    spark.config().setReportName(report_name);
	    spark.config().setTheme(Theme.DARK);
	    extent.setSystemInfo("System info", hostname);
	    this.tagname=tagname;
		
	}
	
	public void create_test(String name, String auth_name, String device) {
	
		test=extent.createTest(name).assignAuthor(auth_name).assignCategory(tagname).assignDevice(device);
	}
	
	public void create_info(String name) {
		test.log(Status.INFO, name);
	}
	public void test_pass(String name) throws IOException {
		
		test.log(Status.PASS, name);
		
	}
	public void test_fail(String name, ITestResult result) throws IOException {
		
		test.log(Status.FAIL, name);
		test.addScreenCaptureFromPath(TakeScreenshot.Take_screenshot(result));
		
	}
	public void test_skip(String name) {
		test.log(Status.SKIP, name);
	}
	public static void flush() {
		extent.flush();
	}

}
