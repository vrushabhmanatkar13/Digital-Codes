package com.Digitalcodes.testcases;

import com.Digitalcodes.pageobject.Codes_Section;
import com.Digitalcodes.pageobject.Favorite;
import com.Digitalcodes.pageobject.Header;
import com.Digitalcodes.pageobject.Login_page;
import com.Digitalcodes.pageobject.Menu;
import com.Digitalcodes.pageobject.TOC;
import com.Digitalcodes.pageobject.Titles;
import com.Digitalcodes.utilities.AssertStatements;
import com.Digitalcodes.utilities.Baseclass;

import org.testng.Assert;

import org.testng.annotations.Test;

public class TC_Loginpage extends Prerequisites_Teardown {

	
	
	public static String email;
	public static String name;
	
	@Test(priority = 1, description = "User able to Login")

	public void login() throws InterruptedException {
		Login_page login = new Login_page(driver);
		
		
		stepStart("===== Enter Username and Passowrd, Click SignIn button ========");
		
		report.create_info(excel.getCellValue(1, "Steps"));
        
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
		Header header=new Header(driver);
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

	

	@Test(priority = 2, description = "Verify user able to navigate to Group of Title")
	public void navigateToGroupTitles() throws Exception {
		Menu menu = new Menu(driver);
		
		stepStart("==== Click on Menu > CLick on Icodes > Click on 2021 I-codes ========");
		 report.create_info(excel.getCellValue(4, "Steps"));
		 menu.navigateToTitlesCover(excel.getCellValue(4, "Expected text"), excel.getCellValue(4, "Expected text _1"));
		 String itemname = menu.getTitleHeading();
		stepEnd();
		
		report.create_info("Group of Title is " + itemname);
		report.create_info("Page Title is " + driver.getTitle());
		
		AssertStatements.assertEquals(itemname, excel.getCellValue(4, "Expected text _1"));
		AssertStatements.assertEquals(driver.getTitle(), itemname+" Building Codes - ICC Digital Codes");
		Assert.assertEquals(itemname, excel.getCellValue(4, "Expected text _1"));
		Assert.assertEquals(driver.getTitle(), itemname+" Building Codes - ICC Digital Codes");
		
		
	

	}
	
	@Test(priority = 3,description = "Verify User able to Navigate to Title Landing page and verify Tag")
	public void navigateToTitle() throws Exception {
		Titles title = new Titles(driver);
		
		stepStart("==== Navigate to Title landing page ========");
		report.create_info(excel.getCellValue(5, "Steps"));
		
		String titlename = title.clickOnTitlesCover(excel.getCellValue(5, "Expected text"));
		stepEnd();
		stepStart("==== Get Tag Name & Active Premium text ========");
		String tagname = title.getTagName();
		String premiumtext=title.getActivepremiumText();
		stepEnd();
		
		report.create_info("Title Name is " + titlename);
		report.create_info("Tag Name is " + tagname);
		report.create_info("Title is " + premiumtext);
		
		AssertStatements.assertEquals(titlename, excel.getCellValue(5, "Expected text"));
		AssertStatements.assertEquals(tagname, excel.getCellValue(5, "Expected text _1"));
		AssertStatements.assertEquals(premiumtext,  excel.getCellValue(5, "Expected text _2"));
		
		Assert.assertEquals(titlename, excel.getCellValue(5, "Expected text"));
		Assert.assertEquals(tagname, excel.getCellValue(5, "Expected text _1"));
		Assert.assertEquals(premiumtext,  excel.getCellValue(5, "Expected text _2"));
		
	}
	

	@Test(priority = 4, description = "Verify user able to mark favorite and title should be displayed in favorites feature")
	public void markFavoriteTitle() throws InterruptedException {
		Titles title = new Titles(driver);
		stepStart("==== Click Favorites icon =====");
		report.create_info(excel.getCellValue(6, "Steps"));
		
		title.clickOnFavorite();
		Thread.sleep(4000);
		String text=title.getFavoriteText();
		stepEnd();
		
		stepStart("==== Verify Favorite Title is display in favorites ======");
		Menu menu=new Menu(driver);
		menu.navigetToStaticFeaturs("Favorites");
		String titlename=new Favorite(driver).getTitleName();
		stepEnd();
		
		stepStart("==== Close Menu ======");
		menu.clickOnMainMenu();
		menu.closeMainmenu();
		stepEnd();
		
		report.create_info("Text after mark favorite "+ text);
		report.create_info("Title Name is  "+ titlename);
		
		AssertStatements.assertEquals(text, excel.getCellValue(6, "Expected text"));
		AssertStatements.assertEquals(titlename,excel.getCellValue(5, "Expected text") );
		
		Assert.assertEquals(text, excel.getCellValue(6, "Expected text"));
		Assert.assertEquals(titlename,excel.getCellValue(5, "Expected text"));
		

	}
	
	
	@Test(priority = 5,description = "Verify user able to remove favorite title")
	public void removeFavoriteTitle() throws InterruptedException {
		stepStart("===== Remove feavorite of Title ======");
		 
		report.create_info(excel.getCellValue(7, "Steps"));
		
		Titles title=new Titles(driver);
		title.clickOnUnFavorite();
		Thread.sleep(4000);
		String text=title.getFavoriteText();
		stepEnd();
		
		report.create_info("Text after mark unfavorite "+ text);
		AssertStatements.assertEquals(text, excel.getCellValue(7, "Expected text"));
		Assert.assertEquals(text,excel.getCellValue(7, "Expected text"));
	
		
		
	}
	
	
	
	@Test(priority = 6, description = "Verify User able to navigat to my notes From TOC", enabled = true)
	public void navigateToMyNotes_fromTOC() {
		stepStart("========= Click on My Notes present on TOC =========");
		report.create_info(excel.getCellValue(8, "Steps"));
		TOC toc=new TOC(driver);
		
		toc.clickOnMyNotes();
		String notestext=toc.verifyMynotes();
		stepEnd();
		
		AssertStatements.assertEquals(notestext, excel.getCellValue(8, "Expected text"));
		Assert.assertEquals(notestext, excel.getCellValue(8, "Expected text"));
		
	}

	@Test(priority = 7, description = "Verify user able to naviagate Section from TOC",enabled = true)
	public void navigateToSection_fromTOC() throws Exception{
		
		Codes_Section section = new Codes_Section(driver);
		stepStart("======= Navigate to Section of title =========");
		report.create_info(excel.getCellValue(9, "Steps"));
		
		section.clickCodesSection();
		String chapterName=section.navigateToChapter(excel.getCellValue(9, "Expected text"));
		String chapterHeading=section.getChapterName();
       stepEnd();
       AssertStatements.assertEquals(chapterName,excel.getCellValue(9, "Expected text"));
       AssertStatements.assertEquals(chapterHeading, chapterName);
       Assert.assertEquals(chapterName,excel.getCellValue(9, "Expected text"));
       Assert.assertEquals(chapterHeading, chapterName);
       
	}

	@Test(priority = 8, description = "Verify user able to create notes at section",enabled = true)
	public void createNotes_atSection() {
		Codes_Section section = new Codes_Section(driver);
		stepStart("========= Click on Apps =========");
		report.create_info(excel.getCellValue(10, "Steps"));
		
		section.clickOnApps(section.getSessionID_FromChapter());
		stepEnd();
		stepStart("========= Double Click on Text and Create Note =========");
		section.doubleClickOnTitle_Section();
		section.createNote_FromSection(excel.getCellValue(10, "Expected text"));
		stepEnd();
		stepStart("========= Create & Select New Tag and Save =========");
		section.createNewTag(excel.getCellValue(10, "Expected text _1"));
		String tagName=section.selectTag(excel.getCellValue(10, "Expected text _1"));
		section.clickOnSaveButton();
		stepEnd();
		
		stepStart("=========Verify note text, tagname and user information ========");
		System.out.println("Actul Tag name " + tagName);
		
		System.out.println( section.getTagName());
		
		AssertStatements.assertEquals(tagName, section.getTagName());
		AssertStatements.assertEquals(section.getDescription(), excel.getCellValue(10, "Expected text"));
		AssertStatements.assertEquals(section.getCreatedBy(), name+" ("+email+")");
		
		Assert.assertEquals(tagName, section.getTagName());
		Assert.assertEquals(section.getDescription(), excel.getCellValue(10, "Expected text"));
		Assert.assertEquals(section.getCreatedBy(), name+" ("+email+")");
		stepEnd();
		
	}
	
	@Test(priority=6,description ="Verify user able to create notes",enabled = false)
	public void TC8() throws Exception{
		Codes_Section code=new Codes_Section(driver);
		stepStart(excel.getCellValue(9, "Steps"));
		report.create_info(excel.getCellValue(9, "Steps"));
	//	code.Create_note();
	//	code.textbox_note_bookmark(excel.getCellValue(9, "Expected text"),excel.getCellValue(9, "Expected text_1"),excel.getCellValue(9, "Expected text_2"));
		stepEnd();
		
	}

}
