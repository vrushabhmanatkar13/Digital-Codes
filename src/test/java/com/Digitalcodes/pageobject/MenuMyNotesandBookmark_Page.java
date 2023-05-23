package com.Digitalcodes.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Digitalcodes.utilities.Baseclass;


public class MenuMyNotesandBookmark_Page extends Baseclass{
 
	WebDriver driver;
	
	@SuppressWarnings("static-access")
	public MenuMyNotesandBookmark_Page() {
		this.driver=super.driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class=\"accent--text\"]")
	private WebElement titleName;
	
	@FindBy(xpath = "//div[@class=\"v-card__title\"]")
    private WebElement chapterName;
	
	@FindBy(xpath = "//p[@class=\"mt-2 ml-1\"]")
	private WebElement decriptiontext;
	
	@FindBy(xpath = "//div[@class=\"d-flex\"]//h5[1]")
	private WebElement createdBytext;
	@FindBy(xpath = "//div[@class=\"d-flex\"]//h5[2]")
	private WebElement projectTagtext;
	@FindBy(xpath = "//div[@class=\"d-flex\"]//h5[3]")
	private WebElement timeStamptext;
	
	@FindBy(xpath = "//span[contains(text(),'Remove')]")
	private WebElement removeButton;
	@FindBy(xpath = "//div[@class=\"v-card__actions\"]//span[contains(text(),'Remove')]")
	private WebElement removeButton2;
	
public String getTitleName() {
	 return getText(titleName);
	}

public void clickTitleName() {
	click(titleName);
}
	

public boolean verifyChapterName_Decription(String chapname, String decription) {
	     click(titleName);
	     
	     Baseclass.scrollUptoElement(chapterName);
	    if (chapterName.getText().equals(chapname) && decriptiontext.getText().equals(decription)) {
	    	 
	    	return true;
	    	
	    } else {
	    	return false;
	    }
	     
	     
	
}



public boolean verifyDetails(String createdBy, String projectTag) throws Exception {
	if (getText(createdBytext).equals("Created By: "+createdBy) && getText(projectTagtext).equals("Project Tag: "+projectTag) && timeStamptext.isDisplayed()) {
		return true;
	}
	else {
		return false;
	}
}

public void removeNotes_Bookmark() {
	click(removeButton);
	
	click(removeButton2);
	
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
}
	
	
	
}
