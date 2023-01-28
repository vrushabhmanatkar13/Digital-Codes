package com.Digitalcodes.TestCase;

import java.io.IOException;

import com.Digitalcodes.SetCapbilites;

import com.Digitalcodes.Util.Sparkreport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import org.testng.annotations.Test;

import page_Objects.Codes_Section;
import page_Objects.Homepage;
import page_Objects.Titles;
import page_Objects.Login_page;
import page_Objects.Menu;

public class TC_Loginpage extends Prerequisites_Teardown {

	@Test(priority = 1, description = "User able to Login")

	public void TC1() {
		Login_page login = new Login_page();
		Homepage home = new Homepage();

		stepStart(excel.Row(1, "Steps"));

		home.fun_signin();
		report.create_info(excel.Row(1, "Steps"));

		login.Fun_Login(excel.Row(1, "Username"), excel.Row(1, "Password"));

		report.create_info("Username is " + excel.Row(1, "Username"));
		report.create_info("Password is " + excel.Row(1, "Password"));
		Assert("Login", true);
		// MyLibrarypage library = new MyLibrarypage();
		// AssertJUnit.assertTrue(library.lable_Welcome.isDisplayed());
		stepEnd();

	}

	@Test(dependsOnMethods = "TC1", description = "Verify user able to see user name and subscription type")
	public void TC1_1() {
		Menu menu = new Menu();
		menu.username_type();

		stepStart( "UserName is "+menu._username +" Type of subscription "+menu._type);

		Assert.assertTrue(menu.username.isDisplayed());
		Assert.assertEquals(menu._type, excel.Row(1, "Expected text"));
		Assert("UserName is Displayed", menu.username.isDisplayed());
		AssertEquels(menu._type, excel.Row(1, "Expected text"));

		report.create_info("User Name is " + menu._username);
		report.create_info("Subscription Type is " + menu._type);

		stepEnd();

	}

	@Test(priority = 2, description = "Verify user able to navigate to Title")
	public void TC4() {
		Menu menu = new Menu();
		stepStart(excel.Row(4, "Steps"));
		report.create_info(excel.Row(4, "Steps"));

		menu.menu();
		String itemname = menu.click_list_item(excel.Row(4, "Expected text"), excel.Row(4, "Expected text _1"));
		Assert.assertEquals(itemname, excel.Row(4, "Expected text _1"));
		AssertEquels(itemname, excel.Row(4, "Expected text _1"));
		
		report.create_info("Navigate to " + itemname);

		stepEnd();

		Titles title = new Titles();
		stepStart(excel.Row(5, "Steps"));
		report.create_info(excel.Row(5, "Steps"));

		String title_name = title.click_book(excel.Row(5, "Expected text"));
		System.out.println(title_name);
		// System.out.println(excel.Row(5, "Expected text") + "\r\n" + excel.Row(5,
		// "Expected text _1"));
		// Assert.assertEquals(title_name.replaceAll("\\s",""), excel.Row(5, "Expected
		// text")+"\r\n"+ excel.Row(5, "Expected text _1").replaceAll("\\s",""));

		String acttagname = title.tagname();
		Assert.assertEquals(acttagname, excel.Row(5, "Expected text _1"));
		AssertEquels(acttagname, excel.Row(5, "Expected text _1"));
		
		report.create_info("User navigate to " + title_name + " this book is " + acttagname);

		

	
		String acttext = title.active_premium();
		Assert.assertEquals(acttext, excel.Row(5, "Expected text _2"));
		AssertEquels(acttext, excel.Row(5, "Expected text _2"));

		stepEnd();

	}

	@Test(priority = 3, description = "Verify User able to navigat to my notes")
	public void TC5() {
		Titles title = new Titles();
		stepStart(excel.Row(6, "Steps"));
		report.create_info(excel.Row(6, "Steps"));

		String actnote = title.click_mynotes();
		Assert.assertEquals(actnote, excel.Row(6, "Expected text"));
		AssertEquels(actnote, excel.Row(6, "Expected text"));
		
		report.create_info("User have" + actnote);
		
		stepEnd();
	}

	@Test(priority = 4, description = "Verify user able to navigate to section from TOC")
	public void TC6() throws Exception{
		Codes_Section codessection = new Codes_Section();
		stepStart(excel.Row(7, "Steps"));

		report.create_info(excel.Row(7, "Steps"));
		boolean actchaper = codessection.nav_section(excel.Row(7, "Expected text"));
		Assert.assertTrue(actchaper);
		Assert("Chapter is Displayed", actchaper);
		
		report.create_info("User is navigated to " + excel.Row(7, "Expected text"));
		
		String sub_sectionname = codessection.click_sub_section();
		Assert.assertFalse(sub_sectionname.isEmpty());
		Assert("Chapter is Displayed", sub_sectionname.isEmpty());
       stepEnd();
	}

	@Test(priority = 5, description = "Verify user able to click on apps")
	public void TC7() {
		Codes_Section code = new Codes_Section();
		stepStart(excel.Row(8, "Steps"));
		report.create_info(excel.Row(8, "Steps"));
		code.click_apps();
		stepEnd();
	}
	
	@Test(priority=6,description ="Verify user able to create notes")
	public void TC8(){
		Codes_Section code=new Codes_Section();
		stepStart("");
		code.click_note();
		
		
	}

}
