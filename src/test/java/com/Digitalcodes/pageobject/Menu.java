package com.Digitalcodes.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Digitalcodes.utilities.Baseclass;

public class Menu extends Baseclass {

	
   WebDriver driver;
	public Menu(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	
	@CacheLookup
	@FindBy(xpath = "//i[normalize-space()='menu']")
	private WebElement menu;


	@FindAll({ @FindBy(xpath = "//div[@class=\"px-2 v-list-item v-list-item--link theme--light\"]") })
	private List<WebElement> menuList;

	
	@FindAll({ @FindBy(xpath = "//div[@class=\"v-treeview-node v-treeview-node--leaf\"]") })
	private List<WebElement> groupTitle;
	
	
	@FindBy(xpath = "//h1[@class='primary--text display-1']")
	private WebElement groupTitleDisplay;

	@FindBy(xpath = "//h4[normalize-space()='Main Menu']")
	private WebElement mainMenu;
	
	@FindBy(xpath = "//i[@class='v-icon notranslate material-icons theme--dark']")
	private WebElement menuClose;
	
	@FindAll({@FindBy(xpath = "//div[@class=\"v-list-item__title font-weight-bold\"]")})
	private List<WebElement> features;
	
	
	public void clickOnMenu() {
		menu.click();
	}

	

	
	public void navigateToTitlesCover(String listitem, String groupofTitle) {
		clickOnMenu();
		for (WebElement webElement : menuList) {		
			if (webElement.getText().equalsIgnoreCase(listitem)) {
				webElement.click();	
				if (groupTitle.get(1).isDisplayed()) {
					for (WebElement title : groupTitle) {
						if (title.getText().equals(groupofTitle)) {

							title.click();

						      break;
					}
				}
					break;

			}
			
			}

		}
		

	}
	public String getTitleHeading() {
		return groupTitleDisplay.getText();
	}

	
	
	public void navigetToStaticFeaturs(String listitem) {
		clickOnMenu();
		for (WebElement webElement :features) {
			if (webElement.getText().equalsIgnoreCase(listitem)) {
				webElement.click();
				break;
			}
			}
		}
		
	
	public void clickOnMainMenu() {
		mainMenu.click();
	}
	
	public void closeMainmenu() {
		menuClose.click();
	}
	
	}







