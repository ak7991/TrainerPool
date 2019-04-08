package com.controller;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SendMail<String> s= new SendMail<String>();
		s.setMessage("Hello Akshay");
		System.out.println(s.getMessage());
		Email e = new Email("xyz@gamil.com","Hi Gmail");
		SendMail<Email> s1 = new SendMail<Email>();
		s1.setMessage(e);
		System.out.println(s1.getMessage());
	}

}
