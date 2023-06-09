package com.Digitalcodes.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.Digitalcodes.utilities.Baseclass;



public class TableOfContent_Page extends Baseclass{
	
	WebDriver driver;
	
	@SuppressWarnings("static-access")
	public TableOfContent_Page() {
		this.driver=super.driver;
		PageFactory.initElements(driver, this);
	}

	//Codes Section
	@FindBy(xpath = "//div[contains(text(),'Code Sections')]")
	private WebElement codessection;
	
	@FindAll({ @FindBy(xpath = "//p[@class='mb-0 py-2 body-2']/a") })
	private List<WebElement> list_section;

	@FindAll({ @FindBy(xpath = "//div[@class=\"row ma-0 row--dense align-center pl-3\"]//a") })
	private List<WebElement> list_subsection;
	
	
	
	//My Notes 
	@FindBy(xpath ="//div[contains(text(),'My Notes')]")
	private WebElement mynotes;
	
	@FindBy(xpath ="//p[@class='caption text-center']")
	 private WebElement nothaveanynotestext;
	
	@FindBy(xpath = "//p[@class=\"mb-0\"]/a")
	private WebElement notesection;
	
	@FindBy(xpath = "//div[@class=\"container\"]//i[@class='v-icon notranslate mdi mdi-menu-down theme--light']")
	private WebElement tagListbox;
	
	@FindBy(xpath = "//div[@role=\"listbox\"]//div[@class=\"v-list-item__content\"]")
	private WebElement tagName;
	
	@FindBy(xpath = "//div[@class='v-simple-checkbox']/i")
	private WebElement tagCheckBox;
	
	//Move Notes 
	
	@FindBy(xpath = "//span[normalize-space()='Move Notes']")
	private WebElement moveNotesButton;
	
	@FindBy(xpath = "//p[@class=\"text-center pt-8\"]")
	private WebElement noNotesMsgInmovenotes;
	
	@FindBy(xpath = "//span[normalize-space()='Close']")
	private WebElement closeButton;
	
	@FindBy(xpath = "//span[normalize-space()='Manage Notes']")
	private WebElement manageNotesButton;
	
	//Lock on Toc
	
	@FindBy(xpath = "//div[@class='white v-card v-sheet v-sheet--outlined theme--dark']")
	private WebElement locklabel;
	
	@FindBy(xpath = "//div[@class='white v-card v-sheet v-sheet--outlined theme--dark']//button")
	private WebElement subscribedNow;
	
	@FindBy(xpath = "//div[@class='primary--text text-lg text-center mb-2']")
    private WebElement titleName;	
	
	@FindBy(xpath = "//i[@class='v-icon notranslate mdi mdi-close theme--light']")
	private WebElement crossicon;
	
	@FindBy(xpath = "//div[@class='white v-card v-sheet v-sheet--outlined theme--dark']//p")
	private WebElement notPremiumtext;
	
	//ASTM chapter Name
	@FindBy(xpath = "//p[@class='mb-0 py-2 body-2 pl-2']/a")
	private WebElement chapterName;
	
	
	public void clickCodesSection() {
		  codessection.click();
	  }
	
	public String navigateToChapter(String section) throws Exception {
		 String chaptername=null;
		
		 
		for (WebElement webElement : list_section) {
			if (getText(webElement).equalsIgnoreCase(section)) {
				chaptername=getText(webElement);
				click(webElement);
           
				break;
				 
			}

		}
		
		
		return chaptername;

		
	}
	
	public String navigateToSubChapter(int indexofsubchapter) throws Exception {
		
		if (isDisplayed(list_subsection.get(indexofsubchapter))) {
			click(list_subsection.get(indexofsubchapter));
			
			return getText(list_subsection.get(indexofsubchapter));
		} 
		else {
			throw new Exception("Sub Chapter is not Present");
		}
	}
	
	public void clickOnMyNotes() {
		 click(mynotes);
	}
	
	public String getTextInMyNotes() throws Exception {
		
    	String notes=null;
    	if (nothaveanynotestext.isDisplayed()) {
    		notes=getText(nothaveanynotestext);
    	}else {
			throw new Exception("No Text is displayed in My Notes");
		}
    	
    	return notes;
    }
	
	public String getChapterNameInMynotes() throws Exception {
		String notes=null;
		if(notesection.isDisplayed()) {
    		notes=getText(notesection);
    		
    	}
		else {
			throw new Exception("Notes Chapter Name is not Displayed");
		}
		return notes;
	}
	
	public String getTagNameInMyNotes() {
		click(tagListbox);
		return getText(tagName);
		
	}
	
	public boolean clickCheckBox() {
		click(tagCheckBox);
		
		return tagCheckBox.isSelected();
	}
	
	public boolean clickMoveNote() {
		click(moveNotesButton);
		boolean text=isDisplayed(noNotesMsgInmovenotes);
		click(closeButton);
		return text;
	}
	public String clickManageNotes() {
		click(manageNotesButton);
		 
		return getTitle();
	}
	
	public String getNotPremiumText() {
		return getText(notPremiumtext);
	}
	
	public boolean locklabelisDisplayed() {
		return isDisplayed(locklabel);
	}
	
	public String clickSubscribedNow() {
		click(subscribedNow);
		String text=getText(titleName);
		click(crossicon);
		return text;
	}
	
	public void clickChapter() {
		click(chapterName);
	}
}
