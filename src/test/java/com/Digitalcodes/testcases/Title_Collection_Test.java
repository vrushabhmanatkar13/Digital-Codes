package com.Digitalcodes.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.Collections_Page;
import com.Digitalcodes.pageobject.MenuMyNotesandBookmark_Page;
import com.Digitalcodes.pageobject.TableOfContent_Page;
import com.Digitalcodes.pageobject.TitleCover_Page;
import com.Digitalcodes.pageobject.TitleLanding_Page;
import com.Digitalcodes.pageobject.TitleSection_Page;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.Load_Excle;
import com.Digitalcodes.utilities.Sparkreport;

public class Title_Collection_Test extends Prerequisites_Teardown{
     TitleCover_Page coverpage;
     TitleLanding_Page landingpage;
     TableOfContent_Page toc;
     TitleSection_Page section;
     Collections_Page collectionpgae;
     
     
	@BeforeClass(alwaysRun = true)
	public void beforeclassCollection() {
		coverpage=new TitleCover_Page();
		toc=new TableOfContent_Page();
		section=new TitleSection_Page();
		collectionpgae=new Collections_Page();
		  landingpage=new TitleLanding_Page();
	}
	
	@DataProvider(name="collection")
	public Object[][] getCollectionData(){
		return excel.getDataFromExcle("Collection");
	}
	
	@DataProvider(name="Premium Complete")
	public Object[][] getPremiumComplete(){
		return excel.getDataFromExcle("Collection", 1);
	}

	
	@AfterMethod(alwaysRun = true)
    public void afterMethod_Collection() {
		    closeWindow();
		   retrunToMainWindow();
	}
	
	
	  @Test(priority = 1,description ="Verify user able to navigate to Collection title",dataProvider = "collection" ,groups = {"Smoke"})
	  public void TC28_verifyCollectionLandingPage(String Section, String subsection, String title, String chapter) throws Exception { 
		  
	  menu.navigateToCollections(Section);
	  Sparkreport.Step("Click menu"); 
	  Sparkreport.Step("Click "+Section); 
	  
	  String heading=coverpage.getHeading();
	  report.create_info("Page Title is "+ getTitle());
	  assertEquals(heading +" Building Codes - ICC Digital Codes", getTitle());
	  
	 
	  coverpage.clickOnTitlesCover(subsection);
	  Sparkreport.Step("Click on "+ subsection);
	  Thread.sleep(6000);
	  collectionpgae.clickIncludeTitles(title);
	  Baseclass.switchToWindow();
	  Sparkreport.Step("Click on "+ title);
	  
	   String titlename = landingpage.getTitleHeading();
	   String activetext=landingpage.getActivepremiumText();
	  
	   report.create_info("Title Name is "+ titlename );
	   report.create_info("Subscription Tag is " + landingpage.getTagName());
	   report.create_info(activetext);
	   
	   assertEquals(title, titlename);
	   assertEquals(landingpage.getTagName(), jsonArrayValue("Premium", "tag"));
	   assertEquals(activetext,jsonArrayValue("Premium", "Access-title"));
	   
	  
		
	  
	  }
	  
	  
	  @Test(priority = 2,description = "Verify user able to navigate to Section on Collection title",dataProvider = "collection",groups = {"Smoke,Regression"})
	  public void TC29_verifySection_collectionTitles(String Section, String subsection, String title, String chapter) throws Exception {
		
		 commanstep.navigateToCollectionTitle(Section, subsection, title);
		  Sparkreport.Step("Click menu"); 
		  Sparkreport.Step("Click "+ Section); 
		
		  Sparkreport.Step("Click on "+ subsection);
		  
		  Thread.sleep(2000);
		 
		  Sparkreport.Step("Click on "+ title);
		  
		 String cahptername= toc.navigateToChapter(chapter);
		 Sparkreport.Step("Click on Chapter "+ cahptername);
		 
		 
		 assertEquals(cahptername, chapter);
		
	  }
	 
	  @Test(priority = 3, description = "Verify user able to create Notes on Collection titles", dataProvider = "Premium Complete",groups = {"Smoke,Regression"})
	  public void TC30_verifyCreateNotes_collectionTitles(String Section, String subsection, String title, String chapter) throws Exception {
		  commanstep.navigateToCollectionTitle(Section, subsection, title);
		  Sparkreport.Step("Click menu"); 
		  Sparkreport.Step("Click "+ Section); 
		
		  Sparkreport.Step("Click on "+ subsection);
		  
		  Thread.sleep(2000);
		  
		  Sparkreport.Step("Click on "+ title);
		  
		 String cahptername= toc.navigateToChapter(chapter);
		 Sparkreport.Step("Click on Chapter "+ cahptername);
		 
			section.doubleClickOnTitle_Section();
			Sparkreport.Step("Double Click on text");

			section.createNote_FromSection(jsonValue("notes-text"));
			Sparkreport.Step("Click Note icon");
			Sparkreport.Step("Enter Decription in textBox");

			section.createNewTag(Section);
			Sparkreport.Step("Click New Tag");
			Sparkreport.Step("Enter TagName");
			Sparkreport.Step("Click Save");

			Thread.sleep(2000);
			section.selectTag(Section);
			Sparkreport.Step("Click Tag DropDown");
			Sparkreport.Step("Select Tag Name");

			section.clickOnSaveButton();
			Sparkreport.Step("Click Save");

			
			report.create_info("Note Created at :- " + section.getChapterName());
			report.create_info("Tag Name :- " + section.getTagName());
			report.create_info("Description is :- " + section.getDescription());
			report.create_info("Note Created by :- " + section.getCreatedBy());

			assertEquals(section.getTagName(), Section);
			assertEquals(section.getDescription(), jsonValue("notes-text"));
			assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

			toc.clickOnMyNotes();
			Sparkreport.Step("Click My Notes");

			report.create_info("Chapter Name at my notes :- " + toc.getChapterNameInMynotes());

			assertEquals(toc.getChapterNameInMynotes(), chapter);

			menu.navigateToPremiumToolFeaturs("My notes and Bookmarks");
			MenuMyNotesandBookmark_Page mynote_bookmark = new MenuMyNotesandBookmark_Page();

			assertEquals(mynote_bookmark.getTitleName(), title);
			assertTrue(mynote_bookmark.verifyChapterName_Decription(chapter, jsonValue("notes-text")));
			assertTrue(mynote_bookmark.verifyDetails(Login_Test.NAME, Section));

			mynote_bookmark.removeNotes_Bookmark();
			
		  
	  }
	
	  @Test(priority = 4, description = "Verify user able to create Bookmark on Collection titles", dataProvider = "Premium Complete",groups = {"Smoke,Regression"})
	  public void TC31_verifyCreateBookmark_collectionTitles(String Section, String subsection, String title, String chapter) throws Exception {
		
		  commanstep.navigateToCollectionTitle(Section, subsection, title);
		  Sparkreport.Step("Click menu"); 
		  Sparkreport.Step("Click "+ Section); 
		 
		  Sparkreport.Step("Click on "+ subsection);
		
		  Thread.sleep(2000);
		  
		  Sparkreport.Step("Click on "+ title);
		  
		 String cahptername= toc.navigateToChapter(chapter);
		 Sparkreport.Step("Click on Chapter "+ cahptername);
		 

			section.clickOnApps(section.getSessionID_FromChapter());
			Sparkreport.Step("Click Apps");

			section.createBookamrk_FromSection(jsonValue("bookmark-text"));
			Sparkreport.Step("Click Bookmark");
			Sparkreport.Step("Enter Decription in textBox");

			section.selectTag(Section);
			Sparkreport.Step("Click Tag DropDown");
			Sparkreport.Step("Select Tag Name");
			Thread.sleep(2000);
			section.clickOnSaveButton();
			Sparkreport.Step("Click Save");

			Thread.sleep(4000);

			report.create_info("Tag Name :- " + section.getTagName());
			report.create_info("Note Created at :- " + section.getChapterName());
			report.create_info("Description is :- " + section.getDescription());
			report.create_info("Note Created by :- " + section.getCreatedBy());

			assertEquals(section.getTagName(), Section);
			assertEquals(section.getDescription(), jsonValue("bookmark-text"));
			assertEquals(section.getCreatedBy(), Login_Test.NAME + " (" + Login_Test.EMAIL + ")");

			toc.clickOnMyNotes();
			Sparkreport.Step("Click My Notes");
			report.create_info("Chapter Name at my notes :- " + toc.getChapterNameInMynotes());

			assertEquals(toc.getChapterNameInMynotes(), chapter);

			menu.navigateToPremiumToolFeaturs("My notes and Bookmarks");
			MenuMyNotesandBookmark_Page mynote_bookmark = new MenuMyNotesandBookmark_Page();

			assertEquals(mynote_bookmark.getTitleName(), title);
			assertTrue(mynote_bookmark.verifyChapterName_Decription(chapter, jsonValue("bookmark-text")));
			assertTrue(mynote_bookmark.verifyDetails(Login_Test.NAME, Section));

			mynote_bookmark.removeNotes_Bookmark();
		
	  
	  
	  }
	  
	  @Test(priority = 5, description = "Verify user able to Share section on Collection titles", dataProvider = "Premium Complete",groups = {"Smoke,Regression"})
	  public void TC32_verifyShareSection_CollectionTitles(String Section, String subsection, String title, String chapter) throws Exception {
		
		  commanstep.navigateToCollectionTitle(Section, subsection, title);
		  Sparkreport.Step("Click menu"); 
		  Sparkreport.Step("Click "+ Section); 
		 
		  Sparkreport.Step("Click on "+ subsection);
		
		  Thread.sleep(2000);
		 
		  Sparkreport.Step("Click on "+ title);
		  
		 String cahptername= toc.navigateToChapter(chapter);
		 Sparkreport.Step("Click on Chapter "+ cahptername);
		 
			section.clickOnApps(section.getSessionID_FromChapter());
			Sparkreport.Step("Click Apps");
			
			String message=section.shareSection(Login_Test.EMAIL);
			Sparkreport.Step("Click Share");
			Sparkreport.Step("Enter email");
			Sparkreport.Step("Click AddMore");
			Sparkreport.Step("Click Remove");
			Sparkreport.Step("Click Share Button");
			Sparkreport.Step("Click Close");
			report.create_info("Successful message :- " + message);
			assertEquals(message, jsonValue("share-successful"));
			
	  }
	  

	  @Test(priority = 6, description = "Verify user able to Print section on Collection titles", dataProvider = "Premium Complete",groups = {"Smoke,Regression"})
	  public void TC33_verifyPrintSection_CollectionTitles(String Section, String subsection, String title, String chapter) throws Exception {
		
		  commanstep.navigateToCollectionTitle(Section, subsection, title);
		  Sparkreport.Step("Click menu"); 
		  Sparkreport.Step("Click "+ Section); 
		 
		  Sparkreport.Step("Click on "+ subsection);
		
		  Thread.sleep(2000);
		 
		  Sparkreport.Step("Click on "+ title);
		  
		 String cahptername= toc.navigateToChapter(chapter);
		 Sparkreport.Step("Click on Chapter "+ cahptername);
		
			section.clickOnApps(section.getSessionID_FromChapter());
			Sparkreport.Step("Click Apps");
		     section.clickOnPrintIcon();
		 	boolean pdfprint=Baseclass.verifyNewWindow();
		 	Baseclass.closeWindow();
		 	Baseclass.retrunToMainWindow();
		 	Baseclass.switchToWindow();
		 	Baseclass.closeWindow();
		 	Baseclass.retrunToMainWindow();
			Sparkreport.Step("Click Print");
			assertTrue(pdfprint);
	  }
	  
	  
	
	
	
}