package com.acmebicycle.brand;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "model")
@XmlType(propOrder = { "name" ,"price", "year", "frame" , "fork", "components", "rating" })
public class Model {
  private Frame frame;
  private Fork fork;
  private ArrayList<Components> components;
  private Rating rating;
  private String price;
  private String name;
  private int year;
  public int getYear() {
	    return year;
	  }

	  public void setYear(int year) {
	    this.year = year;
	  }
  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }
  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }
  public Frame getFrame(){
	  return frame;
  }
  public void setFrame(Frame frame){
	  this.frame = frame;
  }
  public void makeFrame(){
	  frame = new Frame();
  }
  public Fork getFork(){
	  return fork;
  }
  public void setFork(Fork fork){
	  this.fork = fork;
  }
  public void makeFork(){
	  fork = new Fork();
  }
  public ArrayList<Components> getComponents(){
	  return components;
  }
  public void setComponents(ArrayList<Components> components){
	  this.components = components;
  }
  public void makeComponents(int i){
	  components = new ArrayList<Components>();
	  while(i>0)
	  {
		  Components comp = new Components();
		  components.add(comp);
		  i--;
	  }
  }
  public Rating getRating(){
	  return rating;
  }
  public void setRating(Rating rating){
	  this.rating = rating;
  }
  public void makeRating(){
	  rating = new Rating();
  }
} 
