package com.Digitalcodes.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.utilities.Baseclass;

public class TOC {
	
	WebDriver driver;
	
	public TOC(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//div[contains(text(),'My Notes')]")
	private WebElement mynotes;
	
	@FindBy(xpath ="//p[@class='caption text-center']")
	 private WebElement nothaveanynotestext;
	
	@FindBy(xpath = "//p[@class=\"mb-0\"]/a")
	private WebElement notesection;
	
	
	public void clickOnMyNotes() {
		 mynotes.click();
	}
	
	public String verifyMynotes() {
    	
		
    	String notes=null;
    	if (nothaveanynotestext.isDisplayed()) {
    		notes=nothaveanynotestext.getText();
    	}
    	else if(notesection.isDisplayed()) {
    		notes=notesection.getText();
    		
    	}
    	return notes;
    }
	
	
}
