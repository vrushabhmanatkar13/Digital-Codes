package com.Digitalcodes.pageobject;



import java.util.List;

import org.openqa.selenium.By;
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

	//get Title Name
	
	@FindBy(xpath = "//h1[@class=\"font-weight-regular\"]")
	private WebElement titleName;
	
	//get tag name
    @FindBy(xpath = "//span[@class=\"v-chip__content\"]//a")
    private WebElement tag;
	//active premium text
	@FindBy(xpath = "//p[@class='mb-0 primary--text']" )
	private WebElement activepremium;
	//favorite
	@FindBy(xpath = "//button[normalize-space()='favorite_border']")
	private WebElement favorite;
	
	@FindBy(xpath = "//p[@class='ml-2 mb-0']")
	WebElement favoritetext;
	
	@FindBy(xpath = "//button[text()='favorite']")
	private WebElement unfavorite;
	
	//Current Viewing (Change Version)
	
	@FindBy(xpath = "//div[@class=\"my-6\"]//div[@aria-haspopup=\"listbox\"]")
	private WebElement versionListBox;
	
	@FindBy(xpath = "(//div[@class=\"text-right col\"])[1]")
	private WebElement currentlyViewing1;
	
	@FindBy(xpath = "(//div[@role=\"option\"])[2]")
	private WebElement secondVersion;
	
	@FindBy(xpath = "(//div[@class=\"text-right col\"])[2]")
	private WebElement currentlyViewing2;
	
	
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
    
    public String getTitleHeading() {
    	String titlename = null;
    	//first we get child element of parent element
    	List<WebElement> childelement = titleName.findElements(By.xpath("./*"));
    	//iterate it and replace all text of child element 
    	for (WebElement webElement : childelement) {
    		titlename=getText(titleName).replaceAll(getText(webElement), "").trim();
			
		}
    	
       	return titlename;
    }

    
    public boolean changeVersion() throws Exception {
    	
    	click(versionListBox);
    	
    	if (isDisplayed(currentlyViewing1)) {
    		click(secondVersion);
    		Thread.sleep(3000);
    		click(versionListBox);
    	
    		return isDisplayed(currentlyViewing2);
    		
    	}
    	else {
			throw new Exception("Currently Viewing Text is Present");
		}
    	
    }
}

