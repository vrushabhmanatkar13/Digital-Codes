package com.Digitalcodes.utilities;

import java.time.Duration;


import java.util.Properties;
import java.util.Set;

import com.Digitalcodes.capabilities.SetCapbilites;

import com.Digitalcodes.perfectocloud.Perfecto_Capabailites;
import com.Digitalcodes.testcases.Prerequisites_Teardown;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass extends Perfecto_Capabailites{
	
	
	public static Actions action;
	private static String Win_id;
    public static WebDriverWait wait;
	public static Perfecto_Capabailites cap;
	
	public static WebDriver driver;
	
	//---------------Launch Browser with capability--------------->>
	public WebDriver browserLaunch(String incognito,String headless,String browserName,String platform, String securityToken,String cloudName, String tag, String resolution, String width, String hight) throws Exception {

		
		 if (platform.equalsIgnoreCase("Perfecto")) {
				cap=new com.Digitalcodes.perfectocloud.Perfecto_Capabailites();
				driver=cap.Perfecto(browserName, securityToken, cloudName, tag,incognito,headless,resolution);
				driver.manage().window().maximize();
		       action=new Actions(driver);
			 return driver;
			 }
		 
				else {
					if (browserName.equalsIgnoreCase("Chrome")) {
						WebDriverManager.chromedriver().setup();
						ChromeOptions chromeoption=SetCapbilites.getChromecapabalites(incognito, headless);
						WebDriver Cdriver = new ChromeDriver(chromeoption);
						driver = Cdriver;

					}

					else if (browserName.equalsIgnoreCase("firefox")) {
						WebDriverManager.firefoxdriver().setup();

						WebDriver Fdriver = new FirefoxDriver(SetCapbilites.getFirefoxcapabalites(incognito, headless));
					
						driver = Fdriver;
					}
					driver.manage().window().setSize(setDimension(width, hight));
					driver.manage().window().maximize();
					
					action = new Actions(driver);
					return driver;
				}
		
		     
		
		

	}

	/*
	 * Fatech Platform Name As local Or Perfecto
	*/
	public static String fatechPlatformName(Properties prop) throws Exception {
		String platform=System.getProperty("PlatformName");

		if (platform==null) {
			return prop.getProperty("PlatformName");
		}
		else {
			return platform;
		}
		
	}
	
	public static String fatechBrowserName(Properties prop) throws Exception {
		String finalBrowser= System.getProperty("BrowserName");
		if (finalBrowser==null) {
			return prop.getProperty("BrowserName");
		}
		else {
			return finalBrowser;
		}
		
		
	}
	
	public static String getTitle() {
		
		return driver.getTitle();
	}

	// Implicit wait
	public void implicitWait(long sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
		
	}
	public void pageLoadTimeout(long sec) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(sec));
	}
	
	
	//Webdriver Wait - Explicite wait 
	public void waitForElement(long sec) {
		wait=new WebDriverWait(driver, Duration.ofSeconds(sec));
	}

	// navigate to Url
	public  void navigateToUrl(String url) {
		driver.get(url);
		//driver.manage().window().maximize();
	}

	public static void refreshBrowser() {
		driver.navigate().refresh();
	}
 
	// close browser
	public static void closeWindow() {
		driver.close();
	}
	
	
    //navigate to back
	public static void navigateToBack() {
		driver.navigate().back();
	}
	 public static Dimension setDimension(String width,String hight) {
		 
		 Dimension resolution=new Dimension(Integer.valueOf(width), Integer.valueOf(hight));
		 return resolution;
	 }
	
	
    //scroll upto element
	public static void scrollUptoElement(WebElement element) {
		JavascriptExecutor js_exe = (JavascriptExecutor) driver;
		js_exe.executeScript("arguments[0].scrollIntoView(true)", element);
		
	}
 //------------------------switch to new tab------------------------------>>
	
	public static void switchToWindow() throws Exception {
		Win_id = driver.getWindowHandle();
		
		Set<String> next_win = driver.getWindowHandles();
		for (String string : next_win) {
			if(!string.equals(Win_id)) {

				driver.switchTo().window(string);
				break;
			}
		}
		
		
	}

	//----------return back to main window------>>
	public static void retrunToMainWindow() {
		driver.switchTo().window(Win_id);
	}
	
	//-----Handle alert------------------------->>
	public static Alert handleAlert() {	
		Alert alert=driver.switchTo().alert();
		return alert;
	}

	public void click(WebElement e) {
		wait.until(ExpectedConditions.visibilityOf(e));
		wait.until(ExpectedConditions.elementToBeClickable(e));
		e.click();
	}
	
	public void sendKeys(WebElement e,String s) {
		wait.until(ExpectedConditions.visibilityOf(e));
		wait.until(ExpectedConditions.elementToBeClickable(e));
		e.clear();
		e.sendKeys(s);
	}
	
	public String getText(WebElement e) {
		wait.until(ExpectedConditions.visibilityOf(e));
		return e.getText();
	}
	
	public static String jsonValue(String key) {
		return Prerequisites_Teardown.json.get(key).asText();
	}
	
	public static String jsonArrayValue(String parentkey, String key) {
		return Prerequisites_Teardown.json.get(parentkey).get(key).asText();
	}
	
	public static void assertEquals(String actual,String expecated) {
		Perfecto_Capabailites.AssertEquels(actual, expecated);
		Assert.assertEquals(actual,expecated);
	}
	public static void assertEquals(int actual,int expecated) {
		Perfecto_Capabailites.AssertEquels(actual, expecated);
		Assert.assertEquals(actual,expecated);
	}
	
	public static void assertTrue(boolean Booleanvalue) {
		Assert.assertTrue(Booleanvalue);
		Perfecto_Capabailites.Assert("", Booleanvalue);
	}
	public static void assertFalse(boolean booleanvalue) {
		Assert.assertFalse(booleanvalue);
		Perfecto_Capabailites.Assert("", booleanvalue);
	}
	
	public static boolean isDisplayed(WebElement e) {
		wait.until(ExpectedConditions.visibilityOfAllElements(e));
		return e.isDisplayed();
	}
	
	
	
}
