package com.revature.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//TODO Modify localhost
import org.springframework.web.bind.annotation.RestController;

import com.revature.dao.ListingDao;
import com.revature.model.Listing;
import com.revature.model.User;
import com.revature.service.UserService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ListingController {
	
	private ListingDao listingDao;
	
	public ListingController() {}
	
	@Autowired
	public ListingController(ListingDao listingDao) {
		super();
		this.listingDao = listingDao;
	}

//	@GetMapping("/api/v1/listing.app")
//	public @ResponseBody Optional<Listing>  findListingById(Integer id) {
//		return listingDao.findById(id);		
//	}
//	
//	@PostMapping("/api/v1/application")
//	public Listing createApplication(@RequestBody Listing listing) {
//		return listingDao.save(listing);
//	}

	@GetMapping(value="/listing.app", produces="application/json", params= {"id"})
	public Listing findListingById(int id) {
		
		Optional<Listing> res =  listingDao.findById(id);
		if(res.isPresent() == false)
			return null;
		
		return res.get();
	}
	
	@GetMapping(value="/listing/find-by-user.app", produces="application/json")
	public ResponseEntity<List<Listing>> findAllByUser(@CookieValue(value = "token", defaultValue = "") String token) {
				
		int userId = UserService.getUserIdFromJWT(token);
		if(userId < 1) {
			return ResponseEntity
					.status(401)
					.body(null);
		}
		
		User user = new User();
		user.setId(userId);
		
		List<Listing> listings =  listingDao.findAllByUser(user);

		return ResponseEntity
				.status(200)
				.body(listings);
	}
	
	@GetMapping(value="/listing/search.app", produces="application/json", params= {"page", "type", "city"})
	public Page<Listing> findListingByTypeAndCity(int page, int type, String city) {
		
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
}
