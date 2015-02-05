package com.carfinance.api;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class HttpApiService {
	
	private final static Logger logger = Logger.getLogger(HttpApiService.class);
	private final static Properties configuration = readUtf8Properties(HttpApiService.class.getResourceAsStream("/conf/config.cfg"));
	private static final String PUSH_BASE_URL = configuration.getProperty("PUSH_BASE");
	
	private static String GROUP_URL = PUSH_BASE_URL + "/group?flag=%s&type=%s&code=%s&userid=%s&username=%s&groupid=%s&groupname=%s&role=%s&content=%s";

	public static Properties readUtf8Properties(InputStream is) {
		Properties properties = new Properties();
		try {
			properties.load(is);
			for (Object key : properties.keySet()) {
				String value = properties.getProperty(key.toString());
				String goodValue = new String(value.getBytes("iso8859-1") , "utf-8");
				properties.setProperty(key.toString() , goodValue);
			}
			is.close();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		return properties;
	}
}
