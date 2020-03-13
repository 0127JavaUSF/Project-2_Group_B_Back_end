package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AnimalGender {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int genderId;
	
	@Column(nullable=false)
	private String genderName;
	
	//public no args constructor
	public AnimalGender() {}

	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	@Override
	public String toString() {
		return "AnimalGender [genderId=" + genderId + ", genderName=" + genderName + "]";
	}

	public AnimalGender(int genderId, String genderName) {
		super();
		this.genderId = genderId;
		this.genderName = genderName;
	}
	
	public AnimalGender(String genderName) {
		super();		
		this.genderName = genderName;
	}
}
