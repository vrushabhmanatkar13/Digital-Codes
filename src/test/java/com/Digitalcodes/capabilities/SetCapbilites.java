package com.Digitalcodes.capabilities;


import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.remote.DesiredCapabilities;

public class SetCapbilites {

	// this was deprecated
	public static DesiredCapabilities setCapabilities(String browserName) {
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "latest", Platform.ANY);

		capabilities.setCapability("platformVersion", "10");
		capabilities.setCapability("browserName", browserName);
		capabilities.setCapability("location", "US East");
		return capabilities;

	}

	public static ChromeOptions getChromecapabalites(String incognito, String headless) {

		ChromeOptions chrome = new ChromeOptions();

		if (incognito.equalsIgnoreCase("true")) {
			chrome.addArguments("--incognito");
		}
		if (headless.equalsIgnoreCase("true")) {
			chrome.addArguments("-headless");
		}

		chrome.addArguments("--remote-allow-origins=*");
		//chrome.setPlatformName("Windows");
		//chrome.setBrowserVersion("latest");
		// chrome.merge(setCapabilities(browserName));
		return chrome;
	}

	public static FirefoxOptions getFirefoxcapabalites(String incognito, String headless) {

		FirefoxOptions firefox = new FirefoxOptions();
		if (incognito.equalsIgnoreCase("true")) {
			firefox.addArguments("--incognito");
		}
		if (headless.equalsIgnoreCase("true")) {
			firefox.addArguments("-headless");

		}
		firefox.addArguments("--remote-allow-origins=*");
		//firefox.setPlatformName("Windows");
		//firefox.setBrowserVersion("latest");

		// firefox.merge(setCapabilities(browserName));
		return firefox;
	}

}
