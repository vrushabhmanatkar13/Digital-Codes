package com.Digitalcodes.capabilities;



import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxOptions;



public class SetCapbilites {

	// this was deprecated
	/*
	 * public static DesiredCapabilities setCapabilities(String browserName) {
	 * DesiredCapabilities capabilities = new DesiredCapabilities(browserName,
	 * "latest", Platform.ANY);
	 * 
	 * capabilities.setCapability("platformVersion", "10");
	 * capabilities.setCapability("browserName", browserName);
	 * capabilities.setCapability("location", "US East"); return capabilities;
	 * 
	 * }
	 */

	public static ChromeOptions getChromecapabalites(String incognito, String headless) {

		ChromeOptions chrome = new ChromeOptions();

		if (incognito.equalsIgnoreCase("true")) {
			chrome.addArguments("--incognito");
		}
		if (headless.equalsIgnoreCase("true")) {
			chrome.addArguments("--headless=new");
			chrome.addArguments("--enable-automation");
		}

		chrome.addArguments("--remote-allow-origins=*");
		chrome.addArguments("force-device-scale-factor=0.80");
		chrome.addArguments("high-dpi-support=0.80");
		
		return chrome;
	}

	public static FirefoxOptions getFirefoxcapabalites(String incognito, String headless) {

		FirefoxOptions firefox = new FirefoxOptions();
		if (incognito.equalsIgnoreCase("true")) {
			firefox.addArguments("--incognito");
		}
		if (headless.equalsIgnoreCase("true")) {
			firefox.addArguments("--headless=new");
            firefox.addArguments("--enable-automation");
		}
		firefox.addArguments("force-device-scale-factor=0.85");
		firefox.addArguments("high-dpi-support=0.85");
		
		return firefox;
	}
	
	
	public static void  getlamdatest(ChromeOptions options) {
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "vrushabhmanatkar13");
		ltOptions.put("accessKey", "Cmg7QXWZSREFgaPboVGTkCH0m6Rtkq859gKMW60In46IhhIQ8K");
		ltOptions.put("geoLocation", "US");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "Digital Codes");
		ltOptions.put("project", "Digital Codes");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");
		options.setCapability("LT:Options", ltOptions);
	}

}
