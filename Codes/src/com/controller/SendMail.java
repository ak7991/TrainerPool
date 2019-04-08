package com.controller;

public class SendMail<T> {

	T msg;

	public void setMessage(T msg) 
	{
		this.msg=msg;
		
	}
	
	public T getMessage()
	{
		return msg;
	}
	
	
	

}
