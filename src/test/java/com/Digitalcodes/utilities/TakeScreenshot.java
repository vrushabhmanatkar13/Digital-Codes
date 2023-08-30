package com.Digitalcodes.utilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class TakeScreenshot extends Baseclass {

	public static String Take_screenshot(ITestResult result) throws IOException {
		// String Dateformat=new SimpleDateFormat("YYYYMMDDHHMM").format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) driver;

		File src = screenshot.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "\\Screenshot\\" + result.getMethod().getMethodName() + ".png";
		File dest = new File(path);
		FileUtils.copyFile(src, dest);
		return path;
	}

	@Attachment(value = "ScreenShot", type = "image/png")
	public static byte[] allure_screenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

}
