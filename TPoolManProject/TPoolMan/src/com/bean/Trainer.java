package com.bean;

import java.util.Date;

public class Trainer {
	
	@Override
	public String toString() {
		return "Trainer [tFname=" + tFname + ", tLname=" + tLname + ", tAge=" + tAge + ", tGender=" + tGender
				+ ", tContactNumber=" + tContactNumber + ", tmail=" + tmail + ", tUsername=" + tUsername
				+ ", tPassword=" + tPassword + ", tDateOfBegin=" + tDateOfBegin + ", tDateOfEnd=" + tDateOfEnd
				+ ", trainerId=" + trainerId + ", tStatus=" + tStatus + "]";
	}
	String tFname,tLname;
	int tAge;
	String tGender;
	String tContactNumber,tmail,tUsername,tPassword;
	Date tDateOfBegin,tDateOfEnd;
	int trainerId,tStatus;
	
	
	public Trainer(String tFname, String tLname, int tAge, String tGender, String tContactNumber, String tmail,
			String tUsername, String tPassword, int trainerId, int tStatus) {
		super();
		this.tFname = tFname;
		this.tLname = tLname;
		this.tAge = tAge;
		this.tGender = tGender;
		this.tContactNumber = tContactNumber;
		this.tmail = tmail;
		this.tUsername = tUsername;
		this.tPassword = tPassword;
		this.trainerId = trainerId;
		this.tStatus = tStatus;
	}

	public Trainer(String tFname, String tLname, int tAge, String tGender, String tContactNumber, String tmail,
			String tUsername, String tPassword, Date tDateOfBegin, Date tDateOfEnd, int trainerId, int tStatus) {
		super();
		this.tFname = tFname;
		this.tLname = tLname;
		this.tAge = tAge;
		this.tGender = tGender;
		this.tContactNumber = tContactNumber;
		this.tmail = tmail;
		this.tUsername = tUsername;
		this.tPassword = tPassword;
		this.tDateOfBegin = tDateOfBegin;
		this.tDateOfEnd = tDateOfEnd;
		this.trainerId = trainerId;
		this.tStatus = tStatus;
	}

	public String getTmail() {
		return tmail;
	}

	public void setTmail(String tmail) {
		this.tmail = tmail;
	}
	
	public Trainer() {
	}

	public String gettFname() {
		return tFname;
	}
	public void settFname(String tFname) {
		this.tFname = tFname;
	}
	public String gettLname() {
		return tLname;
	}
	public void settLname(String tLname) {
		this.tLname = tLname;
	}
	public int gettAge() {
		return tAge;
	}
	public void settAge(int tAge) {
		this.tAge = tAge;
	}
	public String gettGender() {
		return tGender;
	}
	public void settGender(String tGender) {
		this.tGender = tGender;
	}
	public String gettContactNumber() {
		return tContactNumber;
	}
	public void settContactNumber(String tContactNumber) {
		this.tContactNumber = tContactNumber;
	}
	public String gettUsername() {
		return tUsername;
	}
	public void settUsername(String tUsername) {
		this.tUsername = tUsername;
	}
	public String gettPassword() {
		return tPassword;
	}
	public void settPassword(String tPassword) {
		this.tPassword = tPassword;
	}
	public Date gettDateOfBegin() {
		return tDateOfBegin;
	}
	public void settDateOfBegin(Date tDateOfBegin) {
		this.tDateOfBegin = tDateOfBegin;
	}
	public Date gettDateOfEnd() {
		return tDateOfEnd;
	}
	public void settDateOfEnd(Date tDateOfEnd) {
		this.tDateOfEnd = tDateOfEnd;
	}
	public int getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}
	public int gettStatus() {
		return tStatus;
	}
	public void settStatus(int tStatus) {
		this.tStatus = tStatus;
	}
	
	

}
