package page_Objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Digitalcodes.Util.Baseclass;

public class Titles extends Baseclass {

	public Titles() {
		PageFactory.initElements(driver, this);
	}

	@FindAll({ @FindBy(xpath = "//div[@class=\"row row--dense justify-center\"]/div") })
	private List<WebElement> books;
	@FindBy(xpath = "//h1[@class='font-weight-regular']")
	private WebElement bookname;
    @FindBy(xpath = "//span[@class=\"v-chip__content\"]//a")
    private WebElement tag;
	
	@FindBy(xpath = "//p[@class='mb-0 primary--text']" )
	private WebElement activepremium;
	
	@FindBy(xpath ="//div[normalize-space()='My Notes']")
	private WebElement mynotes;
	
	@FindBy(xpath ="//p[@class='caption text-center']")
	 private WebElement no_notestext;
	@FindBy(xpath = "//p[@class=\"mb-0\"]/a")
	private WebElement notesection;
	
	
	public String click_book(String title) {
		for (WebElement book_1 : books) {
			if (book_1.getText().equals(title)) {
				book_1.click();
				
				break;
			}
			
		}
		return bookname.getText();

	}
    
	public String tagname() {
		String tagname1=null;
		if(tag.isDisplayed()){
			tagname1=tag.getText();
	}
		return tagname1;
	}
    public String active_premium() {
		return activepremium.getText();
	}

    public String click_mynotes() {
    	mynotes.click();
    	
    	String notes=null;
    	if (no_notestext.isDisplayed()) {
    		notes=no_notestext.getText();
    	}
    	else if(notesection.isDisplayed()) {
    		notes=notesection.getText();
    		
    	}
    	return notes;
    }
    
}

