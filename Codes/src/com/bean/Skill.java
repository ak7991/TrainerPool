package com.bean;

public class Skill {
	
	int skillId;
	String skillName;
	
	//Empty Constructor
	public Skill() {
		super();
	}
	
	//Constructor (int, String)
	public Skill(int skillId, String skillName) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
	}
	
	//Getters and Setters
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", skillName=" + skillName + "]";
	}
}
