package com.acmebicycle.regex;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.acmebicycle.brand.*;
import com.acmebicycle.scraper.WebScraper;

public class BikeReader {
	private static Map<String, String> nodes = new HashMap<String, String>();
	private static Map<String, String> components = new HashMap<String, String>();
	private static List<String> sizeElem = new ArrayList<String>();
	private static ArrayList<Model> models = new ArrayList<Model>();
	private static Brand brand;
	private List<String> modelLinks;
	private static String filePath;
	private String BRAND_XML = null;

	private static final String PRICE = "(\\$\\d+\\,?\\d+?[\\.||<](\\d+[\\.|<|\\s])?)";
	private static final String SIZE = "> Sizes:.+?<br";
	private static final String SIZES = "\\d+";
	private static final String FRAMES = "Frame:.+?<br";
	private static final String FORK = "Fork:.+?<br";
	private static final String COMPONENTS = "(?<=Components</strong><br)(?s:.+|\n+|\r+)(<stro|<script language)";
	private static final String MAX_RATING = "(?<=bestRating\" content=\").+?(?=\")";
	private static final String CURR_RATING = "(?<=ratingValue\" content=\").+?(?=\")";
	private static final String NUM_VOTES = "(?<=ratingCount\" content=\").+?(?=\")";
//	private static String curr_brand;
	public static void main(String[] args) {
		BikeReader bkReader = new BikeReader();
		bkReader.read();
	}

	public void read() {
		//System.out.print(modelLinks.size());
		BikeReader bikeReader = new BikeReader();

		String bike = null;

		// IMPORTANT: Should be removed after verifying integration with
		// CommandLineHandler by invoking setModelLinks(List<String>)
		bikeReader.hardCodeModelLinks();

		if (modelLinks != null && modelLinks.size() > 0) {

			for (int i = 0; i < modelLinks.size(); i++) {
				brand = new Brand();
				//curr_brand = modelLinks.get(i).split("/")[4];
				brand.setManufacturer(modelLinks.get(i).split("/")[4]);

				bike = WebScraper.get(modelLinks.get(i));

				nodes = new HashMap<String, String>();
				nodes.put("year", modelLinks.get(i).split("/")[5].split("-")[0]);
				nodes.put("name",
						modelLinks.get(i).split("/")[6].replaceAll("-", " "));
				String priceinit = WebScraper.matchGroup(PRICE, bike);
				int i2 = 0;
				if(priceinit!=null)
				{
					for(i2 = priceinit.length()-1;(i2>=0)&&(!Character.isDigit(priceinit.charAt(i2)));i2--);
					if(i2!=priceinit.length())
					{
						priceinit = priceinit.substring(0,i2+1);
					}
				}

				nodes.put("price", priceinit);

				parseFrameSet(bike);
				parseComponent(bike);

				nodes.put("maxRating", WebScraper.matchGroup(MAX_RATING, bike));
				nodes.put("currRating",
						WebScraper.matchGroup(CURR_RATING, bike));
				nodes.put("numVotes", WebScraper.matchGroup(NUM_VOTES, bike));

				bikeReader.addModel();

				brand.setModel(models);
				try {
					bikeReader.createXML(brand);
				} catch (Exception ex) {
					System.out.println(ex.getStackTrace());
				}
				brand = null;
			}
		}
	}

	public static void parseFrameSet(String str) {
		String sizes = WebScraper.matchGroup(SIZE, str);
			sizeElem.clear();
		if (null != sizes) {
			ArrayList<String> sizeList = WebScraper.matchGroups(SIZES, sizes);
			sizeElem.addAll(sizeList);
		} else {
			sizeElem.clear();
		}

		String frames = WebScraper.matchGroup(FRAMES, str);
		
		frames = (frames != null) ? frames.split("<br")[0].split(":")[1]
				: frames;
		String fork = WebScraper.matchGroup(FORK, str);
		
		
		fork = (fork != null) ? fork.split("<br")[0].split(":")[1] : fork;

		/*
		 * sizes = (sizes != null) ? sizes.split("<br")[0].split(":")[1] :
		 * sizes; if (null != sizes) { String sizeArr[] = sizes.split(","); for
		 * (int i = 0; i < sizeArr.length; i++) { if (i != sizeArr.length - 1) {
		 * sizeElem.add(sizeArr[i].substring(1)); } else { String lastSize =
		 * sizeArr[i].substring(1, sizeArr[i].length() - 2);
		 * sizeElem.add(lastSize); } } }
		 */
		nodes.put("frames", frames);
		nodes.put("fork", fork);
	}

	public static void parseComponent(String str) {
		String component = WebScraper.matchGroup(COMPONENTS, str);
		if (null != component) {
			int indexOf = component.indexOf("</p>");
			if(indexOf != -1) {
				component = component.substring(0, indexOf);
			}
			if(component.contains("Road Bike"))
			{
				component = component.substring(0,component.indexOf("Road Bike"));
			}
			String comps[] = component.split("<br\\r?\\n/> ");

			String pair[];
			for (String comp : comps) {
				pair = comp.split(":");
				if (pair.length > 1) {
					if(pair[0].startsWith("\n") ) 
					{
						pair[0] = pair[0].substring(4);
					}
					components.put(pair[0], pair[1]);
				}
			}
		}

	}

	public void addModel() {
		Model model = new Model();

		model.setName(nodes.get("name"));
		model.setPrice(nodes.get("price"));
		if (nodes.get("year") != null) {
			model.setYear(Integer.parseInt(nodes.get("year")));
		}

		model.makeFrame();
		model.getFrame().makeMaterial();
		model.getFrame().getMaterial().add(nodes.get("frames"));

		model.getFrame().makeSize();
		for(String size:sizeElem){
		model.getFrame().getSize().add(Integer.parseInt(size));
		}

		model.makeFork();
		model.getFork().makeMaterial();
		model.getFork().getMaterial().add(nodes.get("fork"));

		model.makeComponents(components.size());

		int i = 0;
		Set<Entry<String, String>> entrySet = components.entrySet();
		for (Map.Entry<String, String> entry : entrySet) {
			String name = entry.getKey();
			String value = entry.getValue();
			model.getComponents().get(i).makeComponent();
			model.getComponents().get(i).getName().add(name);
			model.getComponents().get(i).getComponent().add(value);
			i++;
		}

		model.makeRating();
		if (null != nodes.get("maxRating"))
			model.getRating().setMax(Double.valueOf(nodes.get("maxRating")));
		if (null != nodes.get("currRating"))
			model.getRating().setCurrent(
					Double.valueOf(nodes.get("currRating")));
		if (null != nodes.get("numVotes"))
			model.getRating().setVotes(Double.valueOf(nodes.get("numVotes")));

		models.add(model);
	}

	private void createXML(Brand brand) throws JAXBException, IOException {

		BRAND_XML = filePath + "brand-" + brand.getManufacturer() + ".xml";
		JAXBContext context = JAXBContext.newInstance(Brand.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// m.marshal(brand, System.out);

		Writer w = null;
		w = new FileWriter(BRAND_XML);
		m.marshal(brand, w);
		w.close();
		nodes = null;
		brand = null;
	}

	private void hardCodeModelLinks() {
		modelLinks = new ArrayList<String>();
		modelLinks
				.add("http://bikereviews.com/road-bikes/bianchi/2010-bianchi/bianchi-mono-q-ultegra-compact-road-bike/");
		modelLinks
				.add("http://bikereviews.com/road-bikes/schwinn/2010-schwinn/schwinn-fastback-road-bike/");

	}

	public void setModelLinks(List<String> modelLinks) {
		this.modelLinks = modelLinks;
	}
	
	public static void setFilePath(String filePath) {
		BikeReader.filePath = filePath;
	}
}