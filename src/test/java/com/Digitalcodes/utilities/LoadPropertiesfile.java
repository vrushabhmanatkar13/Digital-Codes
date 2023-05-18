package com.Digitalcodes.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class LoadPropertiesfile {

	//private XSSFSheet sheet=null;

	public Properties load_properties() throws Exception {

		Properties prop = new Properties();
		prop.load(new FileInputStream(new File(System.getProperty("user.dir")+"\\TestData\\Config.properties")));
		return prop;
	}
	
	public JsonNode readJson(String JsonFilepath) throws IOException {
		
 
	   ObjectMapper mapper=new ObjectMapper();
	   JsonNode node =mapper.readTree(new File(JsonFilepath));
	    return node;
	
	}
}
