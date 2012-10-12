package com.acmebicycle.brand;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "components")
// If you want you can define the order in which the fields are written
// Optional
//@XmlType(propOrder = { "component"})
public class Components{
/*  @XmlElementWrapper(name = "model")
  @XmlElements
  ({
	  @XmlElement(name = "frame"),
	  @XmlElement(name = "fork"),
	  @XmlElement(name = "component"),
	  @XmlElement(name = "rating")
  })*/
  private ArrayList<String> name;
  private ArrayList<String> component;
  // If you like the variable name, e.g. "name", you can easily change this
  // name for your XML-Output:
  @XmlAttribute
  public ArrayList<String> getName(){
	  return name;
  }
  public void setName(ArrayList<String> name){
	  this.name = name;
  }
  @XmlValue
  public ArrayList<String> getComponent() {
	    return component;
	  }
  public void setComponent(ArrayList<String> component) {
	    this.component = component;
	  }
  public void makeComponent() {
	  component = new ArrayList<String>();
	  name = new ArrayList<String>();
	  //return material;
	  }
  
} 
