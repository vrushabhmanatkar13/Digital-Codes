package com.Digitalcodes.testcases;

import com.Digitalcodes.pageobject.Codes_Section;
import com.Digitalcodes.pageobject.Favorite;

import com.Digitalcodes.pageobject.Login_page;

import com.Digitalcodes.pageobject.TOC;
import com.Digitalcodes.pageobject.TitleCover_Page;
import com.Digitalcodes.pageobject.TitleLanding_Page;
import com.Digitalcodes.perfectocloud.AssertStatements;


import org.testng.Assert;

import org.testng.annotations.Test;

public class TC_Loginpage extends Prerequisites_Teardown {

	
	
	public static String email;
	public static String name;
	
	@Test(priority = 1, description = "User able to Login with valid creditionls")

	public void login() throws InterruptedException {
		Login_page login = new Login_page();
		
		
		stepStart("===== Enter Username and Passowrd, Click SignIn button ========");
		
		report.create_info(excel.getCellValue(1, "Steps"));
        
		//click on SingIn Button Show On Header
		 header.clickSignIn();
		
		//Enter User-name and Password then Click on SignIn
		login.login(excel.getCellValue(1, "Username"), excel.getCellValue(1, "Password"));

		report.create_info("Username is " + excel.getCellValue(1, "Username")+ "&" +"Password is "+excel.getCellValue(1, "Password"));
		report.create_info("Page Title is " + driver.getTitle());
		
		stepEnd();
		Thread.sleep(4000);
		
		//Verify first page should be My library
	    AssertStatements.assertEquals(driver.getCurrentUrl(), prop.getProperty("Url")+"dashboard/library");
	    Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("Url")+"dashboard/library");
		Assert.assertEquals(driver.getTitle(), "My Library");
		
		
	}
	
	
	@Test(dependsOnMethods = "login", description = "Verify User able to displyed Subscription type, Name, email ")
	public void verifyUserInformation() {
		
		stepStart("===== Get Subscription Type and User Name, Email =======");
		report.create_info("Login > mouser over on > Subscription Type");
		
		try {
			Thread.sleep(2000);
		
		//Get Subscription type	
		String subscription=header.getSubscrptionType();
		//Hold on Subscription type
		 header.holdOnSubscriptionType();
		//get the User Name and Email from drop down
		name=header.getName();
		email=header.getEmail();
		
		//Verify Subscription type - UserName - Email  
		stepEnd();
		report.create_info("Subscripton Types is "+subscription);
		report.create_info("User Name is "+name);
		report.create_info("User Email is "+email);
		report.create_info("Page Title is " + driver.getTitle());
		
		AssertStatements.assertEquals(subscription, excel.getCellValue(1, "Expected text"));
		AssertStatements.assertEquals(name,excel.getCellValue(1, "Expected text _1"));
		AssertStatements.assertEquals(email, excel.getCellValue(1, "Username"));
		
		Assert.assertEquals(subscription, excel.getCellValue(1, "Expected text"));
		Assert.assertEquals(name,excel.getCellValue(1, "Expected text _1"));
		Assert.assertEquals(email, excel.getCellValue(1, "Username"));
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

	
	
	
	
	
	
	
	
	

}
