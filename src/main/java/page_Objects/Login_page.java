package page_Objects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.Util.Baseclass;

public class Login_page extends Baseclass {

	

	@FindBy(name = "email")
	private WebElement txt_email;
	@FindBy(name = "password")
	private WebElement txt_password;
	@FindBy(xpath = "(//b[contains(text(),'Sign In')])")
	private WebElement btn_signin;

	public void Fun_Login(String username, String password) {
		txt_email.clear();
		txt_email.sendKeys(username);
		txt_password.clear();
		txt_password.sendKeys(password);
		btn_signin.click();
	}
	

public Login_page() {
	PageFactory.initElements(driver, this);
}
	

}
