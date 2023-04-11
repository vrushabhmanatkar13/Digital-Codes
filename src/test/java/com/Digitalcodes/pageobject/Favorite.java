package com.Digitalcodes.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Favorite {

	WebDriver driver;

	public Favorite(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "accent--text")
	private WebElement titleName;

	public String getTitleName() {

		return titleName.getText();
	}

}
