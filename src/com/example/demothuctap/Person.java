package com.example.demothuctap;
/**
 * 
 * @author dnp_it
 * The Object Person data of listview
 */
public class Person {
	private String mTextName,mTextDescrition;
	private int mImg;
	/**
	 * this constructor object Person
	 * @param img the image of person
	 * @param textName the name of person
	 * @param textDescription the description of person
	 */
	protected Person(int img,String textName,String textDescription){
		this.mTextName = textName;
		this.mTextDescrition = textDescription;
		this.mImg = img;
	}
	protected String getTextName() {
		return mTextName;
	}
	protected String getTextDescrition() {
		return mTextDescrition;
	}
	protected int getImg() {
		return mImg;
	}
	protected void setTextName(String textName) {
		this.mTextName = textName;
	}
	protected void setTextDescrition(String textDescrition) {
		this.mTextDescrition = textDescrition;
	}
	protected void setImg(int img) {
		this.mImg = img;
	}
	
}
