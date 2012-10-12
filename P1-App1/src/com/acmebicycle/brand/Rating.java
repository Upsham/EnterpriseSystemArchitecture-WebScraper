package com.acmebicycle.brand;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "rating")
@XmlType(propOrder = { "max", "current", "votes"})
public class Rating{

  private double max;
  private double current;
  private double votes;
  public double getMax() {
	    return max;
	  }
  public void setMax(double max) {
	    this.max = max;
	  }
  public double getCurrent() {
	    return current;
	  }
  public void setCurrent(double d) {
	    this.current = d;
	  }
  public double getVotes() {
	  	return votes;
  	  }
  public void setVotes(double votes) {
	  	this.votes = votes;
      }

  
} 
