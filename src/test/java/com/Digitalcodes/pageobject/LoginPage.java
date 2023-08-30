package com.Digitalcodes.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.utilities.Baseclass;

public class LoginPage extends Baseclass {

	@SuppressWarnings("static-access")
	public LoginPage() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);
	}

	WebDriver driver;

	@FindBy(id = "emailAddress")
	private WebElement emailtextbox;

	@FindBy(id = "password")
	private WebElement passwordtestbox;
	@FindBy(xpath = "(//b[contains(text(),'Sign In')])")
	private WebElement SignInbutton;

	public void login(String username, String password) {

		sendKeys(emailtextbox, username);
		sendKeys(passwordtestbox, password);
		SignInbutton.click();

	}

}
