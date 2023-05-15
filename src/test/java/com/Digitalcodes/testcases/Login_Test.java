package com.Digitalcodes.testcases;

import com.Digitalcodes.pageobject.Login_page;
import com.Digitalcodes.utilities.Sparkreport;

import org.testng.annotations.Test;

public class Login_Test extends Prerequisites_Teardown {

	public static String EMAIL;
	public static String NAME;

	@Test(priority = 1, description = "User able to Login with valid creditionls")

	public void login() throws InterruptedException {
		Login_page login = new Login_page();
		
		
		header.clickSignIn();
		Sparkreport.Step("Click SignIN");
		
		login.login(jsonValue("username"), jsonValue("password"));
		Sparkreport.Step("Enter username " + jsonValue("username"));
		Sparkreport.Step("Enter password " + jsonValue("password"));
		Sparkreport.Step("Click SignIn Button");
		report.create_info("Page Title is " + getTitle());
		Thread.sleep(6000);

		// Verifying Url and Title of Page
		assertEquals(driver.getCurrentUrl(), prop.getProperty("Url") + "dashboard/library");
		assertEquals(getTitle(), jsonArrayValue("Page-titles", "library"));

	}

	@Test(dependsOnMethods = "login", description = "Verify User able to displyed Subscription type, Name, email ")
	public void verifyUserInformation() {


		String subscription = header.getSubscrptionType();
		
		header.holdOnSubscriptionType();
		Sparkreport.Step("Hover on "+ subscription);
		NAME = header.getName();
		EMAIL = header.getEmail();

		// Verify Subscription type - UserName - Email
		report.create_info("Subscripton Types is " + subscription);
		report.create_info("User Name is " + NAME);
		report.create_info("User Email is " + EMAIL);
		report.create_info("Page Title is " + driver.getTitle());

		assertEquals(subscription, jsonValue("subscription-type"));
		assertEquals(NAME, jsonValue("full-name"));
		assertEquals(EMAIL, jsonValue("username"));

	}

}
