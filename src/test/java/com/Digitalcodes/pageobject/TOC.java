package com.Digitalcodes.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Digitalcodes.utilities.Baseclass;

public class TOC extends Baseclass{
	
	WebDriver driver;
	
	@SuppressWarnings("static-access")
	public TOC() {
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
	
	@FindBy(xpath = "//div[@class='v-list-item theme--light']//div[@class='v-list-item__title']")
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
	
	public void clickCodesSection() {
		  codessection.click();
	  }
	
	public String navigateToChapter(String section) throws Exception {
		 String chaptername=null;
		 Baseclass.wait.until(ExpectedConditions.visibilityOfAllElements(list_section));
		 
		for (WebElement webElement : list_section) {

			if (webElement.getText().equalsIgnoreCase(section)) {
				Thread.sleep(2000);
				chaptername=getText(webElement);
				webElement.click();
           
				break;
				 
			}

		}
		
		
		return chaptername;

		
	}
	
	public String navigateToSubChapter() {

		if (list_subsection.get(0).isDisplayed()) {
			list_subsection.get(0).click();
			
			return list_subsection.get(0).getText();
		} 
		else {
			System.out.println("No subchapter in this chapter");
			return null;
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
}
