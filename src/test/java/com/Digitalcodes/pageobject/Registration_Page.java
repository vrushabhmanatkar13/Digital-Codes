package com.Digitalcodes.pageobject;



import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.utilities.Baseclass;

public class Registration_Page extends Baseclass{

	
	WebDriver driver;
	public Registration_Page() {
		this.driver=Baseclass.driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[@class='primary--text font-weight-regular']")
	private WebElement heading;
	
	@FindBy(id="firstName")
	private WebElement firstname;
	
	@FindBy(id="lastName")
	private WebElement lastname;
	
	@FindBy(id="securityQuestion")
	private WebElement securityquestion;
	
	@FindBy(id="securityAnswer")
	private WebElement securityans;
	
	@FindBy(id="emailAddress")
	private WebElement emailaddress;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="confirmPassword")
	private WebElement conformpassword;
	
	@FindBy(id="spamProtection")
	private WebElement spamprotection;
	
	@FindBy(xpath = "//div[@class='v-input--selection-controls__input']")
	private WebElement checkbox;
	
	@FindBy(xpath = "//div[@style='width: 55%;']//div[@class='v-alert__content']")
	private WebElement free14dayalert;
	
	@FindBy(xpath = "//div[@style='width: 55%;']//button[@aria-label='Close']")
	private WebElement close;
	
	@FindBy(xpath = "//i[@class='v-icon notranslate mdi mdi-alert theme--light red--text']")
	private WebElement errorsymbol;
	
	@FindBy(xpath = "//div[contains(@class,'v-text-field--outlined error')]//label")
	private WebElement errorinputbox;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitbutton;
	
	@FindBy(id="search")
	private WebElement searchmailinator;
	
	@FindBy(xpath = "//tr[@ng-repeat='email in emails']")
	private WebElement emailinemail;
	
	@FindBy(xpath = "//span[text()=' Verify Email ']")
	private WebElement verifyemail;
	
	@FindBy(xpath = "//input[@type='text']")
    private List<WebElement> alltextbox;	
	
	//Purchase Premium complete
	@FindBy(xpath = "//span[contains(text(),'Subscribe to Premium Complete')]")
	private WebElement subscribetoPremium;
	
	
	//Add to Cart 
	@FindBy(xpath = "//span[@class='roboto caption']")
	private WebElement start14Day;
	
	@FindBy(xpath = "//div[@role='radiogroup']//p[@class='mb-0']")
	private List<WebElement> listBilled;
	
	@FindBy(xpath = "//div[@class='row align-center yellow lighten-5']//div[@class='text-right col col-auto']//p[@class='mb-0']")
	private WebElement price;
	
	@FindBy(xpath = "//input[@type='tel']")
	private WebElement qtyInput;
	
	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	private WebElement addToCart;
	
	
	public void registerUser(String firstName, String lastName, String securityQuestion, String securityAns, String emailId, String Password, String conformPassword, String spam) {
		
		sendKeys(firstname, firstName);
		sendKeys(lastname, lastName);
		sendKeys(securityquestion, securityQuestion);
		sendKeys(securityans, securityAns);
		sendKeys(emailaddress, emailId);
		sendKeys(password, Password);
		sendKeys(conformpassword, conformPassword);
		sendKeys(spamprotection, spam);
		
	}
	
	public boolean clickcheckBox() {
		click(checkbox);
		return free14dayalert.isDisplayed();
	}
	
	public boolean checkboxSelected() {
		return checkbox.isSelected();
	}
	public void clickSubmit() {
		click(submitbutton);
	}
	
	public boolean submitIsEnable() {
		return submitbutton.isEnabled();
	}
	
	public String getErrorSymbolOnInputBox() throws Exception {
		
		if (isDisplayed(errorsymbol)) {
	
			return getText(errorinputbox);
		
		}
		else {
			throw new Exception("Error Symbole is not displying");
		}
	}
	
	
	public String getHeading() {
		return getText(heading);
	}
	
	
	public void verifyemail(String email) {
		sendKeys(searchmailinator, email);
		searchmailinator.sendKeys(Keys.ENTER);
		click(emailinemail);
		swichToframe("html_msg_body");
		scrollUptoElement(verifyemail);
		click(verifyemail);
		swichToParentFrame();
	}
	
	
	public String clickSubscribeToPremium() {
		String text=getText(subscribetoPremium);
		click(subscribetoPremium);
		return text;
	}
	
	public String get14DayTrialText() {
		return getText(start14Day);
	}
	
	public String  selectBillingCycle(String billingcycle) {
		for (WebElement webElement : listBilled) {
			if (getText(webElement).equalsIgnoreCase(billingcycle)) {
				click(webElement);
				break;
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		return getText(price);
	}
	
	
	public void setQuentity(String qty) {
		sendKeys(qtyInput, qty);
	}
	
	public void clickAddToCart() {
		click(addToCart);
	}
	
	
	public void clearInvalidValue(String text) throws Exception {
		for (WebElement webElement : alltextbox) {
			
			if (getTextByJS(webElement).equalsIgnoreCase(text)) {
				webElement.clear();
				break;
			}
			else {
				throw new Exception("Text Not Matched");
			}
		}
	}
	
	
}
