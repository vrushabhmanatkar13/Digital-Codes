package com.Digitalcodes.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Digitalcodes.utilities.Baseclass;

public class TitleSection_Page extends Baseclass {
	WebDriver driver;

	@SuppressWarnings("static-access")
	public TitleSection_Page() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h4[@class='primary--text']")
	private WebElement titlename;

	// Chapter Section

	@FindBy(xpath = "//div[@id='sticky-chapter-info']//div[@class='row row--dense']//div[@class='py-0 col']")
	private WebElement chapterHeading;

	@FindBy(xpath = "//section[@class=\"chapter\"]")
	private WebElement chapterName;

	@FindAll({ @FindBy(xpath = "//section[@class='level1']") })
	private List<WebElement> subChapterName;

	@FindAll({ @FindBy(xpath = "//i[contains(@id,'section-action-icon-')]") })
	private List<WebElement> apps;

	@FindBy(xpath = "//h1[@class=\"chapter\"]")
	private WebElement titleofChapter;

	@FindBy(xpath = "(//h1[@class=\"level1\"])[1]")
	private WebElement titleofSubChapter;

	@FindBy(xpath = "//h4[@class=\"primary--text\"]")
	private WebElement titleHeading;

	// Note popup
	@FindBy(xpath = "//div[@class='v-dialog__content v-dialog__content--active']//div[@class='v-card__title primary white--text headline']")
	private WebElement notes_bookmarkpopup;
	// Notes

	@FindBy(xpath = "//i[text()=' border_color']")
	private WebElement note;

	@FindBy(xpath = "//div[@class='ql-editor ql-blank']")
	private WebElement textcontainer;

	@FindBy(xpath = "(//span[text()='Save'])[1]")
	private WebElement saveButton_Notes;

	// Subsection icons
	@FindBy(xpath = "//div[@class='section-action-buttons disable-selection']//i[@title='Select text to enable the highlight and note creation']")
	private List<WebElement> noteicon;

	@FindBy(xpath = "//i[@title='Bookmark']")
	private List<WebElement> Bookmarkicon;

	@FindBy(xpath = "//i[@title='Share']")
	private List<WebElement> shareicon;

	@FindBy(xpath = "//i[@title='Print']")
	private List<WebElement> printIcon;

	@FindBy(xpath = "//a[@class=\"print-one-section\"]")
	private WebElement printthissection;

	@FindBy(xpath = "//a[@class=\"print-all-sections\"]")
	private WebElement printAllsection;

	// Child Subsection

	@FindBy(xpath = "//section[@class='level2']")
	private List<WebElement> childSubSection;

	@FindBy(xpath = "(//h1[@class=\"level2\"]/span)[1]")
	private WebElement textchildSection;

	@FindBy(xpath = "(//h1[@class=\"level2\"])[1]")
	private WebElement childSectionName;

	// Bookmarks

	@FindBy(xpath = "//i[@title=\"Bookmark\"]")
	private WebElement bookmarkicon;

	// Tag

	@FindBy(xpath = "//span[contains(text(),'New Tag')]")
	private WebElement newTagButton;

	@FindBy(xpath = "//input[@placeholder=\"Tag Name\"]")
	private WebElement tagNameInput;

	@FindBy(xpath = "(//span[contains(text(),'Save')])[2]")
	private WebElement saveButton_Tag;

	@FindBy(xpath = "//div[@class=\"col\"]//div[@aria-haspopup=\"listbox\"]")
	private WebElement tagListBox;

	@FindAll({ @FindBy(xpath = "//div[@role='option']") })
	private List<WebElement> listTagName;

	// My Notes at Chapter after create notes

	@FindBy(xpath = "//div[@class='caption cl description']")
	private WebElement descriptionText;

	@FindBy(xpath = "//h5[@class=\"font-weight-regular left\"][1]")
	private WebElement getTagName;

	@FindBy(xpath = "//h5[@class=\"font-weight-regular left\"][2]")
	WebElement getCreatedBy;

	// Share section

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

	// Print section

	@FindBy(xpath = "//i[@title=\"Print\"]")
	private WebElement print;

	@FindBy(xpath = "//embed[@type=\"application/pdf\"]")
	private WebElement pdf;

	// Share note
	@FindBy(xpath = "//i[text()='share']")
	private WebElement shareIcon;

	@FindBy(xpath = "//label[text()='Enter email address']/following::input")
	private WebElement emailinput;

	@FindBy(xpath = "//span[contains(text(),'Submit')]")
	private WebElement submitButton;

	// Edit note

	@FindBy(xpath = "//i[text()='edit']")
	private WebElement editIcon;

	@FindBy(xpath = "//div[@class=\"ql-editor\"]")
	private WebElement filledtextcontainer;

	// Delete Note

	@FindBy(xpath = "//i[text()='delete']")
	private WebElement deleteIcon;

	@FindBy(xpath = "//div[@class=\"v-dialog v-dialog--active\"]//button[@class=\"v-btn v-btn--contained theme--light v-size--default error\"]")
	private WebElement removeButton;

	// Links in chapter
	@FindBy(xpath = "//section[@section-number=\"24199940\"]")
	private WebElement section;

	@FindAll({ @FindBy(xpath = "(//a[@class=\"book_reference iccpub pubname\"])[1]"),
			@FindBy(xpath = "(//a[@class=\"section_reference\"])[1]") })
	private WebElement titleLink;

	@FindBy(xpath = "(//a[@class=\"chapter_reference\"])[1]")
	private WebElement chapterlink;

	// Notes-Bookmark View

	@FindBy(xpath = "//div[@class=\"v-card-item text note-bookmark-list-item\"]")
	private WebElement bookmark_notes;

	/// Commentary Page

	@FindBy(xpath = "//div[@class='py-0 col col-auto']/button")
	private WebElement hideButton;

	@FindBy(xpath = "(//p[@class='chapter_subtitle'])[1]")
	private WebElement commentry_subtitle;

	// ASTM Link
	@FindBy(xpath = "//a[contains(text(),'ASTM ')]")
	private List<WebElement> ASTMlink;

	@FindBy(xpath = "//iframe[@id='pdfViewer']")
	private WebElement pdfviewer;

	public String getChapterName() {
		return getText(chapterHeading);
	}

	public String getSessionID_FromChapter() {
		return chapterName.getAttribute("section-number");

	}

	public String getSessionID_FromSubChapter() {
		return subChapterName.get(0).getAttribute("section-number");
	}

	public String getSectionID_FromChildSection() {
		return childSubSection.get(0).getAttribute("section-number");
	}

	public String getChildSubSectionName() {
		return childSectionName.getText();
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

	public void doubleClikcOn_ChildSection() {
		Baseclass.action.doubleClick(textchildSection).build().perform();
	}

	public void clickNotesIcon() {
		click(note);
	}

	public void createNote_FromSection(String notetext) throws Exception {
		if (isDisplayed(notes_bookmarkpopup)) {
			sendKeys(textcontainer, notetext);
		} else {
			throw new Exception("Notes/Bookmark Popup is not displayed");
		}

	}

	public void clickBookmarkIcon() {
		click(bookmarkicon);
	}

	public void createBookamrk_FromSection(String bookamrk) throws Exception {

		if (isDisplayed(notes_bookmarkpopup)) {
			textcontainer.clear();
			sendKeys(textcontainer, bookamrk);
		} else {
			throw new Exception("Notes/Bookmark Popup is not displayed");
		}

	}

	public void createNote_FromSubSection(String sectionId, String notetext) throws Exception {

		for (WebElement webElement : noteicon) {
			if (webElement.getAttribute("id").equals(sectionId + "_note")) {
				click(webElement);
				if (isDisplayed(notes_bookmarkpopup)) {
					Thread.sleep(1000);
					textcontainer.clear();
					sendKeys(textcontainer, notetext);
					break;
				} else {
					throw new Exception("Notes/Bookmark Popup is not displayed");
				}

			}
		}

	}

	public void creteBookmark_FromSubSection(String sectionId, String bookmarktext) throws Exception {

		for (WebElement webElement : Bookmarkicon) {
			if (webElement.getAttribute("id").equals(sectionId + "_bookmark")) {
				click(webElement);
				if (isDisplayed(notes_bookmarkpopup)) {

					sendKeys(textcontainer, bookmarktext);
					break;
				} else {
					throw new Exception("Notes/Bookmark Popup is not displayed");
				}

			}
		}

	}

	public void creatNote_FromChildSubSection(String sectionId, String notetext) throws Exception {
		for (WebElement webElement : noteicon) {
			if (webElement.getAttribute("id").equals(sectionId + "_note")) {
				click(webElement);
				if (isDisplayed(notes_bookmarkpopup)) {

					textcontainer.clear();
					sendKeys(textcontainer, notetext);
					break;
				} else {
					throw new Exception("Notes/Bookmark Popup is not displayed");
				}

			}
		}

	}

	public void createBookmark_FromChildSubSection(String sectionId, String bookmarktext) throws Exception {

		for (WebElement webElement : Bookmarkicon) {
			if (webElement.getAttribute("id").equals(sectionId + "_bookmark")) {
				click(webElement);
				if (isDisplayed(notes_bookmarkpopup)) {
					textcontainer.clear();
					sendKeys(textcontainer, bookmarktext);
					break;
				} else {
					throw new Exception("Notes/Bookmark Popup is not displayed");
				}

			}

		}
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

	public String selectTag(String tagname) throws InterruptedException {
		click(tagListBox);

		for (WebElement webElement : listTagName) {
			if (getText(webElement).equalsIgnoreCase(tagname)) {
				Thread.sleep(500);
				click(webElement);
				break;
			}
		}

		return getText(tagListBox);
	}

	public boolean Notes_BookmarkisDisplayed() {
		try {
			return bookmark_notes.isDisplayed();
		} catch (Exception e) {
			return false;
		}

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

	public void clickShareIcon() {
		click(share);
	}

	public String shareSection(String email) {

		sendKeys(emailInput, email);
		click(addmore);
		if (newemailInput.isDisplayed()) {

			click(remove);
		}
		click(shareButton);
		String message = getText(sharesucessfullyMsg);
		click(closeButton);

		return message;
	}

	public String shareSubSection(String sectionId, String email) {
		String message = null;
		for (WebElement webElement : shareicon) {

			if (webElement.getAttribute("id").equals(sectionId + "_share")) {
				click(webElement);
				sendKeys(emailInput, email);
				click(addmore);
				if (newemailInput.isDisplayed()) {
					click(remove);
				}
				click(shareButton);
				message = getText(sharesucessfullyMsg);
				click(closeButton);

			}

		}
		return message;
	}

	public String shareChildSubSection(String sectionId, String email) {
		String message = null;
		for (WebElement webElement : shareicon) {

			if (webElement.getAttribute("id").equals(sectionId + "_share")) {
				click(webElement);
				sendKeys(emailInput, email);
				click(addmore);
				if (newemailInput.isDisplayed()) {
					click(remove);
				}
				click(shareButton);
				message = getText(sharesucessfullyMsg);
				click(closeButton);

			}

		}
		return message;
	}

	public boolean printSection() {
		Baseclass.getParentWindow();
		boolean viewPdf = false;
		click(print);

		try {
			Baseclass.switchToWindow();
			viewPdf = isDisplayed(pdf);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Baseclass.closeWindow();
		return viewPdf;
	}

	public void clickOnPrintIcon() {
		click(print);
	}

	public boolean printSection_NewWindow() {

		return isDisplayed(pdf);
	}

	public boolean printThisSubSection(String sectionid) throws Exception {
		boolean viewPdf = false;
		Baseclass.getParentWindow();
		for (WebElement webElement : printIcon) {
			if (webElement.getAttribute("id").equals(sectionid + "_print")) {
				click(webElement);
				click(printthissection);
				Baseclass.switchToWindow();

				viewPdf = isDisplayed(pdf);
				Baseclass.closeWindow();
				Baseclass.retrunToMainWindow();

			}

		}
		return viewPdf;
	}

	public boolean printSubSection(String sectionid) throws Exception {
		boolean viewPdf = false;
		Baseclass.getParentWindow();
		for (WebElement webElement : printIcon) {
			if (webElement.getAttribute("id").equals(sectionid + "_print")) {
				click(webElement);
				click(printAllsection);
				Baseclass.switchToWindow();

				viewPdf = isDisplayed(pdf);
				Baseclass.closeWindow();
				Baseclass.retrunToMainWindow();

			}

		}
		return viewPdf;
	}

	public boolean printChildSubSection(String sectionid) throws Exception {
		boolean viewPdf = false;
		Baseclass.getParentWindow();
		for (WebElement webElement : printIcon) {
			if (webElement.getAttribute("id").equals(sectionid + "_print")) {
				click(webElement);
				Baseclass.switchToWindow();

				viewPdf = isDisplayed(pdf);
				Baseclass.closeWindow();
				Baseclass.retrunToMainWindow();

			}
		}
		return viewPdf;
	}

	public String shareNotes_Bookmark(String email) {
		click(shareIcon);
		sendKeys(emailinput, email);
		click(submitButton);
		String successfulMsg = getText(sharesucessfullyMsg);
		click(closeButton);
		return successfulMsg;
	}

	public void editNotes_Bookmark(String text) throws Exception {
		click(editIcon);
		if (isDisplayed(notes_bookmarkpopup)) {
			filledtextcontainer.clear();
			sendKeys(textcontainer, text);
			click(saveButton_Notes);
		} else {
			throw new Exception("Notes/Bookmark Popup is not displayed");
		}

	}

	public void deleteNotes__Bookmark() throws InterruptedException {
		click(deleteIcon);
		click(removeButton);

	}

	public String getTitlelinkText() {
		return getText(titleLink);
	}

	public String getChapterlinkText() {
		return getText(chapterlink);
	}

	public boolean clickOnTitleLink() {
		Baseclass.action.moveToElement(titleLink);

		Baseclass.action.click(titleLink).build().perform();

		return new TitleLanding_Page().getTitleNameisDisplayed();
	}

	public boolean clickOnChapterLink() {

		Baseclass.action.moveToElement(chapterlink).build().perform();

		Baseclass.action.click(chapterlink).build().perform();
		return isDisplayed(titleofChapter);
	}

	// Hide Commentary
	public boolean subtitleIsDisplayed() {
		return commentry_subtitle.isDisplayed();
	}

	public String clickHideButton() {
		wait.until(ExpectedConditions.visibilityOf(commentry_subtitle));
		click(hideButton);
		return getText(hideButton);
	}

	public String clickShowButton() throws InterruptedException {
		click(hideButton);
		Thread.sleep(500);
		return getText(hideButton);
	}

	// ASTM LINK

	public String click_getLinkText(String linktext) throws Exception {
		String text = null;
		for (WebElement webElement : ASTMlink) {
			if (getText(webElement).equalsIgnoreCase(linktext)) {
				text = getText(webElement);
				try {
					action.click(webElement).build().perform();
				} catch (Exception e) {
					action.click(webElement).build().perform();
				}
				break;
			}
		}
		if (text == null) {
			throw new Exception(linktext + " this text link is not present in this chapter");
		}

		return text;
	}

	public String getTitleName() {
		return getText(titlename);
	}

	public boolean pdfViewer() {
		return isDisplayed(pdfviewer);
	}

}
