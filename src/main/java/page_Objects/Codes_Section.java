package page_Objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.Util.Baseclass;

public class Codes_Section extends Baseclass {

	public Codes_Section() {
		PageFactory.initElements(driver, this);
	}

	public String sessionnumber;

	@FindBy(xpath = "//div[normalize-space()='Code Sections']")
	private WebElement codessection;

	@FindAll({ @FindBy(xpath = "//p[@class='mb-0 py-2 body-2']/a") })
	private List<WebElement> list_section;

	@FindAll({ @FindBy(xpath = "//div//div[@class=\"row ma-0 row--dense align-center pl-3\"]/div/p/a") })
	private List<WebElement> list_subsection;

	@FindBy(xpath = "//h1[@class=\"chapter\"]")
	private WebElement chaper;

	@FindBy(xpath = "(//h1[@class=\"level1\"])[1]")
	private WebElement chapter_name;
	
	@FindBy(xpath = "(//h2[@class=\"frontmatter_subhead\"]/span)[1]")
	private WebElement subchap_name;
	
	@FindBy(xpath = "(//section[@class=\"level1\"])[1]")
	private WebElement sectionnumber;

	@FindAll({ @FindBy(xpath = "//div[contains(@id,'section-action-icon-')]") })
	private List<WebElement> apps;
    
	@FindAll({@FindBy(xpath = "//h1[contains(@id,'text-id-')]")})
	private List<WebElement> doubleclick;
	
	@FindAll({ @FindBy(xpath = "//i[contains(@id,'_note')]") })
	private List<WebElement> note;

	
	
	public boolean nav_section(String section) throws Exception {
		codessection.click();

		for (WebElement webElement : list_section) {

			if (webElement.getText().equalsIgnoreCase(section)) {
				Thread.sleep(2000);
				webElement.click();

				break;
			}

		}

		return chaper.isDisplayed();
	}

	public String click_sub_section() {
		String sub_section = null;

		if (list_subsection.get(0).isDisplayed()) {
			list_subsection.get(0).click();
		} 

		if (chapter_name.isDisplayed() || subchap_name.isDisplayed()) {
			sub_section = list_subsection.get(0).getText();
			sessionnumber = sectionnumber.getAttribute("section-number");
		}

		return sub_section;

	}

	public void click_apps() {
		for (WebElement webElement : apps) {
			if (webElement.getAttribute("id").equals("section-action-icon-"+sessionnumber)) {
				webElement.click();
			}
			break;
		}
		
	}

	public void click_note() {
		for (WebElement webElement : note) {
			if (webElement.getAttribute("id").equals(sessionnumber+"_note")) {
				for (WebElement titlename : doubleclick) {
					if(titlename.getAttribute("id").equals("text-id-"+sessionnumber)) {
						action.doubleClick(titlename);
					}
					break;
				}
				webElement.click();
			}
			break;
		}
	}


}
