package com.Digitalcodes.pageobject;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.Digitalcodes.utilities.Baseclass;

public class TitleLanding_Page extends Baseclass {

	WebDriver driver;
	@SuppressWarnings("static-access")
	public TitleLanding_Page() {
		this.driver=super.driver;
		PageFactory.initElements(driver, this);
	}

	
    @FindBy(xpath = "//span[@class=\"v-chip__content\"]//a")
    private WebElement tag;
	
	@FindBy(xpath = "//p[@class='mb-0 primary--text']" )
	private WebElement activepremium;
	
	@FindBy(xpath = "//button[normalize-space()='favorite_border']")
	private WebElement favorite;
	
	@FindBy(xpath = "//p[@class='ml-2 mb-0']")
	WebElement favoritetext;
	
	@FindBy(xpath = "//button[text()='favorite']")
	private WebElement unfavorite;
	
	
	public String getTagName() throws Exception {
		String tagname1=null;
		if(tag.isDisplayed()){
			tagname1=getText(tag);
	}
		
		else {
			throw new Exception("Tag not Displayed");
		}
		return tagname1;
	}
	
    public String getActivepremiumText() {
		return getText(activepremium);
	}
    
    public void clickOnFavorite() {
    	click(favorite);
    }
    
    public String getFavoriteText() {
    	
    	
    	return getText(favoritetext);
    }
    
    public void clickOnUnFavorite() {
    	click(unfavorite);
    }

    
    
}

