package com.Digitalcodes.testcases;

import java.io.IOException;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
@Listeners
public class LogResult extends Prerequisites_Teardown implements ITestListener {

	//public static Properties prop;
	
	  public void onTestStart(ITestResult result) {
	   
		try {
			
			  
			report.create_test(result.getMethod().getMethodName(), prop.getProperty("Auth_Name"),"null" ,prop.getProperty("browserName"));
			
			testStart(result.getMethod().getDescription(),"null"); 
			
		//System.out.println(result.getMethod().getId());
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	  
	  
	  
	  }
	 

	public void onTestSuccess(ITestResult result) {
            
		try {
			report.test_pass(result.getMethod().getMethodName());
			
			System.out.println(result.getMethod().getMethodName()+ " <------------ Passed -------->");
		

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		
		try {
			report.test_fail(result.getMethod().getMethodName() + " is failed due to /n" +result.getThrowable(), result);
			
			System.out.println(result.getMethod().getMethodName() + " <----------- Failed -------->");
			System.out.println(result.getThrowable());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		report.test_skip(result.getMethod().getMethodName());
	}

	/*
	 * public void onStart(ITestContext context) { // not implemented }
	 * 
	 * }
	 */

	
	/*
	 * public void onFinish(ITestContext context) {
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
	
	
	 

}
