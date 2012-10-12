package com.acmebicycle.transform;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Use the TraX interface to perform a transformation in the simplest manner
 * possible (3 statements).
 */
public class BikeTransform {

	private String xslSource;
	private String xmlSource;
	private String fileoutName;

	public BikeTransform(String xslSource, String xmlSource, String fileoutName) {
		this.xslSource = xslSource;
		this.xmlSource = xmlSource;
		this.fileoutName = fileoutName;
	}

	public BikeTransform(String xslSource, String xmlSource) {
		this.xslSource = xslSource;
		this.xmlSource = xmlSource;
		this.fileoutName = "output.html";
	}

	public String getXslSource() {
		return xslSource;
	}

	public void setXslSource(String xslSource) {
		this.xslSource = xslSource;
	}

	public String getXmlSource() {
		return xmlSource;
	}

	public void setXmlSource(String xmlSource) {
		this.xmlSource = xmlSource;
	}

	public String getFileoutName() {
		return fileoutName;
	}

	public void setFileoutName(String fileoutName) {
		this.fileoutName = fileoutName;
	}

	public String transform() throws TransformerException,
			TransformerConfigurationException, FileNotFoundException,
			IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer(new StreamSource(
				xslSource));
		transformer.transform(new StreamSource(xmlSource), new StreamResult(
				System.out));
		transformer.transform(new StreamSource(xmlSource), new StreamResult(
				baos));
		return baos.toString();
	}

	public void transform(String brandName, String filePath) throws TransformerException,
			TransformerConfigurationException, FileNotFoundException,
			IOException {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer(new StreamSource(
				xslSource));
		transformer.setParameter("brandName", brandName);
		transformer.transform(new StreamSource(xmlSource), new StreamResult(
				new FileOutputStream(filePath + fileoutName)));
	}


}
