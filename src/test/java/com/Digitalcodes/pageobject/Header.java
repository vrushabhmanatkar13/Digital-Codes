package com.Digitalcodes.pageobject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Digitalcodes.utilities.Baseclass;

public class Header{
	
	
	WebDriver driver;
	
	public Header(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[@class=\"v-btn__content\"]/div")
	  WebElement SignIn;
	
	@FindBy(xpath = "//span[@class='v-btn__content']//div[@class='row row--dense align-center']")
	private WebElement person;
	
	@FindBy(xpath = "//div[@class=\"v-toolbar__items\"]//h4")                       
	private WebElement subscriptiontype;
	
	@FindBy(xpath = "//div[@class=\"v-list-item__title white--text\"]/h4")
	private WebElement Name;
	 
	@FindBy(xpath = "//div[@class=\"v-list-item__title white--text\"]/h5")
	private WebElement Email;
	
	@FindBy(xpath = "//span[normalize-space()='Advanced Search']")
	 private WebElement AdvanceSearch;
	
	@FindBy(xpath = "//i[text()='shopping_cart']")
	private WebElement shoppingcart;
	
	public void clickSignIn() {
			SignIn.click();
		
	}
	
	
	public String getSubscrptionType() {
		
		Baseclass.wait.until(ExpectedConditions.visibilityOf(subscriptiontype));
		return subscriptiontype.getText();
	}
	
	public void holdOnSubscriptionType() {
		Baseclass.action.moveToElement(person).build().perform();
	}
	
	public String getName() {
		Baseclass.wait.until(ExpectedConditions.visibilityOf(Name));
		return Name.getText();
	}
	
	public String getEmail() {
		return Email.getText();
	}
	
	
	
	
	

}
