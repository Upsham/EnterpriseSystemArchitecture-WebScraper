package com.acmebicycle.transform;

import java.util.Scanner;

public class Application3 {

	public static void main(String[] args) {
		String filePath = args[0];
		BikeTransform bkTransform = new BikeTransform("brand.xsl",
				filePath + "unified.xml");
		try {
			String brandsavail = bkTransform.transform();

			System.out.print("Please enter the name of the brand:");
			Scanner s = new Scanner(System.in);
			String brandName = s.nextLine();
			if(brandsavail.contains(brandName))
			{
				bkTransform.setXslSource("model.xsl");
				bkTransform.transform(brandName, filePath);
				System.out.print("output.html generated");
			}
			else
			{
				System.out.print("The input was incorrect. No html was generated. Please enter the exact brand name!");
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}
	
}
