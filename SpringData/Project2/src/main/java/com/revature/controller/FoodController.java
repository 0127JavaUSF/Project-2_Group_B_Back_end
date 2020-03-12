package com.revature.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.dao.FoodDao;
import com.revature.dao.ListingRepository;
import com.revature.dao.TemplateDao;
import com.revature.dao.UserDao;
import com.revature.model.Food;
import com.revature.model.Listing;


import com.revature.model.Template;

import com.revature.model.User;

@Controller
@CrossOrigin(origins="http://localhost:4200") 
public class FoodController {

	@Autowired
	private FoodDao foodDao;
	
	@Autowired
	private ListingRepository listingDao;
	
	@Autowired
	private TemplateDao templateDao;

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
	public @ResponseBody Listing findListingById(int id) {
		
		Optional<Listing> res =  listingDao.findById(id);
		if(res.isPresent() == false)
			return null;
		
		return res.get();
	}
	// call function I just made.
	// return a list using findbyuserid.
	// getting all the listings that match the userId (all the listings made by the same user).
	@GetMapping(value="/listing/find-by-user.app", produces="application/json")
	public @ResponseBody List<Listing> findAllByUser() {
		User user = new User();
		user.setId(1); //test.getuserfromsession
		
		List<Listing> listings =  listingDao.findAllByUser(user);

		return listings;
	}
	
	@GetMapping(value="/listing/search.app", produces="application/json", params= {"page", "type", "city"})
	public @ResponseBody Page<Listing> findListingByTypeAndCity(int page, int type, String city) {
		
		//Pageable pageable = PageRequest.of(0, 8, Sort.by("city").descending());
		Pageable pageable = PageRequest.of(page, 2);
		
		if(type > 0 && city.isEmpty() == false) {
			return listingDao.findByTypeAndCityContainingIgnoreCase(type, city, pageable);
		}
		else if(type > 0) {
			return listingDao.findByType(type, pageable);
		}
		else if(city.isEmpty() == false) {
			return listingDao.findByCityContainingIgnoreCase(city, pageable);
		}
		else {
			return listingDao.findAll(pageable);
		}
	}
	
	@PostMapping(value="/template/create.app", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Template> createTemplate(@RequestBody @Valid Template template) {
		
		//test
		template.getUser().setId(1);
		
		Timestamp ts = new Timestamp(Instant.now().toEpochMilli());
		template.setDate(ts);
	    		
		Template createdTemplate = templateDao.save(template);
		
		createdTemplate.setUser(null); //do not return user
		
		return ResponseEntity
				.status(201)
				.body(createdTemplate);
	}
	
	@GetMapping(value="/template.app", produces="application/json", params= {"listingId"})
	public @ResponseBody Template findTemplateByListingId(int listingId) {
		
		Listing listing = new Listing();
		listing.setId(listingId);
				
		return templateDao.findByListing(listing);
	}
}
