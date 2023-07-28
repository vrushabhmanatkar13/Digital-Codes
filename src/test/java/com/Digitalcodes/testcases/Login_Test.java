package com.Digitalcodes.testcases;

import com.Digitalcodes.pageobject.LoginPage;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.Sparkreport;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login_Test extends Prerequisites_Teardown {

	public static String EMAIL;
	public static String PASSWORD;
	public static String NAME;
    public static String SUBSCRIPTION;
   
    LoginPage login;
    @BeforeClass(alwaysRun = true)
    public void beforeClass(){
    	 login = new LoginPage();
    }
    
	  @DataProvider(name="Dynamic") 
	  public Object[][] getData() throws Exception{
      if (USER.equalsIgnoreCase("PREMIUM")) { 
		  return excel.getDataFromExcle("Users",1); 
		  }
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
	  
	  
	  
	 	
	
	@Test(priority = 1, description = "User able to Login with valid creditionls",dataProvider = "Dynamic",groups = {"Smoke","Regression"})

	public void TC01_loginValidData(String Username, String Password, String FirstName,String LastName, String Subscription) throws InterruptedException {
		
		userdata.put("ExistingEMAIL", Username);
		userdata.put("ExistingPASSWORD", Password);
		userdata.put("ExistingFIRST_NAME", FirstName);
		userdata.put("ExistingLAST_NAME", LastName);
		userdata.put("ExistingSUBSCRIPTION", Subscription);
		
		
		header.clickSignIn();
		Sparkreport.Step("Click SignIN");
		String title=getTitle();
		login.login(userdata.get(USERTYPE+"EMAIL"), userdata.get(USERTYPE+"PASSWORD"));
		Sparkreport.Step("Enter username " +userdata.get(USERTYPE+"EMAIL"));
		Sparkreport.Step("Enter password " + userdata.get(USERTYPE+"PASSWORD"));
		Sparkreport.Step("Click SignIn Button");
		Sparkreport.Step("Page Title is " + title);
		Thread.sleep(4000);
	
		assertEquals(title, jsonArrayValue("Page-titles", "login"));
		assertEquals(driver.getCurrentUrl(), URL+"dashboard/library");
		assertEquals(getTitle(), jsonArrayValue("Page-titles", "library"));

	}

	@Test(priority = 2,description = "Verify User able to displyed Subscription type, Name, email ",groups = {"Smoke","Regression"})
	public void TC02_verifyUserInformation() throws InterruptedException {

        Thread.sleep(3000);
	    SUBSCRIPTION = header.getSubscrptionType();
		
		header.holdOnSubscriptionType();
		Sparkreport.Step("Hover on "+ SUBSCRIPTION);
		
		Thread.sleep(3000);
		 NAME = header.getName();
		EMAIL = header.getEmail();
   
		// Verify Subscription type - UserName - Email
		Sparkreport.Step("Subscripton Types is " + SUBSCRIPTION);
		Sparkreport.Step("User Name " + NAME);
		Sparkreport.Step("User Email " + EMAIL);
		Sparkreport.Step("Page Title " + driver.getTitle());
		
		assertEquals(SUBSCRIPTION,userdata.get(USERTYPE+"SUBSCRIPTION"));
		assertEquals(NAME, userdata.get(USERTYPE+"FIRST_NAME")+" "+userdata.get(USERTYPE+"LAST_NAME"));
		assertEquals(EMAIL,userdata.get(USERTYPE+"EMAIL"));
	}
	
	

}
