package com.Digitalcodes.Util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class LoadData {

	//private XSSFSheet sheet=null;

	public Properties load_properties() throws Exception {

		FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\Properties\\Config.properties"));
		Properties prop = new Properties();
		prop.load(file);
		return prop;
	}
	
   
	

}
