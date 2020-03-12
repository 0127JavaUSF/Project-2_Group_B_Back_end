package com.revature.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ListingController {
	@Autowired
	ListingDao listingDao;
	
	@GetMapping("/listing.app")
	public @ResponseBody Optional<Listing>  findListingById(Integer id) {
		return listingDao.findById(id);		
	}
	
	@PostMapping("/application")
	public Listing createApplication(@RequestBody Listing listing) {
		return listingDao.save(listing);
	}

}
