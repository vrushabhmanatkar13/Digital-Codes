package com.Digitalcodes.Util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TakeScreenshot extends Baseclass{
	
	public static String Take_screenshot() throws IOException {
		String Dateformat=new SimpleDateFormat("YYYYMMDDHHMM").format(new Date());
		TakesScreenshot screenshot= (TakesScreenshot) driver;
		
		File src=screenshot.getScreenshotAs(OutputType.FILE);
		
		String path=System.getProperty("user.dir")+"\\Screenshot\\"+Dateformat+".png";
		File dest=new File(path);
		FileUtils.copyFile(src, dest);
		return  path;
	}

}
