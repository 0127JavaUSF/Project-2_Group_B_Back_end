package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AnimalBreeds {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int breedId;
	
	@Column(nullable=false)
	private int animalTypeId;
	
	@Column(nullable=false)
	private String breedName;
	
	//public no args constructor
	public AnimalBreeds() {}

	public int getBreedId() {
		return breedId;
	}

	public void setBreedId(int breedId) {
		this.breedId = breedId;
	}

	public int getAnimalTypeId() {
		return animalTypeId;
	}

	public void setAnimalTypeId(int animalTypeId) {
		this.animalTypeId = animalTypeId;
	}

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}

	@Override
	public String toString() {
		return "AnimalBreeds [breedId=" + breedId + ", animalTypeId=" + animalTypeId + ", breedName=" + breedName + "]";
	}

	public AnimalBreeds(int breedId, int animalTypeId, String breedName) {
		super();
		this.breedId = breedId;
		this.animalTypeId = animalTypeId;
		this.breedName = breedName;
	}
	
	public AnimalBreeds(int animalTypeId, String breedName) {
		super();		
		this.animalTypeId = animalTypeId;
		this.breedName = breedName;
	}
	

}
