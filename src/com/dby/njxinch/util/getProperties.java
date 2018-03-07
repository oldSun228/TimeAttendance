package com.dby.njxinch.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class getProperties {
	private static Properties config = null;

	static {
		InputStream in = getProperties.class.getClassLoader()
				.getResourceAsStream("commonContext.properties");
		config = new Properties();
		try {
			config.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("No AreaPhone.properties defined error");
		}
	}
	
	 public static String readValue(String key) {
		  // Properties props = new Properties();
		  try {
		   String value = config.getProperty(key);
		   return value;
		  } catch (Exception e) {
		   e.printStackTrace();
		   System.err.println("ConfigInfoError" + e.toString());
		   return null;
		  }
		 }
}
