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
	@SuppressWarnings("static-access")
	public Menu() {
		this.driver=super.driver;
		PageFactory.initElements(driver, this);

	}
	
	
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
	
	
	@FindBy( css = ".v-list-item__title.lighten-1.font-weight-bold")
	private WebElement premiumTools;
	
	@FindAll({@FindBy(css = ".v-list-item__title.accent--text.font-weight-regular")})
	private List<WebElement> premiumToolsFeatures;
	
	
	public void clickOnMenu() {
		click(menu);
	}

	

	
	public void navigateToTitlesCover(String listitem, String groupofTitle) {
		
		for (WebElement webElement : menuList) {		
			if (getText(webElement).equalsIgnoreCase(listitem)) {
				click(webElement);
				if (groupTitle.get(1).isDisplayed()) {
					for (WebElement title : groupTitle) {
						if (getText(title).equalsIgnoreCase(groupofTitle)) {

							click(title);

						      break;
					}
				}
					

			}
				break;
			
			}

		}
		

	}
	public String getTitleHeading() {
		return getText(groupTitleDisplay);
	}

	
	
	public void navigetToStaticFeaturs(String listitem) {
		
		for (WebElement webElement :features) {
			if (webElement.getText().equalsIgnoreCase(listitem)) {
				click(webElement);
				break;
			}
			}
		}
		
	public void clickOnPremiumtools() {
		click(premiumTools);
	}
	
	
	public void clickOnPremiumToolfeature(String feature) {
		for (WebElement webElement : premiumToolsFeatures) {
			if (webElement.getText().equalsIgnoreCase(feature)) {
				click(webElement);
				break;
			}
			
		}
	}
	
	
	public void clickOnMainMenu() {
		click(mainMenu);
	}
	
	public void closeMainmenu() {
		click(menuClose);
	}
	
	}







