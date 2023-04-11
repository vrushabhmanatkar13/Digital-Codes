package com.Digitalcodes.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class LoadPropertiesfile {

	//private XSSFSheet sheet=null;

	public Properties load_properties() throws Exception {

		Properties prop = new Properties();
		prop.load(new FileInputStream(new File(System.getProperty("user.dir")+"\\TestData\\Config.properties")));
		return prop;
	}
	
   
	

}
