package com.revature.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.model.Food;

public interface FoodDao extends CrudRepository<Food, Integer>{
	
	public Food findByFoodId(Integer foodId);
	
	public List<Food> findAll();

}
