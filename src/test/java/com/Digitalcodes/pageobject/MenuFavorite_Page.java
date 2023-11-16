package com.Digitalcodes.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.utilities.Baseclass;

public class MenuFavorite_Page extends Baseclass {

	WebDriver driver;

	@SuppressWarnings("static-access")
	public MenuFavorite_Page() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath  = "//div[@class='v-list-item__title font-weight-bold']")
	private WebElement titleName;

	public String getTitleName() {

		return getText(titleName);
	}

}
