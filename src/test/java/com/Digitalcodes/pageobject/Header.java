package com.Digitalcodes.pageobject;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.Digitalcodes.utilities.Baseclass;

public class Header extends Baseclass{
	
	
	WebDriver driver;
	
	@SuppressWarnings("static-access")
	public Header() {
		this.driver=super.driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[@class=\"v-btn__content\"]/div")
	  private WebElement SignIn;
	
	@FindBy(xpath = "//span[@class='v-btn__content']//div[@class='row row--dense align-center']")
	private WebElement person;
	
	@FindBy(xpath = "//div[@class=\"v-toolbar__items\"]//h4")                       
	private WebElement subscriptiontype;
	
	@FindBy(xpath = "//h4[@class='pb-1 primary--text txt text-capitalize']")
	private WebElement Name;
	 
	@FindBy(xpath = "//div[@class=\"v-list-item__title white--text\"]/h5")
	private WebElement Email;
	
	@FindBy(xpath = "//span[normalize-space()='Advanced Search']")
	 private WebElement AdvanceSearch;
	
	@FindBy(xpath = "//i[text()='shopping_cart']")
	private WebElement shoppingcart;
	
	public void clickSignIn() {
			click(SignIn);
		
	}
	
	
	public String getSubscrptionType() {
		
		return getText(subscriptiontype);
	}
	
	public void holdOnSubscriptionType() {
		Baseclass.action.moveToElement(person).build().perform();
	}
	
	public String getName() {
		
		return getText(Name);
	}
	
	public String getEmail() {
		return getText(Email);
	}
	
	
	
	
	

}
