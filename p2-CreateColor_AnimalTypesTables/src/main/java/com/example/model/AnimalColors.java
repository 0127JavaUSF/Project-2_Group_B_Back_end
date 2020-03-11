package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
/* 
 * id, type_id,colorName
 * 
 *  */

public class AnimalColors {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int colorId;
	
	@Column(nullable=false)
	private int animalTypeId;
	
	@Column(nullable=false)
	private String colorName;
	
	//public no args constructor
	public AnimalColors() {}
	

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public int getAnimalTypeId() {
		return animalTypeId;
	}

	public void setAnimalTypeId(int animalTypeId) {
		this.animalTypeId = animalTypeId;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	@Override
	public String toString() {
		return "AnimalColors[colorId=" + colorId + ", animalTypeId=" + animalTypeId + ", colorName=" + colorName + "]";
	}
	
	public AnimalColors(int colorId, int animalTypeId, String colorName) {
		this.colorId = colorId;
		this.animalTypeId = animalTypeId;
		this.colorName = colorName;
	}
	public AnimalColors(int animalTypeId, String colorName) {
		this.animalTypeId = animalTypeId;
		this.colorName = colorName;
	}
	
	
	
	
	
	
	
}
