package com.examples.course.needone.Database.Beans;

import java.sql.Date;

public class Users{

	private String USERNAME;
	private Date DOB;
	private String PASSWORD;
	private String PICURL;
	private int credit;

	
	public Users(){

	}
	
		
	public void setUsername(String USERNAME){
		this.USERNAME=USERNAME;
	}

	public String getUsername(){
		return USERNAME;
	}
	
	public String getUrl(){
		return PICURL;
	}
	public void setUrl(String PICURL){
		this.PICURL=PICURL;
	}

	public Date getDob(){
		return DOB;
	}
	public void setDob(Date DOB){
		this.DOB=DOB;
	}

	
	public String getPassword() {
		return PASSWORD;
	}

	public void setPassword(String PASSWORD) {
		this.PASSWORD = PASSWORD;
	}
	
	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	
}
