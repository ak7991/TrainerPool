package com.bean;

import java.util.Date;

public class SME {
	
	String sFname,sLname;
	int sAge;
	String sGender;
	String sContactNumber,smail,sUsername,sPassword;
	Date sDateOfBegin,sDateOfEnd;
	int SMEId,sStatus;
	
	//Empty Constructor
	public SME() {
	super();
	}
	
	//Constructor(String X2, int, String X5, int X2)
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

	//Constructor(String X2, int, String X5, Date X2 int X2)
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

	//Getters and Setters
	public String getSmail() {
		return smail;
	}

	public void setSmail(String smail) {
		this.smail = smail;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + SMEId;
		result = prime * result + ((sUsername == null) ? 0 : sUsername.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SME other = (SME) obj;
		if (SMEId != other.SMEId)
			return false;
		if (sUsername == null) {
			if (other.sUsername != null)
				return false;
		} else if (!sUsername.equals(other.sUsername))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SME [sFname=" + sFname + ", sLname=" + sLname + ", sAge=" + sAge + ", sGender=" + sGender
				+ ", sContactNumber=" + sContactNumber + ", smail=" + smail + ", sUsername=" + sUsername
				+ ", sPassword=" + sPassword + ", sDateOfBegin=" + sDateOfBegin + ", sDateOfEnd=" + sDateOfEnd
				+ ", SMEId=" + SMEId + ", sStatus=" + sStatus + "]";
	}
}
