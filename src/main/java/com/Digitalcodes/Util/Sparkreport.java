package com.Digitalcodes.Util;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Sparkreport {
	
	public ExtentSparkReporter spark;
	private static ExtentReports extent;
	private static  ExtentTest test;
	
	public Sparkreport(String title,String report_name) {
		spark=new ExtentSparkReporter(System.getProperty("user.dir")+"\\Report\\Reportsparkreport.html");
		extent=new ExtentReports();
		extent.attachReporter(spark);
	    spark.config().setDocumentTitle(title);
	    spark.config().setReportName(report_name);
	    spark.config().setTheme(Theme.DARK);
		
	}
	
	public void create_test(String name, String auth_name, String cataergory, String device) {
	
		test=extent.createTest(name).assignAuthor(auth_name).assignCategory(cataergory).assignDevice(device);
	}
	
	public void create_info(String name) {
		test.log(Status.INFO, name);
	}
	public void test_pass(String name) throws IOException {
		
		test.log(Status.PASS, name);
		
	}
	public void test_fail(String name) throws IOException {
		
		test.log(Status.FAIL, name);
		test.addScreenCaptureFromPath(TakeScreenshot.Take_screenshot());
		
	}
	public void test_skip(String name) {
		test.log(Status.SKIP, name);
	}
	public static void flush() {
		extent.flush();
	}

}
