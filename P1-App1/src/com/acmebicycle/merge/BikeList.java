package com.acmebicycle.merge;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.acmebicycle.brand.Brand;


@XmlRootElement(namespace = "")
@XmlType(propOrder = { "brand"})
public class BikeList {

  private ArrayList<Brand> brand;

  public void setBrand(ArrayList<Brand> brand) {
    this.brand = brand;
  }

  public ArrayList<Brand> getBrand() {
	    return brand;
	  }
} 