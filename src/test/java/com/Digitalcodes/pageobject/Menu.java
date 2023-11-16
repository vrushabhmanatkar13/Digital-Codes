package com.Digitalcodes.pageobject;


import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Digitalcodes.utilities.Baseclass;

public class Menu extends Baseclass {

	WebDriver driver;

	@SuppressWarnings("static-access")
	public Menu() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//i[normalize-space()='menu']")
	private WebElement menu;

	@FindAll({ @FindBy(xpath  = "//*[@class='v-list-item v-list-item--link theme--light']") })
	private List<WebElement> menu_options_L1;

	@FindAll({ @FindBy(xpath = "//div[@class='v-list-item__title fs-16 font-weight-bold accent--text']") })
	private List<WebElement> menu_options_L2;
	
	@FindBy(xpath = "//a[@class='accent--text']")
	private List<WebElement> menu_options_L3;
	
	@FindBy(xpath = "//div[@tabindex='0']/div[normalize-space()='Main Menu']")
	private WebElement mainMenu;

	@FindBy(xpath = "//i[text()='clear']")
	private WebElement menuClose;


	public void clickOnMenu() {
		click(menu);
	}
	
	public void clickOnMainMenu() {
		if (mainMenu.isDisplayed()) {
			click(mainMenu);
		}
	}
	
	public void click_menu_optionL1(String option_L1) {
		for (WebElement webElement : menu_options_L1) {
			if (webElement.getText().equalsIgnoreCase(option_L1)) {
				click(webElement);
				break;
			}
			
		}
	}
	
	public void click_menu_optionL2(String option_L2) {
		for (WebElement webElement : menu_options_L2) {
			if (webElement.getText().equalsIgnoreCase(option_L2)) {
				click(webElement);
				break;
			}
			
		}
	}
	
	public void click_menu_optionL3(String option_L3) {
		for (WebElement webElement : menu_options_L3) {
			if (webElement.getText().equalsIgnoreCase(option_L3)) {
				click(webElement);
				break;
			}
			
		}
	}
	

	public TitleCover_Page navigateToTitlesCover(String option_L1, String option_L2, String option_L3) {
		clickOnMenu();
		//clickOnMainMenu();
		this.click_menu_optionL1(option_L1);
		this.click_menu_optionL2(option_L2);
		this.click_menu_optionL3(option_L3);
		return new TitleCover_Page();

	}

		

	public void closemenu() {
		click(menuClose);
	}

}
