package com.Digitalcodes.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.utilities.Baseclass;

public class Collections_Page extends Baseclass{
	WebDriver driver;
	
	public Collections_Page() {
		this.driver=Baseclass.driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[@class=\"font-weight-regular\"]")
	private WebElement heading;
	
	@FindBy(xpath = "//div[@class='v-data-iterator']//a[@target='_blank']")
	private List<WebElement> includeTitles;
	
	@FindBy (xpath = "//div[@target='_blank']//p[@class='mb-0 font-weight-bold']")
	private List<WebElement> recentlyAdded;
	
	public String getHeading() {
		return getText(heading);
	}
	
	public void clickIncludeTitles(String title) throws Exception {
		
		
		for (WebElement webElement : includeTitles) {
			
			if (getText(webElement).equalsIgnoreCase(title)) {
		     
				click(webElement);
				break;
			}
			
			
		}
	}
	
	public void clickRecentlyAddedTitles(String title) throws Exception {
		for (WebElement webElement : recentlyAdded) {
			Baseclass.scrollUptoElement(webElement);
			if (getText(webElement).equalsIgnoreCase(title)) {
				click(webElement);
				break;
			}
			else {
				throw new Exception(title +" is Not Displyed on Current Webpage");
			}
			
		}
	}
	
}
