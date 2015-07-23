package com.example.demothuctap;
/**
 * 
 * @author dnp_it
 * The Object Person data of listview
 */
public class Person {
	String textName,textDescrition;
	int img;
	/**
	 * this constructor object Person
	 * @param img the image of person
	 * @param textName the name of person
	 * @param textDescription the description of person
	 */
	public Person(int img,String textName,String textDescription){
		this.textName = textName;
		this.textDescrition = textDescription;
		this.img = img;
	}
	protected String getTextName() {
		return textName;
	}
	protected String getTextDescrition() {
		return textDescrition;
	}
	protected int getImg() {
		return img;
	}
	protected void setTextName(String textName) {
		this.textName = textName;
	}
	protected void setTextDescrition(String textDescrition) {
		this.textDescrition = textDescrition;
	}
	protected void setImg(int img) {
		this.img = img;
	}
	
}
