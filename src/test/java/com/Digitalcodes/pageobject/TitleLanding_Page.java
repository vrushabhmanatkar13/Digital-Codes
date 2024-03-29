package com.Digitalcodes.pageobject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.testcases.Prerequisites_Teardown;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.Sparkreport;

public class TitleLanding_Page extends Baseclass {

	WebDriver driver;

	@SuppressWarnings("static-access")
	public TitleLanding_Page() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);
	}

	// get Title Name

	@FindBy(xpath = "//h1[@class=\"font-weight-regular\"]")
	private WebElement titleName;

	// get tag name
	@FindBy(xpath = "//span[@class=\"v-chip__content\"]//a")
	private WebElement tag;
	// active premium text
	@FindBy(xpath = "//p[@class='mb-0 primary--text']")
	private WebElement activepremium;
	// favorite
	@FindBy(xpath = "//button[normalize-space()='favorite_border']")
	private WebElement favorite;

	@FindBy(xpath = "//p[@class='ml-2 mb-0']")
	WebElement favoritetext;

	@FindBy(xpath = "//button[text()='favorite']")
	private WebElement unfavorite;

	// Current Viewing (Change Version)

	@FindBy(xpath = "//div[@class='my-6']//div[@class='v-input__append-inner']")
	private WebElement versionListBox;

	@FindBy(xpath = "(//div[@class='text-right col'])[1]")
	private WebElement currentlyViewing1;

	@FindBy(xpath = "(//div[@role=\"option\"])[2]")
	private WebElement secondVersion;

	@FindBy(xpath = "(//div[@class=\"text-right col\"])[2]")
	private WebElement currentlyViewing2;

	// Releated Titles

	@FindAll({ @FindBy(xpath = "//div[@class=\"col-lg-6 col-12\"]//a[@target=\"_blank\"]") })
	private List<WebElement> releatedTitles;

	// Categories
	@FindBy(xpath = "(//a/span[@class='v-chip__content'])[1]")
	private WebElement categories;

	// Not Premium text

	@FindBy(xpath = "//p[@class='accent--text mb-0']")
	private WebElement Notpremiumtext;

	public String getTagName() throws Exception {
		String tagname1 = null;
		if (tag.isDisplayed()) {
			tagname1 = getText(tag);
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
		// Baseclass.action.moveToElement(favorite).build().perform();
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
		// first we get child element of parent element
		List<WebElement> childelement = titleName.findElements(By.xpath("./*"));
		// iterate it and replace all text of child element
		for (WebElement webElement : childelement) {
			titlename = getText(titleName).replaceAll(getText(webElement), "").trim();

		}

		return titlename;
	}

	public boolean changeVersion() throws Exception {

		click(versionListBox);

		if (isDisplayed(currentlyViewing1)) {
			click(secondVersion);

			click(versionListBox);
			if (isDisplayed(currentlyViewing2)) {
				return isDisplayed(currentlyViewing2);
			} else {
				throw new Exception("Currently Viewing Text is not Changed to next virson on book");
			}

		} else {
			throw new Exception("Currently Viewing Text is Present");
		}

	}

	public int verifyReleatedTitles() throws MalformedURLException, IOException {
		int code = 0;
		if (releatedTitles.get(0).isDisplayed()) {

			for (WebElement webElement : releatedTitles) {

				Baseclass.scrollUptoElement(webElement);
				String url = webElement.getAttribute("href");
				HttpURLConnection openUrl = (HttpURLConnection) (new URL(url).openConnection());
				openUrl.setRequestMethod("HEAD");
				openUrl.connect();
				code = openUrl.getResponseCode();

				if (code != 200) {
					Sparkreport.Step(getText(webElement) + "Status Code = " + code);
					return code;
				} else {

					return code;
				}
			}

		} else {
			code = 200;
			Sparkreport.Step("This Title not having Releated Titles");
		}
		return code;

	}

	public String getTagNameFromCategory() {
		String tagname = null;
		String[] text = getText(categories).split(" ");
		if (text[1].equals("Standards")) {
			tagname = "PREMIUM LITE";
		} else {
			tagname = "PREMIUM";
		}
		return tagname;
	}

	public String getCategoryText() {
		String text = getText(categories);
		click(categories);
		return text;

	}

	public boolean getTitleNameisDisplayed() {
		return isDisplayed(titleName);
	}

	public String getNotPremiumText() {
		return getText(Notpremiumtext);
	}

}
