package com.Digitalcodes.utilities;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import com.Digitalcodes.capabilities.SetCapbilites;
import com.Digitalcodes.perfectocloud.Perfecto_Capabailites;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass extends Perfecto_Capabailites{
	
	
	public static Actions action;
	private static String Win_id;
    public static WebDriverWait wait;
	public static Perfecto_Capabailites cap;
	
	public static WebDriver driver;
	
	//---------------Launch Browser with capability--------------->>
	public WebDriver browserLaunch(String incognito,String headless,String browserName,String platform, String securityToken,String cloudName, String tag) throws Exception {

		
		 if (platform.equalsIgnoreCase("Perfecto")) {
				cap=new com.Digitalcodes.perfectocloud.Perfecto_Capabailites();
				driver=cap.Perfecto(browserName, securityToken, cloudName, tag);
		       action=new Actions(driver);
			 return driver;
			 }
		 
				else {
					if (browserName.equalsIgnoreCase("Chrome")) {
						WebDriverManager.chromedriver().setup();
						WebDriver Cdriver = new ChromeDriver(
								SetCapbilites.getChromecapabalites(incognito, headless, browserName));
						System.out.println(browserName + "Browser launched");
						driver = Cdriver;

					}

					else if (browserName.equalsIgnoreCase("firefox")) {
						WebDriverManager.firefoxdriver().setup();

						WebDriver Fdriver = new FirefoxDriver(
								SetCapbilites.getFirefoxcapabalites(incognito, headless, browserName));
						System.out.println(browserName + " Browser launched");
						driver = Fdriver;
					}
					driver.manage().window().maximize();
					action = new Actions(driver);
					return driver;
				}
		
		     
		
		

	}

	// Implicit wait
	public void implicitWait(long sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	//	driver.manage().timeouts().pageLoad
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
	public void closeBrowser() {
		driver.close();
	}
	
	
    //navigate to back
	public static void navigateToBack() {
		driver.navigate().back();
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
		Iterator<String> itr = next_win.iterator();
		while (itr.hasNext()) {
			String Win_id1 = itr.next();
			if (!Win_id1.equals(Win_id)) {
				driver.switchTo().window(Win_id1);
			} else {
				throw new Exception("New Window not opened");
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

	
	
	
	
	
}
