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
	
	@FindBy(xpath = "//div[@class=\"v-data-iterator\"]//a[@target=\"_blank\"]")
	private List<WebElement> includeTitles;
	
	public String getHeading() {
		return getText(heading);
	}
	
	public void clickIncludeTitles(String title) {
		for (WebElement webElement : includeTitles) {
			if (getText(webElement).equals(title)) {
				click(webElement);
				break;
			}
		}
	}
	
	
}
