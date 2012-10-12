package com.acmebicycle.brand;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "component")
// If you want you can define the order in which the fields are written
// Optional
public class Component{
/*  @XmlElementWrapper(name = "model")
  @XmlElements
  ({
	  @XmlElement(name = "frame"),
	  @XmlElement(name = "fork"),
	  @XmlElement(name = "component"),
	  @XmlElement(name = "rating")
  })*/
  private ArrayList<String> name = new ArrayList<String>();
  private ArrayList<String> value= new ArrayList<String>();
  // If you like the variable name, e.g. "name", you can easily change this
  // name for your XML-Output:
  @XmlAttribute
  public ArrayList<String> getName(){
	  return name;
  }
  public void setName(ArrayList<String> name){
	  this.name = name;
  }
  public void makeName(){
	  name = new ArrayList<String>();
  }
  @XmlValue
  public ArrayList<String> getValue() {
	    return value;
	  }
  public void setValue(ArrayList<String> value) {
	    this.value = value;
	  }
  public void makeValue() {
	  value = new ArrayList<String>();
	  //return material;
	  }
  
} 
