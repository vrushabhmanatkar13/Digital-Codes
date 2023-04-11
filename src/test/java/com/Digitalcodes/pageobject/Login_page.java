package com.Digitalcodes.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_page {

	WebDriver driver;
	@FindBy(id = "emailAddress")
	private WebElement emailtextbox;
	@FindBy(id = "password")
	
	private WebElement passwordtestbox;
	@FindBy(xpath = "(//b[contains(text(),'Sign In')])")
	private WebElement SignInbutton;

	public Login_page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void login(String username, String password) {
		try {
			new Header(driver).clickSignIn();
			emailtextbox.clear();
			emailtextbox.sendKeys(username);
			passwordtestbox.clear();
			passwordtestbox.sendKeys(password);
			SignInbutton.click();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
