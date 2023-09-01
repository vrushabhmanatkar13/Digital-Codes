package com.Digitalcodes.testcases;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.Registration_Page;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.Sparkreport;

import io.qameta.allure.Step;

public class Registration_Test extends Prerequisites_Teardown {

	Registration_Page register;

	@BeforeClass(alwaysRun = true)
	public void beforeclass() {
		register = new Registration_Page();
	}

	@DataProvider(name = "invalid Data")
	public Object[][] readDataFromExcle() {
		return excel.getDataFromExcle("Registration");
	}

	@Test(priority = 1, description = "Verfiy user able to Register with valid Data", groups = { "Smoke",
			"Regression" })
	public void TC_Registeruser_ValidData() throws InterruptedException {
		String EMAIL = RandomStringUtils.randomAlphabetic(8).toLowerCase() + "@mailinator.com";

		userdata.put("NewEMAIL", EMAIL);
		userdata.put("NewPASSWORD", jsonArrayValue("valid-data", "password"));
		userdata.put("NewFIRST_NAME", jsonArrayValue("valid-data", "firstname"));
		userdata.put("NewLAST_NAME", jsonArrayValue("valid-data", "lastname"));
		userdata.put("NewSUBSCRIPTION", jsonArrayValue("Basic", "subscription"));

		Baseclass.getParentWindow();
		header.clickRegister();
		Sparkreport.Step("Click Register");
		String pagetitle = null;
		try {
			Baseclass.switchToWindow();
			Thread.sleep(2000);
			pagetitle = getTitle();
			Sparkreport.Step("Page Title:- " + pagetitle);
		} catch (Exception e) {

			e.printStackTrace();
		}
		register.registerUser(userdata.get("NewFIRST_NAME"), userdata.get("NewLAST_NAME"),
				jsonArrayValue("valid-data", "security"), userdata.get("NewFIRST_NAME"), EMAIL,
				userdata.get("NewPASSWORD"), userdata.get("NewPASSWORD"), jsonArrayValue("valid-data", "spam"));
		Sparkreport.Step("Enter FirstName:- " + userdata.get("NewFIRST_NAME"));
		Sparkreport.Step("Enter LastName:- " + userdata.get("NewLAST_NAME"));
		Sparkreport.Step("Enter Security Que:- " + jsonArrayValue("valid-data", "security"));
		Sparkreport.Step("Enter Security Ans:- " + userdata.get("NewFIRST_NAME"));
		Sparkreport.Step("Enter Email:- " + EMAIL);
		Sparkreport.Step("Enter Password:- " + userdata.get("NewPASSWORD"));
		Sparkreport.Step("Enter Spamvalue:- " + jsonArrayValue("valid-data", "spam"));

		boolean alert = register.clickcheckBox();
		Sparkreport.Step("Uncheck Free trial CheckBox");
		boolean checkbox = register.checkboxSelected();
		assertTrue(alert);
		assertFalse(checkbox);
		register.clickSubmit();
		Sparkreport.Step("Click Submit");

		assertEquals(pagetitle, jsonArrayValue("Page-titles", "registration"));
		Thread.sleep(4000);
		Sparkreport.Step("Page Title after Submit:- " + getTitle());
		assertEquals(getTitle(), jsonArrayValue("Page-titles", "confirm registration"));

		navigateToUrl(prop.getProperty("mailinator"));
		Baseclass.getParentWindow();
		register.verifyemail(EMAIL);
		Sparkreport.Step("Click Verify Email");
		try {
			Baseclass.switchToWindow();
			Baseclass.closeWindow();
			Baseclass.retrunToMainWindow();
			Baseclass.closeWindow();
			Baseclass.switchToWindow();
			Baseclass.getParentWindow();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test(priority = 1, description = "Verify user unable to Register with invalid Data", dataProvider = "invalid Data")
	public void TC_Registeruser_InValidData(String category, String firstname, String lastname, String securityque,
			String securityans, String email, String password) {

		Baseclass.getParentWindow();
		header.clickRegister();
		Sparkreport.Step("Click Register");
		try {
			Baseclass.switchToWindow();

		} catch (Exception e) {

			e.printStackTrace();
		}

		register.registerUser(firstname, lastname, securityque, securityans, email, password, password,
				jsonArrayValue("valid-data", "spam"));

		Sparkreport.Step("Register user by :- " + category);

		String textboxName = null;
		try {
			register.clearInvalidValue("Invalid");
			textboxName = register.getErrorSymbolOnInputBox();

			register.clickSubmit();
		} catch (Exception e) {

			e.printStackTrace();
		}
		Sparkreport.Step(textboxName);

		assertFalse(register.submitIsEnable());
		Baseclass.closeWindow();
		Baseclass.retrunToMainWindow();
	}

}
