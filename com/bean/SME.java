package com.bean;

import java.util.Date;

public class SME {
	
	String sFname,sLname;
	int sAge;
	String sGender;
	String sContactNumber,smail,sUsername,sPassword;
	Date sDateOfBegin,sDateOfEnd;
	int SMEId,sStatus;
	
	
	public SME(String sFname, String sLname, int sAge, String sGender, String sContactNumber, String smail,
			String sUsername, String sPassword, int sMEId, int sStatus) {
		super();
		this.sFname = sFname;
		this.sLname = sLname;
		this.sAge = sAge;
		this.sGender = sGender;
		this.sContactNumber = sContactNumber;
		this.smail = smail;
		this.sUsername = sUsername;
		this.sPassword = sPassword;
		SMEId = sMEId;
		this.sStatus = sStatus;
	}

	public SME(String sFname, String sLname, int sAge, String sGender, String sContactNumber, String smail,
			String sUsername, String sPassword, Date sDateOfBegin, Date sDateOfEnd, int sMEId, int sStatus) {
		super();
		this.sFname = sFname;
		this.sLname = sLname;
		this.sAge = sAge;
		this.sGender = sGender;
		this.sContactNumber = sContactNumber;
		this.smail = smail;
		this.sUsername = sUsername;
		this.sPassword = sPassword;
		this.sDateOfBegin = sDateOfBegin;
		this.sDateOfEnd = sDateOfEnd;
		SMEId = sMEId;
		this.sStatus = sStatus;
	}

	public String getSmail() {
		return smail;
	}

	public void setSmail(String smail) {
		this.smail = smail;
	}
	
	public SME() {
	}
	
	public String getsFname() {
		return sFname;
	}
	public void setsFname(String sFname) {
		this.sFname = sFname;
	}
	public String getsLname() {
		return sLname;
	}
	public void setsLname(String sLname) {
		this.sLname = sLname;
	}
	public int getsAge() {
		return sAge;
	}
	public void setsAge(int sAge) {
		this.sAge = sAge;
	}
	public String getsGender() {
		return sGender;
	}
	public void setsGender(String sGender) {
		this.sGender = sGender;
	}
	public String getsContactNumber() {
		return sContactNumber;
	}
	public void setsContactNumber(String sContactNumber) {
		this.sContactNumber = sContactNumber;
	}
	public String getsUsername() {
		return sUsername;
	}
	public void setsUsername(String sUsername) {
		this.sUsername = sUsername;
	}
	public String getsPassword() {
		return sPassword;
	}
	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}
	public Date getsDateOfBegin() {
		return sDateOfBegin;
	}
	public void setsDateOfBegin(Date sDateOfBegin) {
		this.sDateOfBegin = sDateOfBegin;
	}
	public Date getsDateOfEnd() {
		return sDateOfEnd;
	}
	public void setsDateOfEnd(Date sDateOfEnd) {
		this.sDateOfEnd = sDateOfEnd;
	}
	public int getSMEId() {
		return SMEId;
	}
	public void setSMEId(int sMEId) {
		SMEId = sMEId;
	}
	public int getsStatus() {
		return sStatus;
	}
	public void setsStatus(int sStatus) {
		this.sStatus = sStatus;
	}
	

}
