package com.Digitalcodes.pageobject;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Digitalcodes.utilities.Baseclass;

public class ContentSearch_Page extends Baseclass {

	WebDriver driver;

	public ContentSearch_Page() {
		this.driver = Baseclass.driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='txt_search']")
	private WebElement searchInputBox;

	@FindBy(xpath = "//span[contains(text(),'Advanced Search')]")
	private WebElement advanceSearchButton;

	@FindBy(xpath = "//div[@class='search-item-breadcrumb']//a")
	private List<WebElement> titleNames;

	@FindBy(xpath = "(//span[@style='background-color: yellow;'])")
	private List<WebElement>  resulttext;

	@FindBy(xpath = "(//p[@class='mb-0 font-weight-bold'])[1]")
	private WebElement searchtext;

	@FindBy(xpath = "//div[@class=\"col\"]//span[contains(@class,'mr-1 mt-1 text-wrap v-chip')]")
	private List<WebElement> filters;

	@FindBy(xpath = "//div[@class='v-card__text py-0']//h5")
	private List<WebElement> filterName;

	@FindBy(xpath = "//button[@class='v-icon notranslate white--text v-icon--link mdi mdi-plus theme--light']")
	private List<WebElement> plusbutton;

	@FindBy(xpath = "//button[@class='v-icon notranslate white--text v-icon--link mdi mdi-minus theme--light']")
	private List<WebElement> minusbutton;

	@FindBy(xpath = "//p[@class=\"mb-0 caption text-wrap\"]")
	private List<WebElement> categoriesfilter;
	
	@FindBy(xpath = "//p[@class='my-1 caption text-wrap']")
	private List<WebElement> yearfilter;
	
	@FindBy(xpath = "//label[@class='caption']")
	private List<WebElement> premiumtitlesfilter;
	
	@FindBy(xpath = "//div[@multiple='multiple']//p[@class='mb-0 caption']")
	private List<WebElement> contenttypefilter;

	@FindBy(xpath = "//button[contains(@class,'checkbox-blank-outline')]")
	private List<WebElement> checkbox;

	@FindBy(xpath = "//div[@class='col']//span[@class='v-chip__content']")
	private List<WebElement> checkboxText;

	@FindBy(xpath = "//button[@class='v-icon notranslate v-chip__close v-icon--link v-icon--right mdi mdi-close-circle theme--light']")
	private WebElement closefilter;

	@FindBy(xpath = "(//i[text()='input'])[1]")
	private WebElement jumpbutton;

	@FindBy(xpath = "(//button[text()='file_copy'])[1]")
	private WebElement copybutton;

	@FindBy(xpath = "(//div[@class='v-snack__wrapper v-sheet theme--dark success']//div[@role='status'])[1]")
	private WebElement copystatus;

	@FindBy(xpath = "(//button[text()='local_printshop'])[1]")
	private WebElement printbutton;

	@FindBy(xpath = "(//button[text()='link'])[1]")
	private WebElement linkbutton;

	@FindBy(xpath = "(//button[text()='bookmark_border'])[1]")
	private WebElement bookmarkbutton;

	@FindBy(xpath = "//p[@class='subtitle-1 mb-0 font-weight-bold']")
	private List<WebElement> subtitle;

	@FindBy(css = "button[role='button']")
	private WebElement advancedtermsearch;

	@FindBy(xpath = "//input[@placeholder='Ex. Bearing Wall Structure']")
	private WebElement extractphasetxtbox;

	@FindBy(xpath = "(//input[@placeholder='Ex. Area, Building, Brick'])[1]")
	private WebElement allofwordstxtbox;

	@FindBy(xpath = "(//input[@placeholder='Ex. Area, Building, Brick'])[2]")
	private WebElement anyofwordstxtbox;

	@FindBy(xpath = "//input[@placeholder='Ex. Concrete, Cement']")
	private WebElement noneofwordstxtbox;

	@FindBy(xpath = "//input[@placeholder='Ex. Fire']")
	private WebElement nearsearchtxtbox;

	@FindBy(xpath = "//input[@placeholder='Ex. Content']")
	private WebElement neartxtbox;

	@FindBy(xpath = "(//span[contains(text(),'Advanced Term Search')])[2]")
	private WebElement advancetermsearchbutton;

	@FindBy(xpath = "(//p[@class='mb-0 font-weight-bold'])[2]")
	private WebElement allofwordstxt;

	@FindBy(xpath = "(//p[@class='mb-0 font-weight-bold'])[3]")
	private WebElement anyofwordtxt;

	@FindBy(xpath = "(//p[@class='mb-0 font-weight-bold'])[4]")
	private WebElement nonofwordtxt;

	@FindBy(xpath = "//h1[@class='primary--text font-weight-regular title']")
	private WebElement pageHeading;

	@FindBy(xpath = "//span[contains(text(),'Clear Search')]")
	private WebElement clearsearch;

	@FindBy(xpath = "//span[contains(text(),'Save Search')]")
	private WebElement savesearch;

	@FindBy(id = "searchTerm")
	private WebElement nearsearch;

	@FindBy(id = "withinTerm")
	private WebElement near;

	@FindBy(xpath = "//button[text()='close']")
	private WebElement closesearch;

	@FindBy(xpath = "//span[contains(text(),'Show Relevant Titles')]")
	private WebElement showrelevanttitles;

	@FindBy(xpath = "(//a[@class='accent--text']/p)[1]")
	private WebElement releveanttitles;

	@FindBy(xpath = "(//div[@class=\"title-card-hover\"]//button)[1]")
	private WebElement favoriteicon;
	
	@FindBy(xpath = "//button[text()='favorite']")
	private WebElement unfavoriteicon;

	@FindBy(xpath = "//div[@class='col col-auto']/p[@class='mb-0 caption']")
	private WebElement totalresults;

	@FindBy(xpath = "//input[@placeholder='Saved search label']")
	private WebElement savesearchtextbox;

	@FindBy(xpath = "//button[@class='v-btn v-btn--contained theme--light v-size--default primary']")
	private WebElement savebutton;

	@FindBy(xpath = "//div[@class='v-list-item__title caption']")
	private WebElement savesearchtext;

	@FindBy(xpath = "//i[text()='delete']")
	private WebElement deleteicon;

	@FindBy(xpath = "//span[contains(text(),'Confirm')]")
	private WebElement conformbutton;

	@FindBy(xpath = "//div[@class='mt-5']//p")
	private WebElement nosearchtext;

	@FindBy(xpath = "//div[@class='col col-1']//div[@class='v-input__icon v-input__icon--append']")
	private WebElement viconShowing;

	@FindBy(xpath = "//div[@class='col col-1']//div[@class='v-select__selections']")
	private WebElement countofresult;

	@FindBy(xpath = "//div[@role='option']")
	private List<WebElement> list;

	@FindBy(xpath = "//i[@class='v-icon notranslate mdi mdi-close theme--light']")
	private WebElement closerelevanttitles;
	
	
	//Search Suggestions
	
	@FindBy(xpath = "//div[@class='overlay-container']//div[@class='v-list-item__title']")
	private List<WebElement> searchsuggetions;
	
	@FindBy(xpath = "(//a[@tabindex='0'])[1]")
	private WebElement suggestionstitles;
	
	@FindBy(xpath = "(//div[@class='row grey lighten-3 row--dense']//i)[1]")
	private WebElement seealltext;

	public void search(String text) {
		searchInputBox.clear();
		sendKeys(searchInputBox, text);

	}

	public void pressEnter() {
		searchInputBox.sendKeys(Keys.ENTER);
	}

	public String clickAdvanceSearch() {
		
		click(advanceSearchButton);
		return getText(advanceSearchButton);
	}

	public boolean titleNameDisplyed(String titlename) {
		boolean title = false;
		for (WebElement webElement : titleNames) {
			if (getText(webElement).equals(titlename)) {
				return title = true;
			} else {
				return title = false;
			}
		}

		return title;
	}

	public String getTitleName() {
		return getText(titleNames.get(0));
	}

	public String getSearchResultText(String search) {
		String text=null;
		List<String> longtext=new ArrayList<String>();
     if (search.split("\\s").length==1) {
    	 text=getText(resulttext.get(0));
     }
     else {
    	 for (int i=0;i<=2;i++) {
    		
    		 longtext.add(getText(resulttext.get(i)));
    		 if (String.join(" ", longtext).equalsIgnoreCase(search)) {
    			 text= String.join(" ", longtext);
    			 break;
    		 }
    	 }
    	 
     }
		return text;
	}

	public String getSearchText() {
		return getText(searchtext).replaceAll("\"", "");
	}

	public List<String> getFilters() {
		List<String> text = new ArrayList<>();
		for (WebElement webElement : filters) {
			text.add(getText(webElement));
		}
		return text;
	}

	public void clickPlusButton(String filter) {
		for (int i = 0; i < filterName.size(); i++) {
			if (getText(filterName.get(i)).equalsIgnoreCase(filter)) {
				click(plusbutton.get(i));
                break;
				
			}
		}
	}
	
	public void clickMinusButtton(String filter) {
		for (int i = 0; i < filterName.size(); i++) {
			if (getText(filterName.get(i)).equalsIgnoreCase(filter)) {
				click(minusbutton.get(i));
                break;
				
			}
		}
	}
	
	public void applyFilterOnCategories(String filtercategory) {
		for (int j = 0; j < categoriesfilter.size(); j++) {
			if (getText(categoriesfilter.get(j)).equalsIgnoreCase(filtercategory)) {
				click(checkbox.get(j));
				break;
			}
		}

	}
	
	public void applyFilterOnYears(String year) {
		for (int j = 0; j < yearfilter.size(); j++) {
			if (getText(yearfilter.get(j)).equalsIgnoreCase(year)) {
				click(yearfilter.get(j));
				break;
			}
		}
	}
	
	public void applyFilterOnMyPremiumTitles(String premium) {
		for (int j = 0; j < premiumtitlesfilter.size(); j++) {
			if (getText(premiumtitlesfilter.get(j)).equalsIgnoreCase(premium)) {
				click(premiumtitlesfilter.get(j));
				break;
			}
		}
	}
	
	public void applyFilterOnContentType(String contenttype) {
		for (int j = 0; j < contenttypefilter.size(); j++) {
			if (getText(contenttypefilter.get(j)).equalsIgnoreCase(contenttype)) {
				click(contenttypefilter.get(j));
				break;
			}
		}
	}

	public String checkbox(String filtertext) throws Exception {
		String text = null;
		for (WebElement webElement : checkboxText) {
			if (getText(webElement).equalsIgnoreCase(filtertext)) {
				text = getText(webElement);
				break;
			} else {
				throw new Exception("Filter " + filtertext + "not present");
			}
		}
		return text;
	}

	public void clickCloseFilter() {
		click(closefilter);
	}

	public void clickJumptoSection() {
		click(jumpbutton);
	}

	public String copyThisSection() {
		click(copybutton);
		return getText(copystatus);
	}

	public void clickPrint() {
		click(printbutton);
	}

	public void clickShareSection() {
		click(linkbutton);
	}

	public void clickBookmark() {
		click(bookmarkbutton);
	}

	public String getSubtitleText() {
		return getText(subtitle.get(0));
	}

	public int getCountofResultOnpage() {
		return subtitle.size();
	}

	public void clickAdvanceTermSearch() {
		Baseclass.scrollUptoElement(pageHeading);
		Baseclass.action.click(advancedtermsearch).build().perform();
	}

	public void AdvanceTermSearch(String allofwords, String anyofwords, String noneofwords) {

		sendKeys(allofwordstxtbox, allofwords);
		sendKeys(anyofwordstxtbox, anyofwords);
		sendKeys(noneofwordstxtbox, noneofwords);
		click(advancetermsearchbutton);
	}

	public String getAllofWords() {
		return getText(allofwordstxt).replaceAll("\"", "");
	}

	public String getAnyofWords() {
		return getText(anyofwordtxt).replaceAll("\"", "");
	}

	public String getNoneofWords() {
		return getText(nonofwordtxt).replaceAll("\"", "");
	}

	public void clickClearSearch() {
		click(clearsearch);
	}

	public void nearSearch(String nearsearchtext, String neartext) {
		sendKeys(nearsearch, nearsearchtext);
		sendKeys(near, neartext);
		click(advancetermsearchbutton);
	}

	

	public void clickSaveSearch() {
		click(savesearch);
	}

	public void clickShowRelevantTitles() {
		Baseclass.scrollUptoElement(pageHeading);
		click(showrelevanttitles);
	}

	public String getRelevantTitle() {
		return getText(releveanttitles);
	}

	public String getofResults() {
		return getText(totalresults);
	}

	public String saveSearch(String savesearch) {
		sendKeys(savesearchtextbox, savesearch);
		click(savebutton);
		return getText(savesearchtext);
	}

	public void clickSaveSearchText() {
		click(savesearchtext);
	}

	public void deleteSaveSearch() {
		click(deleteicon);
		click(conformbutton);
	}

	public String getNoSaveSearchText() {
		return getText(nosearchtext);
	}

	public void clickFavorite() throws Exception {
		if (getText(favoriteicon).equals("favorite_border")) {
		click(favoriteicon);
		}
		else {
			throw new Exception("Title is already marked as favorite");
		}
	}
	public void clickUnfavorite() {
		click(unfavoriteicon);
	}

	public int getCount() {
		Baseclass.scrollUptoElement(pageHeading);
		return Integer.parseInt(getText(countofresult));
	}

	public void selectCount(int count) {
		Baseclass.scrollUptoElement(pageHeading);
		click(viconShowing);
		for (WebElement webElement : list) {
			if (Integer.parseInt(getText(webElement)) == count) {
				click(webElement);
				break;
			}
		}
	}

	public void closeRelevantTitles() {
		click(closerelevanttitles);
	}
	
	
	public boolean getSearchSuggetions(String searchtext) {
		boolean result=false;
		for (WebElement webElement : searchsuggetions) {
			for (String text : getText(webElement).split(" ")) {
				if (text.equalsIgnoreCase(searchtext)) {
					result=true;
					break;
				}
				else {
					result=false;
				}
				
			}
		}
		return result;
	}
	
	public String getSearchSuggetions() throws Exception {
		if (isDisplayed(suggestionstitles)) {
			return getText(suggestionstitles);
		}
		else {
			throw new Exception("Title Name not showing");
		}
	}
	
	public String getSeeAlltext() {
		return getText(seealltext);
	}
	
	public void clickSeeAll() {
		click(seealltext);
	}
	
	
	
}
