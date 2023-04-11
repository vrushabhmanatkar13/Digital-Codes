package com.Digitalcodes.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Digitalcodes.utilities.Baseclass;

public class Codes_Section{
      WebDriver driver;
	
	public Codes_Section(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	

	@FindBy(xpath = "//div[contains(text(),'Code Sections')]")
	private WebElement codessection;

	@FindAll({ @FindBy(xpath = "//p[@class='mb-0 py-2 body-2']/a") })
	private List<WebElement> list_section;

	@FindAll({ @FindBy(xpath = "//div[@class=\"row ma-0 row--dense align-center pl-3\"]//a") })
	private List<WebElement> list_subsection;

	@FindBy(xpath = "//div[@class=\"py-0 col\"]")
	private WebElement chapterHeading;
	
	@FindBy(xpath = "//section[@class=\"chapter\"]")
	private WebElement chapterName;

	@FindAll({@FindBy(xpath = "//section[contains(@class,\"level1\")]")})
	private List<WebElement> subChapterName;
	
	
	
	

	@FindAll({ @FindBy(xpath = "//i[contains(@id,'section-action-icon-')]") })
	private List<WebElement> apps;
    
	@FindBy(xpath = "//h1[@class=\"chapter\"]")
	private WebElement titleofChapter;
	
	@FindBy(xpath = "//h1[@class=\"level1\"]")
	private WebElement titleofSubChapter;
	
	@FindBy(xpath = "//i[text()=' border_color']") 
	private WebElement note;

  @FindBy (xpath = "//div[@class=\"ql-editor ql-blank\"]")	
  private WebElement textcontainer;
	
  @FindBy(xpath = "(//span[contains(text(),'Save')])[1]")
  private WebElement saveButton_Notes;
  
  
  
 
  
  
  @FindBy(xpath = "//span[contains(text(),'New Tag')]")
  private WebElement newTagButton;
   
  @FindBy(xpath = "//input[@placeholder=\"Tag Name\"]")
  private WebElement tagNameInput;
  
  
  @FindBy(xpath = "(//span[contains(text(),'Save')])[2]")
  private WebElement saveButton_Tag;
  
  @FindBy(xpath = "(//div[@aria-haspopup=\"listbox\"]//span)[1]")
  private WebElement newTagName;
  
  @FindAll({@FindBy(xpath = "//div[contains(@id,\"list-item-\")]")})
  private List<WebElement> listTagName;
  
  @FindBy(xpath = "//div[@class='caption cl description']")
  private WebElement descriptionText;
 
  @FindBy(xpath = "//h5[@class=\"font-weight-regular left\"][1]")
  private WebElement getTagName;
  
  @FindBy(xpath = "//h5[@class=\"font-weight-regular left\"][2]")
  WebElement getCreatedBy;
  
  
  
  public void clickCodesSection() {
	  codessection.click();
  }
  
 
  
  
	public String navigateToChapter(String section) throws Exception {
		 String chaptername=null;
		 Baseclass.wait.until(ExpectedConditions.visibilityOfAllElements(list_section));
		 
		for (WebElement webElement : list_section) {

			if (webElement.getText().equalsIgnoreCase(section)) {
				Thread.sleep(2000);
				chaptername=webElement.getText();
				webElement.click();
            
				break;
				 
			}

		}
		
		
		return chaptername;

		
	}
	 public String getChapterName() {
			return chapterHeading.getText();
		}
	
	
	
	
	public String getSessionID_FromChapter() {
		return chapterName.getAttribute("section-number");
	
}
	
	
	

	public String navigateToSubChapter() {

		if (list_subsection.get(0).isDisplayed()) {
			list_subsection.get(0).click();
			
			return list_subsection.get(0).getText();
		} 
		else {
			System.out.println("No subchapter in this chapter");
			return null;
		}
	}
	
	public String getSessionID_FromSubChapter() {
		return subChapterName.get(0).getAttribute("section-number");
	}

		

	

	public void clickOnApps(String session) {
		for (WebElement webElement : apps) {
			if (webElement.getAttribute("id").equals("section-action-icon-"+session)) {
				webElement.click();
				break;
			}
			
		}
		
	}

	
	
	public void doubleClickOnTitle_Section() {
		Baseclass.action.doubleClick(titleofChapter).build().perform();
	}
	
	public void doubleClickOnTitle_Subsection() {
		Baseclass.action.doubleClick(titleofSubChapter).build().perform();
	}
	
	
	public void createNote_FromSection(String notetext) {			
		note.click();
		textcontainer.clear();
		textcontainer.sendKeys(notetext);
		
					
	}
	
	public void clickOnSaveButton() {
		saveButton_Notes.click();
	}
	
	
	public void createNewTag(String tagname) {
		newTagButton.click();
		tagNameInput.clear();
		tagNameInput.sendKeys(tagname);
		
		saveButton_Tag.click();
		 
	}
	
	public String selectTag(String tagname) {
		newTagName.click();
		
		for (WebElement webElement : listTagName) {
			if (webElement.getText().equals(tagname)) {
				webElement.click();
				break;
			}
			
		}
		return newTagName.getText();
	}
	
	
	
	public String getDescription() {
		return descriptionText.getText();
	}

	
	public String getTagName() {
		return getTagName.getText().substring(13);
	}
	
	public String getCreatedBy() {
		return getCreatedBy.getText().substring(12);
		
	}
    
}
