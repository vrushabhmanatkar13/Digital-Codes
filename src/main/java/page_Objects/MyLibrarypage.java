package page_Objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.Util.Baseclass;

public class MyLibrarypage extends Baseclass {
	public MyLibrarypage() {
		PageFactory.initElements(driver, this);
	}

	// ---------welcome banner----------------------------------
	@FindBy(xpath = "//div[@class=\"row row--dense\"]//div[@class=\"col\"]")
	public WebElement lable_Welcome;

	// ------Sync Button and cancel ----------------------------------
	@FindBy(xpath = "//span[text()='Sync Library']")
	protected WebElement btn_sync;
	@FindBy(xpath = "//span[contains(text(),'Cancel')]")
	protected WebElement btn_cancel;

	public boolean fun_sync() throws Exception {
		if (btn_sync.isEnabled()) {
			btn_sync.click();
			return btn_cancel.isDisplayed();
		} else {
			throw new Exception("Button not Enlabel");
		}

	}

	// ----------View Entire Library-------------------------
	@FindBy(xpath = "//span[contains(text(),'View Entire Library')]")
	private WebElement btn_viewlibrary;
	@FindBy(xpath = "//span[contains(text(),'View Row Library Display')]")
	private WebElement btn_view_rowlibrary;
	@FindBy(xpath = "//h2[contains(text(),'Your Entire Library')]")
	public WebElement label_your_entirelibrary;
	@FindBy(xpath = "//div[@class=\"v-data-iterator\"]")
	public WebElement label_result;

	public String fun_view_entirelibrary() throws Exception {
		if (btn_viewlibrary.isEnabled()) {
			btn_viewlibrary.click();
			return btn_view_rowlibrary.getText();
		} else {
			throw new Exception("Button not enlabel");
		}
	}

}
