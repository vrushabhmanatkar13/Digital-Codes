package com.Digitalcodes.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.utilities.Baseclass;

public class MenuMyNotesandBookmark_Page extends Baseclass {

	WebDriver driver;

	@SuppressWarnings("static-access")
	public MenuMyNotesandBookmark_Page() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='accent--text']")
	private List<WebElement> titleName;

	@FindBy(xpath = "//div[@class='v-card__title']")
	private List<WebElement> chapterName;

	@FindBy(xpath = "//p[@class='mt-2 ml-1']/p")
	private List<WebElement> decriptiontext;

	@FindBy(xpath = "//div[@class=\"d-flex\"]//h5[1]")
	private List<WebElement> createdBytext;
	@FindBy(xpath = "//div[@class=\"d-flex\"]//h5[2]")
	private List<WebElement> projectTagtext;

	@FindBy(xpath = "//div[@class=\"d-flex\"]//h5[3]")
	private List<WebElement> timeStamptext;

	@FindBy(xpath = "//span[contains(text(),'Remove')]")
	private List<WebElement> removeButton;
	@FindBy(xpath = "//div[@class=\"v-card__actions\"]//span[contains(text(),'Remove')]")
	private WebElement removeButton2;

	public String getTitleName(String title) throws Exception {
		String text = null;
		for (WebElement webElement : titleName) {
			if (getText(webElement).equals(title)) {
				text = getText(webElement);
				break;
			} else {
				text = null;
			}

		}
		if (text == null) {
			throw new Exception("this Title Name not showing in My Notes and Bookmark");
		}
		return text;

	}

	public void clickTitleName(String title) {
		for (WebElement webElement : titleName) {
			if (getText(webElement).equals(title)) {
				click(webElement);
				break;
			}
		}

	}

	public boolean verifyChapterName_Decription(String title, String chapname, String decription) throws Exception {
		clickTitleName(title);
		boolean result = false;
		for (int i = 0; i <= chapterName.size(); i++) {
			if (getText(decriptiontext.get(i)).equals(decription)) {
				Baseclass.scrollUptoElement(decriptiontext.get(i));
				if (getText(chapterName.get(i)).equals(chapname) && getText(decriptiontext.get(i)).equals(decription)) {
					result = true;
					break;
				} else {
					throw new Exception("Chapter name " + chapname + " Or Description " + decription
							+ " is Not Matched in My Notes and Bookmark");

				}

			}

		}
		return result;

	}

	public boolean verifyDetails(String chaptername, String createdBy, String projectTag) throws Exception {
		boolean result = false;
		for (int i = 0; i < chapterName.size(); i++) {
			if (getText(chapterName.get(i)).equals(chaptername)) {
				if (getText(createdBytext.get(i)).equals("Created By: " + createdBy)
						&& getText(projectTagtext.get(i)).equals("Project Tag: " + projectTag)
						&& isDisplayed(timeStamptext.get(i))) {
					result = true;
					break;
				}
			}
		}
		if (result == false) {
			throw new Exception(chaptername + " Or " + createdBy + " Or " + projectTag
					+ " any one on this is not correct showing in My Notes and Bookmark");
		}
		return result;

	}

	public void removeNotes_Bookmark(String decription) {
		for (int i = 0; i <= chapterName.size(); i++) {
			if (getText(decriptiontext.get(i)).equals(decription)) {
				click(removeButton.get(i));
				click(removeButton2);
				break;
			}
		}
	}

}
