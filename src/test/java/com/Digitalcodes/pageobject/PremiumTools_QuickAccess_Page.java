package com.Digitalcodes.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.utilities.Baseclass;

public class PremiumTools_QuickAccess_Page extends Baseclass {

	WebDriver driver;

	public PremiumTools_QuickAccess_Page() {
		this.driver = Baseclass.driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='col-sm-6 col-12']//input[@placeholder='Start typing to Search']")
	private WebElement inputTitle;

	@FindBy(xpath = "//div[@class='col-lg-6 col']//input[@placeholder='Start typing to Search']")
	private WebElement inputSection;

	@FindBy(xpath = "//div[contains(@id,'list-item-')]//p[@class=\"mb-0\"]")
	private List<WebElement> titleNames;

	@FindBy(xpath = "//div[@aria-haspopup='listbox']//div[@class='v-select__selections']")
	private WebElement versionarrow;

	@FindBy(xpath = "//div[@class='v-menu__content theme--light menuable__content__active']//div[@role='option']")
	private List<WebElement> versions;

	@FindBy(xpath = "//div[@class='v-menu__content theme--light menuable__content__active v-autocomplete__content']//div[@role='option']")
	private List<WebElement> sectionNames;

	@FindBy(xpath = "//h1[@class='level2']")
	private WebElement getSectionName;

	@FindBy(xpath = "//button[@class='mt-5 v-btn v-btn--contained theme--light v-size--x-large primary']")
	private WebElement jumptoSection;

	@FindBy(xpath = "(//h1[@class='level2'])[1]")
	private WebElement sectionNameOnchapterpage;

	@FindBy(xpath = "//span[@class='level2_title']")
	private WebElement sectionname;

	@FindBy(xpath = "(//h1[@class='level2']/span[@class='section_number'])[1]")
	private WebElement sectionNumber;

	@FindBy(xpath = "//button[text()='input']")
	private WebElement inputIcon;

	@FindBy(xpath = "//button[text()='file_copy']")
	private WebElement copyIcon;

	@FindBy(xpath = "//div[@class='v-snack__wrapper v-sheet theme--dark success']/div[@class='v-snack__content']")
	private WebElement copiedText;

	@FindBy(xpath = "//button[text()='local_printshop']")
	private WebElement printIcon;

	@FindBy(xpath = "//embed[@type=\"application/pdf\"]")
	private WebElement pdf;

	@FindBy(xpath = "//button[text()='link']")
	private WebElement shareIcon;

	@FindBy(xpath = "//input[@data-qa=\"share-section-modal-email-0\"]")
	private WebElement emailInput;

	@FindBy(xpath = "//button[@data-qa=\"share-section-modal-share\"]")
	private WebElement shareButton;

	@FindBy(xpath = "//div[@class=\"v-alert v-sheet theme--dark success\"]//div[@class=\"v-alert__content\"]")
	private WebElement sharesucessfullyMsg;

	@FindBy(xpath = "//button[text()='bookmark_border']")
	private WebElement bookmarkIcon;

	@FindBy(xpath = "//div[@class=\"ql-editor ql-blank\"]")
	private WebElement textcontainer;

	@FindBy(xpath = "(//span[contains(text(),'Save')])[1]")
	private WebElement saveButton_Notes;

	// Recently Accessed Sections

	@FindAll(@FindBy(xpath = "//div[@class='col-sm-6 col-12']//h3"))
	private List<WebElement> recentlyAccessed;

	@FindAll(@FindBy(xpath = "//div[@class='col-sm-6 col-12']//p"))
	private List<WebElement> recentlyAcceddedtitlename;

	public String enterTextinStep1_SelectTitle(String step1, String title) throws Exception {
		String text = null;
		sendKeys(inputTitle, step1);
		for (WebElement webElement : titleNames) {
			if (getText(webElement).equalsIgnoreCase(title)) {
				text = getText(webElement);
				action.click(webElement).build().perform();

				break;
			}

		}
		if (text == null) {
			throw new Exception(title + " this title not available in results");
		}
		return text;

	}

	public String selectVersion(String version) throws Exception {
		String text = null;
		action.click(versionarrow).build().perform();
		Thread.sleep(2000);
		for (WebElement webElement : versions) {
			if (getText(webElement).equalsIgnoreCase("(" + version + ")")) {
				text = getText(webElement);
				click(webElement);
				break;
			}
		}
		if (text == null) {
			throw new Exception(version + " this Verison not Availabe in DropDown");
		}

		return text;
	}

	public String enterTextinStep2_SelectSection(Object step2, String section) throws Exception {
		String text = null;
		sendKeys(inputSection, (String) step2);

		for (WebElement webElement : sectionNames) {
			if (getText(webElement).equalsIgnoreCase(section)) {
				text = getText(webElement);
				action.click(webElement).build().perform();
				break;
			}
		}
		if (text == null) {
			throw new Exception(section + " this Section Not Availabel in List");
		}

		return text;

	}

	public String getSectionName() {
		return getText(getSectionName);
	}

	public void clickJumpToSection() {
		click(jumptoSection);
	}

	public String getSectionNameOnChapterPage() throws Exception {
		List<WebElement> section = sectionname.findElements(By.xpath("//span"));
		String sectiontext = getText(sectionname).replaceAll(section.get(0).getText(), "");

		String text = getText(sectionNumber) + sectiontext;
		return text;
	}

	public void clickInputIcon() {
		click(inputIcon);
	}

	public String copyThisSection() {
		click(copyIcon);
		return getText(copiedText);
	}

	public boolean printThisSection() throws Exception {
		Baseclass.getParentWindow();
		click(printIcon);
		Baseclass.switchToWindow();
		boolean Actpdf = isDisplayed(pdf);
		Baseclass.closeWindow();
		Baseclass.retrunToMainWindow();
		return Actpdf;

	}

	public String shareThisSection(String email) {
		click(shareIcon);
		sendKeys(emailInput, email);
		click(shareButton);
		String text = getText(sharesucessfullyMsg);

		return text;

	}

	public void bookmarkThisSection(String text) {

		click(bookmarkIcon);
		sendKeys(textcontainer, text);
		click(saveButton_Notes);
	}

	public String getRecentlyAccessedSection(String Section) {
		String text = null;
		for (WebElement webElement : recentlyAccessed) {

			if (webElement.getText().replaceAll(" ", "").equalsIgnoreCase(Section.replaceAll(" ", ""))) {
				text = webElement.getText().replaceAll(" ", "");
				break;
			}

		}
		return text;
	}

	public String getRecentlyAccessedTitleName(String title) {
		String text = null;
		for (WebElement webElement : recentlyAcceddedtitlename) {
			if (webElement.getText().equalsIgnoreCase(title)) {
				text = webElement.getText();
				break;
			}
		}
		return text;
	}

	public void clickRecentlyAccessedSection(String Section) {

		for (WebElement webElement : recentlyAccessed) {

			if (webElement.getText().replaceAll(" ", "").equalsIgnoreCase(Section.replaceAll(" ", ""))) {
				click(webElement);
				break;
			}

		}

	}

}
