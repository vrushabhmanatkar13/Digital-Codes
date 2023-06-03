package com.Digitalcodes.testcases;

import com.Digitalcodes.pageobject.Login_Page;
import com.Digitalcodes.utilities.Sparkreport;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login_Test extends Prerequisites_Teardown {

	public static String EMAIL;
	public static String NAME;
    public static String SUBSCRIPTION;
    
   
	
	
	  @DataProvider(name="Dynamic") 
	  public Object[][] getData() throws Exception{
      if (USER.equalsIgnoreCase("PREMIUM")){ 
		  return excel.getDataFromExcle("Users",1); }
	  else if(USER.equalsIgnoreCase("SINGLE")) {
		  return excel.getDataFromExcle("Users",2);
	  }
	  else if (USER.equalsIgnoreCase("BASIC")) {
		  return excel.getDataFromExcle("Users",3);
	  }
	  else {
		  throw new Exception("Please Select User like : Premium, Single, Basic");
	  }
	  
	  
	  }
	  
	  
	  
	 	
	
	@Test(priority = 1, description = "User able to Login with valid creditionls", dataProvider = "Dynamic",groups = {"Smoke","Regression"})

	public void TC01_loginValidData(String Username, String Password, String Name, String Subscription) throws InterruptedException {
		
		
		
		Login_Page login = new Login_Page();
		header.clickSignIn();
		Sparkreport.Step("Click SignIN");
		
		login.login(Username, Password);
		Sparkreport.Step("Enter username " +Username);
		Sparkreport.Step("Enter password " + Password);
		Sparkreport.Step("Click SignIn Button");
		report.create_info("Page Title is " + getTitle());
		Thread.sleep(4000);

		// Verifying Url and Title of Page
		assertEquals(driver.getCurrentUrl(), prop.getProperty("Url") + "dashboard/library");
		assertEquals(getTitle(), jsonArrayValue("Page-titles", "library"));

	}

	@Test(dependsOnMethods = "TC01_loginValidData", dataProvider = "Dynamic",description = "Verify User able to displyed Subscription type, Name, email ",groups = {"Smoke","Regression"})
	public void TC02_verifyUserInformation(String Username, String Password, String Name, String Subscription) throws InterruptedException {

         Thread.sleep(10000);
	    SUBSCRIPTION = header.getSubscrptionType();
		
		
		header.holdOnSubscriptionType();
		Sparkreport.Step("Hover on "+ SUBSCRIPTION);
		
		Thread.sleep(4000);
		NAME = header.getName();
		EMAIL = header.getEmail();

		// Verify Subscription type - UserName - Email
		report.create_info("Subscripton Types is " + SUBSCRIPTION);
		report.create_info("User Name is " + NAME);
		report.create_info("User Email is " + EMAIL);
		report.create_info("Page Title is " + driver.getTitle());
        
		assertEquals(SUBSCRIPTION,Subscription);
		assertEquals(NAME, Name);
		assertEquals(EMAIL,Username);
	}

}
