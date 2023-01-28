package page_Objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.Util.Baseclass;

public class Homepage extends Baseclass{
	public Homepage() {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[contains(text(),'Sign In')]")
	public WebElement btn_personsignin;
     
	@FindBy(xpath = "//div[@class='row access-level row--dense align-center']")
	private WebElement btn_username;
	
	@FindBy(xpath = "//div[contains(text(),'Sign Out')]")
	private WebElement btn_signout;
	
	public void fun_signin() {
		this.btn_personsignin.click();
	}
	
	public String sign_out() {
		mouse_Over(this.btn_signout);
		return this.btn_personsignin.getText();
		
	}

}
