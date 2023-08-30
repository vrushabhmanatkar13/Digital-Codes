package com.Digitalcodes.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.utilities.Baseclass;

public class TitleCover_Page extends Baseclass {

	WebDriver driver;

	@SuppressWarnings("static-access")
	public TitleCover_Page() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);
	}

	@FindAll({ @FindBy(xpath = "//div[@class=\"row row--dense justify-center\"]/div") })
	private List<WebElement> listOfTitles;

	@FindBy(xpath = "//h1[@class='primary--text display-1']")
	private WebElement groupTitleDisplay;

	// ASTM Standers Cover page
	@FindBy(xpath = "//h2[@class='font-weight-regular white--text']")
	private List<WebElement> categories;

	public TitleLanding_Page clickOnTitlesCover(String title) {

		for (WebElement webElement : listOfTitles) {
			if (getText(webElement).equalsIgnoreCase(title)) {

				click(webElement);
				break;
			}

		}
		return new TitleLanding_Page();

	}

	public String getHeading() {
		return getText(groupTitleDisplay);
	}

	public void clickASTMCategories(String category) {
		for (WebElement webElement : categories) {
			if (getText(webElement).equalsIgnoreCase(category)) {
				Baseclass.action.click(webElement).build().perform();
				break;

			}
		}

	}

}
