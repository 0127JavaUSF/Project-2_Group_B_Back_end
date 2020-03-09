package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.dao.FoodDao;
import com.revature.model.Food;

@Controller
public class FoodController {

	@Autowired
	private FoodDao foodDao;

	@GetMapping("/food.app")
	public @ResponseBody Food findFood() {
		return foodDao.findByFoodId(1);
	}

	//to change page size:
	//url?size=5&page=2 //5 per page, on page 2
	@GetMapping("/allfood.app")
	public @ResponseBody Page<Food> findAllFood(Pageable pageable) {
		return foodDao.findAll(pageable);
	}

	// @PostMapping("/employees")
	// public Employee createEmployee(@RequestBody Employee employee){
	// return employeeRepo.save(employee);
	// }

	@GetMapping("/insert.app")
	public @ResponseBody Food insert() {

		Food food = new Food(0, "double cheeseburger", true);
		return foodDao.save(food);
	}
}
