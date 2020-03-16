package com.revature.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//TODO Modify localhost
import org.springframework.web.bind.annotation.RestController;

import com.revature.dao.ListingDao;
import com.revature.dao.S3Dao;
import com.revature.model.Listing;
import com.revature.service.ListingService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ListingController {
	
	private ListingService listingService;
	
	private Logger logger = Logger.getRootLogger();

	public ListingController() {}
	
	@Autowired
	public ListingController(ListingService listingService) {
		super();
		this.listingService = listingService;
	}

	@GetMapping(value="/listing.app", produces="application/json", params= {"id"})
	public Listing findListingById(int id) {
		
		return listingService.findListingById(id);
	}

	@GetMapping(value="/listing/find-by-user.app", produces="application/json")
	public ResponseEntity<List<Listing>> findAllByUser(@CookieValue(value = "token", defaultValue = "") String token) {
				
		return listingService.findAllByUser(token);
	}
	
	@GetMapping(value="/listing/search.app", produces="application/json", params= {"page", "type", "city", "state"})
	public Page<Listing> findListingByTypeAndCity(int page, int type, String city, String state) {
		
		return listingService.findListingByTypeAndCity(page, type, city, state);
	}
	
	@PostMapping(value="/listing/create.app", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Listing> createListing(@RequestBody @Valid Listing listing, @CookieValue(value = "token", defaultValue = "") String token) {
		
		return listingService.createListing(listing, token);
	}
}
