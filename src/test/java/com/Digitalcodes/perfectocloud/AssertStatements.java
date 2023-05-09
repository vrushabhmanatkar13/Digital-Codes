package com.Digitalcodes.perfectocloud;

import org.testng.Assert;

public class AssertStatements {
	
	
	
	public static void assertEquals(String actual, String expecated) {
		Assert.assertEquals(actual, expecated);
		Perfecto_Capabailites.AssertEquels(actual, expecated);
	}
	
	public static void assertBoolean(boolean actual, String message) {
		if (actual) {
		Assert.assertTrue(actual);
		
		}
		else {
			Assert.assertFalse(actual);
		}
		Perfecto_Capabailites.Assert(message, actual);
	}

}
