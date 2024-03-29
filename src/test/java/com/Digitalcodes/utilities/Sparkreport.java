package com.Digitalcodes.utilities;

import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;

import com.Digitalcodes.perfectocloud.Perfecto_Capabailites;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class Sparkreport {

	public ExtentSparkReporter spark;
	private static ExtentReports extent;
	private static ExtentTest test;
	public static String TAGNAME;
	public static String REPORTPATH;

	public Sparkreport(String title, String report_name, String hostname, String user, String tagname) {
		// String Dateformat=new SimpleDateFormat("YYYY-MM-DD").format(new Date());

		REPORTPATH = System.getProperty("user.dir") + "\\Report\\DigitalCodes_SparkReport.html";

		spark = new ExtentSparkReporter(REPORTPATH);
		spark.config().setDocumentTitle(title);
		spark.config().setReportName(report_name);
		spark.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Enviroment/Platform ", hostname);
		extent.setSystemInfo("OS ", System.getProperty("os.name"));
		extent.setSystemInfo("Java Version ", System.getProperty("java.version"));

		TAGNAME = tagname + "_" + user;

	}

	public void create_test(String name, String auth_name, String device) {

		test = extent.createTest(name).assignAuthor(auth_name).assignCategory(TAGNAME).assignDevice(device);
	}

	public void create_info(String name) {
		test.log(Status.INFO, name);
		System.out.println(name);
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

	public static void Step(String step) {
		test.log(Status.INFO, step);
		Allure.step(step);
		Perfecto_Capabailites.stepStart(step);
		System.out.println(step);
	}

}
