package com.acmebicycle.brand;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "fork")
// If you want you can define the order in which the fields are written
// Optional
@XmlType(propOrder = { "material"})
public class Fork{
/*  @XmlElementWrapper(name = "model")
  @XmlElements
  ({
	  @XmlElement(name = "frame"),
	  @XmlElement(name = "fork"),
	  @XmlElement(name = "component"),
	  @XmlElement(name = "rating")
  })*/
  private ArrayList<String> material;
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
  
} 
