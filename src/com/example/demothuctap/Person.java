package com.example.demothuctap;

public class Person {
	String textName,textDescrition;
	int img;
	Person mPerson;
	public Person(){
		
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
	public Person getPerson(){
		return mPerson;
	}
	
}
