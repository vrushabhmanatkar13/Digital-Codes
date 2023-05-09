package com.Digitalcodes.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.utilities.Baseclass;

public class Favorite extends Baseclass{

	WebDriver driver;

	@SuppressWarnings("static-access")
	public Favorite() {
		this.driver =super.driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "accent--text")
	private WebElement titleName;

	public String getTitleName() {

		return getText(titleName);
	}

}
