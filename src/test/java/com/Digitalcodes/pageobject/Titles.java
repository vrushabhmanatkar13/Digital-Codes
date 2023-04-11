package com.Digitalcodes.pageobject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Digitalcodes.utilities.Baseclass;

public class Titles {

	WebDriver driver;
	public Titles(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindAll({ @FindBy(xpath = "//div[@class=\"row row--dense justify-center\"]/div") })
	private List<WebElement> listOfTitles;
	
	
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
	
	
	public String clickOnTitlesCover(String title) {
		String bookName = null;
		for (WebElement webElement : listOfTitles) {
			if (webElement.getText().equalsIgnoreCase(title)) {
				bookName=webElement.getText();
				webElement.click();
				break;
			}
			
		}
		return bookName;
		

	}
    
	public String getTagName() throws Exception {
		String tagname1=null;
		if(tag.isDisplayed()){
			tagname1=tag.getText();
	}
		
		else {
			throw new Exception("Tag not Displayed");
		}
		return tagname1;
	}
	
    public String getActivepremiumText() {
		return activepremium.getText();
	}
    
    public void clickOnFavorite() {
    	favorite.click();
    }
    
    public String getFavoriteText() {
    	
    	Baseclass.wait.until(ExpectedConditions.visibilityOf(favoritetext));
    	return favoritetext.getText();
    }
    
    public void clickOnUnFavorite() {
    	unfavorite.click();
    }

    
    
}

