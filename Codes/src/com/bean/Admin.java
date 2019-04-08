package com.bean;

public class Admin {
	
	String aUsername,aPassword;

	//Empty Constructor
	public Admin() {
		super();
	}

	//Constructor (String, String)
	public Admin(String aUsername, String aPassword) {
		super();
		this.aUsername = aUsername;
		this.aPassword = aPassword;
	}

	//Getters and Setters
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

	@Override
	public String toString() {
		return "Admin [aUsername=" + aUsername + ", aPassword=" + aPassword + "]";
	}

}
