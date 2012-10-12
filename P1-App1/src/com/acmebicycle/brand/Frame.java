package com.acmebicycle.brand;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "frame")
// If you want you can define the order in which the fields are written
// Optional
@XmlType(propOrder = { "material", "size"})
public class Frame{
/*  @XmlElementWrapper(name = "model")
  @XmlElements
  ({
	  @XmlElement(name = "frame"),
	  @XmlElement(name = "fork"),
	  @XmlElement(name = "component"),
	  @XmlElement(name = "rating")
  })*/
  private ArrayList<String> material;
  private ArrayList<Integer> size;
  // If you like the variable name, e.g. "name", you can easily change this
  // name for your XML-Output:

  public ArrayList<String> getMaterial() {
	    return material;
	  }
  public void setMaterial(ArrayList<String> material) {
	    this.material = material;
	  }
  public void makeMaterial() {
	  material = new ArrayList<String>();
	  //return material;
	  }
  public ArrayList<Integer> getSize() {
	    return size;
	  }
public void setSize(ArrayList<Integer> size) {
	    this.size = size;
	  }
public void makeSize() {
	  size = new ArrayList<Integer>();
	  //return material;
	  }
} 
