package com.Digitalcodes;



import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.remote.DesiredCapabilities;

import perfecto_cloud.PerfectoLabUtils;

public class SetCapbilites {
    

public static ChromeOptions getChromecapabalites(String incognito,String headless,String browserName,String version) {
	
	DesiredCapabilities capabilities = new DesiredCapabilities(browserName, version, Platform.ANY);
	

	ChromeOptions	chrome=new ChromeOptions();
	if (incognito.equalsIgnoreCase("true")) {
		chrome.addArguments("--incognito");
	}
	if (headless.equalsIgnoreCase("true")) {
		chrome.addArguments("-headless");
	}
	capabilities.setCapability("platformName", "Windows");
	capabilities.setCapability("platformVersion", "latest");
	capabilities.setCapability("browserName", browserName);
	capabilities.setCapability("browserVersion", version);
	capabilities.setCapability("location", "US East");
	capabilities.setCapability("resolution", "1024x768");
	
	chrome.merge(capabilities);
	return chrome;
}



public static FirefoxOptions getFirefoxcapabalites(String incognito,String headless,String browserName,String version) {
	
	DesiredCapabilities capabilities = new DesiredCapabilities(browserName, version, Platform.ANY);
	
	FirefoxOptions	firefox=new FirefoxOptions();
		if (incognito.equalsIgnoreCase("true")) {
			firefox.addArguments("--incognito");
		}
		if (headless.equalsIgnoreCase("true")) {
			firefox.addArguments("-headless");
		}
		capabilities.setCapability("platformName", "Windows");
		capabilities.setCapability("platformVersion", "latest");
		capabilities.setCapability("browserName", browserName);
		capabilities.setCapability("browserVersion", version);
		capabilities.setCapability("location", "US East");
		capabilities.setCapability("resolution", "1024x768");
		
	
    firefox.merge(capabilities);
	return firefox;
}


public static DesiredCapabilities getPerfectocapablites(String browserName1,String version,String securityToken) throws Exception {
	DesiredCapabilities capabilities = new DesiredCapabilities(browserName1, version, Platform.ANY);
	capabilities.setCapability("platformName", browserName1);
	capabilities.setCapability("platformVersion", version);
	capabilities.setCapability("browserName", browserName1);
	capabilities.setCapability("browserVersion", "latest");
	capabilities.setCapability("location", "US East");
	capabilities.setCapability("resolution", "1024x768");

	// The below capability is mandatory. Please do not replace it.
	capabilities.setCapability("securityToken", PerfectoLabUtils.fetchSecurityToken(securityToken));
	return capabilities;
}

}
