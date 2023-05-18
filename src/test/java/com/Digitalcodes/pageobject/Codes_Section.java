package com.Digitalcodes.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;


import com.Digitalcodes.utilities.Baseclass;

public class Codes_Section extends Baseclass {
	WebDriver driver;

	@SuppressWarnings("static-access")
	public Codes_Section() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Chapter Section

	@FindBy(xpath = "//div[@class=\"py-0 col\"]/p[@class=\"mb-0 caption\"]")
	private WebElement chapterHeading;

	@FindBy(xpath = "//section[@class=\"chapter\"]")
	private WebElement chapterName;

	@FindAll({ @FindBy(xpath = "//section[contains(@class,\"level1\")]") })
	private List<WebElement> subChapterName;

	@FindAll({ @FindBy(xpath = "//i[contains(@id,'section-action-icon-')]") })
	private List<WebElement> apps;

	@FindBy(xpath = "//h1[@class=\"chapter\"]")
	private WebElement titleofChapter;

	@FindBy(xpath = "//h1[@class=\"level1\"]")
	private WebElement titleofSubChapter;

	
	//Notes 
	
	@FindBy(xpath = "//i[text()=' border_color']")
	private WebElement note;

	@FindBy(xpath = "//div[@class=\"ql-editor ql-blank\"]")
	private WebElement textcontainer;

	@FindBy(xpath = "(//span[contains(text(),'Save')])[1]")
	private WebElement saveButton_Notes;
	
	//Bookmarks
	
	@FindBy(xpath = "//i[@title=\"Bookmark\"]")
	private WebElement bookmarkicon;

	//Tag 
	
	@FindBy(xpath = "//span[contains(text(),'New Tag')]")
	private WebElement newTagButton;

	@FindBy(xpath = "//input[@placeholder=\"Tag Name\"]")
	private WebElement tagNameInput;

	@FindBy(xpath = "(//span[contains(text(),'Save')])[2]")
	private WebElement saveButton_Tag;

	@FindBy(xpath = "//div[@class=\"col\"]//div[@aria-haspopup=\"listbox\"]")
	private WebElement tagListBox;

	@FindAll({ @FindBy(xpath = "//div[contains(@id,\"list-item-\")]") })
	private List<WebElement> listTagName;

	//My Notes at Chapter after create notes 
	
	@FindBy(xpath = "//div[@class='caption cl description']")
	private WebElement descriptionText;

	@FindBy(xpath = "//h5[@class=\"font-weight-regular left\"][1]")
	private WebElement getTagName;

	@FindBy(xpath = "//h5[@class=\"font-weight-regular left\"][2]")
	WebElement getCreatedBy;
	
	
	//Share section
	
	@FindBy(xpath = "//i[@title=\"Share\"]")
	private WebElement share;
	
	@FindBy(xpath = "//input[@data-qa=\"share-section-modal-email-0\"]")
	private WebElement emailInput;
	
	@FindBy(xpath = "//span[contains(text(),'Add More')]")
	private WebElement addmore;
	
	@FindBy(xpath = "//input[@data-qa=\"share-section-modal-email-1\"]")
	private WebElement newemailInput;
	@FindBy(xpath = "//span[contains(text(),'Remove')]")
	private WebElement remove;
	
	@FindBy(xpath = "//button[@data-qa=\"share-section-modal-share\"]")
	private WebElement shareButton;
	
	@FindBy(xpath = "//div[@class=\"v-alert v-sheet theme--dark success\"]//div[@class=\"v-alert__content\"]")
	private WebElement sharesucessfullyMsg;
	
	@FindBy(xpath = "//span[contains(text(),'Close')]")
	private WebElement closeButton;

	//Print section
	
	@FindBy(xpath = "//i[@title=\"Print\"]")
	private WebElement print;
	@FindBy(xpath = "//embed")
	private WebElement pdf;
	
	//Share note
	@FindBy(xpath = "//i[text()='share']")
	private WebElement shareIcon;
	
	@FindBy(xpath = "//label[text()='Enter email address']/following::input")
	private WebElement emailinput;
	
	@FindBy(xpath = "//span[contains(text(),'Submit')]")
	private WebElement submitButton;
	
	//Edit note
	
	@FindBy(xpath = "//i[text()='edit']")
	private WebElement editIcon;
	
	@FindBy(xpath = "//div[@class=\"ql-editor\"]")
	private WebElement filledtextcontainer;
	
	//Delete Note
	
	@FindBy(xpath = "//i[text()='delete']")
	private WebElement deleteIcon;
	
	@FindBy(xpath = "//div[@class=\"v-dialog v-dialog--active\"]//button[@class=\"v-btn v-btn--contained theme--light v-size--default error\"]")
	private WebElement removeButton;
	
	
	public String getChapterName() {
		return getText(chapterHeading);
	}

	public String getSessionID_FromChapter() {
		return chapterName.getAttribute("section-number");

	}

	public String getSessionID_FromSubChapter() {
		return subChapterName.get(0).getAttribute("section-number");
	}

	public void clickOnApps(String session) {
		for (WebElement webElement : apps) {
			if (webElement.getAttribute("id").equals("section-action-icon-" + session)) {
				click(webElement);
				break;
			}

		}

	}

	public void doubleClickOnTitle_Section() {
		Baseclass.action.doubleClick(titleofChapter).build().perform();
	}

	public void doubleClickOnTitle_Subsection() {
		Baseclass.action.doubleClick(titleofSubChapter).build().perform();
	}

	public void createNote_FromSection(String notetext) {
		click(note);
		textcontainer.clear();
		sendKeys(textcontainer, notetext);

	}
	
	public void createBookamrk_FromSection(String bookamrk) {
		click(bookmarkicon);
		textcontainer.clear();
		sendKeys(textcontainer, bookamrk);
	}

	public void clickOnSaveButton() {
		click(saveButton_Notes);
	}

	public void createNewTag(String tagname) {
		click(newTagButton);
		tagNameInput.clear();
		sendKeys(tagNameInput, tagname);

		click(saveButton_Tag);
		

	}

	public String selectTag(String tagname) {
		click(tagListBox);

		for (WebElement webElement : listTagName) {
			if (webElement.getText().equals(tagname)) {
				click(webElement);
				break;
			}

		}
		return getText(tagListBox);
	}

	public String getDescription() {
		return getText(descriptionText);
	}

	public String getTagName() {
		return getText(getTagName).substring(13);
	}

	public String getCreatedBy() {
		return getText(getCreatedBy).substring(12);

	}
	
	
	public String shareSection(String email) {
		click(share);
		sendKeys(emailInput, email);
		click(addmore);
		if(newemailInput.isDisplayed()) {
			click(remove);
		}		
		click(shareButton);
		String message=getText(sharesucessfullyMsg);
		click(closeButton);
		
		return message;
	}
	
	public boolean printSection() {
		
		
		click(print);
		
		try {
			Baseclass.switchToWindow();
			Thread.sleep(5000);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean viewPdf=isDisplayed(pdf);
		 Baseclass.closeWindow();
		 Baseclass.retrunToMainWindow();
		 return viewPdf;
	}
	
	
	
	public String shareNotes_Bookmark(String email) {
		click(shareIcon);
		sendKeys(emailinput, email);
		click(submitButton);
		String successfulMsg=getText(sharesucessfullyMsg);
		click(closeButton);
		return successfulMsg;
	}
	
	public void editNotes_Bookmark(String text) throws InterruptedException {
		click(editIcon);
		
		Thread.sleep(5000);
		filledtextcontainer.clear();
		
		sendKeys(textcontainer, text);
		click(saveButton_Notes);
		
	}
	
	
	public void deleteNotes__Bookmark() throws InterruptedException {
		click(deleteIcon);
		click(removeButton);
		Thread.sleep(3000);
		
	}

}
