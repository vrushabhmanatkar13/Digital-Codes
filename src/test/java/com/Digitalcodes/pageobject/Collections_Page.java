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

	@FindBy(xpath = "//h1[@class='font-weight-regular']")
	private WebElement heading;
	
	@FindBy(xpath = "//div[@class='v-data-iterator']//p[@class='mb-0 font-weight-bold']")
	private List<WebElement> includeTitles;
	
	@FindBy(xpath = "(//div[@class=\"row row--dense\"]//p[@class=\"mb-0 font-weight-bold\"])[1]")
	private WebElement recentlyAdded;
	
	public String getHeading() {
		return getText(heading);
	}
	
	public void clickIncludeTitles(String title) throws Exception {
		  Baseclass.getParentWindow();
		for (WebElement webElement : includeTitles) {
			
			if (getText(webElement).equalsIgnoreCase(title)) {
		     
				click(webElement);

				Baseclass.switchToWindow();
				break;
			}
			
			
		}
	}
	
	public String clickOnRecentlyAddedTitles() throws Exception {
		  Baseclass.getParentWindow();
		 String text=getText(recentlyAdded);
		 click(recentlyAdded);
		 Baseclass.switchToWindow();
		 return text;
	}
	
}
