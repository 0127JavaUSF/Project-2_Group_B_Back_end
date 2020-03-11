package com.revature.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.dao.FoodDao;
import com.revature.dao.ListingDao;
import com.revature.dao.UserDao;
import com.revature.model.Food;
import com.revature.model.Listing;
import com.revature.model.User;

@Controller
@CrossOrigin(origins="http://localhost:4200") 
public class FoodController {

	@Autowired
	private FoodDao foodDao;
	
	@Autowired
	private ListingDao listingDao;
	
	@Autowired
	private UserDao userDao;

	@GetMapping("/food.app")
	public @ResponseBody Food findFood() {
		return foodDao.findByFoodId(1);
	}
	
	@PostMapping("/user.app")
	public @ResponseBody User findUser() {
		return userDao.findByUsername("henry");
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
	
	@GetMapping(value="/listing.app", produces="application/json", params= {"id"})
	public @ResponseBody Listing findById(int id) {
		
		Optional<Listing> res =  listingDao.findById(id);
		if(res.isPresent() == false)
			return null;
		
		return res.get();
	}
	
	@GetMapping(value="/listing/search.app", produces="application/json", params= {"page", "type", "city"})
	public @ResponseBody Page<Listing> findByTypeAndCity(int page, int type, String city) {
		
		//Pageable pageable = PageRequest.of(0, 16, Sort.by("city").descending());
		Pageable pageable = PageRequest.of(page, 16);
		
		if(type > 0 && city.isEmpty() == false) {
			return listingDao.findByTypeAndCity(type, city, pageable);
		}
		else if(type > 0) {
			return listingDao.findByType(type, pageable);
		}
		else if(city.isEmpty() == false) {
			return listingDao.findByCity(city, pageable);
		}
		else {
			return listingDao.findAll(pageable);
		}
	}
}
