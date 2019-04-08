package com.controller;

public class Email {
	String eid;
	String message;
	
	public Email(String eid, String message) {
		super();
		this.eid = eid;
		this.message = message;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Email [eid=" + eid + ", message=" + message + "]";
	}
	
	

}
