package com.Digitalcodes.testcases;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import com.Digitalcodes.utilities.Sparkreport;
import com.Digitalcodes.utilities.TakeScreenshot;

import io.qameta.allure.Allure;

public class LogResult extends Prerequisites_Teardown implements ITestListener {

	//public static Properties prop;
	 @Override
	  public void onTestStart(ITestResult result) {
	   
		try {
			
			  
			report.create_test(result.getMethod().getMethodName(), prop.getProperty("Auth_Name") ,PLATFORM +"   :-  "+ BEOWSER_NAME);
			
			testStart(result.getMethod().getMethodName(),Sparkreport.TAGNAME); 
			
		//System.out.println(result.getMethod().getId());
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	  
	  
	  
	  }
	 
	 @Override
	public void onTestSuccess(ITestResult result) {
            
		try {
			
			report.create_info(result.getMethod().getDescription());
			report.test_pass(result.getMethod().getMethodName());
			
			System.out.println(result.getMethod().getMethodName() + " : PASSED");
		

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	 @Override
	public void onTestFailure(ITestResult result) {
		
		try {
			report.create_info(result.getMethod().getDescription());
			report.test_fail(result.getThrowable()+"", result);
			
			Allure.addAttachment(result.getMethod().getMethodName(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)) );
			//TakeScreenshot.allure_screenshot();
			System.out.println(result.getMethod().getMethodName() + " : FAILED");
			System.out.println("     ");
			result.getThrowable().printStackTrace();
			System.out.println("     ");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	 @Override
	public void onTestSkipped(ITestResult result) {
		report.create_info(result.getMethod().getDescription());
		report.test_skip(result.getMethod().getMethodName());
		
		System.out.println(result.getMethod().getMethodName() + " : SKIPED");
	}

	/*
	 * public void onStart(ITestContext context) { // not implemented }
	 * 
	 * }
	 */

	
	 @Override
	  public void onFinish(ITestContext context) {
	  
	  try {
		Desktop.getDesktop().browse(new File(Sparkreport.REPORTPATH).toURI());
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	  
	  
	  
	  
	  }
	 
	
	
	 

}
