package com.Digitalcodes.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.utilities.Baseclass;

public class PremiumTools_SharingHistory_Page extends Baseclass {

	WebDriver driver;

	public PremiumTools_SharingHistory_Page() {
		this.driver = Baseclass.driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//p[@class='license-book-title ma-0']")
	private WebElement titlename;

	@FindBy(xpath = "//div[@class='v-expansion-panel-header__icon']")
	private WebElement vIcon;

	@FindBy(xpath = "//h3[@class='mt-1 ml-1 title']")
	private WebElement sectionName;

	@FindBy(xpath = "//p[@class='mt-2 ml-1']")
	private WebElement description;

	@FindBy(xpath = "//div[@class='v-card__text']//h5[1]")
	private WebElement tagname;

	@FindBy(xpath = "//div[@class='v-card__text']//h5[2]")
	private WebElement email;

	@FindBy(xpath = "//div[@class='text-center col']//p")
	private WebElement noresultfound;

	public String titleName() {
		return getText(titlename);
	}

	public void clickOnvIcon() {
		click(vIcon);
	}

	public String getDescription() {
		return getText(description);
	}

	public String getSectionName() {
		return getText(sectionName);
	}

	public String getTagName() {

		return getText(tagname).substring(12, tagname.getText().length());
	}

	public String getSharedwith() {
		return getText(email).substring(13);
	}

	public String getNoResultText() {
		return getText(noresultfound);
	}
}