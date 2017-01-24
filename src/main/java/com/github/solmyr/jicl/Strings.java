package com.github.solmyr.jicl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class Strings {
	private final static String DEFAULT_STRINGS_FILE_PATH = "strings.properties";
	
	private Map<String, String> strings = new HashMap<String, String>();

	private static Strings instance;

	private Strings() {
	}

	private static Strings getInstance() {
		if (instance == null) {
			instance = new Strings();
		}

		return instance;
	}

	public static void init(CommandLineConfig clc) {
		Strings.getInstance().loadFile(DEFAULT_STRINGS_FILE_PATH);
		Strings.getInstance().loadFile(clc.getStringsFileName());
		Strings.getInstance().writeSpecific(clc.getStrings());
	}

	private void writeSpecific(Map<String, String> specificStrings) {
		for (Entry<String, String> entry : specificStrings.entrySet()) {
			strings.put(entry.getKey(), entry.getValue());
		}
	}

	private void loadFile(String stringsFileName) {
		if(stringsFileName == null) return;
		
		try (InputStream input = getClass().getClassLoader().getResourceAsStream(stringsFileName)) {
			Properties prop = new Properties();
			prop.load(input);
			Enumeration<?> e = prop.propertyNames();

			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				strings.put(key, prop.getProperty(key));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String m(StringsKey key) {
		return Strings.getInstance().getString(key);
	}

	public static String m(String key) {
		return Strings.getInstance().getString(key);
	}

	private String getString(String key) {
		if (strings.containsKey(key))
			return strings.get(key);
		else
			return key;
	}

	private String getString(StringsKey key) {
		return getString(key.getKey());
	}

}
