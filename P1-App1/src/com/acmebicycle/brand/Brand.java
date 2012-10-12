package com.acmebicycle.brand;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//This statement means that class "Bookstore.java" is the root-element of our example
@XmlRootElement(namespace = "")
@XmlType(propOrder = { "manufacturer", "model"})
public class Brand {

  private ArrayList<Model> model;
  private String manufacturer;

  public void setModel(ArrayList<Model> model) {
    this.model = model;
  }

  public ArrayList<Model> getModel() {
	    return model;
	  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

} 