package com.Digitalcodes.Util;

import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;


import com.Digitalcodes.SetCapbilites;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import perfecto_cloud.PerfectoLabUtils;
import perfecto_cloud.Perfecto_Capabailites;

public class Baseclass extends Perfecto_Capabailites{
	
	
	public static Actions action;
	private static String Win_id;
    
	public static Perfecto_Capabailites cap;
	
	public static WebDriver driver;
	
	//---------------Launch Browser with capability--------------->>
	public static WebDriver browserlaunch(String incognito,String headless,String version,String browserName,String platform, String securityToken,String cloudName, String tag) throws Exception {

		
		 if (platform.equalsIgnoreCase("Perfecto")) {
				cap=new perfecto_cloud.Perfecto_Capabailites();
		        driver=cap.Perfecto(browserName, securityToken, cloudName, tag);
		       action=new Actions(driver);
			 return driver;
			 }
		 
		 else {
			 if (browserName.equalsIgnoreCase("Chrome")) {
					WebDriverManager.chromedriver().setup();
					WebDriver Cdriver = new ChromeDriver(SetCapbilites.getChromecapabalites(incognito, headless,browserName,version));
					System.out.println(browserName+ "Browser launched");
		         driver=Cdriver;
		        
				}

				else if (browserName.equalsIgnoreCase("firefox")) {
					WebDriverManager.firefoxdriver().setup();
		            
					WebDriver Fdriver = new FirefoxDriver(SetCapbilites.getFirefoxcapabalites(incognito, headless, browserName, version));
					System.out.println(browserName+ " Browser launched");
					driver=Fdriver;
				}
			 driver.manage().window().maximize();
			action=new Actions(driver);
			 return driver;
		 }
		

		
		

	}

	// Implicit wait
	public static void Implicitwait(long sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(sec));
	}

	// navigate to Url
	public static void Navigateurl(String url) {
		driver.navigate().to(url);
		//driver.manage().window().maximize();
	}

 
	// close browser
	public static void closebrowser() {
		driver.close();
	}
    //navigate to back
	
	public static void nav_back() {
		driver.navigate().back();
	}
    //scroll upto element
	public static void scroll(WebElement element) {
		JavascriptExecutor js_exe = (JavascriptExecutor) driver;
		js_exe.executeScript("arguments[0].scrollIntoView(true)", element);
		
	}
 //------------------------switch to new tab------------------------------>>
	
	public static void nav_window() throws Exception {
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
	public static void retrun_window() {
		driver.switchTo().window(Win_id);
	}
	
	//-----Handle alert------------------------->>
	public static Alert Alert() {	
		Alert alert=driver.switchTo().alert();
		return alert;
		
		
	}

	public static void mouse_Over(WebElement element) {
         action.moveToElement(element);
	}
	
	
	
	
}
