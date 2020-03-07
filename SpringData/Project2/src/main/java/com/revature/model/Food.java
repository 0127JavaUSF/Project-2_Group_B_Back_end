package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Food")
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_id")
	private Integer foodId;

	@Column(name = "dish_name")
	private String dishName;

	@Column(name = "is_tasty")
	private Boolean isTasty;

	public Food() {
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public Boolean getIsTasty() {
		return isTasty;
	}

	public void setIsTasty(Boolean isTasty) {
		this.isTasty = isTasty;
	}

	public Food(Integer foodId, String dishName, Boolean isTasty) {
		super();
		this.foodId = foodId;
		this.dishName = dishName;
		this.isTasty = isTasty;
	}

	@Override
	public String toString() {
		return "Food [foodId=" + foodId + ", dishName=" + dishName + ", isTasty=" + isTasty + "]";
	}

}
