package com.revature.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
//TODO Modify localhost
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.dao.ListingDao;
import com.revature.model.Application;
import com.revature.model.Listing;
import com.revature.model.User;
import com.revature.service.ListingService;
import com.revature.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ListingController {
			
	private ListingDao listingDao;
	
	private ListingService listingService;
	
	private Logger logger = Logger.getRootLogger();

	public ListingController() {}
	
	@Autowired
	public ListingController(ListingDao listingDao) {
		super();
		this.listingDao = listingDao;
	}

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
		
		for(Listing list : listings) {
			
			list.setDateString( new SimpleDateFormat("MM/dd/yyyy").format(list.getDate()) );
		}

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
	
	@PostMapping(value="/listing/create.app", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Listing> createListing(@RequestBody @Valid Listing listing, @CookieValue(value = "token", defaultValue = "") String token) {
		
		int userId = UserService.getUserIdFromJWT(token);
		if(userId < 1) {
			return ResponseEntity
					.status(401)
					.body(null);
		}
		
		//userId on listing object can't be null
		User user = new User();
		user.setId(userId);
		listing.setUser(user);
		
		return ResponseEntity
				.status(201)
				.body(listingDao.save(listing));
		
		//for some reason listingService is null and the bean does not inject
//		return ResponseEntity
//				.status(201)
//				.body(listingService.create(listing));
	}
	
	//JL test methods
//	@GetMapping("/listing/{id}")
//	public @ResponseBody Listing  findListingById(@PathVariable Integer id) {
//		logger.debug("@PathVariable Integer id: "+id);
//		return listingService.findById(id);		
//	}
//	
//	@GetMapping("/listingAll")
//	public @ResponseBody List<Listing>  findAllLists() {
//		return listingService.findAllListing();		
//	}
//		
//	@ExceptionHandler(HttpClientErrorException.class)
//	public ResponseEntity<String> handleClientError(HttpClientErrorException e) {
//		return ResponseEntity
//				.status(e.getStatusCode())
//				.body(String.format("{\"message\": \"%s\"}", e.getMessage()));
//	}
//	
//	@PostMapping("/newlisting")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Listing create(@RequestBody Listing listing) {
//		return listingService.create(listing);
//	}
//	
//	/* Used to test: successful */
//	@GetMapping("/test/**")
//	public String handleGet() {
//		logger.info("Request mapped with HandleGet");
//		return "Success!";
//	}
}
