package com.acmebicycle.commandlineinterface;

import java.util.List;
import java.util.Scanner;

import com.acmebicycle.regex.BikeReader;
import com.acmebicycle.scraper.WebScraper;

public class Application1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = args[0];
		System.out.println("Writing to this file: " + filePath);
		int userChoice = Integer.MIN_VALUE;
		List<String> brands = getBrands();
		while (true)
		{
			System.out.println("Please choose a brand number from the following:-");
			for(int i=0; i<brands.size(); i++)
			{
				System.out.println(i + ": " + distilBrandName(brands.get(i)));
			}
	        Scanner s = new Scanner(System.in);
	        String brandNumber = s.nextLine();
	        
	        try 
	        {
	        	userChoice = Integer.parseInt(brandNumber);
	        }
	        catch (NumberFormatException nfe)
	        {
	        	System.out.println("Erronous input. Please enter the number of the brand.");
	        	continue;
	        }
	        
	        if (userChoice >= 0 && userChoice < brands.size())
	        	break;
	        else
	        	System.out.println("Please enter a number of a brand between (0 - " + (brands.size() - 1) + ")");
		}
		generateBrandFile(brands.get(userChoice), filePath);
	}
	
	private static void generateBrandFile(String link, String filePath) 
	{
		String modelsLink = link + "2010-" + distilBrandName(link);
		String modelsPage = WebScraper.get(modelsLink);
		List<String> brandYear = WebScraper.match("href=\"(http://bikereviews\\.com/road-bikes/[\\w/-]+)\"(>[\\s\\w-]+</a>[<br|</p]| title=\"[\\s\\w]+)", 0, modelsPage);
		//System.out.println(brandYear);
		BikeReader bkReader = new BikeReader();
		BikeReader.setFilePath(filePath);
		bkReader.setModelLinks(brandYear);
		bkReader.read();
	}
	
	public static List<String> getBrands()
	{
		String w = WebScraper.get("http://bikereviews.com/road-bikes/");
		return WebScraper.match("title=\"[\\w\\s]+\" href=\"(http://bikereviews\\.com/road-bikes/[\\w([-|\\w])?\\s]+/)\"><img", 0, w);
	}
	
	public static String distilBrandName(String brandSrape)
	{
		return brandSrape.substring(brandSrape.indexOf("road-bikes/") + "road-bikes/".length(), brandSrape.lastIndexOf("/"));
	}
}
