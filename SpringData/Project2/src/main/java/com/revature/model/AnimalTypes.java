package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Check;


/*
 * type_id not null serial, type_name varchar(30)
 * 
 */

@Entity
public class AnimalTypes {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int typeId;
	@Check(constraints="typeId > 0")
	
	@Column(nullable=false)
	private String typeName;
	
	//no args constructor
	public AnimalTypes() {}
	
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "AnimalType [typeId=" + typeId + ", typeName=" + typeName + "]";
	}

	public AnimalTypes(int typeId, String typeName) {
		//NB: AnimalType constructor: calling the parent constructor
		this.typeId = typeId;
		this.typeName = typeName;
	}
	
	public AnimalTypes(String typeName) {
		//NB: AnimalType constructor: calling the parent constructor
		this.typeName = typeName;
	}
}
