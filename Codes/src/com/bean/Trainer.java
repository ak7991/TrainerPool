package com.bean;

import java.util.Date;

public class Trainer{
	
	String tFname,tLname;
	int tAge;
	String tGender;
	String tContactNumber,tmail,tUsername,tPassword;
	Date tDateOfBegin,tDateOfEnd;
	int trainerId,tStatus;
	
	//Empty Constructor
	public Trainer() {
		super();
	}
	
	//Constructor (String X2, int, String X5, Date X2, int X2)
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
	
	//Constructor (String X2, int, String X5,int X2)
	public Trainer(String tFname, String tLname, int tAge, String tGender, String tContactNumber,
			String tmail, String tUsername, String tPassword, int trainerId, int tStatus) {
		// TODO Auto-generated constructor stub
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

	//Getters and Setters                        
	public String getTmail() {
		return tmail;
	}
	public void setTmail(String tmail) {
		this.tmail = tmail;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tUsername == null) ? 0 : tUsername.hashCode());
		result = prime * result + trainerId;
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
		Trainer other = (Trainer) obj;
		if (tUsername == null) {
			if (other.tUsername != null)
				return false;
		} else if (!tUsername.equals(other.tUsername))
			return false;
		if (trainerId != other.trainerId)
			return false;
		return true;
	}

	@Override
	public Trainer clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		Trainer t = new Trainer();
		t.settAge(this.gettAge());
		t.settContactNumber(this.gettContactNumber());
		t.settDateOfBegin(this.gettDateOfBegin());
		t.settDateOfEnd(this.gettDateOfEnd());
		t.settFname(this.gettFname());
		t.settLname(this.gettLname());
		t.settGender(this.gettGender());
		t.setTmail(this.getTmail());
		t.settPassword(this.gettPassword());
		t.setTrainerId(this.getTrainerId());
		t.settStatus(this.gettStatus());
		t.settUsername(this.gettUsername());
		
		return t;
	}

	//Testing purposes
	@Override
	public String toString() {
		return "Trainer [tDateOfBegin=" + tDateOfBegin + ", tDateOfEnd=" + tDateOfEnd
				+ ", trainerId=" + trainerId + ", tStatus=" + tStatus + "]\n";
	}
}
