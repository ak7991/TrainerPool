package com.bean;

public class Admin {
	
	String aUsername,aPassword;

	public Admin() {
		super();
	}

	public Admin(String aUsername, String aPassword) {
		super();
		this.aUsername = aUsername;
		this.aPassword = aPassword;
	}

	public String getaUsername() {
		return aUsername;
	}

	public void setaUsername(String aUsername) {
		this.aUsername = aUsername;
	}

	public String getaPassword() {
		return aPassword;
	}

	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}

}
