package com.acmebicycle.merge;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.acmebicycle.brand.*;


public class Application2 {

	/**
	 * @param args
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static void main(String[] args) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(com.acmebicycle.brand.Brand.class);
		JAXBContext context2 = JAXBContext.newInstance(BikeList.class);
		Unmarshaller um = context.createUnmarshaller();
		String filePath = args[0];
		ArrayList<Brand> arr = new ArrayList<Brand>();
		List<File> filelist = getXMLFiles(new File(filePath));
//		System.out.println(filelist.toString());
		if(filelist.contains(new File(filePath + "unified.xml"))){
//			System.out.println("removing unified");
			filelist.remove(new File(filePath + "unified.xml"));
		}
		Iterator<File> i = filelist.iterator();
		while (i.hasNext()) {
			Brand brand = (Brand) um.unmarshal(new FileReader(i.next()));
			arr.add(brand);
		}

		BikeList bikel = new BikeList();
		bikel.setBrand(arr);
		Marshaller m = context2.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//		m.marshal(bikel, System.out);

		Writer w = null;
		try {
			w = new OutputStreamWriter(new FileOutputStream(filePath + "unified.xml"),"UTF-8");
			m.marshal(bikel, w);
		} finally {
			try {
				w.close();
			} catch (Exception e) {
			}
		}
	}

	public static List<File> getXMLFiles(File folder) {
		List<File> aList = new ArrayList<File>();

		File[] files = folder.listFiles();
		for (File pf : files) {

			if (pf.isFile() && getFileExtensionName(pf).indexOf("xml") != -1) {
				aList.add(pf);
			}
		}
		return aList;
	}

	public static String getFileExtensionName(File f) {
		if (f.getName().indexOf(".") == -1) {
			return "";
		} else {
			return f.getName().substring(f.getName().length() - 3,
					f.getName().length());
		}
	}

}
