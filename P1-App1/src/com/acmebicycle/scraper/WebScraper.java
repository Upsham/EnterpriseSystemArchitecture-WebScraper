package com.acmebicycle.scraper;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraper {
	public static String get(String url)
	{
		String content = null;
		URLConnection connection = null;
		try {
		  connection =  new URL(url).openConnection();
		  Scanner scanner = new Scanner(connection.getInputStream());
		  scanner.useDelimiter("\\Z");
		  content = scanner.next();
		  scanner.close();
		}catch ( Exception ex ) {
		    ex.printStackTrace();
		}
		return content;
	}
	
	public static List<String> match(String expr, int flags, String search)
	{
		Pattern patt = Pattern.compile(expr);
		Matcher m = patt.matcher(search);
		List<String> list = new LinkedList<String>();
		while (m.find()) {
		  list.add(m.group(1));
		}
		return list;
	}
	
	public static String matchGroup(String expr, String search) {
		Pattern patt = Pattern.compile(expr);
		Matcher m = patt.matcher(search);
		if (m.find()) {
			String grouped = m.group();
			return grouped;
		}
		return null;
	}
	
	public static ArrayList<String> matchGroups(String expr, String search) {
		Pattern patt = Pattern.compile(expr);
		Matcher m = patt.matcher(search);
		ArrayList<String> arr = new ArrayList<String>(); 
		while (m.find()) {
			//String grouped = m.group();
			arr.add(m.group());
			//return grouped;
		}
		
		return arr;
	}
}
